package service;

import entity.Appointment;
import entity.TimeSlot;
import repository.AppointmentRepository;
import repository.TimeSlotRepository;
import java.util.List;

public class AppointmentService {
    private final AppointmentRepository appointmentRepo;
    private final TimeSlotRepository slotRepo;

    public AppointmentService(AppointmentRepository appointmentRepo, TimeSlotRepository slotRepo) {
        this.appointmentRepo = appointmentRepo;
        this.slotRepo = slotRepo;
    }

    public String book(int userId, int serviceId, int slotId, String userRole) {
        if (userRole == null || (!userRole.equalsIgnoreCase("USER") && !userRole.equalsIgnoreCase("ADMIN"))) {
            return "Access Denied";
        }

        TimeSlot slot = slotRepo.findById(slotId);
        if (slot == null || !slot.getStatus().equals("FREE")) {
            return "Slot unavailable";
        }

        if (appointmentRepo.save(new Appointment(userId, serviceId, slotId))) {
            slotRepo.updateStatus(slotId, "OCCUPIED");
            return "Success";
        }
        return "Database error";
    }

    public boolean cancel(int appointmentId, String userRole) {
        if (userRole == null || !userRole.equalsIgnoreCase("ADMIN")) {
            return false;
        }

        Appointment app = appointmentRepo.findById(appointmentId);
        if (app != null) {
            slotRepo.updateStatus(app.getSlotId(), "FREE");
            return appointmentRepo.delete(appointmentId);
        }
        return false;
    }

    public List<TimeSlot> getAvailableSlots() {
        return slotRepo.findAllFreeSlots();
    }
}