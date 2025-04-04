package wars;
import java.io.*;

/**
 * This class represents a Frigate ship.
 * Frigates have cannons and may or may not have a pinnace (small boat).
 * The pinnace is needed for certain types of encounter like blockade
 */
class Frigate extends Ship {
    private int numCannons;
    private boolean hasPinnace;

    /**
     * Creates a Frigate object with all the needed info.
     * @param name        the name of the ship
     * @param captain     the captain's name
     * @param battleSkill skill level of the ship
     * @param state       the current state (ACTIVE, RESTING, etc.)
     * @param numCannons  how many cannons it has
     * @param hasPinnace  true if it has a pinnace
     */
    public Frigate(String name, String captain, int battleSkill, ShipState state, int numCannons, boolean hasPinnace)
    {
        super(name, captain, numCannons * 10, battleSkill, state);
        this.numCannons = numCannons;
        this.hasPinnace = hasPinnace;
    }

    /**
     * Checks if this frigate can fight a certain type of encounter.
     *
     * @param type the type of encounter (BLOCKADE, BATTLE, SKIRMISH)
     * @return true if allowed, false otherwise
     */
    public boolean canFight(EncounterType type){
        return switch (type) {
            case BLOCKADE -> hasPinnace;
            case BATTLE, SKIRMISH -> true;
            default -> false;
        };
        // TODO: maybe add encounter param instead of type to carry out battleskill and other compariosns
    }

    /**
     * Returns info about this frigate as a string.
     *
     * @return string with ship details
     */
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

