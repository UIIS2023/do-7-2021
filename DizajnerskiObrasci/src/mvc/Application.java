package mvc;

import java.awt.Color;

import javax.swing.JFrame;

public class Application {

	public static void main(String[] args) {
		System.out.println("Dobrodošli na vežbe iz predmeta Dizajnerski obrasci.");
		DrawingModel model = new DrawingModel();
		DrawingFrame frame = new DrawingFrame();
		frame.getView().setModel(model);
		DrawingController controller = new DrawingController(model, frame);
		frame.setControler(controller);
		frame.setSize(1300,750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
}



