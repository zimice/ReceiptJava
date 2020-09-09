
public enum Currency {
	czk, eur, usd;

	public static double toDoubleCrypto(Currency currency, double amount) {
		switch (currency) {
		case czk: {
			return amount;
		}
		case eur: {
			return amount / 26;
		}
		case usd: {
			return amount / 22;
		}
		default: {
			return 0;
		}
		}
	}

	public static double fromDoubleCrypto(Currency currency, double amount) {
		switch (currency) {
		case czk: {
			return amount;
		}
		case eur: {
			return amount * 26;
		}
		case usd: {
			return amount * 22;
		}
		default: {
			return 0;
		}
		}
	}
}
