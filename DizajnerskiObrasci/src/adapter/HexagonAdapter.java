package adapter;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import geometry.Circle;
import geometry.Point;
import geometry.Shape;
import hexagon.Hexagon;

public class HexagonAdapter extends Shape implements Serializable {
	
	 Hexagon hexagon;
	
	public HexagonAdapter() {
		hexagon=new Hexagon(0,0,0);
	}
	
	public HexagonAdapter(Hexagon hexagon) {
	this.hexagon=hexagon;
	}
	public HexagonAdapter(int x,int y,int radius) {
		this.hexagon=new Hexagon(x,y,radius);	
	}
	public HexagonAdapter(int x,int y,int radius,boolean selected) {
		this.hexagon=new Hexagon(x,y,radius);	
		this.hexagon.setSelected(selected);
	}
	public HexagonAdapter(Point center,int radius) {
		this.hexagon= new Hexagon(center.getX(),center.getY(),radius);
	
	}
	public HexagonAdapter(Color edgeColor, Color innerColor) {
	    hexagon = new Hexagon(0, 0, 0);
	    this.hexagon.setAreaColor(innerColor);
	    this.hexagon.setBorderColor(edgeColor);
	}
	public HexagonAdapter(int x,int y,int r,Color eddgeColor,Color innerColor,boolean selected) {
		hexagon= new Hexagon(x,y,r);
		this.hexagon.setAreaColor(innerColor);
		this.hexagon.setBorderColor(eddgeColor);
		this.hexagon.setSelected(selected);
	}
	public HexagonAdapter(int x,int y,int r,Color eddgeColor,Color innerColor) {
		hexagon= new Hexagon(x,y,r);
		this.hexagon.setAreaColor(innerColor);
		this.hexagon.setBorderColor(eddgeColor);
	}
	@Override
	public int compareTo(Object o) {
		if (o instanceof HexagonAdapter) {
			return (this.hexagon.getR() - ((HexagonAdapter) o).hexagon.getR());
		}
		return 0;
	}
	

	@Override
	public boolean contains(Point p) {
		return this.hexagon.doesContain(p.getX(), p.getY());
	}

	@Override
	public boolean contains(int x, int y) {
		return this.hexagon.doesContain(x, y);
	}

	@Override
	public void draw(Graphics g) {
		hexagon.setSelected(isSelected());
		this.hexagon.paint(g);	

	}

	@Override
	public void moveBy(int byX, int byY) {
		this.hexagon.setX(this.hexagon.getX()+byX);
		this.hexagon.setY(this.hexagon.getY()+byY);
	}
	/*public boolean equals(Object obj) {
		if(obj instanceof HexagonAdapter) {
			HexagonAdapter hexAdapter=(HexagonAdapter)obj;
			if(this.hexagon.getX()==hexAdapter.getHexagon().getX()&&
			this.hexagon.getY()==hexAdapter.getHexagon().getY() &&
			this.hexagon.getR()==hexAdapter.getHexagon().getR()
					)	
			{	
				return true;}
				else {
					return false;
				}
				}
		else {
			return false; 
		}
			}*/
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof HexagonAdapter) {
			Hexagon hex=((HexagonAdapter) obj).hexagon;
			return hexagon.getX()==hex.getX()&&hexagon.getY()==hex.getY()&&hexagon.getR()==hex.getR();
		}
		return false;
	}
	
	public Hexagon getHexagon() {
		return hexagon;
	}
	@Override
	public boolean isSelected() {
		return this.hexagon.isSelected();
	}
	@Override
	public void setSelected(boolean selected) {
		this.hexagon.setSelected(selected);
		super.setSelected(selected);
	}
	
	public int getX() {
		return hexagon.getX();
	}
	public void setX(int x) {
		hexagon.setX(x);
	}
	public int getY() {
		return hexagon.getY();
	}
	public void setY(int y) {
		hexagon.setY(y);
	}
	public int getRadius() {
		return hexagon.getR();
	}
	
	public void setRadius(int radius) throws Exception{
		if(radius>=0)
		{
		this.hexagon.setR(radius);}
		else
			throw new NumberFormatException("Radius has to be a value greater then 0!");
	}
	public Color getEdgeColor() {
		return this.hexagon.getBorderColor();
	}
	public void setEdgeColor(Color edgeColor) {
		this.hexagon.setBorderColor(edgeColor);
	}
	public Color getFillColor() {
		return this.hexagon.getAreaColor();
	}
	public void setFillColor(Color fillColor) {
		this.hexagon.setAreaColor(fillColor);
		
	}
	public String toString() {
		return "Hexagon: radius=" + hexagon.getR() + "; x=" + hexagon.getX() + "; y=" + hexagon.getY() + "; edge color=" + getEdgeColor().toString().substring(14).replace('=', '-') + "; area color=" + getFillColor().toString().substring(14).replace('=', '-');
	}

}
	
	
	


