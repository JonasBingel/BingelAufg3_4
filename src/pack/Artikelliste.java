package pack;

// TODO Fragen klären: Müssen/Sollen Getter/Setter verwendet werden? Sollen die Berechnungsschleifen ausgelagert werden
// TODO Bessere Variablennamen wählen
public class Artikelliste {
	private final int MAX;
	private Artikel[] artikelListe;

	Artikelliste(int MAX) {
		this.MAX = MAX;
		artikelListe = new Artikel[MAX];
		for (int i = 0; i <= MAX - 1; i++) {
			artikelListe[i] = new Artikel((MAX - i), "Artikel" + (MAX - i));
		}
	}

	/**
	 * Sortiert die Artikelliste mittels Bubble-Sort unter der Verwendung eines
	 * Hilfsartikels.
	 * 
	 */
	private void sortiereNachArtikelNr() {
		Artikel hilfsArtikel;
		int artikelListenLaenge = artikelListe.length;
		do {
			int neueLaenge = 1;
			/**
			 * Prueft, ob die ArtikelNr eines Artikel groesser ist, als die des darauf
			 * folgenden. Falls ja, tauschen die beiden Artikel ihre Indexpositionen*
			 */
			for (int i = 0; i < artikelListenLaenge - 1; i++) {
				// TODO entscheiden, ob man mit Gettern arbeitet und die Methode
				// istArtikelNrGroesser dafür entfernt
				// if (artikelListe[i].artNr > artikelListe[i + 1].artNr) {
				if (artikelListe[i].istArtikelNrGroesser(artikelListe[i + 1])) {

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

	private int lineareSuche(int Suchwert) {
		for (int i = 0; i < artikelListe.length; i++) {
			if (artikelListe[i].getArtNr() == Suchwert) {
				return i;
			}
		}
		return (-1);
	}

	// TODO Verbessern!
	private int binaereSuche(int Suchwert) {
		int linkesEnde = 0;
		int rechtesEnde = artikelListe.length - 1;
		Artikel hilfsartikel = new Artikel(Suchwert, "hilfsartikel");

		while (linkesEnde < rechtesEnde) {
			int mitteSuchbereich = linkesEnde + ((rechtesEnde - linkesEnde) / 2);

			if (artikelListe[mitteSuchbereich].istArtikelNrgleich(hilfsartikel)) {
				return mitteSuchbereich;
			} else if (artikelListe[mitteSuchbereich].istArtikelNrGroesser(hilfsartikel)) {
				rechtesEnde = mitteSuchbereich - 1;
			} else if (artikelListe[mitteSuchbereich].istArtikelNrkleiner(hilfsartikel)) {
				linkesEnde = mitteSuchbereich + 1;
			}
		}
		return (-1);
	}

	private int binaereSuched(int Suchwert) {
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

	private void ausgeben() {
		System.out.println("ArtNr \tArtikelbezeichnung");
		for (int i = 0; i < MAX; i++) {
			System.out.println(artikelListe[i].toString());
		}
	}

	private int[] extrahiereArtNrListe() {
		int[] artikelListeBibliotheksFunktion = new int[MAX];
		for (int i = 0; i < MAX; i++) {
			artikelListeBibliotheksFunktion[i] = artikelListe[i].getArtNr();
		}
		return artikelListeBibliotheksFunktion;
	}

	public static void main(String[] args) {

		/** Festlegen der Artikellistengroesse */
		final int MAX = 100;

		Artikelliste artikelListe1 = new Artikelliste(MAX);
		artikelListe1.sortiereNachArtikelNr();

		// LINEARE SUCHE
		long start = System.currentTimeMillis();
		for (int i = 0; i < MAX; i++) {
			artikelListe1.lineareSuche(i);
		}
		long ende = System.currentTimeMillis();
		long lineareSuchzeit = ende - start;

		// BINAERE SUCHE
		long startBinaer = System.currentTimeMillis();
		for (int i = 0; i < MAX; i++) {
			artikelListe1.binaereSuched(i);
		}
		long endeBinaer = System.currentTimeMillis();
		long binaereSuchzeit = endeBinaer - startBinaer;

		// BINAERE SUCHE BIBLIOTHEKSFUNKTION
		int[] artikelListeBibliotheksFunktion = artikelListe1.extrahiereArtNrListe();

		long startBibliothek = System.currentTimeMillis();
		for (int i = 0; i < MAX; i++) {
			java.util.Arrays.binarySearch(artikelListeBibliotheksFunktion, i);
		}
		long endeBibliothek = System.currentTimeMillis();
		long bibliotheksSuchzeit = endeBibliothek - startBibliothek;

		// BINAERESUCHE LAENGER
		long binaereSuchzeitVariabel;
		int minimaleAnzahl;
		int durchlauf = 0;
		do {
			minimaleAnzahl = 5000 + durchlauf * 5000;

			Artikelliste artikelListe2 = new Artikelliste(minimaleAnzahl);
			artikelListe2.sortiereNachArtikelNr();

			long startBineareSuchzeit = System.currentTimeMillis();
			for (int i = 0; i < minimaleAnzahl; i++) {
				artikelListe2.binaereSuche(i);
			}
			long endeBineareSuchzeit = System.currentTimeMillis();
			binaereSuchzeitVariabel = endeBineareSuchzeit - startBineareSuchzeit;
			System.out.println(binaereSuchzeitVariabel + "Durchlauf Nr. " + durchlauf + " " + minimaleAnzahl);

			durchlauf = durchlauf + 1;
		} while (binaereSuchzeitVariabel < 1);

		System.out.println("Algorithmus lineareSuche " + String.format("%03d", lineareSuchzeit) + " ms"
				+ System.lineSeparator() + "Algorithmus binaereSuche " + String.format("%03d", binaereSuchzeit) + " ms"
				+ System.lineSeparator() + "Algorithmus java.util.Arrays.binarySearch "
				+ String.format("%03d", bibliotheksSuchzeit) + " ms" + System.lineSeparator()
				+ "binaereSuche dauert länger als 0 ms (= " + String.format("%03d", binaereSuchzeitVariabel)
				+ ") ms bei " + minimaleAnzahl + " Elementen");
	}
}
