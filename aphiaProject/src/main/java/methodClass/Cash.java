package methodClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import aphia.v1_0.AphiaRecord;
import aphia.v1_0.Vernacular;

public class Cash {

	private File cash;
	private HashMap<String, List<Object>> map;
	
	public Cash() {
		cash = new File("aphia.ser");
		map = new HashMap<String, List<Object>>();
		if(cash.exists())
			cash.delete();
		try {
			if (cash.exists()) {
				FileInputStream fileIn = new FileInputStream("aphia.ser");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				map = (HashMap) in.readObject();
				in.close();
				fileIn.close();
			}
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

	public HashMap<String, List<Object>> getMap() {
		return map;
	}

public void addMethodObject(String methodName,MethodObject object,List listObject) throws IOException{
		
		if(map.containsKey(methodName)){
			map.get("getAphiaID").add(object);
		}else{			
			listObject.add(object);
			map.put("getAphiaID", listObject);
		}			
		
		FileOutputStream fileOut = new FileOutputStream("aphia.ser");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(map);
		out.close();
		fileOut.close();
	}
	
	public int getAphiaIDReturnValue(String scientificname ,boolean marine_only){
		List<Object> listObject = new ArrayList<Object>();
		MethodObject object;
		int aphiaID=0;
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
		return aphiaID;
	}
	
	public Vernacular[] getAphiaVernacularsByIDReturnValue(int aphiaID){
			List<Object> listObject = new ArrayList<Object>();
			MethodObject object;
			Vernacular[] vernacular = null;
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
			return vernacular;
		}
			
	
public AphiaRecord[] getAphiaChildrenByIDReturnValue(int aphiaID, int offset,
		boolean marine_only){
		List<Object> listObject = new ArrayList<Object>();
		MethodObject object;
		AphiaRecord[] aphiaRecord = null;
		listObject = map.get("getAphiaChildrenByID");		
		
		for (int i = 0; i < listObject.size(); i++) {
			object = (MethodObject) listObject.get(i);			
			List listParametresObject = object.getListObject();
			// Si appel avec les memes parametres a deja ete fait
			if (listParametresObject.get(0).equals(aphiaID)
					&& listParametresObject.get(1).equals(offset)
					&& listParametresObject.get(2).equals(marine_only)) {
				aphiaRecord = (AphiaRecord[]) object.getReturnValue();
				return aphiaRecord;
			}
		}
		return aphiaRecord;
	}
	
	
	
}


