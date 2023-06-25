package mindera.mindswap.module1.sims.house;
public class House {

    private boolean occupied = false;
    private int houseLevelOfCleanliness = 100;
    private final double HOUSE_PRICE = 5000;
    private Room simplekitchen = new Room(RoomType.KITCHEN );
    private Room simpleLivingRoom = new Room(RoomType.LIVING_ROOM);
    private Room simpleBedroom = new Room(RoomType.BEDROOM);
    private Room simpleBathroom = new Room(RoomType.BATHROOM);
    private Room[] houseRooms = {simpleBedroom, simplekitchen, simpleLivingRoom, simpleBathroom};

    public House() {
    }

    public boolean isOccupied() {
        return occupied;
    }
    public int getHouseLevelOfCleanliness() {
        return houseLevelOfCleanliness;
    }
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
    public void decreaseHouseLevelOfCleanliness(int amount) {
        houseLevelOfCleanliness -= amount;
    }

    public void setHouseLevelOfCleanliness(int houseLevelOfCleanliness) {
        this.houseLevelOfCleanliness = houseLevelOfCleanliness;
    }

    public double getHOUSE_PRICE() {
        return HOUSE_PRICE;
    }
}
