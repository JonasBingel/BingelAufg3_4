package pack;
// TODO Soll immer ein neues Wind Objekt erzeugt werden? Oder soll man die Methode Wind-Simulieren jedes Mal erneut aufrufen.
// Jede Runde wird ein Char-Array initialisiert lieber ein char-Array Attribut? 
import java.util.*;

/**
 * @author BingelJ
 * @version 2.0, 25.11.2018 Die Volocoper Aufgabe erfuellt die Anforderungen der
 *          4. Aufgabe "Volocoper steuern" vom Modul Prog1 AWIS18 WS18/19 an der
 *          HS Mainz.
 *
 */
public class Volocopter {
	private int zeit;
	private double hoehe;
	private double geschwindigkeit;

	private int schub;
	private double beschleunigung;
	private int position;
	private Scanner konsolenInput;
	private String windeinfluss; // kann ein Wind Objekt sein

	/**
	 * Konstruktor der Volocopter Klasse, der alle Attribute initialisiert und
	 * Scanner bereitstellt
	 */
	Volocopter() {
		zeit = 0;
		hoehe = 100;
		geschwindigkeit = 0;
		schub = 0;
		position = 0;
		windeinfluss = "";
		konsolenInput = new Scanner(System.in);

	}

	/**
	 * Methode, in der das Spiel gespielt wird. Der User Input wird gelesen und die
	 * physikalischen Eigenschaften werden simuliert, d.h. Fallbeschleunigung des
	 * Himmelkörpers, Geschwindigkeit, Beschleunigung und Hoehe.
	 */
	private void eineRundespielen() {

		this.verarbeiteEingabe();
		this.simulierePhysik();
	}

	/**
	 * In dieser Methode sind alle Berechnungen des Volocopters, die Hoehe,
	 * Geschwindigkeit und Beschleunigung betrifft. Zudem wird die Zeit jeweils um 1
	 * erhoeht
	 */
	private void simulierePhysik() {

		final double FALLBESCHLEUNIGUNG_ERDE = -9.8;
		this.beschleunigung = FALLBESCHLEUNIGUNG_ERDE + this.schub * 2;
		this.hoehe = this.hoehe + this.geschwindigkeit + 0.5 * this.beschleunigung;
		this.geschwindigkeit = this.geschwindigkeit + this.beschleunigung;
		this.zeit = this.zeit + 1;
	}

	/**
	 * Nimmt den User-Input der Konsole entgegen und verarbeitet die Eingabe. Sei 0
	 * <= Eingabe <= 8 wird der Schub gesetzt, bei 'l' oder 'r' die Position
	 * geaendert. Eine Leertaste setzt den Schub auf 0
	 */
	private void verarbeiteEingabe() {
		String eingabe = konsolenInput.nextLine();
		this.schub = 0;
		this.beschleunigung = 0;
		String gueltigeEingabe = "012345678";

		if (eingabe.equals("r")) {
			this.bewegeRechts();
		} else if (eingabe.equals("l")) {
			this.bewegeLinks();
		} else if (eingabe.equals("")) {
			this.schub = 0;
		} else if (gueltigeEingabe.contains(eingabe)) {
			this.schub = Integer.parseInt(eingabe);
		} else {
			System.out.println("Volocopter ueberlastet! Schub wird auf 0 gesetzt!");
		}

	}

	/**
	 * Position des Volocopters wird um 1 erhoeht, da er sich wegen Benutzereingabe
	 * oder Windeinfluss nach rechts bewegen soll.
	 */
	private void bewegeRechts() {
		this.position = this.position + 1;
	}

	/**
	 * Position des Volocopters wird um 1 vermindert, da er sich wegen
	 * Benutzereingabe oder Windfluss nach links bewegen soll.
	 */
	private void bewegeLinks() {
		this.position = this.position - 1;
	}

	/**
	 * @param windRichtung
	 */
	private void berechneWindEinfluss(Windart windRichtung) {
		this.windeinfluss = "Wind!";
		if (windRichtung.equals(Windart.RECHTS)) {
			bewegeRechts();
		} else if (windRichtung.equals(Windart.LINKS)) {
			bewegeLinks();
		} else {
			this.windeinfluss = "";
		}
	}

	/**
	 * @return String, der das siebenstellige Sichtfeld darstellt. Ist der
	 *         Volocopter auf einer der Position, wird fuer diese ein 'x'
	 *         ausgegeben, sonst ein '.'
	 */
	private String erstellePositionDarstellung() {
		// Jede Rund wird ein neues charArray initialisiert. Evtl. lieber als Attribut
		char[] positionsDarstellung = new char[7];
		java.util.Arrays.fill(positionsDarstellung, '.');
		if (-3 <= this.position && this.position <= 3) {
			positionsDarstellung[this.position + 3] = 'x';
		}
		return new String(positionsDarstellung);

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

		if (this.geschwindigkeit >= -3) {
			geschwindigkeitsBewertung = "Eine perfekte Landung!";
		} else if (this.geschwindigkeit >= -6 && this.geschwindigkeit < -3) {
			geschwindigkeitsBewertung = "...uff ziemlich harte Landung";
		} else {
			geschwindigkeitsBewertung = "Dieser Crash macht Daniel Düsentrieb alle Ehre!";
		}

		if (this.position == 0) {
			positionsBewertung = "Punktlandung!";
		} else {
			positionsBewertung = "Position außerhalb Landeplatz: " + this.position;
		}

		return ("Landegeschwindigkeit: " + String.format("%.2f", this.geschwindigkeit) + " m/s" + System.lineSeparator()
				+ geschwindigkeitsBewertung + System.lineSeparator() + positionsBewertung);

	}

	/**
	 * Diese Methode simuliert das gesamte Spiel und ruft dafuer, solange die Hoehe
	 * > 0 ist, eineRundespielen auf und simuliert den Wind.
	 * 
	 * @return Landebewertung, die von this.bewerteLandung() bereitgestellt wird.
	 */
	public String einGesamtesSpiel() {
		Wind simulationsWind = new Wind();

		System.out.print(String.format("%-8s %-8s %-18s %-16s  %-18s", "Zeit", "Hoehe", "Geschwind.[m/s]",
				"Schub[0..8]", "Position[l/r]") + System.lineSeparator() + this.toString());
		while (this.hoehe > 0) {
			this.eineRundespielen();
			simulationsWind.simuliereWind();
			this.berechneWindEinfluss(simulationsWind.getMomentanerWind());
			System.out.print(this.toString());
		}
		return (System.lineSeparator() + this.bewerteLandung());

	}

	@Override
	public String toString() {
		return String.format("%-8d %-8.2f %-18.2f %-16d  %-7s %-10s", this.zeit, this.hoehe, this.geschwindigkeit,
				this.schub, this.erstellePositionDarstellung(), this.windeinfluss);
	}

	/**
	 * Main-Methode der Volocopter-Klasse. Ein neues Volocopter-Objekt wird erstellt
	 * und einGesamtesSpiel() wird aufgerufen und das Ergebnis davon ausgegeben.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Volocopter volocopter1 = new Volocopter();
		System.out.println(volocopter1.einGesamtesSpiel());

	}

}
