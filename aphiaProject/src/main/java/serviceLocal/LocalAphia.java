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
import java.util.Iterator;
import java.util.List;

import javax.jws.WebService;
import javax.xml.rpc.ServiceException;

import methodClass.GetAphiaID;

import aphia.v1_0.AphiaNameService;
import aphia.v1_0.AphiaNameServiceLocator;
import aphia.v1_0.AphiaNameServicePortType;
import aphia.v1_0.AphiaRecord;
import aphia.v1_0.Classification;
import aphia.v1_0.Source;
import aphia.v1_0.Vernacular;

@WebService(targetNamespace = "http://serviceLocal/", portName = "LocalAphiaPort", serviceName = "LocalAphiaService")
public class LocalAphia implements AphiaNameServicePortType {

	static AphiaNameService aphiaService = new AphiaNameServiceLocator();
	static AphiaNameServicePortType aphiaPort;
	//pour tester l'existence du fichier
	
	static File cash = new File("aphia.ser");
	static{	
		try {
			aphiaPort= aphiaService.getAphiaNameServicePort();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getAphiaID(String scientificname, boolean marine_only)
			throws RemoteException { 
		// TODO Auto-generated method stub
		
		//On met les objets dans le cash dans une liste
		List list = new ArrayList();		
		int aphiaID=0;
		
		if(cash.exists()){		 
			
			try {				
				//récuperer la liste d'objets
				FileInputStream fileIn = new FileInputStream("aphia.ser");				
				ObjectInputStream in = new ObjectInputStream(fileIn);
				
				list = (List) in.readObject();
				in.close();
		        fileIn.close();
		        
				//Chercher si la methode avec ses parametres existe dans la liste
				
				for(Iterator iter=list.iterator();iter.hasNext();){
	        		Object obj = iter.next();
	        		if(obj.getClass().toString().equalsIgnoreCase(GetAphiaID.class.toString())){
	        			GetAphiaID  objetCourant =  (GetAphiaID)obj;
	        			if(objetCourant.equals("getAphiaID", scientificname, marine_only)){					
							aphiaID = objetCourant.getReturnValue();
							
							return aphiaID;
						}
	        		}
	        		
	        	}
				
				//sinon faire un appel distant
							
				aphiaID = aphiaPort.getAphiaID(scientificname, marine_only);
				GetAphiaID getaphiaid = new GetAphiaID("getAphiaID",scientificname,marine_only,aphiaID);
				
				//récuperer la liste d'objets
				 fileIn = new FileInputStream("aphia.ser");				
				 in = new ObjectInputStream(fileIn);
				
				list = (List) in.readObject();
				in.close();
		        fileIn.close();
				//sauvegarder le nom de la methode et ses parametres ainsi
				//que le résultat dans le cash		        
		        
				FileOutputStream fileOut = new FileOutputStream("aphia.ser");
		        ObjectOutputStream out = new ObjectOutputStream(fileOut);
		        
		        list.add(getaphiaid);
		        out.writeObject(list);
		        
		        out.close();
		        fileOut.close();
		        
				return aphiaID;
				
			}catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (ClassNotFoundException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			} 
					   
		}else{
			//sinon faire un appel distant
						
			try {
			aphiaID = aphiaPort.getAphiaID(scientificname, marine_only);
			GetAphiaID getaphiaid = new GetAphiaID("getAphiaID",scientificname,marine_only,aphiaID);
			
			//sauvegarder le nom de la methode et ses parametres ainsi
			//que le résultat dans le cash
			
			FileOutputStream fileOut = new FileOutputStream("aphia.ser");
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        
	        list.add(getaphiaid);
	        out.writeObject(list);
	        
	        out.close();
	        fileOut.close();	        
	        
			}catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		
		
		return aphiaID;
	}
	
	public AphiaRecord[] getAphiaRecords(String scientificname, boolean like,
			boolean fuzzy, boolean marine_only, int offset)
			throws RemoteException {
		// TODO Auto-generated method stub
		
		AphiaRecord[] AphiaRecords;	
		AphiaRecords = aphiaPort.getAphiaRecords(scientificname, like, fuzzy, marine_only, offset);
		
		return AphiaRecords;
	}

	
	public String getAphiaNameByID(int aphiaID) throws RemoteException {
		// TODO Auto-generated method stub		
		
		String aphiaName;		
		aphiaName = aphiaPort.getAphiaNameByID(aphiaID);
			
		return aphiaName;
	}

	
	public AphiaRecord getAphiaRecordByID(int aphiaID) throws RemoteException {
		// TODO Auto-generated method stub		
		
		AphiaRecord aphiaRecord;		
		aphiaRecord = aphiaPort.getAphiaRecordByID(aphiaID);
			
		return aphiaRecord;
	}

	
	public AphiaRecord getAphiaRecordByTSN(int TSN) throws RemoteException {
		// TODO Auto-generated method stub		
		
		AphiaRecord aphiaRecord;		
		aphiaRecord = aphiaPort.getAphiaRecordByTSN(TSN);
			
		return aphiaRecord;
	}

	
	public AphiaRecord[][] getAphiaRecordsByNames(String[] scientificnames,
			boolean like, boolean fuzzy, boolean marine_only)
			throws RemoteException {
		// TODO Auto-generated method stub
		
		AphiaRecord[][] aphiaRecords;		
		aphiaRecords = aphiaPort.getAphiaRecordsByNames(scientificnames, like, fuzzy, marine_only);
		
		return aphiaRecords;
	}

	
	public AphiaRecord[] getAphiaRecordsByVernacular(String vernacular,
			boolean like, int offset) throws RemoteException {
		// TODO Auto-generated method stub
		
		AphiaRecord[] aphiaRecords;		
		aphiaRecords = aphiaPort.getAphiaRecordsByVernacular(vernacular, like, offset);
		
		return aphiaRecords;
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
		
		Source[] sources;
		sources = aphiaPort.getSourcesByAphiaID(aphiaID);
			
		return sources;
	}

	
	public AphiaRecord[] getAphiaSynonymsByID(int aphiaID)
			throws RemoteException {
		// TODO Auto-generated method stub
		
		AphiaRecord[] aphiaSynonyms;		
		aphiaSynonyms = aphiaPort.getAphiaSynonymsByID(aphiaID);
		
		return aphiaSynonyms;
	}

	
	public Vernacular[] getAphiaVernacularsByID(int aphiaID)
			throws RemoteException {
		// TODO Auto-generated method stub
		
		Vernacular[] aphiaVernaculars;		
		aphiaVernaculars = aphiaPort.getAphiaVernacularsByID(aphiaID);
		
		return aphiaVernaculars;
	}

	
	public AphiaRecord[] getAphiaChildrenByID(int aphiaID, int offset,
			boolean marine_only) throws RemoteException {
		// TODO Auto-generated method stub
		
		AphiaRecord[] AphiaChildren;		
		AphiaChildren = aphiaPort.getAphiaChildrenByID(aphiaID, offset, marine_only);
				
		return AphiaChildren;
	}

}
