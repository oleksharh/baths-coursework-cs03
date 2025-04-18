package wars;

import java.util.*;
import java.io.*;

/**
 * This class implements the behaviour expected from the BATHS
 system as required for 5COM2007 Cwk1B BATHS - Feb 2025
 * 
 * @author A.A.Marczyk 
 * @version 16/02/25
 */

public class SeaBattles implements BATHS
{
    // may have one HashMap and select on stat
    private String admiral;
    private double warChest;
    private HashMap<String, Ship> reserveFleet;
    private HashMap<Integer, Encounter> encounters;
    private Squadron squadron;

//**************** BATHS ************************** 
    /** Constructor requires the name of the admiral
     * @param admiral the name of the admiral
     */  
    public SeaBattles(String admiral)
    {
        setupShips();
        setupEncounters();
        setupDefaults(admiral);
    }
    
    /** Constructor requires the name of the admiral and the
     * name of the file storing encounters
     * @param admiral the name of the admiral
     * @param filename name of file storing encounters
     */  
    public SeaBattles(String admiral, String filename)  //Task 3
    {
        setupShips();
        setupEncounters();
        // uncomment for testing Task
        readEncounters(filename);
        setupDefaults(admiral);
    }
    
    
    /**Returns a String representation of the state of the game,including the name of the 
     * admiral, state of the warChest,whether defeated or not, and the ships currently in 
     * the squadron,(or, "No ships" if squadron is empty), ships in the reserve fleet
     * @return a String representation of the state of the game,including the name of the 
     * admiral, state of the warChest,whether defeated or not, and the ships currently in 
     * the squadron,(or, "No ships" if squadron is empty), ships in the reserve fleet
     **/
    public String toString()
    {
        String defeated = isDefeated() ? "Defeated" : "Is OK";
        return  "\n  Name: " + admiral + ",\n" +
                "  Balance: " + warChest + ",\n" +
                "  Defeated: " + defeated + ",\n" +
                "  Squadron: " + getSquadron().replace("Squadron:\n", "")  +
                "  ReserveFleet: " + getReserveFleet();
    }
    
    /** returns true if War Chest <=0 and the admiral's squadron has no ships which 
     * can be retired. 
     * @returns true if War Chest <=0 and the admiral's fleet has no ships
     * which can be retired. 
     */
    public boolean isDefeated()
    {
        return warChest <= 0 && !squadron.hasShips();
    }
    
    /** returns the amount of money in the War Chest
     * @returns the amount of money in the War Chest
     */
    public double getWarChest()
    {
        return this.warChest;
    }
    
    
    /**Returns a String representation of all ships in the reserve fleet
     * @return a String representation of all ships in the reserve fleet
     **/
    public String getReserveFleet()
    {   //assumes reserves is a Hashmap

        // On initialisation, the reserve fleet is the same as the sample data

        StringBuilder sb = new StringBuilder();
        for (Ship ship : reserveFleet.values())
        {
            sb.append(ship.toString());
            sb.append("\n");
        }

        if (!sb.isEmpty())
            return sb.toString();

        return "No ships";

        // TODO: REMOVE ALL THE SHIPS AND UNIT TEST THIS METHOD
    }
    
    /**Returns a String representation of the ships in the admiral's squadron
     * or the message "No ships commissioned"
     * @return a String representation of the ships in the admiral's fleet
     **/
    public String getSquadron()
    {
        if (squadron.hasShips())
            return squadron.toString();

        return "No ships commissioned";
    }
    
    /**Returns a String representation of the ships sunk (or "no ships sunk yet")
     * @return a String representation of the ships sunk
     **/
    public String getSunkShips()
    {
        return squadron.getSunkShips();
    }
    
    /**Returns a String representation of the all ships in the game
     * including their status
     * @return a String representation of the ships in the game
     **/
    public String getAllShips()
    {
        StringBuilder sb = new StringBuilder();

        for (Ship ship : reserveFleet.values())
        {
            sb.append(ship.toString());
            sb.append("\n");
        }

        for (Ship ship : squadron.getShips())
        {
            sb.append(ship.toString());
            sb.append("\n");
        }

        return !sb.isEmpty() ? sb.toString() : "No ships";
    }
    
    
    /** Returns details of any ship with the given name
     * @return details of any ship with the given name
     **/
    public String getShipDetails(String nme)
    {
        Ship ship = this.getShipByName(nme);

        if (ship == null) // If not foudn in the reserve fleet, check the squadron
            return squadron.getShipByNameString(nme); // returns either No such ship or toString for ship

        return ship.toString();
    }     
 
    // ***************** Fleet Ships ************************   
    /** Allows a ship to be commissioned to the admiral's squadron, if there 
     * is enough money in the War Chest for the commission fee.The ship's 
     * state is set to "active"
     * @param nme represents the name of the ship
     * @return "Ship commissioned" if ship is commissioned, "Not found" if 
     * ship not found, "Not available" if ship is not in the reserve fleet, "Not 
     * enough money" if not enough money in the warChest
     **/        
    public String commissionShip(String nme)
    {
        Ship ship = this.getShipByName(nme);

        if (ship == null) return "Not found";
        if (ship.getCommissionFee() > warChest) return "Not enough money";
        if (!ship.isReserve()) return "Not available";

        warChest -= ship.getCommissionFee();

        squadron.commissionShip(ship);
        reserveFleet.remove(nme);
        return "Ship commissioned to the squadron: " + ship.getName();
    }


    /** Returns true if the ship with the name is in the admiral's squadron, false otherwise.
     * @param name is the name of the ship
     * @return returns true if the ship with the name is in the admiral's squadron, false otherwise.
     **/
    public boolean isInSquadron(String name)
    {
        Ship ship = squadron.getShipByName(name);

        return ship != null && !ship.isResting() && !ship.isSunk();
    }
    
    /** Decommissions a ship from the squadron to the reserve fleet (if they are in the squadron)
     * pre-condition: isInSquadron(nme)
     * @param name is the name of the ship
     * @return true if ship decommissioned, else false
     **/
    public boolean decommissionShip(String name) {

        if (!isInSquadron(name))
            return false; // Ship not in the squadron

        Ship ship = squadron.deccommissionShip(name);


        warChest += (double) ship.getCommissionFee() / 2;
        reserveFleet.put(name, ship);

        return true;
    }
    
  
    /**Restores a ship to the squadron by setting their state to ACTIVE
     * @param ref the name of the ship to be restored
     */
    public void restoreShip(String ref) {
        if (ref == null || ref.isEmpty()) return;

        squadron.restoreShip(ref);
    }
    
//**********************Encounters************************* 
    /** returns true if the number represents a encounter
     * @param num is the reference number of the encounter
     * @returns true if the reference number represents a encounter, else false
     **/
     public boolean isEncounter(int num)
     {
        return encounters.containsKey(num);
     }
     
     
/** Retrieves the encounter represented by the encounter 
      * number.Finds a ship from the fleet which can fight the 
      * encounter.The results of fighting an encounter will be 
      * one of the following: 
      * 0-Encounter won by...(ship reference and name)-add prize money to War 
      * Chest and set ship's state to RESTING,  
      * 1-Encounter lost as no ship available - deduct prize from the War Chest,
      * 2-Encounter lost on battle skill and (ship name) sunk" - deduct prize 
      * from War Chest and set ship state to SUNK.
      * If an encounter is lost and admiral is completely defeated because there 
      * are no ships to decommission,add "You have been defeated " to message, 
      * -1 No such encounter
      * Ensure that the state of the war chest is also included in the return message.
      * @param encNo is the number of the encounter
      * @return a String showing the result of fighting the encounter
      */ 
    public String fightEncounter(int encNo)
    {
       Encounter encounter = encounters.get(encNo);

        if (encounter == null) {
            return "-1 No such encounter";
        }

        Ship ship = squadron.getFirstAvailableShip(encounter.getType());

        if (ship == null) {
            warChest -= encounter.getPrizeMoney();
            String result = "1-Encounter lost as no ship available. War Chest: " + warChest;
            result += isDefeatedString();

            return result;
        }

        if (!ship.canFight(encounter.getType())) {
            warChest -= encounter.getPrizeMoney();
            String result = "1-Encounter lost as no ship available. War Chest: " + warChest;
            result += isDefeatedString();

            return result;
        }

        if (ship.getBattleSkill() >= encounter.getRequiredSkill()) {
            warChest += encounter.getPrizeMoney();
            ship.setResting();
            return "0-Encounter won by " + ship.getName() + ". War Chest: " + warChest;
        }

        warChest -= encounter.getPrizeMoney();
        ship.setSunk();

        String result = "2-Encounter lost on battle skill. " + ship.getName() + " sunk. War Chest: " + warChest;
        result += isDefeatedString();

        return result;
    }

    /** Provides a String representation of an encounter given by 
     * the encounter number
     * @param num the number of the encounter
     * @return returns a String representation of a encounter given by 
     * the encounter number
     **/
    public String getEncounter(int num)
    {
        Encounter enc = encounters.get(num);

        if (enc != null)
            return enc.toString();

        return "\nNo such encounter";
    }
    
    /** Provides a String representation of all encounters 
     * @return returns a String representation of all encounters
     **/
    public String getAllEncounters()
    {
        StringBuilder sb = new StringBuilder();

        for (Encounter enc : encounters.values())
        {
            sb.append(enc.toString());
            sb.append("\n");
        }

        if (!sb.isEmpty())
            return sb.toString();

        return "No encounters";
    }


    //****************** private methods for Task 4 functionality*******************
    //*******************************************************************************
     private void setupShips()
     {
         reserveFleet = new HashMap<>();

         reserveFleet.put("Victory", new ManOWar("Victory", "Alan Aikin", 3, ShipState.RESERVE, 3, 30));
         reserveFleet.put("Sophie", new Frigate("Sophie", "Ben Baggins", 8, ShipState.RESERVE, 16, true));
         reserveFleet.put("Endeavour", new ManOWar("Endeavour", "Col Cannon", 4, ShipState.RESERVE, 2, 20));
         reserveFleet.put("Arrow", new Sloop("Arrow", "Dan Dare", 150, ShipState.RESERVE, true));
         reserveFleet.put("Belerophon", new ManOWar("Belerophon", "Ed Evans", 8, ShipState.RESERVE, 3, 50));
         reserveFleet.put("Surprise", new Frigate("Surprise", "Fred Fox", 6, ShipState.RESERVE, 10, false));
         reserveFleet.put("Jupiter", new Frigate("Jupiter", "Gil Gamage", 7, ShipState.RESERVE, 20, false));
         reserveFleet.put("Paris", new Sloop("Paris", "Hal Henry", 200, ShipState.RESERVE, true));
         reserveFleet.put("Beast", new Sloop("Beast", "Ian Idle", 400, ShipState.RESERVE, false));
         reserveFleet.put("Athena", new Sloop("Athena", "John Jones", 100, ShipState.RESERVE, true));
     }
     
    private void setupEncounters()
    {
        encounters = new HashMap<Integer, Encounter>();

        readEncounters("encountersAM.txt");

        if (encounters.isEmpty())
            readEncounters("baths-students/encountersAM.txt");

        if (encounters.isEmpty())
        {
            encounters.put(1, new Encounter(1, EncounterType.BATTLE, "Trafalgar", 3, 300));
            encounters.put(2, new Encounter(2, EncounterType.SKIRMISH, "Belle Isle", 3, 120));
            encounters.put(3, new Encounter(3, EncounterType.BLOCKADE, "Brest", 3, 150));
            encounters.put(4, new Encounter(4, EncounterType.BATTLE, "StMalo", 9, 200));
            encounters.put(5, new Encounter(5, EncounterType.BATTLE, "Dieppe", 7, 90));
            encounters.put(6, new Encounter(6, EncounterType.SKIRMISH, "Jersey", 8, 45));
            encounters.put(7, new Encounter(7, EncounterType.BLOCKADE, "Nantes", 6, 130));
            encounters.put(8, new Encounter(8, EncounterType.BATTLE, "Finisterre", 4, 100));
            encounters.put(9, new Encounter(9, EncounterType.SKIRMISH, "Biscay", 5, 200));
            encounters.put(10, new Encounter(10, EncounterType.BATTLE, "Cadiz", 1, 250));
        }
    }
        
    // Useful private methods to "get" objects from collections/maps
    //*******************************************************************************

    /** Returns the ship with the given name from the reserve fleet or the squadron
     * @param name the name of the ship
     * @return the ship with the given name from the reserve fleet or the squadron
     **/
    private Ship getShipByName(String name)
    {
        // Convert name to lower case and remove leading/trailing spaces
        name = name.toLowerCase();
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        name = name.trim();

        Ship ship = reserveFleet.get(name);

        if (ship == null)
            ship = squadron.getShipByName(name);

        return ship;
    }

    /**Setups the default values for the game, when the constructor is called
     * @param admiral the name of the admiral
     * **/
    private void setupDefaults(String admiral)
    {
        this.admiral = admiral;
        this.warChest = 1000;
        squadron = new Squadron();
    }

    private String isDefeatedString()
    {
        if (isDefeated())
            return "\nYou have been defeated.";

        return "";
    }

    //*******************************************************************************
  
    //************************ Task 3 ************************************************

    
    //******************************** Task 3.5 **********************************
    /** reads data about encounters from a text file and stores in collection of
     * encounters.Data in the file is editable
     * @param filename name of the file to be read
     */
    public void readEncounters(String filename)
    {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int encounterId = 1;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    EncounterType type = EncounterType.valueOf(parts[0].toUpperCase());
                    String location = parts[1];
                    int skill = Integer.parseInt(parts[2]);
                    int prizeMoney = Integer.parseInt(parts[3]);

                    Encounter encounter = new Encounter(encounterId, type, location, skill, prizeMoney);
                    encounters.put(encounterId, encounter);

                    encounterId ++; // increment encounter ID
                }
            }
        } catch (IOException e) {
            // TODO: File's missing, or not readable
        }
    }
 
    
    // ***************   file write/read  *********************
    /** Writes whole game to the specified file
     * @param fname name of file storing requests
     */
    public void saveGame(String fname)
    {   // uses object serialisation 
           try (ObjectOutputStream oOutS = new ObjectOutputStream(new FileOutputStream(fname))) {
               oOutS.writeObject(this);
           } catch (IOException e) {
               // Does nothing just skips saveGame
           }
    }
    
    /** reads all information about the game from the specified file 
     * and returns 
     * @param fname name of file storing the game
     * @return the game (as an SeaBattles object)
     */
    public SeaBattles loadGame(String fname)
    {   // uses object serialisation
        try (ObjectInputStream oInS = new ObjectInputStream(new FileInputStream(fname))) {
            SeaBattles game = (SeaBattles) oInS.readObject();

            return game;
        } catch (IOException | ClassNotFoundException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Error loading game, no such file ").append(fname).append("\n");
            throw new RuntimeException(e);
        }
    }
}



