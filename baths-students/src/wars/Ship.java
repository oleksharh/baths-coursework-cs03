package wars;
import java.io.*;


/**
 * A basic class for all types of ships.
 * Stores common details like name, captain, skill, and state.
 * Other ship types like Frigate or ManOWar will extend this.
 */
abstract class Ship implements Serializable {
    private String name;
    private String captain;
    private int commissionFee;
    private int battleSkill;
    private ShipState state;

    /**
     * Creates a ship with the given details.
     *
     * @param name          the ship's name
     * @param captain       the captain's name
     * @param commissionFee the cost to use the ship
     * @param battleSkill   the ship's skill in battle
     * @param state         the ship's starting state
     */
    public Ship(String name, String captain, int commissionFee, int battleSkill, ShipState state)
    {
        this.name = name;
        this.captain = captain;
        this.commissionFee = commissionFee;
        this.battleSkill = battleSkill;
        this.state = state;
    }

    /** @return the ship's name */
    public String getName()
    {
        return name;
    }

    /** @return the captain's name */
    public String getCaptain()
    {
        return captain;
    }

    /** @return the ship's battle skill level */
    public int getBattleSkill()
    {
        return battleSkill;
    }

    /** @return the ship's current state */
    public ShipState getState()
    {
        return state;
    }

    /** @return the ship's commission fee */
    public int getCommissionFee()
    {
        return commissionFee;
    }

    /**
     * Checks if the ship can fight in a specific type of encounter.
     * (Each subclass will define this.)
     *
     * @param type the type of encounter
     * @return true if this ship type can fight in it
     */
    public abstract boolean canFight(EncounterType type);


    /**
     * Shows the ship’s details as a string.
     */
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

    // Methods for setting different states

    public void setActive()
    {
        this.state = ShipState.ACTIVE;
    }

    public void setResting()
    {
        this.state = ShipState.RESTING;
    }

    public void setSunk()
    {
        this.state = ShipState.SUNK;
    }

    public void setReserve()
    {
        this.state = ShipState.RESERVE;
    }

    // Methods to check ship's state
    /**
     * @return true if the ship is RESTING
     */
    public boolean isActive()
    {
        return this.state == ShipState.ACTIVE;
    }

    /**
     * @return true if the ship is RESTING
     */
    public boolean isResting()
    {
        return this.state == ShipState.RESTING;
    }

    /**
     * @return true if the ship is RESERVE
     */
    public boolean isReserve()
    {
        return this.state == ShipState.RESERVE;
    }

    /**
     * @return true if the ship is SUNK
     */
    public boolean isSunk()
    {
        return this.state == ShipState.SUNK;
    }
}