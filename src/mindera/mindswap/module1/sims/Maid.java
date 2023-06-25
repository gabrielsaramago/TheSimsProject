package mindera.mindswap.module1.sims;

import mindera.mindswap.module1.sims.house.House;
import mindera.mindswap.module1.sims.util.Message;

import java.lang.reflect.Member;

public class Maid {
    private double dailySalary = 50;

    public Maid() {
    }

    public void cleanHouse (House house){
        house.setHouseLevelOfCleanliness(100);
        System.out.println(Message.MAID_CLEANING);
    }

    public double getDailySalary() {
        return dailySalary;
    }
}
