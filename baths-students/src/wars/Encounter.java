package wars;
import java.io.*;

/**
 * This class represents an encounter in the BATHS game.
 * An encounter has a type, location, required skill level,
 * and prize money that the player can earn if they win.
 */
class Encounter implements Serializable
{
    private int id;
    private EncounterType type; // Blockade, Battle, Skirmish, Invalid
    private String location;
    private int requiredSkill;
    private int prizeMoney;

    /**
     * Creates a new encounter with all the needed info.
     *
     * @param id            the ID number of the encounter
     * @param type          the type of encounter
     * @param location      where the encounter takes place
     * @param requiredSkill how much skill is needed to win
     * @param prizeMoney    money awarded for winning
     */
    public Encounter(int id, EncounterType type, String location, int requiredSkill, int prizeMoney)
    {
        this.id = id;
        this.type = type;
        this.location = location;
        this.requiredSkill = requiredSkill;
        this.prizeMoney = prizeMoney;
    }

    /**
     * Gets the type of the encounter.
     *
     * @return the encounter type
     */
    public EncounterType getType()
    {
        return type;
    }

    /**
     * Gets the skill level needed to win.
     *
     * @return required skill level
     */
    public int getRequiredSkill()
    {
        return requiredSkill;
    }

    /**
     * Gets the prize money for winning.
     *
     * @return prize money
     */
    public int getPrizeMoney()
    {
        return prizeMoney;
    }

    /**
     * Returns details about the encounter as a string.
     *
     * @return encounter info
     */
    @Override
    public String toString()
    {
        return "Encounter ID: " + id + ", Type: " + type + ", Location: " + location +
               ", Required Skill: " + requiredSkill + ", Prize Money: " + prizeMoney;
    }
}
