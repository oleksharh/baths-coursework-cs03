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
        boolean hasShips = false;
        for (Ship ship : ships)
        {
            if (ship.getState() != ShipState.SUNK) {
                hasShips = true;
                break;
            }
        }
        return hasShips;
    }


    public String getSunkShips() {
        StringBuilder sunkShips = new StringBuilder();
        for (Ship ship : ships) {
            if (ship.getState() == ShipState.SUNK) {
                sunkShips.append(ship.getName()).append("\n");
            }
        }
        return !sunkShips.isEmpty() ? sunkShips.toString() : "no ships sunk yet";
    }

    public List<Ship> getShips()
    {
        return ships;
    }

    public Ship getFirstAvailableShip(EncounterType encounterType) {
        for (Ship ship : ships) {
            if (ship.getState() == ShipState.ACTIVE) {
                return ship; //  pick first active ship regardless of type
            }
        }
        return null;
    }

    /**Returns the ship with the given name
     * @param name the name of the ship
     * @return the ship with the given name, or null if not found
     * **/
    public Ship getShipByName(String name) {
        for (Ship ship : ships) {
            if (ship.getName().equalsIgnoreCase(name)) {
                return ship;
            }
        }
        return null;
    }

    /**Returns a String representation of the ship with the given name
     * @param name the name of the ship
     * @return a String representation of the ship with the given name
     * **/
    public String getShipByNameString(String name)
    {
        Ship ship = getShipByName(name);
        if (ship != null) {
            return ship.toString();
        } else {
            return "No such ship";
        }
    }


    /**Updates the state of the ship to active
     * and adds it to the squadron
     * @param ship the ship to be commissioned
     * @return true if the ship is commissioned, false otherwise
     * **/
    public void commissionShip(Ship ship) {
        if (ship.getState() == ShipState.RESERVE) {
            ship.updateState(ShipState.ACTIVE);
            this.addShip(ship);
        }
    }

    /**Takes the ship out of the squadron and updates its state to reserve
     * @param ship the name of the ship to be decommissioned
     * @return Ship object that can be added to reserveFleet
     * **/
    public Ship deccommissionShip(String ship) {
        Ship s = getShipByName(ship);

        if (s != null) {
            if (s.getState() == ShipState.ACTIVE) {
                s.updateState(ShipState.RESERVE);
                this.removeShip(s);
            }
        }

        return s;
    }

    /**Restores the ship to the squadron and updates its state to active
     * @param ship the name of the ship to be restored
     * **/
    public String restoreShip(String ship) {
        Ship shipObj = getShipByName(ship);

        if (shipObj == null) {
            return "No such ship is currently present in your squadron.";
        }

        if (shipObj.getState() == ShipState.RESTING) {
            shipObj.updateState(ShipState.ACTIVE);
            return "Ship " + shipObj.getName() + " has been successfully restored to ACTIVE state.";
        } else if (shipObj.getState() == ShipState.ACTIVE) {
            return "Ship " + shipObj.getName() + " is already ACTIVE.";
        } else {
            return "Ship " + shipObj.getName() + " cannot be restored (must be RESTING).";
        }
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