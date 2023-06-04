import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    public void testGetNumber() {
        Card card = new Card(5);
        int number = card.getNumber();
        Assertions.assertEquals(5, number);
    }

    @Test
    public void testGetBeefHead() {
        Card card = new Card(10);
        int beefHead = card.getBeefHead();
        Assertions.assertEquals(3, beefHead);
    }

    @Test
    public void testGetCardBeefHead() {
        Card card1 = new Card(11);
        int beefHead1 = card1.getCardBeefHead();
        Assertions.assertEquals(5, beefHead1);

        Card card2 = new Card(25);
        int beefHead2 = card2.getCardBeefHead();
        Assertions.assertEquals(2, beefHead2);

        Card card3 = new Card(55);
        int beefHead3 = card3.getCardBeefHead();
        Assertions.assertEquals(7, beefHead3);

        Card card4 = new Card(17);
        int beefHead4 = card4.getCardBeefHead();
        Assertions.assertEquals(1, beefHead4);
    }

    // Add more unit tests for other methods in the Card class

}
