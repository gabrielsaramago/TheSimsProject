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
        chooseCharType(name);
    }

    private void chooseCharType(String name){
        ph.sendMessageToPlayer("Choose the character type(S - funnyPlayer | SP - SportyPlayer | LZ - LazyPlayer");
        String op = ph.receiveMessageFromPlayer();
        if(op.toLowerCase().equals("s")){
            playerChar = new FunnyPlayer(name, this);
            return;
        }
        if(op.toLowerCase().equals("sp")){
            playerChar = new SportyPlayer(name, this);
            return;
        }
        if(op.toLowerCase().equals("lz")){
            playerChar = new LazyPlayer(name, this);
        }
        else{
            ph.sendMessageToPlayer("Choose a valid option !");
            chooseCharType(name);
        }
    }

    public SimsCharacter getPlayerChar() {
        return playerChar;
    }
}
