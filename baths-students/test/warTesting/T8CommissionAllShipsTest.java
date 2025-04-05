package warTesting;

import wars.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class T8CommissionAllShipsTest {
    BATHS game;

    public T8CommissionAllShipsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        try {
            game = new SeaBattles("Jean");

            // Set warChest to a very high value for testing using reflection
            Field warChestField = SeaBattles.class.getDeclaredField("warChest");
            warChestField.setAccessible(true);
            warChestField.set(game, Double.MAX_VALUE);

            // Commission all ships
            game.commissionShip("Victory");
            game.commissionShip("Sophie");
            game.commissionShip("Endeavour");
            game.commissionShip("Arrow");
            game.commissionShip("Belerophon");
            game.commissionShip("Surprise");
            game.commissionShip("Jupiter");
            game.commissionShip("Paris");
            game.commissionShip("Beast");
            game.commissionShip("Athena");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            fail("Exception during setup: " + e.getMessage());
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetReserveFleet() {
        String result = game.getReserveFleet();
        assertEquals("No ships", result);
    }

    @Test
    public void testGetSquadron() {
        String result = game.getSquadron();
        assertNotNull(result);
        assertTrue(result.contains("Arrow"));
    }

    @Test
    public void testGetSunkShips() {
        String result = game.getSunkShips();
        assertEquals("No ships sunk yet", result);
    }

    @Test
    public void testGetAllShips() {
        String result = game.getAllShips();
        assertNotNull(result);
        assertTrue(result.contains("Victory"));
    }

    @Test
    public void testGetShipDetails() {
        String result = game.getShipDetails("Athena");
        assertNotNull(result);
        assertTrue(result.contains("Athena"));
    }

    @Test
    public void testCommissionShip() {
        String result = game.commissionShip("Surprise");
        assertEquals("Not available", result);
    }

    @Test
    public void testIsInSquadron() {
        boolean result = game.isInSquadron("Sophie");
        assertTrue(result);
    }
}
