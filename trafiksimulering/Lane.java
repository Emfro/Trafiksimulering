public class Lane {

	
	protected CarPosition[] theLane;
	/**
	 * Lane() creates a Lane of a list of CarPositions
	 * @param n is a integer deciding how many CarPositions there will be in the new Lane
	 */
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
	
	public CarPosition getCarSpot(int pos){
		if(pos > getLength()-1) throw new RuntimeException("out of bound position");
		else 
			return theLane[pos];
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
	/**
	 * Step() moves the cars in the Lane if possible
	 */
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
	/**
	 * firstSpot()
	 * @return the first CarPosition in the Lane
	 */
	public CarPosition firstSpot() {
		CarPosition first = theLane[0];
		return first;
	}
	/**
	 * lastSpot()
	 * @return the last CarPosition in the Lane
	 */
	public CarPosition lastSpot() {
		int i = getLength();
		return theLane[i - 1];
	}
	/**
	 * getFirst()
	 * @return the car positioned at the first spot in the Lane and removes it from that Position
	 */
	public Car getFirst() {
		if(theLane[0].getCurrentCar() != null){
		Car First = theLane[0].getCurrentCar().cloneCar();
		theLane[0].setCurrentCar(null);
		return First;}
		else 
			return null;
		// Returnera och tag bort bilen som står först
	}
	/**
	 * firstCar()
	 * @return the car positioned at the first spot in the lane without removing it from that position
	 */
	public Car firstCar() {
		return theLane[0].getCurrentCar();
		// Returnera bilen som står först utan att ta bort den
	}
	/**
	 * lastFree()
	 * @return if the last spot is null
	 */
	public boolean lastFree() {
		int i = getLength() - 1;
		return (theLane[i].getCurrentCar() == null);
		// Returnera true om sista platsen ledig, annars false
	}
	/**
	 * putLast() puts a Car c in the last CarPosition in the Lane if possible
	 * @param c is the car which is put at the end of the Lane
	 */
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