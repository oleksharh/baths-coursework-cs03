package wars;
import java.io.*;

abstract class Ship implements Serializable {
    protected String name;
    protected String captain;
    protected int commissionFee;
    protected int battleSkill;
    protected String state;

    public Ship(String name, String captain, int commissionFee, int battleSkill, String state) {
        this.name = name;
        this.captain = captain;
        this.commissionFee = commissionFee;
        this.battleSkill = battleSkill;
        this.state = state;
    }

    public abstract int getCommissionFee();

    public boolean canFight(EncounterType encounterType) {
        return !state.equals("Damaged");
    }

    public void updateState(String newState) {
        this.state = newState;
    }
}