package command;

import java.util.ArrayList;

import geometry.Shape;
import mvc.DrawingModel;

public class SelectedShapeCmd implements Command {
	private Shape shape;
	private DrawingModel model;

	
	public SelectedShapeCmd(Shape shape ,DrawingModel model) {
		this.shape=shape;
		this.model=model;

	}
	
	@Override
	public void execute() {
		shape.setSelected(true);
	}

	@Override
	public void unexecute() {
	shape.setSelected(false);
	}
	@Override
	public String logText() {
		return "Select->"+ shape.toString();
	}

}
