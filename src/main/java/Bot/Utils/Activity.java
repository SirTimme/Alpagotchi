package Bot.Utils;

public enum Activity {
	SLEEP, WORK;

	public String getName() {
		return this.name().toLowerCase();
	}
}
