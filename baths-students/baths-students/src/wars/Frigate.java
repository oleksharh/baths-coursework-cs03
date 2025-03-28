package wars;
import java.io.*;

class Frigate extends Ship {
    private int numCannons;
    private boolean hasPinnace;

    public Frigate(String name, String captain, int battleSkill, String state, int numCannons, boolean hasPinnace) {
        super(name, captain, numCannons * 10, battleSkill, state);
        this.numCannons = numCannons;
        this.hasPinnace = hasPinnace;
    }

    @Override
    public int getCommissionFee() {
        return commissionFee;
    }
}