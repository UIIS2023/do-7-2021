package observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import mvc.DrawingFrame;

public class ObserverUpdate implements PropertyChangeListener {

    private DrawingFrame frame;

    public ObserverUpdate(DrawingFrame frame) {
        this.frame = frame;
    }
	
	
	  @Override
	    public void propertyChange(PropertyChangeEvent pce) {

	       if (pce.getPropertyName().equals("buttonDelete")) {
	            boolean isEnabled1 = (boolean)pce.getNewValue();
	            frame.setTglbtnDeleteEnabled(isEnabled1);
	        }
	        if(pce.getPropertyName().equals("buttonModify")) {
	        	boolean isEnabled=(boolean)pce.getNewValue();
	        	frame.setTglbtnModifyEnabled(isEnabled);
	        }
	        if(pce.getPropertyName().equals("buttonBringToFront")) {
	        	boolean isEnabled=(boolean)pce.getNewValue();
	        	frame.setTglbtnBringToFrontEnabled(isEnabled);
	        }
	        if(pce.getPropertyName().equals("buttonBringToBack")) {
	        	boolean isEnabled=(boolean)pce.getNewValue();
	        	frame.setTglbtnBringToBackEnabled(isEnabled);
	        }
	        if(pce.getPropertyName().equals("buttonToFront")) {
	        	boolean isEnabled=(boolean)pce.getNewValue();
	        	frame.setTglbtnToFrontEnabled(isEnabled);
	        }
	        if(pce.getPropertyName().equals("buttonToBack")) {
	        	boolean isEnabled=(boolean)pce.getNewValue();
	        	frame.setTglbtnToBackEnabled(isEnabled);
	        }
	        if(pce.getPropertyName().equals("buttonUndo")) {
	        	boolean isEnabled=(boolean)pce.getNewValue();
	        	frame.setTglbtnUndoEnabled(isEnabled);
	        }
	        if(pce.getPropertyName().equals("buttonRedo")) {
	        	boolean isEnabled=(boolean)pce.getNewValue();
	        	frame.setTglbtnRedoEnabled(isEnabled);
	        }
	    }
	}


