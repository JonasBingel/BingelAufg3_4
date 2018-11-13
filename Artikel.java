package pack;

public class Artikel {

	private int artNr;
	private String bezeichnung;

	Artikel(int artNr, String bezeichnung) {
		this.artNr = artNr;
		this.bezeichnung = bezeichnung;
	} 

	public boolean istArtikelNrGroesser(Artikel other) {
		return this.artNr > other.artNr;
	}
	
	public boolean istArtikelNrkleiner(Artikel other) {
		return this.artNr < other.artNr;
	}
	
	public boolean istArtikelNrgleich(Artikel other) {
		return this.artNr == other.artNr;
	}

	@Override
	public String toString() {
		return artNr + "\t" + bezeichnung;
	}
	
	// TODO Ist nichtmal wirklich notwendig?
	public int getArtNr() {
		return this.artNr;
	}
	
	public void setArtNr(int artNr) {
		this.artNr = artNr;
	}

}
