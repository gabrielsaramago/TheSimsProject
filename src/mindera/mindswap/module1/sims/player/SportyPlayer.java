package mindera.mindswap.module1.sims.player;

public class SportyPlayer extends SimsCharacter{
    private PlayerType playerType = PlayerType.SPORTY_PLAYER;
    public SportyPlayer(String playerName) {
        super(playerName, 120,6000);
    }
}