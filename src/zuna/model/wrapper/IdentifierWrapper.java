package zuna.model.wrapper;

import java.sql.Connection;
import java.util.HashMap;

import zuna.model.Element;
import zuna.model.MyIdentifier;

public class IdentifierWrapper extends Wrapper{

	
	
	public void putEntity(String key, Element value){
		if(value instanceof MyIdentifier){
			MyIdentifier o = (MyIdentifier)value;
		}else{
			return;
		}
	}
	
	public void getEntity(String key){
		
	}
	
	public HashMap<String, MyIdentifier> getEntityList(String project){
		return null;
	}

	@Override
	protected void createTable() {
		// TODO Auto-generated method stub
		
	}
}
