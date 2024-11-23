package fun;

public class Payment {
	private Ride ride;
	private User user;
	private Card card;
	private double cost;

	public Payment(Ride ride, User user, Card card, double cost) {
		this.ride = ride;
		this.user = user;
		this.card = card;
		this.cost = cost;
	}

	public void applyDiscount(String code) {
		if (code == null) {
			return;
		}

		switch (code.toUpperCase()) {
			case "NEWUSER":
				this.cost *= 0.8;
				break;
			case "WEEKEND":
				this.cost *= 0.9;
				break;
			case "HOLIDAY":
				this.cost *= 0.85;
				break;
			default:
				break;
		}
	}

	public boolean processPayment(String discountCode) {
		applyDiscount(discountCode);
		this.ride.setStatus("COMPLETED");
		return true;
	}

	public Ride getRide() {
		return ride;
	}

	public User getUser() {
		return user;
	}

	public Card getCard() {
		return card;
	}

	public double getCost() {
		return cost;
	}

}