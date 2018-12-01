package pack;

/**
 * @author Jonas Bingel
 * @version 3.0, 25.11.2018 Die Artikel-Klasse stellt die Artikel - mit einer
 *          ArtikelNr und Bezeichnung fuer die Klasse Artikelliste bereit.
 *
 */
public class Artikel {

	private int artNr;
	private String bezeichnung;

	/**
	 * Konstruktor der Artikel-Klasse. Die Artikel werden immer mit einer ArtikelNr
	 * und einer Beschreibung initialisiert.
	 * 
	 * @param artNr       Eine Integerzahl
	 * @param bezeichnung Bezeichnung fuer den Artikel
	 */
	Artikel(int artNr, String bezeichnung) {
		this.artNr = artNr;
		this.bezeichnung = bezeichnung;
	}

	@Override
	public String toString() {
		return artNr + "\t" + bezeichnung;
	}

	public int getArtNr() {
		return this.artNr;
	}

	public void setArtNr(int artNr) {
		this.artNr = artNr;
	}

}
