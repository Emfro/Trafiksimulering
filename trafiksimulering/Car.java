import java.io.IOException;



public class Car {

    private int bornTime;
    private CarPosition destination; 

    private CarPosition currentPosition;
    
   
    public void step(){
     bornTime++;
     if(currentPosition.moveForward()){
    	 setCurrentPosition(currentPosition.getForward());
    	 
     }
      	// Uppdatera bilen ett tidssteg
    }
    public Car(CarPosition dest, CarPosition startingPos){
    	bornTime = 0;
    	destination = dest;
    	currentPosition = startingPos; 
    			
    }
    public CarPosition getDestination() {
    	return destination;
    }
    public CarPosition getCurrent_position(){
    	return currentPosition;
    }
    public void setDestination(CarPosition dest){
    	this.destination = dest;
    }
    public void setCurrentPosition(CarPosition pos){
    	this.currentPosition = pos;
    }
    // konstruktor och get- oct set-metoder
    //...

    public String toString(){
    	
    	return "Car(Born Time: " + bornTime + ")\nCar(Destination: " + destination + " \nCar(current position: " + currentPosition + ")\n";
    	}

}