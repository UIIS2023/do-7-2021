package command;

import java.awt.Color;

import adapter.HexagonAdapter;
import geometry.Point;
import hexagon.Hexagon;
import mvc.DrawingModel;

public class UpdateHexagonCmd implements Command{
	
	private HexagonAdapter hexagon;
    private HexagonAdapter newState;
    HexagonAdapter temp = new HexagonAdapter(0,0,0);
    

    public UpdateHexagonCmd(HexagonAdapter hexagon, HexagonAdapter newState) {
        this.hexagon = hexagon;
        this.newState = newState;
    }

    @Override
    public void execute() {
        temp.setX(hexagon.getX());
        temp.setY(hexagon.getY());
        try {
			temp.setRadius(hexagon.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        temp.setEdgeColor(hexagon.getEdgeColor());
        temp.setFillColor(hexagon.getFillColor());

        hexagon.setX(newState.getX());
        hexagon.setY(newState.getY());
        try {
			hexagon.setRadius(newState.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
        hexagon.setEdgeColor(newState.getEdgeColor());
        hexagon.setFillColor(newState.getFillColor());
    }

    @Override
    public void unexecute() {
        hexagon.setX(temp.getX());
        hexagon.setY(temp.getY());
        try {
			hexagon.setRadius(temp.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        hexagon.setEdgeColor(temp.getEdgeColor());
        hexagon.setFillColor(temp.getFillColor());
    }
	@Override
	public String logText() {
		return "Update->"+ hexagon.toString();
	}

}
