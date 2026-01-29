package entity;

public class Appointment {
    private int id;
    private int userId;
    private int serviceId;
    private int slotId;

    public Appointment(int userId, int serviceId, int slotId) {
        this.userId = userId;
        this.serviceId = serviceId;
        this.slotId = slotId;
    }


    public int getUserId() { return userId; }
    public int getServiceId() { return serviceId; }
    public int getSlotId() { return slotId; }
}
