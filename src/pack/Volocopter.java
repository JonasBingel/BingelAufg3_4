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
	// private DecimalFormat df;
	private String windeinfluss;

	Volocopter() {
		zeit = 0;
		hoehe = 100;
		geschwindigkeit = 0;
		schub = 0;
		position = 0;
		windeinfluss = "";
		konsolenInput = new Scanner(System.in);
		// df = new DecimalFormat("0.00");

	}

	private void eineRundespielen() {
		
		
		// TODO EINGABE METHODE
		// Scanner soll immer offen sein
		String eingabe = konsolenInput.nextLine();
		this.schub = 0;
		this.beschleunigung = 0;
		String gueltigeEingabe = "012345678";

		if (eingabe.equals("r")) {
			bewegeRechts();
			//this.position = this.position + 1;
		} else if (eingabe.equals("l")) {
			bewegeLinks();
			//this.position = this.position - 1;
		} else if (eingabe.equals("")) {
			this.schub = 0;
		} else if (gueltigeEingabe.contains(eingabe)) {
			this.schub = Integer.parseInt(eingabe);
		} else {
			System.out.println("Volocopter überlastet! Schub wird auf 0 gesetzt!");
		}
		
		// TODOMETHODE PHYSIK SIMULATION
		final double FALLBESCHLEUNIGUNG_ERDE = -9.8;
		this.beschleunigung = FALLBESCHLEUNIGUNG_ERDE + this.schub * 2;
		this.hoehe = this.hoehe + this.geschwindigkeit + 0.5 * this.beschleunigung;
		this.geschwindigkeit = this.geschwindigkeit + this.beschleunigung;
		this.zeit = this.zeit + 1;
	}
	
	private void bewegeRechts() {
		this.position = this.position + 1;
	}
	
	private void bewegeLinks() {
		this.position = this.position - 1;
	}

	private void berechneWindEinfluss(Windart windRichtung) {
		this.windeinfluss = "Wind!";
		if (windRichtung.equals(Windart.RECHTS)) {
			bewegeRechts();
			//this.position = this.position + 1;			
		} else if (windRichtung.equals(Windart.LINKS)) {
			bewegeLinks();
			//this.position = this.position - 1;			
		} else {
			this.windeinfluss = "";
		}
	}

	private String erstellePositionDarstellung() {
		char[] positionsDarstellung = new char[7];
		java.util.Arrays.fill(positionsDarstellung, '.');
		if (-3 <= this.position && this.position <= 3) {
			positionsDarstellung[this.position + 3] = 'x';
		}
		//String positionAusgabe = new String(positionsDarstellung);
		//return positionAusgabe;
		return new String(positionsDarstellung);

	}

	private String bewerteLandung() {
		// TODO Eventuell direkt ausgeben
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

	@Override
	public String toString() {
		return String.format("%-8d %-8.2f %-18.2f %-16d  %-7s %-10s", this.zeit, this.hoehe, this.geschwindigkeit,
				this.schub, this.erstellePositionDarstellung(), this.windeinfluss);
	}

	public static void main(String[] args) {
		Volocopter volocopter1 = new Volocopter();
		Wind simulationsWind = new Wind();

		System.out.println(String.format("%-8s %-8s %-18s %-16s  %-18s", "Zeit", "Hoehe", "Geschwind.[m/s]",
				"Schub[0..8]", "Position[l/r]"));
		System.out.print(volocopter1.toString());
		while (volocopter1.hoehe > 0) {
			volocopter1.eineRundespielen();
			simulationsWind.simuliereWind();
			volocopter1.berechneWindEinfluss(simulationsWind.getMomentanerWind());
			System.out.print(volocopter1.toString());
		}
		System.out.println(System.lineSeparator() + volocopter1.bewerteLandung());

	}

}
