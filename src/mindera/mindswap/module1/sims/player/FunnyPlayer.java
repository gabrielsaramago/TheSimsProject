package mindera.mindswap.module1.sims.player;

public class FunnyPlayer extends SimsCharacter{

    private PlayerType playerType = PlayerType.FUNNY_PLAYER;

    public FunnyPlayer(String playerName, Player player) {
        super(playerName,110,6500, player);
    }

}
