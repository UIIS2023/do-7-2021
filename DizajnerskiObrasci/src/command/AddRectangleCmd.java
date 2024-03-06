package command;

import geometry.Rectangle;
import mvc.DrawingModel;

public class AddRectangleCmd implements Command {

	private Rectangle rectengle;
	private DrawingModel model;
	
	public AddRectangleCmd (Rectangle rectangle,DrawingModel model) {
		this.rectengle=rectangle;
		this.model=model;
	}
	@Override
	public void execute() {
		model.add(rectengle);
	}
	@Override
	public void unexecute() {
		model.remove(rectengle);
	}
	@Override
	public String logText() {
		return "Add->" +rectengle.toString();
	}
}
