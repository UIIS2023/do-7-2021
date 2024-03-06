package command;

import java.util.ArrayList;
import java.util.Collections;

import geometry.Shape;

public class BringToDownCmd implements Command{

	private ArrayList<Shape> shapeList;
	private Shape shape;
	private int index;
	
	
	public BringToDownCmd(ArrayList<Shape>shapeList,Shape shape) {
		this.shapeList=shapeList;
		this.shape=shape;
		index=this.shapeList.indexOf(this.shape);
	}

	@Override
	public void execute() {
		for(int i=index;i>0;i--) {
			Collections.swap(shapeList, i, i-1);
		}

	}

	@Override
	public void unexecute() {
		for(int i =0;i<shapeList.size()-1;i++) {
			Collections.swap(shapeList, i, i+1);
		}
		
	}
	@Override
	public String logText() {
		return "Bring to back->"+shape.toString();
	}

}
