package zuna.model.wrapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
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
	
	protected final String CLASS = "CLASS";
	protected final String METHOD = "METHOD";
	protected final String FIELD = "FIELD";
	protected final String PACKAGE = "PACKAGE";
	protected final String PARAMETER = "PARAMETER";
	protected final String IDENTIFIER = "IDENTIFIER";
	
	protected final String CLASS_RELATION = "CLASS_RELATION";
	protected final String METHOD_RELATION = "METHOD_RELATION";
	protected final String FIELD_RELATION = "FIELD_RELATION";
	protected final String PACKAGE_RELATION = "PACKAGE_RELATION";
	
	protected final String PACKAGE_CHILDREN = "PCAKGE_CHILDREN";
	protected final String CLASS_CHILDREN = "CLASS_CHILDREN";
	
	protected Wrapper(Connection conn){
		this.conn = conn;
	}
	
	protected void saveEntity(String type, ArrayList<String> fields, ArrayList<Object> values){
		final String tableName = type;
		try{
			Statement stmt = conn.createStatement();
			StringBuffer sb_f = new StringBuffer();
			StringBuffer sb_v = new StringBuffer();
			sb_f.append(" insert into " + tableName + "(");
		    for(int idx = 0 ; idx < fields.size(); idx++)
		    {
		    	sb_f.append(fields.get(idx) + ", ");
		    }
		    sb_f.append(") ");
		    
		    sb_v.append(" values (");
		    for(int idx = 0 ; idx < values.size(); idx++)
		    {
		    	sb_v.append(values.get(idx) + ", ");
		    }
		    sb_f.append(") ");

		    String sql = sb_f.append(sb_v).toString();
		    stmt.executeQuery(sql);
		    stmt.close();
		    
	    }catch(Exception e){
	     System.out.println("error -- " + e.getMessage());
	    }
	}	
	
	
	protected void saveRelationships(String type, String master, ArrayList<String> relatedTo){
		final String tableName = type;
		try{
			PreparedStatement pstmt = null;
			StringBuffer sb = new StringBuffer();
			sb.append(" insert into " + tableName + "(master, slave)");
			sb.append(" values (?,?)");
			pstmt = conn.prepareStatement(sb.toString());
			
		    for(String entityId: relatedTo){
		    	pstmt.setString(0, master);
		    	pstmt.setString(1, entityId);
		    	pstmt.addBatch();
		    }
		    pstmt.executeBatch();
		    pstmt.close();
		          
	    }catch(Exception e){
	     System.out.println("error -- " + e.getMessage());
	    }
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
	
	protected abstract void dropTable();
	protected abstract void createTable();
	public abstract void putEntity(String key, Element value);
	public abstract void getEntity(String key);
	public abstract HashMap<String,?> getEntityList(String project);
	
}
