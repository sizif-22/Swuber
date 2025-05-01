package functionality;

import db.DBConfig;

public class Card {
    int cardId;
    private String cardName;
    private String cardNumber;
    private String expirationDate;
    private String cardHolderName;
    private int userId;
    private static DBConfig dbConnect;

    public Card(String cardName, String cardNumber, String expirationDate, String cardHolderName, int userId, DBConfig dbConnect) {
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cardHolderName = cardHolderName;
        this.userId = userId;
        Card.dbConnect = dbConnect;
    }

    public static void setDBConnect(DBConfig dbConfig) {
        dbConnect = dbConfig;
    }

    public String getCardL4Numbers() {
        if (cardNumber != null && cardNumber.length() >= 4) {
            return "****" + cardNumber.substring(cardNumber.length() - 4);
        } else if (cardNumber != null) {
            return cardNumber;
        } else {
            return "";
        }
    }

    public String getCard() {
        return this.cardName + " " + this.cardNumber + " " + this.expirationDate + " " + this.cardHolderName;
    }

    public String getCardName() {
        return this.cardName;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }
}