import java.util.Scanner;

import org.omg.CORBA.DynAnyPackage.InvalidValue;

 /*
  Modell f�r trafiksimulering
  ===========================

  F�ljande klasser skall anv�ndas: 

     Car representerar fordon
         ankomsttid och destination som s�tts n�r objektet skapas

     Light representerar ljussignaler
         Se nedan
  
     Lane representerar ett v�gavsnitt
         En v�g representeras av en array d�r varje element
	 antingen �r tomt eller inneh�ller en referens till 
         ett bil-objekt.
         OBS: Klassen Lane p�minner om k�mekanismen i f�reg�ende
         uppgift men den skiljer sig ocks� i flera avseende.
         I denna klass st�lls nya bilar i ena �nden av ARRAYEN
         och inte n�rmast efter den som finns d�r. I k�mekanismen        
         var alltid elementen (kunderna) samlade medan bilarna
         i denna klass kan vara utspridda �ver hela arrayen.
         
 
     TrafficSystem
         Definierar de komponeneter dvs de v�gar och signaler
	 som ing�r i systemet. Se vidare nedan

     Simulation
         main-metod som driver simuleringen


  Den situation som skall simuleras ser schematiskt ut enligt



         C           B                               A
       s1<----r1-----<---------r0---------------------
       s2<----r2-----< 


  En fil (v�gstr�cka) r0 delar sig vid B i tv� filer r1 och r2.
  Signal s1 kontrollerar fil r1 och och signal s2 fil r2.
 
  Bilar uppst�r vid A. Sannolikheten att en bil skall komma till A
  vid ett visst tidsteg kallas ankomstintensiteten.

  Vid ett tidssteg r�r sig bilarna ett steg fram�t (om platsen framf�r
  �r ledig). Vid C tas bilarna ut fr�n filerna om repektive
  signal �r gr�n. Vid B tas bilar ut fr�n r0 och l�ggs in p� r1 eller r2
  beroende p� destination (och om platsen �r ledig).

  Anm: Man skulle kunna representera systemet med tv� Lane-objekt
  men d� m�ste man ha n�got s�tt att representera en "avtappning"
  (d�r sv�ngfilen b�rjar). Med den h�r valda representationen
  blir Lane-klassen enklare.  
    
*/





public class Simulation {
	

	
	
    
	public static void main(String [] args) throws InvalidValue {
    	
    	Scanner sc = new Scanner(System.in);
    	System.out.println("For how long time should the Simulation go?\n Enter Number: "); 
    	int time = sc.nextInt();
    	if(time <= 0) {
    		sc.close();
    		throw new InvalidValue("\n InvalidValue: The time must be larger than 0");}
    	System.out.println("Please choose length for lanes, time for lights and Car intesity\n First Lane");
    	int First_Lane = sc.nextInt();
    	System.out.println("turn lanes");
    	int turn_lanes = sc.nextInt();
    	System.out.println("Period for lights");
    	int Period_light_FT = sc.nextInt();
    	System.out.println("Green Time Straight");
    	int green_F = sc.nextInt();
    	System.out.println("Green Time turn");
    	int green_T = sc.nextInt();
    	System.out.println("Car intesity (High value => low intesity)");
    	int Ankomstintesitet = sc.nextInt();
    	TrafficSystem sim = new	TrafficSystem(First_Lane, turn_lanes, Period_light_FT, green_F, Period_light_FT, green_T, Ankomstintesitet);
    	// (r0, r1 o r2, period s1, green s1, period s2, green s2, ankonstint.)
    	sc.close();
    	while(time >= 0){
    		sim.step();
    		sim.print();
    		time--;
   
    	}
    	sim.printStatistics();
    //sim.AvarageTime();
	// Skapar ett TrafficSystem
	// Utf�r stegningen, anropar utskriftsmetoder

	//...

    }
}
