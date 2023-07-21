package mindera.mindswap.module1.sims;

import mindera.mindswap.module1.sims.house.House;
import mindera.mindswap.module1.sims.player.FunnyPlayer;
import mindera.mindswap.module1.sims.player.Player;
import mindera.mindswap.module1.sims.player.PlayerMenu;
import mindera.mindswap.module1.sims.player.SimsCharacter;
import mindera.mindswap.module1.sims.util.*;
import mindera.mindswap.module1.sims.Server.Server;
import java.util.List;
import java.util.stream.Collectors;


public class SimsGame implements Runnable{

    private List<Server.PlayerHandler> playerHandlers;
    private List<Player> players;
    private boolean gameOn;
    private PlayerMenu pm;

    public SimsGame(List<Server.PlayerHandler> playerHandlers) {
        this.playerHandlers = playerHandlers;
        gameOn = true;
        pm = new PlayerMenu();
    }

    @Override
    public void run() {
        players = playerHandlers.stream().map(ph->new Player(ph)).collect(Collectors.toList());
        for(Player player : players){
            player.getPh().sendMessageToPlayer(Message.WELCOME_MESSAGE);
            chooseCharacter(player);
            beginningMessage(player);
        }
        playerRound();
    }
    private void chooseCharacter(Player p){
        p.getPh().sendMessageToPlayer("The game had started");
        p.createSimsChar();
        p.getPlayerChar().buyHouse(new House());
    }
    private void playerRound(){
        while(gameOn){
            pm.actionMenu(players.get(0));
        }
    }
    private void beginningMessage(Player player){
        player.getPh().sendMessageToPlayer(player.getPlayerChar().getPlayerName() + " chose " + player.getPlayerChar().getPlayerType() +"\n"
                + "You are in your bedroom after a long day... It's time to sleep and recover your energy.");


    }

}



