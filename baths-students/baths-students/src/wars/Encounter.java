package wars;
import java.io.*;

class Encounter implements Serializable
{
    private int id;
    private EncounterType type; // Blockade, Battle, Skirmish, Invalid
    private String location;
    private int requiredSkill;
    private int prizeMoney;
    private Ship enemyShip; // Presumably there will be an enemy ship in the encounter

    public Encounter(int id, EncounterType type, String location, int requiredSkill, int prizeMoney)
    {
        this.id = id;
        this.type = type;
        this.location = location;
        this.requiredSkill = requiredSkill;
        this.prizeMoney = prizeMoney;
    }

    public EncounterType getType()
    {
        return type;
    }

    public int getRequiredSkill()
    {
        return requiredSkill;
    }

    public int getPrizeMoney()
    {
        return prizeMoney;
    }

//    public boolean fightEncounter(Ship ship)
//    {
//        if (ship.canFight(type) && ship.battleSkill >= requiredSkill)
//        {
//            return true;
//        }
//        else
//        {
//            ship.updateState("Damaged");
//            return false;
//        }
//    }
}
