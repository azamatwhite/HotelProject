import controllers.HotelController;
import repositories.RoomRepository;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        
        RoomRepository roomRepo = new RoomRepository();
        HotelController controller = new HotelController(roomRepo);
        
       
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Hotel Booking System!");

        while (true) {
            try {
                
                System.out.println("\n--- MAIN MENU ---");
                System.out.println("1. Book a Room");
                System.out.println("0. Exit");
                System.out.print("Select an option: ");

                int choice = scanner.nextInt();

                if (choice == 1) {
                    
                    System.out.print("Enter Room ID: ");
                    int roomId = scanner.nextInt();

                    
                    System.out.print("Enter number of nights: ");
                    int nights = scanner.nextInt();

                    
                    String result = controller.makeReservation(roomId, nights);
                    System.out.println("\n" + result);

                } else if (choice == 0) {
                    System.out.println("Goodbye!");
                    break; 
                } else {
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