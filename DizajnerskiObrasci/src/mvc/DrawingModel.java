package mvc;

import java.io.Serializable;
import java.util.ArrayList;

import geometry.Shape;

public class DrawingModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
public ArrayList<Shape> shapes = new ArrayList<Shape>();
	
	public void add(Shape s) {
		shapes.add(s);
	}
	
	public void remove(Shape s) {
		shapes.remove(s);
	}
	
	public Shape get(int index) {
		return shapes.get(index);
	}
	
	public ArrayList<Shape> getShapes(){
		return shapes;
	}
	public void addMultiple(ArrayList<Shape> shapes) {
		this.shapes.addAll(shapes);
	}
	public int getIndexOf(Shape shape) {
		return shapes.indexOf(shape);
	}
	public void clear() {
		shapes.clear();
	}
}
