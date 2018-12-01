package pack;

/**
 * @author BingelJ Die Volocoper Klasse verwaltet Volocopter-Objekte, die in
 *         VolocopterSpiel verwendet werden.
 *
 */
public class Volocopter {
	private double hoehe;
	private double geschwindigkeit;

	private int schub;
	private double beschleunigung;
	private int position;

	/**
	 * Konstruktor der Volocopter Klasse, der alle Attribute initialisiert
	 */
	Volocopter() {
		hoehe = 100;
		geschwindigkeit = 0;
		schub = 0;
		position = 0;

	}

	/**
	 * Position des Volocopters wird um 1 erhoeht, da er sich wegen Benutzereingabe
	 * oder Windeinfluss nach rechts bewegen soll.
	 */
	public void bewegeRechts() {
		this.position = this.position + 1;
	}

	/**
	 * Position des Volocopters wird um 1 vermindert, da er sich wegen
	 * Benutzereingabe oder Windfluss nach links bewegen soll.
	 */
	public void bewegeLinks() {
		this.position = this.position - 1;
	}

	@Override
	public String toString() {
		return String.format(" %-8.2f %-18.2f %-16d", this.hoehe, this.geschwindigkeit, this.schub);
	}

	public double getHoehe() {
		return hoehe;
	}

	public void setHoehe(double hoehe) {
		this.hoehe = hoehe;
	}

	public double getGeschwindigkeit() {
		return geschwindigkeit;
	}

	public void setGeschwindigkeit(double geschwindigkeit) {
		this.geschwindigkeit = geschwindigkeit;
	}

	public int getSchub() {
		return schub;
	}

	public void setSchub(int schub) {
		this.schub = schub;
	}

	public double getBeschleunigung() {
		return beschleunigung;
	}

	public void setBeschleunigung(double beschleunigung) {
		this.beschleunigung = beschleunigung;
	}

	public int getPosition() {
		return position;
	}

}
