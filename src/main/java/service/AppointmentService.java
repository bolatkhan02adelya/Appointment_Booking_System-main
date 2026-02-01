package service;

import entity.Appointment;
import entity.TimeSlot;
import repository.interfaces.IAppointmentRepository;
import repository.interfaces.ITimeSlotRepository;
import service.interfaces.IAppointmentService;

import java.util.List;

public class AppointmentService implements IAppointmentService {

    private final IAppointmentRepository appointmentRepo;
    private final ITimeSlotRepository slotRepo;

    public AppointmentService(IAppointmentRepository appointmentRepo, ITimeSlotRepository slotRepo) {
        this.appointmentRepo = appointmentRepo;
        this.slotRepo = slotRepo;
    }

    public List<String> getAllAppointments() {
        return appointmentRepo.getAllAppointments();
    }

    public String book(int userId, int serviceId, int slotId) {
        List<TimeSlot> allSlots = slotRepo.getAllSlots();
        TimeSlot selectedSlot = allSlots.stream()
                .filter(s -> s.getId() == slotId)
                .findFirst()
                .orElse(null);

        if (selectedSlot == null) return "Error: Slot not found!";
        if (!selectedSlot.isAvailable()) return "Error: Slot is already occupied!";

        Appointment appointment = new Appointment(userId, serviceId, slotId);
        return appointmentRepo.makeAppointment(appointment) ? "Booking successful!" : "Database error.";
    }
    @Override
    public List<TimeSlot> getAvailableSlots() {
        return slotRepo.getAllSlots().stream()
                .filter(TimeSlot::isAvailable)
                .toList();
    }
}
