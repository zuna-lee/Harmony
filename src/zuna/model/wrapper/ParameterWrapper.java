package zuna.model.wrapper;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import zuna.model.Element;
import zuna.model.MyParameter;

public class ParameterWrapper  extends Wrapper{

	public ParameterWrapper(Connection conn){
		super(conn);
	}
	
	public void putEntity(String key, Element value){
		try{
			ArrayList<String> fields = new ArrayList<String>();
			ArrayList<Object> values = new ArrayList<Object>();
			MyParameter o = (MyParameter)value;
			this.getFields(fields);
			this.getValues(values, o);
		}catch(Exception e){
			e.printStackTrace(System.err);
		}
	}
	
	private void getFields(ArrayList<String> fields) {
		fields.add("id");
		fields.add("se");
		fields.add("ic");
	}

	private void getValues(ArrayList<Object> values, MyParameter o) {
		values.add("\"" + o.getName() + "\"");
		values.add(o.getType());
		values.add("\"" + o.getParent().getID() + "\"");
	}
	
	public void getEntity(String key){
		
	}
	
	public HashMap<String, MyParameter> getEntityList(String project){
		return null;
	}
	
}
