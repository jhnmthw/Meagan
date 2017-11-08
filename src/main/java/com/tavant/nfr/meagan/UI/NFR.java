package com.tavant.nfr.meagan.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JFileChooser;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.util.Properties;

import javax.swing.Action;
import javax.swing.JTextPane;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.Color;
import javax.swing.JButton;


public class NFR {

	private JFrame frame;
	private final Action action = new SwingAction();
	JTextPane textPane = new JTextPane();
	JButton btnStart = new JButton("Start");
	private final Action action_1 = new SwingAction_1();
	static NFR window =  new NFR();; 
	
	//private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					window = new NFR();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
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
		
		JRadioButton rdbtnJmeter = new JRadioButton("Jmeter");
		rdbtnJmeter.setAction(action);
		rdbtnJmeter.setBounds(36, 47, 109, 23);
		frame.getContentPane().add(rdbtnJmeter);
		
		//JTextPane textPane = new JTextPane();
		textPane.setEnabled(false);
		textPane.setBackground(Color.LIGHT_GRAY);
		textPane.setBounds(136, 93, 357, 41);
		frame.getContentPane().add(textPane);
		btnStart.setAction(action_1);
		
		
		btnStart.setBounds(527, 103, 89, 23);
		frame.getContentPane().add(btnStart);
		btnStart.setEnabled(false);
		
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
				
			}
			else
			{
				textPane.enable(false);
				textPane.setBackground(Color.LIGHT_GRAY);
				btnStart.setEnabled(false);
			}
			
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			
			putValue(NAME, "Start");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		
		
		public void actionPerformed(ActionEvent e) {
			System.out.println(textPane.getText());
			String str = textPane.getText();
			try {
				Properties pr=new Properties();
				pr.setProperty("jmxPath", str);
				File file = new File("performance.config.properties");
				FileOutputStream fileOut = new FileOutputStream(file);
				pr.store(fileOut,System.getProperty("jmxPath"));
				fileOut.close();
				}
				catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
					}
			window.frame.dispose();
		}
	}
}
