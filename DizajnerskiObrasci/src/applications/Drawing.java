package applications;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
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
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.border.LineBorder;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Drawing extends JFrame {

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
	private JButton btnActiveBorderColor;
	private JButton btnActiveInnnerColor;
	private Color innerColor=Color.white;
	private Color borderColor=Color.black;
	private boolean innerIndetificator=false;
	private boolean borderIdentificator=false;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Drawing frame = new Drawing();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public boolean isInnerIndetificator() {
		return innerIndetificator;
	}



	public void setInnerIndetificator(boolean innerIndetificator) {
		this.innerIndetificator = innerIndetificator;
	}



	public boolean isBorderIdentificator() {
		return borderIdentificator;
	}



	public void setBorderIdentificator(boolean borderIdentificator) {
		this.borderIdentificator = borderIdentificator;
	}



	public Color getInnerColor() {
		return innerColor;
	}


	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}


	public Color getBorderColor() {
		return borderColor;
	}


	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
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

	public boolean getTglbtnSelect() {
		return tglbtnSelect.isSelected();
	}

	public boolean getTglbtnModify() {
		return tglbtnModify.isSelected();
	}

	public boolean getTglbtnDelete() {
		return tglbtnDelete.isSelected();
	}

	public Drawing() {
		
		setTitle("Ognjen Jovanovic");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 750);
		
		PnlDrawing pnlCenter= new PnlDrawing(this); 
		pnlCenter.setBackground(new Color(255, 255, 255));
		
		Dimension d = new Dimension(99,29); 
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		ButtonGroup group=new ButtonGroup();
		contentPane.setLayout(new BorderLayout(0, 0));
				
		
		pnlCenter.setSize(new Dimension(20, 40));
		pnlCenter.setPreferredSize(new Dimension(200, 400));
		contentPane.add(pnlCenter);
		
		JPanel pnlNorth = new JPanel();
		pnlNorth.setBackground(new Color(255, 218, 185));
		getContentPane().add(pnlNorth, BorderLayout.NORTH);
		GridBagLayout gbl_pnlNorth = new GridBagLayout();
		gbl_pnlNorth.columnWidths = new int[]{0, 0, 0, 110, 110, 110, 110, 110, 110, 110, 110, 0};
		gbl_pnlNorth.rowHeights = new int[]{30};
		gbl_pnlNorth.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gbl_pnlNorth.rowWeights = new double[]{0.0};
		pnlNorth.setLayout(gbl_pnlNorth);
		
		tglbtnPoint = new JToggleButton("Point");
		GridBagConstraints gbc_tglbtnPoint = new GridBagConstraints();
		gbc_tglbtnPoint.insets = new Insets(0, 0, 0, 5);
		//gbc_tglbtnPoint.insets = new Insets(5, 0, 5, 5);
		gbc_tglbtnPoint.gridx = 0;
		gbc_tglbtnPoint.gridy = 0;
		pnlNorth.add(tglbtnPoint, gbc_tglbtnPoint);
		tglbtnPoint.setPreferredSize(d);
		tglbtnPoint.setFont(new Font("Arial", Font.BOLD, 16));
		tglbtnPoint.setForeground(new Color(105, 105, 105));
		tglbtnPoint.setBackground(new Color(255, 235, 205));
		tglbtnPoint.setBorder(new RoundedBorder(5));
		group.add(tglbtnPoint);
		
		tglbtnLine = new JToggleButton("Line");
		GridBagConstraints gbc_tglbtnLine = new GridBagConstraints();
		gbc_tglbtnLine.insets = new Insets(0, 0, 0, 5);
		//gbc_tglbtnLine.insets = new Insets(5, 0, 5, 5);
		gbc_tglbtnLine.gridx = 3;
		gbc_tglbtnLine.gridy = 0;
		pnlNorth.add(tglbtnLine, gbc_tglbtnLine);
		tglbtnLine.setPreferredSize(d);
		tglbtnLine.setFont(new Font("Arial", Font.BOLD, 16));
		tglbtnLine.setForeground(new Color(105, 105, 105));
		tglbtnLine.setBackground(new Color(255, 235, 205));
		tglbtnLine.setBorder(new RoundedBorder(5));		
		group.add(tglbtnLine);
		
		tglbtnRectangle = new JToggleButton("Rectangle");
		GridBagConstraints gbc_tglbtnRectangle = new GridBagConstraints();
		gbc_tglbtnRectangle.insets = new Insets(0, 0, 0, 5);
		//	gbc_tglbtnRectangle.insets = new Insets(5, 0, 5, 5);
			gbc_tglbtnRectangle.gridx = 4;
			gbc_tglbtnRectangle.gridy = 0;
			pnlNorth.add(tglbtnRectangle, gbc_tglbtnRectangle);
			tglbtnRectangle.setPreferredSize(d);
			tglbtnRectangle.setBackground(new Color(255, 235, 205));
			tglbtnRectangle.setForeground(new Color(105, 105, 105));
			tglbtnRectangle.setFont(new Font("Arial", Font.BOLD, 16));
			tglbtnRectangle.setBorder(new RoundedBorder(5));
			group.add(tglbtnRectangle);
		
		tglbtnCircle = new JToggleButton("Circle");
		GridBagConstraints gbc_tglbtnCircle = new GridBagConstraints();
		gbc_tglbtnCircle.insets = new Insets(0, 0, 0, 5);
		//	gbc_tglbtnCircle.insets = new Insets(5, 0, 5, 5);
			gbc_tglbtnCircle.gridx = 5;
			gbc_tglbtnCircle.gridy = 0;
			pnlNorth.add(tglbtnCircle, gbc_tglbtnCircle);
			tglbtnCircle.setPreferredSize(d);
			tglbtnCircle.setBackground(new Color(255, 235, 205));
			tglbtnCircle.setFont(new Font("Arial", Font.BOLD, 16));
			tglbtnCircle.setForeground(new Color(105, 105, 105));
			tglbtnCircle.setBorder(new RoundedBorder(5));
			group.add(tglbtnCircle);
		
		tglbtnDonut = new JToggleButton("Donut");
		GridBagConstraints gbc_tglbtnDonut = new GridBagConstraints();
		gbc_tglbtnDonut.insets = new Insets(0, 0, 0, 5);
		//gbc_tglbtnDonut.insets = new Insets(5, 0, 5, 5);
		gbc_tglbtnDonut.gridx = 6;
		gbc_tglbtnDonut.gridy = 0;
		pnlNorth.add(tglbtnDonut, gbc_tglbtnDonut);
		tglbtnDonut.setPreferredSize(d);
		tglbtnDonut.setBackground(new Color(255, 235, 205));
		tglbtnDonut.setFont(new Font("Arial", Font.BOLD, 16));
		tglbtnDonut.setForeground(new Color(105, 105, 105));
		tglbtnDonut.setBorder(new RoundedBorder(5));
		group.add(tglbtnDonut);
		
		tglbtnSelect = new JToggleButton("Select");
		GridBagConstraints gbc_tglbtnSelect = new GridBagConstraints();
		gbc_tglbtnSelect.insets = new Insets(0, 0, 0, 5);
		//	gbc_tglbtnSelect.insets = new Insets(5, 0, 5, 5);
			gbc_tglbtnSelect.gridx = 7;
			gbc_tglbtnSelect.gridy = 0;
			pnlNorth.add(tglbtnSelect, gbc_tglbtnSelect);
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
		GridBagConstraints gbc_tglbtnModify = new GridBagConstraints();
		gbc_tglbtnModify.insets = new Insets(0, 0, 0, 5);
		//	gbc_tglbtnModify.insets = new Insets(5, 0, 5, 5);
			gbc_tglbtnModify.gridx = 8;
			gbc_tglbtnModify.gridy = 0;
			pnlNorth.add(tglbtnModify, gbc_tglbtnModify);
			tglbtnModify.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) { // proverava s e dali je dugme kliknuto ili ne
					if(pnlCenter.getTempShape()!=null)  //da li je oblik selektovan
					{
						Shape tempShape = pnlCenter.getTempShape();
						ArrayList<Shape> lst = pnlCenter.getShape();
						int index = lst.indexOf(tempShape);
						if(pnlCenter.getTempShape() instanceof Point)
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
								((Point)tempShape).setX(Integer.parseInt(mp.getTxtX()));
								((Point)tempShape).setY(Integer.parseInt(mp.getTxtY()));
								((Point)tempShape).setColor(mp.getColor());
								
								pnlCenter.repaint();
							}
							else {
								tempShape.setSelected(false);
								pnlCenter.setTempShape(tempShape);
								pnlCenter.repaint();
							}
							
						}
						else if(pnlCenter.getTempShape() instanceof Line)
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

							ml.setVisible(true);

							if(ml.isOk())
							{			
								((Line)tempShape).setStartPoint(new Point((Integer.parseInt(ml.getTxtStartX())),(Integer.parseInt(ml.getTxtStartY()))));
								((Line)tempShape).setEndPoint(new Point((Integer.parseInt(ml.getTxtEndX())),(Integer.parseInt(ml.getTxtEndY()))));
								((Line)tempShape).setColor(ml.getColor());
								
								lst.set(index,tempShape);
								pnlCenter.setShape(lst);
								tempShape.setSelected(true);
								pnlCenter.setTempShape(tempShape);
								pnlCenter.repaint();
							}
							else {
								tempShape.setSelected(false);
								pnlCenter.setTempShape(tempShape);
								pnlCenter.repaint();
							}
						}
						else if(pnlCenter.getTempShape() instanceof Rectangle)
						{
							DlgRectangle dr = new DlgRectangle();
							
							dr.setTxtXEdit(false);
							dr.setTxtYEdit(false);
							
							dr.setTxtX(Integer.toString(((Rectangle)tempShape).getUpperLeftPoint().getX()));
							dr.setTxtY(Integer.toString(((Rectangle)tempShape).getUpperLeftPoint().getY()));
							dr.setTxtWidth(Integer.toString(((Rectangle)tempShape).getWidth()));
							dr.setTxtHeight(Integer.toString(((Rectangle)tempShape).getHeight()));
							dr.setEdgeColor(((Rectangle)tempShape).getEdgeColor());
							dr.setFillColor(((Rectangle)tempShape).getFillColor());
										
							dr.setVisible(true);
							
							if(dr.isOk())
							{
								((Rectangle)tempShape).setUpperLeftPoint(new Point(Integer.parseInt(dr.getTxtX().getText()),Integer.parseInt(dr.getTxtY().getText())));						
								((Rectangle)tempShape).setWidth(Integer.parseInt(dr.getTxtWidth().getText()));
								((Rectangle)tempShape).setHeight(Integer.parseInt(dr.getTxtHeight().getText()));
								((Rectangle)tempShape).setEdgeColor(dr.getEdgeColor());
								((Rectangle)tempShape).setFillColor(dr.getFillColor());
								
								lst.set(index,tempShape);
								pnlCenter.setShape(lst);
								tempShape.setSelected(true);
								pnlCenter.setTempShape(tempShape);
								pnlCenter.repaint();
							}			
							else {
								tempShape.setSelected(false);
								pnlCenter.setTempShape(tempShape);
								pnlCenter.repaint();
							}
						}
						else if(pnlCenter.getTempShape() instanceof Donut)
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
								((Donut)tempShape).setCenter(new Point(Integer.parseInt(dd.getTxtX()),Integer.parseInt(dd.getTxtY())));
								((Donut)tempShape).setInnerRadius(Integer.parseInt(dd.getTxtInner()));
								
								try {
									((Donut)tempShape).setRadius(Integer.parseInt(dd.getTxtOuter()));
								} catch (NumberFormatException e) {
									e.printStackTrace();
								} catch (Exception e) {
									JOptionPane.showMessageDialog(null, "Invalid input.", "Error!", JOptionPane.WARNING_MESSAGE);
								}
								
								((Donut) tempShape).setEdgeColor(dd.getEdgeColor());
								((Donut) tempShape).setEdgeColorDonut(dd.getEdgeColor());
								((Donut) tempShape).setFillColor(dd.getFillColor());
								((Donut) tempShape).setFillColorDonut(dd.getFillColor());
								
								lst.set(index,tempShape);
								pnlCenter.setShape(lst);
								tempShape.setSelected(true);
								pnlCenter.setTempShape(tempShape);
								pnlCenter.repaint();
							}	
							else {
								tempShape.setSelected(false);
								pnlCenter.setTempShape(tempShape);
								pnlCenter.repaint();						
								}
						}
						else if(pnlCenter.getTempShape() instanceof Circle)
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
								try {
									((Circle)tempShape).setRadius(Integer.parseInt(dc.getTxtRadius()));
								} catch (Exception e) {
									e.printStackTrace();
								}

								((Circle)tempShape).setCenter(new Point(Integer.parseInt(dc.getTxtX()),Integer.parseInt(dc.getTxtY())));
								((Circle)tempShape).setEdgeColor(dc.getEdgeColor());
								((Circle)tempShape).setFillColor(dc.getFillColor());
								
								lst.set(index,tempShape);
								pnlCenter.setShape(lst);
								tempShape.setSelected(true);
								pnlCenter.setTempShape(tempShape);
								pnlCenter.repaint();
							}
							else {
								tempShape.setSelected(false);
								pnlCenter.setTempShape(tempShape);
								pnlCenter.repaint();						
							}
						}
					}
					else
					{
						JOptionPane.showMessageDialog(new JFrame(), "You must select a shape.", "Error!", JOptionPane.WARNING_MESSAGE);
					}
				}
				});
			group.add(tglbtnModify);
		
		tglbtnDelete = new JToggleButton("Delete");
		tglbtnDelete.setPreferredSize(d);
		tglbtnDelete.setBackground(new Color(255, 235, 205));
		tglbtnDelete.setFont(new Font("Arial", Font.BOLD, 16));
		tglbtnDelete.setForeground(new Color(105, 105, 105));
		tglbtnDelete.setBorder(new RoundedBorder(5));
		GridBagConstraints gbc_tglbtnDelete = new GridBagConstraints();
		gbc_tglbtnDelete.insets = new Insets(0, 0, 0, 5);
		//gbc_tglbtnDelete.insets = new Insets(5, 0, 5, 5);
		gbc_tglbtnDelete.gridx = 9;
		gbc_tglbtnDelete.gridy = 0;
		pnlNorth.add(tglbtnDelete, gbc_tglbtnDelete);
		tglbtnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(pnlCenter.getTempShape()!=null)
				{
					Shape tempShape = pnlCenter.getTempShape();
					ArrayList<Shape> lst = pnlCenter.getShape();
					int index = lst.indexOf(tempShape);
					if(JOptionPane.showConfirmDialog(new JFrame(), "Are you sure you want to delete this shape?", "Confirmation",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
					{
						lst.remove(index);
						pnlCenter.setShape(lst);  //prosledi da je nova lista bez tog izbrisanog
					}
					else {
						tempShape.setSelected(false);
						pnlCenter.setTempShape(tempShape);
						pnlCenter.repaint();
					}
					pnlCenter.setTempShape(null);  //da nista ne bude selektovano
					pnlCenter.repaint();	
				}
				else
				{
					JOptionPane.showMessageDialog(new JFrame(), "You must select a shape.", "Error!", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		group.add(tglbtnDelete);
		
		btnActiveBorderColor = new JButton("Border Color");
		btnActiveBorderColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borderColor=JColorChooser.showDialog(null, "Choose color:", null);
				btnActiveBorderColor.setBackground(borderColor);
				borderIdentificator=true;
			}
		});
		btnActiveBorderColor.setForeground(new Color(105, 105, 105));
		btnActiveBorderColor.setBackground(new Color(255, 235, 205));
		btnActiveBorderColor.setFont(new Font("Arial", Font.BOLD, 14));
		btnActiveBorderColor.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_btnActiveBorderColor = new GridBagConstraints();
		gbc_btnActiveBorderColor.insets = new Insets(0, 0, 0, 5);
		gbc_btnActiveBorderColor.gridx = 10;
		gbc_btnActiveBorderColor.gridy = 0;
		pnlNorth.add(btnActiveBorderColor, gbc_btnActiveBorderColor);
		
		btnActiveInnnerColor = new JButton("Inner Color");
		btnActiveInnnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				innerColor=JColorChooser.showDialog(null, "Choose color:", null);
				btnActiveInnnerColor.setBackground(innerColor);
				innerIndetificator=true;
			}
		});
		btnActiveInnnerColor.setBackground(new Color(255, 235, 205));
		btnActiveInnnerColor.setFont(new Font("Arial", Font.BOLD, 14));
		btnActiveInnnerColor.setForeground(new Color(105, 105, 105));
		GridBagConstraints gbc_btnActiveInnnerColor = new GridBagConstraints();
		gbc_btnActiveInnnerColor.gridx = 11;
		gbc_btnActiveInnnerColor.gridy = 0;
		pnlNorth.add(btnActiveInnnerColor, gbc_btnActiveInnnerColor);
				
		pnlSouth = new JPanel();
		pnlSouth.setBorder(new LineBorder(new Color(105, 105, 105)));
		pnlSouth.setBackground(new Color(255, 218, 185));
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		pnlSouth.setLayout(new GridLayout(1, 0, 0, 0));
		
		
		lblXY = new JLabel("x: 0, y: 0");
		lblXY.setHorizontalAlignment(SwingConstants.CENTER);
		lblXY.setBackground(Color.WHITE);
		pnlSouth.add(lblXY);
		pnlCenter.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				lblXY.setText("x: " + arg0.getX() + "  " + "y: " + arg0.getY());
			}
		});

	}
	
}
