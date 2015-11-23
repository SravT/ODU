import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import java.util.*;

public class VirtualFileSystem implements ActionListener{    

ArrayList<file> files = new ArrayList<file>();
ArrayList<file> newFiles = new ArrayList<file>();
Process running;
JPanel titlePanel, scorePanel, buttonPanel, allFiles, newFilePanel;
JLabel consoleLabel, blueLabel, extension, content, fileName;
JButton addButton, removeButton, printButton, pStatus;
JTextArea console, contentText, ext, nameOfFile;
DefaultMutableTreeNode A, B, C;
DefaultMutableTreeNode root;
DefaultMutableTreeNode selectedNode;
DefaultTreeModel model;
private JTree tree;


public JPanel createContentPane (){

    // We create a bottom JPanel to place everything on.
    JPanel totalGUI = new JPanel();
    totalGUI.setLayout(null);
    totalGUI.setSize(1000,1000);

    
    //create the root node
    root = new DefaultMutableTreeNode("VFS");
    
    //create the child nodes
    file one = new file("test", "java", "System.out.println(\"TEST\")");
    files.add(new file("test1", "doc", "There is text here"));
    files.add(new file("Essay","doc", "Essay about unix"));
    files.add(new file("Dispactcher", "java", "java file for dispatcher"));
   
    A = new DefaultMutableTreeNode("Drive A");
    DefaultMutableTreeNode OS = new DefaultMutableTreeNode("OS Project");
    A.add(OS);
    OS.add(new DefaultMutableTreeNode(one));
    OS.add(new DefaultMutableTreeNode(files.get(0)));
    A.add(new DefaultMutableTreeNode(files.get(1)));
    OS.add(new DefaultMutableTreeNode(files.get(2)));
    
    B = new DefaultMutableTreeNode("Drive B");
    DefaultMutableTreeNode Projects = new DefaultMutableTreeNode("Projects");
    B.add(Projects);
    Projects.add(new DefaultMutableTreeNode(new file("congruency", "docx", "This is a congruency theorem paper")));
    Projects.add(new DefaultMutableTreeNode(new file("spreadsheet", "xls", "Column Spaced Values here")));
    
    C = new DefaultMutableTreeNode("Drive C");
    DefaultMutableTreeNode documents = new DefaultMutableTreeNode("documents");
    C.add(documents);
    DefaultMutableTreeNode downloads = new DefaultMutableTreeNode("downloads");
    documents.add(downloads);
    downloads.add(new DefaultMutableTreeNode(new file("chrome","exe", "Install for Google Chrome")));
    downloads.add(new DefaultMutableTreeNode(new file("photoshop","zip","Download for photoshop")));
    documents.add(new DefaultMutableTreeNode(new file("familypic1","png","Family Picture 1")));
    

    //add the child nodes to the root node
    root.add(A);
    root.add(B);
    root.add(C);
    tree = new JTree(root);
    model = (DefaultTreeModel)(tree.getModel());
    
    
    tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
  
        public void valueChanged(TreeSelectionEvent e) {
            selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            removeButton.setEnabled(true);
            printButton.setEnabled(true);
   
        }
    });
    
    

    consoleLabel = new JLabel("Console ");
    consoleLabel.setLocation(500, 50);
    consoleLabel.setSize(300, 30);
    consoleLabel.setHorizontalAlignment(0);
    consoleLabel.setForeground(Color.red);
    totalGUI.add(consoleLabel);

    scorePanel = new JPanel();
    scorePanel.setLayout(null);
    scorePanel.setLocation(10, 40);
    scorePanel.setSize(450, 600);
    totalGUI.add(scorePanel);

    allFiles = new JPanel();
    allFiles.setLocation(0, 0);
    allFiles.setSize(450, 600);
    allFiles.add(tree);
    scorePanel.add(allFiles);


    buttonPanel = new JPanel();
    buttonPanel.setLayout(null);
    buttonPanel.setLocation(10, 650);
    buttonPanel.setSize(500, 250);
    totalGUI.add(buttonPanel);
    
    newFilePanel = new JPanel();
    newFilePanel.setLayout(null);
    newFilePanel.setLocation(500, 400);
    totalGUI.add(newFilePanel);
    
    fileName = new JLabel("New File Name: ");
    fileName.setLocation(0,40);
    fileName.setSize(160, 30);
    fileName.setForeground(Color.black);
    buttonPanel.add(fileName);
    
    nameOfFile = new JTextArea("");
    nameOfFile.setLocation(170, 40);
    nameOfFile.setSize(90, 20);
    buttonPanel.add(nameOfFile);
    
    extension = new JLabel("File Extension: ");
    extension.setLocation(0,70);
    extension.setSize(160,30);
    extension.setForeground(Color.black);
    buttonPanel.add(extension);

    ext = new JTextArea("");
    ext.setLocation(170,70);
    ext.setSize(30, 20);
    buttonPanel.add(ext);
    
    content = new JLabel("Content for new file: ");
    content.setLocation(0,100);
    content.setSize(160, 30);
    content.setForeground(Color.black);
    buttonPanel.add(content);
    
    contentText = new JTextArea("");
    contentText.setLocation(170,100);
    contentText.setSize(200, 30);
    contentText.setLineWrap(true);
    buttonPanel.add(contentText);
    
    console = new JTextArea("");
    console.setLocation(500, 100);
    console.setEditable(false);
    console.setSize(450, 350);
    console.setLineWrap(true);
    totalGUI.add(console);
    
    addButton = new JButton("Add New File");
    addButton.setLocation(0, 130);
    addButton.setSize(170, 30);
    addButton.addActionListener(this);
    buttonPanel.add(addButton);

    removeButton = new JButton("Remove file");
    removeButton.setLocation(220, 0);
    removeButton.setSize(170, 30);
    removeButton.addActionListener(this);
    removeButton.setEnabled(false);
    buttonPanel.add(removeButton);

    printButton = new JButton("Print");
    printButton.setLocation(0, 0);
    printButton.setSize(170, 30);
    printButton.addActionListener(this);
    printButton.setEnabled(false);
    buttonPanel.add(printButton);
    
    totalGUI.setOpaque(true);
    return totalGUI;
}


public void actionPerformed(ActionEvent e) {
    if(e.getSource() == addButton)
    {
    	model.insertNodeInto(new DefaultMutableTreeNode(new file(nameOfFile.getText(), ext.getText(), contentText.getText())), selectedNode, 0);
    	nameOfFile.setText("");
    	ext.setText("");
    	contentText.setText("");
    	//model.insertNodeInto(new DefaultMutableTreeNode(files.get(0)), selectedNode, 0);
    	
    }
    else if(e.getSource() == removeButton)
    {
    	model.removeNodeFromParent(selectedNode);
    	removeButton.setEnabled(false);
    }
    else if(e.getSource() == printButton)
    {try{
    	file f = (file)selectedNode.getUserObject();
    	console.append(f.toString2() + "\n");
    }
    catch (ClassCastException C) {
    	console.append(selectedNode.toString() + "\n");
    }
  
    }
}

private static void createAndShowGUI() {

    JFrame.setDefaultLookAndFeelDecorated(true);
    JFrame frame = new JFrame("Virtual File System");

    //Create and set up the content pane.
    VirtualFileSystem vfs = new VirtualFileSystem();
    frame.setContentPane(vfs.createContentPane());

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1000, 1000);    
    frame.setVisible(true);
}

public static void main(String[] args) {

    SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            createAndShowGUI();
        }
    });
}

}
	


