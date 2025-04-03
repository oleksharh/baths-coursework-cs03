package wars;
import java.io.*;

class BlueAdmiral implements Serializable {
    private String name;
    private int warChest;
    private Squadron squadron;

    public BlueAdmiral(String name, int warChest) {
        this.name = name;
        this.warChest = warChest;
        this.squadron = new Squadron();
    }

    public boolean isDefeated() {
        return warChest <= 0 && !squadron.hasShips();
    }
}




//  public void commissionShip(Ship ship) {
 //     if (warChest >= ship.getCommissionFee()) {
//           squadron.addShip(ship);
 //          warChest -= ship.getCommissionFee();
//       }
// }

//    public void decommissionShip(Ship ship) {
//        squadron.removeShip(ship);
//    }
//
//    public void fightEncounter(Encounter encounter) {
//        Ship ship = squadron.getFirstAvailableShip(encounter.getType());
//        if (ship != null && ship.battleSkill >= encounter.getRequiredSkill()) {
//            warChest += encounter.getPrizeMoney();
//        } else {
//            ship.updateState("Damaged");
//        }
//    }
//}