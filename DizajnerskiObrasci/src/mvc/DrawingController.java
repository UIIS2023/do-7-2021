package mvc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Stack;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import adapter.HexagonAdapter;
import applications.DlgCircle;
import applications.DlgDonut;
import applications.DlgHexagon;
import applications.DlgRectangle;
import applications.Drawing;
import applications.ModLine;
import applications.ModPoint;
import command.AddCircleCmd;
import command.AddDonutCmd;
import command.AddLineCmd;
import command.AddPointCmd;
import command.AddRectangleCmd;
import command.AddShapeCmd;
import command.BringToDownCmd;
import command.BringToFrontCmd;
import command.Command;
import command.DeselectShapeCmd;
import command.RemoveCircleCmd;
import command.RemoveDonutCmd;
import command.RemoveLineCmd;
import command.RemovePointCmd;
import command.RemoveRectangleCmd;
import command.RemoveShapeCmd;
import command.SelectedShapeCmd;
import command.ToBackCmd;
import command.ToFrontCmd;
import command.UpdateCircleCmd;
import command.UpdateDonutCmd;
import command.UpdateHexagonCmd;
import command.UpdateLineCmd;
import command.UpdatePointCmd;
import command.UpdateRectangleCmd;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import hexagon.Hexagon;
import observer.Observer;
import observer.ObserverUpdate;
import strategy.SaveLog;
import strategy.SaveManagerFile;
import strategy.SavePainting;

public class DrawingController extends JPanel {

	 // Deklarisemo kako bi mogli da detektujemo koje je dugme pritisnuto u klasi Drawing
	private ArrayList<Shape> shape = new ArrayList<Shape>(); 
	private Point startPoint;
	private Shape tempShape; 
	private ArrayList<Shape> selectedShapes= new ArrayList<Shape>();;
	private Stack<Command> undoStack = new Stack<Command>();
	private Stack<Command> redoStack = new Stack<Command>();
	private DefaultListModel<String> logText= new DefaultListModel<String>();
	private Observer observer = new Observer();
	private ObserverUpdate observerUpdate;
	private SaveLog openLog; 
	private BufferedReader reader;
	
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
	public void setSelectedShapes(ArrayList<Shape> selectedShapes) {
		for(Shape s:selectedShapes) {
			s.setSelected(true);
		}
		this.selectedShapes=selectedShapes;
	}
	public ArrayList<Shape> isSelectedShapes(){
		return selectedShapes;
	}
	
	
	DrawingModel model;
    DrawingFrame frame;
    private Command command;
	
	
	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
		logText=frame.getlist();
		observer= new Observer();
		observerUpdate= new ObserverUpdate(frame);
		observer.addPropertyChangeListener(observerUpdate);
		
	}
	// DrawingController controler= new DrawingController();
	
	public DrawingController() {
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				mouseClicked(e);
			}
		});
	}
		public DrawingController(DrawingFrame frame) {
			this.frame=frame;
			addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
				mouseClicked(e);
				}
			});
		}
		


	public void mouseClicked(MouseEvent e) throws NumberFormatException {
		notifyAllObservers(0);
		if(frame.getTglbtnSelect())
		{
			// Biramo poslednji iscrtan element u slucaju biranja preseka izmedju 2 elementa
			
			//tempShape = null;

			/*Point p = new Point(e.getX(),e.getY());
			Iterator<Shape> it=model.shapes.iterator();
			while(it.hasNext())
			{
				Shape temp= it.next();
				
				if(temp.contains(p.getX(), p.getY())) {
					temp.setSelected(!temp.isSelected());
					SelectedShapeCmd selectedShapecmd= new SelectedShapeCmd(selectedShapes,temp,model);
					if(temp.isSelected()) {		
						selectedShapecmd.execute();
						undoStack.push(selectedShapecmd);
					}
					else{
						//SelectedShapeCmd selectedShapeCmd= new SelectedShapeCmd(selectedShapes,temp);
						selectedShapecmd.unexecute();
						undoStack.push(selectedShapecmd);
					}
				}
			}
				if(selectedShapes.size()==1) {
				ArrayList<Shape>tempaaa= new ArrayList<Shape>();
				tempaaa=isSelectedShapes();
				tempShape=tempaaa.get(0);
				
				}else {
					tempShape=null;
				}*/
			int size=selectedShapes.size();
			System.out.println(size);
			notifyAllObservers(size);
			Point p = new Point(e.getX(),e.getY());
			for(int i=0;i<=model.getShapes().size()-1;i++) {
				if(model.get(i).contains(p.getX(),p.getY())) {
					if(!model.get(i).isSelected()) {
						SelectedShapeCmd select= new SelectedShapeCmd(model.get(i),model);
						select.execute();
						selectedShapes.add(model.get(i));
						undoStack.push(select);
						redoStack.clear();
						logText.addElement(select.logText());
						notifyAllObservers(selectedShapes.size());
					}
					else {
						DeselectShapeCmd deselect=new DeselectShapeCmd(model.get(i),model);
						deselect.execute();
						selectedShapes.remove(model.get(i));
						undoStack.push(deselect);
						redoStack.clear();
						logText.addElement(deselect.logText());
						notifyAllObservers(selectedShapes.size());
					}
				}
				notifyAllObservers(selectedShapes.size());
			}
			if(selectedShapes.size()==1) {
				ArrayList<Shape>tempaaa= new ArrayList<Shape>();
				tempaaa=isSelectedShapes();
				tempShape=tempaaa.get(0);
				
				}else {
					tempShape=null;
				}
		}
		if(frame.getTglbtnPoint())
		{
	Point shapeTemp = new Point(e.getX(), e.getY());
			
			Point p = new Point(e.getX(), e.getY());
			
			
			
			ModPoint dialog = new ModPoint();
			
			dialog.setTxtX(Integer.toString(p.getX()));
			dialog.setTxtY(Integer.toString(p.getY()));
			
			dialog.setTxtXEdit(false);
			dialog.setTxtYEdit(false);
			
			dialog.setVisible(true);
			/*if(frame.isBorderIdetificator()) {
			shapeTemp.setColor(frame.getBorderColor());
			}
			else {
			shapeTemp.setColor(dialog.getColor());
			}*/
			shapeTemp.setColor(frame.getBorderColor());
			if(dialog.isOk())
			{
				
			    command = new AddShapeCmd(shapeTemp, model);
				command.execute();
				undoStack.push(command);
				redoStack.clear();
				/*undoStack.push(shapeTemp);
				model.add(shapeTemp);
				frame.repaint();
				//shape.add(shapeTemp); */
				logText.addElement(command.logText());
				frame.setTglbtnUndoEnabled(true);
				frame.setTglbtnRedoEnabled(false);
				notifyAllObservers(selectedShapes.size());
			}
			
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
				/*if(frame.isBorderIdetificator()) {
				shapeTemp.setColor(frame.getBorderColor());
				}
				else {
				shapeTemp.setColor(dialog.getColor());
				}*/
				shapeTemp.setColor(frame.getBorderColor());
				if (dialog.isOk()) {
					//shape.add(shapeTemp);
				startPoint = null;
				command = new AddShapeCmd(shapeTemp, model);
				command.execute();
				undoStack.push(command);
				redoStack.clear();
				/*undoStack.push(shapeTemp);
				model.add(shapeTemp);
				frame.repaint();*/
				logText.addElement(command.logText());
				frame.setTglbtnUndoEnabled(true);
				frame.setTglbtnRedoEnabled(false);
				notifyAllObservers(selectedShapes.size());
			}
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
			/*	if(frame.isBorderIdetificator() || frame.isInnerIdentificator())
				{
				shapeTemp.setEdgeColor(frame.getBorderColor());
				shapeTemp.setFillColor(frame.getInnerColor());
				}*/
				shapeTemp.setEdgeColor(frame.getBorderColor());
				shapeTemp.setFillColor(frame.getInnerColor());
				command = new AddShapeCmd(shapeTemp, model);
				command.execute();
				undoStack.push(command);
				redoStack.clear();
				//shape.add(shapeTemp);
				/*undoStack.push(shapeTemp);
				model.add(shapeTemp);
				frame.repaint();*/
				logText.addElement(command.logText());
				frame.setTglbtnUndoEnabled(true);
				frame.setTglbtnRedoEnabled(false);
				notifyAllObservers(selectedShapes.size());
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
			/*	if(frame.isBorderIdetificator() || frame.isInnerIdentificator()) {
				shapeTemp.setEdgeColor(frame.getBorderColor());
				shapeTemp.setFillColor(frame.getInnerColor());
				}*/
				shapeTemp.setEdgeColor(frame.getBorderColor());
				shapeTemp.setFillColor(frame.getInnerColor());
				command = new AddShapeCmd(shapeTemp, model);
				command.execute();
				undoStack.push(command);
				redoStack.clear();
				//shape.add(shapeTemp);
				/*undoStack.push(shapeTemp);
				model.add(shapeTemp);
				frame.repaint();*/
				logText.addElement(command.logText());
				frame.setTglbtnUndoEnabled(true);
				frame.setTglbtnRedoEnabled(false);
				notifyAllObservers(selectedShapes.size());
			}
		}
		else if(frame.getTglBtnHexagon()) {
            Point p = new Point(e.getX(),e.getY());
			
            DlgHexagon dialog = new DlgHexagon();
			
			dialog.setTxtX(Integer.toString(p.getX()));
			dialog.setTxtY(Integer.toString(p.getY()));
			
			dialog.setTxtXEdit(false);
			dialog.setTxtYEdit(false);
			
			dialog.setVisible(true);
			
			if(dialog.isOk())
			{
				int radius = Integer.parseInt(dialog.getTxtRadius());
				HexagonAdapter shapeTemp = new HexagonAdapter(p,radius);
				shapeTemp.setEdgeColor(dialog.getEdgeColor());
				shapeTemp.setFillColor(dialog.getFillColor());
				/*if(frame.isBorderIdetificator() || frame.isInnerIdentificator()) {
				shapeTemp.setEdgeColor(frame.getBorderColor());
				shapeTemp.setFillColor(frame.getInnerColor());
				}*/
				shapeTemp.setEdgeColor(frame.getBorderColor());
				shapeTemp.setFillColor(frame.getInnerColor());
				command = new AddShapeCmd(shapeTemp, model);
				command.execute();
				undoStack.push(command);
				redoStack.clear();		
				frame.setTglbtnUndoEnabled(true);
				frame.setTglbtnRedoEnabled(false);
				logText.addElement(command.logText());
				System.out.println(undoStack.size());
				notifyAllObservers(selectedShapes.size());
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
				/*if(frame.isBorderIdetificator() || frame.isInnerIdentificator()) {
				shapeTemp.setFillColor(frame.getInnerColor());
				shapeTemp.setFillColorDonut(frame.getInnerColor());
				shapeTemp.setEdgeColor(frame.getBorderColor());
				shapeTemp.setEdgeColorDonut(frame.getBorderColor());
				}*/
				shapeTemp.setFillColor(frame.getInnerColor());
				shapeTemp.setFillColorDonut(frame.getInnerColor());
				shapeTemp.setEdgeColor(frame.getBorderColor());
				shapeTemp.setEdgeColorDonut(frame.getBorderColor());
				command = new AddShapeCmd(shapeTemp, model);
				command.execute();
				undoStack.push(command);
				redoStack.clear();
				logText.addElement(command.logText());
				frame.setTglbtnUndoEnabled(true);
				frame.setTglbtnRedoEnabled(false);
				//shape.add(shapeTemp);
				/*undoStack.push(shapeTemp);
				model.add(shapeTemp);
				frame.repaint();*/
				notifyAllObservers(selectedShapes.size());
			}
		}
		else if(frame.getTglbtnModify()) {
			if(!selectedShapes.isEmpty())  
			{
				Shape tempShape =selectedShapes.get(0);
				ArrayList<Shape> lst =model.getShapes();
				int index = lst.indexOf(tempShape);
				if(selectedShapes.get(0) instanceof Point)
				{
					ModPoint mp = new ModPoint();
					
					// Ne postavljamo X i Y na uneditable
					
					mp.setTxtX(Integer.toString(((Point)tempShape).getX()));
					mp.setTxtY(Integer.toString(((Point)tempShape).getY()));
					mp.setColor(((Point)tempShape).getColor());
				
					mp.setTxtXEdit(false);
					mp.setTxtYEdit(false);
					
					mp.setVisible(true);
					
					// Podaci se cuvaju u IF bloku u slucaju da korisnik odustane od modifikacije
					
					if(mp.isOk()) {  
						

						Point newPointState = new Point(
					            Integer.parseInt(mp.getTxtX()),
					            Integer.parseInt(mp.getTxtY()),
					            mp.getColor()
					        );
					        command = new UpdatePointCmd(
					            (Point) tempShape,
					            newPointState
					        );
					        command.execute();
					        undoStack.push(command);
					        redoStack.clear();
					       // tempShape.setSelected(false);
					        logText.addElement(command.logText());
					        notifyAllObservers(selectedShapes.size());
					    }
						
					
					else {
						selectedShapes.get(0).setSelected(false);
						setTempShape(tempShape);
						//model.add(tempShape);
						frame.repaint();
					}
					
				}
				else if(tempShape instanceof Line)
				{
					ModLine ml = new ModLine();
					
					ml.setTxtStartXEdit(false);
					ml.setTxtStartYEdit(false);
					ml.setTxtEndXEdit(false);
					ml.setTxtEndYEdit(false);
					
					ml.setTxtStartX(Integer.toString(((Line) tempShape).getStartPoint().getX()));
					ml.setTxtStartY(Integer.toString(((Line) tempShape).getStartPoint().getY()));
					ml.setTxtEndX(Integer.toString(((Line) tempShape).getEndPoint().getX()));
					ml.setTxtEndY(Integer.toString(((Line) tempShape).getEndPoint().getY()));	
					ml.setColor(((Line) tempShape).getColor());
					//Point s1= new Point(((Point) tempShape).getX());

					ml.setVisible(true);

					if(ml.isOk())
					{			

						Line newLine=new Line();
						newLine.setStartPoint(new Point((Integer.parseInt(ml.getTxtStartX())),(Integer.parseInt(ml.getTxtStartY()))));
						newLine.setEndPoint(new Point((Integer.parseInt(ml.getTxtEndX())),(Integer.parseInt(ml.getTxtEndY()))));
						newLine.setColor(ml.getColor());
					      
						command= new UpdateLineCmd(
					            (Line) tempShape,newLine
					            
					        );

						command.execute();
					      undoStack.push(command);
					      redoStack.clear();
					    //  tempShape.setSelected(false);
					      logText.addElement(command.logText());
					      notifyAllObservers(selectedShapes.size());
					}
					else {
						tempShape.setSelected(false);
						setTempShape(tempShape);
						//frame.repaint();
					}
				}
				else if(tempShape instanceof Rectangle)
				{
					DlgRectangle dr = new DlgRectangle();
					
					dr.setTxtXEdit(false);
					dr.setTxtYEdit(false);
					
					dr.setTxtX(Integer.toString(((Rectangle)tempShape).getUpperLeftPoint().getX()));
					dr.setTxtY(Integer.toString(((Rectangle)tempShape).getUpperLeftPoint().getY()));
					dr.setTxtWidth(Integer.toString(((Rectangle)tempShape).getWidth()));
					dr.setTxtHeight(Integer.toString(((Rectangle)tempShape).getHeight()));
					dr.setFillColor(((Rectangle)tempShape).getFillColor());
					dr.setEdgeColor(((Rectangle)tempShape).getEdgeColor());
					
					
					dr.setVisible(true);
					
					if(dr.isOk())
					{

						Point newUpperLeftPointState = new Point(
					            Integer.parseInt(dr.getTxtX().getText()),
					            Integer.parseInt(dr.getTxtY().getText()));
						
						        int newWidth = Integer.parseInt(dr.getTxtWidth().getText());
						        int newHeight = Integer.parseInt(dr.getTxtHeight().getText());
						        Color newFillColor=dr.getFillColor();
						        Color newEdgeColor=dr.getEdgeColor();
						       
						    

						        Rectangle newRectangleState = new Rectangle(newUpperLeftPointState, newWidth, newHeight,newEdgeColor,newFillColor);
						    
						        command = new UpdateRectangleCmd(
						            (Rectangle) tempShape,
						            newRectangleState
						        );
						        command.execute();
						        undoStack.push(command);
						        redoStack.clear();
						       // tempShape.setSelected(false);
						        logText.addElement(command.logText());
						        notifyAllObservers(selectedShapes.size());
					}			
					else {
						tempShape.setSelected(false);
						setTempShape(tempShape);
						//model.add(tempShape);
						//frame.repaint();
					}
				}
				else if(tempShape instanceof Donut)
				{
					DlgDonut dd = new DlgDonut();
					
					dd.setTxtXEdit(false);
					dd.setTxtYEdit(false);
					
					dd.setTxtX(Integer.toString(((Donut)tempShape).getCenter().getX()));
					dd.setTxtY(Integer.toString(((Donut)tempShape).getCenter().getY()));
					dd.setTxtInner(Integer.toString(((Donut)tempShape).getInnerRadius()));
					dd.setTxtOuter(Integer.toString(((Donut)tempShape).getRadius()));
					dd.setEdgeColor(((Donut) tempShape).getEdgeColorDonut());
					dd.setFillColor(((Donut) tempShape).getFillColorDonut());
					
					dd.setVisible(true);
				
					if(dd.isOk())
					{
					/*	((Donut)tempShape).setCenter(new Point(Integer.parseInt(dd.getTxtX()),Integer.parseInt(dd.getTxtY())));
						((Donut)tempShape).setInnerRadius(Integer.parseInt(dd.getTxtInner()));
						
						try {
							((Donut)tempShape).setRadius(Integer.parseInt(dd.getTxtOuter()));
						} catch (NumberFormatException ex) {
							ex.printStackTrace();
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "Invalid input.", "Error!", JOptionPane.WARNING_MESSAGE);
						}
						
						((Donut) tempShape).setEdgeColor(dd.getEdgeColor());
						((Donut) tempShape).setEdgeColorDonut(dd.getEdgeColor());
						((Donut) tempShape).setFillColor(dd.getFillColor());
						((Donut) tempShape).setFillColorDonut(dd.getFillColor());
						
						lst.set(index,tempShape);
						setShape(lst);
						tempShape.setSelected(true);
						setTempShape(tempShape);
						//model.add(tempShape);
						//undoStack.push(tempShape);
						//frame.repaint();
						tempShape.setSelected(false);*/
						
						Donut newDonut= new Donut();
						try {
							newDonut.setRadius(Integer.parseInt(dd.getTxtOuter()));
						} catch(NumberFormatException ex) {
							ex.printStackTrace();
						} catch(Exception ex) {
							JOptionPane.showMessageDialog(null, "Invalid input.", "Error!", JOptionPane.WARNING_MESSAGE);
						}
						newDonut.setInnerRadius(Integer.parseInt(dd.getTxtInner()));
						newDonut.setCenter(new Point(Integer.parseInt(dd.getTxtX()),Integer.parseInt(dd.getTxtY())));
						newDonut.setEdgeColor(dd.getEdgeColor());
						newDonut.setEdgeColorDonut(dd.getEdgeColor());
						newDonut.setFillColor(dd.getFillColor());
						newDonut.setFillColorDonut(dd.getFillColor());
						
						command = new UpdateCircleCmd((Donut)tempShape,newDonut);
						command.execute();
						undoStack.push(command);
						redoStack.clear();
						//tempShape.setSelected(false);
						logText.addElement(command.logText());
						notifyAllObservers(selectedShapes.size());

						}	
					else {
						tempShape.setSelected(false);
						setTempShape(tempShape);
						//model.add(tempShape);
						//frame.repaint();						
						}
				}
				else if(tempShape instanceof Circle)
				{
					
					DlgCircle dc = new DlgCircle();
					
					dc.setTxtXEdit(false);
					dc.setTxtYEdit(false);
					
					dc.setTxtX(Integer.toString(((Circle)tempShape).getCenter().getX()));
					dc.setTxtY(Integer.toString(((Circle)tempShape).getCenter().getY()));
					dc.setRadius(Integer.toString(((Circle)tempShape).getRadius()));
					dc.setEdgeColor(((Circle)tempShape).getEdgeColor());
					dc.setFillColor(((Circle)tempShape).getFillColor());
					
					dc.setVisible(true);
					
					if(dc.isOk())
					{

						Circle newCircle= new Circle();
						try {
							newCircle.setRadius(Integer.parseInt(dc.getTxtRadius()));
						} catch(Exception ex) {
							ex.printStackTrace();
						}			
						newCircle.setCenter(new Point(Integer.parseInt(dc.getTxtX()),Integer.parseInt(dc.getTxtY())));
						newCircle.setEdgeColor(dc.getEdgeColor());
						newCircle.setFillColor(dc.getFillColor());
						
						command = new UpdateCircleCmd((Circle)tempShape,newCircle);
						command.execute();
						undoStack.push(command);
						redoStack.clear();
						//tempShape.setSelected(false);
						logText.addElement(command.logText());
						notifyAllObservers(selectedShapes.size());

	
					}
					else {
						
						setTempShape(tempShape);
						//model.add(tempShape);
						tempShape.setSelected(false);
						//frame.repaint();						
					}
				}
				else if (tempShape instanceof HexagonAdapter) {
					DlgHexagon dx = new DlgHexagon();
					
					dx.setTxtXEdit(false);
					dx.setTxtYEdit(false);
					
					dx.setTxtX(Integer.toString(((HexagonAdapter)tempShape).getX()));
					dx.setTxtY(Integer.toString(((HexagonAdapter)tempShape).getY()));
					dx.setRadius(Integer.toString(((HexagonAdapter)tempShape).getRadius()));
					dx.setEdgeColor(((HexagonAdapter)tempShape).getEdgeColor());
					dx.setFillColor(((HexagonAdapter)tempShape).getFillColor());
					
					dx.setVisible(true);
					if(dx.isOk()) {
						HexagonAdapter newHexagon= new HexagonAdapter();
						try {
							newHexagon.setRadius(Integer.parseInt(dx.getTxtRadius()));
						} catch(Exception ex) {
							ex.printStackTrace();
						}			
						newHexagon.setX(Integer.parseInt(dx.getTxtX()));
						newHexagon.setY(Integer.parseInt(dx.getTxtY()));
						newHexagon.setEdgeColor(dx.getEdgeColor());
						newHexagon.setFillColor(dx.getFillColor());
						
						command = new UpdateHexagonCmd((HexagonAdapter)tempShape,newHexagon);
						command.execute();
						undoStack.push(command);
						redoStack.clear();
						//tempShape.setSelected(false);
						logText.addElement(command.logText());
						notifyAllObservers(selectedShapes.size());

					}
						else {
						
						setTempShape(tempShape);
						//model.add(tempShape);
						tempShape.setSelected(false);
						//frame.repaint();						
					}
				}
			}
			else
			{
				JOptionPane.showMessageDialog(new JFrame(), "You must select a shape.", "Error!", JOptionPane.WARNING_MESSAGE);
			}
		}
		else if(frame.getTglbtnUndo())
		{	
			undo();
				}
			
		else if(frame.getTglbtnRedo()) {
				redo();
	}
		else if(frame.getTglbtnBringToFront()) {
			if(!selectedShapes.isEmpty()) {
				Shape tempShape=selectedShapes.get(0);
				ArrayList<Shape>shapeList=model.getShapes();
				int index=shapeList.indexOf(tempShape);
				if(index!=-1&&index<shapeList.size()-1) { 
					BringToFrontCmd bringToFrontCmd=new BringToFrontCmd(shapeList,tempShape);
					bringToFrontCmd.execute();
					undoStack.push(bringToFrontCmd);
					logText.addElement(bringToFrontCmd.logText());
					  notifyAllObservers(selectedShapes.size());
				}
			    selectedShapes.clear();
			    for (Shape s : model.getShapes()) {
			        if (s.isSelected()) {
			            selectedShapes.add(s);
			          
			        }
			    }
			}
			else
			{
				JOptionPane.showMessageDialog(new JFrame(), "You must select a shape.", "Error!", JOptionPane.WARNING_MESSAGE);
			}
			
		}
		else if(frame.getTglbtnBringToDown()) {
			if(!selectedShapes.isEmpty()) {
				Shape tempShape=selectedShapes.get(0);
				ArrayList<Shape>shapeList=model.getShapes();
				int index=shapeList.indexOf(tempShape);
				if(index!=-1&&index>0) {
					BringToDownCmd bringToDown= new BringToDownCmd(shapeList,tempShape);
					bringToDown.execute();
					undoStack.push(bringToDown);
					redoStack.clear();
					logText.addElement(bringToDown.logText());
					frame.repaint();
					notifyAllObservers(selectedShapes.size());
					
				}
			}
			else {
				JOptionPane.showMessageDialog(new JFrame(), "You must select a shape.","Error",JOptionPane.WARNING_MESSAGE);
			}
		}
		else if(frame.getTglbtnToFront()) {
		 if(!selectedShapes.isEmpty()) {
			Shape tempShape=selectedShapes.get(0);
			ArrayList<Shape>shapeList=model.getShapes();
			int index=shapeList.indexOf(tempShape);
			if(index!=-1&&index<shapeList.size()-1) {
				ToFrontCmd toFront= new ToFrontCmd(shapeList,tempShape);
				toFront.execute();
				undoStack.push(toFront);
				logText.addElement(toFront.logText());
				notifyAllObservers(selectedShapes.size());
			}
		 }
		else {
			JOptionPane.showMessageDialog(new JFrame(), "You must select a shape.","Error",JOptionPane.WARNING_MESSAGE);
		}
		 }
		else if (frame.getTglbtnToBack()) {
			if(!selectedShapes.isEmpty()) {
				Shape tempShape=selectedShapes.get(0);
				ArrayList<Shape> shapeList=model.getShapes();
				int index=shapeList.indexOf(tempShape);
				if(index!=-1&&index>0) {
					ToBackCmd toBack= new ToBackCmd(shapeList,tempShape);
					toBack.execute();
					undoStack.push(toBack);
					logText.addElement(toBack.logText());
					 notifyAllObservers(selectedShapes.size());
				}
			}
			else{
				JOptionPane.showMessageDialog(new JFrame(), "You must select a shape.","Error",JOptionPane.WARNING_MESSAGE);
			}
		}
		
		else if(frame.getTglbtnDelete()) {
		/*	
			if(getTempShape()!=null)
			{
				
				Shape tempShape = getTempShape();
				ArrayList<Shape> lst = model.getShapes();
				//int index = lst.indexOf(tempShape);
				
				command = new RemoveShapeCmd(tempShape, model);
					command.execute();
					undoStack.push(command);
				if(JOptionPane.showConfirmDialog(new JFrame(), "Are you sure you want to delete this shape?", "Confirmation",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
				{
					//lst.remove(index);
					setShape(lst);

				}
				else {
					tempShape.setSelected(false);
					setTempShape(tempShape);
					//frame.repaint();
				}
				setTempShape(null);  
				//frame.repaint();	
			}
			if(!selectedShapes.isEmpty()) {
		
				/*ArrayList<Shape>shapesToRemove=new ArrayList<Shape>(selectedShapes);
				//ArrayList<Shape> reverse= new ArrayList<Shape>(shapesToRemove);
				//Collections.reverse(reverse);
				command=new RemoveShapeCmd(shapesToRemove,model);
				undoStack.push(command);
				command.execute();*/
			
				/*if(JOptionPane.showConfirmDialog(new JFrame(), "Are you sure you want to delete this shape?", "Confirmation",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
				{
					for(Shape s:selectedShapes) {
						
						int index=model.getShapes().indexOf(s);
						command= new RemoveShapeCmd(s,model,index);
						command.execute();
						undoStack.push(command);
						logText.addElement(command.logText());
						s.setSelected(true);
					//model.getShapes().removeAll(shapesToRemove);
						frame.setTglbtnDelete(false);
				}
					
				}
				else {
					for(Shape tempShape:selectedShapes) {
						tempShape.setSelected(false);
					}
					setTempShape(selectedShapes.get(0));
				}
				selectedShapes.clear();
				setTempShape(null);
			}

			else
			{
				JOptionPane.showMessageDialog(new JFrame(), "You must select a shape.", "Error!", JOptionPane.WARNING_MESSAGE);
			}*/
			if(!selectedShapes.isEmpty()) {
				if(JOptionPane.showConfirmDialog(new JFrame(), "Are you sure you want to delete this shape?", "Confirmation",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
				{
					for(Shape s:selectedShapes) {
						
						int index=model.getShapes().indexOf(s);
						command= new RemoveShapeCmd(s,model,index);
						command.execute();
						undoStack.push(command);
						logText.addElement(command.logText());
						s.setSelected(true);
						frame.setTglbtnDelete(false);
				}
					
				}
				else {
					for(Shape tempShape:selectedShapes) {
						tempShape.setSelected(false);
					}
					setTempShape(selectedShapes.get(0));
				}
				selectedShapes.clear();
				setTempShape(null);
			}

			else
			{
				JOptionPane.showMessageDialog(new JFrame(), "You must select a shape.", "Error!", JOptionPane.WARNING_MESSAGE);
			}
	}
	
		else if (frame.getTglBtnnSave()) {
			File fileToSave=new File("C:\\Users\\Ognjen\\Desktop\\fileToSave.txt");
			File fileToSaveLog=new File("C:\\Users\\Ognjen\\Desktop\\fileToSaveLog.txt");
			try {
				save(fileToSave,fileToSaveLog);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		
		}
		
		/*else if (frame.getTglBtnOpen()) {
		    JFileChooser fileChooser = new JFileChooser();
		    int result = fileChooser.showOpenDialog(frame);

		    if (result == JFileChooser.APPROVE_OPTION) {
		        File fileToOpen = fileChooser.getSelectedFile();
		        
		        String logFileName = fileToOpen.getName();
		        int lastDotIndex = logFileName.lastIndexOf(".");
		        
		        if (lastDotIndex != -1) {
		            logFileName = logFileName.substring(0, lastDotIndex);
		        }
		        
		        logFileName += ".txt";

		        File fileToOpenLog = new File(fileToOpen.getParentFile(), logFileName);

		        try {
		            open(fileToOpen, fileToOpenLog);
		       
		        } catch (IOException e1) {
		            e1.printStackTrace();
		        }
		    }
		}*/
		else if (frame.getTglBtnOpen()) {
		    JFileChooser fileChooser = new JFileChooser();
		    int result = fileChooser.showOpenDialog(frame);

		    if (result == JFileChooser.APPROVE_OPTION) {
		        File fileToOpen = fileChooser.getSelectedFile();
		       
		        String logFileName = fileToOpen.getName();
		        int lastDotIndex = logFileName.lastIndexOf(".");
		        
		        if (lastDotIndex != -1) {
		            logFileName = logFileName.substring(0, lastDotIndex);
		        }
		        
		        logFileName += ".txt";

		        File fileToOpenLog = new File(fileToOpen.getParentFile(), logFileName);

		        try {
		            open(fileToOpen, fileToOpenLog);
		            SaveLog saveLog= new SaveLog();
		            for(int i=0;i<saveLog.Tekst(fileToOpenLog).size();i++) {
		            	String s=saveLog.Tekst(fileToOpenLog).get(i);	
		            	// logText.addElement(s);
		            	 readLine(s);
		            }
		            frame.revalidate();
		            frame.repaint();
		        } catch (IOException e1) {
		            e1.printStackTrace();
		        }
		    }
		}


		
	

		else if(tempShape!=null)
		{
			tempShape.setSelected(true);
		}
		
		if(shape!=null) 
			frame.repaint();
	}
	 public void notifyAllObservers(int size) {
		 if(size>=1) {
			 if(size==1) {
				 observer.setBtnModifyEnabled(true);
				 observer.setBtnBringToFrontEnabled(true);
				 observer.setBtnBringToDownEnabled(true);
				 observer.setBtnToFrontEnabled(true);
				 observer.setBtnToBackEnabled(true);
			 }
			 else {
				 observer.setBtnModifyEnabled(false);
				 observer.setBtnBringToFrontEnabled(false);
				 observer.setBtnBringToDownEnabled(false);
				 observer.setBtnToFrontEnabled(false);
				 observer.setBtnToBackEnabled(false);
			 }
			 observer.setBtnDeleteEnabled(true);
			 }
		 
		 else {
			 observer.setBtnModifyEnabled(false);
			 observer.setBtnBringToFrontEnabled(false);
			 observer.setBtnBringToDownEnabled(false);
			 observer.setBtnToFrontEnabled(false);
			 observer.setBtnToBackEnabled(false);
			 observer.setBtnDeleteEnabled(false);
		 }
	 }
	 public void save(File fileToSave,File fileToSaveLog) throws IOException {
		 SaveManagerFile savePainting = new SaveManagerFile(new SavePainting());
		 SaveManagerFile saveLog= new SaveManagerFile(new SaveLog());
		 savePainting.save(model, fileToSave);
		 saveLog.save(frame,fileToSaveLog);
		 JOptionPane.showMessageDialog(new JFrame(), "File is succesfully saved", "Ok!", JOptionPane.INFORMATION_MESSAGE);
		 while(!model.getShapes().isEmpty()) {
			 model.clear();
			 logText.removeAllElements();
		 }
	
	 }
	 

	    public void open(File fileToOpen, File fileToOpenLog) throws IOException {
	        SaveManagerFile openPainting = new SaveManagerFile(new SavePainting(model));
	        SaveManagerFile openLog = new SaveManagerFile(new SaveLog());

	        openPainting.open(fileToOpen);
	        openLog.open(fileToOpenLog);

	        //JOptionPane.showMessageDialog(new JFrame(), "Files are successfully opened", "Ok!", JOptionPane.INFORMATION_MESSAGE);
	        notifyAllObservers(selectedShapes.size());
	        frame.setTglbtnUndoEnabled(true);
			frame.setTglbtnRedoEnabled(false);
	       /* reader = new BufferedReader(new FileReader(fileToOpenLog));
			parser = new DlgParser();
			parser.setFileLog(this);
			parser.addCommand(reader.readLine());
			parser.setVisible(true);*/
	    }
	
	
	public void readLine(String commands)
	{
		try {
			if(JOptionPane.showConfirmDialog(new JFrame(), "Are you sure you want to execute this command?", "Confirmation",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
				
				
			//JOptionPane.showMessageDialog(new JFrame(), "Files are successfully opened", "Ok!", JOptionPane.INFORMATION_MESSAGE);
			String[] commands1 = commands.split("->");
			
			switch(commands1[0]) {
			case "Undo":
				undo();
				frame.repaint();
				break;
			case "Redo":
				redo();
				frame.repaint();
				break;
			case "Add":
				Shape shape= parseShape(commands1[1].split(":")[0],commands1[1].split(":")[1]);
				 command = new AddShapeCmd(shape, model);
					command.execute();
					undoStack.push(command);
					redoStack.clear();
					//frame.getlist().addElement("Add->" + shape.toString());
					logText.addElement(command.logText());
					frame.repaint();
					break;
			case "Select":
				Shape shape1= parseShape(commands1[1].split(":")[0],commands1[1].split(":")[1]);
				selectShape(shape1);
				break;
			case "Deselect":				
				Shape shape2= parseShape(commands1[1].split(":")[0],commands1[1].split(":")[1]);
			deselectShape(shape2);
				break;
			case "Remove":
				deleteShapesinFile();
				//logText.addElement(command.logText());
				break;
			case "Update":
				modifyShape(commands1[1]);
				break;
				case "To front":
					Shape tempShape=parseShape(commands1[1].split(":")[0], commands1[1].split(":")[1]);
					command=new ToFrontCmd(model.getShapes(),tempShape);
					command.execute();
					undoStack.push(command);
					redoStack.clear();
					frame.repaint();
					logText.addElement(command.logText());
					//frame.getlist().addElement("To front->" + tempShape.toString());
				break;
				case "To back":
					Shape tempShape1=parseShape(commands1[1].split(":")[0], commands1[1].split(":")[1]);
					command =new ToBackCmd(model.getShapes(),tempShape1);
					command.execute();
					undoStack.push(command);
					redoStack.clear();
					frame.repaint();
					logText.addElement(command.logText());
					//frame.getlist().addElement("To back->" + tempShape1.toString());
					break;
				case "Bring to front":
					Shape tempShape2=parseShape(commands1[1].split(":")[0],commands1[1].split(":")[1]);
					command= new BringToFrontCmd(model.getShapes(),tempShape2);
					command.execute();
					undoStack.push(command);
					redoStack.clear();
					frame.repaint();
					logText.addElement(command.logText());
					//frame.getlist().addElement("Bring to front->" + tempShape2.toString());
					break;
				case "Bring to back":
					Shape tempShape3= parseShape(commands1[1].split(":")[0],commands1[1].split(":")[1]);
					command= new BringToDownCmd(model.getShapes(),tempShape3);
					command.execute();
					undoStack.push(command);
					redoStack.clear();
					frame.repaint();
					logText.addElement(command.logText());
					//frame.getlist().addElement("Bring to back->" + tempShape3.toString());
					break;
					
			}
		
			}else {
				
				return;
				
			}
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	private Shape parseShape(String shape, String shapeParameters) {
	System.out.println(shape);
		if (shape.equals("Point")) return parsePoint(shapeParameters);
		else if (shape.equals("Hexagon")) return parseHexagon(shapeParameters);
		else if (shape.equals("Line")) return parseLine(shapeParameters);
		else if (shape.equals("Circle")) return parseCircle(shapeParameters);
		else if (shape.equals("Rectangle")) return parseRectangle(shapeParameters);
		else if (shape.equals("Donut")) return parseDonut(shapeParameters);
		else return parseDonut(shapeParameters);
	}
	
	private Point parsePoint(String string) {
		String [] pointParts = string.split(";"); 		
		String s = pointParts[2].split("=")[1].substring(1, pointParts[2].split("=")[1].length() - 1);
		String [] colors = s.split(",");
		return new Point(Integer.parseInt(pointParts[0].split("=")[1]), Integer.parseInt(pointParts[1].split("=")[1]), new Color(Integer.parseInt(colors[0].split("-")[1]), Integer.parseInt(colors[1].split("-")[1]), Integer.parseInt(colors[2].split("-")[1])));
		
	}
	private Line parseLine(String string){
			String [] lineParts = string.split(";"); 	
			int xStart = Integer.parseInt(lineParts[0].split("=")[1]);
			int yStart = Integer.parseInt(lineParts[1].split("=")[1]);
			int xEnd = Integer.parseInt(lineParts[2].split("=")[1]);
			int yEnd = Integer.parseInt(lineParts[3].split("=")[1]);
			String s = lineParts[4].split("=")[1].substring(1, lineParts[4].split("=")[1].length() - 1);
			String [] edgeColors = s.split(",");
			return new Line(new Point(xStart, yStart), new Point(xEnd, yEnd), new Color(Integer.parseInt(edgeColors[0].split("-")[1]), Integer.parseInt(edgeColors[1].split("-")[1]), Integer.parseInt(edgeColors[2].split("-")[1])));
		
	}
	private Rectangle parseRectangle(String string) {
		String [] rectangleParts = string.split(";"); 	
		int x = Integer.parseInt(rectangleParts[0].trim().split("=")[1]);
		int y = Integer.parseInt(rectangleParts[1].split("=")[1]);
		int height = Integer.parseInt(rectangleParts[2].split("=")[1]);
		int width = Integer.parseInt(rectangleParts[3].split("=")[1]);
		String s = rectangleParts[4].split("=")[1].substring(1, rectangleParts[4].split("=")[1].length() - 1);
		String [] edgeColors = s.split(",");
		String s1 = rectangleParts[5].split("=")[1].substring(1, rectangleParts[5].split("=")[1].length() - 1);
		String [] interiorColors = s1.split(",");
		return new Rectangle(new Point(x, y), width, height, new Color(Integer.parseInt(edgeColors[0].split("-")[1]), Integer.parseInt(edgeColors[1].split("-")[1]), Integer.parseInt(edgeColors[2].split("-")[1])), new Color(Integer.parseInt(interiorColors[0].split("-")[1]), Integer.parseInt(interiorColors[1].split("-")[1]), Integer.parseInt(interiorColors[2].split("-")[1])));
	}
	private Circle  parseCircle(String string) {
		String [] circleParts = string.split(";"); 	
		int radius = Integer.parseInt(circleParts[0].split("=")[1]);
		int x = Integer.parseInt(circleParts[1].split("=")[1]);
		int y = Integer.parseInt(circleParts[2].split("=")[1]);
		String s = circleParts[3].split("=")[1].substring(1, circleParts[3].split("=")[1].length() - 1);
		String [] edgeColors = s.split(",");
		String s1 = circleParts[4].split("=")[1].substring(1, circleParts[4].split("=")[1].length() - 1);
		String [] interiorColors = s1.split(",");
		//return new Circle(new Point(x, y), radius, parseColor(edgeColors), parseColor(interiorColors));
		return new Circle(new Point(x, y), radius, new Color(Integer.parseInt(edgeColors[0].split("-")[1]), Integer.parseInt(edgeColors[1].split("-")[1]), Integer.parseInt(edgeColors[2].split("-")[1])), new Color(Integer.parseInt(interiorColors[0].split("-")[1]), Integer.parseInt(interiorColors[1].split("-")[1]), Integer.parseInt(interiorColors[2].split("-")[1])));
	}
	private Donut parseDonut(String string) {
		String [] donutParts = string.split(";"); 	
		int radius = Integer.parseInt(donutParts[0].split("=")[1]);
		int x = Integer.parseInt(donutParts[1].split("=")[1]);
		int y = Integer.parseInt(donutParts[2].split("=")[1]);
		String s = donutParts[3].split("=")[1].substring(1, donutParts[3].split("=")[1].length() - 1);
		String [] edgeColors = s.split(",");
		String s1 = donutParts[4].split("=")[1].substring(1, donutParts[4].split("=")[1].length() - 1);
		String [] interiorColors = s1.split(",");
		int innerRadius = Integer.parseInt(donutParts[5].split("=")[1]);
		/*Point p =new Point(x,y);
		Donut d = new Donut(p,radius,innerRadius);
		d.setEdgeColorDonut(new Color(Integer.parseInt(edgeColors[0].split("-")[1]), Integer.parseInt(edgeColors[1].split("-")[1]), Integer.parseInt(edgeColors[2].split("-")[1])));
		d.setFillColorDonut(new Color(Integer.parseInt(interiorColors[0].split("-")[1]), Integer.parseInt(interiorColors[1].split("-")[1]), Integer.parseInt(interiorColors[2].split("-")[1])));
		return new Donut(d);*/
		return new Donut(new Point(x, y), radius, innerRadius, new Color(Integer.parseInt(edgeColors[0].split("-")[1]), Integer.parseInt(edgeColors[1].split("-")[1]), Integer.parseInt(edgeColors[2].split("-")[1])), new Color(Integer.parseInt(interiorColors[0].split("-")[1]), Integer.parseInt(interiorColors[1].split("-")[1]), Integer.parseInt(interiorColors[2].split("-")[1])));
	}

	public HexagonAdapter parseHexagon(String string) {
		String [] hexagonParts = string.split(";"); 	
		int radius = Integer.parseInt(hexagonParts[0].split("=")[1]);
		int x = Integer.parseInt(hexagonParts[1].split("=")[1]);
		int y = Integer.parseInt(hexagonParts[2].split("=")[1]);
		String s = hexagonParts[3].split("=")[1].substring(1, hexagonParts[3].split("=")[1].length() - 1);
		String [] edgeColors = s.split(",");
		String s1 = hexagonParts[4].split("=")[1].substring(1, hexagonParts[4].split("=")[1].length() - 1);
		String [] interiorColors = s1.split(",");
		Hexagon h = new Hexagon(x, y, radius);
		h.setBorderColor(new Color(Integer.parseInt(edgeColors[0].split("-")[1]), Integer.parseInt(edgeColors[1].split("-")[1]), Integer.parseInt(edgeColors[2].split("-")[1])));
		h.setAreaColor(new Color(Integer.parseInt(interiorColors[0].split("-")[1]), Integer.parseInt(interiorColors[1].split("-")[1]), Integer.parseInt(interiorColors[2].split("-")[1])));
		return new HexagonAdapter(h);
	}
	public void undo() {
		if(!undoStack.isEmpty()) {
			//System.out.println(undoStack.size());
			   Command command = undoStack.pop();
			   command.unexecute(); 
			   redoStack.push(command);
			   
			   // System.out.println(redoStack.size());
			    
			    frame.repaint();
			    logText.addElement("Undo->"+command.logText());
			    
			    selectedShapes.clear();
			    for (Shape s : model.getShapes()) {
			        if (s.isSelected()) {
			            selectedShapes.add(s);
			            notifyAllObservers(selectedShapes.size());
			        }
			    }
			    if(undoStack.size()!=0) {
			    frame.setTglbtnRedoEnabled(true);}
			    else {
			    	frame.setTglbtnUndoEnabled(false);
			    	frame.setTglbtnRedoEnabled(true);
			    	frame.setTglbtnUndo(false);
			    }
		}
		else {
			JOptionPane.showMessageDialog(new JFrame(), "Stack is empty.", "Error!", JOptionPane.WARNING_MESSAGE);
		}
	}
	public void redo(){
		if(!redoStack.isEmpty()) {
	
		  System.out.println(redoStack.size());
		   Command command = redoStack.pop();
		   //System.out.println(redoStack.size());
		    command.execute();
		    undoStack.push(command);  
		    frame.repaint();
		    logText.addElement("Redo->"+command.logText());
		    System.out.println(redoStack.size());
		    
		    selectedShapes.clear();
		    for (Shape s : model.getShapes()) {
		        if (s.isSelected()) {
		            selectedShapes.add(s);
		            notifyAllObservers(selectedShapes.size());
		        }
		    }
		    if(redoStack.size()!=0) {
		    	frame.setTglbtnUndoEnabled(true);
		    }
		    else {
		    	frame.setTglbtnRedoEnabled(false);
		    	frame.setTglbtnUndoEnabled(true);
		    	frame.setTglbtnRedo(false);
		    }
		}else {
			JOptionPane.showMessageDialog(new JFrame(), "Stack is empty.", "Error!", JOptionPane.WARNING_MESSAGE);
		}
		    
	//System.out.println(System.currentTimeMillis());
	}
	private void selectShape(Shape shape) {
	    for (int i = 0; i < model.getShapes().size(); i++) {
	        Shape currentShape = model.get(i);
	        if (currentShape.equals(shape) && !currentShape.isSelected()) {
	            SelectedShapeCmd select = new SelectedShapeCmd(currentShape, model);
	            select.execute();
	            selectedShapes.add(currentShape);
	            undoStack.push(select);
	            redoStack.clear();
	            logText.addElement(select.logText());
	            frame.repaint();
	            notifyAllObservers(selectedShapes.size());
	            break; 
	        }
	    }
	}
	private void deselectShape(Shape shape) {
	    for (int i = 0; i <model.getShapes().size(); i++) {
	        Shape currentShape = model.get(i);
	        if (currentShape.equals(shape) && currentShape.isSelected()) {
	            DeselectShapeCmd deselect = new DeselectShapeCmd(currentShape, model);
	            deselect.execute();
	            selectedShapes.remove(currentShape);
	            undoStack.push(deselect);
	            redoStack.clear();
	            logText.addElement(deselect.logText());
	            frame.repaint();
	            notifyAllObservers(selectedShapes.size());
	            break; 
	        }
	    }
	}
	private void deleteShapesinFile() {
		if(!selectedShapes.isEmpty()) {
			//if(JOptionPane.showConfirmDialog(new JFrame(), "Are you sure you want to delete this shape?", "Confirmation",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)


				
				for(Shape s:selectedShapes) {
					
					int index=model.getShapes().indexOf(s);
					command= new RemoveShapeCmd(s,model,index);
					command.execute();
					undoStack.push(command);
					redoStack.clear();
					frame.repaint();
					logText.addElement(command.logText());
					s.setSelected(true);
					frame.setTglbtnDelete(false);			
			}				
			}
			else {
				for(Shape tempShape:selectedShapes) {
					tempShape.setSelected(false);
				}
				frame.repaint();
				
				//setTempShape(selectedShapes.get(0));
			}
			selectedShapes.clear();
			//setTempShape(null);
		}
	private void modifyShape(String shapeParameters) {
	    Shape oldShape = parseShape(shapeParameters.split(":")[0], shapeParameters.split(":")[1]);
	    int index = model.getIndexOf(oldShape);

	    if (oldShape instanceof Point) {
	        Point newPoint = parsePoint(shapeParameters.split(":")[1]);
	        command = new UpdatePointCmd((Point) model.get(index), newPoint);
	        command.execute();
	        undoStack.push(command);
	        redoStack.clear();
	        frame.repaint();
	        logText.addElement(command.logText());
	    } else if (oldShape instanceof Line) {
	        Line newLine=parseLine(shapeParameters.split(":")[1]);
	        command= new UpdateLineCmd((Line)model.get(index),newLine);
	        command.execute();
	        undoStack.push(command);
	        redoStack.clear();
	        frame.repaint();
	        logText.addElement(command.logText());
	    } else if (oldShape instanceof Circle) {
	    	Circle newCircle=parseCircle(shapeParameters.split(":")[1]);
	        command= new UpdateCircleCmd((Circle)model.get(index),newCircle);
	        command.execute();
	        undoStack.push(command);
	        redoStack.clear();
	        frame.repaint();
	        logText.addElement(command.logText());
	    } else if (oldShape instanceof Rectangle) {
	    	Rectangle newRec=parseRectangle(shapeParameters.split(":")[1]);
	        command= new UpdateRectangleCmd((Rectangle)model.get(index),newRec);
	        command.execute();
	        undoStack.push(command);
	        redoStack.clear();
	        frame.repaint();
	        logText.addElement(command.logText());
	    } else if (oldShape instanceof HexagonAdapter) {
	      	 HexagonAdapter newHex=parseHexagon(shapeParameters.split(":")[1]);
		        command= new UpdateHexagonCmd((HexagonAdapter)model.get(index),newHex);
		        command.execute();
		        undoStack.push(command);
		        redoStack.clear();
		        frame.repaint();
		        logText.addElement(command.logText());
	    } else if (oldShape instanceof Donut) {
	    	 Donut newDonut=parseDonut(shapeParameters.split(":")[1]);
		        command= new UpdateDonutCmd((Donut)model.get(index),newDonut);
		        command.execute();
		        undoStack.push(command);
		        redoStack.clear();
		        frame.repaint();
		        logText.addElement(command.logText()); 
	    }

	}

		
}
	
	



