import java.util.ArrayList;
import java.util.Random;

public class TrafficSystem {
    // Definierar de vägar och signaler som ingår i det 
	private int TotalCarTime = 0;
	private int AvarageCarTime = 0; // system som skall studeras.
	private int CarsF = 0;
	private int CarsT = 0;
	private int lowTime;
	private int longTime = 0;
   // Samlar statistik
    
    // Attribut som beskriver beståndsdelarna i systemet
    private Lane  r0;
    private Lane  r1;
    private Lane  r2;
    private Light s1;
    private Light s2;
    private int Ankomstintesitet;
    private int NextCar = 0;
    // Diverse attribut för simuleringsparametrar (ankomstintensiteter,
    // destinationer...)

    // Diverse attribut för statistiksamling
    //....    
    
    private int time = 0;
    
//    List of type Car
    ArrayList<Car> Carlist = new ArrayList<>();
    
    
    /**
     * nextcar() generates a random number between 1 and n
     * @param n upperbound of random
     */
    public void nextcar(int n){
    	Random rand = new Random();
		int Ai = rand.nextInt(n)+1;
		this.NextCar = Ai;
	}
    /**
     * creates a new TrafficSystem
     * @param r0 is a integer deciding length of the first Lane
     * @param r is a integer deciding the length of turning Lanes
     * @param s1Period is a integer deciding what period the Lights will have
     * @param s1Green a integer that decides the green time of the straight forward Light
     * @param s2Green a integer that decides the green time of the turning Light
     * @param Ankomstintensitet an integer that decides how often a new car will spawn
     */
	public TrafficSystem(int r0,  int r, int s1Period, int s1Green,  int s2Green, int Ankomstintensitet){
		
    	this.r0 = new Lane(r0);
    	this.r1 = new Lane(r);
    	this.r2 = new Lane(r);
    	this.r2.firstSpot().setTurn(1);
    	this.s1 = new Light(s1Period,s1Green);
    	this.s2 = new Light(s1Period,s2Green);
    	this.Ankomstintesitet = Ankomstintensitet;
    	nextcar(this.Ankomstintesitet);
    	
    	
    	//...
    	}
	/**
	 * CreateCar() create a new car to be put at the last spot of the first lane
	 * @return the new car with its parameters
	 */
	
	public Car CreateCar(){
		Random rand = new Random();
		int Dest = rand.nextInt(10)+1;
		int identity = rand.nextInt(10)+1;
		if (identity<=8){
			if(Dest <= 5){
				return  new Car(getTime(), 1, r0.lastSpot());
			}else {
				return  new Car(getTime(), 0, r0.lastSpot());
			}	
		}
		else{
			if(Dest <= 5){
				return  new Ferrari(getTime(), 1, r0.lastSpot());
			}else {
				return  new Ferrari(getTime(), 0, r0.lastSpot());
			}	
		}
	}
	/**
	 * AverageTime() calculates the averege time(timesteps)it takes for the cars to pass the light
	 * @return the average time 
	 */
	public int AvarageTime(){
		AvarageCarTime = TotalCarTime/getCars();
		return AvarageCarTime;
	}
	/**
	 * carTime(Car c) calculates how long it took for the car sent in as an argument to pass the light
	 * @param c a Car with info about the time
	 * @return the time it took
	 */
	public int carTime(Car c){
		return this.getTime() - c.getBornTime(); 
	} 
    public void readParameters() {
	// Läser in parametrar för simuleringen
	// Metoden kan läsa från terminalfönster, dialogrutor
	// eller från en parameterfil. Det sista alternativet
	// är att föredra vid uttestning av programmet eftersom
	// man inte då behöver mata in värdena vid varje körning.
        // Standardklassen Properties är användbar för detta. 
    }
    /**
     * Steps the trafficSystem by moving the Cars in the lanes if possible 
     * and removing and getting data from the cars that leaves the turning lanes.
     * Invokes Create new car if requirements are met and puts it at the end of the first lane 
     * It also updates the light and steps the Time.
     */
    public void step() {
    	if(s1.isGreen()){
    		Car tmp1 = r1.getFirst();
    		if(tmp1 != null){
    			
    			
    			if(getCars() == 0){
    				setLowTime(carTime(tmp1));
    			}if(carTime(tmp1) < getLowTime()){
    				setLowTime(carTime(tmp1));
    				
    			}
    			if(carTime(tmp1) > getLongTime()){
        			setLongTime(carTime(tmp1));
        			
    			}
    			setCarsF(getCarsF() + 1);
    			TotalCarTime = TotalCarTime + carTime(tmp1);
    			Carlist.add(tmp1);
    		}
    	}
    	if(s2.isGreen()){
    		Car tmp2 = r2.getFirst();
    		if(tmp2 != null){
    			
    			if(getCars() == 0){
    				setLowTime(carTime(tmp2));
    			}
    			if(carTime(tmp2) < getLowTime()){
    				setLowTime(carTime(tmp2));
    			}
    			if(carTime(tmp2) > getLongTime()){
    			setLongTime(carTime(tmp2));	
    			}
    			setCarsT(getCarsT() + 1);
    			TotalCarTime = TotalCarTime + carTime(tmp2);
    			Carlist.add(tmp2);
    		}
    	}
    	
    	r1.step();
    	r2.step();
    	
    	if(r0.firstCar() != null){
    		
    		if(r0.firstCar().getDestination() > 0){   		
    			r1.putLast(r0.getFirst());	
    		}   	
    		else{    	  			
    			r2.putLast(r0.getFirst());
    		}
    	}
    	
    	r0.step();
    	
    	if(this.NextCar != 0){
    		this.NextCar--;
    		
    	}
    	else{     		
    		r0.putLast(CreateCar());
    		nextcar(Ankomstintesitet);
    	}
    	s1.step();
    	s2.step();
    	this.setTime(this.getTime() + 1);
    	
    }
    	
	// Stega systemet ett tidssteg m h a komponenternas step-metoder
	// Skapa bilar, lägg in och ta ur på de olika Lane-kompenenterna
    
    /**
     * prints the accumilated statistics of this trafficsystem
     */
    public void printStatistics() {
    	System.out.println("Statistics for the traffic system: \n Cars F passed: " + getCarsF() + " \n Cars T passed: " + getCarsT() + "\n total Cars passed:" + getCars() +"\n Average pass time: " +  AvarageTime() + "\nLongest/shortest pass time: " + getLongTime() + " / " + getLowTime() );
	// Skriv statistiken samlad så här långt
    }
    /**
     * prints a graphical representation of the trafficsystem
     */
    public void print() {
    	System.out.flush();
    	String s = s1.toString() + "\n" + s2.toString() + "\n" + r1.toString() + r0.toString() + "\n" + r2.toString();
    	System.out.println(s);
	// Skriv ut en grafisk representation av kösituationen
	// med hjälp av klassernas toString-metoder
    }
	public int getCarsF() {
		return CarsF;
	}
	public void setCarsF(int cars) {
		CarsF = cars;
	}
	public int getCarsT() {
		return CarsT;
	}
	public void setCarsT(int cars) {
		CarsT = cars;
	}
	public int getCars(){
		if(Carlist == null) 
			return 0;
		else
			return Carlist.size();
		
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getLongTime() {
		return longTime;
	}
	public void setLongTime(int longTime) {
		this.longTime = longTime;
	}
	public int getLowTime() {
		return lowTime;
	}
	public void setLowTime(int lowTime) {
		this.lowTime = lowTime;
	}

} 	
