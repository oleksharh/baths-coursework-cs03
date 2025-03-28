package wars;

class ManOWar extends Ship {
    private int numDecks;
    private int marines;

    public ManOWar(String name, String captain, int battleSkill, String state, int numDecks, int marines) {
        super(name, captain, numDecks > 2 ? 500 : 300, battleSkill, state);
        this.numDecks = numDecks;
        this.marines = marines;
    }

    @Override
    public int getCommissionFee() {
        return commissionFee;
    }
}