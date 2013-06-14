package exesoft;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * 
 * Class creates String in JSON's format based on map of objects.
 * @author Karol Buler, Wojciech Mucha
 * 
 */
public class JModelImpl implements JModel {
	/* 
	 * 
	 * Method encode takes map of objects and return String compatible with JSON's format.
	 * @author Karol Buler
	 * 
	 * @see exesoft.JModel#encode(java.util.Map)
	 * @param toJson - a Map <String, Object>
	 * @return String in JSON's format.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String encode(final Map<String, Object> toJson)
			throws ClassCastException {

		StringBuffer bufferJson = new StringBuffer();
		Iterator<Entry<String, Object>> it = toJson.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			if (pairs.getKey().equals("#JSerializeMetaData#RootClassName")) {
				bufferJson.insert(0, pairs.getValue() + " {");

			} else if (pairs.getValue() instanceof List) {
				bufferJson.append(getName((String) pairs.getKey()));
				bufferJson.append(handleList((List) pairs.getValue()));
				if (it.hasNext())
					bufferJson.append(", ");

			} else if (pairs.getValue() instanceof Map) {
				bufferJson.append(getName((String) pairs.getKey()));
				bufferJson.append(": {");
				bufferJson.append(handleMap((Map) pairs.getValue()));

			} else {
				if (!it.hasNext()) {
					if (pairs.getKey().toString().contains("value"))
						bufferJson.append(getName((String) pairs.getKey())
								+ ": { " + pairs.getValue() + " ");
					else
						bufferJson.append(getName((String) pairs.getKey())
								+ ":" + pairs.getValue() + " ");
				} else {
					if (pairs.getKey().toString().contains("value"))
						bufferJson.append(getName((String) pairs.getKey())
								+ ": { " + pairs.getValue() + ", ");
					else
						bufferJson.append(getName((String) pairs.getKey())
								+ ":" + pairs.getValue() + ", ");
				}
			}
			it.remove();
		}
		bufferJson.append("}");

		return bufferJson.toString();
	}

	/**
	 * Helper method of Encode class that uses lists (for example ArrayList) and returns JSON's format.
	 * @param list - a List
	 * @return String in JSON's format.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String handleList(List list) {

		StringBuffer bufferJson = new StringBuffer();
		
		bufferJson.append(":[ ");
		if (list.get(0) instanceof List) {
			for (int i = 0; i < list.size(); i++) {
				bufferJson.append("{");
				bufferJson.append(handleList((List) list.get(i)));
				bufferJson.append("}");
				if (i < list.size() - 1)
					bufferJson.append(", ");
			}
		} else if (list.get(0) instanceof Map) {
			for (int i = 0; i < list.size(); i++) {
				bufferJson.append("{");
				bufferJson.append(handleMap((Map) list.get(i)));
				if (i < list.size() - 1)
					bufferJson.append(", ");
			}
		} else {
			for (int i = 0; i < list.size(); i++) {
				bufferJson.append(list.get(i));
				if (i < list.size() - 1)
					bufferJson.append(", ");
			}
		}
		bufferJson.append(" ]");

		return bufferJson.toString();
	}

	/**
	 * Helper method of Encode class that uses maps (for example hashMap) and returns String in JSON's format.
	 * @param mapaIn - Map <String, Object>
	 * @return String in JSON's format.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String handleMap(Map<String, Object> mapaIn) {

		StringBuffer bufferJson = new StringBuffer();
		Iterator<Entry<String, Object>> it = mapaIn.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			if (pairs.getValue() instanceof List) {
				bufferJson.append(getName(pairs.getKey().toString()));
				bufferJson.append(handleList((List) pairs.getValue()));
				if (it.hasNext())
					bufferJson.append(", ");

			} else if (pairs.getValue() instanceof Map) {
				bufferJson.append(getName(pairs.getKey().toString()));
				bufferJson.append(": {");
				bufferJson.append(handleMap((Map) pairs.getValue()));

			} else {
				if (!it.hasNext()) {
					if (pairs.getKey().toString().contains("value"))
						bufferJson.append(getName(pairs.getKey().toString())
								+ ": { " + pairs.getValue() + " ");
					else
						bufferJson.append(pairs.getKey() + ":"
								+ pairs.getValue() + " ");
				} else {
					if (pairs.getKey().toString().contains("value"))
						bufferJson.append(getName(pairs.getKey().toString())
								+ ": { " + pairs.getValue() + ", ");
					else
						bufferJson.append(getName(pairs.getKey().toString())
								+ ":" + pairs.getValue() + ", ");
				}
			}
			it.remove();
		}
		bufferJson.append("}");

		return bufferJson.toString();
	}

	/* 
	 * Method encode takes JSON's String and returns map of objects.
	 * @author Wojciech Mucha
	 * @return map of objects
	 * @see exesoft.JModel#decode(java.lang.String)
	 */
	@Override
	public Map<String, Object> decode(String fromJson) {
		Map<String, Object> outJson = new HashMap<String, Object>();

		return outJson;
	}

	/**
	 * Helper method class of Encode that returns names of praticular object fields
	 * @param str - a String
	 * @return String in JSON's format.
	 */
	private String getName(String str) {
		StringBuffer out = new StringBuffer();

		boolean hash = true;
		int i = 0;
		while (hash) {
			if (str.charAt(i) == '#') {
				hash = false;
			} else
				out.append(str.charAt(i));
			i++;
		}

		return "\"" + out.toString() + "\"";
	}
}