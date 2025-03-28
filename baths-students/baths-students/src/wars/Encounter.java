package wars;
import java.io.*;

class Encounter implements Serializable {
    private int id;
    private EncounterType type;
    private String location;
    private int requiredSkill;
    private int prizeMoney;

    public Encounter(int id, EncounterType type, String location, int requiredSkill, int prizeMoney) {
        this.id = id;
        this.type = type;
        this.location = location;
        this.requiredSkill = requiredSkill;
        this.prizeMoney = prizeMoney;
    }

    public EncounterType getType() {
        return type;
    }

    public int getRequiredSkill() {
        return requiredSkill;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }
}
