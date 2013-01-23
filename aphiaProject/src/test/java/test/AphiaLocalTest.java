package test;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import serviceLocal.AphiaRecord;
import serviceLocal.LocalAphiaInterface;
import serviceLocal.LocalAphiaService;
import serviceLocal.LocalAphiaServiceLocator;
import serviceLocal.Source;
import serviceLocal.Vernacular;

public class AphiaLocalTest {

	LocalAphiaService aphiaService;
	LocalAphiaInterface aphiaPort;
	double a;
	double b;

	public AphiaLocalTest() {
		aphiaService = new LocalAphiaServiceLocator();
		try {
			aphiaPort = aphiaService.getLocalAphiaPort();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Before
	public void before() {
		a = System.nanoTime();
	}

	@After
	public void after() {
		b = System.nanoTime();
		System.out.println((b - a) / 1000000000);
	}

	@Test
	public void getAphiaIDTest() throws RemoteException {

		int aphiaID = aphiaPort.getAphiaID("Biota", true);
		
		Assert.assertEquals(1, aphiaID);
	}

	@Test
	public void getAphiaRecordsTest() throws RemoteException {

		AphiaRecord[] aphiaRecords;

		// Bryozoa% lui ressemble trois elements avec les IDs:
		// 146142,162579,605374
		int aphiaIDs[] = { 146142, 162579, 605374 };
		aphiaRecords = aphiaPort.getAphiaRecords("Bryozoa%", true, true, true,
				1);
		for (int i = 0; i < aphiaRecords.length; i++) {
			Assert.assertEquals(aphiaIDs[i], aphiaRecords[i].getAphiaID());
		}
	}

	@Test
	public void getAphiaNameByIDTest() throws RemoteException {

		String aphiaName = aphiaPort.getAphiaNameByID(368660);
		Assert.assertEquals("Chromobiota", aphiaName);
	}

	@Test
	public void getAphiarecordByIDTest() throws RemoteException {

		AphiaRecord aphiaRecord;

		aphiaRecord = aphiaPort.getAphiaRecordByID(8);
		Assert.assertEquals(8, aphiaRecord.getAphiaID());
		Assert.assertEquals("Woese, Kandler & Wheelis, 1990",
				aphiaRecord.getAuthority());
		Assert.assertEquals("Archaea", aphiaRecord.getScientificname());
	}

	@Test
	public void getAphiarecordByTSNTest() throws RemoteException {

		AphiaRecord aphiaRecord;

		aphiaRecord = aphiaPort.getAphiaRecordByTSN(46861);
		Assert.assertEquals(558, aphiaRecord.getAphiaID());
		Assert.assertEquals("Grant, 1836", aphiaRecord.getAuthority());
		Assert.assertEquals("Porifera", aphiaRecord.getScientificname());

	}

	@Test
	public void getAphiaRecordsByNames() throws RemoteException {

		AphiaRecord[][] aphiaRecords = null;

		// Nematoda lui cooréspond :
		String[] nematoda = { "Nematoda", "Nematoda incertae sedis",
				"Nematodactylus", "Nematodactylus boopis",
				"Nematodactylus flexipes" };
		// Macrodasyidalui corréspond :
		String[] macrodasyida = { "Macrodasyida",
				"Macrodasyida incertae sedis", "Macrodasyidae" };

		String[] noms = { "Nematoda", "Macrodasyida" };

		aphiaRecords = aphiaPort.getAphiaRecordsByNames(noms, true, true, true);

		for (int i = 0; i < aphiaRecords[0].length; i++) {
			Assert.assertEquals(nematoda[i],
					aphiaRecords[0][i].getScientificname());
		}
		for (int i = 0; i < aphiaRecords[1].length; i++) {
			Assert.assertEquals(macrodasyida[i],
					aphiaRecords[1][i].getScientificname());
		}
	}

	@Test
	public void getAphiaRecordsByVernacular() throws RemoteException {

		AphiaRecord[] aphiaRecords;

		// mosdiertjes lui corréspond deux records avec les IDs: 146142,2601
		int aphiaIDs[] = { 146142, 2601 };
		String[] scientificName = { "Bryozoa", "Ectoprocta" };
		aphiaRecords = aphiaPort.getAphiaRecordsByVernacular("mosdiertjes",
				true, 1);
		for (int i = 0; i < aphiaRecords.length; i++) {
			Assert.assertEquals(aphiaIDs[i], aphiaRecords[i].getAphiaID());
			Assert.assertEquals(scientificName[i],
					aphiaRecords[i].getScientificname());
		}
	}

	/*
	 * @Test public void getAphiaClassificationByIDTest() throws RemoteException
	 * {
	 * 
	 * double a = System.nanoTime ();
	 * Assert.assertEquals(,aphiaPort.getAphiaClassificationByID(4)); double b =
	 * System.nanoTime (); System.out.println((b-a)/1000000000); }
	 */
	@Test
	public void getSourcesByAphiaIDTest() throws RemoteException {

		Source[] source;
		source = aphiaPort.getSourcesByAphiaID(151);

		// Nassariidae AphiaID: 151
		int[] source_id = { 6852, 39377 };
		for (int i = 0; i < source.length; i++) {
			Assert.assertEquals(source_id[i], source[i].getSource_id());
		}
	}

	/*
	 * @Test public void getAphiaSynonymsByIDTest() throws RemoteException {
	 * 
	 * double a = System.nanoTime ();
	 * Assert.assertEquals(,aphiaPort.getAphiaSynonymsByID()); double b =
	 * System.nanoTime (); System.out.println((b-a)/1000000000); }
	 */

	@Test
	public void getAphiaChildrenByIDTest() throws RemoteException {

		AphiaRecord[] aphiaRecords;

		int fils[] = { 1824, 146420, 1822, 146419 };
		aphiaRecords = aphiaPort.getAphiaChildrenByID(1821, 1, true);
		for (int i = 0; i < aphiaRecords.length; i++) {
			Assert.assertEquals(fils[i], aphiaRecords[i].getAphiaID());
		}
	}

	@Test
	public void getAphiaVernacularsByIDTest() throws RemoteException {

		Vernacular[] aphiaVernaculars;
		aphiaVernaculars = aphiaPort.getAphiaVernacularsByID(146142);

		// Bryozoa AphiaID: 146142
		String[] language = { "Dutch", "English", "English" };
		String[] vernacular = { "mosdiertjes", "moss animals", "sea mats" };
		for (int i = 0; i < aphiaVernaculars.length; i++) {
			Assert.assertEquals(language[i], aphiaVernaculars[i].getLanguage());
			Assert.assertEquals(vernacular[i],
					aphiaVernaculars[i].getVernacular());
		}
	}
}
