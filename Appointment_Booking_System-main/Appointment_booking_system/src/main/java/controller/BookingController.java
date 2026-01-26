package controller;

import controller.interfaces.IBookingController;
import service.AppointmentService;
import entity.TimeSlot;
import java.util.List;

public class BookingController implements IBookingController {
    private final AppointmentService appointmentService;

    public BookingController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @Override
    public String bookAppointment(int userId, int serviceId, int slotId) {
        return appointmentService.book(userId, serviceId, slotId);
    }

    @Override
    public List<TimeSlot> getAvailableSlots() {
        return appointmentService.getAvailableSlots();
    }
}
