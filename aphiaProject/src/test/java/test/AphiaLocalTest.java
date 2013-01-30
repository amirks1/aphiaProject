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
			// TODO
			// Au(date2.getTime())/(1000*60*60*24*355)+"       "+date1.getTime()to-generated
			// catch block
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
	public void getAphiaIDTestDistant() throws RemoteException {

		int aphiaID = aphiaPort.getAphiaID("Chrysophyta", true);
		aphiaID = aphiaPort.getAphiaID("Chrysophyta", true);
		System.out.println("getAphiaIDTestDistant " + aphiaID);

		Assert.assertEquals(17166, aphiaID);
	}

	@Test
	public void getAphiaIDTestLocal() throws RemoteException {

		int aphiaID = aphiaPort.getAphiaID("Chrysophyta", true);
		aphiaID = aphiaPort.getAphiaID("Chrysophyta", true);
		Assert.assertEquals(17166, aphiaID);

	}

	@Test
	public void getAphiaRecordsTestDistant() throws RemoteException {

		AphiaRecord[] aphiaRecords;

		// Bryozoa% lui ressemble trois elements avec les IDs:
		// 146142,162579,605374
		int aphiaIDs[] = { 146142, 162579, 605374 };
		aphiaRecords = aphiaPort.getAphiaRecords("Crypt%", true, true, true, 1);
		aphiaRecords = aphiaPort.getAphiaRecords("Bryozoa%", true, true, true,
				1);
		for (int i = 0; i < aphiaRecords.length; i++) {
			Assert.assertEquals(aphiaIDs[i], aphiaRecords[i].getAphiaID());
		}
	}

	@Test
	public void getAphiaRecordsTestLocal() throws RemoteException {

		AphiaRecord[] aphiaRecords;

		// Bryozoa% lui ressemble trois elements avec les IDs:
		// 146142,162579,605374
		int aphiaIDs[] = { 146142, 162579, 605374 };
		aphiaRecords = aphiaPort.getAphiaRecords("Bryozoa%", true, true, true,
				1);
		aphiaRecords = aphiaPort.getAphiaRecords("Bryozoa%", true, true, true,
				1);
		for (int i = 0; i < aphiaRecords.length; i++) {
			Assert.assertEquals(aphiaIDs[i], aphiaRecords[i].getAphiaID());
		}
	}

	@Test
	public void getAphiaNameByIDTestDistant() throws RemoteException {

		String aphiaName = aphiaPort.getAphiaNameByID(571373);
		aphiaName = aphiaPort.getAphiaNameByID(377975);
		System.out.println(aphiaName);
		Assert.assertEquals("Firmicutes", aphiaName);
	}

	@Test
	public void getAphiaNameByIDTestLocal() throws RemoteException {

		String aphiaName;
		aphiaName = aphiaPort.getAphiaNameByID(377975);
		aphiaName = aphiaPort.getAphiaNameByID(377975);
		Assert.assertEquals("Firmicutes", aphiaName);

	}

	@Test
	public void getAphiarecordByIDTestDistant() throws RemoteException {

		AphiaRecord aphiaRecord;
		aphiaRecord = aphiaPort.getAphiaRecordByID(603985);
		aphiaRecord = aphiaPort.getAphiaRecordByID(8);
		Assert.assertEquals(8, aphiaRecord.getAphiaID());
		Assert.assertEquals("Woese, Kandler & Wheelis, 1990",
				aphiaRecord.getAuthority());
		Assert.assertEquals("Archaea", aphiaRecord.getScientificname());
	}

	@Test
	public void getAphiarecordByIDTestLocal() throws RemoteException {

		AphiaRecord aphiaRecord;

		aphiaRecord = aphiaPort.getAphiaRecordByID(8);
		aphiaRecord = aphiaPort.getAphiaRecordByID(8);
		Assert.assertEquals(8, aphiaRecord.getAphiaID());
		Assert.assertEquals("Woese, Kandler & Wheelis, 1990",
				aphiaRecord.getAuthority());
		Assert.assertEquals("Archaea", aphiaRecord.getScientificname());
	}

	@Test
	public void getAphiarecordByTSNTestDistant() throws RemoteException {

		AphiaRecord aphiaRecord;

		aphiaRecord = aphiaPort.getAphiaRecordByTSN(53963);
		aphiaRecord = aphiaPort.getAphiaRecordByTSN(46861);
		Assert.assertEquals(558, aphiaRecord.getAphiaID());
		Assert.assertEquals("Grant, 1836", aphiaRecord.getAuthority());
		Assert.assertEquals("Porifera", aphiaRecord.getScientificname());

	}

	@Test
	public void getAphiarecordByTSNTestLocal() throws RemoteException {

		AphiaRecord aphiaRecord;

		aphiaRecord = aphiaPort.getAphiaRecordByTSN(46861);
		aphiaRecord = aphiaPort.getAphiaRecordByTSN(46861);
		Assert.assertEquals(558, aphiaRecord.getAphiaID());
		Assert.assertEquals("Grant, 1836", aphiaRecord.getAuthority());
		Assert.assertEquals("Porifera", aphiaRecord.getScientificname());

	}

	@Test
	public void getAphiaRecordsByNamesDistant() throws RemoteException {

		AphiaRecord[][] aphiaRecords = null;

		// Nematoda lui corréspond :
		String[] nematoda = { "Nematoda", "Nematoda incertae sedis",
				"Nematodactylus", "Nematodactylus boopis",
				"Nematodactylus flexipes" };
		// Macrodasyidalui corréspond :
		String[] macrodasyida = { "Macrodasyida",
				"Macrodasyida incertae sedis", "Macrodasyidae" };

		String[] noms = { "Nematoda", "Macrodasyida" };
		aphiaRecords = aphiaPort
				.getAphiaRecordsByNames(noms, true, true, false);
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
	public void getAphiaRecordsByNamesLocal() throws RemoteException {

		AphiaRecord[][] aphiaRecords = null;

		// Nematoda lui corréspond :
		String[] nematoda = { "Nematoda", "Nematoda incertae sedis",
				"Nematodactylus", "Nematodactylus boopis",
				"Nematodactylus flexipes" };
		// Macrodasyidalui corréspond :
		String[] macrodasyida = { "Macrodasyida",
				"Macrodasyida incertae sedis", "Macrodasyidae" };

		String[] noms = { "Nematoda", "Macrodasyida" };

		aphiaRecords = aphiaPort.getAphiaRecordsByNames(noms, true, true, true);
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
	public void getAphiaRecordsByVernacularDistant() throws RemoteException {

		AphiaRecord[] aphiaRecords;

		// mosdiertjes lui corréspond deux records avec les IDs: 146142,2601
		int aphiaIDs[] = { 146142, 2601 };
		String[] scientificName = { "Bryozoa", "Ectoprocta" };
		aphiaRecords = aphiaPort.getAphiaRecordsByVernacular("platyhelminths",
				true, 1);
		aphiaRecords = aphiaPort.getAphiaRecordsByVernacular("mosdiertjes",
				true, 1);
		for (int i = 0; i < aphiaRecords.length; i++) {
			Assert.assertEquals(aphiaIDs[i], aphiaRecords[i].getAphiaID());
			Assert.assertEquals(scientificName[i],
					aphiaRecords[i].getScientificname());
		}
	}

	@Test
	public void getAphiaRecordsByVernacularLocal() throws RemoteException {

		AphiaRecord[] aphiaRecords;

		// mosdiertjes lui corréspond deux records avec les IDs: 146142,2601
		int aphiaIDs[] = { 146142, 2601 };
		String[] scientificName = { "Bryozoa", "Ectoprocta" };
		aphiaRecords = aphiaPort.getAphiaRecordsByVernacular("mosdiertjes",
				true, 1);
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
	public void getSourcesByAphiaIDTestDistant() throws RemoteException {

		Source[] source;
		source = aphiaPort.getSourcesByAphiaID(793);
		source = aphiaPort.getSourcesByAphiaID(151);
		// Nassariidae AphiaID: 151
		int[] source_id = { 6852, 39377 };
		for (int i = 0; i < source.length; i++) {
			Assert.assertEquals(source_id[i], source[i].getSource_id());
		}
	}

	@Test
	public void getSourcesByAphiaIDTestLocal() throws RemoteException {

		Source[] source;
		source = aphiaPort.getSourcesByAphiaID(151);
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
	public void getAphiaChildrenByIDTestDistant() throws RemoteException {

		AphiaRecord[] aphiaRecords;

		int fils[] = { 1824, 146420, 1822, 146419 };

		aphiaRecords = aphiaPort.getAphiaChildrenByID(368663, 1, true);
		aphiaRecords = aphiaPort.getAphiaChildrenByID(1821, 1, true);
		for (int i = 0; i < aphiaRecords.length; i++) {
			Assert.assertEquals(fils[i], aphiaRecords[i].getAphiaID());
		}
	}

	@Test
	public void getAphiaChildrenByIDTestLocal() throws RemoteException {

		AphiaRecord[] aphiaRecords;

		int fils[] = { 1824, 146420, 1822, 146419 };
		aphiaRecords = aphiaPort.getAphiaChildrenByID(1821, 1, true);
		aphiaRecords = aphiaPort.getAphiaChildrenByID(1821, 1, true);
		for (int i = 0; i < aphiaRecords.length; i++) {
			Assert.assertEquals(fils[i], aphiaRecords[i].getAphiaID());
		}
	}

	@Test
	public void getAphiaVernacularsByIDTestDistant() throws RemoteException {

		Vernacular[] aphiaVernaculars;
		aphiaVernaculars = aphiaPort.getAphiaVernacularsByID(559429);
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

	@Test
	public void getAphiaVernacularsByIDTestLocal() throws RemoteException {

		Vernacular[] aphiaVernaculars;
		aphiaVernaculars = aphiaPort.getAphiaVernacularsByID(146142);
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
