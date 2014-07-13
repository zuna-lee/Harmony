package zuna.model.wrapper;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import zuna.db.DBConnector;
import zuna.model.Element;
import zuna.model.MyField;
import zuna.model.MyMethod;

public class MethodWrapper extends Wrapper{

	public MethodWrapper(){
		super.dropTable(super.METHOD);
		this.createTable();
		this.createRelationTable();
	}
	
	protected void createRelationTable(){
		
		super.cleanRelationTable(super.FAN_IN);
		super.cleanRelationTable(super.FAN_OUT);
		super.cleanRelationTable(super.REFERED_FIELD);
		super.cleanRelationTable(super.OWNED_PARAMETER);
		
	}
	public void putEntity(String key, Element value){
		try{
			ArrayList<String> fields = new ArrayList<String>();
			ArrayList<Object> values = new ArrayList<Object>();
			MyMethod o = (MyMethod)value;
			this.getFields(fields);
			this.getValues(values, o);
			
			super.saveEntity(super.METHOD, fields, values);
			
//			super.saveRelationships(super.FAN_IN, o.getID(), super.convert(o.getFanIn()));
//			super.saveRelationships(super.FAN_OUT, o.getID(), super.convert(o.getFanOut()));
//			super.saveRelationships(super.REFERED_FIELD, o.getID(), super.convert(o.getReferencedField()));
//			super.saveRelationships(super.OWNED_PARAMETER, o.getID(), super.convert(o.getParameters()));
			
		}catch(Exception e){
			e.printStackTrace(System.err);
		}
	}

	public void addReferredField(MyMethod owner, ArrayList<MyField> ownees) {
		super.saveRelationship(Wrapper.REFERED_FIELD, owner.getID(), super.convert(ownees));
	}
	
	public void addFanoutMethod(MyMethod owner, ArrayList<MyMethod> ownees) {
		super.saveRelationship(Wrapper.FAN_OUT, owner.getID(), super.convert(ownees));
	}
	
	private void getValues(ArrayList<Object> values, MyMethod o) {
		
		int access = -1;
		
		values.add("\"" + o.getID() + "\"");
		values.add(o.getSe());
		values.add(o.getIc());
		values.add(o.isLibrary()==true? "1": "0");
		
		values.add("\"" + o.getName() + "\"");
		values.add(o.isAbstract()==true? "1": "0");
		values.add(o.isSupport()==true? "1": "0");
		values.add("\"" + o.getParent().getID() + "\"");
		values.add("\"" + o.getIdentifier() + "\"");
		values.add(o.getStatementCnt());
		values.add(o.isOverride()==true? "1": "0");
		values.add(o.isConstructor()==true? "1": "0");
		values.add(o.isImplemented()==true? "1": "0");
		values.add(o.isStatic()==true? "1": "0");
		values.add(o.getNoOfParams());
		values.add(o.isCallToParent()==true? "1": "0");
		
		if(o.isPrivate()) access = 0;
		else if(o.isProtected()) access = 1;
		else access = 2;
		
		values.add(access);
	}
	
	public void getEntity(String key){
		
	}
	
	
	private void getFanoutMethods(){
		
	}
	
	private void getFaninMethods(){
		
	}

	private void getReferredFields(){
		
	}
	
	
	public HashMap<String, MyMethod> getEntityList(String project){
		return null;
	}
	


	private void getFields(ArrayList<String> fields) {
		
		fields.add("id");
		fields.add("se");
		fields.add("ic");
		fields.add("lib");
		
		fields.add("name");
		fields.add("abstract");
		fields.add("isSupport");
		fields.add("parent");
		fields.add("identifier");
		fields.add("statementCnt");
		fields.add("override");
		fields.add("constructor");
		fields.add("implemented");
		fields.add("static");
		fields.add("noOfParams");
		fields.add("call2Parent");
		fields.add("access");
	}
	
	@Override
	protected void createTable() {
		// TODO Auto-generated method stub
		try{ 
			Statement stmt = DBConnector.getConn().createStatement();
			String sql = "CREATE TABLE "+super.METHOD+
	                   " (ID VARCHAR(200) PRIMARY KEY     NOT NULL," +
	                   " SE           DOUBLE    NOT NULL, " + 
	                   " IC            DOUBLE     NOT NULL, " + 
	                   " LIB        BOOLEAN, " +
	                   
	                   " name VARCHAR(200),  " + 
	                   " abstract BOOLEAN,  " +
	                   " isSupport BOOLEAN,  " +
	                   " parent VARCHAR(200),  " +
	                   " identifier VARCHAR(200),  " +
	                   " statementCnt int,  " +
	                   " override BOOLEAN,  " +
	                   " constructor BOOLEAN,  " +
	                   " implemented BOOLEAN, " +
	                   " static BOOLEAN,  " +
	                   " noOfParams INT,  " +
	                   " call2Parent BOOLEAN,  " +
	                   " access INT " +
	                   ")";
			stmt.executeUpdate(sql);
		    stmt.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	}
}
