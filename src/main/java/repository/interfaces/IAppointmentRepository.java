package repository.interfaces;

import entity.Appointment;
import entity.AppointmentDetailsDTO;
import java.util.List;

public interface IAppointmentRepository {
    boolean makeAppointment(Appointment appointment);
    List<String> getAllAppointments();
    AppointmentDetailsDTO getFullAppointment(int appointmentId);
}
