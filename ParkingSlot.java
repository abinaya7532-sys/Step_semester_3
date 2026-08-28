class ParkingSlot {
    String slotNo;
    int capacity;
    int occupiedCount;
    ParkingSlot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }
    void allot(String vehicleNo) {
        if (occupiedCount < capacity) {
            occupiedCount++;
            System.out.println(vehicleNo+ " allotted to slot " + slotNo);
        }
    }
    static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {
        for (int i = 0; i < slots.length; i++) {
            if (slots[i].occupiedCount < slots[i].capacity) {
                return slots[i];
            }
        }
        return null;
    }
    static void safeAllot(ParkingSlot[] slots, String vehicleNo) {
        ParkingSlot freeSlot = findAvailableSlot(slots);
        if (freeSlot != null) {
            freeSlot.allot(vehicleNo);
        } else {
            System.out.println("No slots available for " + vehicleNo);
        }
    }
    public static void main(String[] args) {
        ParkingSlot slot1 =new ParkingSlot("A1", 4, 3);
        ParkingSlot slot2 =new ParkingSlot("A2", 5, 5);
        ParkingSlot[] slots = {slot1, slot2};
        System.out.println("First attempt:");
        safeAllot(slots, "TN09AB1234");
        System.out.println("\nSecond attempt:");
        safeAllot(slots, "TN09AB1234");
    }
}