import config.PostgresDB;
import config.interfaces.IDB;
import controllers.UserController;
import controllers.interfaces.IUserController;
import repository.*;
import repository.interfaces.*;
import service.AppointmentService;

public class Main {
    public static void main(String[] args) {
        IDB db = new PostgresDB();

        IUserRepository userRepo = new UserRepository(db);
        IServiceRepository serviceRepo = new ServiceRepository(db);
        ITimeSlotRepository slotRepo = new TimeSlotRepository(db);
        IAppointmentRepository appointmentRepo = new AppointmentRepository(db);

        IUserController userController = new UserController(userRepo);
        AppointmentService appointmentService = new AppointmentService(appointmentRepo, slotRepo);

        MyApplication app = new MyApplication(userController, serviceRepo, appointmentService);
        app.start();
    }
}
