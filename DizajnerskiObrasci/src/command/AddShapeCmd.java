package command;

import geometry.Shape;
import mvc.DrawingModel;

public class AddShapeCmd implements Command {

	private Shape shape;
	private DrawingModel model;
	public AddShapeCmd() {
		
	}
	public AddShapeCmd(Shape shape,DrawingModel model) {
		this.shape=shape;
		this.model=model;
	}
	@Override
	public void execute() {
		model.add(shape);
	}
	@Override
	public void unexecute() {
		model.remove(shape);
	}
	@Override
	public String logText() {
		return "Add->"+ shape.toString();
	}
}