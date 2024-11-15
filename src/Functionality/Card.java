package Functionality;

public class Card {
    protected String cardName;
    private String cardNumber;
    private String expirationDate;
    private String cardHolderName;

    public Card(String cardName, String cardNumber, String expirationDate, String cardHolderName) {
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cardHolderName = cardHolderName;
    }

    public String getCard() {
        String last4Digits = cardNumber.substring(cardNumber.length() - 4);
        return cardName + "***" + last4Digits;
    }

    
}