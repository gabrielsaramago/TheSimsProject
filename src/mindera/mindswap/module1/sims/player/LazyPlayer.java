package mindera.mindswap.module1.sims.player;

public class LazyPlayer extends SimsCharacter {

    private PlayerType playerType = PlayerType.LAZY_PLAYER;

    public LazyPlayer(String playerName) {
        super(playerName, 80,7000);
    }
}
