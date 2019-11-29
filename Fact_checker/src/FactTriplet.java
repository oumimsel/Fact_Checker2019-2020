
public class FactTriplet {

	
	String factID;
	String fact;
	boolean veracity;
	
	public FactTriplet(String factID, String fact, boolean veracity) {
		super();
		this.factID = factID;
		this.fact = fact;
		this.veracity = veracity;
	}
	
	
	public String getFactID() {
		return factID;
	}
	public void setFactID(String factID) {
		this.factID = factID;
	}
	public String getFact() {
		return fact;
	}
	public void setFact(String fact) {
		this.fact = fact;
	}
	public boolean isVeracity() {
		return veracity;
	}
	public void setVeracity(boolean veracity) {
		this.veracity = veracity;
	}
	
	
	 @Override
	    public String toString() {
	        return factID+"  "+fact+"  "+veracity;
	    }
	
}
