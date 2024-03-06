package command;

import java.util.ArrayList;
import java.util.Collections;

import geometry.Shape;

public class ToBackCmd implements Command{

	private Shape shape;
	private ArrayList<Shape> shapeList;
	private int index;
	
	public ToBackCmd(ArrayList<Shape>shapeList,Shape shape) {
		this.shapeList=shapeList;
		this.shape=shape;
		index=this.shapeList.indexOf(this.shape);
	}
	
	@Override
	public void execute() {
		if(index>0) {
		Collections.swap(shapeList, index, index-1);
	}
	}
	@Override
	public void unexecute() {
		if(index<=shapeList.size()-1) {
			Collections.swap(shapeList, index, index-1);
		}
	}
	@Override
	public String logText() {
		return "To back->"+ shape.toString();
	}

}
