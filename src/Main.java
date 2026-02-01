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
        System.out.println("=== Welcome to the Advanced Hotel System ===");
        
        System.out.print("Enter your phone number to login/register: ");
        String phone = scanner.next();

        if (!phone.matches("\\d+") || phone.length() < 5) {
            System.out.println("Invalid phone format! Exiting.");
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
                System.out.println("1. View Available Rooms");
                System.out.println("2. Book a Room");
                
                if (currentUser.getRole().equals("ADMIN")) {
                    System.out.println("3. View All Reservations (Admin Only)");
                }
                
                System.out.println("0. Exit");
                System.out.print("Select: ");
                
                int choice = scanner.nextInt();

                if (choice == 1) {
                    roomRepo.getAllRooms().forEach(room -> System.out.println(room));
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
                        resRepo.getAllReservationsFullInfo().forEach(info -> System.out.println(info));
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