package applications;


import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Circle;
import geometry.Point;

import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.lang.invoke.CallSite;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Color;

public class Stack extends JFrame {

	private JPanel contentPane;
	private DefaultListModel<Circle> dlm=new DefaultListModel<>();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Stack frame = new Stack();
					frame.setTitle("IT7-2021 Ognjen Jovanovic");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Stack() {
		
		setTitle("IT7-2021 Ognjen Jovanovic");
		setForeground(SystemColor.activeCaption); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setBounds(100, 100, 550, 550);	
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		Dimension d = new Dimension(80,30);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JList list = new JList();
		list.setFont(new Font("Arial", Font.PLAIN, 18));
		list.setModel(dlm);
		
		contentPane.setLayout(new BorderLayout(0, 0));
		scrollPane.setViewportView(list);
		contentPane.add(scrollPane);
		
		JPanel pnlButtons = new JPanel();
		pnlButtons.setBackground(new Color(255, 222, 173));
		contentPane.add(pnlButtons, BorderLayout.SOUTH);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setForeground(new Color(105, 105, 105));
		btnAdd.setBackground(new Color(255, 235, 205));
		pnlButtons.add(btnAdd);
		btnAdd.setFont(new Font("Arial", Font.BOLD, 17));
		btnAdd.setBorder(new RoundedBorder(3));
		btnAdd.setPreferredSize(d);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Circle circle = new Circle();
				DlgCircle dlg = new DlgCircle();
				dlg.setVisible(true);
				if(dlg.isOk())
				{
					circle.setCenter(new Point(Integer.parseInt((dlg.getTxtX())),Integer.parseInt(dlg.getTxtY())));
					try {
						circle.setRadius(Integer.parseInt(dlg.getTxtRadius()));
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					dlm.add(0,circle);
				}
			}
		});
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(new Color(105, 105, 105));
		btnDelete.setBackground(new Color(255, 235, 205));
		pnlButtons.add(btnDelete);
		btnDelete.setFont(new Font("Arial", Font.BOLD, 17));
		btnDelete.setBorder(new RoundedBorder(3));
		btnDelete.setPreferredSize(d);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!dlm.isEmpty()) {
					
					Circle circle=(Circle) dlm.getElementAt(0);
					DlgCircle dr=new DlgCircle();
					
					dr.setTxtX(Integer.toString(circle.getCenter().getX()));
					dr.setTxtY(Integer.toString(circle.getCenter().getY()));
					dr.setRadius(Integer.toString(circle.getRadius()));
					
				    dr.setTxtXEdit(false);
					dr.setTxtYEdit(false);
				
					
					dr.setVisible(true);
					
					if(dr.isOk());
					dlm.removeElementAt(0);
				}
				else if(dlm.isEmpty()) {
					JOptionPane.showMessageDialog(new JFrame(), "List is empty.", "Error!", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
	}
}
