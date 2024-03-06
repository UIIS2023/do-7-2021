package strategy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import geometry.Shape;
import mvc.DrawingModel;

public class SavePainting implements SaveAndOpenFile {

	private FileOutputStream fileOutputStream;
	private FileInputStream fileInputStream;
	private DrawingModel model;
	
	public SavePainting(DrawingModel model) {
		this.model = model;
	}
	
	public SavePainting() {
		
	}
	@Override
	public void save(Object o, File f) {
		DrawingModel model=(DrawingModel)o;
		ObjectOutputStream ous=null;
		try {
			ous= new ObjectOutputStream(new FileOutputStream(f));
			ous.writeObject(model.getShapes());
			ous.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public void open(File file) {
		try {
			fileInputStream= new FileInputStream(file);
			ObjectInputStream objectInputStream= new ObjectInputStream(fileInputStream);
			model.addMultiple((ArrayList<Shape>)objectInputStream.readObject());
			objectInputStream.close();
			fileInputStream.close();
		}catch(Exception e) {
			System.out.print(e.getMessage());
		}
	}

}
