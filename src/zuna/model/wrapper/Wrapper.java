package zuna.model.wrapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import zuna.model.Element;

public abstract class Wrapper {
	
	protected Connection conn;
	
	protected final String OWNED_METHOD = "OWNED_METHOD";
	protected final String OWNED_FIELD = "OWNED_FIELD";
	protected final String IMPLEMENTED_CLASS = "IMPLEMENTED_CLASS";
	protected final String INTERFACE = "INTERFACE";
	protected final String CHILD_CLASS = "CHILD_CLASS";
	protected final String USES_CLASS = "USES_CLASS";
	protected final String USED_CLASS = "USED_CLASS";
	
	protected final String FAN_OUT = "FAN_OUT";
	protected final String FAN_IN = "FAN_IN";
	protected final String REFERED_FIELD = "REFERED_FIELD";
	protected final String REFERED_METHOD = "REFERED_METHOD";
	
	
	protected Wrapper(Connection conn){
		this.conn = conn;
	}
	
	protected void saveEntity(ArrayList<String> fields, ArrayList<Object> values){
		
	}

	protected ArrayList<String> convert(HashMap<String, ?> elements){
		ArrayList<String> relations = new ArrayList<String>();
		for(String o: elements.keySet()){
			Element e = (Element) elements.get(o);
			relations.add(e.getID());
		}
		
		return relations;
	}
	
	protected ArrayList<String> convert(HashSet<?> elements){
		ArrayList<String> relations = new ArrayList<String>();
		for(Object o: elements){
			Element e = (Element) o;
			relations.add(e.getID());
		}
		
		return relations;
	}
	
	protected ArrayList<String> convert(ArrayList<?> elements){
		ArrayList<String> relations = new ArrayList<String>();
		for(Object o: elements){
			Element e = (Element) o;
			relations.add(e.getID());
		}
		
		return relations;
	}
	
	protected void saveRelationships(String type, String master, ArrayList<String> relationships){
		final String tableName = type;
		PreparedStatement pstmt;
		
		for(String entityId: relationships){
			
		}
	}
	
	protected void saveRelationships(String type, String master, HashMap<String, Element> relationships){
		final String tableName = type;
		for(String relationship: relationships.keySet()){
			
		}
	}
	
	public abstract void putEntity(String key, Element value);
	public abstract void getEntity(String key);
	public abstract HashMap<String,?> getEntityList(String project);
	
}
