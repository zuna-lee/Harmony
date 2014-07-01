package zuna.model.wrapper;

import java.sql.Connection;
import java.util.HashMap;

import zuna.model.Element;
import zuna.model.MyIdentifier;
import zuna.model.MyMethod;

public class MethodWrapper extends Wrapper{

	public MethodWrapper(Connection conn){
		super(conn);
	}
	
	public void putEntity(String key, Element value){
		if(value instanceof MyMethod){
			MyMethod o = (MyMethod)value;
		}else{
			return;
		}
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
