package com.tavant.nfr.meagan.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JFileChooser;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;

import java.awt.CheckboxGroup;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;


public class NFR {

	private JFrame frame;
	private JFrame frame2;
	private final Action action = new SwingAction();
	JTextPane textPane = new JTextPane();
	JButton btnStart = new JButton("Start");
	private final Action action_1 = new SwingAction_1();
	static NFR window = new NFR();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_4;
	private JTextField textField_3;
	private final Action action_2 = new SwingAction_2();
	private final Action action_3 = new SwingAction_3();
	JRadioButton rdbtnIteration;
	JRadioButton rdbtnDuration;
	private JLabel lblNewLabel_1;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	JFileChooser fileChooser;
	
	//private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public NFR() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 815, 624);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		frame2 = new JFrame();
		frame2.setBounds(221,185, 600, 450);
		//frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.getContentPane().setLayout(null);
		
		JRadioButton rdbtnJmeter = new JRadioButton("Jmeter");
		rdbtnJmeter.setAction(action);
		rdbtnJmeter.setBounds(36, 47, 109, 23);
		frame.getContentPane().add(rdbtnJmeter);
		textPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
		  window.frame2.setVisible(true);
			fileChooser.setVisible(true);	
			//fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("*.jmx", "jmx"));
			FileNameExtensionFilter fy=new FileNameExtensionFilter("*.jmx", "jmx");
			fileChooser.setFileFilter(fy);
			}
		});
		
		//JTextPane textPane = new JTextPane();
		textPane.setEnabled(false);
		textPane.setBackground(Color.LIGHT_GRAY);
		textPane.setBounds(136, 93, 485, 23);
		frame.getContentPane().add(textPane);
		btnStart.setAction(action_1);
		
		
		btnStart.setBounds(658, 93, 89, 23);
		frame.getContentPane().add(btnStart);
		btnStart.setEnabled(false);
		
		JLabel lblJmxPath = new JLabel("JMX Path");
		lblJmxPath.setBounds(59, 93, 67, 41);
		frame.getContentPane().add(lblJmxPath);
		
		JLabel lblNewLabel = new JLabel("No of Threads");
		lblNewLabel.setBounds(56, 145, 89, 19);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				if(evt.getKeyChar()>='0' && evt.getKeyChar()<='9')
				{
					super.keyTyped(evt);
				}
				else
				{
					evt.consume();
				}
			}
		});
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(163, 142, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setEnabled(false);
		
		JLabel lblRampUp = new JLabel("Ramp Up");
		lblRampUp.setBounds(318, 145, 79, 19);
		frame.getContentPane().add(lblRampUp);
		
		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar()>='0' && e.getKeyChar()<='9')
				{
					super.keyTyped(e);
				}
				else
				{
					e.consume();
				}
			}
		});
		textField_1.setBackground(Color.LIGHT_GRAY);
		textField_1.setBounds(407, 142, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		textField_1.setEnabled(false);
		
		rdbtnIteration = new JRadioButton("Iteration");
		buttonGroup.add(rdbtnIteration);
		rdbtnIteration.setAction(action_2);
		rdbtnIteration.setBounds(552, 141, 89, 23);
		frame.getContentPane().add(rdbtnIteration);
		rdbtnIteration.setEnabled(false);
		
		rdbtnDuration = new JRadioButton("Duration");
		buttonGroup.add(rdbtnDuration);
		rdbtnDuration.setAction(action_3);
		rdbtnDuration.setBounds(551, 167, 90, 23);
		frame.getContentPane().add(rdbtnDuration);
		rdbtnDuration.setEnabled(false);
		
		textField_4 = new JTextField();
		textField_4.setText("0");
		textField_4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent er) {
				if(er.getKeyChar()>='0' && er.getKeyChar()<='9')
				{
					super.keyTyped(er);
				}
				else
				{
					er.consume();
				}
			}
		});
		textField_4.setBackground(Color.LIGHT_GRAY);
		textField_4.setBounds(661, 142, 86, 20);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		textField_4.setEnabled(false);
		
		textField_3 = new JTextField();
		textField_3.setText("0");
		textField_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent et) {
				if(et.getKeyChar()>='0' && et.getKeyChar()<='9')
				{
					super.keyTyped(et);
				}
				else
				{
					et.consume();
				}
			}
		});
		textField_3.setBackground(Color.LIGHT_GRAY);
		textField_3.setBounds(661, 168, 86, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		textField_3.setEnabled(false);
		
		lblNewLabel_1 = new JLabel("Sahil");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setEnabled(false);
		lblNewLabel_1.setBounds(226, 11, 375, 23);
		frame.getContentPane().add(lblNewLabel_1);
		
		fileChooser = new JFileChooser();
		fileChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String Jname=fileChooser.getSelectedFile().getPath();
				textPane.setText(Jname);
				frame2.dispose();
				textPane.setEditable(false);
			}
		});
		fileChooser.setBounds(0, 0, 582, 397);
		fileChooser.setVisible(false);
		frame2.getContentPane().add(fileChooser);
		lblNewLabel_1.setVisible(false);
		
		
		
	}
	
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Jmeter");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			JRadioButton rdb= (JRadioButton) e.getSource();
			if(rdb.isSelected()) {
				textPane.enable(true);
				textPane.setBackground(Color.WHITE);
				btnStart.setEnabled(true);
				textField.setEnabled(true);
				textField.setBackground(Color.WHITE);
				textField_1.setEnabled(true);
				textField_1.setBackground(Color.WHITE);
				//textField_2.setEnabled(true);
				//textField_2.setBackground(Color.WHITE);
				textField_4.setEnabled(true);
				textField_4.setBackground(Color.WHITE);
				rdbtnDuration.setEnabled(true);
				rdbtnIteration.setEnabled(true);
				rdbtnIteration.setSelected(true);
				
				
			}
			else
			{
				textPane.enable(false);
				textPane.setBackground(Color.LIGHT_GRAY);
				btnStart.setEnabled(false);
				textField.setEnabled(false);
				textField.setBackground(Color.LIGHT_GRAY);
				textField_1.setEnabled(false);
				textField_1.setBackground(Color.LIGHT_GRAY);
				textField_4.setEnabled(false);
				textField_4.setBackground(Color.LIGHT_GRAY);
				textField_3.setEnabled(false);
				textField_3.setBackground(Color.LIGHT_GRAY);
				rdbtnDuration.setEnabled(false);
				rdbtnIteration.setEnabled(false);
			}
			
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			
			putValue(NAME, "Start");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			
			
			String str = textPane.getText();
			String str1=textField.getText();
			String str2=textField_1.getText();
			//System.out.println(str);
		
			
			if(str.isEmpty() || str1.isEmpty() || str2.isEmpty())
			{
				
				lblNewLabel_1.setVisible(true);
				lblNewLabel_1.setText("Required Feild Empty");
				lblNewLabel_1.setForeground(Color.RED);
				lblNewLabel_1.setEnabled(true);
				
			}
			else {
				
				lblNewLabel_1.setVisible(false);			
			try {
			Properties pr=new Properties();
			pr.setProperty("JmxPath", str);
			pr.setProperty("Thread", str1);
			pr.setProperty("Rampup", str2);
			int str3=0;
			if(rdbtnIteration.isSelected())
			{
				str3=Integer.parseInt(textField_4.getText());
				pr.setProperty("Iteration",String.valueOf(str3));
			}
			else if(rdbtnDuration.isSelected()) {
				str3=Integer.parseInt(textField_3.getText());
				pr.setProperty("Duration",String.valueOf(str3));
			}
			else
			{

				pr.setProperty("Iteration",String.valueOf(str3));
								
			}
			
			System.out.println(str +" "+ str1 +" "+ str2 +" "+ str3);
			
			File file = new File("performance.config.properties");
			FileOutputStream fileOut = new FileOutputStream(file);
			//FileInputStream filein=new FileInputStream(file);
			//pr.load(filein);
			//filein.close();
	//	     pr.storeToXML(fileOut, "Favorite Things");
		//      pr.load(fileOut);
			pr.store(fileOut, " ");
		      //pr.load(fileOut);
			fileOut.close();
			frame.dispose();
			}
			catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			//frame.dispose();
			}
		}
	}
	private class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {
			putValue(NAME, "Iteration");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
              if(rdbtnIteration.isSelected()) {
				
				//rdbtnDuration.setEnabled(false);
				//textField_3.setEnabled(false);
				//textField_3.setBackground(Color.LIGHT_GRAY);
				textField_4.setEnabled(true);
				textField_4.setBackground(Color.WHITE);
				textField_3.setEnabled(false);
				textField_3.setBackground(Color.LIGHT_GRAY);
			}
             /* else {
            	//rdbtnDuration.setEnabled(true);
  				//textField_3.setEnabled(true);
  				//textField_3.setBackground(Color.WHITE);
  				textField_4.setEnabled(false);
				textField_4.setBackground(Color.LIGHT_GRAY);
            	 
              }*/
		}
	}
	private class SwingAction_3 extends AbstractAction {
		public SwingAction_3() {
			putValue(NAME, "Duration");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			if(rdbtnDuration.isSelected()) {
				
				//rdbtnIteration.setEnabled(false);
				//textField_2.setEnabled(false);
				//textField_2.setBackground(Color.LIGHT_GRAY);
				textField_3.setEnabled(true);
				textField_3.setBackground(Color.WHITE);
				textField_4.setEnabled(false);
				textField_4.setBackground(Color.LIGHT_GRAY);
			}
			/*else {
				//rdbtnIteration.setEnabled(true);
				//textField_2.setEnabled(true);
				//textField_2.setBackground(Color.WHITE);
				textField_3.setEnabled(false);
				textField_3.setBackground(Color.LIGHT_GRAY);
          	  
            }*/
		}
	}
}
