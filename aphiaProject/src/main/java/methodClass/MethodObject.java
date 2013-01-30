package methodClass;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class MethodObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	

	private String methodName;
	private Date date;
	private List listObject;
	private Object returnValue;

	public MethodObject(String methodName, Date date, List list) {
		this.methodName = methodName;
		this.date = date;
		this.listObject = list;		
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List getListObject() {
		return listObject;
	}

	public void setListObject(List listObject) {
		this.listObject = listObject;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Object getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(Object returnValue) {
		this.returnValue = returnValue;
	}

}
