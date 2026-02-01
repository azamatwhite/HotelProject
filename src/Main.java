import controllers.HotelController;
import models.User;
import models.Room;
import repositories.RoomRepository;
import repositories.UserRepository;
import repositories.ReservationRepository;
import repositories.interfaces.*;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        IUserRepository userRepo = new UserRepository();
        IRoomRepository roomRepo = new RoomRepository();
        IReservationRepository resRepo = new ReservationRepository();
        HotelController controller = new HotelController(roomRepo, resRepo);

        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Welcome to the Hotel System ===");

        System.out.print("Enter your phone number to login/register: ");
        String phone = scanner.next();

        if (!phone.matches("\\d{5}")) {
            System.out.println("Phone number must be 5 digits! Exiting.");
            return;
        }

        User currentUser = userRepo.getUserByPhone(phone);

        if (currentUser == null) {
            System.out.println("New user detected.");
            System.out.print("Enter name: ");
            String name = scanner.next();
            System.out.print("Enter surname: ");
            String surname = scanner.next();

            currentUser = new User(0, name, surname, phone, "CLIENT");
            currentUser = userRepo.createUser(currentUser);
        } else {
            System.out.println("Welcome back, " + currentUser.getName() + "!");
        }

        while (true) {
            try {
                System.out.println("\n--- MENU (" + currentUser.getRole() + ") ---");
                System.out.println("1. View available Rooms");
                System.out.println("2. Book a Room");

                if (currentUser.getRole().equals("ADMIN")) {
                    System.out.println("3. View All Reservations (Admin Only)");
                }

                System.out.println("0. Exit");
                System.out.print("Select: ");

                int choice = scanner.nextInt();

                if (choice == 1) {
                    System.out.println("Do you want to filter by dates? (1 - Yes, 2 - No, show all)");
                    int filterChoice = scanner.nextInt();

                    if (filterChoice == 1) {
                        System.out.print("Enter Check-in date (YYYY-MM-DD): ");
                        String dateFrom = scanner.next();
                        System.out.print("Enter Check-out date (YYYY-MM-DD): ");
                        String dateTo = scanner.next();

                        System.out.println("\n--- AVAILABLE ROOMS (" + dateFrom + " to " + dateTo + ") ---");
                        controller.getAvailableRooms(dateFrom, dateTo).forEach(System.out::println);
                    } else {
                        System.out.println("\n--- ALL ROOMS ---");
                        roomRepo.getAllRooms().forEach(System.out::println);
                    }
                }
                else if (choice == 2) {
                    System.out.print("Enter Room ID: ");
                    int roomId = scanner.nextInt();
                    System.out.print("Date From (YYYY-MM-DD): ");
                    String dateFrom = scanner.next();
                    System.out.print("Date To (YYYY-MM-DD): ");
                    String dateTo = scanner.next();

                    String result = controller.makeReservation(currentUser, roomId, dateFrom, dateTo);
                    System.out.println(result);
                }

                else if (choice == 3) {
                    if (currentUser.getRole().equals("ADMIN")) {
                        for (String info : resRepo.getAllReservationsFullInfo()) {
                            System.out.println(info);
                        }
                    } else {
                        System.out.println("Access Denied!");
                    }
                }
                else if (choice == 0) {
                    System.out.println("Goodbye!");
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid number!");
                scanner.next();
            }
        }
        scanner.close();
    }
}