package strategy;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import mvc.DrawingController;
import mvc.DrawingFrame;
import mvc.DrawingModel;

public class SaveLog implements SaveAndOpenFile {

	
	private BufferedWriter writer;
	private BufferedReader reader;
	private DrawingFrame frame;
	private DrawingModel model;
	private DrawingController controller;
    private DefaultListModel<String> logText = new DefaultListModel<>();


	
	public SaveLog(DrawingFrame frame, DrawingModel model, DrawingController controller) {
		this.frame = frame;
		this.model = model; 
		this.controller = controller;
	}
	
	public SaveLog() {
		
	}
	
	@Override
	public void save(Object o, File f) {
		DrawingFrame frame =(DrawingFrame)o;
		BufferedWriter bf= null;
		try {
			bf= new BufferedWriter((new FileWriter(f.getAbsolutePath())));
			DefaultListModel<String> list = frame.getlist();
			for (int i = 0; i < frame.getlist().size(); i++) {
				bf.write(list.getElementAt(i));
				bf.newLine();
			}
			
			bf.close();
		
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	 @Override
	    public void open(File f) {
	        BufferedReader reader = null;
	        try {
	            reader = new BufferedReader(new FileReader(f));
	            String logText = new String();
	            //logText= new DefaultListModel<String>();
	            while ((logText = reader.readLine()) != null) {
	            	
	                System.out.println(logText);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (reader != null) {
	                try {
	                    reader.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }
	 public ArrayList<String> Tekst(File f) {
		 ArrayList<String> lis= new ArrayList<String>();
		 ArrayList<Component> listic= new ArrayList<Component>();
		 JScrollPane scrollPane= new JScrollPane();
		  BufferedReader reader = null;
	        try {
	            reader = new BufferedReader(new FileReader(f));
	            String logText = new String();
	            //logText= new DefaultListModel<String>();
	            while ((logText = reader.readLine()) != null) {
	            	lis.add(logText);
	            	listic.add(scrollPane);
	               // System.out.println(logText);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	 }
	      return lis;
	        }


		
	
	/*public void readLine(String command) {
	try {
	String[] commands1=command.split("->");
	switch(commands1[0]) {
	case "Undo":
		frame.getTglbtnUndo();
		break;
	}
	}catch(Exception e) {
	e.printStackTrace();
			}
		}*/
	}