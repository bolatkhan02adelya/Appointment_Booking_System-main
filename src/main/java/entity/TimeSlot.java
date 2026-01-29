package entity;

import repository.interfaces.ITimeSlotRepository;

public class TimeSlot {
    private int id;
    private String slotTime;
    private boolean isAvailable;

    public TimeSlot(String slotTime, boolean isAvailable) {
        this.slotTime = slotTime;
        this.isAvailable = isAvailable;
    }


    public TimeSlot(int id, String slotTime, boolean isAvailable) {
        this.id = id;
        this.slotTime = slotTime;
        this.isAvailable = isAvailable;
    }

    public int getId() { return id; }
    public String getSlotTime() { return slotTime; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
}
