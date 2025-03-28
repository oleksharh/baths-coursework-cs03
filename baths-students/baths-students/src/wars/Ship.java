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
}