package command;

import geometry.Point;
import geometry.Rectangle;

public class UpdateRectangleCmd implements Command {
	
	private Rectangle rectangle;
	private Rectangle newState;
	Rectangle temp= new Rectangle(new Point(0,0),0,0);
	
	public UpdateRectangleCmd(Rectangle rectangle,Rectangle newState) {
		this.rectangle=rectangle;
		this.newState=newState;
	}
	@Override
	public void execute() {
		temp.getUpperLeftPoint().setX(rectangle.getUpperLeftPoint().getX());
		temp.getUpperLeftPoint().setY(rectangle.getUpperLeftPoint().getY());
		temp.setFillColor(rectangle.getFillColor());
		temp.setEdgeColor(rectangle.getEdgeColor());
		temp.setHeight(rectangle.getHeight());
		temp.setWidth(rectangle.getWidth());
		
		rectangle.getUpperLeftPoint().setX(newState.getUpperLeftPoint().getX());
		rectangle.getUpperLeftPoint().setY(newState.getUpperLeftPoint().getY());	
		rectangle.setEdgeColor(newState.getEdgeColor());
		rectangle.setFillColor(newState.getFillColor());
		rectangle.setHeight(newState.getHeight());
		rectangle.setWidth(newState.getWidth());
	}
	@Override
	public void unexecute() {
		rectangle.getUpperLeftPoint().setX(temp.getUpperLeftPoint().getX());
		rectangle.getUpperLeftPoint().setY(temp.getUpperLeftPoint().getY());	
		rectangle.setEdgeColor(temp.getEdgeColor());
		rectangle.setFillColor(temp.getFillColor());
		rectangle.setHeight(temp.getHeight());
		rectangle.setWidth(temp.getWidth());
	}
	@Override
	public String logText() {
		return "Update->"+ rectangle.toString();
	}
}
