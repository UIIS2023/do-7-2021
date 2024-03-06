package geometry;

import java.awt.Graphics;
import java.io.Serializable;

public abstract class Shape implements Movable, Comparable, Serializable {

	private boolean selected;

	public Shape() {
		
	}
	
	public Shape(boolean selected) {
		this.selected = selected;
	}
	
	public abstract boolean contains(Point p);
	
	public abstract boolean contains(int x, int y);
	
	public abstract void draw(Graphics g);
	
	public abstract void moveBy(int byX, int byY);
	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	
}
