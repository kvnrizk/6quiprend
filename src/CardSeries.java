import java.util.ArrayList;
import java.util.List;

public class CardSeries {

    private List<Card> cardsInTable;
    private int position;
    public CardSeries(int position, List<Card> cardsInTable)
    {
        this.position = position;
        this.cardsInTable = cardsInTable;
    }

    @Override
    public String toString() {
        return "Series " + position;
    }

    public List<Card> getCardsInTable() {
        return cardsInTable;
    }

    public void setCardsInTable(List<Card> cardsInTable) {
        this.cardsInTable = cardsInTable;
    }

    public Card getLastCardOf(){
        return this.cardsInTable.get(cardsInTable.size()-1);
    }

    public int getDifferenceBetweenLastAndNew(Card newCard){
        int nbOfLastCard = getLastCardOf().getNumber();
        int nbOfNewCard = newCard.getNumber();
        return nbOfNewCard - nbOfLastCard;
    }

    public int getNbOfCard(){
        return this.cardsInTable.size();
    }

    public static CardSeries newSeries(int position, Card card){
        List<Card> cardList = new ArrayList<>();
        cardList.add(card);
        return new CardSeries(position, cardList);
    }

    public int getPosition() {
        return position;
    }

    public int getTotalBeefHead(){
        int total = 0;
        for (Card card : this.getCardsInTable())
        {
            total = total + card.getCardBeefHead();
        }
        return total;
    }
}

