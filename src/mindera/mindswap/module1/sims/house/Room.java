package mindera.mindswap.module1.sims.house;
import mindera.mindswap.module1.sims.player.SimsCharacter;

public class Room {
    private RoomType roomType;
    private SimsCharacter player;
    public Room(RoomType roomType) {
        this.roomType = roomType;
    }
    public RoomType getRoomType() {
        return roomType;
    }


}
