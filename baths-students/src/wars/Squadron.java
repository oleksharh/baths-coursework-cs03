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

    /**Checks if the squadron has any ships
     * @return true if the squadron has ships, false otherwise
     * **/
    public boolean hasShips()
    {
        return !ships.isEmpty();
    }

    public List<Ship> getSunkShips() {
        List<Ship> sunkShips = new ArrayList<>();
        for (Ship ship : ships) {
            if (ship.state == ShipState.SUNK) {
                sunkShips.add(ship);
            }
        }
        return sunkShips;
    }

    public List<Ship> getShips() { return ships; }

    public Ship getFirstAvailableShip(EncounterType encounterType) {
        for (Ship ship : ships) {
            if (ship.canFight()) {
                return ship;
            }
        }
        return null;
    }

    public Ship getShipByName(String name) {
        for (Ship ship : ships) {
            if (ship.name.equalsIgnoreCase(name)) {
                return ship;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Squadron:\n");
        for (Ship ship : ships) {
            sb.append(ship.toString()).append("\n");
        }
        return sb.toString();
    }
}