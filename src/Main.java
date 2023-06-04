import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Main {

    public Game game;
    private Scanner scanner;

    private List<Player> players;
    private List<CardSeries> seriesListInTable;

    public Main(InputStream is) {
        this.game = new Game();
        this.scanner = new Scanner(is);
        this.players=new ArrayList<Player>();
    }

    public static void main(String[] args) {
        Main main = new Main(System.in);
        main.play();
    }

    public List<Player> getPlayers() {
        return this.players;
    }
    public void displayAllSeries(List<CardSeries> series) {
        int maxSize = Math.max(
                Math.max(series.get(0).getNbOfCard(), series.get(1).getNbOfCard()),
                Math.max(series.get(2).getNbOfCard(), series.get(3).getNbOfCard())
        );

        System.out.print("            ");
        for (int i = 0; i < series.size(); i++) {
            System.out.printf("Series %d\t\t", i + 1);
        }
        System.out.println();

        System.out.print("            ");
        for (int i = 0; i < series.size(); i++) {
            System.out.print("---------------\t");
        }
        System.out.println();

        for (int i = 0; i < maxSize; i++) {
            for (int j = 0; j < series.size(); j++) {
                List<Card> cards = series.get(j).getCardsInTable();
                String element = i < cards.size() ? cards.get(i).toString() : "";
                if (j == 0)
                    System.out.print("            ");
                System.out.printf("%-15s|", element);
            }
            System.out.println();
        }
    }
    private void play() {
//        List<Card> allCard = game.getAllCard();
        players = game.getPlayers();
        seriesListInTable = game.getSeriesListInTable();

        game.shuffleCards();

        int nbOfPlayers = getNumberOfPlayers();
        boolean playWithAI = getPlayWithAI();

        if (playWithAI) {
            game.addAIPlayer();
            System.out.println("You decided to add an AI.");
        }

        addHumanPlayers(nbOfPlayers);

        System.out.println("Here is the list of players: " + players.toString());

        game.initSeriesOnTable();

        while (!game.areAllDecksEmpty(players)) {
            System.out.println("Here are the series on the table:");
            displayAllSeries(seriesListInTable);

            List<Integer> chosenNumberList = getPlayerCardSelection();
            HashMap<Integer, Player> getPlayerFromChosenCard = game.mapPlayersToChosenCards(chosenNumberList);

            Collections.sort(chosenNumberList);

            for (int number : chosenNumberList) {
                Card playerCard = new Card(number);
                Player currentPlayer = getPlayerFromChosenCard.get(number);

                System.out.println("It's " + currentPlayer.getName() + "'s turn");

                if (currentPlayer instanceof AI) {
                    AI aiPlayer = (AI) currentPlayer;
                    boolean aiCardTooWeak = false;

                    if (game.getTheSeriesWithSmallestDifference(playerCard) == null) {
                        aiCardTooWeak = true;
                    }

                    if (!aiCardTooWeak) {
                        CardSeries aiSeries = game.getTheSeriesWithSmallestDifference(playerCard);

                        if (aiSeries.getNbOfCard() == 5) {
                            game.processForFullSeries(aiPlayer, aiSeries,playerCard);
                            System.out.println("This series is full, so AI has retrieved the series " + aiSeries.getPosition() + ". The AI's card becomes the first card of the series.");
                        } else {
                            game.normalProcess(aiPlayer, aiSeries, playerCard);
                            System.out.println("AI chose series " + aiSeries.getPosition() + " for the card " + playerCard.toString());
                        }
                    } else {
                        CardSeries aiSeries = aiPlayer.getSeriesToRetrieve(seriesListInTable);
                        game.processForCardTooWeak(aiSeries.getPosition(), aiPlayer, playerCard);
                        System.out.println("AI's card is too weak. It retrieved series " + aiSeries.getPosition() + ".");
                    }
                } else {
                    boolean isPossible = false;
                    while (!isPossible) {
                        System.out.println("Please choose the series you want to put you card " + playerCard.toString() + " in:");
                        displayAllSeries(seriesListInTable);
                        int index = scanner.getIntegerInRange(1, seriesListInTable.size());
                        CardSeries chosenSeries = seriesListInTable.get(index - 1);
                        Card lastCardInSeries = chosenSeries.getLastCardOf();

                        if (lastCardInSeries.getNumber() < number) {
                            if (game.getTheSeriesWithSmallestDifference(playerCard).getPosition() == chosenSeries.getPosition()) {
                                if (chosenSeries.getNbOfCard() == 5) {
                                    System.out.println("This series is full, so you need to retrieve the cards of this series. Therefore, your card becomes the first card of the series.");
                                    game.processForFullSeries(currentPlayer, chosenSeries, playerCard);
                                    isPossible = true;
                                } else {
                                    game.normalProcess(currentPlayer, chosenSeries, playerCard);
                                    System.out.println("You chose series " + chosenSeries.getPosition() + " for the card " + playerCard.toString());
                                    isPossible = true;
                                }
                            } else {
                                System.out.println("You are unable to select this series because the discrepancy with the previous card is not the smallest.");
                            }
                        } else if (game.isCardTooWeak(playerCard)) {
                            System.out.println("Your card is too weak, please choose the series you want to take.");
                            int i = scanner.getIntegerInRange(1, seriesListInTable.size());
                            game.processForCardTooWeak(i, currentPlayer, playerCard);
                            isPossible = true;
                        } else {
                            System.out.println("You cannot select this series because your card is smaller than the last card in the series.");
                        }
                    }
                }
            }
        }

        List<Player> winners = game.determineWinner();
       System.out.println("The winner is: " + winners.toString());
    }

    public int getNumberOfPlayers() {
        System.out.println("Number of players?");
        return scanner.getIntegerInRange(1, 10);
    }

    public boolean getPlayWithAI() {
        String answer = scanner.getTextWithPrompt("Press YES to add an AI!");
        return answer.equalsIgnoreCase("Yes");
    }



    public void addHumanPlayers(int numberOfPlayers) {
        for (int i = 1; i <= numberOfPlayers; i++) {
            System.out.println("Name of player " + i);
            String name = scanner.getText();
            Deck deck = game.getPlayerDeck();
            List<Card> retrievedCards = new ArrayList<>();
            RetrievedSeries pack = new RetrievedSeries(retrievedCards);
            players.add(new Player(name, deck, pack));
        }
    }



    private List<Integer> getPlayerCardSelection() {
        List<Integer> chosenNumberList = new ArrayList<>();

        for (Player player : players) {
            System.out.println("It's " + player.getName() + "'s turn");

            if (player instanceof AI) {
                AI ai = (AI) player;
                Card aiCard = ai.getCard(seriesListInTable);

                if (aiCard != null) {
                    System.out.println("AI chose the card " + aiCard.toString());
                } else {
                    aiCard = ai.getCardTooWeak();
                    System.out.println("AI chose the card too weak " + aiCard.toString());
                }

                int numberOfCard = aiCard.getNumber();
                chosenNumberList.add(numberOfCard);
                System.out.println("AI has chosen his card");
            } else {
                System.out.println("Choose a card you want to play?");
                System.out.println("Your deck: " + player.getDeck().toString());
                int number = scanner.getCardNumberInput(player.getDeck());
                chosenNumberList.add(number);
            }
        }

        return chosenNumberList;
    }






}
