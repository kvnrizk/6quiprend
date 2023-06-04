import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class CardSeriesTest {

    private CardSeries cardSeries;

    @BeforeEach
    public void setUp() {
        List<Card> cards = Arrays.asList(
                new Card(3),
                new Card(6),
                new Card(9)
        );
        cardSeries = new CardSeries(1, cards);
    }

    @Test
    public void testGetPosition() {
        int position = cardSeries.getPosition();
        Assertions.assertEquals(1, position);
    }

    @Test
    public void testGetCards() {
        List<Card> cards = cardSeries.getCardsInTable();
        Assertions.assertEquals(3, cards.size());
        Assertions.assertEquals(3, cards.get(0).getNumber());
        Assertions.assertEquals(6, cards.get(1).getNumber());
        Assertions.assertEquals(9, cards.get(2).getNumber());
    }

    // Add more unit tests for other methods in the CardSeries class

}
