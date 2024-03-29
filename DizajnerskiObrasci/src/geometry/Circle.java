package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Circle extends Shape implements Serializable {

	protected Point center;
	public int radius;
	private Color edgeColor;
	private Color fillColor;
	
	public Circle() {

	}

	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}

	public Circle(Point center, int radius, boolean selected) {
		this(center, radius);
		setSelected(selected);
	}
	public Circle(Point center, int radius,Color edge,Color file) {
		this.center = center;
		this.radius = radius;
		this.fillColor=file;
		this.edgeColor=edge;
	}

	@Override
	public void draw(Graphics g) {
		if (fillColor!=null) {
			g.setColor(fillColor);
			g.fillOval(this.getCenter().getX() - this.getRadius(), this.getCenter().getY() - this.getRadius(),
					this.getRadius() * 2, this.getRadius() * 2);
		}
		g.setColor(Color.BLACK);
		if (edgeColor!=null)
			g.setColor(edgeColor);
		g.drawOval(this.getCenter().getX() - this.getRadius(), this.getCenter().getY() - this.getRadius(),
				this.getRadius() * 2, this.getRadius() * 2);
		g.setColor(new Color(0, 0, 0));
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(getCenter().getX() - 3, getCenter().getY() - 3, 6, 6);
			g.drawRect(getCenter().getX() - 3 - getRadius(), getCenter().getY() - 3, 6, 6);
			g.drawRect(getCenter().getX() - 3 + getRadius(), getCenter().getY() - 3, 6, 6);
			g.drawRect(getCenter().getX() - 3, getCenter().getY() - 3 + getRadius() , 6, 6);
			g.drawRect(getCenter().getX() - 3, getCenter().getY() - 3 - getRadius(), 6, 6);
		}
	}
	
	@Override
	public void moveBy(int byX, int byY) {
		center.moveBy(byX, byY);
		
	}
	
	@Override
	public int compareTo(Object o) {
		if (o instanceof Circle) {
			return (this.radius - ((Circle) o).radius);
		}
		return 0;
	}
	
	public boolean contains(int x, int y) {
		return this.getCenter().distance(x, y) <= radius;
	}
	
	public boolean contains(Point p) {
		return p.distance(getCenter().getX(), getCenter().getY()) <= radius;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle c = (Circle) obj;
			if (this.center.equals(c.getCenter()) && this.radius == c.getRadius()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public double area() {
		return radius * radius * Math.PI;
	}
	
	public Point getCenter() {
		return center;
	}
	public void setCenter(Point center) {
		this.center = center;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) throws Exception {
		if (radius >= 0) {
			this.radius = radius;
		} else {
			throw new NumberFormatException("Radius has to be a value greater then 0!");
		}
	}
	
	public Color getEdgeColor() {
		return edgeColor;
	}

	public void setEdgeColor(Color edgeColor) {
		this.edgeColor = edgeColor;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
	
	public String toString() {
		return "Circle: radius=" + radius + "; x=" + center.getX() + "; y=" + center.getY() + "; edge color=" + getEdgeColor().toString().substring(14).replace('=', '-') + "; area color=" + getFillColor().toString().substring(14).replace('=', '-');
		
	}
	

	
}
