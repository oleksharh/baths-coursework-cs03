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


    public boolean canFight(EncounterType type){
        switch (type){
            case BLOCKADE:
                return hasPinnace;
            case BATTLE:
            case SKIRMISH:
                return true;
            default:
                return false;
        }
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

