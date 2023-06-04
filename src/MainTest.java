import org.junit.jupiter.api.Test;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

public class MainTest {

    private Main main;

    @BeforeEach
    public void setUp() {
        String input = "2\nNo\nPlayer1\nPlayer2\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        main = new Main(inputStream);
    }

    @Test
    public void testGetNumberOfPlayers() {
        int numberOfPlayers = main.getNumberOfPlayers();
        Assertions.assertEquals(2, numberOfPlayers);
    }

    @Test
    public void testGetPlayWithAI() {
        boolean playWithAI = main.getPlayWithAI();
        Assertions.assertFalse(playWithAI);
    }


    @Test
    public void testAddHumanPlayers() {
        main.addHumanPlayers(2);
        List<Player> players = main.getPlayers();
        Assertions.assertEquals(2, players.size());
        Assertions.assertEquals("2", players.get(0).getName());
        Assertions.assertEquals("No", players.get(1).getName());
    }

    // Add more unit tests for other methods in the Main class

}
