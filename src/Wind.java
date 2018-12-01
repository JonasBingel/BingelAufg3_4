package pack;

/**
 * @author Jonas Bingel Wind-Klasse fuer die VolocopterSpiel Klasse. Diese
 *         Klasse simuliert Wind
 */
public class Wind {
	private Windart momentanerWind;

	/**
	 * Konstruktor der Wind-Klasse
	 */
	public Wind() {
		momentanerWind = Windart.KEIN_WIND;
	}

	/**
	 * Simuliert den Wind, sodass mit jeweils 20% Wahrscheinlichkeit nach links oder
	 * rechts weht. Die Windart ist durch ein Enum auf drei Werte beschraenkt
	 */
	public void simuliereWind() {
		double zufallsZahl = Math.random();
		if (0 <= zufallsZahl && zufallsZahl <= 0.2) {
			this.momentanerWind = Windart.LINKS;
		} else if (0.8 <= zufallsZahl && zufallsZahl <= 1.0) {
			this.momentanerWind = Windart.RECHTS;
		} else {
			this.momentanerWind = Windart.KEIN_WIND;
		}
	}

	public Windart getMomentanerWind() {
		return momentanerWind;
	}

	public String toString() {
		if (momentanerWind.equals(Windart.KEIN_WIND))
			return "";
		else
			return "Wind!";
	}
}
