package repository.interfaces;

import entity.Appointment;

public interface IAppointmentRepository {
    boolean makeAppointment(Appointment appointment);
}