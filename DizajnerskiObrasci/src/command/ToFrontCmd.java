package command;

import java.util.ArrayList;
import java.util.Collections;

import geometry.Shape;

public class ToFrontCmd implements Command {

	private Shape shape;
	private ArrayList<Shape> shapeList;
	private int index;
	
	public ToFrontCmd(ArrayList<Shape>shapeList,Shape shape) {
		this.shapeList=shapeList;
		this.shape=shape;
		index=this.shapeList.indexOf(this.shape);
	}
	@Override
	public void execute() {
		if(index<shapeList.size()-1) {
		Collections.swap(shapeList, index, index+1);
		}
	}

	@Override
	public void unexecute() {
		if(this.index<=this.shapeList.size()-1) {
		Collections.swap(shapeList, this.index, this.index+1);
		}
	}
	@Override
	public String logText() {
		return "To front->"+ shape.toString();
	}

}
