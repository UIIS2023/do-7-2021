package command;

import geometry.Rectangle;
import mvc.DrawingModel;

public class RemoveRectangleCmd implements Command {
	
	private Rectangle rectangle;
	private DrawingModel model;
	
	public RemoveRectangleCmd(Rectangle rectangle,DrawingModel model) {
		this.rectangle=rectangle;
		this.model=model;
	}
	@Override
	public void execute() {
		model.remove(rectangle);
	}
	@Override
	public void unexecute() {
		model.add(rectangle);
	}
	@Override
	public String logText() {
		return "Add->"+ rectangle.toString();
	}
}
