package mindera.mindswap.module1.sims.house;

public enum Activity {
    EATING(0,20, 30),
    GO_TO_BATHROOM(0, 0, 10),
    SLEEP(0,100, 20),
    WORK(50, 0, 20),
    WORKOUT(30,0,30);

    private int energyUse;
    private int energyGain;
    private int houseUse;
    Activity(int energyUse, int energyGain, int houseUse){
        this.energyUse = energyUse;
        this.energyGain = energyGain;
        this.houseUse = houseUse;
    }

    public int getEnergyUse() {
        return energyUse;
    }

    public int getEnergyGain() {
        return energyGain;
    }

    public int getHouseUse() {
        return houseUse;
    }
}
