import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.*;

public class Main implements ActionListener{    

int redScoreAmount = 0;
int blueScoreAmount = 0;
int counter = 0;
ArrayList<Process> allprocesses = new ArrayList<Process>();
PriorityQueue<Process> waitQ = new PriorityQueue<>(5,prioritycomp);
PriorityQueue<Process> blocked = new PriorityQueue<>(4, prioritycomp);
Process running;
JPanel titlePanel, scorePanel, buttonPanel;
JLabel consoleLabel, blueLabel, addedProcess, blueScore;
JButton addButton, runButton, contextSwitch, pStatus;
JTextArea console;

public JPanel createContentPane (){

    // We create a bottom JPanel to place everything on.
    JPanel totalGUI = new JPanel();
    totalGUI.setLayout(null);
    totalGUI.setSize(1000,1000);

    // Creation of a Panel to contain the title labels
    titlePanel = new JPanel();
    titlePanel.setLayout(null);
    titlePanel.setLocation(10, 0);
    titlePanel.setSize(250, 30);
    totalGUI.add(titlePanel);

    consoleLabel = new JLabel("Console ");
    consoleLabel.setLocation(0, 0);
    consoleLabel.setSize(300, 30);
    consoleLabel.setHorizontalAlignment(0);
    consoleLabel.setForeground(Color.red);
    titlePanel.add(consoleLabel);

    scorePanel = new JPanel();
    scorePanel.setLayout(null);
    scorePanel.setLocation(10, 40);
    scorePanel.setSize(450, 600);
    totalGUI.add(scorePanel);

    console = new JTextArea("");
    console.setLocation(0, 0);
    console.setEditable(false);
    //console.setText(t);
    console.setSize(450, 600);
    console.setLineWrap(true);
    scorePanel.add(console);


    buttonPanel = new JPanel();
    buttonPanel.setLayout(null);
    buttonPanel.setLocation(10, 650);
    buttonPanel.setSize(500, 70);
    totalGUI.add(buttonPanel);
    
    addButton = new JButton("Create Process");
    addButton.setLocation(0, 0);
    addButton.setSize(170, 30);
    addButton.addActionListener(this);
    buttonPanel.add(addButton);

    runButton = new JButton("Start");
    runButton.setLocation(220, 0);
    runButton.setSize(170, 30);
    runButton.addActionListener(this);
    runButton.setEnabled(false);
    buttonPanel.add(runButton);

    contextSwitch = new JButton("Switch");
    contextSwitch.setLocation(0, 40);
    contextSwitch.setSize(170, 30);
    contextSwitch.addActionListener(this);
    contextSwitch.setEnabled(false);
    buttonPanel.add(contextSwitch);
    
    pStatus = new JButton("Get Status");
    pStatus.setLocation(220, 40);
    pStatus.setSize(170,30);
    pStatus.addActionListener(this);
    pStatus.setEnabled(false);
    buttonPanel.add(pStatus);
    
    totalGUI.setOpaque(true);
    return totalGUI;
}


public void actionPerformed(ActionEvent e) {
    if(e.getSource() == addButton)
    {
    	if(counter > 1){
    	runButton.setEnabled(true);
    	pStatus.setEnabled(true);
    	}
    	allprocesses.add(new Process(counter,1+(int)(Math.random()*(2)),1));
    	counter++;
        console.append(""+allprocesses.get(0).toStringOne()+"\n");
        allprocesses.get(0).ready();
        waitQ.offer(allprocesses.get(0));
        allprocesses.remove(0);
        
    }
    else if(e.getSource() == runButton)
    {
    	running = waitQ.poll();
    	running.running();
        console.append(""+running.toStringOne() +"\n");
        runButton.setEnabled(false);
        contextSwitch.setEnabled(true);
       
    }
    else if(e.getSource() == contextSwitch)
    {
        console.append("\n");
        
       
        running.block();
        Process E = waitQ.poll();
        if(blocked.peek()!=null)
        	waitQ.add(blocked.poll());
        blocked.offer(running);
        if(E!=null)
        	running = E;
        else
        	running = waitQ.poll();
        running.running();
        console.append(""+running.toStringTwo());
        console.append("\n");
        console.append(running.toStringOne());
        console.append("Blocked Processes: "+blocked);
        console.append("\n");
        console.append("Ready/Waiting Processes:" +waitQ);
        console.append("\n");
       
  
    }
    else if(e.getSource() == pStatus)
    {
    	
    	console.append("SYSTEM STATUS: \n");
      	console.append("Running: "+running);
    	console.append("\n");
    	 console.append("Blocked Processes: "+blocked);
         console.append("\n");
         console.append("Ready/Waiting Processes:" +waitQ);
         console.append("\n");
    }
}

private static void createAndShowGUI() {

    JFrame.setDefaultLookAndFeelDecorated(true);
    JFrame frame = new JFrame("[=] JButton Scores! [=]");

    //Create and set up the content pane.
    Main dispatcher = new Main();
    frame.setContentPane(dispatcher.createContentPane());

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 800);    
    frame.setVisible(true);
}

public static void main(String[] args) {

    SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            createAndShowGUI();
        }
    });
}
public static Comparator<Process> prioritycomp = new Comparator<Process>(){
    
    @Override
    public int compare(Process c1, Process c2) {
        return (int) (c1.getPriority() - c2.getPriority());
    }
};
}
	


