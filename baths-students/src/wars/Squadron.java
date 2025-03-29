package wars;
import java.io.*;
import java.util.*;

class Squadron implements Serializable {
    private List<Ship> ships;

    public Squadron() {
        ships = new ArrayList<>();
    }

    public void addShip(Ship ship) {
        ships.add(ship);
    }

    public void removeShip(Ship ship) {
        ships.remove(ship);
    }

    public Ship getFirstAvailableShip(EncounterType encounterType) {
        for (Ship ship : ships) {
            if (ship.canFight()) {
                return ship;
            }
        }
        return null;
    }
}