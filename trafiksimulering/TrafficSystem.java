import java.util.Random;

public class TrafficSystem {
    // Definierar de v�gar och signaler som ing�r i det 
   private int AvarageCarTime = 0; // system som skall studeras.
   private int Cars = 0;
   private int lowTime;
   private int longTime;
   // Samlar statistik
    
    // Attribut som beskriver best�ndsdelarna i systemet
    private Lane  r0;
    private Lane  r1;
    private Lane  r2;
    private Light s1;
    private Light s2;
    private int Ankomstintesitet;
    private int NextCar;
    // Diverse attribut f�r simuleringsparametrar (ankomstintensiteter,
    // destinationer...)

    // Diverse attribut f�r statistiksamling
    //....    
    
    private int time = 0;
    
    
    public void nextcar(int n){
    	Random rand = new Random();
		int Ai = rand.nextInt(n)+1;
		this.NextCar = Ai;
	}
	public TrafficSystem(int r0, int r1, int r2, int s11, int s12, int s21, int s22, int n){
		CarPosition turn = new CarPosition(this.r2);
    	this.r0 = new Lane(r0);
    	this.r1 = new Lane(r1);
    	this.r2 = new Lane(r2);
    	this.r2.firstSpot().setTurn(turn);
    	this.s1 = new Light(s11,s12);
    	this.s2 = new Light(s21,s22);
    	Ankomstintesitet = n;
    	nextcar(Ankomstintesitet);
    	
    	//...
    	}
	public Car CreateCar(){
		Random rand = new Random();
		int Dest = rand.nextInt(1)+1;
		CarPosition last = r0.lastSpot();
		if(Dest == 1){
			return  new Car(time, r1.lastSpot(), last);
		}else {
			return  new Car(time, r2.lastSpot(), last);
		}	
	}
	
    public void readParameters() {
	// L�ser in parametrar f�r simuleringen
	// Metoden kan l�sa fr�n terminalf�nster, dialogrutor
	// eller fr�n en parameterfil. Det sista alternativet
	// �r att f�redra vid uttestning av programmet eftersom
	// man inte d� beh�ver mata in v�rdena vid varje k�rning.
        // Standardklassen Properties �r anv�ndbar f�r detta. 
    }

    public void step() {
    	if(s1.isGreen()){
    		Car tmp1 = r1.getFirst();
    	}
    	if(s2.isGreen()){
    		Car tmp2 = r2.getFirst();
    	}
    	
    	
    	r2.step();
    	r1.step();
    	
    	if(this.NextCar != 0){
    		NextCar--;
    	}else{ 
    		r0.putLast(CreateCar());
    		nextcar(Ankomstintesitet);
    	}
    	
    	if(r0.firstCar().getDestination() == r1.lastSpot()){
    		r1.putLast(r0.getFirst()); //Komih�g tider!
    	}else{
    		r2.putLast(r0.getFirst());
    	}
    	r0.step();
    	s1.step();
    	s2.step();
    	this.time++;
    	
    }
    	
	// Stega systemet ett tidssteg m h a komponenternas step-metoder
	// Skapa bilar, l�gg in och ta ur p� de olika Lane-kompenenterna
    

    public void printStatistics() {
	// Skriv statistiken samlad s� h�r l�ngt
    }

    public void print() {
    	String s = r0.toString() + r1.toString() + r2.toString();
    	System.out.println(s);
	// Skriv ut en grafisk representation av k�situationen
	// med hj�lp av klassernas toString-metoder
    }

}