package mindera.mindswap.module1.sims.util;

public class NotHaveHouseException extends Exception{
    public NotHaveHouseException(){
        super("The player must have a house before do anything.");
    }
}
