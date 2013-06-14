package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import exesoft.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestsJSerializerReaderImpl {

	JSerializeReaderImpl jr;
	JSerializeWriterImpl jw;
	Osoba os1;
	Osoba os2;
	Rodzina rodzina;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		jr = new JSerializeReaderImpl();
		jw = new JSerializeWriterImpl();
		os1 = new Osoba("Heniu", 180, 48);
		os2 = new Osoba("Henia", 160, 98);
		rodzina = new Rodzina("Henki");
		rodzina.rodzina.add(os1);
		rodzina.rodzina.add(os2);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testObjektuZWynikiemReadera() {
		assertTrue(os1.isEquals((Osoba) jr.fromMap(jw.prepareMap(os1))));
	}

	@Test
	public void testListyObjektowZWynikiemReadera() {
		assertTrue(rodzina.equals(jr.fromMap(jw.prepareMap(rodzina))));
	}

	public static Map<String, Object> sampleHashMap() {
		/**
		 * Osoba ja = new Osoba("Karol", 10); Osoba mama = new Osoba("Jola",
		 * 30);
		 * 
		 * Rodzina rodzina = new Rodzina();
		 * 
		 * rodzina.rodzina.add(ja);
		 * 
		 * rodzina.rodzina.add(mama);
		 */
		Map<String, Object> tmp = new HashMap<String, Object>();
		Map<String, Object> inner = new HashMap<String, Object>();
		Map<String, Object> x = new HashMap<String, Object>();
		Map<String, Object> y = new HashMap<String, Object>();
		y.put("String", "y");
		x.put("String", "x");
		inner.put("0", x);
		inner.put("1", y);
		tmp.put("Osoba", inner);

		return tmp;

	}

}
