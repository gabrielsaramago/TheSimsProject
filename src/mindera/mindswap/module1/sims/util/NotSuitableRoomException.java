package mindera.mindswap.module1.sims.util;

public class NotSuitableRoomException extends Exception{
    public NotSuitableRoomException(){
        super("This room is not suitable for the chosen action, go to a more suitable room");
    }
}
