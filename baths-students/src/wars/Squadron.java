package wars;
import java.io.*;
import java.util.*;

/**
 * The Squadron class holds a group of ships that are currently in use.
 * It lets you add, remove, find, restore, and manage the states of ships.
 */
class Squadron implements Serializable {
    private List<Ship> ships;


    /** Creates a new, empty squadron. */
    public Squadron() {
        ships = new ArrayList<>();
    }

    /**
     * Adds a ship to the squadron.
     *
     * @param ship the ship to add
     */
    public void addShip(Ship ship) {
        ships.add(ship);
    }

    /**
     * Removes a ship from the squadron.
     *
     * @param ship the ship to remove
     */
    public void removeShip(Ship ship) {
        ships.remove(ship);
    }

    /**
     * Checks if there are any ships that aren't sunk.
     *
     * @return true if at least one ship isn't sunk, false otherwise
     */
    public boolean hasShips()
    {
        boolean hasShips = false;
        for (Ship ship : ships)
        {
            if (!ship.isSunk()) {
                hasShips = true;
                break;
            }
        }
        return hasShips;
    }

    /**
     * Gets a list of sunk ships.
     *
     * @return a string with names of sunk ships or a message if none are sunk
     */
    public String getSunkShips() {
        StringBuilder sunkShips = new StringBuilder();
        for (Ship ship : ships) {
            if (ship.isSunk()) {
                sunkShips.append(ship.getName()).append("\n");
            }
        }
        return !sunkShips.isEmpty() ? sunkShips.toString() : "No ships sunk yet";
    }

    /**
     * @return the list of all ships in the squadron
     */
    public List<Ship> getShips()
    {
        return ships;
    }

    /**
     * Gets the first active ship
     *
     * @param encounterType the type of encounter (not used here)
     * @return the first active ship, or null if none found
     */
    public Ship getFirstAvailableShip(EncounterType encounterType) {
        for (Ship ship : ships) {
            if (ship.isActive()) {
                return ship;
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
        if (ship.isReserve()) {
            ship.setActive();
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
            if (s.isActive()) {
                s.setReserve();
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

        if (shipObj.isResting()) {
            shipObj.setActive();
            return "Ship " + shipObj.getName() + " has been successfully restored to ACTIVE state.";
        } else if (shipObj.isActive()) {
            return "Ship " + shipObj.getName() + " is already ACTIVE.";
        } else {
            return "Ship " + shipObj.getName() + " cannot be restored (must be RESTING).";
        }
    }


    /**
     * Returns a string showing all ships in the squadron.
     */
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