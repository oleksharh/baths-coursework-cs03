package wars;
import java.io.*;

class Frigate extends Ship {
    private int numCannons;
    private boolean hasPinnace;

    public Frigate(String name, String captain, int battleSkill, ShipState state, int numCannons, boolean hasPinnace)
    {
        super(name, captain, numCannons * 10, battleSkill, state);
        this.numCannons = numCannons;
        this.hasPinnace = hasPinnace;
    }

    @Override
    public String toString() {
        return "Frigate{" +
                "name='" + this.getName() + '\'' +
                ", captain='" + this.getCaptain() + '\'' +
                ", commissionFee=" + this.getCommissionFee() +
                ", battleSkill=" + this.getBattleSkill() +
                ", state=" + this.getState() +
                ", numCannons=" + numCannons +
                ", hasPinnace=" + hasPinnace +
                '}';
    }
}