package zuna.model.wrapper;

import java.sql.Connection;
import java.util.HashMap;

import zuna.model.Element;
import zuna.model.MyPackage;

public class PackageWrapper extends Wrapper{

	public PackageWrapper(Connection conn){
		super(conn);
	}
	
	public void putEntity(String key, Element value){
		if(value instanceof MyPackage){
			MyPackage o = (MyPackage)value;
		}else{
			return;
		}
	}
	
	public void getEntity(String key){
		
	}
	
	public HashMap<String, MyPackage> getEntityList(String project){
		return null;
	}
}
