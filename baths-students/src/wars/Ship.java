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
     * Checks if the ship can fight (not sunk).
     *
     * @return true if ship is not sunk
     */
    public boolean canFight()
    {
        return state != ShipState.SUNK;
    }

    /**
     * Changes the ship's state to a new one.
     *
     * @param newState the new state to set
     */
    public void updateState(ShipState newState)
    {
        this.state = newState;
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
     * @return true if the ship is RESTING
     */
    public boolean isActive()
    {
        if (this.state == ShipState.ACTIVE)
            return true;

        return false;
    }


    /**
     * @return true if the ship is RESTING
     */
    public boolean isResting()
    {
        if (this.state == ShipState.RESTING)
            return true;

        return false;
    }

    /**
     * @return true if the ship is SUNK
     */
    public boolean isSunk()
    {
        return this.state == ShipState.SUNK;
    }

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
}