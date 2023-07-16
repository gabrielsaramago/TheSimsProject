package mindera.mindswap.module1.sims.player;

import mindera.mindswap.module1.sims.util.*;

public class PlayerMenu {


    public PlayerMenu() {
    }

    public void actionMenu(Player p)
    {
        p.getPh().sendMessageToPlayer(Message.ACTION_MENU_OPTIONS);

        String option = p.getPh().receiveMessageFromPlayer();
        switch (option) {
            case "/useToilet":
                try {
                    p.getPlayerChar().useToilet();
                } catch (NotSuitableRoomException | NotHaveHouseException e) {
                    p.getPh().sendMessageToPlayer(e.getMessage());
                }
                break;
            case "/workout":
                try {
                    p.getPlayerChar().workOut();
                } catch (NotSuitableRoomException | HouseCleannessException | NotEnoughEnergyException |
                         HaveToUseToiletException | NotHaveHouseException e) {
                    p.getPh().sendMessageToPlayer(e.getMessage());
                }
                break;
            case "/work":
                try {
                    p.getPlayerChar().work();
                } catch (NotSuitableRoomException | NotEnoughEnergyException | HouseCleannessException |
                         HaveToUseToiletException | NotHaveHouseException e) {
                    p.getPh().sendMessageToPlayer(e.getMessage());
                }
                break;
            case "/eat":
                try {
                    p.getPlayerChar().eat();
                } catch (NotSuitableRoomException | NotHaveHouseException | HouseCleannessException |
                         HaveToUseToiletException | NotEnoughCashException e) {
                    p.getPh().sendMessageToPlayer(e.getMessage());
                }
                break;
            case "/sleep":
                try {
                    p.getPlayerChar().sleep();
                } catch (NotSuitableRoomException | HouseCleannessException | HaveToUseToiletException |
                         NotHaveHouseException e) {
                    p.getPh().sendMessageToPlayer(e.getMessage());
                }
                break;
            case "/move":
                moveMenuOptions(p);
                break;
            case "/status":
                p.getPlayerChar().charStatus();
            default:
                System.out.println("Command invalid...");
                break;
        }

    }

    private void moveMenuOptions(Player player){
        player.getPh().sendMessageToPlayer(Message.MOVE_MENU_OPTIONS);
        String op = player.getPh().receiveMessageFromPlayer();

        switch (op){
            case "/bathroom":
                player.getPlayerChar().goToBathroom();
                break;
            case "/bedroom":
                player.getPlayerChar().goToBedroom();
                break;
            case "/kitchen":
                player.getPlayerChar().goToKitchen();
                break;
            case "/livingRoom":
                player.getPlayerChar().goToLivingRoom();
                break;
            default:
                player.getPh().sendMessageToPlayer("Command invalid...");
                break;
        }
    }
}
