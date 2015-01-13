public class Light {
    private int period;
    private int time;  // Intern klocka: 0, 1, ... period-1, 0, 1 ...
    private int green; // Signalen grön när time<green 

    /**
     * Creates a new Light
     * @param period is a integer which tells in which range the light works within
     * @param green is a integer deciding how long green light is showing must be within the range of the period
     */
    public Light(int period, int green) {
    	this.period = period;
    	this.green = green;
    	this.time = 0;
    }
    /**
     * Step() updates the time one step
     */
    public void step() { 
    	if( this.time < this.period ){
    		this.time++;
    	}else {
    		this.time = 0;
    	}
       // Stegar fram klocka ett steg
    }
    /**
     * boolean isGreen()
     * @return if green is larger than time
     */
    public boolean isGreen()   {
    	return (time <= green);
    	
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