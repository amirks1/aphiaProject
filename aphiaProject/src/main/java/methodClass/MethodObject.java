package methodClass;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class MethodObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String methodName;
	private Date time;
	private List listObject;
	private Object returnValue;

	public MethodObject(String methodName, Date time, List list,
			Object returnValue) {
		this.methodName = methodName;
		this.time = time;
		this.listObject = list;
		this.returnValue = returnValue;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
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

	public void setReturnValue(int returnValue) {
		this.returnValue = returnValue;
	}

}
