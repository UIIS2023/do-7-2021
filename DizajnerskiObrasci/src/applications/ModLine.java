package applications;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.Dimension;

public class ModLine extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtStartX;
	private JTextField txtStartY;
	private JTextField txtEndX;
	private JTextField txtEndY;
	private boolean isOk;
	private Color c;

	public void setTxtStartXEdit(boolean b){
		txtStartX.setEditable(b);
	}
	public void setTxtStartYEdit(boolean b){
		txtStartY.setEditable(b);
	}
	public void setTxtEndXEdit(boolean b){
		txtEndX.setEditable(b);
	}
	public void setTxtEndYEdit(boolean b){
		txtEndY.setEditable(b);
	}
	
	public Color getColor() {
		return c;
	}

	public void setColor(Color color) {
		this.c = color;
	}

	public String getTxtStartX() {
		return txtStartX.getText();
	}

	public void setTxtStartX(String txtStartX) {
		this.txtStartX.setText(txtStartX);
	}

	public String getTxtStartY() {
		return txtStartY.getText();
	}

	public void setTxtStartY(String txtStartY) {
		this.txtStartY.setText(txtStartY);
	}

	public String getTxtEndX() {
		return txtEndX.getText();
	}

	public void setTxtEndX(String txtEndX) {
		this.txtEndX.setText(txtEndX);
	}

	public String getTxtEndY() {
		return txtEndY.getText();
	}

	public void setTxtEndY(String txtEndY) {
		this.txtEndY.setText(txtEndY);
	}

	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean ok) {
		this.isOk = ok;
	}

	public static void main(String[] args) {
		try {
			ModLine dialog = new ModLine();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ModLine() {
		setTitle("Line");
		
		setBounds(100, 100, 356, 477);
		getContentPane().setLayout(new BorderLayout());
		setModal(true);
		Dimension d = new Dimension(80,30);

		contentPanel.setBackground(new Color(255, 235, 205));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblStartPoint = new JLabel("Start Point:");
		lblStartPoint.setFont(new Font("Arial", Font.PLAIN, 20));
		
		JLabel lblStartX = new JLabel("X coordinate:");
		lblStartX.setFont(new Font("Arial", Font.PLAIN, 20));
		
		JLabel lblStartY = new JLabel("Y coordinate:");
		lblStartY.setFont(new Font("Arial", Font.PLAIN, 20));
		
		JLabel lblEndPoint = new JLabel("End Point:");
		lblEndPoint.setFont(new Font("Arial", Font.PLAIN, 20));
		
		JLabel lblEndX = new JLabel("X coordinate:");
		lblEndX.setFont(new Font("Arial", Font.PLAIN, 20));
		
		JLabel lblEndY = new JLabel("Y coordinate:");
		lblEndY.setFont(new Font("Arial", Font.PLAIN, 20));
		
		txtStartX = new JTextField();
		txtStartX.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtStartX.setColumns(10);
		txtStartX.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		      char c = e.getKeyChar();
		      if (!((c >= '0') && (c <= '9') ||
		         (c == KeyEvent.VK_BACK_SPACE) ||
		         (c == KeyEvent.VK_DELETE))) {
		        getToolkit().beep();
		        e.consume();
		      }
		    }
		  });
		
		txtStartY = new JTextField();
		txtStartY.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtStartY.setColumns(10);
		txtStartY.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		      char c = e.getKeyChar();
		      if (!((c >= '0') && (c <= '9') ||
		         (c == KeyEvent.VK_BACK_SPACE) ||
		         (c == KeyEvent.VK_DELETE))) {
		        getToolkit().beep();
		        e.consume();
		      }
		    }
		  });
		
		txtEndX = new JTextField();
		txtEndX.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtEndX.setColumns(10);
		txtEndX.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		      char c = e.getKeyChar();
		      if (!((c >= '0') && (c <= '9') ||
		         (c == KeyEvent.VK_BACK_SPACE) ||
		         (c == KeyEvent.VK_DELETE))) {
		        getToolkit().beep();
		        e.consume();
		      }
		    }
		  });
		
		txtEndY = new JTextField();
		txtEndY.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtEndY.setColumns(10);
		txtEndY.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		      char c = e.getKeyChar();
		      if (!((c >= '0') && (c <= '9') ||
		         (c == KeyEvent.VK_BACK_SPACE) ||
		         (c == KeyEvent.VK_DELETE))) {
		        getToolkit().beep();
		        e.consume();
		      }
		    }
		  });
		
		JButton btnColor = new JButton("Color");
		btnColor.setBackground(new Color(255, 222, 173));
		btnColor.setForeground(new Color(105, 105, 105));
		btnColor.setFont(new Font("Arial", Font.BOLD, 17));
		btnColor.setBorder(new RoundedBorder(3));
		btnColor.setPreferredSize(d);
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c = JColorChooser.showDialog(null, "Choose a color:", null);
			}
		});
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(34)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStartPoint)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblStartY, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblStartX, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblEndX, Alignment.LEADING)
								.addComponent(lblEndPoint, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
								.addComponent(lblEndY, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(38)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtEndY, 0, 0, Short.MAX_VALUE)
								.addComponent(txtEndX, 0, 0, Short.MAX_VALUE)
								.addComponent(txtStartY, 0, 0, Short.MAX_VALUE)
								.addComponent(txtStartX, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))))
					.addContainerGap(56, Short.MAX_VALUE))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap(127, Short.MAX_VALUE)
					.addComponent(btnColor, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addGap(127))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(29)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblStartPoint)
							.addGap(29)
							.addComponent(lblStartX))
						.addComponent(txtStartX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblStartY)
						.addComponent(txtStartY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblEndPoint, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblEndX))
						.addComponent(txtEndX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEndY)
						.addComponent(txtEndY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
					.addComponent(btnColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 222, 173));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton saveButton = new JButton("Save");
				saveButton.setBackground(new Color(255, 235, 205));
				saveButton.setForeground(new Color(105, 105, 105));
				saveButton.setFont(new Font("Arial", Font.BOLD, 17));
				saveButton.setBorder(new RoundedBorder(3));
				saveButton.setPreferredSize(d);
				saveButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (txtStartX.getText().isEmpty() ||
								txtStartY.getText().isEmpty() || 
								txtEndX.getText().isEmpty() ||
								txtEndY.getText().isEmpty()) {
							isOk = false;
							setVisible(true);
							JOptionPane.showMessageDialog(null, "All fields must be filled.", "Error!", JOptionPane.WARNING_MESSAGE);
						} else {
						isOk=true;
						dispose(); 
						}
					}
				});
				buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				saveButton.setActionCommand("OK");
				buttonPane.add(saveButton);
				getRootPane().setDefaultButton(saveButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBackground(new Color(255, 235, 205));
				cancelButton.setFont(new Font("Arial", Font.BOLD, 17));
				cancelButton.setForeground(new Color(105, 105, 105));
				cancelButton.setBorder(new RoundedBorder(3));
				cancelButton.setPreferredSize(d);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
