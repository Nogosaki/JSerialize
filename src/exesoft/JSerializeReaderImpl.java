package exesoft;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
 
/**
<<<<<<< HEAD
 * JSerializeReader - Class responsible for the mapping of the object based on the information obtained from
 * object JModel, which in decoding process (method decode) will create a String object,
 * as we can see in the example.
 *<br>
 * String to process in JModel :<br>
 * java.lang.Osoba{imie(java.lang.String):x;nazwisko(java.lang.String):y;}
 * 
=======
 * 
 *
>>>>>>> JSerialize/master
 * @author Micha³ Krakiewicz
 *
 */
@SuppressWarnings("unchecked")
public class JSerializeReaderImpl implements JSerializeReader {
 
        /**
<<<<<<< HEAD
         * Helper class for holding fields data
=======
         * 
>>>>>>> JSerialize/master
         *
         * @author Micha³
         *
         */
        static class JSONElement {
 
               
<<<<<<< HEAD
                /**
                 * Stores field name.
                 */
                private String name;
                /**
                 * Stores field type.
                 */
                private String type;
 
                /**
                 * Stores non-parsed inner objects.
                 */
                private Object innerTypes;
 
                /**
                 * @param name - a String
                 * @param type - a String
                 * @param innerTypes - non-parsed object containing inner hashmap or list
=======
                private String name;
 
                private String type;
 
                
                private Object innerTypes;
 
                /**
                 * @param name
                 * @param type
                 * @param innerTypes
>>>>>>> JSerialize/master
                 */
                public JSONElement(String name, String type, final Object innerTypes) {
 
                        this.name = name;
                        this.type = type;
                        this.innerTypes = innerTypes;
                }
 
                /**
                 * 
<<<<<<< HEAD
                 * @return Get parsed object, or class member name.
=======
                 *
                 * @return
>>>>>>> JSerialize/master
                 */
                public String getName() {
                        return name;
                }
 
                /**
<<<<<<< HEAD
                 * 
                 * @return Get string containing object type to be created.
=======
                 *
                 *
                 * @return
>>>>>>> JSerialize/master
                 */
                public String getType() {
                        return type;
                }
 
                /**
<<<<<<< HEAD
                 * Set new object type to be created.
                 *
                 * @param newtype - object string
=======
                 * 
                 *
                 * @param newtype
>>>>>>> JSerialize/master
                 */
                public void setType(String newtype) {
                        type = newtype;
                }
 
                /**
<<<<<<< HEAD
                 * Helper function to cast as Map<>.
                 *
                 * @return Get object as Map.
=======
                 * 
                 *
                 * @return
>>>>>>> JSerialize/master
                 */
                public Map<String, Object> getAsMap() {
                        return ((Map<String, Object>) innerTypes);
                }
 
                /**
<<<<<<< HEAD
                 * Helper function to cast as List.
                 *
                 * @return Get object as List.
=======
                 * 
                 *
                 * @return
>>>>>>> JSerialize/master
                 */
                public List<Object> getAsList() {
                        return (List<Object>) innerTypes;
                }
 
                /**
<<<<<<< HEAD
                 * Helper function that returns supposed raw Map string (name#type)
                 *
                 * @return Get string as hashmap key.
=======
                 * 
                 *
                 * @return
>>>>>>> JSerialize/master
                 */
                public String getStringWithHash() {
                        return new String(name + '#' + type);
                }
 
                /**
<<<<<<< HEAD
                 * Access inner object without casting.
                 *
                 * @return Get non-parser object containing inner hashmap or list.
=======
                 *
                 *
                 * @return
>>>>>>> JSerialize/master
                 */
                public Object getInner() {
                        return innerTypes;
                }
 
        }
 
<<<<<<< HEAD
        /**
         * Class member holding the deserialized object.
         */
        private Object deserializedObject;
 
        /**
         * Object hash map.
         *
         */
        private Map<String, Object> objectHashMap;
 
        /**
         * Helper flag that, if true, produce debug messages
         */
        private static boolean debug = true;
 
        /**
         * Helper method to output debug info if in debug mode.
         *
         * @param msg - a string displays the statements.
=======
       
        private Object deserializedObject;
 
      
        private Map<String, Object> objectHashMap;
 
        
        private static boolean debug = true;
 
        /**
         * 
         *
         * @param msg
>>>>>>> JSerialize/master
         */
        private static void dbg(String msg) {
 
                if (debug) {
                        System.err.println(msg);
                }
 
        }
 
<<<<<<< HEAD
        /**
         * Constant that defines map key containing main class type.
         */
        private static final String rootClassKey = "#JSerializeMetaData#RootClassName";
 
        /**
         * Creates the object from hashmap using java reflection.
         *
=======
       
        private static final String rootClassKey = "#JSerializeMetaData#RootClassName";
 
        /**
         * 
         *
         * (non-Javadoc).
         *@param map
>>>>>>> JSerialize/master
         * @see exesoft.JSerializeReader#fromMap(java.util.Map)
         */
        @Override
        public Object fromMap(Map<String, Object> map) {
 
                dbg("Started creating object");
 
                JSONElement rootClass = processClassRoot(map);
 
                dbg("Class name: " + rootClass.getType());
 
                deserializedObject = null;
 
                try {
                        deserializedObject = createObject(rootClass);
                } catch (ClassNotFoundException e) {
<<<<<<< HEAD
=======
                        // TODO Auto-generated catch block
>>>>>>> JSerialize/master
                        e.printStackTrace();
                }
 
                return deserializedObject;
 
        }
 
        /**
<<<<<<< HEAD
        *
        * Helper function that decodes List type Hashmaps.
        * @param encoded - JSONElement to be encoded.
        * @param field - Field object of type of field to be encoded.
        * @return Get a List.
        */
=======
         *
         * 
         *
         * @param encoded
         * @param field
         * @return
         */
>>>>>>> JSerialize/master
        protected List<Object> decodeList(final JSONElement encoded, final Field field) {
 
                ArrayList<JSONElement> elementDataArr = decodeHashMapKeys(encoded
                                .getAsMap());
 
                JSONElement elementData = elementDataArr.get(0);
 
                Type type = field.getGenericType();
 
                ParameterizedType pt = (ParameterizedType) type;
 
                Type t = pt.getActualTypeArguments()[0];
                String tmp = t.toString();
 
                int space = tmp.indexOf(' ');
 
                String className = tmp.substring(space + 1, tmp.length());
 
                if (debug) {
                        dbg("Name: " + className);
                        System.out.println("type: " + type);
                        System.out.println("raw type: " + pt.getRawType());
 
                }
 
                List<HashMap<String, Object>> hashmaps = (List<HashMap<String, Object>>) elementData.getInner();
                List<Object> returnList = new ArrayList<>();
                try {
                        for (HashMap<String,Object> map : hashmaps) {
 
                                if (map.get("0") == null) {
                                        JSONElement object = new JSONElement("elementData",
                                                        className, decodeHashMapKeys(map));
 
                                        if (object != null) {
                                                returnList.add(createObject(object));
                                        
                                        }
                                }
 
                        }
                } catch (ClassNotFoundException e) {
                       
                        e.printStackTrace();
                }
 
            
 
                return returnList;
 
        }
 
        /**
<<<<<<< HEAD
         * Helper function that converts List of type Integer to int[] array
         * @param list - list of integer number.
         * @return Get a array of integer.
=======
         * 
         *
         * @param list
         * @return
>>>>>>> JSerialize/master
         */
        private int[] toIntArray(List<Integer> list) {
                int[] ret = new int[list.size()];
                int i = 0;
                for (Integer e : list) {
                        ret[i++] = e.intValue();
                }
                return ret;
        }
 
        /**
<<<<<<< HEAD
         * Helper function that creates Object of type specified in JSONElement object.
         * @param elem - JSONELement to be deserialied.
         * @return members object.
         * @throws ClassNotFoundException Thrown when an application tries to load in a class through its string name using:
					<br>- The forName method in class Class.
					<br>- The findSystemClass method in class ClassLoader .
					<br>- The loadClass method in class ClassLoader.
=======
         * 
         *
         * @param elem
         * @return
         * @throws ClassNotFoundException
>>>>>>> JSerialize/master
         */
        protected Object createObject(final JSONElement elem)
                        throws ClassNotFoundException {
 
                try {
 
                        switch (elem.getType()) {
                        case "intArray":
                              
                                int[] tmp = toIntArray((List<Integer>) elem.getInner());
                                return Arrays.copyOf(tmp, tmp.length);
                        case "charArray":
                                return elem.getInner();
                        case "0":
                                return "0";
                               
 
                        }
 
                        Class<?> deserialized = Class.forName(elem.getType());
 
                        Object deserializedInstance = deserialized.newInstance();
 
                        Object innerTypes = elem.getInner();
 
                        if (innerTypes != null) {
 
                                ArrayList<JSONElement> members = null;
 
                                if (innerTypes instanceof Map<?, ?>) {
                                        members = decodeHashMapKeys((Map<String, Object>) innerTypes);
                                }
                                else if (innerTypes instanceof List) {
                                        members = (ArrayList<JSONElement>) innerTypes;
                                }
 
                                for (JSONElement jsonElement : members) {
                                        dbg("Deserializing member: " + jsonElement.getType() + " "
                                                        + jsonElement.getName());
                                       
                                        Field field = deserialized.getDeclaredField(jsonElement
                                                        .getName());
                                        field.setAccessible(true);
 
                               
                                        
 
                                        if (jsonElement.getType().equals(List.class.getName())) {
 
                                                List<Object> l = decodeList(jsonElement, field);
                                                field.set(deserializedInstance, l);
                                        } else if (jsonElement.getType().equals(
                                                        String.class.getName())) {
 
                                                ArrayList<JSONElement> tmp = decodeHashMapKeys(jsonElement
                                                                .getAsMap());
                                                List<Object> inner = (List<Object>) createObject(tmp.get(0));
 
                                                StringBuilder sb = new StringBuilder(inner.size());
                                                for (Object c_ : inner) {
                                                        sb.append(c_);
                                                }
                                                String result = sb.toString();
 
                                                String value = result;
 
                                                field.set(deserializedInstance, value);
                                        } else {
 
                                                Object tmp = createObject(jsonElement);
 
                                                field.set(deserializedInstance, tmp);
                                                dbg("else");
                                               
                                        }
                                        
 
                                        dbg(" ");
 
                                }
 
                        } else {
                                throw new InvalidParameterException(elem.toString()
                                                + " has no innerTypes!\n");
                        }
 
                        return deserializedInstance;
 
                } catch (ClassNotFoundException e) {
                        throw new ClassNotFoundException("Class " + elem.getType()
                                        + "not found!\n");
                } catch (NoSuchFieldException e) {
                       
                        e.printStackTrace();
                } catch (SecurityException e) {
                        
                        e.printStackTrace();
                } catch (InstantiationException e) {
                        
                        e.printStackTrace();
                } catch (IllegalAccessException e) {
                       
                        e.printStackTrace();
                }
 
                return null;
 
        }
 
        /**
<<<<<<< HEAD
         * Reads the object from input stream.
         *@param input - read objects.
=======
         * 
         *
>>>>>>> JSerialize/master
         * @see exesoft.JSerializeReader#readObject(java.io.InputStream)
         */
        @Override
        public Boolean readObject(final InputStream input) {
                JModel parser = new JModelImpl();
 
<<<<<<< HEAD
                /**
                 * Convert InputStream to string:
                 */
                // Create BufferedReader object
=======
               
              
>>>>>>> JSerialize/master
                BufferedReader bReader = new BufferedReader(
                                new InputStreamReader(input));
 
                StringBuffer sbf = new StringBuffer();
                String line = null;
 
<<<<<<< HEAD
                // read line by line
=======
                
>>>>>>> JSerialize/master
                try {
                        while ((line = bReader.readLine()) != null) {
                                sbf.append(line);
                        }
 
                } catch (IOException e) {
                        e.printStackTrace();
                        return false;
                }
 
                objectHashMap = parser.decode(sbf.toString());
 
                if (objectHashMap == null) {
                        return false;
                }
 
                return true;
        }
 
        /**
<<<<<<< HEAD
         * Helper function that decodes Map into list of JSONElements. Inner elements are to be parsed further.
         * @param map - map type of string
         * @return Get decoded list object type of JSONElements.
=======
         *
         *
         * @param map
         * @return
>>>>>>> JSerialize/master
         */
        protected static ArrayList<JSONElement> decodeHashMapKeys(
                        final Map<String, Object> map) {
 
                ArrayList<JSONElement> tmp = new ArrayList<>();
 
                Set<String> keys = map.keySet();
 
                if (keys.size() == 0) {
                        // empty hashmap?
                        return null;
                }
 
                Iterator<String> i = keys.iterator();
 
                while (i.hasNext()) {
 
                        String full_string = i.next();
 
                        int hash_index = full_string.indexOf('#');
 
                        if (hash_index == -1) {
 
                                if (full_string.equals("0")) {
                                        Object inner = map.get(full_string);
                                        if (((String) inner).equals("0")) {
                                                tmp.add(new JSONElement("0", "0", null));
                                                continue;
                                        }
                                }
 
                                throw new java.security.InvalidParameterException('"'
                                                + full_string + '"'
                                                + " doesn't contain a hash (#) in hashmap name: \n"
                                                + map.toString());
                        }
                        String name = full_string.substring(0, hash_index);
                        String type = full_string.substring(hash_index + 1,
                                        full_string.length());
 
                        Object inner = map.get(full_string);
 
                        
 
                        tmp.add(new JSONElement(name, type, inner));
 
                }
 
                return tmp;
 
        }
 
        /**
<<<<<<< HEAD
         * @param map - map type of string
         * @return JSONElement with name of the class, and innerTypes containing the
         *         map without class name map entry as 'JSerializeMetaData#RootClassName'
=======
         * @param map
         * @return JSONElement with name of the class, and innerTypes containing the
         *         map without class name map entry as
         *         'JSerializeMetaData#RootClassName'
>>>>>>> JSerialize/master
         */
        protected static JSONElement processClassRoot(final Map<String, Object> map) {
                Object tmp = null;
                tmp = map.get(rootClassKey);
 
                String type = (String) tmp;
 
                if ((type == null) || type.isEmpty()) {
                        throw new InvalidParameterException("Hashmap: \n" + map.toString()
                                        + "\ndoesn't contain class name (" + rootClassKey + ") !");
                }
 
                map.remove(rootClassKey);
 
                return new JSONElement(rootClassKey, type, map);
        }
 
}