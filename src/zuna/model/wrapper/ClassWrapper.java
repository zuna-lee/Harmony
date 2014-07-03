package zuna.model.wrapper;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import zuna.model.Element;
import zuna.model.MyClass;
import zuna.model.MyField;
import zuna.model.MyMethod;
import zuna.model.MyPackage;

public class ClassWrapper extends Wrapper{

	public ClassWrapper(Connection conn){
		super(conn);
	}
	
	public void putEntity(String key, Element entity){
		try{
			ArrayList<String> fields = new ArrayList<String>();
			ArrayList<Object> values = new ArrayList<Object>();
			MyClass o = (MyClass)entity;
			this.getFields(fields);
			this.getValues(values, o);
			
			super.saveEntity(super.CLASS, fields, values);
			super.saveRelationships(super.OWNED_METHOD, o.getID(), super.convert(o.getOwnedMethods()));
			super.saveRelationships(super.OWNED_FIELD, o.getID(), super.convert(o.getOwendField()));
			super.saveRelationships(super.USES_CLASS, o.getID(), super.convert(o.getUseClasses()));
			super.saveRelationships(super.USED_CLASS, o.getID(), super.convert(o.getUsedClasses()));
			super.saveRelationships(super.INTERFACE, o.getID(), super.convert(o.getInterface()));
			super.saveRelationships(super.IMPLEMENTED_CLASS, o.getID(), super.convert(o.getImplementedClasses()));
			super.saveRelationships(super.CHILD_CLASS, o.getID(), super.convert(o.getChildClasses()));
			
		}catch (Exception e){
			e.printStackTrace(System.err);
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

	}

	private void getValues(ArrayList<Object> values, MyClass o) {
		values.add("\"" + o.getID() + "\"");
		values.add(o.getSe());
		values.add(o.getIc());
		values.add(o.isAbstract());
		values.add(o.isLibrary());
		values.add("\"" + o.getOutterClassUri()+ "\"");
		values.add(o.getNoOfCalls());
		values.add(o.getIcIninheritance());
		values.add(o.isCompletelyCohesive());
		values.add(o.isEnumuration());
		values.add(o.isInterface());
		
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
	
}