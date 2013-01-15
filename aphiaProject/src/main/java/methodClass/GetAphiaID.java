package methodClass;

import java.io.Serializable;

public class GetAphiaID implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String methodName;
	private String scientificname;
	private boolean marine_only;
	private int returnValue;
	
	public GetAphiaID(String methodName,String scientificname,boolean marine_only,int returnValue){
		this.methodName = methodName;
		this.scientificname = scientificname;
		this.marine_only = marine_only;
		this.returnValue = returnValue;
	}
	
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getScientificname() {
		return scientificname;
	}
	public void setScientificname(String scientificname) {
		this.scientificname = scientificname;
	}
	public boolean isMarine_only() {
		return marine_only;
	}
	public void setMarine_only(boolean marine_only) {
		this.marine_only = marine_only;
	}
	public int getReturnValue() {
		return returnValue;
	}
	public void setReturnValue(int returnValue) {
		this.returnValue = returnValue;
	}
	
	public boolean equals(String methodName,String scientificname,boolean marine_only){
		if(this.methodName.equalsIgnoreCase(methodName) && this.scientificname.equalsIgnoreCase(scientificname)
				&& this.marine_only==marine_only) 
			return true;
		else
			return false;
	}
}
