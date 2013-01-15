package test;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import junit.framework.Assert;
import org.junit.Test;



import serviceLocal.AphiaRecord;
import serviceLocal.LocalAphiaInterface;
import serviceLocal.LocalAphiaService;
import serviceLocal.LocalAphiaServiceLocator;
import serviceLocal.Source;
import serviceLocal.Vernacular;
 
public class AphiaLocalTest {
 
	static LocalAphiaService aphiaService = new LocalAphiaServiceLocator();
	static LocalAphiaInterface aphiaPort;
	
	static{
		try {
			aphiaPort = aphiaService.getLocalAphiaPort();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getAphiaNameByIDTest() throws RemoteException  {
		
		double a = System.nanoTime ();
		String aphiaName = aphiaPort.getAphiaNameByID(2);
		double b = System.nanoTime ();					
		Assert.assertEquals("Animalia",aphiaName);		
		System.out.println("getAphiaNameByIDTest: " +(b-a)/1000000000);		 
	}
	
	@Test
	public void getAphiaIDTest() throws RemoteException {
		
		double a = System.nanoTime ();	
		int aphiaID = aphiaPort.getAphiaID("Animalia", true);
		double b = System.nanoTime ();					
		Assert.assertEquals(2,aphiaID);		
		System.out.println("getAphiaIDTest: "+(b-a)/1000000000);		
	}
	/*
	@Test
	public void getAphiaClassificationByIDTest() throws RemoteException {
		
			double a = System.nanoTime ();			
			Assert.assertEquals(,aphiaPort.getAphiaClassificationByID(4));						
			double b = System.nanoTime ();
			System.out.println((b-a)/1000000000);				 
	}
	
	@Test
	public void getAphiaSynonymsByIDTest() throws RemoteException {
		
			double a = System.nanoTime ();			
			Assert.assertEquals(,aphiaPort.getAphiaSynonymsByID());						
			double b = System.nanoTime ();
			System.out.println((b-a)/1000000000);				 
	}
	*/
	@Test
	public void getAphiaChildrenByIDTest() throws RemoteException {

			AphiaRecord[] aphiaRecords;
			
			//Chordata à 4 fils avec les IDs: 1824,146420,1822,146419
			int fils[]={1824,146420,1822,146419};
			double a = System.nanoTime ();
			aphiaRecords = aphiaPort.getAphiaChildrenByID(1821, 1, true);
			double b = System.nanoTime ();
			for(int i=0; i<aphiaRecords.length;i++){
				Assert.assertEquals(fils[i],aphiaRecords[i].getAphiaID());
			}
			System.out.println((b-a)/1000000000);
	}
	@Test
	public void getAphiarecordByIDTest() throws RemoteException {

		AphiaRecord aphiaRecord;		
		
		double a = System.nanoTime ();
		aphiaRecord = aphiaPort.getAphiaRecordByID(8);
		double b = System.nanoTime ();		
		
		Assert.assertEquals(8,aphiaRecord.getAphiaID());
		Assert.assertEquals("Woese, Kandler & Wheelis, 1990",aphiaRecord.getAuthority());
		Assert.assertEquals("Archaea",aphiaRecord.getScientificname());
		System.out.println((b-a)/1000000000);
	}
	
	@Test
	public void getAphiarecordByTSNTest() throws RemoteException {

		AphiaRecord aphiaRecord;		
		
		double a = System.nanoTime ();
		aphiaRecord = aphiaPort.getAphiaRecordByTSN(46861);
		double b = System.nanoTime();		
		
		Assert.assertEquals(558,aphiaRecord.getAphiaID());
		Assert.assertEquals("Grant, 1836",aphiaRecord.getAuthority());
		Assert.assertEquals("Porifera",aphiaRecord.getScientificname());
		System.out.println((b-a)/1000000000);
	}
	
	@Test
	public void getAphiaVernacularsByIDTest() throws RemoteException {
		
		Vernacular[] aphiaVernaculars;		
		double a = System.nanoTime ();
		aphiaVernaculars = aphiaPort.getAphiaVernacularsByID(146142);
		double b = System.nanoTime();
		
		//Bryozoa AphiaID: 146142 
		String[] language = {"Dutch","English","English"};
		String[] vernacular = {"mosdiertjes","moss animals","sea mats"};
		for(int i=0;i<aphiaVernaculars.length;i++){
			Assert.assertEquals(language[i],aphiaVernaculars[i].getLanguage());
			Assert.assertEquals(vernacular[i],aphiaVernaculars[i].getVernacular());
		}
		System.out.println((b-a)/1000000000);
	}
	@Test
	public void getSourcesByAphiaIDTest() throws RemoteException {
		
		Source[] source;		
		double a = System.nanoTime ();
		source = aphiaPort.getSourcesByAphiaID(151);
		double b = System.nanoTime();
		
		//Nassariidae AphiaID: 151 
		int[] source_id = {6852,39377};		
		for(int i=0;i<source.length;i++){
			Assert.assertEquals(source_id[i],source[i].getSource_id());			
		}
		System.out.println((b-a)/1000000000);
	}
	@Test
	public void getAphiaRecordsTest() throws RemoteException {

			AphiaRecord[] aphiaRecords;
			
			//Bryozoa% lui resemble trois elements avec les IDs: 146142,162579,605374
			int aphiaIDs[]={146142,162579,605374};
			double a = System.nanoTime ();
			aphiaRecords = aphiaPort.getAphiaRecords("Bryozoa%", true, true, true, 1);
			double b = System.nanoTime ();
			for(int i=0; i<aphiaRecords.length;i++){
				Assert.assertEquals(aphiaIDs[i],aphiaRecords[i].getAphiaID());
			}
			System.out.println((b-a)/1000000000);
	}
	@Test
	public void getAphiaRecordsByVernacular() throws RemoteException {

			AphiaRecord[] aphiaRecords;
			
			//mosdiertjes lui cooréspond deux records avec les IDs: 146142,2601
			int aphiaIDs[]={146142,2601};
			String[] scientificName= {"Bryozoa","Ectoprocta"};
			double a = System.nanoTime ();
			aphiaRecords = aphiaPort.getAphiaRecordsByVernacular("mosdiertjes", true, 1);
			double b = System.nanoTime();
			for(int i=0; i<aphiaRecords.length;i++){
				Assert.assertEquals(aphiaIDs[i],aphiaRecords[i].getAphiaID());
				Assert.assertEquals(scientificName[i],aphiaRecords[i].getScientificname());
			}
			System.out.println((b-a)/1000000000);
	}
}
