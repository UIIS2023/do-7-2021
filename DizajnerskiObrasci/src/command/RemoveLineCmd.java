package command;

import geometry.Line;
import mvc.DrawingModel;

public class RemoveLineCmd implements Command {

	private Line line;
	private DrawingModel model;
	
	public RemoveLineCmd(Line line,DrawingModel model) {
		this.line=line;
		this.model=model;
	}
	@Override
	public void execute() {
		model.remove(line);
	}
	@Override
	public void unexecute() {
		model.add(line);
	}
	@Override
	public String logText() {
		return "Remove->"+ line.toString();
	}
	
}
