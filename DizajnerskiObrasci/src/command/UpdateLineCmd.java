package command;

import geometry.Line;
import geometry.Point;

public class UpdateLineCmd implements Command {

	private Line line;
	private Line newState;
	Line temp = new Line(new Point(0,0),new Point(0,0));
	
	public UpdateLineCmd(Line line,Line newState) {
		this.line=line;
		this.newState=newState;
	}
	@Override
	public void execute() {
		 if (temp.getStartPoint() != null && line.getStartPoint() != null) {
		temp.getStartPoint().setX(line.getStartPoint().getX());
		temp.getStartPoint().setY(line.getStartPoint().getY());}
		 if (temp.getEndPoint() != null && line.getEndPoint() != null) {
		temp.getEndPoint().setX(line.getEndPoint().getX());
		temp.getEndPoint().setY(line.getEndPoint().getY());
		temp.setColor(line.getColor());}
		 if (temp.getStartPoint() != null && line.getStartPoint() != null) {
		line.getStartPoint().setX(newState.getStartPoint().getX());
		line.getStartPoint().setY(newState.getStartPoint().getY());}
		 if (temp.getEndPoint() != null && line.getEndPoint() != null) {
		line.getEndPoint().setX(newState.getEndPoint().getX());
		line.getEndPoint().setY(newState.getEndPoint().getY());
		line.setColor(newState.getColor());}
		}
	@Override
	public void unexecute() {
		 if (temp.getStartPoint() != null && line.getStartPoint() != null) {
	line.getStartPoint().setX(temp.getStartPoint().getX());
	line.getStartPoint().setY(temp.getStartPoint().getY());}
		 if (temp.getEndPoint() != null && line.getEndPoint() != null) {
	line.getEndPoint().setX(temp.getEndPoint().getX());
	line.getEndPoint().setY(temp.getEndPoint().getY());
	line.setColor(temp.getColor());}
}
	@Override
	public String logText() {
		return "Update->"+ line.toString();
	}
}