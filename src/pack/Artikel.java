package pack;

public class Artikel {

	private int artNr;
	private String bezeichnung;

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
