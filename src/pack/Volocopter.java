package pack;

import java.text.DecimalFormat;
import java.util.*;

public class Volocopter {
	private int zeit;
	private double hoehe;
	private double geschwindigkeit;

	private int schub;
	private double beschleunigung;
	private int position;
	private Scanner konsolenInput;
	private DecimalFormat df;

	Volocopter() {
		zeit = 0;
		hoehe = 100;
		geschwindigkeit = 0;
		schub = 0;
		position = 0;
		konsolenInput = new Scanner(System.in);
		df = new DecimalFormat("0.00");

	}

	private void eineRundespielen() {
		// scanner ist implementiert und bleibt dauerhaft offen
		// scanner in separater Methode, die Input überprüft oder doch lieber hier erst
		// prüfen und dem nach handeln
		final double erdbeschleunigung = -9.8;
		String eingabe = konsolenInput.nextLine();

		this.schub = 0;
		this.beschleunigung = 0;
		String gueltigeEingabe = "0 1 2 3 4 5 6 7 8";

		if (eingabe.equals("r")) {
			this.position = this.position + 1;
		} else if (eingabe.equals("l")) {
			this.position = this.position - 1;
		} else if (eingabe.equals("")) {
			this.schub = 0;
		} else if (gueltigeEingabe.contains(eingabe)) {
			this.schub = Integer.parseInt(eingabe);
		} else {
			System.out.println("Volocopter überlastet! Schub wird auf 0 gesetzt!");
		}

		this.beschleunigung = erdbeschleunigung + this.schub * 2;
		// TODO Berechne Hoehe
		this.hoehe = this.hoehe + this.geschwindigkeit + 0.5 * this.beschleunigung;
		// TODO Berechne Geschwindigkeit
		// Die Geschwindigkeit berechnet sich erst nach der neuen Beschleunigung d.h.
		// erst wird die Hoehe berechnet
		this.geschwindigkeit = this.geschwindigkeit + this.beschleunigung;

		
		//TODO Aufruf der Wind-Simulation
		// Ändern der Position nach Windrichtung default: KEIN_WIND
		// RETURNED STRING MIT WINDENFLUSS d.h. "" oder "WIND!"
		
		this.zeit = this.zeit + 1;
	}

	private String erstellePositionDarstellung() {
		// TODO Wind! Lässt sich vermutlich hier am besten einbauen
		// Sonst in der ToSTring-Methode -> Wert auf 8 vermindern
		char[] positionsDarstellung = new char[7];

		java.util.Arrays.fill(positionsDarstellung, '.');

		if (-2 <= this.position && this.position <= 2) {
			positionsDarstellung[this.position + 3] = 'x';
		}
		String positionAusgabe = new String(positionsDarstellung);
		return positionAusgabe;

	}

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

	private void ausgeben() {
		// toString kann auch implementiert werden
		// ausgeben() eventuell lieber für Ausgabe von Positin usw. verwenden

	}

	@Override
	public String toString() {
		return String.format("%-8d %-8.2f %-18.2f %-16d  %-7s %-10s", this.zeit, this.hoehe, this.geschwindigkeit,
				this.schub, this.erstellePositionDarstellung(), "Wind!");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// while hoehe > 0 eineRundespielen() aufrufen
		// volocopter wird erzeugt
		// TODO Format-String als Variable deklarien. df kann abgelöst werden!

		Volocopter volocopter1 = new Volocopter();
		System.out.println(String.format("%-8s %-8s %-18s %-16s  %-18s", "Zeit", "Hoehe", "Geschwind.[m/s]",
				"Schub[0..8]", "Position[l/r]"));
		System.out.print(volocopter1.toString());
		while (volocopter1.hoehe > 0) {
			volocopter1.eineRundespielen();
			System.out.print(volocopter1.toString());
		}
		System.out.println(System.lineSeparator() + volocopter1.bewerteLandung());

	}

}
