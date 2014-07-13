package zuna.model.wrapper;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import zuna.db.DBConnector;
import zuna.model.Element;

public abstract class Wrapper {

	protected static final String CLASS = "CLASS";
	protected static final String METHOD = "METHOD";
	protected static final String FIELD = "FIELD";
	protected static final String PACKAGE = "PACKAGE";
	protected static final String PARAMETER = "PARAMETER";
	protected static final String IDENTIFIER = "IDENTIFIER";
	
	protected static final String OWNED_METHOD = "OWNED_METHOD";
	protected static final String OWNED_FIELD = "OWNED_FIELD";
	protected static final String IMPLEMENTED_CLASS = "IMPLEMENTED_CLASS";
	protected static final String INTERFACE = "INTERFACE";
	protected static final String CHILD_CLASS = "CHILD_CLASS";
	protected static final String USES_CLASS = "USES_CLASS";
	protected static final String USED_CLASS = "USED_CLASS";
	
	protected static final String FAN_OUT = "FAN_OUT";
	protected static final String FAN_IN = "FAN_IN";
	protected static final String REFERED_FIELD = "REFERED_FIELD";
	protected static final String REFERED_METHOD = "REFERED_METHOD";
	protected static final String OWNED_PARAMETER = "OWNED_PARAMETER";
	
	protected static final String PACKAGE_CHILDREN = "PCAKGE_CHILDREN";
	protected static final String CLASS_CHILDREN = "CLASS_CHILDREN";
	
	
	protected void saveEntity(String type, ArrayList<String> fields, ArrayList<Object> values){
		final String tableName = type;
		try{
			Statement stmt = DBConnector.getConn().createStatement();
			StringBuffer sb_f = new StringBuffer();
			StringBuffer sb_v = new StringBuffer();
			sb_f.append(" insert into " + tableName + "(");
		    for(int idx = 0 ; idx < fields.size(); idx++)
		    {
		    	if(idx==fields.size()-1){
		    		sb_f.append(fields.get(idx) + ") ");
		    	}else{
		    		sb_f.append(fields.get(idx) + ", ");
		    	}
		    	
		    }
		    
		    sb_v.append(" values (");
		    for(int idx = 0 ; idx < values.size(); idx++)
		    {
		    	if(idx==values.size()-1){
		    		sb_v.append(values.get(idx) + ") ");
		    	}else{
		    		sb_v.append(values.get(idx) + ", ");
		    	}
		    	
		    }
		    
		    String sql = sb_f.append(sb_v).toString();
		    stmt.execute(sql);
		    stmt.close();
		    
	    }catch(Exception e){
//	    	System.out.println("error ---- " + tableName + "/" + e.getMessage());
	    }
	}	
	
	protected void cleanRelationTable(String tableName){
		this.dropTable(tableName);
		this.createRelationTable(tableName);
	}
	
	protected static void saveRelationship(String type, String owner, String ownee){
		final String tableName = type;
		try{
			Statement stmt = DBConnector.getConn().createStatement();
			StringBuffer sb = new StringBuffer();
			sb.append(" insert into " + tableName + "(master, slave) ");
			sb.append(" values (\'"+owner+"\', \'"+ ownee+"\')");
		    
		    String sql = sb.toString();
		    stmt.execute(sql);
		    stmt.close();
		    
	    }catch(Exception e){
	    	System.out.println("error ---- " + tableName + "/" + e.getMessage());
	    }
	}
	
	protected void saveRelationship(String type, String owner, ArrayList<String> ownees){
		final String tableName = type;
		try{
			String sql = " insert into " + tableName + "(master, slave) values (?, ?)";
			PreparedStatement pstmt = DBConnector.getConn().prepareStatement(sql);
			
			for(String ownee: ownees){
				pstmt.setString(1, owner);
				pstmt.setString(2, ownee);
				pstmt.addBatch();
			}
			
			pstmt.executeBatch();
			pstmt.close();
		    
	    }catch(Exception e){
	    	System.out.println("error ---- " + tableName + "/" + e.getMessage());
	    }
	}
	
	protected void saveRelationshipInverse(String type, String owner, ArrayList<String> ownees){
		final String tableName = type;
		try{
			String sql = " insert into " + tableName + "(master, slave) values (?, ?)";
			PreparedStatement pstmt = DBConnector.getConn().prepareStatement(sql);
			
			for(String ownee: ownees){
				pstmt.setString(1, ownee);
				pstmt.setString(2, owner);
				pstmt.addBatch();
			}
			
			pstmt.executeBatch();
			pstmt.close();
		    
	    }catch(Exception e){
	    	System.out.println("error ---- " + tableName + "/" + e.getMessage());
	    }
	}
	
	
	protected void dropTable(String tableName){
		try{ 
			Statement stmt = DBConnector.getConn().createStatement();
			String sql = "Drop TABLE  "+tableName;
			stmt.executeUpdate(sql);
		    stmt.close();
	    } catch ( java.sql.SQLException e ) {
	    	
	    }
	}
	private void createRelationTable(String tableName){
		try{ 
			Statement stmt = DBConnector.getConn().createStatement();
			String sql = "CREATE TABLE "+tableName+
	                   " (master varchar(200),"
	                   + "slave varchar(200))";
			stmt.executeUpdate(sql);
		    stmt.close();
	    } catch ( Exception e ) {
	      System.err.println("==="+ e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
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
			o.getClass().getName();
			Element e = (Element) o;
			relations.add(e.getID());
		}
		
		return relations;
	}
	
	protected abstract void createTable();
	public abstract void putEntity(String key, Element value);
	public abstract void getEntity(String key);
	public abstract HashMap<String,?> getEntityList(String project);

	public void update(String tableName, String condition, String updateValue) {
		try{ 
			Statement stmt = DBConnector.getConn().createStatement();
			String sql = "Update " + tableName +
	                   " set superClass = \'"+ updateValue  + "\'" +
	                   " where id = \'" + condition + "\'";
			stmt.executeUpdate(sql);
		    stmt.close();
	    } catch ( Exception e ) {
	      System.err.println("==="+ e.getClass().getName() + ": " + e.getMessage() );
	      e.printStackTrace();
	    }
	}
	
}
