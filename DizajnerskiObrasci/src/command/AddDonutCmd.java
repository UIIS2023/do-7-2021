package command;

import geometry.Donut;
import mvc.DrawingModel;

public class AddDonutCmd implements Command {

	private Donut dount;
	private DrawingModel model;
	
	public AddDonutCmd(Donut dount,DrawingModel model) {
		this.dount=dount;
		this.model=model;
	}
	@Override
	public void execute() {
		model.add(dount);
	}
	@Override
	public void unexecute() {
		model.remove(dount);
	}
	@Override
	public String logText() {
		return "Add->" +dount.toString();
	}
}
 