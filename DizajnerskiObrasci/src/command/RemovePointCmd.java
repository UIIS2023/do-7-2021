package command;

import geometry.Point;
import mvc.DrawingModel;

public class RemovePointCmd implements Command {

	private Point point;
	private DrawingModel model;
	
	public RemovePointCmd(Point point,DrawingModel model) {
		this.point=point;
		this.model=model;
	}
	@Override
	public void execute() {
		model.remove(point);
	}
	public void unexecute() {
		model.add(point);
	}
	@Override
	public String logText() {
		return "Remove->"+ point.toString();
	}
}
