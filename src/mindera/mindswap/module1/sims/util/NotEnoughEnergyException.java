package mindera.mindswap.module1.sims.util;

public class NotEnoughEnergyException extends Exception{
    public NotEnoughEnergyException(){
        super("Not enough energy to do this action.");
    }
}
