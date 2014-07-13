package zuna.model.wrapper;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import zuna.db.DBConnector;
import zuna.model.Element;
import zuna.model.MyField;
import zuna.model.MyMethod;

public class FieldWrapper extends Wrapper{

	public FieldWrapper(){
		super.dropTable(super.FIELD);
		this.createTable();
		this.createRelationTable();
	}

	protected void createRelationTable(){
		super.cleanRelationTable(super.REFERED_METHOD);
	}
	
	public void putEntity(String key, Element value){
		try{
			ArrayList<String> fields = new ArrayList<String>();
			ArrayList<Object> values = new ArrayList<Object>();
			MyField o = (MyField)value;
			this.getFields(fields);
			this.getValues(values, o);
			super.saveEntity(super.FIELD, fields, values);
		}catch(Exception e){
			e.printStackTrace(System.out);
		}
	}
	
	
	private void getFields(ArrayList<String> fields) {
		fields.add("id");
		fields.add("se");
		fields.add("ic");
		fields.add("lib");
		
		fields.add("type");
		fields.add("parent");
	}

	private void getValues(ArrayList<Object> values, MyField o) {
		values.add("\"" + o.getID() + "\"");
		values.add(o.getSe());
		values.add(o.getIc());
		values.add(o.isLibrary()==true? "1": "0");
		
		values.add("\"" + o.getType() + "\"");
		values.add("\"" + o.getParent().getID() + "\"");
	}
	
	public void getEntity(String key){
		
	}
	
	public HashMap<String, MyField> getEntityList(String project){
		return null;
	}


	@Override
	protected void createTable() {
		// TODO Auto-generated method stub
		try{ 
			Statement stmt = DBConnector.getConn().createStatement();
			String sql = "CREATE TABLE "+super.FIELD+
	                   " (ID VARCHAR(200) PRIMARY KEY     NOT NULL," +
	                   " SE           DOUBLE    NOT NULL, " + 
	                   " IC            DOUBLE     NOT NULL, " + 
	                   " LIB        BOOLEAN, " +
	                   
	                   " TYPE        VARCHAR(200), " +
	                   " PARENT        VARCHAR(200) " +
	                   ")";
			stmt.executeUpdate(sql);
		    stmt.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	}
}
