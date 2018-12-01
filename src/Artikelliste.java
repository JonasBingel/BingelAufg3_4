package pack;

/**
 * @author Jonas Bingel
 * @version 3.0, 25.11.2018 Die Artikelliste Klasse erfuellt die Anforderungen
 *          der 3. Aufgabe "Suche in Artikelliste" vom Modul Prog1 AWIS18
 *          WS18/19 an der HS Mainz. Eine Artikelliste mit MAX Artikel-Objekten
 *          wird initialisiert und einmal komplett mit linearer und binaerer -
 *          selbstgeschrieben und aus der Bibliothek - durchsucht.
 *
 */
public class Artikelliste {
	private Artikel[] artikelListe;

	/**
	 * Konstruktor der Artikellisten-Klasse. Es werden insgesamt MAX Artikel Objekte
	 * erstellt und in einem Artikelarray gespeichert. Dabei hat der Artikel mit
	 * Index 0 die ArtNr MAX
	 * 
	 * @param MAX Integerwert, der die Anzahl an Artikeln bestimmt
	 */
	Artikelliste(int MAX) {
		artikelListe = new Artikel[MAX];
		for (int i = 0; i <= MAX - 1; i++) {
			artikelListe[i] = new Artikel((MAX - i), "Artikel" + (MAX - i));
		}
	}

	/**
	 * Sortiert die Artikelliste mittels Bubble-Sort unter der Verwendung eines
	 * Hilfsartikels (Dreiecks-Tausch)
	 * siehe: de.wikipedia.org/Bubblesort
	 * 
	 */
	private void sortiereNachArtikelNr() {
		Artikel hilfsArtikel;
		int artikelListenLaenge = artikelListe.length;
		do {
			int neueLaenge = 1;
			/**
			 * Prueft, ob die ArtikelNr eines Artikel groesser ist, als die des darauf
			 * folgenden. Falls true, tauschen beiden Artikel ihre Indexpositionen*
			 */
			for (int i = 0; i < artikelListenLaenge - 1; i++) {
				if (artikelListe[i].getArtNr() > (artikelListe[i + 1].getArtNr())) {

					// Implementierung eines Dreiecktauschs
					hilfsArtikel = artikelListe[i + 1];
					artikelListe[i + 1] = artikelListe[i];
					artikelListe[i] = hilfsArtikel;
					neueLaenge = i + 1;
				}
			} /**
				 * Die Artikellistenlänge vermindert sich jedes Mal um 1, da der groesste
				 * Artikel bereits an der letzten Position angekommen ist.
				 */
			artikelListenLaenge = neueLaenge;

		} while (artikelListenLaenge > 1);
	}

	/**
	 * Implementierung einer linearen Suche, d.h. die Liste wird von Anfang bis Ende
	 * iteriert und dabei jede ArtNr mit dem Suchwert verglichen
	 * 
	 * @param Suchwert ist der int Wert, der innerhalb der Liste gesucht werden
	 *                 soll.
	 * @return Indexposition des gesuchten Werts innerhalb der Liste, -1, wenn der
	 *         Wert nicht in der Liste enthalten ist.
	 */
	private int lineareSuche(int Suchwert) {
		for (int i = 0; i < artikelListe.length; i++) {
			if (artikelListe[i].getArtNr() == Suchwert) {
				return i;
			}
		}
		return (-1);
	}

	/**
	 * Implementierung einer objektorientierten Version der binaeren Suche von
	 * Wikipedia. siehe: de.wikipedia.org/wiki/Binäre_Suche
	 * 
	 * @param Suchwert ist der int Wert, der innerhalb der Liste gesucht werden
	 *                 soll.
	 * @return Indexposition des gesuchten Werts innerhalb der Liste, -1, wenn der
	 *         Wert nicht in der Liste enthalten ist.
	 * 
	 */
	private int binaereSuche(int Suchwert) {
		int linkesEnde = 0;
		int rechtesEnde = artikelListe.length - 1;

		while (linkesEnde < rechtesEnde) {
			int mitteSuchbereich = linkesEnde + ((rechtesEnde - linkesEnde) / 2);

			if (artikelListe[mitteSuchbereich].getArtNr() == Suchwert) {
				return mitteSuchbereich;
			} else if (artikelListe[mitteSuchbereich].getArtNr() > Suchwert) {
				rechtesEnde = mitteSuchbereich - 1;
			} else if (artikelListe[mitteSuchbereich].getArtNr() < Suchwert) {
				linkesEnde = mitteSuchbereich + 1;
			}
		}
		return (-1);
	}

	/**
	 * Um die binaere Suche der Bibliothek zu verwenden, wird ein int Array
	 * benoetigt. Diese Methode extrahiet alle ArtNr aus der Artikelliste und
	 * erzeugt das int Array.
	 * 
	 * @return Integer-Array das alle ArtNr enthält
	 */
	private int[] extrahiereArtNrListe() {
		int[] artikelListeBibliotheksFunktion = new int[artikelListe.length];
		for (int i = 0; i < artikelListe.length; i++) {
			artikelListeBibliotheksFunktion[i] = artikelListe[i].getArtNr();
		}
		return artikelListeBibliotheksFunktion;
	}

	/**
	 * Gibt alle Artikel und deren Bezeichnung in der Reihenfolge aus, in der sie
	 * derzeit in der Artikelliste gespeichert sind.
	 */
	private void ausgeben() {
		System.out.println("ArtNr \tArtikelbezeichnung");
		for (int i = 0; i < artikelListe.length; i++) {
			System.out.println(artikelListe[i].toString());
		}
	}

	/**
	 * Misst die Suchzeit, die die angegebene Suchart benoetigt, um einmal alle
	 * Elemente in der Artikelliste zu suchen.
	 * 
	 * @param suchart Angabe der Suchart, die verwendet werden soll. Auswahl anhand
	 *                des Enums Sucharten
	 * @return Suchzeit, die eine Suchmethode benoetigt, um einmal alle Elemente der
	 *         Liste zu suchen.
	 */
	private long messeSuchzeit(Sucharten suchart) {
		long startZeit = 0;
		long endeZeit = 0;

		switch (suchart) {
		case LINEAR: {

			startZeit = System.currentTimeMillis();
			for (int i = 0; i < artikelListe.length; i++) {
				this.lineareSuche(i);
			}
			endeZeit = System.currentTimeMillis();
			break;
		}
		case BINAER: {
			startZeit = System.currentTimeMillis();
			for (int i = 0; i < artikelListe.length; i++) {
				this.binaereSuche(i);
			}
			endeZeit = System.currentTimeMillis();
			break;
		}
		case BINAER_BIB: {

			int[] artikelListeBibliotheksFunktion = this.extrahiereArtNrListe();
			startZeit = System.currentTimeMillis();

			for (int i = 0; i < artikelListe.length; i++) {
				java.util.Arrays.binarySearch(artikelListeBibliotheksFunktion, i);
			}
			endeZeit = System.currentTimeMillis();
			break;
		}
		}
		return endeZeit - startZeit;

	}

	/**
	 * Mittels einer do while-Schleife, in der die Artikelmenge in jedem Durchlauf
	 * um 5000 erhoeht wird, soll die minimale Artikelmenge gefunden werden, bei der
	 * die Suchzeit der binaereSuche() > 1ms ist.
	 * 
	 * @return Einen String, der die benoetigte Suchzeit > 1, sowie die dafuer
	 *         minimal notwendige Anzahl an Elementen enthaelt
	 */
	private String ermittleMinimaleArtikelmenge() {
		long binaereSuchzeitVariabel;
		int minimaleAnzahl;
		int durchlauf = 0;
		do {
			minimaleAnzahl = 5000 + durchlauf * 5000;

			Artikelliste artikelListeBerechnung = new Artikelliste(minimaleAnzahl);
			artikelListeBerechnung.sortiereNachArtikelNr();
			binaereSuchzeitVariabel = artikelListeBerechnung.messeSuchzeit(Sucharten.BINAER);

			durchlauf = durchlauf + 1;
		} while (binaereSuchzeitVariabel < 1);
		return ("binaereSuche dauert länger als 0 ms (= " + String.format("%03d", binaereSuchzeitVariabel) + ") ms bei "
				+ minimaleAnzahl + " Elementen");

	}

	/**
	 * Stellt eine Methode bereit, um die gemessenen Suchzeiten der einzelnen
	 * Sucharten auszugeben.
	 * 
	 * @param lineareSuchzeit   gemessene Suchzeit, wenn die lineare Suche verwendet
	 *                          wird
	 * @param binaereSuchzeit   gemessene Suchzeit, wenn die binaere Suche verwendet
	 *                          wird
	 * @param binaerBibSuchzeit gemessene Suchzeit, wenn die binaere Suche der
	 *                          Java-Bibliothek verwendet wird.
	 */
	public void SuchzeitAusgabe(long lineareSuchzeit, long binaereSuchzeit, long binaerBibSuchzeit) {

		System.out.println("Algorithmus lineareSuche " + String.format("%03d", lineareSuchzeit) + " ms"
				+ System.lineSeparator() + "Algorithmus binaereSuche " + String.format("%03d", binaereSuchzeit) + " ms"
				+ System.lineSeparator() + "Algorithmus java.util.Arrays.binarySearch "
				+ String.format("%03d", binaerBibSuchzeit) + " ms");
	}

	/**
	 * In der Main-Methode wird die Artikelanzahl festgelegt und die Methoden zur
	 * Suchzeiermittlung und Ausgabe aufgerufen.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Festlegen der Anzahl an Artikeln in der Artikelliste
		final int MAX = 75000;

		Artikelliste artikelListe1 = new Artikelliste(MAX);

		artikelListe1.sortiereNachArtikelNr();
		// artikelListe1.ausgeben();
		artikelListe1.SuchzeitAusgabe(artikelListe1.messeSuchzeit(Sucharten.LINEAR),
				artikelListe1.messeSuchzeit(Sucharten.BINAER), artikelListe1.messeSuchzeit(Sucharten.BINAER_BIB));
		System.out.println(artikelListe1.ermittleMinimaleArtikelmenge());
	}
}
