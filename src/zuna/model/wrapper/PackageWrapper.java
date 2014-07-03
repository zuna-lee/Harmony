package zuna.model.wrapper;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import zuna.model.Element;
import zuna.model.MyPackage;

public class PackageWrapper extends Wrapper{

	public PackageWrapper(Connection conn){
		super(conn);
	}

	public void putEntity(String key, Element value){
		try{
			ArrayList<String> fields = new ArrayList<String>();
			ArrayList<Object> values = new ArrayList<Object>();
			MyPackage o = (MyPackage)value;
			this.getFields(fields);
			this.getValues(values, o);
			super.saveRelationships(super.PACKAGE_CHILDREN, o.getID(), super.convert(o.getPackageChildren()));
			super.saveRelationships(super.CLASS_CHILDREN, o.getID(), super.convert(o.getClassChildren()));
		}catch(Exception e){
			e.printStackTrace(System.err);
		}
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
		values.add(o.isLibrary());
	}
	
	public void getEntity(String key){
		
	}
	
	public HashMap<String, MyPackage> getEntityList(String project){
		return null;
	}
}
