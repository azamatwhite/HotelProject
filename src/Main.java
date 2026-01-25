import controllers.HotelController;
import models.User;
import models.Room;
import repositories.RoomRepository;
import repositories.UserRepository;
import repositories.ReservationRepository;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepo = new UserRepository();
        RoomRepository roomRepo = new RoomRepository();
        HotelController controller = new HotelController(roomRepo);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Hotel Booking System!");

        while (true) {
            try {
                System.out.println("\n--- MAIN MENU ---");
                System.out.println("1. View All Rooms");
                System.out.println("2. Book a Room");
                System.out.println("3. Find User by ID");
                System.out.println("0. Exit");
                System.out.print("Select an option: ");

                int choice = scanner.nextInt();

                if (choice == 1) {
                    System.out.println("\nAvailable Rooms:");
                    for (Room r : roomRepo.getAllRooms()) {
                        System.out.println(r);
                    }
                } 
                else if (choice == 2) {
                    System.out.print("Enter your name: ");
                    String name = scanner.next();
                    System.out.print("Enter your surname: ");
                    String surname = scanner.next();
                    System.out.print("Enter your phone: ");
                    String phone = scanner.next();

                    User user = new User(0, name, surname, phone);
                    userRepo.createUser(user);

                    System.out.println("User registered successfully!");

                    System.out.print("Enter Room ID: ");
                    int roomId = scanner.nextInt();

                    System.out.print("Дата заезда (2023-12-31): ");
                    String dateFrom = scanner.next();

                    System.out.print("Дата выезда (2024-01-05): ");
                    String dateTo = scanner.next();

                    String res = controller.makeReservation(user, roomId, dateFrom, dateTo);
                    System.out.println(res);

                } 
                else if (choice == 3) {
                    System.out.print("Enter User ID to find: ");
                    int userId = scanner.nextInt();
                    User foundUser = userRepo.getIdbyUser(userId);

                    if (foundUser != null) {
                        System.out.println("User found: " + foundUser);
                    } else {
                        System.out.println("User with ID " + userId + " not found.");
                    }
                } 
                else if (choice == 0) {
                    System.out.println("Goodbye!");
                    break; 
                } 
                else {
                    System.out.println("Invalid option. Try again.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid number!");
                scanner.next();
            }
        }
        scanner.close();
    }
}