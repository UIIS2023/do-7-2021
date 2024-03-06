package mvc;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import applications.DlgCircle;
import applications.DlgDonut;
import applications.DlgRectangle;
import applications.Drawing;
import applications.ModLine;
import applications.ModPoint;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

public class DrawingView extends JPanel {
	DrawingModel model= new DrawingModel();
	
	public void setModel(DrawingModel model) {
		this.model=model;
	}
	public void paint(Graphics g) {
		Iterator<Shape> it=model.getShapes().iterator();
		while(it.hasNext()) {
			it.next().draw(g);
		}
	}

}
