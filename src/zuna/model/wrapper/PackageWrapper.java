package zuna.model.wrapper;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import zuna.db.DBConnector;
import zuna.model.Element;
import zuna.model.MyClass;
import zuna.model.MyMethod;
import zuna.model.MyPackage;

public class PackageWrapper extends Wrapper{

	public PackageWrapper(){
		super.dropTable(Wrapper.PACKAGE);
		this.createTable();
		this.createRelationTable();
	}

	protected void createRelationTable(){
		super.cleanRelationTable(Wrapper.PACKAGE_CHILDREN);
		super.cleanRelationTable(Wrapper.CLASS_CHILDREN);
	}
	
	public void putEntity(String key, Element value){
		try{
			ArrayList<String> fields = new ArrayList<String>();
			ArrayList<Object> values = new ArrayList<Object>();
			MyPackage o = (MyPackage)value;
			
			this.getFields(fields);
			this.getValues(values, o);
			
			super.saveEntity(Wrapper.PACKAGE, fields, values);
		}catch(Exception e){
			e.printStackTrace(System.err);
		}
		
	}
	
	public void addClassChild(MyPackage owner, ArrayList<MyClass> ownees) {
		super.saveRelationship(Wrapper.CLASS_CHILDREN, owner.getID(), super.convert(ownees));
	}
	
//	public static void addClassChild(MyPackage owner, ArrayList<MyClass> ownees) {
//		Wrapper.saveRelationship(Wrapper.CLASS_CHILDREN, owner.getID(), );
//	}
	
	public static void addPackageChild(MyPackage owner, MyPackage ownee) {
		Wrapper.saveRelationship(Wrapper.PACKAGE_CHILDREN, owner.getID(), ownee.getID());
	}
	
	private void getFields(ArrayList<String> fields) {
		fields.add("id");
		fields.add("se");
		fields.add("ic");
		fields.add("lib");
	}

	private void getValues(ArrayList<Object> values, MyPackage o) {
		values.add("\"" + o.getID() + "\"");
		values.add(o.getSe());
		values.add(o.getIc());
		values.add(o.isLibrary()==true? "1": "0");
	}
	
	public void getEntity(String key){
		
	}
	
	public HashMap<String, MyPackage> getEntityList(String project){
		return null;
	}

	@Override
	protected void createTable() {
		// TODO Auto-generated method stub
		try{ 
			Statement stmt = DBConnector.getConn().createStatement();
			String sql = "CREATE TABLE "+super.PACKAGE+
	                   " (ID VARCHAR(200) PRIMARY KEY     NOT NULL," +
	                   " SE           DOUBLE    NOT NULL, " + 
	                   " IC            DOUBLE     NOT NULL, " + 
	                   " LIB        BOOLEAN)";
			stmt.executeUpdate(sql);
		    stmt.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	}
}
