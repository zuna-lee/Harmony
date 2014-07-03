package zuna.model.wrapper;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.jdt.core.dom.FieldDeclaration;

import zuna.model.Element;
import zuna.model.MyClass;
import zuna.model.MyField;
import zuna.model.MyMethod;

public class FieldWrapper extends Wrapper{

	public FieldWrapper(Connection conn){
		super(conn);
	}

	public void putEntity(String key, Element value){
		try{
			ArrayList<String> fields = new ArrayList<String>();
			ArrayList<Object> values = new ArrayList<Object>();
			MyField o = (MyField)value;
			this.getFields(fields);
			this.getValues(values, o);
			super.saveRelationships(super.REFERED_METHOD, o.getID(), super.convert(o.getReferencingMethod()));
		}catch(Exception e){
			e.printStackTrace(System.err);
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
		values.add(o.isLibrary());
		
		values.add("\"" + o.getType() + "\"");
		values.add("\"" + o.getParent().getID() + "\"");
	}
	
	public void getEntity(String key){
		
	}
	
	public HashMap<String, MyField> getEntityList(String project){
		return null;
	}
}
