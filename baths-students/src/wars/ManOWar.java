package wars;

class ManOWar extends Ship {
    private int numDecks;
    private int marines;

    public ManOWar(String name, String captain, int battleSkill, ShipState state, int numDecks, int marines)
    {
        super(name, captain, numDecks > 2 ? 500 : 300, battleSkill, state);
        this.numDecks = numDecks;
        this.marines = marines;
    }

    @Override
    public String toString() {
        return "ManOWar{" +
                "name='" + name + '\'' +
                ", captain='" + captain + '\'' +
                ", commissionFee=" + commissionFee +
                ", battleSkill=" + battleSkill +
                ", state=" + state +
                ", numDecks=" + numDecks +
                ", marines=" + marines +
                '}';
    }
}