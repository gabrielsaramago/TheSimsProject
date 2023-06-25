package mindera.mindswap.module1.sims.util;

public class NotEnoughCashException extends Exception{
    public NotEnoughCashException(){
        super("Not enough cash to do this action...");
    }
}
