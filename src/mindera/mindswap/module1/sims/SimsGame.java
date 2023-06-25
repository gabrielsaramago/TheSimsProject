package mindera.mindswap.module1.sims;

import mindera.mindswap.module1.sims.house.House;
import mindera.mindswap.module1.sims.player.FunnyPlayer;
import mindera.mindswap.module1.sims.player.SimsCharacter;
import mindera.mindswap.module1.sims.util.*;

public class SimsGame {
    public static void main(String[] args) {

        SimsCharacter mainPlayer = new FunnyPlayer("Gabriel");

        Maid maid = new Maid();

        mainPlayer.buyHouse(new House());

        mainPlayer.goToBedroom();

        try {
            mainPlayer.sleep();
        } catch (NotSuitableRoomException | HouseCleannessException | HaveToUseToiletException | NotHaveHouseException e) {
            System.out.println(e.getMessage());
        }
        mainPlayer.checkPlayerAndHouse();

        mainPlayer.goToKitchen();

        try {
            mainPlayer.eat();
        } catch (NotSuitableRoomException | HouseCleannessException | HaveToUseToiletException |
                 NotEnoughCashException | NotHaveHouseException e) {
            System.out.println(e.getMessage());
        }
        mainPlayer.checkPlayerAndHouse();

        mainPlayer.goToLivingRoom();

        try {
            mainPlayer.work();
        } catch (NotSuitableRoomException | NotEnoughEnergyException | HouseCleannessException |
                 HaveToUseToiletException | NotHaveHouseException e) {
            System.out.println(e.getMessage());
        }
        mainPlayer.checkPlayerAndHouse();

        try {
            mainPlayer.useToilet();
        } catch (NotSuitableRoomException | NotHaveHouseException e) {
            System.out.println(e.getMessage());
        }

        mainPlayer.goToBathroom();

        try {
            mainPlayer.useToilet();
        } catch (NotSuitableRoomException | NotHaveHouseException e) {
            System.out.println(e.getMessage());
        }
        mainPlayer.checkPlayerAndHouse();

        mainPlayer.goToLivingRoom();

        try {
            mainPlayer.workOut();
        } catch (NotSuitableRoomException | NotEnoughEnergyException | HouseCleannessException |
                 HaveToUseToiletException | NotHaveHouseException e) {
            System.out.println(e.getMessage());
        }
        mainPlayer.checkPlayerAndHouse();

        try {
            mainPlayer.workOut();
        } catch (NotSuitableRoomException | NotEnoughEnergyException | HouseCleannessException |
                 HaveToUseToiletException | NotHaveHouseException e) {
            System.out.println(e.getMessage());
        }
        mainPlayer.checkPlayerAndHouse();

        try {
            mainPlayer.workOut();
        } catch (NotSuitableRoomException | NotEnoughEnergyException | HouseCleannessException |
                 HaveToUseToiletException | NotHaveHouseException e) {
            System.out.println(e.getMessage());
        }
        mainPlayer.checkPlayerAndHouse();

        try {
            mainPlayer.callMaid(maid);
        } catch (NotEnoughCashException e) {
            throw new RuntimeException(e);
        }
        mainPlayer.checkPlayerAndHouse();

        mainPlayer.goToKitchen();
        try {
            mainPlayer.eat();
        } catch (NotSuitableRoomException | HouseCleannessException | HaveToUseToiletException |
                 NotEnoughCashException | NotHaveHouseException e) {
            System.out.println(e.getMessage());
        }
        mainPlayer.checkPlayerAndHouse();

        mainPlayer.goToBedroom();
        try {
            mainPlayer.sleep();
        } catch (NotSuitableRoomException | HouseCleannessException | HaveToUseToiletException | NotHaveHouseException e) {
            System.out.println(e.getMessage());
        }
        mainPlayer.checkPlayerAndHouse();



    }



}