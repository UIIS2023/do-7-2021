package applications;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.Dimension;

public class DlgRectangle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtHeight;
	private JTextField txtWidth;
	private boolean isOk;
	private JLabel lblX;
	private JLabel lblY;
	private JLabel lblHeight;
	private JLabel lblWidth;
	private JButton saveButton;
	private JButton cancelButton;
	private Color edgeColor;
	private Color fillColor;

	public Color getEdgeColor() {
		return edgeColor;
	}

	public void setEdgeColor(Color edgeColor) {
		this.edgeColor = edgeColor;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
	
	public void setTxtXEdit(boolean b){
		txtX.setEditable(b);
	}
	
	public void setTxtYEdit(boolean b){
		txtY.setEditable(b);
	}
	
	public void setTxtHeightEdit(boolean b){
		txtHeight.setEditable(b);
	}
	
	public void setTxtWidthEdit(boolean b){
		txtWidth.setEditable(b);
	}

	public JTextField getTxtX() {
		return txtX;
	}

	public void setTxtX(String s) {
		this.txtX.setText(s);
	}
	
	public JTextField getTxtY() {
		return txtY;
	}

	public void setTxtY(String s) {
		this.txtY.setText(s);;
	}

	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean ok) {
		this.isOk = ok;
	}

	public JTextField getTxtHeight() {
		return txtHeight;
	}

	public void setTxtHeight(String number) {
		this.txtHeight.setText(number);
	}

	public JTextField getTxtWidth() {
		return txtWidth;
	}

	public void setTxtWidth(String number) {
		this.txtWidth.setText(number);
	}

	public static void main(String[] args) {
		try {
			DlgRectangle dialog = new DlgRectangle();
			dialog.setTitle("Rectangle");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public DlgRectangle() {
		
		setTitle("Rectangle");
		setBounds(100, 100, 352, 428);
		getContentPane().setLayout(new BorderLayout());
		this.setModal(true);
		contentPanel.setBackground(new Color(255, 235, 205));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		Dimension d = new Dimension(80,30);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		{
			lblX = new JLabel("X coordinate:");
			lblX.setFont(new Font("Arial", Font.PLAIN, 20));
			lblX.setHorizontalTextPosition(SwingConstants.LEFT);
			lblX.setHorizontalAlignment(SwingConstants.LEFT);
		}
		
		{
			txtX = new JTextField();
			txtX.setFont(new Font("Arial", Font.PLAIN, 20));
			txtX.setColumns(10);
			txtX.addKeyListener(new KeyAdapter() {  
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
		}
		
		{
			lblY = new JLabel("Y coordinate:");
			lblY.setFont(new Font("Arial", Font.PLAIN, 20));
		}
		
		{
			txtY = new JTextField();
			txtY.setFont(new Font("Arial", Font.PLAIN, 20));
			txtY.setColumns(10);
			txtY.addKeyListener(new KeyAdapter() {
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
		}
		
		{
			lblHeight = new JLabel("Height:");
			lblHeight.setHorizontalAlignment(SwingConstants.RIGHT);
			lblHeight.setFont(new Font("Arial", Font.PLAIN, 20));
		}
		
		{
			txtHeight = new JTextField();
			txtHeight.setFont(new Font("Arial", Font.PLAIN, 20));
			txtHeight.setColumns(10);
			txtHeight.addKeyListener(new KeyAdapter() {
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
		}
		
		{
			lblWidth = new JLabel("Width:");
			lblWidth.setHorizontalAlignment(SwingConstants.RIGHT);
			lblWidth.setFont(new Font("Arial", Font.PLAIN, 20));
			
		}
		
		{
			txtWidth = new JTextField();
			txtWidth.setFont(new Font("Arial", Font.PLAIN, 20));
			txtWidth.setColumns(10);
			txtWidth.addKeyListener(new KeyAdapter() {
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
		}
		
		JButton btnEdgeColor = new JButton("Edge Color");
		btnEdgeColor.setBackground(new Color(255, 222, 173));
		btnEdgeColor.setForeground(new Color(105, 105, 105));
		btnEdgeColor.setFont(new Font("Arial", Font.BOLD, 17));
		btnEdgeColor.setBorder(new RoundedBorder(3));
		btnEdgeColor.setPreferredSize(d);
		btnEdgeColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				edgeColor = JColorChooser.showDialog(null, "Choose a color:", null);
			}
		});
		
		JButton btnFillColor = new JButton("Fill Color");
		btnFillColor.setBackground(new Color(255, 222, 173));
		btnFillColor.setForeground(new Color(105, 105, 105));
		btnFillColor.setFont(new Font("Arial", Font.BOLD, 17));
		btnFillColor.setBorder(new RoundedBorder(3));
		btnFillColor.setPreferredSize(d);
		btnFillColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillColor = JColorChooser.showDialog(null, "Choose a color:", null);
			}
		});
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(32)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblX)
						.addComponent(lblY, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblHeight, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblWidth, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnEdgeColor, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
							.addComponent(txtHeight, 0, 0, Short.MAX_VALUE)
							.addComponent(txtY, 0, 0, Short.MAX_VALUE)
							.addComponent(txtX, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
							.addComponent(txtWidth, 0, 0, Short.MAX_VALUE))
						.addComponent(btnFillColor, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(34, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(43)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblX)
						.addComponent(txtX, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtY, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblY))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHeight))
					.addGap(21)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWidth))
					.addGap(33)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEdgeColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnFillColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 222, 173));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				saveButton = new JButton("Save");
				saveButton.setBackground(new Color(255, 235, 205));
				saveButton.setForeground(new Color(105, 105, 105));
				saveButton.setFont(new Font("Arial", Font.BOLD, 17));
				saveButton.setBorder(new RoundedBorder(3));
				saveButton.setPreferredSize(d);
				saveButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (txtX.getText().isEmpty() ||
								txtY.getText().isEmpty() ||
								txtWidth.getText().isEmpty() ||
								txtHeight.getText().isEmpty()) {
							isOk = false;
							setVisible(true);
							JOptionPane.showMessageDialog(null, "All fields must be filled.", "Error!", JOptionPane.WARNING_MESSAGE);
						} else {
						isOk=true;
						dispose(); 
						}
					}
				});
				saveButton.setActionCommand("OK");
				getRootPane().setDefaultButton(saveButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setBackground(new Color(255, 235, 205));
				cancelButton.setFont(new Font("Arial", Font.BOLD, 17));
				cancelButton.setForeground(new Color(105, 105, 105));
				cancelButton.setBorder(new RoundedBorder(3));
				cancelButton.setPreferredSize(d);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						isOk=false;
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
			}
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			buttonPane.add(saveButton);
			buttonPane.add(cancelButton);
			
			
		}
	}
}
