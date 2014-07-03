package zuna.model.wrapper;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import zuna.model.Element;
import zuna.model.MyMethod;

public class MethodWrapper extends Wrapper{

	public MethodWrapper(Connection conn){
		super(conn);
	}
	
	public void putEntity(String key, Element value){
		try{
			ArrayList<String> fields = new ArrayList<String>();
			ArrayList<Object> values = new ArrayList<Object>();
			MyMethod o = (MyMethod)value;
			this.getFields(fields);
			this.getValues(values, o);
			
			super.saveRelationships(super.FAN_IN, o.getID(), super.convert(o.getFanIn()));
			super.saveRelationships(super.FAN_OUT, o.getID(), super.convert(o.getFanOut()));
			super.saveRelationships(super.REFERED_FIELD, o.getID(), super.convert(o.getReferencedField()));
			super.saveRelationships(super.PARAMETER, o.getID(), super.convert(o.getParameters()));
			
		}catch(Exception e){
			e.printStackTrace(System.err);
		}
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

	private void getValues(ArrayList<Object> values, MyMethod o) {
		
		int access = -1;
		
		values.add("\"" + o.getID() + "\"");
		values.add(o.getSe());
		values.add(o.getIc());
		values.add(o.isLibrary());
		
		values.add("\"" + o.getName() + "\"");
		values.add(o.isAbstract());
		values.add(o.isSupport());
		values.add("\"" + o.getParent().getID() + "\"");
		values.add("\"" + o.getIdentifier() + "\"");
		values.add(o.getStatementCnt());
		values.add(o.isOverride());
		values.add(o.isConstructor());
		values.add(o.isImplemented());
		values.add(o.isStatic());
		values.add(o.getNoOfParams());
		values.add(o.isCallToParent());
		
		if(o.isPrivate()) access = 0;
		else if(o.isProtected()) access = 1;
		else if(o.isPublic()) access = 2;
		
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
}
