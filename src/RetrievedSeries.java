import java.util.List;

public class RetrievedSeries extends Deck{


    public RetrievedSeries(List<Card> cards) {
        super(cards);
    }

    @Override
    public String toString() {
        return this.getCards().toString();
    }
}
