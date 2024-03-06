package command;

import java.awt.Color;

import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import mvc.DrawingModel;

public class TestCommand {

	public void main(String[] agrs) {
		
		
		DrawingModel model= new DrawingModel();
		Point p1= new Point();
		AddPointCmd addPointCmd= new AddPointCmd(p1,model);
		addPointCmd.execute();
		System.out.println(model.getShapes().size());
		
		addPointCmd.unexecute();
		System.out.println(model.getShapes().size());
		
		addPointCmd.execute();
		System.out.println(model.getShapes().size());
		
		RemovePointCmd removePointCmd = new RemovePointCmd(p1,model);
		
		removePointCmd.execute();
		System.out.println(model.getShapes().size());
		
		removePointCmd.unexecute();
		System.out.println(model.getShapes().size());
		Point p2 = new Point(20, 20, Color.BLACK);
		UpdatePointCmd updatePointCmd = new UpdatePointCmd(p1, p2);

		updatePointCmd.execute();
		System.out.println(p1);
		//p1 = p2;
		updatePointCmd.unexecute();
		System.out.println(p1);
		
		Point p3 = new Point(30, 30, Color.BLACK);
		Point p4 = new Point(40, 40, Color.BLACK);
		Line l1 = new Line(p1, p2, Color.BLACK);
		Line l2 = new Line(p3, p4, Color.RED);

		AddLineCmd addLineCmd = new AddLineCmd(l1, model);
		RemoveLineCmd removeLineCmd = new RemoveLineCmd(l1,model);

		addLineCmd.execute();
		System.out.println(model.getShapes().size()); // ---> 2

		removeLineCmd.execute();
		System.out.println(model.getShapes().size()); // ---> 1

		removeLineCmd.unexecute();
		System.out.println(model.getShapes().size()); // ---> 2

		addLineCmd.unexecute();
		System.out.println(model.getShapes().size()); // ---> 1
		
		
		//Testiranje modifikacije linije
				UpdateLineCmd updateLineCmd = new UpdateLineCmd(l1, l2);

				updateLineCmd.execute();
				System.out.println(l1);

				p3.setX(100);
				System.out.println(l1);

				updateLineCmd.unexecute();
				System.out.println(l1);
				
				
				//Testiranje dodavanja i brisanja kruga
				Circle c= new Circle(p3,45,Color.BLACK,Color.RED);
				Circle c2= new Circle(p1,75,Color.BLUE,Color.RED);
				AddCircleCmd addcirclecmd = new AddCircleCmd(c,model);
				addcirclecmd.execute();
				System.out.println(model.getShapes().size()); // ---> 1
				
				addcirclecmd.unexecute();
				System.out.println(model.getShapes().size()); // ---> 0
				addcirclecmd.execute();
				RemoveCircleCmd removecirclecmd = new RemoveCircleCmd(c,model);
				removecirclecmd.execute();
				System.out.println(model.getShapes().size()); // ---> 0
				removecirclecmd.unexecute();
				System.out.println(model.getShapes().size()); // ---> 1
				
				
				//Testiranje modifikacije Kruga
				UpdateCircleCmd updateCircleCmd = new UpdateCircleCmd(c, c2);

				updateLineCmd.execute();
				System.out.println(c);

				p3.setX(100);
				System.out.println(c);

				updateLineCmd.unexecute();
				System.out.println(c);
				
				//Testiranje dodavanja i brisanja pravougaonika
				Rectangle r1= new Rectangle(p3,45,55,Color.BLACK,Color.RED);
				Rectangle r2= new Rectangle(p2,85,55,Color.BLACK,Color.BLUE);
				AddRectangleCmd addreccmd = new AddRectangleCmd(r1,model);
				addreccmd.execute();
				System.out.println(model.getShapes().size()); // ---> 1
				
				addreccmd.unexecute();
				System.out.println(model.getShapes().size()); // ---> 0
				addreccmd.execute();
				RemoveRectangleCmd removereccmd = new RemoveRectangleCmd(r1,model);
				removereccmd.execute();
				System.out.println(model.getShapes().size()); // ---> 0
				removereccmd.unexecute();
				System.out.println(model.getShapes().size()); // ---> 1
				
				
				//Testiranje modifikacije Kruga
				UpdateRectangleCmd updaterecCmd = new UpdateRectangleCmd(r1, r2);

				updaterecCmd.execute();
				System.out.println(r1);

				p3.setX(100);
				System.out.println(r1);

				updaterecCmd.unexecute();
				System.out.println(r1);
				
				//Testiranje dodavanja i brisanja krugova sa rupom
				Donut d1= new Donut(p3,75,55,Color.BLACK,Color.RED);
				Donut d2= new Donut(p2,85,55,Color.BLACK,Color.BLUE);
				AddDonutCmd adddonutcmd = new AddDonutCmd(d1,model);
				adddonutcmd.execute();
				System.out.println(model.getShapes().size()); // ---> 1
				
				adddonutcmd.unexecute();
				System.out.println(model.getShapes().size()); // ---> 0
				adddonutcmd.execute();
				RemoveDonutCmd removedonutcmd = new RemoveDonutCmd(d1,model);
				removedonutcmd.execute();
				System.out.println(model.getShapes().size()); // ---> 0
				removedonutcmd.unexecute();
				System.out.println(model.getShapes().size()); // ---> 1
				
				
				//Testiranje modifikacije Kruga
				UpdateDonutCmd updatdonutCmd = new UpdateDonutCmd(d1, d2);

				updatdonutCmd.execute();
				System.out.println(r1);

				p3.setX(100);
				System.out.println(r1);

				updatdonutCmd.unexecute();
				System.out.println(r1);
				
				
	 }
}
