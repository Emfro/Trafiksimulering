public class Light {
    private int period;
    private int time;  // Intern klocka: 0, 1, ... period-1, 0, 1 ...
    private int green; // Signalen grön när time<green 

    public Light(int period, int green) {
    	this.period = period;
    	this.green = green;
    	this.time = 0;
    }

    public void step() { 
    	if( this.time < this.period ){
    		this.time++;
    	}else {
    		this.time = 0;
    	}
       // Stegar fram klocka ett steg
    }

    public boolean isGreen()   {
    	return (time < green);
    	
    	// Returnerar true om time<green, annars false
    }

    public String  toString()  {
    	if(isGreen()){
    	return "Green: " + (green-time) + "\n";
    	}
    	else{
    		return "Red:   " + (period-time) + "\n";
    	}
    		
    	}
    	
	
}