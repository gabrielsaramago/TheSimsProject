package mindera.mindswap.module1.sims;

import mindera.mindswap.module1.sims.house.House;
import mindera.mindswap.module1.sims.util.Message;

import java.lang.reflect.Member;

public class Maid {
    private static double dailySalary = 50;

    public Maid() {
    }

    public static void cleanHouse (House house){
        house.setHouseLevelOfCleanliness(100);
        System.out.println(Message.MAID_CLEANING);
    }

    public static double getDailySalary() {
        return dailySalary;
    }
}
