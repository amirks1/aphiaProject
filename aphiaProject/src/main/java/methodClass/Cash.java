package methodClass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Cash {

	private File cash;
	private HashMap<String, List<MethodObject>> map;

	public Cash() {
		cash = new File("aphia.ser");
		map = new HashMap<String, List<MethodObject>>();
		if (cash.exists())
			cash.delete();
	}

	public void addMethodObject(MethodObject object) {
		String methodName = object.getMethodName();
		if (map.containsKey(methodName))
			map.get(methodName).add(object);
		else {
			System.out.println("Cash " + object.getReturnValue());
			List<MethodObject> listObject = new ArrayList<MethodObject>();
			listObject.add(object);
			map.put(methodName, listObject);
		}
	}

	public MethodObject getCashMethodObject(MethodObject object) {

		String methodName = object.getMethodName();
		MethodObject cashObject = null;
		int time = 60;

		if (map.containsKey(methodName)) {
			List<MethodObject> listMethodObject = map.get(methodName);
			for (int i = 0; i < listMethodObject.size(); i++) {
				cashObject = (MethodObject) listMethodObject.get(i);
				List listParametresCashObject = cashObject.getListObject();
				List listParametresObject = object.getListObject();

				if (compareParameters(methodName, listParametresCashObject,
						listParametresObject)) {
					Date date = new Date();
					if (date.getTime() - cashObject.getDate().getTime() > time * 60 * 1000) {
						listMethodObject.remove(i);
						return null;
					} else
						return cashObject;
				}
			}
			return null;
		}
		return null;
	}

	public boolean compareParameters(String methodName,
			List listParametresCashObject, List listParametresObject) {
		boolean returnValue = false;

		if (methodName.equalsIgnoreCase("getAphiaID")) {
			if (listParametresObject.get(0).equals(
					listParametresCashObject.get(0))
					&& listParametresObject.get(1).equals(
							listParametresCashObject.get(1)))
				returnValue = true;
			else
				returnValue = false;
		} else if (methodName.equalsIgnoreCase("getAphiaRecords")) {
			if (listParametresObject.get(0).equals(
					listParametresCashObject.get(0))
					&& listParametresObject.get(1).equals(
							listParametresCashObject.get(1))
					&& listParametresObject.get(2).equals(
							listParametresCashObject.get(2))
					&& listParametresObject.get(3).equals(
							listParametresCashObject.get(3))
					&& listParametresObject.get(4).equals(
							listParametresCashObject.get(4)))
				returnValue = true;
			else
				returnValue = false;
		} else if (methodName.equalsIgnoreCase("getAphiaNameByID")) {
			if (listParametresObject.get(0).equals(
					listParametresCashObject.get(0)))
				returnValue = true;
			else
				returnValue = false;
		} else if (methodName.equalsIgnoreCase("getAphiaRecordByID")) {
			if (listParametresObject.get(0).equals(
					listParametresCashObject.get(0)))
				returnValue = true;
			else
				returnValue = false;

		} else if (methodName.equalsIgnoreCase("getAphiaRecordByTSN")) {
			if (listParametresObject.get(0).equals(
					listParametresCashObject.get(0)))
				returnValue = true;
			else
				returnValue = false;
		} else if (methodName.equalsIgnoreCase("getAphiaRecordsByNames")) {

			String[] cashNames = (String[]) listParametresCashObject.get(0);
			String[] requestNames = (String[]) listParametresObject.get(0);
			boolean boolVar = true;

			if (cashNames.length == requestNames.length) {

				for (int j = 0; j < cashNames.length; j++) {
					if (!cashNames[j].equalsIgnoreCase(requestNames[j])) {
						boolVar = false;
						break;
					}
				}

			} else
				boolVar = false;
			if (boolVar
					&& listParametresObject.get(1).equals(
							listParametresCashObject.get(1))
					&& listParametresObject.get(2).equals(
							listParametresCashObject.get(2))
					&& listParametresObject.get(3).equals(
							listParametresCashObject.get(3)))
				returnValue = true;
			else
				returnValue = false;
		} else if (methodName.equalsIgnoreCase("getAphiaRecordsByVernacular")) {
			if (listParametresObject.get(0).equals(
					listParametresCashObject.get(0))
					&& listParametresObject.get(1).equals(
							listParametresCashObject.get(1))
					&& listParametresObject.get(2).equals(
							listParametresCashObject.get(2)))
				returnValue = true;
			else
				returnValue = false;
		} else if (methodName.equalsIgnoreCase("getAphiaClassificationByID")) {
			if (listParametresObject.get(0).equals(
					listParametresCashObject.get(0)))
				returnValue = true;
			else
				returnValue = false;
		} else if (methodName.equalsIgnoreCase("getSourcesByAphiaID")) {
			if (listParametresObject.get(0).equals(
					listParametresCashObject.get(0)))
				returnValue = true;
			else
				returnValue = false;
		} else if (methodName.equalsIgnoreCase("getAphiaSynonymsByID")) {
			if (listParametresObject.get(0).equals(
					listParametresCashObject.get(0)))
				returnValue = true;
			else
				returnValue = false;
		} else if (methodName.equalsIgnoreCase("getAphiaVernacularsByID")) {
			if (listParametresObject.get(0).equals(
					listParametresCashObject.get(0)))
				returnValue = true;
			else
				returnValue = false;
		} else if (methodName.equalsIgnoreCase("getAphiaChildrenByID")) {
			if (listParametresObject.get(0).equals(
					listParametresCashObject.get(0))
					&& listParametresObject.get(1).equals(
							listParametresCashObject.get(1))
					&& listParametresObject.get(2).equals(
							listParametresCashObject.get(2)))
				returnValue = true;
			else
				returnValue = false;
		}

		return returnValue;
	}

	public void saveMap() {

		try {
			FileOutputStream fileOut = new FileOutputStream("aphia.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);

			out.writeObject(map);

			out.close();
			fileOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
