package command;

import java.util.ArrayList;
import java.util.Collections;

import geometry.Shape;

public class BringToFrontCmd implements Command {

	
	private ArrayList<Shape> shapeList;
	private Shape shape;
	private int index;
	private boolean selected;
	
	public BringToFrontCmd(ArrayList<Shape> shapeList,Shape shape) {
		this.shapeList=shapeList;
		this.shape=shape;
		index=this.shapeList.indexOf(this.shape);
		//this.selected=shape.isSelected();
	}
	
	
	@Override
	public void execute() {
		for(int i=index;i<shapeList.size()-1;i++)
		{
			Collections.swap(shapeList, i, i+1);
		}
		//shape.setSelected(selected);
	}

	@Override
	public void unexecute() {
		for(int i=shapeList.size()-1;i>index;i--) {
			Collections.swap(shapeList,i,i-1);
		}
		//shape.setSelected(selected);
	}
	@Override
	public String logText() {
		return "Bring to front->"+shape.toString();
	}

}
