package command;

import geometry.Circle;
import geometry.Point;

public class UpdateCircleCmd implements Command {

	private Circle circle;
	private Circle newState;
	Circle temp= new Circle(new Point(0,0),0);
	
	public UpdateCircleCmd(Circle circle,Circle newState) {
		this.circle=circle;
		this.newState=newState;

	}
	@Override
	public void execute() {
	
		temp.getCenter().setX(circle.getCenter().getX());
		temp.getCenter().setY(circle.getCenter().getY());
		try {
			temp.setRadius(circle.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		temp.setFillColor(circle.getFillColor());
		temp.setEdgeColor(circle.getEdgeColor());
		
		circle.getCenter().setX(newState.getCenter().getX());
		circle.getCenter().setY(newState.getCenter().getY());
		try {
			circle.setRadius(newState.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		circle.setFillColor(newState.getFillColor());
		circle.setEdgeColor(newState.getEdgeColor());
		
	}
	@Override 
	public void unexecute() {
		circle.getCenter().setX(temp.getCenter().getX());
		circle.getCenter().setY(temp.getCenter().getY());
		try {
			circle.setRadius(temp.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		circle.setFillColor(temp.getFillColor());
		circle.setEdgeColor(temp.getEdgeColor());
	}
	@Override
	public String logText() {
		return "Update->"+ circle.toString();
	}
	
}
