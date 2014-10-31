public class TrafficSystem {
    // Definierar de v�gar och signaler som ing�r i det 
   private int AvarageCarTime = 0; // system som skall studeras.
   
   // Samlar statistik
    
    // Attribut som beskriver best�ndsdelarna i systemet
    private Lane  r0;
    private Lane  r1;
    private Lane  r2;
    private Light s1;
    private Light s2;
    
    
    // Diverse attribut f�r simuleringsparametrar (ankomstintensiteter,
    // destinationer...)

    // Diverse attribut f�r statistiksamling
    //....    
    
    private int time = 0;

    public TrafficSystem(int r0, int r1, int r2, int s11, int s12, int s21, int s22){
    	this.r0 = new Lane(r0);
    	this.r1 = new Lane(r1);
    	this.r2 = new Lane(r2);
    	this.s1 = new Light(s11,s12);
    	this.s2 = new Light(s21,s22);
    	
    	
    	//...
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
    	this.time++;
	// Stega systemet ett tidssteg m h a komponenternas step-metoder
	// Skapa bilar, l�gg in och ta ur p� de olika Lane-kompenenterna
    }

    public void printStatistics() {
	// Skriv statistiken samlad s� h�r l�ngt
    }

    public void print() {
    	return System.out.print()
	// Skriv ut en grafisk representation av k�situationen
	// med hj�lp av klassernas toString-metoder
    }

}