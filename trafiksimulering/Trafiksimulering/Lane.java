public class Lane {

	public static class OverflowException extends RuntimeException {

		// Undantag som kastas när det inte gick att lägga
		// in en ny bil på vägen
	}

	protected CarPosition[] theLane;

	public Lane(int n) {
		theLane = new CarPosition[n];

		for (int i = 0; i <= n - 1; i++) {
			 theLane[i] = new CarPosition(this);
		}
		for (int x = n - 1; x > 0; x--) {
			theLane[x].setForward(theLane[x - 1]);
		}
		// Konstruerar ett Lane-objekt med plats för n fordon
		// Samt länker ihop varje CarPosition med forward för den framför
	}

	public boolean matchEnd(CarPosition target) {
		if (theLane[0] == target)
			return true;
		else
			return false;

	}

	public int getLength() {
		return theLane.length;
	}

//	public void setParallel(Lane sideLane) {
//		int i = 0;
//
//		while (i < sideLane.getLength() && i < theLane.length) {
//			theLane[i].setTurn(sideLane.theLane[i]);
//			i++;
//		}
//	}

	public void step() {

		int i = 1;
		// if(theLane[0].getCurrentCar() != null){
		// getFirst();
		// }
		while (i <= getLength()-1) {
			if (theLane[i].getCurrentCar() != null && theLane[i].moveForward()) {
				theLane[i].getCurrentCar().step();
				theLane[i].setCurrentCar(null);
			}
			i++;
		}

		// Stega fram alla fordon (utom det på plats 0) ett steg
		// (om det går). (Fordonet på plats 0 tas bort utifrån
		// mm h a metoden nedan.)
	}

	public CarPosition firstSpot() {
		CarPosition first = theLane[0];
		return first;
	}

	public CarPosition lastSpot() {
		int i = getLength();
		return theLane[i - 1];
	}

	public Car getFirst() {
		Car First = theLane[0].getCurrentCar();
		theLane[0].setCurrentCar(null);
		return First;
		// Returnera och tag bort bilen som står först
	}

	public Car firstCar() {
		return theLane[0].getCurrentCar();
		// Returnera bilen som står först utan att ta bort den
	}

	public boolean lastFree() {
		int i = getLength() - 1;
		return (theLane[i].getCurrentCar() == null);
		// Returnera true om sista platsen ledig, annars false
	}

	public void putLast(Car c) {
		if (!lastFree()) {
				throw new RuntimeException("There is an ovwerflow of cars in the lanes! \n Please retart the program and choose new settings.");
			} else {
			int i = getLength() - 1;
			theLane[i].setCurrentCar(c);
			c.setCurrentPosition(theLane[i]);

		}
		// Ställ en bil på sista platsen på vägen ^throws OverflowException
		// (om det går).
	}

	public String toString() {
		int i = 0;
		String s = "<-";
		while (i < getLength()) {
			if (theLane[i].getCurrentCar() != null) {
				s += theLane[i].getCurrentCar().toString();
			} else {
				s += "---";

			}
			i++;
		}
		return s;
		// ...
	}

}