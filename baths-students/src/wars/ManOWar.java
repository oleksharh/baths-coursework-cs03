package wars;

/**
 * Represents a Man-O-War ship, a type of warship with multiple decks and marines.
 * Extends the {@link Ship} class and is specialized for BLOCKADE and BATTLE encounters.
 */
class ManOWar extends Ship {
    private int numDecks;
    private int marines;

    /**
     * Constructs a Man-O-War ship with the given attributes.
     * The commission fee is set to 500 if the ship has more than 2 decks, otherwise 300.
     *
     * @param name         the name of the ship
     * @param captain      the name of the captain
     * @param battleSkill  the battle skill rating of the ship
     * @param state        the initial state of the ship (e.g., ACTIVE, SUNK)
     * @param numDecks     the number of decks the ship has
     * @param marines      the number of marines on board
     */

    public ManOWar(String name, String captain, int battleSkill, ShipState state, int numDecks, int marines)
    {
        super(name, captain, numDecks > 2 ? 500 : 300, battleSkill, state);
        this.numDecks = numDecks;
        this.marines = marines;
    }

    /**
     * Determines if the Man-O-War can participate in a given encounter type.
     * Man-O-Wars can fight in BLOCKADE and BATTLE encounters.
     *
     * @param type the type of encounter
     * @return true if the ship can fight in the encounter; false otherwise
     */
    @Override
    public boolean canFight(EncounterType type) {
        return type == EncounterType.BLOCKADE || type == EncounterType.BATTLE;
    }

    /**
     * Returns a string representation of the Man-O-War, including its attributes.
     *
     * @return a string describing the ship
     */
    @Override
    public String toString() {
        return "ManOWar{" +
                "name='" + this.getName() + '\'' +
                ", captain='" + this.getCaptain() + '\'' +
                ", commissionFee=" + this.getCommissionFee() +
                ", battleSkill=" + this.getBattleSkill() +
                ", state=" + this.getState() +
                ", numDecks=" + numDecks +
                ", marines=" + marines +
                '}';
    }
}