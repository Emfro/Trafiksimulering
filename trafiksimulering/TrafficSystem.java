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
    
    
    public void nextcar(int n){
    	Random rand = new Random();
		int Ai = rand.nextInt(n)+1;
		this.NextCar = Ai;
	}
	public TrafficSystem(int r0,  int r, int s1Period, int s1Green, int s2Period, int s2Green, int Ankomstintensitet){
		
    	this.r0 = new Lane(r0);
    	this.r1 = new Lane(r);
    	this.r2 = new Lane(r);
    	this.r2.firstSpot().setTurn(1);
    	this.s1 = new Light(s1Period,s1Green);
    	this.s2 = new Light(s2Period,s2Green);
    	this.Ankomstintesitet = Ankomstintensitet;
    	nextcar(this.Ankomstintesitet);
    	
    	
    	//...
    	}
	
	
	public Car CreateCar(){
		Random rand = new Random();
		int Dest = rand.nextInt(10)+1;
		
		if(Dest <= 5){
			return  new Car(time, 1, r0.lastSpot());
		}else {
			return  new Car(time, 0, r0.lastSpot());
		}	
	}
	public int AvarageTime(){
		AvarageCarTime = TotalCarTime/getCars();
		return AvarageCarTime;
	}
	public int carTime(Car c){
		return this.time - c.getBornTime(); 
	} 
    public void readParameters() {
	// Läser in parametrar för simuleringen
	// Metoden kan läsa från terminalfönster, dialogrutor
	// eller från en parameterfil. Det sista alternativet
	// är att föredra vid uttestning av programmet eftersom
	// man inte då behöver mata in värdena vid varje körning.
        // Standardklassen Properties är användbar för detta. 
    }

    public void step() {
    	if(s1.isGreen()){
    		Car tmp1 = r1.getFirst();
    		if(tmp1 != null){
    			
    			
    			if(getCars() == 0){
    				lowTime = carTime(tmp1);
    			}if(carTime(tmp1) < lowTime){
    				lowTime = carTime(tmp1);
    				
    			}
    			if(carTime(tmp1) > longTime){
        			longTime = carTime(tmp1);
        			
    			}
    			setCarsF(getCarsF() + 1);
    			TotalCarTime = TotalCarTime + carTime(tmp1);
    		}
    	}
    	if(s2.isGreen()){
    		Car tmp2 = r2.getFirst();
    		if(tmp2 != null){
    			
    			if(getCars() == 0){
    				lowTime = carTime(tmp2);
    			}
    			if(carTime(tmp2) < lowTime){
    				lowTime = carTime(tmp2);
    			}
    			if(carTime(tmp2) > longTime){
    			longTime = carTime(tmp2);	
    			}
    			setCarsT(getCarsT() + 1);
    			TotalCarTime = TotalCarTime + carTime(tmp2);
    		}
    	}
    	
    	r1.step();
    	r2.step();
    	
    	if(r0.firstCar() != null){
    		if(r0.firstCar().getDestination() > 0){
    			if(r1.lastFree()){
    		r1.putLast(r0.getFirst());
    			}
    		}
    	
    		else{    	
    			if(r2.lastFree()){
    			r2.putLast(r0.getFirst());
    			}
    		}
    	}
    	
    	
    	r0.step();
    	
    	if(this.NextCar != 0){
    		this.NextCar--;
    	}else{ 
    		if(r0.lastFree()){
    		r0.putLast(CreateCar());
    		nextcar(Ankomstintesitet);
    		}
    	}
    	
    	
    	
    	
    	s1.step();
    	s2.step();
    	this.time++;
    	
    }
    	
	// Stega systemet ett tidssteg m h a komponenternas step-metoder
	// Skapa bilar, lägg in och ta ur på de olika Lane-kompenenterna
    

    public void printStatistics() {
    	System.out.println("Statistics for the traffic system: \n Cars F passed: " + getCarsF() + " \n Cars T passed: " + getCarsT() + "\n Average pass time: " +  AvarageTime() + "\nLongest/shortest pass time: " + longTime + " / " + lowTime );
	// Skriv statistiken samlad så här långt
    }

    public void print() {
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
		return CarsF + CarsT;
		
	}

} 	
