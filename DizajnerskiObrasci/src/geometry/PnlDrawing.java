 package geometry;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import applications.ModPoint;
import applications.ModLine;
import geometry.Circle;
import geometry.Donut;
import geometry.Point;
import applications.DlgCircle;
import applications.DlgDonut;
import applications.DlgRectangle;
import applications.Drawing;

public class PnlDrawing extends JPanel {

	private Drawing frame; // Deklarisemo kako bi mogli da detektujemo koje je dugme pritisnuto u klasi Drawing
	private ArrayList<Shape> shape = new ArrayList<Shape>(); 
	private Point startPoint;
	private Shape tempShape;
	
	public ArrayList<Shape> getShape() {
		return shape;
	}

	public void setShape(ArrayList<Shape> shape) {
		this.shape = shape;
	}

	public Shape getTempShape() {
		return tempShape;
	}

	public void setTempShape(Shape tempShape) {
		this.tempShape = tempShape;
	}
	
	public PnlDrawing() {
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				thisMouseClicked(e);
			}
		});
	}
	
	// Konstruktor kojim cemo povezati u klasi Drawing da bi mogli da iscrtavamo
	
	public PnlDrawing(Drawing frame) {
		this.frame=frame;
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			thisMouseClicked(e);
			}
		});
	}
	
	// Protected metode su vidljive samo u datom paketu
	
	protected void thisMouseClicked(MouseEvent e) {
		
		if(frame.getTglbtnSelect())
		{
			// Biramo poslednji iscrtan element u slucaju biranja preseka izmedju 2 elementa
			
			tempShape = null;
			Point p = new Point(e.getX(),e.getY());
			Iterator<Shape> it = shape.iterator();
			while(it.hasNext())
			{
				Shape temp = it.next();
				temp.setSelected(false);
				if((temp.contains(p.getX(), p.getY())) && temp.isSelected() == false)
				{
					tempShape = temp;
				}
			}		
		}
		
		if(frame.getTglbtnPoint()) {
			
			Point shapeTemp = new Point(e.getX(), e.getY());
			
			Point p = new Point(e.getX(), e.getY());
			
			
			
			ModPoint dialog = new ModPoint();
			
			dialog.setTxtX(Integer.toString(p.getX()));
			dialog.setTxtY(Integer.toString(p.getY()));
			
			dialog.setTxtXEdit(false);
			dialog.setTxtYEdit(false);
			
			dialog.setVisible(true);
			
			shapeTemp.setColor(dialog.getColor());
			if(frame.isBorderIdentificator()) {
				shapeTemp.setColor(frame.getBorderColor());
			}
			
			if(dialog.isOk())
				shape.add(shapeTemp); 
		}
		
		else if(frame.getTglbtnLine())
		{
			Point p = new Point(e.getX(), e.getY());
			
			ModLine dialog = new ModLine();
			
			
			if (startPoint == null) {
				startPoint = new Point(e.getX(), e.getY());
			}
			else {
				
				// U otvorenom dijalogu se prikazuju vrednosti X i Y koordinate pocetne tacke
				
				dialog.setTxtStartX(Integer.toString(startPoint.getX()));
				dialog.setTxtStartY(Integer.toString(startPoint.getY()));
				
				dialog.setTxtStartXEdit(false);
				dialog.setTxtStartYEdit(false);
				
				// Pravimo novi temp koristeci konstruktor iz klase Line 
				
				Line shapeTemp = new Line(startPoint, new Point(e.getX(), e.getY()));
				
				dialog.setTxtEndX(Integer.toString(p.getX()));
				dialog.setTxtEndY(Integer.toString(p.getY()));
				
				dialog.setTxtEndXEdit(false);
				dialog.setTxtEndYEdit(false);
				
				dialog.setVisible(true);
				
				shapeTemp.setColor(dialog.getColor());
				if(frame.isBorderIdentificator()) {
					shapeTemp.setColor(frame.getBorderColor());
				}
				
				if (dialog.isOk())
					shape.add(shapeTemp);
				startPoint = null;
			}
		}
		
		else if(frame.getTglbtnRectangle())
		{
			
			Point p = new Point(e.getX(),e.getY());
			
			DlgRectangle dialog = new DlgRectangle();
			
			dialog.setTxtX(Integer.toString(p.getX()));
			dialog.setTxtY(Integer.toString(p.getY()));
			
			dialog.setTxtXEdit(false);
			dialog.setTxtYEdit(false);
			
			dialog.setVisible(true);
			
			if(dialog.isOk()) {
				int height = Integer.parseInt(dialog.getTxtHeight().getText());
				int width = Integer.parseInt(dialog.getTxtWidth().getText());
				Rectangle shapeTemp = new Rectangle(p,width,height);
				shapeTemp.setEdgeColor(dialog.getEdgeColor());
				shapeTemp.setFillColor(dialog.getFillColor());
				if(frame.isInnerIndetificator()|| frame.isBorderIdentificator()) {
					shapeTemp.setEdgeColor(frame.getBorderColor());
					shapeTemp.setFillColor(frame.getInnerColor());	
				}
				
				shape.add(shapeTemp);
			}
		}
		
		else if(frame.getTglbtnCircle())
		{
			
			Point p = new Point(e.getX(),e.getY());
			
			DlgCircle dialog = new DlgCircle();
			
			dialog.setTxtX(Integer.toString(p.getX()));
			dialog.setTxtY(Integer.toString(p.getY()));
			
			dialog.setTxtXEdit(false);
			dialog.setTxtYEdit(false);
			
			dialog.setVisible(true);
			
			if(dialog.isOk())
			{
				int radius = Integer.parseInt(dialog.getTxtRadius());
				Circle shapeTemp = new Circle(p,radius);
				shapeTemp.setEdgeColor(dialog.getEdgeColor());
				shapeTemp.setFillColor(dialog.getFillColor());
				if(frame.isBorderIdentificator() || frame.isInnerIndetificator()) {
					shapeTemp.setEdgeColor(frame.getBorderColor());
					shapeTemp.setFillColor(frame.getInnerColor());
				}
				shape.add(shapeTemp);
			}
		}
		
		else if(frame.getTglbtnDonut())
		{
			
			Point p = new Point(e.getX(),e.getY()); 
			
			DlgDonut dialog = new DlgDonut();
			
			dialog.setTxtX(Integer.toString(p.getX()));
			dialog.setTxtY(Integer.toString(p.getY()));
			
			dialog.setTxtXEdit(false);
			dialog.setTxtYEdit(false);
			
			dialog.setVisible(true);
			
			if(dialog.isOk())
			{
				int innerRadius = Integer.parseInt(dialog.getTxtInner());
				int outerRadius = Integer.parseInt(dialog.getTxtOuter());
				Donut shapeTemp = new Donut(p,outerRadius,innerRadius);
				shapeTemp.setEdgeColor(dialog.getEdgeColor());
				shapeTemp.setEdgeColorDonut(dialog.getEdgeColor());
				shapeTemp.setFillColor(dialog.getFillColor());
				shapeTemp.setFillColorDonut(dialog.getFillColor());
				if(frame.isBorderIdentificator()||frame.isInnerIndetificator()) {
					shapeTemp.setEdgeColor(frame.getBorderColor());
					shapeTemp.setEdgeColorDonut(frame.getBorderColor());
					shapeTemp.setFillColor(frame.getInnerColor());
					shapeTemp.setFillColorDonut(frame.getInnerColor());
				}
				shape.add(shapeTemp);
			}
		}
		
		else if(tempShape!=null)
		{
			tempShape.setSelected(true);
		}
		
		if(shape!=null) 
			repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Iterator it = shape.iterator();
		while(it.hasNext())
			((Shape) it.next()).draw(g);
        }

}
