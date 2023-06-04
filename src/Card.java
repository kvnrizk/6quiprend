import javax.swing.*;

public class Card {

    private int number ;
    private int beefHead ;
    private ImageIcon imageIcon;

    public Card(int number) {
        this.number = number;
        this.beefHead = getCardBeefHead();
    }

    public int getNumber() {
        return number;
    }

    public int getBeefHead() {
        return beefHead;
    }


    public int getCardBeefHead() {
        if (this.number == 55) {
            return 7;
        } else if (this.number % 11 == 0) {
            return 5;
        } else if(this.number % 10 == 0) {
            return 3;
        } else if (this.number % 5 == 0) {
            return 2;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return "Card " + this.getNumber() + "(" + this.getBeefHead() + " Tete)";
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return getNumber() == card.getNumber();
    }
}


