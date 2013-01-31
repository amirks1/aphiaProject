package serviceLocal;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;
import javax.xml.rpc.ServiceException;

import methodClass.Cash;
import methodClass.MethodObject;

import aphia.v1_0.AphiaNameService;
import aphia.v1_0.AphiaNameServiceLocator;
import aphia.v1_0.AphiaNameServicePortType;
import aphia.v1_0.AphiaRecord;
import aphia.v1_0.Classification;
import aphia.v1_0.Source;
import aphia.v1_0.Vernacular;

@WebService(targetNamespace = "http://serviceLocal/", portName = "LocalAphiaPort", serviceName = "LocalAphiaService")
public class LocalAphia implements AphiaNameServicePortType {

	AphiaNameService aphiaService;
	AphiaNameServicePortType aphiaPort;
	Cash cash;

	public LocalAphia() {
		aphiaService = new AphiaNameServiceLocator();
		cash = new Cash();
		try {
			aphiaPort = aphiaService.getAphiaNameServicePort();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public int getAphiaID(String scientificname, boolean marine_only)
			throws RemoteException {

		int aphiaID = 0;
		List<Object> listeParametres = new ArrayList<Object>();
		listeParametres.add(scientificname);
		listeParametres.add(marine_only);
		Date time = new Date();

		MethodObject requestObject = new MethodObject("getAphiaID", time,
				listeParametres);
		MethodObject cashObject = cash.getCashMethodObject(requestObject);
		if (cashObject != null) {
			return (Integer) cashObject.getReturnValue();
		} else {
			aphiaID = aphiaPort.getAphiaID(scientificname, marine_only);
			requestObject.setReturnValue(aphiaID);
			cash.addMethodObject(requestObject);
		}

		return aphiaID;
	}

	public AphiaRecord[] getAphiaRecords(String scientificname, boolean like,
			boolean fuzzy, boolean marine_only, int offset)
			throws RemoteException {

		AphiaRecord[] aphiaRecords = null;
		List<Object> listeParametres = new ArrayList<Object>();
		listeParametres.add(scientificname);
		listeParametres.add(like);
		listeParametres.add(fuzzy);
		listeParametres.add(marine_only);
		listeParametres.add(offset);
		Date time = new Date();

		MethodObject requestObject = new MethodObject("getAphiaRecords", time,
				listeParametres);
		MethodObject cashObject = cash.getCashMethodObject(requestObject);
		if (cashObject != null) {
			return (AphiaRecord[]) cashObject.getReturnValue();
		} else {
			aphiaRecords = aphiaPort.getAphiaRecords(scientificname, like,
					fuzzy, marine_only, offset);
			requestObject.setReturnValue(aphiaRecords);
			cash.addMethodObject(requestObject);
		}

		return aphiaRecords;
	}

	public String getAphiaNameByID(int aphiaID) throws RemoteException {

		String aphiaName = null;
		List<Object> listeParametres = new ArrayList<Object>();
		listeParametres.add(aphiaID);
		Date time = new Date();

		MethodObject requestObject = new MethodObject("getAphiaNameByID", time,
				listeParametres);
		MethodObject cashObject = cash.getCashMethodObject(requestObject);
		if (cashObject != null) {
			return (String) cashObject.getReturnValue();
		} else {
			aphiaName = aphiaPort.getAphiaNameByID(aphiaID);
			requestObject.setReturnValue(aphiaName);
			cash.addMethodObject(requestObject);
		}
		return aphiaName;
	}

	public AphiaRecord getAphiaRecordByID(int aphiaID) throws RemoteException {

		AphiaRecord aphiaRecord = null;
		List<Object> listeParametres = new ArrayList<Object>();
		listeParametres.add(aphiaID);
		Date time = new Date();

		MethodObject requestObject = new MethodObject("getAphiaRecordByID",
				time, listeParametres);
		MethodObject cashObject = cash.getCashMethodObject(requestObject);
		if (cashObject != null) {
			return (AphiaRecord) cashObject.getReturnValue();
		} else {
			aphiaRecord = aphiaPort.getAphiaRecordByID(aphiaID);
			requestObject.setReturnValue(aphiaRecord);
			cash.addMethodObject(requestObject);
		}
		return aphiaRecord;
	}

	public AphiaRecord getAphiaRecordByTSN(int TSN) throws RemoteException {

		AphiaRecord aphiaRecord = null;
		List<Object> listeParametres = new ArrayList<Object>();
		listeParametres.add(TSN);
		Date time = new Date();

		MethodObject requestObject = new MethodObject("getAphiaRecordByTSN",
				time, listeParametres);
		MethodObject cashObject = cash.getCashMethodObject(requestObject);
		if (cashObject != null) {
			return (AphiaRecord) cashObject.getReturnValue();
		} else {
			aphiaRecord = aphiaPort.getAphiaRecordByTSN(TSN);
			requestObject.setReturnValue(aphiaRecord);
			cash.addMethodObject(requestObject);
		}
		return aphiaRecord;
	}

	public AphiaRecord[][] getAphiaRecordsByNames(String[] scientificnames,
			boolean like, boolean fuzzy, boolean marine_only)
			throws RemoteException {

		AphiaRecord[][] aphiaRecord = null;
		List<Object> listeParametres = new ArrayList<Object>();
		listeParametres.add(scientificnames);
		listeParametres.add(like);
		listeParametres.add(fuzzy);
		listeParametres.add(marine_only);
		Date time = new Date();

		MethodObject requestObject = new MethodObject("getAphiaRecordsByNames",
				time, listeParametres);
		MethodObject cashObject = cash.getCashMethodObject(requestObject);
		if (cashObject != null) {
			return (AphiaRecord[][]) cashObject.getReturnValue();
		} else {
			aphiaRecord = aphiaPort.getAphiaRecordsByNames(scientificnames,
					like, fuzzy, marine_only);
			requestObject.setReturnValue(aphiaRecord);
			cash.addMethodObject(requestObject);
		}
		return aphiaRecord;
	}

	public AphiaRecord[] getAphiaRecordsByVernacular(String vernacular,
			boolean like, int offset) throws RemoteException {

		AphiaRecord[] aphiaRecord = null;
		List<Object> listeParametres = new ArrayList<Object>();
		listeParametres.add(vernacular);
		listeParametres.add(like);
		listeParametres.add(offset);
		Date time = new Date();

		MethodObject requestObject = new MethodObject(
				"getAphiaRecordsByVernacular", time, listeParametres);
		MethodObject cashObject = cash.getCashMethodObject(requestObject);
		if (cashObject != null) {
			return (AphiaRecord[]) cashObject.getReturnValue();
		} else {
			aphiaRecord = aphiaPort.getAphiaRecordsByVernacular(vernacular,
					like, offset);
			requestObject.setReturnValue(aphiaRecord);
			cash.addMethodObject(requestObject);
		}
		return aphiaRecord;
	}

	public Classification getAphiaClassificationByID(int aphiaID)
			throws RemoteException {

		Classification aphiaClassification;
		List<Object> listeParametres = new ArrayList<Object>();
		listeParametres.add(aphiaID);
		Date time = new Date();

		MethodObject requestObject = new MethodObject(
				"getAphiaClassificationByID", time, listeParametres);
		MethodObject cashObject = cash.getCashMethodObject(requestObject);
		if (cashObject != null) {
			return (Classification) cashObject.getReturnValue();
		} else {
			aphiaClassification = aphiaPort.getAphiaClassificationByID(aphiaID);
			requestObject.setReturnValue(aphiaClassification);
			cash.addMethodObject(requestObject);
		}
		return aphiaClassification;
	}

	public Source[] getSourcesByAphiaID(int aphiaID) throws RemoteException {

		Source[] source = null;
		List<Object> listeParametres = new ArrayList<Object>();
		listeParametres.add(aphiaID);
		Date time = new Date();

		MethodObject requestObject = new MethodObject("getSourcesByAphiaID",
				time, listeParametres);
		MethodObject cashObject = cash.getCashMethodObject(requestObject);
		if (cashObject != null) {
			return (Source[]) cashObject.getReturnValue();
		} else {
			source = aphiaPort.getSourcesByAphiaID(aphiaID);
			requestObject.setReturnValue(source);
			cash.addMethodObject(requestObject);
		}
		return source;
	}

	public AphiaRecord[] getAphiaSynonymsByID(int aphiaID)
			throws RemoteException {

		AphiaRecord[] aphiaRecord = null;
		List<Object> listeParametres = new ArrayList<Object>();
		listeParametres.add(aphiaID);
		Date time = new Date();

		MethodObject requestObject = new MethodObject("getAphiaSynonymsByID",
				time, listeParametres);
		MethodObject cashObject = cash.getCashMethodObject(requestObject);
		if (cashObject != null) {
			return (AphiaRecord[]) cashObject.getReturnValue();
		} else {
			aphiaRecord = aphiaPort.getAphiaSynonymsByID(aphiaID);
			requestObject.setReturnValue(aphiaRecord);
			cash.addMethodObject(requestObject);
		}
		return aphiaRecord;
	}

	public Vernacular[] getAphiaVernacularsByID(int aphiaID)
			throws RemoteException {

		Vernacular[] vernacular = null;
		List<Object> listeParametres = new ArrayList<Object>();
		listeParametres.add(aphiaID);
		Date time = new Date();

		MethodObject requestObject = new MethodObject(
				"getAphiaVernacularsByID", time, listeParametres);
		MethodObject cashObject = cash.getCashMethodObject(requestObject);
		if (cashObject != null) {
			return (Vernacular[]) cashObject.getReturnValue();
		} else {
			vernacular = aphiaPort.getAphiaVernacularsByID(aphiaID);
			requestObject.setReturnValue(vernacular);
			cash.addMethodObject(requestObject);
		}

		return vernacular;
	}

	public AphiaRecord[] getAphiaChildrenByID(int aphiaID, int offset,
			boolean marine_only) throws RemoteException {

		AphiaRecord[] aphiaRecord = null;
		List<Object> listeParametres = new ArrayList<Object>();
		listeParametres.add(aphiaID);
		listeParametres.add(offset);
		listeParametres.add(marine_only);
		Date time = new Date();

		MethodObject requestObject = new MethodObject("getAphiaChildrenByID",
				time, listeParametres);
		MethodObject cashObject = cash.getCashMethodObject(requestObject);
		if (cashObject != null) {
			return (AphiaRecord[]) cashObject.getReturnValue();
		} else {
			aphiaRecord = aphiaPort.getAphiaChildrenByID(aphiaID, offset,
					marine_only);
			requestObject.setReturnValue(aphiaRecord);
			cash.addMethodObject(requestObject);
		}

		return aphiaRecord;
	}

	protected void finalize() {
		cash.saveMap();
	}
}
