package observer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Observer {

	private boolean btnDeleteEnabled;
	private boolean btnModifyEnabled;
	private boolean btnBringToFrontEnabled;
	private boolean btnBringToBackEnabled;
	private boolean btnToFrontEnabled;
	private boolean btnToBackEnabled;
	private boolean btnUndoEnabled;
	private boolean btnRedoEnabled;

	private PropertyChangeSupport propertyChangeSupport;
	
	public Observer() {
		this.propertyChangeSupport= new PropertyChangeSupport(this);
	}
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		propertyChangeSupport.addPropertyChangeListener(pcl);
	}
	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		propertyChangeSupport.removePropertyChangeListener(pcl);
	}
	public void setBtnDeleteEnabled(boolean btnDeleteEnabled) {
		propertyChangeSupport.firePropertyChange("buttonDelete", this.btnDeleteEnabled, btnDeleteEnabled);
		this.btnDeleteEnabled=btnDeleteEnabled;
	}
	public void setBtnModifyEnabled(boolean btnModifyEnabled) {
		propertyChangeSupport.firePropertyChange("buttonModify", this.btnModifyEnabled, btnModifyEnabled);
		this.btnModifyEnabled=btnModifyEnabled;
	}
	public void setBtnBringToFrontEnabled(boolean btnBringToFrontEnabled) {
		propertyChangeSupport.firePropertyChange("buttonBringToFront", this.btnBringToFrontEnabled, btnBringToFrontEnabled);
		this.btnBringToFrontEnabled=btnBringToFrontEnabled;
	}
	public void setBtnBringToDownEnabled(boolean btnBringToBackEnabled) {
		propertyChangeSupport.firePropertyChange("buttonBringToBack", this.btnBringToBackEnabled, btnBringToBackEnabled);
		this.btnBringToBackEnabled=btnBringToBackEnabled;
	}
	public void setBtnToFrontEnabled(boolean btnToFrontEnabled) {
		propertyChangeSupport.firePropertyChange("buttonToFront", this.btnToFrontEnabled, btnToFrontEnabled);
		this.btnToFrontEnabled=btnToFrontEnabled;
	}
	public void setBtnToBackEnabled(boolean btnToBackEnabled) {
		propertyChangeSupport.firePropertyChange("buttonToBack", this.btnToBackEnabled, btnToBackEnabled);
		this.btnToBackEnabled=btnToBackEnabled;
	}
	public void setBtnUndoEnabled(boolean btnUndoEnabled) {
		propertyChangeSupport.firePropertyChange("buttonUndo", this.btnUndoEnabled, btnUndoEnabled);
		this.btnUndoEnabled=btnUndoEnabled;
	}
	public void setBtnRedoEnabled(boolean btnRedoEnabled) {
		propertyChangeSupport.firePropertyChange("buttonRedo", this.btnRedoEnabled, btnRedoEnabled);
		this.btnRedoEnabled=btnRedoEnabled;
	}
	}
