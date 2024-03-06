package mvc;

	import java.awt.BorderLayout;
	import java.awt.EventQueue;

	import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;
	import javax.swing.border.EmptyBorder;

	import geometry.Line;
	import geometry.PnlDrawing;
	import geometry.Point;
	import geometry.Circle;
	import geometry.Rectangle;
	import geometry.Donut;
	import geometry.Shape;

	import javax.swing.JToggleButton;
	import java.awt.GridBagLayout;
	import java.awt.GridBagConstraints;
	import java.awt.Insets;
	import javax.swing.ImageIcon;
	import javax.swing.JColorChooser;

	import java.awt.Font;
	import java.awt.Dimension;
	import java.awt.event.MouseAdapter;
	import java.awt.event.MouseEvent;
	import java.awt.event.MouseMotionAdapter;
	import java.util.ArrayList;
	import java.awt.Color;
	import javax.swing.border.LineBorder;

import applications.DlgCircle;
import applications.DlgDonut;
import applications.DlgRectangle;
import applications.ModLine;
import applications.ModPoint;

import java.awt.GridLayout;
	import java.awt.Image;

	import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
	import javax.swing.JButton;
	import java.awt.event.ActionListener;
	import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextArea;

	public class DrawingFrame extends JFrame {

		private JPanel contentPane;
		JToggleButton tglbtnPoint;
		JToggleButton tglbtnLine;
		JToggleButton tglbtnRectangle;
		JToggleButton tglbtnCircle;
		JToggleButton tglbtnDonut;
		JToggleButton tglbtnSelect;
		JToggleButton tglbtnModify;
		JToggleButton tglbtnDelete;
		private JPanel pnlSouth;
		private JLabel lblXY;
		private Color borderColor=Color.black;
		private Color innerColor=Color.white;
		private JButton btnActiveBorderColor;
		private JButton btnInnerColor;
		private boolean borderIdetificator=false;
		private boolean innerIdentificator=false;
		private JList<String> activityLog;
		private DefaultListModel<String> logText= new DefaultListModel<String>();
		private JScrollPane scrollPane= new JScrollPane();
		
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						DrawingFrame frame = new DrawingFrame();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		DrawingView view=new DrawingView();
		DrawingController controler;
		private JToggleButton tglbtnUndo;
		private JToggleButton tglbtnRedo;
		private JToggleButton tglbtnHexagon;
		private JToggleButton tglbtnBringToFront;
		private JToggleButton tglbtnBringToBack;
		private JToggleButton tglbtnToFront;
		private JToggleButton tglbtnToBack;
		private JTextField txtCommand;
		private JPanel pnlLeft;
		private JToggleButton tglbtnSave;
		private JToggleButton tglbtnOpen;
		
		
		public DrawingView getView() {
			return view;
		}
		public void setControler(DrawingController controler) {
			this.controler=controler;
		}
		
		
		public boolean isInnerIdentificator() {
			return innerIdentificator;
		}


		public void setInnerIdentificator(boolean innerIdentificator) {
			this.innerIdentificator = innerIdentificator;
		}


		public boolean isBorderIdetificator() {
			return borderIdetificator;
		}


		public void setBorderIdetificator(boolean borderIdetificator) {
			this.borderIdetificator = borderIdetificator;
		}


		public boolean getTglbtnPoint() {  //vraca stanje dugmeta 
			return tglbtnPoint.isSelected();
		}

		public boolean getTglbtnLine() {
			return tglbtnLine.isSelected();
		}

		public boolean getTglbtnRectangle() {
			return tglbtnRectangle.isSelected();
		}

		public boolean getTglbtnCircle() {
			return tglbtnCircle.isSelected();
		}

		public boolean getTglbtnDonut() {
			return tglbtnDonut.isSelected();
		}
		public boolean getTglBtnHexagon() {
			return tglbtnHexagon.isSelected();
		}

		public boolean getTglbtnSelect() {
			return tglbtnSelect.isSelected();
		}

		public boolean getTglbtnModify() {
			return tglbtnModify.isSelected();
		}
		public void setTglbtnModifyEnabled(boolean enabled) {
		    tglbtnModify.setEnabled(enabled);
		}

		public boolean getTglbtnDelete() {
			return tglbtnDelete.isSelected();
		}
		public void setTglbtnDeleteEnabled(boolean enabled) {
		    tglbtnDelete.setEnabled(enabled);
		}
		public void setTglbtnDelete(boolean b) {
			tglbtnDelete.setSelected(b);
			tglbtnSelect.doClick();
		}
	
		public boolean getTglbtnUndo() {
			return tglbtnUndo.isSelected();
		}
		public void setTglbtnUndo(boolean b) {
			tglbtnUndo.setSelected(b);
			tglbtnSelect.doClick();
		}
		public void setTglbtnRedo(boolean b) {
			tglbtnRedo.setSelected(b);
			tglbtnSelect.doClick();
		}
		public void setTglbtnUndoEnabled(boolean enabled) {
			tglbtnUndo.setEnabled(enabled);
		}
		
		public boolean getTglbtnRedo() {
			return tglbtnRedo.isSelected();
		}
		public void setSelectedUndo(boolean b) {
			tglbtnRedo.setSelected(b);
		}
		public void setTglbtnRedoEnabled(boolean enabled) {
			tglbtnRedo.setEnabled(enabled);
		}
		public boolean getTglbtnBringToFront() {
			return tglbtnBringToFront.isSelected();
		}
		public void setTglbtnBringToFrontEnabled(boolean enabled) {
			tglbtnBringToFront.setEnabled(enabled);
		}
		public boolean getTglbtnBringToDown() {
			return tglbtnBringToBack.isSelected();
		}
		public void setTglbtnBringToBackEnabled(boolean enabled) {
			tglbtnBringToBack.setEnabled(enabled);
		}
		public boolean getTglbtnToFront() {
			return tglbtnToFront.isSelected();
		}
		public void setTglbtnToFrontEnabled(boolean enabled) {
			tglbtnToFront.setEnabled(enabled);
		}
		public boolean getTglbtnToBack() {
			return tglbtnToBack.isSelected();
		}
		public void setTglbtnToBackEnabled(boolean enabled) {
			tglbtnToBack.setEnabled(enabled);
		}
		
		public Color getBorderColor() {
			return borderColor;
		}

		public void setBorderColor(Color borderColor) {
			this.borderColor = borderColor;
		}


		public Color getInnerColor() {
			return innerColor;
		}

		public void setInnerColor(Color innerColor) {
			this.innerColor = innerColor;
		}
		public DefaultListModel<String> getlist(){
			return logText;
		}
		
		public void setLogText(DefaultListModel<String> logText) {
			this.logText = logText;
		}
		public boolean getTglBtnnSave() {
			return tglbtnSave.isSelected();
		}
		public boolean getTglBtnOpen() {
			return tglbtnOpen.isSelected();
		}
		public DrawingFrame() {
			

			setTitle("Ognjen Jovanovic");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 1300, 750);
		//	pnlCenter.setBackground(new Color(255, 255, 255));
			
			Dimension d = new Dimension(99,29);   
			
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			
			ButtonGroup group=new ButtonGroup();
			contentPane.setLayout(new BorderLayout(0, 0));
			
			JPanel pnlNorth = new JPanel();
			pnlNorth.setBackground(new Color(255, 218, 185));
			getContentPane().add(pnlNorth, BorderLayout.NORTH);
			pnlNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			tglbtnPoint = new JToggleButton("Point");
			pnlNorth.add(tglbtnPoint);
			tglbtnPoint.setPreferredSize(d);
			tglbtnPoint.setFont(new Font("Arial", Font.BOLD, 16));
			tglbtnPoint.setForeground(new Color(105, 105, 105));
			tglbtnPoint.setBackground(new Color(255, 235, 205));
			tglbtnPoint.setBorder(new RoundedBorder(5));
			group.add(tglbtnPoint);
			
			tglbtnLine = new JToggleButton("Line");
			pnlNorth.add(tglbtnLine);
			tglbtnLine.setPreferredSize(d);
			tglbtnLine.setFont(new Font("Arial", Font.BOLD, 16));
			tglbtnLine.setForeground(new Color(105, 105, 105));
			tglbtnLine.setBackground(new Color(255, 235, 205));
			tglbtnLine.setBorder(new RoundedBorder(5));		
			group.add(tglbtnLine);
			
			tglbtnRectangle = new JToggleButton("Rectangle");
				pnlNorth.add(tglbtnRectangle);
				tglbtnRectangle.setPreferredSize(d);
				tglbtnRectangle.setBackground(new Color(255, 235, 205));
				tglbtnRectangle.setForeground(new Color(105, 105, 105));
				tglbtnRectangle.setFont(new Font("Arial", Font.BOLD, 16));
				tglbtnRectangle.setBorder(new RoundedBorder(5));
				group.add(tglbtnRectangle);
			
			tglbtnCircle = new JToggleButton("Circle");
				pnlNorth.add(tglbtnCircle);
				tglbtnCircle.setPreferredSize(d);
				tglbtnCircle.setBackground(new Color(255, 235, 205));
				tglbtnCircle.setFont(new Font("Arial", Font.BOLD, 16));
				tglbtnCircle.setForeground(new Color(105, 105, 105));
				tglbtnCircle.setBorder(new RoundedBorder(5));
				group.add(tglbtnCircle);
			
			tglbtnDonut = new JToggleButton("Donut");
			pnlNorth.add(tglbtnDonut);
			tglbtnDonut.setPreferredSize(d);
			tglbtnDonut.setBackground(new Color(255, 235, 205));
			tglbtnDonut.setFont(new Font("Arial", Font.BOLD, 16));
			tglbtnDonut.setForeground(new Color(105, 105, 105));
			tglbtnDonut.setBorder(new RoundedBorder(5));
			group.add(tglbtnDonut);
			tglbtnRectangle.setBorder(new RoundedBorder(5));
			group.add(tglbtnRectangle);    
			
			
			tglbtnHexagon = new JToggleButton("Hexagon");
			tglbtnHexagon.setPreferredSize(d);
			tglbtnHexagon.setForeground(new Color(105, 105, 105));
			tglbtnHexagon.setFont(new Font("Arial", Font.BOLD, 16));
			tglbtnHexagon.setBackground(new Color(255, 235, 205));
			tglbtnHexagon.setBorder(new RoundedBorder(5));
			group.add(tglbtnHexagon);
			pnlNorth.add(tglbtnHexagon);
			
			
			tglbtnSelect = new JToggleButton("Select");
				pnlNorth.add(tglbtnSelect);
				group.add(tglbtnSelect);
				tglbtnSelect.setPreferredSize(d);
				tglbtnSelect.setBackground(new Color(255, 235, 205));
				tglbtnSelect.setFont(new Font("Arial", Font.BOLD, 16));
				tglbtnSelect.setForeground(new Color(105, 105, 105));
				tglbtnSelect.setBorder(new RoundedBorder(5));
			
			tglbtnModify = new JToggleButton("Modify");
			tglbtnModify.setPreferredSize(d);
			tglbtnModify.setBackground(new Color(255, 235, 205));
			tglbtnModify.setFont(new Font("Arial", Font.BOLD, 16));
			tglbtnModify.setForeground(new Color(105, 105, 105));
			tglbtnModify.setBorder(new RoundedBorder(5));
				pnlNorth.add(tglbtnModify);				
				group.add(tglbtnModify);		
				 tglbtnModify.setEnabled(false);
				

			
			tglbtnDelete = new JToggleButton("Delete");
			tglbtnDelete.setPreferredSize(d);
			tglbtnDelete.setBackground(new Color(255, 235, 205));
			tglbtnDelete.setFont(new Font("Arial", Font.BOLD, 16));
			tglbtnDelete.setForeground(new Color(105, 105, 105));
			tglbtnDelete.setBorder(new RoundedBorder(5));
			pnlNorth.add(tglbtnDelete);
			group.add(tglbtnDelete);
			tglbtnDelete.setEnabled(false);
		
			btnActiveBorderColor = new JButton("Border Color");
			btnActiveBorderColor.setBackground(new Color(255, 235, 205));
			btnActiveBorderColor.setBackground(Color.BLACK);
			btnActiveBorderColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					//btnActiveBorderColor.setBackground(Color.BLACK);
					borderColor=JColorChooser.showDialog(null, "Choose color:",Color.BLACK);
					btnActiveBorderColor.setBackground(borderColor);
					//borderIdetificator=true;
				}
			});
			btnActiveBorderColor.setForeground(new Color(105, 105, 105));
			btnActiveBorderColor.setFont(new Font("Arial", Font.BOLD, 14));
			pnlNorth.add(btnActiveBorderColor);
			
			btnInnerColor = new JButton("Inner Color");
			btnInnerColor.setForeground(new Color(105, 105, 105));
			btnInnerColor.setFont(new Font("Arial", Font.BOLD, 14));
			btnInnerColor.setBackground(new Color(255, 235, 205));
			btnInnerColor.setBackground(Color.WHITE);
			btnInnerColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					//btnInnerColor.setBackground(Color.WHITE);
					innerColor=JColorChooser.showDialog(null,"Choose color:",Color.WHITE);
					btnInnerColor.setBackground(innerColor);
					
				}
			});
			pnlNorth.add(btnInnerColor);
					
			pnlSouth = new JPanel();
			pnlSouth.setBorder(new LineBorder(new Color(105, 105, 105)));
			pnlSouth.setBackground(new Color(255, 218, 185));
			contentPane.add(pnlSouth, BorderLayout.SOUTH);
			pnlSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			tglbtnUndo = new JToggleButton("Undo");
			tglbtnUndo.setForeground(new Color(105, 105, 105));
			tglbtnUndo.setPreferredSize(d);
			tglbtnUndo.setBackground(new Color(255, 235, 205));
			tglbtnUndo.setFont(new Font("Arial", Font.BOLD, 16));
			tglbtnUndo.setBorder(new RoundedBorder(5));
			group.add(tglbtnUndo);
			pnlSouth.add(tglbtnUndo);
			tglbtnUndo.setEnabled(false);
			
			tglbtnRedo = new JToggleButton("Redo");
			tglbtnRedo.setBackground(new Color(255, 235, 205));
			tglbtnRedo.setPreferredSize(d);
			tglbtnRedo.setFont(new Font("Arial", Font.BOLD, 16));
			tglbtnRedo.setForeground(new Color(105, 105, 105));
			tglbtnRedo.setBorder(new RoundedBorder(5));
			group.add(tglbtnRedo); 
			pnlSouth.add(tglbtnRedo);
			tglbtnRedo.setEnabled(false);
			
			tglbtnToFront = new JToggleButton("To Front");
			tglbtnToFront.setPreferredSize(d);
			tglbtnToFront.setForeground(new Color(105, 105, 105));
			tglbtnToFront.setFont(new Font("Arial", Font.BOLD, 16));
			tglbtnToFront.setBackground(new Color(255, 235, 205));
			tglbtnToFront.setBorder(new RoundedBorder(5));
			pnlSouth.add(tglbtnToFront);
			group.add(tglbtnToFront);
			tglbtnToFront.setEnabled(false);
			
			tglbtnToBack = new JToggleButton("To Back");
			tglbtnToBack.setFont(new Font("Arial", Font.BOLD, 16));
			tglbtnToBack.setPreferredSize(d);
			tglbtnToBack.setBackground(new Color(255, 235, 205));
			tglbtnToBack.setForeground(new Color(105, 105, 105));
			tglbtnToBack.setBorder(new RoundedBorder(5));
			pnlSouth.add(tglbtnToBack);
			group.add(tglbtnToBack);
			tglbtnToBack.setEnabled(false);
			
			tglbtnBringToFront = new JToggleButton("Bring To Front");
			tglbtnBringToFront.setPreferredSize(new Dimension(123, 29));
			tglbtnBringToFront.setFont(new Font("Arial", Font.BOLD, 16));
			tglbtnBringToFront.setForeground(new Color(105, 105, 105));
			tglbtnBringToFront.setBackground(new Color(255, 235, 205));
			tglbtnBringToFront.setBorder(new RoundedBorder(5));
			pnlSouth.add(tglbtnBringToFront);
			group.add(tglbtnBringToFront);
			tglbtnBringToFront.setEnabled(false);
			
			tglbtnBringToBack = new JToggleButton("Bring To Back");
			tglbtnBringToBack.setPreferredSize(new Dimension(133, 29));
			tglbtnBringToBack.setForeground(new Color(105, 105, 105));
			tglbtnBringToBack.setFont(new Font("Arial", Font.BOLD, 16));
			tglbtnBringToBack.setBackground(new Color(255, 235, 205));
			tglbtnBringToBack.setBorder(new RoundedBorder(5));
			pnlSouth.add(tglbtnBringToBack);  
			group.add(tglbtnBringToBack);
			tglbtnBringToBack.setEnabled(false);
			
			
			tglbtnSave = new JToggleButton("Save");
			tglbtnSave.setBackground(new Color(255, 235, 205));
			tglbtnSave.setPreferredSize(new Dimension(133, 29));
			tglbtnSave.setForeground(new Color(105, 105, 105));
			tglbtnSave.setBorder(new RoundedBorder(5));
			tglbtnSave.setFont(new Font("Arial", Font.BOLD, 16));
			pnlSouth.add(tglbtnSave);
			group.add(tglbtnSave);
			
			tglbtnOpen = new JToggleButton("Open");
			tglbtnOpen.setBackground(new Color(255, 235, 205));
			tglbtnOpen.setFont(new Font("Arial", Font.BOLD, 16));
			tglbtnOpen.setBorder(new RoundedBorder(5));
			tglbtnOpen.setForeground(new Color(105, 105, 105));
			tglbtnOpen.setHorizontalAlignment(SwingConstants.TRAILING);
			pnlSouth.add(tglbtnOpen);
			group.add(tglbtnOpen);
			
	/*		lblXY = new JLabel("x: 0, y: 0");
			lblXY.setHorizontalAlignment(SwingConstants.CENTER); 
			lblXY.setBackground(Color.WHITE);
			pnlSouth.add(lblXY);
			pnlCenter.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseMoved(MouseEvent arg0) {
					lblXY.setText("x: " + arg0.getX() + "  " + "y: " + arg0.getY());
				}
			});*/
			view.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					controler.mouseClicked(e);
				}
			});
			
			//JPanel panel = new JPanel();
			getContentPane().add(view, BorderLayout.CENTER);
			
			pnlLeft = new JPanel();
			pnlLeft.setForeground(new Color(0, 0, 0));
			pnlLeft.setBackground(new Color(255, 255, 255));
			contentPane.add(pnlLeft, BorderLayout.WEST);
			pnlLeft.setBounds(0,0,250,250);
			activityLog = new JList<String>();
			activityLog.setVisibleRowCount(38);
			logText= new DefaultListModel<String>();
			activityLog.setEnabled(false);
			activityLog.setModel(logText);
			activityLog.setSize(120, 120);
			activityLog.setFont(new Font(("Arial"),Font.BOLD,12));
			scrollPane= new JScrollPane();
			scrollPane.setViewportView(activityLog);
			//scrollPane.setSize(20, 80);
			pnlLeft.add(scrollPane);
			pnlLeft.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			 Dimension dimension = new Dimension(270, 100);
			 pnlLeft.setPreferredSize(dimension);
			 pnlLeft.setBorder(new EmptyBorder(5, 5, 5, 5));
	
		}
	}



