package command;

import geometry.Donut;
import geometry.Point;

public class UpdateDonutCmd implements Command {
	
	private Donut donut;
	private Donut newState;
	Donut temp= new Donut(new Point(0,0),0,0);
	
	public UpdateDonutCmd(Donut donut,Donut newState) {
		this.donut=donut;
		this.newState=newState;
	}
	
	@Override
	public void execute() {
		temp.getCenter().setX(donut.getCenter().getX());
		temp.getCenter().setY(donut.getCenter().getY());
		temp.setInnerRadius(donut.getInnerRadius());
		try {
			temp.setRadius(donut.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		temp.setEdgeColor(donut.getEdgeColor());
		temp.setEdgeColorDonut(donut.getEdgeColorDonut());
		temp.setFillColor(donut.getFillColor());	
		temp.setFillColorDonut(donut.getFillColorDonut());
		
		donut.getCenter().setX(newState.getCenter().getX());
		donut.getCenter().setY(newState.getCenter().getY());
		donut.setInnerRadius(newState.getInnerRadius());
		try {
			donut.setRadius(newState.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		donut.setEdgeColor(newState.getEdgeColor());
		donut.setEdgeColorDonut(newState.getEdgeColorDonut());
		donut.setFillColor(newState.getFillColor());
		donut.setFillColorDonut(newState.getFillColorDonut());
	}
	@Override
	public void unexecute() {
		donut.getCenter().setX(temp.getCenter().getX());
		donut.getCenter().setY(temp.getCenter().getY());
		donut.setInnerRadius(temp.getInnerRadius());
		try {
			donut.setRadius(temp.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		donut.setEdgeColor(temp.getEdgeColor());
		donut.setEdgeColorDonut(temp.getEdgeColorDonut());
		donut.setFillColor(temp.getFillColor());
		donut.setFillColorDonut(temp.getFillColorDonut());
	}
	@Override
	public String logText() {
		return "Update->"+ donut.toString();
	}

}
