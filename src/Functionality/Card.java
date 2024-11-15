package functionality;

// public class Card {
//     protected String cardName;
//     private String cardNumber;
//     private String expirationDate;
//     private String cardHolderName;

//     public Card(String cardName, String cardNumber, String expirationDate, String cardHolderName) {
//         this.cardName = cardName;
//         this.cardNumber = cardNumber;
//         this.expirationDate = expirationDate;
//         this.cardHolderName = cardHolderName;
//     }

//     public String getCard() {
//         String last4Digits = cardNumber.substring(cardNumber.length() - 4);
//         return cardName + "***" + last4Digits;
//     }

//     protected String getCardNumber() {
//         return cardNumber;
//     }

// }
// First, let's enhance the Card class with validation methods
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

    protected String getCardNumber() {
        return cardNumber;
    }

    // New methods for card validation
    public boolean isValid() {
        return isCardNumberValid() && 
               !isExpired() && 
               cardHolderName != null && 
               !cardHolderName.trim().isEmpty();
    }

    private boolean isCardNumberValid() {
        if (cardNumber == null || cardNumber.trim().isEmpty()) {
            return false;
        }
        
        // Remove any spaces or dashes
        String number = cardNumber.replaceAll("[-\\s]", "");
        
        // Check if the card number contains only digits and has valid length (13-19 digits)
        if (!number.matches("\\d{13,19}")) {
            return false;
        }
        
        // Implement Luhn algorithm for card number validation
        int sum = 0;
        boolean alternate = false;
        
        // Loop through values starting from the rightmost digit
        for (int i = number.length() - 1; i >= 0; i--) {
            int digit = Character.digit(number.charAt(i), 10);
            
            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            
            sum += digit;
            alternate = !alternate;
        }
        
        return (sum % 10 == 0);
    }

    private boolean isExpired() {
        try {
            // Assuming expirationDate is in format "MM/YY" or "MM/YYYY"
            String[] parts = expirationDate.split("/");
            if (parts.length != 2) {
                return true;
            }

            int month = Integer.parseInt(parts[0]);
            int year = Integer.parseInt(parts[1]);
            
            // Convert 2-digit year to 4-digit year
            if (year < 100) {
                year += 2000;
            }

            // Get current date
            java.time.YearMonth currentYearMonth = java.time.YearMonth.now();
            java.time.YearMonth cardExpirationDate = java.time.YearMonth.of(year, month);

            // Check if card is expired
            return cardExpirationDate.isBefore(currentYearMonth);
            
        } catch (Exception e) {
            // If there's any error parsing the date, consider the card expired
            return true;
        }
    }
}