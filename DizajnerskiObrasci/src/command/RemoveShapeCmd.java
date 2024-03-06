package command;

import java.util.ArrayList;

import geometry.Shape;
import mvc.DrawingModel;

public class RemoveShapeCmd implements Command {
	private Shape shape ;//=new ArrayList<Shape>(0);
	private DrawingModel model;
	int index = 0;
	private boolean selected;
	
	public RemoveShapeCmd( Shape shape,DrawingModel model,int index) {
		this.shape=shape;
		this.model=model;
		this.index=index;
		this.selected=shape.isSelected();
	}
	@Override
	public void execute() {
		model.remove(shape);
	}
	
	@Override
	public void unexecute() {
	//for(Shape s:shape) {
		shape.setSelected(selected);
		model.getShapes().add(index, shape); 
		
	
	}
	@Override
	public String logText() {
		return "Remove->"+ shape.toString();
	}
}
