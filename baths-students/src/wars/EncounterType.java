package wars;

import java.io.*;
/**
 * Enumeration class EncounterType
 * Defines states of the encounters
 * 
 * @author A.Marczyk
 * @version 12/02/2025
 */
public enum EncounterType implements Serializable
{
    BLOCKADE(" Blockade"),
    BATTLE(" Battle"),
    SKIRMISH (" Skirmish"),
    INVALID (" Invalid");

    private String type;
    
    private EncounterType(String ty)
    {
        type = ty;
    }
    
    public String toString()
    {
        return type;
    }
}
