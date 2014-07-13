package zuna.model.wrapper;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import zuna.db.DBConnector;
import zuna.model.Element;
import zuna.model.MyClass;
import zuna.model.MyField;
import zuna.model.MyMethod;
import zuna.model.MyPackage;

public class ClassWrapper extends Wrapper{

	public ClassWrapper(){
		super.dropTable(Wrapper.CLASS);
		this.createTable();
		this.createRelationTable();
	}

	protected void createRelationTable(){
		super.cleanRelationTable(Wrapper.OWNED_METHOD);
		super.cleanRelationTable(Wrapper.OWNED_FIELD);
		super.cleanRelationTable(Wrapper.USED_CLASS);
		super.cleanRelationTable(Wrapper.USES_CLASS);
		super.cleanRelationTable(Wrapper.CLASS_CHILDREN);
		super.cleanRelationTable(Wrapper.IMPLEMENTED_CLASS);
		super.cleanRelationTable(Wrapper.INTERFACE);
	}
	
	protected void createTable(){
		try{ 
			Statement stmt = DBConnector.getConn().createStatement();
			String sql = "CREATE TABLE "+Wrapper.CLASS+
	                   " (id VARCHAR(200) PRIMARY KEY     NOT NULL," +
	                   " se           DOUBLE    NOT NULL, " + 
	                   " ic            DOUBLE     NOT NULL, " + 
	                   " isAbstract        BOOLEAN, " + 
	                   " lib        BOOLEAN, " +
	                   " outterClassUri        VARCHAR(200), " +
	                   " noOfCalls        DOUBLE, " +
	                   " icInheritance        DOUBLE, " +
	                   " ISCOMPLETELYCOHESIVE        BOOLEAN, " +
	                   " isEnum        BOOLEAN, " +
	                   " isInterface        BOOLEAN, " +
	                   " superClass        VARCHAR(200))";
			stmt.executeUpdate(sql);
		    stmt.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
	}
	
	private void getFields(ArrayList<String> fields) {
		
		fields.add("id");
		fields.add("se");
		fields.add("ic");
		fields.add("isAbstract");
		fields.add("lib");
		fields.add("outterClassUri");
		fields.add("noOfCalls");
		fields.add("icInheritance");
		fields.add("isCompletelyCohesive");
		fields.add("isEnum");
		fields.add("isInterface");
		fields.add("superClass");
	}

	private void getValues(ArrayList<Object> values, MyClass o) {
		values.add("\"" + o.getID() + "\"");
		values.add(o.getSe());
		values.add(o.getIc());
		values.add(o.isAbstract()==true? "1": "0");
		values.add(o.isLibrary()==true? "1": "0");
		values.add("\"" + o.getOutterClassUri()+ "\"");
		values.add(o.getNoOfCalls());
		values.add(o.getIcIninheritance());
		values.add(o.isCompletelyCohesive()==true? "1": "0");
		values.add(o.isEnumuration()==true? "1": "0");
		values.add(o.isInterface()==true? "1": "0");
		values.add("\"" + "java.lang.Object" + "\"");
	}
	
	public void putEntity(String key, Element entity){
		try{
			ArrayList<String> fields = new ArrayList<String>();
			ArrayList<Object> values = new ArrayList<Object>();
			MyClass o = (MyClass)entity;
			this.getFields(fields);
			this.getValues(values, o);
			
			super.saveEntity(Wrapper.CLASS, fields, values);
			
		}catch (Exception e){
			e.printStackTrace(System.err);
		}
	}
	
	public void getEntity(String key){
		
	}
	
	public ArrayList<MyClass> getImplementedClasses(String key){
		return null;
	}

	public ArrayList<MyClass> getInterfaces(String key){
		return null;
	}

	public ArrayList<MyClass> getChildClasses(String key){
		return null;
	}

	public HashSet<MyClass> getUsesClasses(String key){
		return null;
	}
	
	public HashSet<MyClass> getUsedClasses(String key){
		return null;
	}

	public ArrayList<MyMethod> getOwnedMethods(String key){
		return null;
	}
	
	public ArrayList<MyField> getOwnedFields(String key){
		return null;
	}
	
	public MyPackage getParentPackage(String key){
		return null;
	}
	
	public MyClass getSuperClass(String key){
		return null;
	}
	
	public HashMap<String, MyClass> getEntityList(String project){
		return null;
	}

	public void addMethod(MyClass owner, ArrayList<MyMethod> ownees) {
		super.saveRelationship(Wrapper.OWNED_METHOD, owner.getID(), super.convert(ownees));
	}
	
	public void updateSuperClass(MyClass c, MyClass superClass) {
		super.update(Wrapper.CLASS, c.getID(), superClass.getID());
	}
	
	public void addField(MyClass owner, ArrayList<MyField> ownees) {
		super.saveRelationship(Wrapper.OWNED_FIELD, owner.getID(), super.convert(ownees));
	}

	public void addUsesClasses(MyClass owner, ArrayList<MyClass> ownees) {
		super.saveRelationship(Wrapper.USES_CLASS, owner.getID(), super.convert(ownees));
	}
	
	public void addInterfaces(MyClass c, ArrayList<MyClass> interfaces) {
		super.saveRelationship(Wrapper.INTERFACE, c.getID(), super.convert(interfaces));
	}
	
}