package geometry;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;

public class Donut extends Circle implements Serializable {
	
	private int innerRadius;
	private Color edgeColorDonut;
	private Color fillColorDonut;

	public Donut() {
		
	}
	
	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius);
		this.innerRadius = innerRadius;
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center, radius, innerRadius);
		setSelected(selected);
	}
	public Donut(Point center, int radius, int innerRadius,Color edge,Color file) {
		super(center, radius);
		this.innerRadius = innerRadius;
		//this.fillColorDonut=file;
		//this.edgeColorDonut=edge;
		setFillColorDonut(file);
		setEdgeColorDonut(edge);
		setFillColor(file);
		setEdgeColor(edge);
	}
	
	public void draw(Graphics g) {
		if(innerRadius>radius) {
			throw new NumberFormatException("InnerRadius can't be bigger then OuterRadius!");
		};
		/*if (fillColorDonut != null) {
			g.setColor(fillColorDonut);
			g.fillOval(this.getCenter().getX() - this.getRadius(), this.getCenter().getY() - this.getRadius(),
					this.getRadius() * 2, this.getRadius() * 2);
		}
		super.draw(g);
		if (fillColorDonut != null) {
			g.setColor(new Color(250, 250, 250));
			g.fillOval(this.getCenter().getX() - this.getInnerRadius(), this.getCenter().getY() - this.getInnerRadius(),
					this.getInnerRadius() * 2, this.getInnerRadius() * 2);
		}
		g.setColor(Color.BLACK);
		if (edgeColorDonut != null)
			g.setColor(edgeColorDonut);

		g.drawOval(this.getCenter().getX() - this.getInnerRadius(), this.getCenter().getY() - this.getInnerRadius(),
				this.getInnerRadius() * 2, this.getInnerRadius() * 2);

		g.setColor(new Color(0, 0, 0));*/
		
			Ellipse2D outerCircle= new Ellipse2D.Double(this.getCenter().getX()-this.getRadius(),this.getCenter().getY()-this.getRadius(),this.getRadius()*2,this.getRadius()*2);
			Ellipse2D smallerCircle= new Ellipse2D.Double(this.getCenter().getX()-this.getInnerRadius(),this.getCenter().getY()-this.getInnerRadius(),this.getInnerRadius()*2,this.getInnerRadius()*2);
			Area bigArea=new Area(outerCircle);
			bigArea.subtract(new Area(smallerCircle));
			Graphics2D g2d= (Graphics2D)g;
			g2d.setColor(getFillColor());
			g2d.fill(bigArea);
			g2d.setColor(getEdgeColor());
			g2d.draw(bigArea);
			
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.getCenter().getX() - 3, this.getCenter().getY() - 3, 6, 6);
			g.drawRect(this.getCenter().getX() + getInnerRadius() - 3, this.getCenter().getY() - 3, 6, 6);
			g.drawRect(this.getCenter().getX() - getInnerRadius() - 3, this.getCenter().getY() - 3, 6, 6);
			g.drawRect(this.getCenter().getX() - 3, this.getCenter().getY() + getInnerRadius() - 3, 6, 6);
			g.drawRect(this.getCenter().getX() - 3, this.getCenter().getY() - getInnerRadius() - 3, 6, 6);
			g.drawRect(this.getCenter().getX() - 3, this.getCenter().getY() - 3, 6, 6);
			g.drawRect(this.getCenter().getX() + getRadius() - 3, this.getCenter().getY() - 3, 6, 6);
			g.drawRect(this.getCenter().getX() - getRadius() - 3, this.getCenter().getY() - 3, 6, 6);
			g.drawRect(this.getCenter().getX() - 3, this.getCenter().getY() + getRadius() - 3, 6, 6);
			g.drawRect(this.getCenter().getX() - 3, this.getCenter().getY() - getRadius() - 3, 6, 6);
			g.setColor(Color.BLACK);
		}
	}
	
	public int compareTo(Object o) {
		if (o instanceof Donut) {
			return (int) (this.area() - ((Donut) o).area());
		}
		return 0;
	}
	
	public boolean contains(int x, int y) {
		double dFromCenter = this.getCenter().distance(x, y);
		return super.contains(x, y) && dFromCenter > innerRadius;
	}
	
	public boolean contains(Point p) {
		double dFromCenter = this.getCenter().distance(p.getX(), p.getY());
		return super.contains(p.getX(), p.getY()) && dFromCenter > innerRadius;
	}
	
	public double area() {
		return super.area() - innerRadius * innerRadius * Math.PI;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut d = (Donut) obj;
			if (this.getCenter().equals(d.getCenter()) &&
					this.getRadius() == d.getRadius() &&
					innerRadius == d.getInnerRadius()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public int getInnerRadius() {
		return innerRadius;
	}
	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}	

	public Color getEdgeColorDonut() {
		return edgeColorDonut;
	}

	public void setEdgeColorDonut(Color edgeColorDonut) {
		this.edgeColorDonut = edgeColorDonut;
	}

	public Color getFillColorDonut() {
		return fillColorDonut;
	}

	public void setFillColorDonut(Color fillColorDonut) {
		this.fillColorDonut = fillColorDonut;
	}
	
	public String toString() {
		return "Donut: radius=" + radius + "; x=" + center.getX() + "; y=" + center.getY() + "; edge color=" + getEdgeColor().toString().substring(14).replace('=', '-') + "; area color=" + getFillColor().toString().substring(14).replace('=', '-') + "; inner radius=" + innerRadius;
	}
	
} 
