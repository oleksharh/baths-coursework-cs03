package wars;


/**
 * A Sloop is a small and fast ship with a fixed battle skill of 5.
 * It can fight in BATTLE and SKIRMISH encounters.
 * Some Sloops may have a doctor on board.
 */
class Sloop extends Ship {
    private boolean hasDoctor;

    /**
     * Creates a new Sloop with the given details.
     *
     * @param name          the name of the ship
     * @param captain       the name of the captain
     * @param commissionFee the cost to use the ship
     * @param state         the ship's starting state
     * @param hasDoctor     true if the ship has a doctor
     */
    public Sloop(String name, String captain, int commissionFee, ShipState state, boolean hasDoctor)
    {
        super(name, captain, commissionFee, 5, state);
        this.hasDoctor = hasDoctor;
    }

    /**
     * Checks if this Sloop can fight in a specific type of encounter.
     * Sloops can fight in BATTLE and SKIRMISH types.
     *
     * @param type the type of encounter
     * @return true if the Sloop can fight in this type
     */
    public boolean canFight(EncounterType type) {
        return type == EncounterType.BATTLE || type == EncounterType.SKIRMISH;
    }


    /**
     * Shows the Sloop's details as a string.
     */
    @Override
    public String toString() {
        return "Sloop{" +
                "name='" + this.getName() + '\'' +
                ", captain='" + this.getCaptain() + '\'' +
                ", commissionFee=" + this.getCommissionFee() +
                ", battleSkill=" + this.getBattleSkill() +
                ", state=" + this.getState() +
                ", hasDoctor=" + hasDoctor +
                '}';
    }
}
