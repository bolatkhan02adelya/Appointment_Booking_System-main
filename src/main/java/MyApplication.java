import controllers.interfaces.IUserController;
import repository.interfaces.IServiceRepository;
import service.AppointmentService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApplication {
    private final Scanner scanner = new Scanner(System.in);
    private final IUserController userController;
    private final IServiceRepository serviceRepo;
    private final AppointmentService appointmentService;

    public MyApplication(IUserController userController, IServiceRepository serviceRepo, AppointmentService appointmentService) {
        this.userController = userController;
        this.serviceRepo = serviceRepo;
        this.appointmentService = appointmentService;
    }

    public void start() {
        while (true) {
            System.out.println("\n===== SYSTEM MENU =====");
            System.out.println("1. Get all users");
            System.out.println("2. Create user");
            System.out.println("3. View services and slots");
            System.out.println("4. Book appointment");
            System.out.println("5. View appointments");
            System.out.println("6. View full appointment details by id");
            System.out.println("0. Exit");
            System.out.print("Option: ");

            try {
                int option = scanner.nextInt();
                switch (option) {
                    case 1 -> System.out.println(userController.getAllUsers());
                    case 2 -> createUserMenu();
                    case 3 -> showServicesAndSlots();
                    case 4 -> createBookingMenu();
                    case 5 -> appointmentService.getAllAppointments().forEach(System.out::println);
                    case 6 -> viewFullAppointmentMenu();
                    case 0 -> { return; }
                    default -> System.out.println("Invalid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Enter a number.");
                scanner.nextLine();
            }
        }
    }

    private void createUserMenu() {
        System.out.print("Name: ");
        String name = scanner.next();
        System.out.print("Surname: ");
        String surname = scanner.next();
        System.out.print("Gender (true=male/false=female): ");
        try {
            boolean gender = scanner.nextBoolean();
            System.out.println(userController.createUser(name, surname, gender));
        } catch (InputMismatchException e) {
            System.out.println("Error: Gender must be 'true' or 'false'.");
            scanner.nextLine();
        }
    }

    private void showServicesAndSlots() {
        System.out.println("\n--- Services ---");
        serviceRepo.getAllServices().forEach(s ->
                System.out.println(s.getId() + ": " + s.getName() + " (" + s.getPrice() + ")"));

        System.out.println("\n--- Free Slots ---");
        appointmentService.getAvailableSlots().forEach(ts ->
                System.out.println(ts.getId() + ": " + ts.getSlotTime()));
    }

    private void createBookingMenu() {
        System.out.print("User ID: ");
        int uId = scanner.nextInt();
        System.out.print("Service ID: ");
        int sId = scanner.nextInt();
        System.out.print("Slot ID: ");
        int slotId = scanner.nextInt();
        System.out.println(appointmentService.book(uId, sId, slotId));
    }
    private void viewFullAppointmentMenu() {
        System.out.print("Enter appointment id: ");
        try {
            int id = scanner.nextInt();

            var dto = appointmentService.getFullAppointment(id);

            if (dto == null) {
                System.out.println("Appointment not found!");
                return;
            }

            System.out.println("===== APPOINTMENT DETAILS =====");
            System.out.println("Appointment ID: " + dto.getAppointmentId());
            System.out.println("User: " + dto.getUserName());
            System.out.println("Service: " + dto.getServiceName());
            System.out.println("Time: " + dto.getSlotTime());

        } catch (InputMismatchException e) {
            System.out.println("Error: Enter a number.");
            scanner.nextLine();
        }
    }

}
