public class Ferrari extends Car{
	
	public Ferrari(int bornTime, int dest, CarPosition startingPos) {
		super(bornTime, dest, startingPos);
	}

	public String toString(){
    	if(this.getDestination() > 0) {
    		return "[<*]";
    	}else{
    		return   "[v*]";
    	}
    }

}