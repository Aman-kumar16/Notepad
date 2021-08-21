package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;

public class GUI extends JFrame implements ActionListener {
	
	JTextArea area;
	JScrollPane pane;
	String text= "";
	String fileName = null;
	String fileAddress = null;
	
	public GUI() {
		setBounds(120,150,1200,600); //x-offset , y-offset, length of JFrame, width of JFrame.
		setTitle("Notepad");
		
		JMenuBar menuBar = new JMenuBar();
		
		//file menu
		JMenu file = new JMenu("File");
		//menuitems for file menu;
		JMenuItem newdoc = new JMenuItem("New");
		// ctrl is an action event and N is the key event so adding them together in keystroke.
		// setAccelerator is used to do the work without open the new window.
		newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));// to set the shortcut keys.
		newdoc.addActionListener(this);
		
		JMenuItem open = new JMenuItem("Open");
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
		open.addActionListener(this);
		
		JMenuItem save = new JMenuItem("Save");
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
		save.addActionListener(this);
		
		
		JMenuItem saveAs = new JMenuItem("SaveAs");
		saveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
		saveAs.addActionListener(this);
		
		JMenuItem print = new JMenuItem("Print");
		print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
		print.addActionListener(this);
		
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0));//ActionEvent is set to 0 because we want to exit by only esc button.
		exit.addActionListener(this);
		
		
		file.add(newdoc);
		file.add(open);
		file.add(save);
		file.add(saveAs);
		file.add(print);
		file.add(exit);
		
		
		//edit menu;
		JMenu edit = new JMenu("Edit");
		
		JMenuItem copy = new JMenuItem("Copy");
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
		copy.addActionListener(this);
		
		
		JMenuItem paste = new JMenuItem("Paste");
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		paste.addActionListener(this);
		
		
		JMenuItem cut = new JMenuItem("Cut");
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		cut.addActionListener(this);
		
		
		JMenuItem selectall = new JMenuItem("Select All");
		selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		selectall.addActionListener(this);
		
		edit.add(copy);
		edit.add(paste);
		edit.add(cut);
		edit.add(selectall);
		
		
		//help menu
		JMenu help = new JMenu("Help");
		
		JMenuItem about = new JMenuItem("About the Notepad");
		about.addActionListener(this);
		
		
		help.add(about);
		
		
		//adding menu's to menubar;
		menuBar.add(file);
		menuBar.add(edit);
		menuBar.add(help);
		
		setJMenuBar(menuBar);// used to set the menuBar at the top. -- other-wise add(menuBar) will place it in center.
		
		//creating textArea;
		area = new JTextArea();
		area.setFont(new Font("SAN_SERIF", Font.PLAIN,20));
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		
		pane = new JScrollPane(area);
		pane.setBorder(BorderFactory.createEmptyBorder()); // it will remove the border around our text area in the pane.
		
		add(pane,BorderLayout.CENTER);  // adds the actual text area with scroll bar in the JFrame and at location center of the frame.
		                                // out of 5 location north,south,west,east and center.
		
		
	}// end GUI constructor.
	
	@Override
	public void actionPerformed(ActionEvent ap) {
		
		if(ap.getActionCommand().equals("New")) {
			
			area.setText("");  // it will remove all the text form the notepad.
			setTitle("New");
			fileName = null;
			fileAddress = null;
			
		}else if(ap.getActionCommand().equals("Open")) {
		
			JFileChooser chooser = new JFileChooser();
			chooser.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter restrict = new FileNameExtensionFilter(".txt Files","txt");
			chooser.addChoosableFileFilter(restrict);
			
			int action = chooser.showOpenDialog(this);
			if(action != JFileChooser.APPROVE_OPTION) { // approve option is set to Save if not pressed then return.
				return;
			}
			
			 File file = chooser.getSelectedFile();
			 fileName = file.getName();
			 fileAddress = file.getAbsolutePath();
			 setTitle(fileName);
			 
			 try {
				 BufferedReader reader = new BufferedReader(new FileReader(fileAddress));
				 area.read(reader, null);
				 
			 }catch(Exception e) {
				 System.out.println("File cannot be read");
			 }
			
		}else if(ap.getActionCommand().equals("SaveAs")) {
			saveAs();
		}else if(ap.getActionCommand().equals("Save")) {
			
			// if the file doesn't already exist then we have to do same work as saveAs so call that function itself.
			if(fileName==null) {
				saveAs();
			}else {
				// it means the file was already present we just want to overwrite the content of the file
				// so just write down the present content of the text area to the file.
				
				try {
					FileWriter fw = new FileWriter(fileAddress);
					fw.write(area.getText());
					setTitle(fileName);
					fw.close();
					
				}catch(Exception e) {
				   System.out.println("Error Occures while Saving!!!");
				}
				
				
			}
			
			
		}else if(ap.getActionCommand().equals("Print")) {
			
			try {
				area.print();
			}catch(Exception e) {
				System.out.println("Cannot print");
			}
			
		}else if(ap.getActionCommand().equals("Exit")) {
			System.exit(0);
			
		}else if(ap.getActionCommand().equals("Copy")) {
			text = area.getSelectedText();
			
		}else if(ap.getActionCommand().equals("Paste")) {
			area.insert(text, area.getCaretPosition() );
			
		}else if(ap.getActionCommand().equals("Cut")) {
			text = area.getSelectedText();
			area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
			
		}else if(ap.getActionCommand().equals("Select All")) {
			area.selectAll();
			
		}else if(ap.getActionCommand().equals("About the Notepad")) {
			new About().setVisible(true);
		}
		
	}// end Action Performed;
	
	
	public void saveAs() {
		
		JFileChooser saveas = new JFileChooser();
		saveas.setApproveButtonText("Save");
		
		int action = saveas.showOpenDialog(this);
		if(action != JFileChooser.APPROVE_OPTION) { // approve option is set to Save if not pressed then return.
			return;
		}
		
		File filename = new File(saveas.getSelectedFile() + ".txt");
		fileName = filename.getName();
		fileAddress= filename.getAbsolutePath();
		setTitle(fileName);
		
		BufferedWriter outFile = null;
		
		try {
			outFile = new BufferedWriter(new FileWriter(filename));
			area.write(outFile);
		}catch(Exception e) {
			System.out.println("Exception occured!! Please check");
		}
	}
	
	public static void main(String []args) {
		new GUI().setVisible(true);
	}

	
	
}
