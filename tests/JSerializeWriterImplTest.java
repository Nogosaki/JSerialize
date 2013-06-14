package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import exesoft.*;

public class JSerializeWriterImplTest {

	JSerializeWriter writer;
	ObjectWithPrimitives os;
	ObjectWithAnotherObject os2;
	ObjectWithList os3;
	ObjectWithPrivateField os4;
	ObjectWithTreeMap os5;
	ObjectWithTransientField os6;
	ObjectWithHashSet os7;
	ObjectWithTreeSet os8;
	ObjectWithLinkedList os9;
	ObjectWithPriorityQueue os10;
	ObjectWithArrayDeque os11;
	ObjectWithBooleanField os12;
	ObjectWithFloatField os13;
	ObjectWithDoubleField os14;

	boolean equalMaps(Map<String, Object> m1, Map<String, Object> m2) {

		if (m1.size() != m2.size()) {
			return false;
		}
		if (!m1.keySet().equals(m2.keySet())) {
			return false;
		}
		return true;
	}

	@Before
	public void setUp() throws Exception {
		writer = new JSerializeWriterImpl();
		os = new ObjectWithPrimitives();
		os2 = new ObjectWithAnotherObject();
		os3 = new ObjectWithList();
		os4 = new ObjectWithPrivateField();
		os5 = new ObjectWithTreeMap();
		os6 = new ObjectWithTransientField();
		os7 = new ObjectWithHashSet();
		os8 = new ObjectWithTreeSet();
		os9 = new ObjectWithLinkedList();
		os10 = new ObjectWithPriorityQueue();
		os11 = new ObjectWithArrayDeque();
		os12 = new ObjectWithBooleanField();
		os13 = new ObjectWithFloatField();
		os14 = new ObjectWithDoubleField();
	}

	/**
	 * Test sprawdzaj�cy dzia�anie metody dla klasy z typem prymitywnym np. int,
	 * float. Sprawdza, czy wygenerowana mapa b�dzie zgodna z oczekiwaniami.
	 */
	@Test
	public void testObjectWithPrimitives() {

		Map<String, Object> expected = new HashMap<String, Object>();
		expected.put("a#int", 10);
		expected.put("b#int", 10);
		expected.put("#JSerializeMetaData#RootClassName", os.getClass()
				.getName());

		Map<String, Object> generated = writer.prepareMap(os);

		assertTrue(equalMaps(expected, generated));
	}

	/**
	 * Test sprawdzaj�cy dzia�anie metody dla klasy z list� np.ArrayList.
	 * Sprawdza, czy wygenerowana mapa b�dzie zgodna z oczekiwaniami.
	 */

	@Test
	public void testObjectWithList() {
		Map<String, Object> expected = new HashMap<String, Object>();
		expected.put("lista#java.util.ArrayList", new HashMap<String, Object>());
		expected.put("#JSerializeMetaData#RootClassName", os3.getClass()
				.getName());

		Map<String, Object> generated = writer.prepareMap(os3);

		assertTrue(equalMaps(expected, generated));
	}

	@Test
	public void testObjectWithArrayDeque() {
		Map<String, Object> expected = new HashMap<String, Object>();
		expected.put("deque#java.util.ArrayDeque",
				new HashMap<String, Object>());
		expected.put("#JSerializeMetaData#RootClassName", os11.getClass()
				.getName());

		Map<String, Object> generated = writer.prepareMap(os11);

		assertTrue(equalMaps(expected, generated));
	}

	/**
	 * Test sprawdza, czy prywatne pola klasy s� serializowane. Sprawdza, czy
	 * wygenerowana mapa b�dzie zgodna z oczekiwaniami
	 */
	@Test
	public void testObjectWithPrivateFields() {
		Map<String, Object> expected = new HashMap<String, Object>();
		expected.put("a#int", 10);
		expected.put("b#int", 10);
		expected.put("c#int", 10);
		expected.put("#JSerializeMetaData#RootClassName", os4.getClass()
				.getName());

		Map<String, Object> generated = writer.prepareMap(os4);

		assertTrue(equalMaps(expected, generated));
	}

	@Test
	public void testObjectWithTransientFields() {
		Map<String, Object> expected = new HashMap<String, Object>();
		expected.put("a#int", 10);
		expected.put("b#int", 10);
		expected.put("#JSerializeMetaData#RootClassName", os6.getClass()
				.getName());

		Map<String, Object> generated = writer.prepareMap(os6);

		assertTrue(equalMaps(expected, generated));
	}

	@Test
	public void testObjectWithHashSet() {
		Map<String, Object> expected = new HashMap<String, Object>();
		expected.put("set#java.util.Set", new HashMap<String, Object>());
		expected.put("#JSerializeMetaData#RootClassName", os7.getClass()
				.getName());

		Map<String, Object> generated = writer.prepareMap(os7);

		assertTrue(equalMaps(expected, generated));
	}

	@Test
	public void testObjectWithTreeSet() {
		Map<String, Object> expected = new HashMap<String, Object>();
		expected.put("ts#java.util.TreeSet", new HashMap<String, Object>());
		expected.put("#JSerializeMetaData#RootClassName", os8.getClass()
				.getName());

		Map<String, Object> generated = writer.prepareMap(os8);

		assertTrue(equalMaps(expected, generated));
	}

	@Test
	public void testObjectWithLinkedList() {
		Map<String, Object> expected = new HashMap<String, Object>();
		expected.put("link#java.util.LinkedList", new HashMap<String, Object>());
		expected.put("#JSerializeMetaData#RootClassName", os9.getClass()
				.getName());

		Map<String, Object> generated = writer.prepareMap(os9);

		assertTrue(equalMaps(expected, generated));
	}

	/**
	 * Test sprawdza, czy serializowane s� kolejki priorytetowe Sprawdza, czy
	 * wygenerowana mapa b�dzie zgodna z oczekiwaniami.
	 */
	@Test
	public void testObjectWithPriorityQueue() {
		Map<String, Object> expected = new HashMap<String, Object>();
		expected.put("prq#java.util.PriorityQueue",
				new HashMap<String, Object>());
		expected.put("#JSerializeMetaData#RootClassName", os10.getClass()
				.getName());

		Map<String, Object> generated = writer.prepareMap(os10);

		assertTrue(equalMaps(expected, generated));
	}

	/**
	 * Test sprawdza, czy serializowane s� obiekty r�ne od typ�w prymitywnych w
	 * danej klasie. Sprawdza, czy wygenerowana mapa b�dzie zgodna z
	 * oczekiwaniami.
	 */
	@Test
	public void testObjectWithAnotherObject() {
		Map<String, Object> expected = new HashMap<String, Object>();
		expected.put("str#java.lang.String", new HashMap<String, Object>().put(
				"value#charArray",
				new ArrayList<Object>().add(new Character[] { 'A', 'A' })));
		expected.put("#JSerializeMetaData#RootClassName", os2.getClass()
				.getName());

		Map<String, Object> generated = writer.prepareMap(os2);

		assertTrue(equalMaps(expected, generated));
	}

	@Test
	public void testObjectWithTreeMap() {
		Map<String, Object> expected = new HashMap<String, Object>();
		expected.put("map#java.util.TreeMap", new Object());
		expected.put("#JSerializeMetaData#RootClassName", os5.getClass()
				.getName());

		Map<String, Object> generated = writer.prepareMap(os5);
		writer.printMap(expected);
		writer.printMap(generated);
		assertTrue(equalMaps(expected, generated));
	}

	@Test
	public void testObjectWithBooleanField() {
		Map<String, Object> expected = new HashMap<String, Object>();
		expected.put("a#boolean", true);
		expected.put("b#boolean", false);
		expected.put("#JSerializeMetaData#RootClassName", os12.getClass()
				.getName());

		Map<String, Object> generated = writer.prepareMap(os12);

		assertTrue(equalMaps(expected, generated));
	}

	@Test
	public void testObjectWithFloatField() {
		Map<String, Object> expected = new HashMap<String, Object>();
		expected.put("a#float", 1.5);
		expected.put("b#float", 2.1);
		expected.put("#JSerializeMetaData#RootClassName", os13.getClass()
				.getName());

		Map<String, Object> generated = writer.prepareMap(os13);

		assertTrue(equalMaps(expected, generated));
	}

	@Test
	public void testObjectWithDoubleField() {
		Map<String, Object> expected = new HashMap<String, Object>();
		expected.put("a#double", 1.5);
		expected.put("b#double", 2.1);
		expected.put("#JSerializeMetaData#RootClassName", os14.getClass()
				.getName());

		Map<String, Object> generated = writer.prepareMap(os14);

		assertTrue(equalMaps(expected, generated));
	}

	@Test
	public void isRootNamePresent() {
		Map<String, Object> generated = writer.prepareMap(os);
		assertTrue(generated.containsKey("#JSerializeMetaData#RootClassName"));
	}
}
