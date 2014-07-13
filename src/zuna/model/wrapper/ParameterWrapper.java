package zuna.model.wrapper;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import zuna.db.DBConnector;
import zuna.model.Element;
import zuna.model.MyParameter;

public class ParameterWrapper  extends Wrapper{

	public ParameterWrapper(){
		super.dropTable(super.PARAMETER);
		this.createTable();
	}
	
	public void putEntity(String key, Element value){
		try{
			ArrayList<String> fields = new ArrayList<String>();
			ArrayList<Object> values = new ArrayList<Object>();
			MyParameter o = (MyParameter)value;
			
			this.getFields(fields);
			this.getValues(values, o);
			super.saveEntity(super.PARAMETER, fields, values);
			
		}catch(Exception e){
			e.printStackTrace(System.err);
		}
	}
	
	private void getFields(ArrayList<String> fields) {
		fields.add("id");
		fields.add("type");
		fields.add("parent");
	}

	private void getValues(ArrayList<Object> values, MyParameter o) {
		values.add("\"" + o.getName() + "\"");
		values.add("\"" + o.getType() + "\"");
		values.add("\"" + o.getParent().getID() + "\"");
	}
	
	public void getEntity(String key){
		
	}
	
	public HashMap<String, MyParameter> getEntityList(String project){
		return null;
	}

	@Override
	protected void createTable() {
		// TODO Auto-generated method stub
		try{ 
			Statement stmt = DBConnector.getConn().createStatement();
			String sql = "CREATE TABLE "+super.PARAMETER+
	                   " (id VARCHAR(200) PRIMARY KEY     NOT NULL," +
	                   " type           VARCHAR(200)    NOT NULL, " + 
	                   " parent            VARCHAR(200)     NOT NULL)";
			stmt.executeUpdate(sql);
		    stmt.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	}
	
}
