package zuna.model.wrapper;

import java.sql.Connection;
import java.util.HashMap;

import zuna.model.Element;
import zuna.model.MyParameter;

public class ParameterWrapper  extends Wrapper{

	public ParameterWrapper(Connection conn){
		super(conn);
	}
	
	
	public void putEntity(String key, Element value){
		if(value instanceof MyParameter){
			MyParameter o = (MyParameter)value;
		}else{
			return;
		}
	}
	
	public void getEntity(String key){
		
	}
	
	public HashMap<String, MyParameter> getEntityList(String project){
		return null;
	}
	
}
