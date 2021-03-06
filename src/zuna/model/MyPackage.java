package zuna.model;

import java.util.HashMap;

import org.eclipse.jdt.core.IPackageFragment;

import zuna.model.wrapper.PackageWrapper;

public class MyPackage extends Element {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6020323031399856763L;
	private MyPackage parent = null;
	private HashMap<String, MyPackage> packageChildren = new HashMap<String, MyPackage>();
	private HashMap<String, MyClass> classChildren = new HashMap<String, MyClass>();
	private IPackageFragment pf;
	
	public MyPackage(String uri, IPackageFragment pf) {
		super(uri, false);
		this.pf = pf;
		
	}
	
	public MyPackage(String uri, boolean lib) {
		super(uri, lib);
	}
	
	public IPackageFragment getPf() {
		return pf;
	}

	public HashMap<String, MyPackage> getPackageChildren() {
		return packageChildren;
	}

	public void addPackageChild(MyPackage child) {
		PackageWrapper.addPackageChild(this, child);
	}
	
	public HashMap<String, MyClass> getClassChildren() {
		return classChildren;
	}
	
//	public void addClassChild(MyClass child) {
////		PackageWrapper.addClassChild(this, child);
////		this.classChildren.put(child.getID(), child);
//	}

	public MyPackage getParent() {
		return parent;
	}

	public void setParent(MyPackage parent) {
		this.parent = parent;
		this.parent.addPackageChild(this);
	}
}
