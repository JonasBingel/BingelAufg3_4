package pack;

public class Wind {
	private Windart momentanerWind;

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
