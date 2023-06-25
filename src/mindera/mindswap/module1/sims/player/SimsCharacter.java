package mindera.mindswap.module1.sims.player;
import mindera.mindswap.module1.sims.Maid;
import mindera.mindswap.module1.sims.house.Activity;
import mindera.mindswap.module1.sims.house.House;
import mindera.mindswap.module1.sims.house.RoomType;
import mindera.mindswap.module1.sims.util.*;

public abstract class SimsCharacter {

    private boolean hasHouse = false;
    private String playerName;
    private double playerCash;
    private int initialEnergyLevel;
    private int playerEnergyLevel = initialEnergyLevel;
    private RoomType actualRoom;
    private House actualHouse = null;
    private boolean hasToUseToilet = false;
    private final double dailySalary = 100.0;
    private double mealPrice = 10.0;

    public SimsCharacter(String playerName, int initialEnergyLevel, double playerCash) {
        this.playerName = playerName;
        this.initialEnergyLevel = initialEnergyLevel;
        this.playerCash = playerCash;
    }

    //getters & setters
    public boolean isHasHouse() {
        return hasHouse;
    }
    public void setHasHouse(boolean hasHouse) {
        this.hasHouse = hasHouse;
    }
    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public double getPlayerCash() {
        return playerCash;
    }
    public int getPlayerEnergyLevel() {
        return playerEnergyLevel;
    }
    public House getActualHouse() {
        return actualHouse;
    }

    //Action Methods
    public void buyHouse(House house) {
        if(!hasHouse){
            playerCash -= house.getHOUSE_PRICE();
            System.out.println("Player " + playerName + " bought a house !");
            hasHouse = true;
            actualHouse = house;
            return;
        }
        System.out.println("The player " + playerName + " already have a house.");
    }

    public void goToBedroom() {
        System.out.println(playerName + Message.GO_TO_BEDROOM);
        actualRoom = RoomType.BEDROOM;
    }
    public void goToKitchen() {
        System.out.println(playerName + Message.GO_TO_KITCHEN);
        actualRoom = RoomType.KITCHEN;
    }
    public void goToLivingRoom() {
        System.out.println(playerName + Message.GO_TO_LIVINGROOM);
        actualRoom = RoomType.LIVING_ROOM;
    }
    public void goToBathroom(){
        System.out.println(playerName + Message.GO_TO_BATHROOM);
        actualRoom = RoomType.BATHROOM;
    }

    public void sleep() throws NotSuitableRoomException, HouseCleannessException, HaveToUseToiletException, NotHaveHouseException {
        checkHasHouse();
        checkHouseCleanliness();
        checkToiletNecessity();
        if (actualRoom==RoomType.BEDROOM){
            System.out.println(playerName + Message.GO_TO_SLEEP);
            increaseEnergyLevel(Activity.SLEEP);
            actualHouse.decreaseHouseLevelOfCleanliness(Activity.SLEEP.getHouseUse());
            System.out.println(playerName + Message.WAKE_UP);
            return;
        }
        throw new NotSuitableRoomException();
    }

    public void eat() throws NotSuitableRoomException, HouseCleannessException, HaveToUseToiletException, NotEnoughCashException, NotHaveHouseException {
        checkHasHouse();
        checkHouseCleanliness();
        checkToiletNecessity();
        if(playerCash<mealPrice){
            throw new NotEnoughCashException();
        }
        if(actualRoom==RoomType.KITCHEN){
            System.out.println(playerName + Message.EAT);
            increaseEnergyLevel(Activity.EATING);
            actualHouse.decreaseHouseLevelOfCleanliness(Activity.EATING.getHouseUse());
            playerCash -= 10;
            hasToUseToilet = true;
            return;
        }
        throw new NotSuitableRoomException();
    }
    public void work() throws NotSuitableRoomException, NotEnoughEnergyException, HouseCleannessException, HaveToUseToiletException, NotHaveHouseException {
        checkHasHouse();
        checkHouseCleanliness();
        checkToiletNecessity();
        if(actualRoom==RoomType.LIVING_ROOM || actualRoom==RoomType.BEDROOM){
            System.out.println(playerName + Message.START_WORK  + actualRoom);
            decreaseEnergyLevel(Activity.WORK);
            System.out.println(playerName + Message.FINISH_WORK);
            actualHouse.decreaseHouseLevelOfCleanliness(Activity.WORK.getHouseUse());
            playerCash += dailySalary;
            return;
        }
        throw new NotSuitableRoomException();
    }

    public void workOut() throws NotSuitableRoomException, NotEnoughEnergyException, HouseCleannessException, HaveToUseToiletException, NotHaveHouseException {
        checkHasHouse();
        checkHouseCleanliness();
        checkToiletNecessity();
        if(actualRoom==RoomType.LIVING_ROOM || actualRoom==RoomType.BEDROOM){
            System.out.println(playerName + Message.START_WORKOUT);
            decreaseEnergyLevel(Activity.WORKOUT);
            System.out.println(playerName + Message.FINISH_WORKOUT);
            actualHouse.decreaseHouseLevelOfCleanliness(Activity.WORKOUT.getHouseUse());
            return;
        }
        throw new NotSuitableRoomException();
    }

    public void useToilet() throws NotSuitableRoomException, NotHaveHouseException {
        checkHasHouse();
        if(actualRoom == RoomType.BATHROOM){
            System.out.println(playerName + Message.USE_TOILET);
            hasToUseToilet = false;
            return;
        }
        throw new NotSuitableRoomException();
    }

    public void increaseEnergyLevel(Activity activity){
        playerEnergyLevel = (playerEnergyLevel + activity.getEnergyGain()> initialEnergyLevel) ?
                initialEnergyLevel : playerEnergyLevel+activity.getEnergyGain();
    }
    public void decreaseEnergyLevel(Activity activity) throws NotEnoughEnergyException {
        if(playerEnergyLevel>=activity.getEnergyUse()){
            playerEnergyLevel -= activity.getEnergyUse();
            return;
        }
        throw new NotEnoughEnergyException();
    }

    public void callMaid(Maid maid) throws NotEnoughCashException{
        if(playerCash>maid.getDailySalary()){
            System.out.println(playerName + Message.CALL_MAID);
            maid.cleanHouse(actualHouse);
            playerCash -= maid.getDailySalary();
            return;
        }
        throw new NotEnoughCashException();

    }

    //checkers
    public void checkHouseCleanliness() throws HouseCleannessException{
        if(actualHouse.getHouseLevelOfCleanliness()<=0){
            throw new HouseCleannessException();
        }
    }
    public void checkToiletNecessity() throws HaveToUseToiletException {
        if(hasToUseToilet){
            throw new HaveToUseToiletException();
        }
    }
    public void checkHasHouse() throws NotHaveHouseException {
        if(!hasHouse){
            throw new NotHaveHouseException();
        }
    }

    public void checkPlayerAndHouse(){
        System.out.println("Player energy level: "+ getPlayerEnergyLevel());
        System.out.println("Player cash: " + playerCash);
        System.out.println("Player have to use toilet? " + hasToUseToilet);
        System.out.println("House level of cleanliness: " + getActualHouse().getHouseLevelOfCleanliness());
        System.out.println("");
    }



}
