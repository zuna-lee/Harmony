package zuna.refactoring.ui.actions;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jdt.internal.core.CompilationUnit;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;

import zuna.metric.classDS.ArchitectureBasedDS;
import zuna.metric.classDS.InformationContents4System;
import zuna.refactoring.ProjectAnalyzer;

@SuppressWarnings("restriction")
public class Harmony implements IWorkbenchWindowActionDelegate {
	private static IWorkbenchWindow window;
	public static double th=21;
	private static Class<?> task;
	/**
	 * The constructor.
	 */
	public Harmony() {
		
	}

	
	
	/**
	 * The action has been activated. The argument of the
	 * method represents the 'real' action sitting
	 * in the workbench UI.
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	
	@Override
	public void run(IAction action) {
	
		window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if (window != null)
		{
			try {
				IStructuredSelection selection = (IStructuredSelection) window.getSelectionService().getSelection();
		        Object firstElement = selection.getFirstElement();
        		init();
        		
        		IProject project = (IProject)((IAdaptable)firstElement).getAdapter(IProject.class);
	            ProjectAnalyzer.firstElement = (IAdaptable)firstElement;
	            ProjectAnalyzer.url = project.getLocationURI().getPath().toString().substring(1);
	            File dbFile = new File(ProjectAnalyzer.url + "\\" + project.getName() + ".db");
	            
	            if(dbFile.exists()){
	            	MessageBox dialog = new MessageBox(window.getShell(), SWT.ICON_QUESTION | SWT.OK| SWT.CANCEL);
	        		dialog.setText("Select");
	        		dialog.setMessage("The DB file of the project exists. \n Do you want to rebuild the project?");
	        		int returnCode = dialog.open();
	        		
	        		if(returnCode==SWT.OK){
	        			dbFile.delete();
	        			this.analysis(project);
	        			this.showMessage("Complete","Rebuild has been complete!");
	        			//do something
	        			this.doTask();
	        		}else{
	        			//do something
	        			this.doTask();
	        		}
	            }else{
	            	this.analysis(project);
	            	this.showMessage("Complete","Rebuild has been complete!");
	            	//do something
	            	this.doTask();
	            }
        		
	            
			}catch(java.lang.NullPointerException e){
				e.printStackTrace();
			}catch (java.lang.ClassCastException e2){
				e2.printStackTrace();
			}
		}
	}
	
	private void doTask(){
		try {
			
			URL bin = new File("C:\\Users\\zuna\\Documents\\GitHub\\APP-1\\bin").toURL();
			Class<?> c = new URLClassLoader(new URL[] { bin }).loadClass("task.MetricTask");
			Object o = c.newInstance();
			Method m = c.getMethod("doTask", (Class[]) null);
			m.invoke(o, (Object[]) null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void analysis(IProject project){
        ProjectAnalyzer.analyze(project);
        InformationContents4System icCalcul = new InformationContents4System();
        icCalcul.calculateIC();
        new ArchitectureBasedDS();
	}
    private void init(){
		ProjectAnalyzer.project = null;
		ProjectAnalyzer.firstElement=null;
	}
    
    private void showMessage(String title, String message){
		MessageBox dialog2 = new MessageBox(window.getShell(), SWT.ICON_QUESTION | SWT.OK);
		dialog2.setText(title);
		dialog2.setMessage(message);
		dialog2.open();
	}
    
	private String getClassID(CompilationUnit cu) {
		String classID = cu.getPath().toString().replace(cu.getPackageFragmentRoot().getPath().toString() + "/", "");
		classID = classID.replace("/", ".");
		classID = classID.substring(0, classID.length()-5);
		return classID;
	}
	
	
	/**
	 * Selection in the workbench has been changed. We 
	 * can change the state of the 'real' action here
	 * if we want, but this can only happen after 
	 * the delegate has been created.
	 * @see IWorkbenchWindowActionDelegate#selectionChanged
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
	}

	/**
	 * We can use this method to dispose of any system
	 * resources we previously allocated.
	 * @see IWorkbenchWindowActionDelegate#dispose
	 */
	@Override
	public void dispose() {
		
	}

	/**
	 * We will cache window object in order to
	 * be able to provide parent shell for the message dialog.
	 * @see IWorkbenchWindowActionDelegate#init
	 */
	@Override
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}
	
//	private HashMap<String, MyMethod> getRefactoredMode(Repo p1, Repo p2){
//		ArrayList<MyMethod> diff = ASTParserUtil.findDifferences(p1, p2);
//		HashMap<String, MyMethod> changedMethods = new HashMap<String, MyMethod>(); 
//		for(MyMethod m: diff){
//			changedMethods.put(m.getID(), m);
//		}
//		
//		return changedMethods;
//	}

}
