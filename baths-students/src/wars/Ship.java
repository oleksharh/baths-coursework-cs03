package wars;
import java.io.*;

abstract class Ship implements Serializable {
    protected String name;
    protected String captain;
    protected int commissionFee;
    protected int battleSkill;
    protected ShipState state;

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
        return state != ShipState.SUNK && state != ShipState.RESERVE;
    }

    public void updateState(ShipState newState)
    {
        this.state = newState;
    }

    /*
        * Returns true if the ship is in an active state (i.e., not sunk or in reserve).
     */
    public boolean isActive()
    {
        if (this.state == ShipState.ACTIVE)
            return true;

        return false;
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