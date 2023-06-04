import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AI extends Player{

    public AI(String name, Deck deck, RetrievedSeries pack) {
        super(name, deck, pack);
    }

    public Card getCard(List<CardSeries> seriesList) {
       List<Card> cardList = new ArrayList<>();

        for (Card card : this.getDeck().getCards()) {
            List<CardSeries> possibleSeries = new ArrayList<>();
            for (CardSeries series : seriesList) {
                if (series.getLastCardOf().getNumber() < card.getNumber()) {
                    possibleSeries.add(series);
                }
            }
            if (!possibleSeries.isEmpty()) {
                cardList.add(card);
            }
        }

        if (cardList.size() > 0)
        {
            Random rd = new Random();
            int index = rd.nextInt(cardList.size());
            return cardList.get(index);
        }
        return null;
    }


    public Card getCardTooWeak(){
        List<Integer> numberList = new ArrayList<>();
        for (Card card : this.getDeck().getCards())
        {
            numberList.add(card.getNumber());
        }
        int min = Collections.min(numberList);
        int index = numberList.indexOf(min);
        return this.getDeck().getCards().get(index);
    }

    public CardSeries getSeriesToRetrieve(List<CardSeries> seriesList){
        List<Integer> pointList = new ArrayList<>();
        for (CardSeries series : seriesList)
        {
            int point = series.getTotalBeefHead();
            pointList.add(point);
        }
        int min = Collections.min(pointList);
        int index = pointList.indexOf(min);
        return seriesList.get(index);
    }

}
