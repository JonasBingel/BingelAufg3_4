package pack;
// TODO eventuell toString Methode mit if (momentanerWind.equals(Windart.KEIN_WIND)) return "" else return "Wind!";

/**
 * @author Jonas Bingel Wind-Klasse fuer die Volocopter Klasse, in der Wind
 *         simuliert werden kann.
 *
 */
public class Wind {
	private Windart momentanerWind;

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
}
