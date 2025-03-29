package wars;

class Sloop extends Ship {
    private boolean hasDoctor;

    public Sloop(String name, String captain, int commissionFee, ShipState state, boolean hasDoctor)
    {
        super(name, captain, commissionFee, 5, state);
        this.hasDoctor = hasDoctor;
    }
}
