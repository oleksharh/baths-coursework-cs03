package wars;

class Sloop extends Ship {
    private boolean hasDoctor;

    public Sloop(String name, String captain, int commissionFee, ShipState state, boolean hasDoctor)
    {
        super(name, captain, commissionFee, 5, state);
        this.hasDoctor = hasDoctor;
    }

    @Override
    public String toString() {
        return "Sloop{" +
                "name='" + name + '\'' +
                ", captain='" + captain + '\'' +
                ", commissionFee=" + commissionFee +
                ", battleSkill=" + battleSkill +
                ", state=" + state +
                ", hasDoctor=" + hasDoctor +
                '}';
    }
}
