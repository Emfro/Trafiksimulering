import java.io.IOException;
import java.util.Scanner;  


public class Car {
    private int bornTime;
    private int destination; 
    // 0 > Rakt fram  0 =<  Sväng
    private CarPosition currentPosition;
    
   
    public void step(){
    	if(currentPosition != null && currentPosition.moveForward()){
    		
    	 setCurrentPosition(currentPosition.getForward());
    	 currentPosition.setCurrentCar(this);
     }
      	// Uppdatera bilen ett tidssteg
    }
    public Car(int bornTime, int dest, CarPosition startingPos){
    	this.bornTime = bornTime;
    	destination = dest;
    	currentPosition = startingPos; 
    			
    }
    public int getBornTime(){
    	return bornTime;
    	
    }
    public int getDestination() {
    	return this.destination;
    }
    public CarPosition getCurrent_position(){
    	return this.currentPosition;
    }
    public void setDestination(int dest){
    	this.destination = dest;
    }
    public void setCurrentPosition(CarPosition pos){
    	this.currentPosition = pos;
    }
    // konstruktor och get- oct set-metoder
    //...

    public String toString(){
    	if(this.getDestination() > 0) {
    		return "[<]";
    	}else{
    		return   "[v]";
    	}
    }
   // public static void main(String args[]){
    //	System.out.println("Vi gör en bil");
    //	Car bob = new Car(null, null);
    //	System.out.println(bob);
   // }
}
