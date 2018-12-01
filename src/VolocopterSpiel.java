package pack;

import java.util.*;

/**
 * @author BingelJ 25.11.2018 Die VolocopterSpiel-Klasse erfuellt die
 *         Anforderungen der 4. Aufgabe "Volocoper steuern" vom Modul Prog1
 *         AWIS18 WS18/19 an der HS Mainz.
 *
 */
public class VolocopterSpiel {

	private Volocopter volocopter;
	private Wind wind;
	private Scanner konsolenInput;
	private int zeit;
	private char[] positionDarstellung;

	/**
	 * Konstruktor der VolocopterSpiel-Klasse.
	 */
	public VolocopterSpiel() {
		this.volocopter = new Volocopter();
		this.wind = new Wind();
		this.zeit = 0;
		this.positionDarstellung = new char[7];
		konsolenInput = new Scanner(System.in);

	}

	/**
	 * Diese Methode simuliert das gesamte Spiel und ruft dafuer, solange die Hoehe
	 * > 0 ist, eineRundespielen auf.
	 * 
	 * @return Landebewertung, die von this.bewerteLandung() bereitgestellt wird.
	 */
	public String einGesamtesSpiel() {

		// die erste Konsolen-Ausgabe ist das Interface sowie die Werte zum Zeitpunkt
		// zeit = 0
		this.ausgeben(String.format("%-8s %-8s %-18s %-15s %-16s", "Zeit", "Hoehe", "Geschwind.[m/s]",
				"Schub[0..8]", "Position[l/r]") + System.lineSeparator() + this.toString());
		while (volocopter.getHoehe() > 0) {
			this.ausgeben(this.eineRundespielen());
		}
		return (System.lineSeparator() + this.bewerteLandung());

	}

	/**
	 * 
	 * Methode, in der das Spiel gespielt wird. Der User Input wird gelesen, die
	 * Physik und der Windeinfluss simuliert und berechnet.
	 * 
	 * @return einen String besteht aus Volocopter.toString() und der Angabe des
	 *         momentanen Winds
	 */
	private String eineRundespielen() {
		this.verarbeiteEingabe();
		this.simulierePhysik();
		this.berechneWindEinfluss();

		return this.toString();
	}

	/**
	 * Nimmt den User-Input der Konsole entgegen und verarbeitet die Eingabe. Sei 0
	 * <= Eingabe <= 8 wird der Schub gesetzt, bei 'l' oder 'r' die Position
	 * geaendert. Ein einfaches Return setzt den Schub auf 0
	 */
	private void verarbeiteEingabe() {
		String eingabe = konsolenInput.nextLine();
		volocopter.setSchub(0);
		volocopter.setBeschleunigung(0);
		String gueltigeEingabe = "012345678";

		if (eingabe.equals("r")) {
			volocopter.bewegeRechts();
		} else if (eingabe.equals("l")) {
			volocopter.bewegeLinks();
		} else if (eingabe.equals("")) {
			// Schub wurde schon auf 0 gesetzt
		} else if (gueltigeEingabe.contains(eingabe)) {
			volocopter.setSchub(Integer.parseInt(eingabe));
		} else {
			System.out.println("Volocopter ueberlastet! Schub wird auf 0 gesetzt!");
		}

	}

	/**
	 * In dieser Methode sind alle Berechnungen des Volocopters, die Hoehe,
	 * Geschwindigkeit und Fallbeschleunigung des Himmelkörpers betrifft. Zudem wird
	 * die Zeit jeweils um 1 erhoeht
	 */
	private void simulierePhysik() {

		final double FALLBESCHLEUNIGUNG_ERDE = -9.8;
		volocopter.setBeschleunigung(FALLBESCHLEUNIGUNG_ERDE + volocopter.getSchub() * 2);
		volocopter.setHoehe(
				volocopter.getHoehe() + volocopter.getGeschwindigkeit() + 0.5 * volocopter.getBeschleunigung());
		volocopter.setGeschwindigkeit(volocopter.getGeschwindigkeit() + volocopter.getBeschleunigung());
		this.zeit = this.zeit + 1;
	}

	/**
	 * Vermindert/erhoeht die Position des Volocopters je nach Windeinfluss
	 */
	private void berechneWindEinfluss() {
		wind.simuliereWind();
		switch (wind.getMomentanerWind()) {
		case LINKS: {
			volocopter.bewegeLinks();
			System.out.println("WIND LINKS!");
			break;
		}
		case RECHTS: {
			volocopter.bewegeRechts();
			System.out.println("WIND RECHTS");
			break;
		}
		default:
			// Keine Positionsaenderung
		}

	}

	/**
	 * Ist der Volocopter auf einer der Position, wird fuer diese ein 'x' gesetzt,
	 * sonst ein '.'
	 * 
	 * @return String, der das siebenstellige Sichtfeld darstellt.
	 */
	private String erstellePositionDarstellung() {
		java.util.Arrays.fill(this.positionDarstellung, '.');
		if (-3 <= volocopter.getPosition() && volocopter.getPosition() <= 3) {
			this.positionDarstellung[volocopter.getPosition() + 3] = 'x';
		}
		return new String(this.positionDarstellung);

	}

	/**
	 * Bewertet die Landung des Volocopters hinsichtlich seiner Geschwindigkeit und
	 * Position nach dem geforderten Schema.
	 * 
	 * @return Landebewertung als String
	 */
	private String bewerteLandung() {
		String geschwindigkeitsBewertung = "";
		String positionsBewertung = "";

		if (volocopter.getGeschwindigkeit() >= -3) {
			geschwindigkeitsBewertung = "Eine perfekte Landung!";
		} else if (volocopter.getGeschwindigkeit() >= -6 && volocopter.getGeschwindigkeit() < -3) {
			geschwindigkeitsBewertung = "...uff ziemlich harte Landung";
		} else {
			geschwindigkeitsBewertung = "Dieser Crash macht Daniel Düsentrieb alle Ehre!";
		}

		if (volocopter.getPosition() == 0) {
			positionsBewertung = "Punktlandung!";
		} else {
			positionsBewertung = "Position außerhalb Landeplatz: " + volocopter.getPosition();
		}

		return ("Landegeschwindigkeit: " + String.format("%.2f", volocopter.getGeschwindigkeit()) + " m/s"
				+ System.lineSeparator() + geschwindigkeitsBewertung + System.lineSeparator() + positionsBewertung);

	}

	/**
	 * Die Methode gibt den String auf der Konsole aus. Man koennte auch einfach
	 * print an den Stellen verwenden, aber so kann der Output gezielt geaendert
	 * werden, falls keine Konsole vorhanden ist
	 * 
	 * @param ausgabe: String, der ausgegeben werden soll
	 */
	public void ausgeben(String ausgabe) {
		System.out.print(ausgabe);
	}

	@Override
	public String toString() {
		return String.format("%-8d", this.zeit) + volocopter.toString()
				+ String.format("%-8s", this.erstellePositionDarstellung()) + String.format("%-10s", wind.toString());
	}

	public static void main(String[] args) {
		VolocopterSpiel spiel1 = new VolocopterSpiel();
		System.out.println(spiel1.einGesamtesSpiel());
	}
}