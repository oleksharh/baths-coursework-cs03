package wars;
import java.io.*;

abstract class Ship implements Serializable {
    private String name;
    private String captain;
    private int commissionFee;
    private int battleSkill;
    private ShipState state;

    public String getName()
    {
        return name;
    }

    public String getCaptain()
    {
        return captain;
    }

    public int getBattleSkill()
    {
        return battleSkill;
    }

    public ShipState getState()
    {
        return state;
    }

    public Ship(String name, String captain, int commissionFee, int battleSkill, ShipState state)
    {
        this.name = name;
        this.captain = captain;
        this.commissionFee = commissionFee;
        this.battleSkill = battleSkill;
        this.state = state;
    }

    public int getCommissionFee()
    {
        return commissionFee;
    }

    public boolean canFight()
    {
        return state != ShipState.SUNK;
    }

    public void updateState(ShipState newState)
    {
        this.state = newState;
    }

    public abstract boolean canFight(EncounterType type);

    /*
        * Returns true if the ship is in an active state (i.e., not sunk or in reserve).
     */
    public boolean isActive()
    {
        if (this.state == ShipState.ACTIVE)
            return true;

        return false;
    }

    /*
        * Returns true if the ship is in a Rest state.
     */
    public boolean isResting()
    {
        if (this.state == ShipState.RESTING)
            return true;

        return false;
    }

    /*
        * Returns true if the ship is in a sunk state.
     */
    public boolean isSunk()
    {
        return this.state == ShipState.SUNK;
    }


    @Override
    public String toString() {
        return "Ship{" +
                "name='" + name + '\'' +
                ", captain='" + captain + '\'' +
                ", commissionFee=" + commissionFee +
                ", battleSkill=" + battleSkill +
                ", state=" + state +
                '}';
    }

}