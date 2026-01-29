package repository.interfaces;

import entity.Appointment;
import java.util.List;

public interface IAppointmentRepository {
    boolean makeAppointment(Appointment appointment);
    List<String> getAllAppointments();
}