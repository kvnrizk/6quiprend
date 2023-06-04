import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;


public class AITest {

    private AI ai;

    @BeforeEach
    public void setUp() {
        Deck deck = new Deck(Arrays.asList(
                new Card(5),
                new Card(8),
                new Card(12),
                new Card(15),
                new Card(20)
        ));
        RetrievedSeries pack = new RetrievedSeries(Arrays.asList(
                new Card(2),
                new Card(4),
                new Card(7)
        ));
        ai = new AI("AI", deck, pack);
    }

    @Test
    public void testGetCard() {
        List<CardSeries> seriesList = Arrays.asList(
                new CardSeries(1, Arrays.asList(new Card(3), new Card(6))),
                new CardSeries(2, Arrays.asList(new Card(9), new Card(11))),
                new CardSeries(3, Arrays.asList(new Card(14), new Card(18))),
                new CardSeries(4, Arrays.asList(new Card(22), new Card(25)))
        );

        Card card = ai.getCard(seriesList);
        Assertions.assertNotNull(card);
        Assertions.assertTrue(card.getNumber() >= 5 && card.getNumber() <= 20);
    }

    @Test
    public void testGetCardTooWeak() {
        Card card = ai.getCardTooWeak();
        Assertions.assertNotNull(card);
        Assertions.assertEquals(5, card.getNumber());
    }

    @Test
    public void testGetSeriesToRetrieve() {
        List<CardSeries> seriesList = Arrays.asList(
                new CardSeries(1, Arrays.asList(new Card(3), new Card(6))),
                new CardSeries(2, Arrays.asList(new Card(9), new Card(11))),
                new CardSeries(3, Arrays.asList(new Card(14), new Card(18))),
                new CardSeries(4, Arrays.asList(new Card(22), new Card(25)))
        );

        CardSeries series = ai.getSeriesToRetrieve(seriesList);
        Assertions.assertNotNull(series);
        Assertions.assertEquals(1, series.getPosition());
    }

    // Add more unit tests for other methods in the AI class

}
