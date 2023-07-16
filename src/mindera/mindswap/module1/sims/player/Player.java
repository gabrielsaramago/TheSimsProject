package mindera.mindswap.module1.sims.player;

import mindera.mindswap.module1.sims.Server.Server;

public class Player {
    private Server.PlayerHandler ph;
    private SimsCharacter playerChar;

    public Player(Server.PlayerHandler ph) {
        this.ph = ph;
    }

    public Server.PlayerHandler getPh() {
        return ph;
    }

    public void createSimsChar(){
        ph.sendMessageToPlayer("Choose the character name: ");
        String name = ph.receiveMessageFromPlayer();
        playerChar = new FunnyPlayer(name, this);
    }

    public SimsCharacter getPlayerChar() {
        return playerChar;
    }
}
