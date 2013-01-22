package serviceLocal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.jws.WebService;
import javax.xml.rpc.ServiceException;

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
	File cash;
	HashMap<String, List<Object>> map;

	public LocalAphia() {
		aphiaService = new AphiaNameServiceLocator();
		cash = new File("aphia.ser");
		map = new HashMap<String, List<Object>>();
		try {
			aphiaPort = aphiaService.getAphiaNameServicePort();
			if (cash.exists()) {
				FileInputStream fileIn = new FileInputStream("aphia.ser");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				map = (HashMap) in.readObject();
				in.close();
				fileIn.close();
			}

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getAphiaID(String scientificname, boolean marine_only)
			throws RemoteException {
		// TODO Auto-generated method stub

		int aphiaID = 0;
		List<Object> listObject = new ArrayList<Object>();
		MethodObject object;
		List<Object> listeParametres = new ArrayList<Object>();
		listeParametres.add(scientificname);
		listeParametres.add(marine_only);

		try {
			// Si la methode existe dans le cash
			if (map.containsKey("getAphiaID")) {
				listObject = map.get("getAphiaID");

				for (int i = 0; i < listObject.size(); i++) {
					object = (MethodObject) listObject.get(i);
					List listParametresObject = object.getListObject();
					// Si appel avec les memes parametres a deja ete fait
					if (listParametresObject.get(0).equals(scientificname)
							&& listParametresObject.get(1).equals(marine_only)) {
						aphiaID = (Integer) object.getReturnValue();
						return aphiaID;
					}
				}
				aphiaID = aphiaPort.getAphiaID(scientificname, marine_only);
				Date time = new Date();
				object = new MethodObject("getAphiaID", time, listeParametres,
						aphiaID);
				map.get("getAphiaID").add(object);
				// mise a jour cash
				FileOutputStream fileOut = new FileOutputStream("aphia.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(map);
				out.close();
				fileOut.close();
			} else {
				// sinon faire un appel distant
				aphiaID = aphiaPort.getAphiaID(scientificname, marine_only);

				Date time = new Date();
				object = new MethodObject("getAphiaID", time, listeParametres,
						aphiaID);
				listObject.add(object);
				map.put("getAphiaID", listObject);

				// mise a jour cash
				FileOutputStream fileOut = new FileOutputStream("aphia.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);

				out.writeObject(map);

				out.close();
				fileOut.close();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return aphiaID;
	}

	public AphiaRecord[] getAphiaRecords(String scientificname, boolean like,
			boolean fuzzy, boolean marine_only, int offset)
			throws RemoteException {
		// TODO Auto-generated method stub

		AphiaRecord[] AphiaRecords = null;
		List<Object> listObject = new ArrayList<Object>();
		MethodObject object;

		List<Object> listeParametres = new ArrayList<Object>();
		listeParametres.add(scientificname);
		listeParametres.add(like);
		listeParametres.add(fuzzy);
		listeParametres.add(marine_only);
		listeParametres.add(offset);

		try {
			// Si la methode existe dans le cash
			if (map.containsKey("getAphiaRecords")) {
				listObject = map.get("getAphiaRecords");

				for (int i = 0; i < listObject.size(); i++) {
					object = (MethodObject) listObject.get(i);
					List listParametresObject = object.getListObject();
					// Si un appel avec les memes parametres a deja ete fait
					if (listParametresObject.get(0).equals(scientificname)
							&& listParametresObject.get(1).equals(like)
							&& listParametresObject.get(2).equals(fuzzy)
							&& listParametresObject.get(3).equals(marine_only)
							&& listParametresObject.get(4).equals(offset)) {
						AphiaRecords = (AphiaRecord[]) object.getReturnValue();
						return AphiaRecords;
					}
				}
				// sinon faire un appel distant
				AphiaRecords = aphiaPort.getAphiaRecords(scientificname, like,
						fuzzy, marine_only, offset);
				Date time = new Date();
				object = new MethodObject("getAphiaID", time, listeParametres,
						AphiaRecords);
				map.get("getAphiaRecords").add(object);
				// mise a jour cash
				FileOutputStream fileOut = new FileOutputStream("aphia.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(map);
				out.close();
				fileOut.close();
			} else {
				AphiaRecords = aphiaPort.getAphiaRecords(scientificname, like,
						fuzzy, marine_only, offset);

				Date time = new Date();
				object = new MethodObject("getAphiaRecords", time,
						listeParametres, AphiaRecords);
				listObject.add(object);
				map.put("getAphiaRecords", listObject);
				// mise a jour cash

				FileOutputStream fileOut = new FileOutputStream("aphia.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);

				out.writeObject(map);

				out.close();
				fileOut.close();
			}

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return AphiaRecords;
	}

	public String getAphiaNameByID(int aphiaID) throws RemoteException {
		// TODO Auto-generated method stub

		String aphiaName = null;
		List<Object> listObject = new ArrayList<Object>();
		MethodObject object;
		List<Object> listeParametres = new ArrayList<Object>();
		listeParametres.add(aphiaID);

		try {
			// Si la methode existe dans le cash
			if (map.containsKey("getAphiaNameByID")) {
				listObject = map.get("getAphiaNameByID");

				for (int i = 0; i < listObject.size(); i++) {
					object = (MethodObject) listObject.get(i);
					List listParametresObject = object.getListObject();
					// Si appel avec les memes parametres a deja ete fait
					if (listParametresObject.get(0).equals(aphiaID)) {
						aphiaName = (String) object.getReturnValue();
						return aphiaName;
					}
				}
				aphiaName = aphiaPort.getAphiaNameByID(aphiaID);
				Date time = new Date();
				object = new MethodObject("getAphiaNameByID", time,
						listeParametres, aphiaName);
				map.get("getAphiaNameByID").add(object);
				// mise a jour cash
				FileOutputStream fileOut = new FileOutputStream("aphia.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(map);
				out.close();
				fileOut.close();
			} else {
				// sinon faire un appel distant
				aphiaName = aphiaPort.getAphiaNameByID(aphiaID);

				Date time = new Date();
				object = new MethodObject("getAphiaNameByID", time,
						listeParametres, aphiaName);
				listObject.add(object);
				map.put("getAphiaNameByID", listObject);

				// mise a jour cash
				FileOutputStream fileOut = new FileOutputStream("aphia.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);

				out.writeObject(map);

				out.close();
				fileOut.close();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return aphiaName;
	}

	public AphiaRecord getAphiaRecordByID(int aphiaID) throws RemoteException {
		// TODO Auto-generated method stub

		AphiaRecord aphiaRecord = null;
		List<Object> listObject = new ArrayList<Object>();
		MethodObject object;
		List<Object> listeParametres = new ArrayList<Object>();
		listeParametres.add(aphiaID);

		try {
			// Si la methode existe dans le cash
			if (map.containsKey("getAphiaRecordByID")) {
				listObject = map.get("getAphiaRecordByID");

				for (int i = 0; i < listObject.size(); i++) {
					object = (MethodObject) listObject.get(i);
					List listParametresObject = object.getListObject();
					// Si appel avec les memes parametres a deja ete fait
					if (listParametresObject.get(0).equals(aphiaID)) {
						aphiaRecord = (AphiaRecord) object.getReturnValue();
						return aphiaRecord;
					}
				}
				aphiaRecord = aphiaPort.getAphiaRecordByID(aphiaID);
				Date time = new Date();
				object = new MethodObject("getAphiaRecordByID", time,
						listeParametres, aphiaRecord);
				map.get("getAphiaRecordByID").add(object);
				// mise a jour cash
				FileOutputStream fileOut = new FileOutputStream("aphia.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(map);
				out.close();
				fileOut.close();
			} else {
				// sinon faire un appel distant
				aphiaRecord = aphiaPort.getAphiaRecordByID(aphiaID);

				Date time = new Date();
				object = new MethodObject("getAphiaRecordByID", time,
						listeParametres, aphiaRecord);
				listObject.add(object);
				map.put("getAphiaRecordByID", listObject);

				// mise a jour cash
				FileOutputStream fileOut = new FileOutputStream("aphia.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);

				out.writeObject(map);

				out.close();
				fileOut.close();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return aphiaRecord;
	}

	public AphiaRecord getAphiaRecordByTSN(int TSN) throws RemoteException {
		// TODO Auto-generated method stub

		List<Object> listObject = new ArrayList<Object>();
		MethodObject object;
		List<Object> listeParametres = new ArrayList<Object>();
		listeParametres.add(TSN);

		AphiaRecord aphiaRecord = null;

		try {
			// Si la methode existe dans le cash
			if (map.containsKey("getAphiaRecordByTSN")) {
				listObject = map.get("getAphiaRecordByTSN");

				for (int i = 0; i < listObject.size(); i++) {
					object = (MethodObject) listObject.get(i);
					List listParametresObject = object.getListObject();
					// Si un appel avec les memes parametres a deja ete fait
					if (listParametresObject.get(0).equals(TSN)) {
						aphiaRecord = (AphiaRecord) object.getReturnValue();
						return aphiaRecord;
					}
				}
				aphiaRecord = aphiaPort.getAphiaRecordByTSN(TSN);
				Date time = new Date();
				object = new MethodObject("getAphiaRecordByTSN", time,
						listeParametres, aphiaRecord);
				map.get("getAphiaRecordByTSN").add(object);
				// mise a jour cash
				FileOutputStream fileOut = new FileOutputStream("aphia.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(map);
				out.close();
				fileOut.close();
			} else {
				// sinon faire un appel distant
				aphiaRecord = aphiaPort.getAphiaRecordByTSN(TSN);

				Date time = new Date();
				object = new MethodObject("getAphiaRecordByTSN", time,
						listeParametres, aphiaRecord);
				listObject.add(object);
				map.put("getAphiaRecordByTSN", listObject);

				// mise a jour cash
				FileOutputStream fileOut = new FileOutputStream("aphia.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);

				out.writeObject(map);

				out.close();
				fileOut.close();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return aphiaRecord;
	}

	public AphiaRecord[][] getAphiaRecordsByNames(String[] scientificnames,
			boolean like, boolean fuzzy, boolean marine_only)
			throws RemoteException {
		// TODO Auto-generated method stub

		List<Object> listObject = new ArrayList<Object>();
		MethodObject object;
		List<Object> listeParametres = new ArrayList<Object>();
		listeParametres.add(scientificnames);
		listeParametres.add(like);
		listeParametres.add(fuzzy);
		listeParametres.add(marine_only);

		AphiaRecord[][] aphiaRecord = null;
		// pour tester l'egalité de scientificnames et la donnée corréspondante
		// dans le cash
		boolean boolVar;
		try {
			// Si la methode existe dans le cash
			if (map.containsKey("getAphiaRecordsByNames")) {
				listObject = map.get("getAphiaRecordsByNames");

				for (int i = 0; i < listObject.size(); i++) {

					object = (MethodObject) listObject.get(i);
					List listParametresObject = object.getListObject();
					// Si un appel avec les memes parametres a deja ete fait
					String[] tab = (String[]) listParametresObject.get(0);
					boolVar = true;
					if (tab.length == scientificnames.length) {

						for (int j = 0; j < tab.length; j++) {
							if (!tab[i].equalsIgnoreCase(scientificnames[i])) {
								boolVar = false;
								break;
							}
						}
					}
					if (boolVar && listParametresObject.get(1).equals(like)
							&& listParametresObject.get(2).equals(fuzzy)
							&& listParametresObject.get(3).equals(marine_only)) {
						aphiaRecord = (AphiaRecord[][]) object.getReturnValue();
						return aphiaRecord;
					}
				}
				aphiaRecord = aphiaPort.getAphiaRecordsByNames(scientificnames,
						like, fuzzy, marine_only);
				Date time = new Date();
				object = new MethodObject("getAphiaRecordsByNames", time,
						listeParametres, aphiaRecord);
				map.get("getAphiaRecordsByNames").add(object);
				// mise a jour cash
				FileOutputStream fileOut = new FileOutputStream("aphia.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(map);
				out.close();
				fileOut.close();
			} else {
				// sinon faire un appel distant
				aphiaRecord = aphiaPort.getAphiaRecordsByNames(scientificnames,
						like, fuzzy, marine_only);

				Date time = new Date();
				object = new MethodObject("getAphiaRecordsByNames", time,
						listeParametres, aphiaRecord);
				listObject.add(object);
				map.put("getAphiaRecordsByNames", listObject);

				// mise a jour cash
				FileOutputStream fileOut = new FileOutputStream("aphia.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);

				out.writeObject(map);

				out.close();
				fileOut.close();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return aphiaRecord;
	}

	public AphiaRecord[] getAphiaRecordsByVernacular(String vernacular,
			boolean like, int offset) throws RemoteException {
		// TODO Auto-generated method stub

		List<Object> listObject = new ArrayList<Object>();
		MethodObject object;
		List<Object> listeParametres = new ArrayList<Object>();
		listeParametres.add(vernacular);
		listeParametres.add(like);
		listeParametres.add(offset);

		AphiaRecord[] aphiaRecord = null;

		try {
			// Si la methode existe dans le cash
			if (map.containsKey("getAphiaRecordsByVernacular")) {
				listObject = map.get("getAphiaRecordsByVernacular");

				for (int i = 0; i < listObject.size(); i++) {
					object = (MethodObject) listObject.get(i);
					List listParametresObject = object.getListObject();
					// Si un appel avec les memes parametres a deja ete fait
					if (listParametresObject.get(0).equals(vernacular)
							&& listParametresObject.get(1).equals(like)
							&& listParametresObject.get(2).equals(offset)) {
						aphiaRecord = (AphiaRecord[]) object.getReturnValue();
						return aphiaRecord;
					}
				}
				aphiaRecord = aphiaPort.getAphiaRecordsByVernacular(vernacular,
						like, offset);
				Date time = new Date();
				object = new MethodObject("getAphiaRecordsByVernacular", time,
						listeParametres, aphiaRecord);
				map.get("getAphiaRecordsByVernacular").add(object);
				// mise a jour cash
				FileOutputStream fileOut = new FileOutputStream("aphia.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(map);
				out.close();
				fileOut.close();
			} else {
				// sinon faire un appel distant
				aphiaRecord = aphiaPort.getAphiaRecordsByVernacular(vernacular,
						like, offset);
				Date time = new Date();
				object = new MethodObject("getAphiaRecordsByVernacular", time,
						listeParametres, aphiaRecord);
				listObject.add(object);
				map.put("getAphiaRecordsByVernacular", listObject);

				// mise a jour cash
				FileOutputStream fileOut = new FileOutputStream("aphia.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);

				out.writeObject(map);

				out.close();
				fileOut.close();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return aphiaRecord;
	}

	public Classification getAphiaClassificationByID(int aphiaID)
			throws RemoteException {
		// TODO Auto-generated method stub

		Classification aphiaClassification;
		aphiaClassification = aphiaPort.getAphiaClassificationByID(aphiaID);

		return aphiaClassification;
	}

	public Source[] getSourcesByAphiaID(int aphiaID) throws RemoteException {
		// TODO Auto-generated method stub
		Source[] source = null;
		List<Object> listObject = new ArrayList<Object>();
		MethodObject object;
		List<Object> listeParametres = new ArrayList<Object>();
		listeParametres.add(aphiaID);

		try {
			// Si la methode existe dans le cash
			if (map.containsKey("getSourcesByAphiaID")) {
				listObject = map.get("getSourcesByAphiaID");

				for (int i = 0; i < listObject.size(); i++) {
					object = (MethodObject) listObject.get(i);
					List listParametresObject = object.getListObject();
					// Si appel avec les memes parametres a deja ete fait
					if (listParametresObject.get(0).equals(aphiaID)) {
						source = (Source[]) object.getReturnValue();
						return source;
					}
				}
				source = aphiaPort.getSourcesByAphiaID(aphiaID);
				Date time = new Date();
				object = new MethodObject("getSourcesByAphiaID", time,
						listeParametres, source);
				map.get("getSourcesByAphiaID").add(object);
				// mise a jour cash
				FileOutputStream fileOut = new FileOutputStream("aphia.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(map);
				out.close();
				fileOut.close();
			} else {
				// sinon faire un appel distant
				source = aphiaPort.getSourcesByAphiaID(aphiaID);
				Date time = new Date();
				object = new MethodObject("getSourcesByAphiaID", time,
						listeParametres, source);
				listObject.add(object);
				map.put("getSourcesByAphiaID", listObject);

				// mise a jour cash
				FileOutputStream fileOut = new FileOutputStream("aphia.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);

				out.writeObject(map);

				out.close();
				fileOut.close();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return source;
	}

	public AphiaRecord[] getAphiaSynonymsByID(int aphiaID)
			throws RemoteException {
		// TODO Auto-generated method stub
		AphiaRecord[] aphiaRecord = null;
		List<Object> listObject = new ArrayList<Object>();
		MethodObject object;
		List<Object> listeParametres = new ArrayList<Object>();
		listeParametres.add(aphiaID);

		try {
			// Si la methode existe dans le cash
			if (map.containsKey("getAphiaSynonymsByID")) {
				listObject = map.get("getAphiaSynonymsByID");

				for (int i = 0; i < listObject.size(); i++) {
					object = (MethodObject) listObject.get(i);
					List listParametresObject = object.getListObject();
					// Si appel avec les memes parametres a deja ete fait
					if (listParametresObject.get(0).equals(aphiaID)) {
						aphiaRecord = (AphiaRecord[]) object.getReturnValue();
						return aphiaRecord;
					}
				}
				aphiaRecord = aphiaPort.getAphiaSynonymsByID(aphiaID);
				Date time = new Date();
				object = new MethodObject("getAphiaSynonymsByID", time,
						listeParametres, aphiaRecord);
				map.get("getAphiaSynonymsByID").add(object);
				// mise a jour cash
				FileOutputStream fileOut = new FileOutputStream("aphia.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(map);
				out.close();
				fileOut.close();
			} else {
				// sinon faire un appel distant
				aphiaRecord = aphiaPort.getAphiaSynonymsByID(aphiaID);

				Date time = new Date();
				object = new MethodObject("getAphiaSynonymsByID", time,
						listeParametres, aphiaRecord);
				listObject.add(object);
				map.put("getAphiaSynonymsByID", listObject);

				// mise a jour cash
				FileOutputStream fileOut = new FileOutputStream("aphia.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);

				out.writeObject(map);

				out.close();
				fileOut.close();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return aphiaRecord;
	}

	public Vernacular[] getAphiaVernacularsByID(int aphiaID)
			throws RemoteException {
		// TODO Auto-generated method stub
		Vernacular[] vernacular = null;
		List<Object> listObject = new ArrayList<Object>();
		MethodObject object;
		List<Object> listeParametres = new ArrayList<Object>();
		listeParametres.add(aphiaID);

		try {
			// Si la methode existe dans le cash
			if (map.containsKey("getAphiaVernacularsByID")) {
				listObject = map.get("getAphiaVernacularsByID");

				for (int i = 0; i < listObject.size(); i++) {
					object = (MethodObject) listObject.get(i);
					List listParametresObject = object.getListObject();
					// Si appel avec les memes parametres a deja ete fait
					if (listParametresObject.get(0).equals(aphiaID)) {
						vernacular = (Vernacular[]) object.getReturnValue();
						return vernacular;
					}
				}
				vernacular = aphiaPort.getAphiaVernacularsByID(aphiaID);
				Date time = new Date();
				object = new MethodObject("getAphiaVernacularsByID", time,
						listeParametres, vernacular);
				map.get("getAphiaVernacularsByID").add(object);
				// mise a jour cash
				FileOutputStream fileOut = new FileOutputStream("aphia.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(map);
				out.close();
				fileOut.close();
			} else {
				// sinon faire un appel distant
				vernacular = aphiaPort.getAphiaVernacularsByID(aphiaID);
				Date time = new Date();
				object = new MethodObject("getAphiaVernacularsByID", time,
						listeParametres, vernacular);
				listObject.add(object);
				map.put("getAphiaVernacularsByID", listObject);

				// mise a jour cash
				FileOutputStream fileOut = new FileOutputStream("aphia.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);

				out.writeObject(map);

				out.close();
				fileOut.close();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		return vernacular;
	}

	public AphiaRecord[] getAphiaChildrenByID(int aphiaID, int offset,
			boolean marine_only) throws RemoteException {
		// TODO Auto-generated method stub
		AphiaRecord[] aphiaRecord = null;
		List<Object> listObject = new ArrayList<Object>();
		MethodObject object;
		List<Object> listeParametres = new ArrayList<Object>();
		listeParametres.add(aphiaID);
		listeParametres.add(offset);
		listeParametres.add(marine_only);

		try {
			// Si la methode existe dans le cash
			if (map.containsKey("getAphiaChildrenByID")) {
				listObject = map.get("getAphiaChildrenByID");

				for (int i = 0; i < listObject.size(); i++) {
					object = (MethodObject) listObject.get(i);
					List listParametresObject = object.getListObject();
					// Si appel avec les memes parametres a deja ete fait
					if (listParametresObject.get(0).equals(aphiaID)
							&& listParametresObject.get(1).equals(offset)
							&& listParametresObject.get(1).equals(marine_only)) {
						aphiaRecord = (AphiaRecord[]) object.getReturnValue();
						return aphiaRecord;
					}
				}
				aphiaRecord = aphiaPort.getAphiaChildrenByID(aphiaID, offset,
						marine_only);
				Date time = new Date();
				object = new MethodObject("getAphiaChildrenByID", time,
						listeParametres, aphiaRecord);
				map.get("getAphiaChildrenByID").add(object);
				// mise a jour cash
				FileOutputStream fileOut = new FileOutputStream("aphia.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(map);
				out.close();
				fileOut.close();
			} else {
				// sinon faire un appel distant
				aphiaRecord = aphiaPort.getAphiaChildrenByID(aphiaID, offset,
						marine_only);
				Date time = new Date();
				object = new MethodObject("getAphiaChildrenByID", time,
						listeParametres, aphiaRecord);
				listObject.add(object);
				map.put("getAphiaChildrenByID", listObject);

				// mise a jour cash
				FileOutputStream fileOut = new FileOutputStream("aphia.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);

				out.writeObject(map);

				out.close();
				fileOut.close();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		return aphiaRecord;
	}

}
