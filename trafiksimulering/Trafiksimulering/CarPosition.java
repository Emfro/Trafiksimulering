


// Håller i en bil och känner till sina "grannar". 
public class CarPosition{
	
	private Car currentCar = null; // null om ingen bil finns på positionen
	
	private Lane owner;
	
	private CarPosition forward;
	private int turn = 0;
	
	public CarPosition(Lane a_Owner){
		
		owner = a_Owner;
	}
	
	public boolean isEnd(CarPosition target){
		
		return owner.matchEnd(target);
	}
	
	public boolean moveForward(){
		
	 if(getForward() != null){
		 return (getForward().getCurrentCar() == null);
		 
	 }
	 else {
		 return false;
	 }
	
		// Flytta bilen fram till forward
	}
	
	
	

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public int getTurn() {
		return this.turn;
	}

	public Car getCurrentCar() {
		return currentCar;
	}

	public void setCurrentCar(Car currentCar) {
		this.currentCar = currentCar;
		
	}

	public CarPosition getForward() {
		if(forward != null){
		return forward;
		}
		else {
			return null;
		}
	}

	public void setForward(CarPosition forward) {
		this.forward = forward;
	}

	
		
	
	
	
}
