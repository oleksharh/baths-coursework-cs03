package wars;

class Sloop extends Ship {
    private boolean hasDoctor;

    public Sloop(String name, String captain, int commissionFee, int battleSkill, String state, boolean hasDoctor) {
        super(name, captain, commissionFee, battleSkill, state);
        this.hasDoctor = hasDoctor;
    }

    @Override
    public int getCommissionFee() {
        return commissionFee;
    }
}
