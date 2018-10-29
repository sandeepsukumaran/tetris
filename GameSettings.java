package io.github.sandeepsukumaran.javatetrisgame;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import static javax.swing.BoxLayout.PAGE_AXIS;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;

/**
 * Class used to display dialog for adjusting gameplay settings prior to game start.
 */
class GameSettings extends JFrame implements ActionListener{
    
    GameSettings(){
        super("Settings");
        init();
    }
    
    private void init(){
        Mslider = new JSlider(1,10,5);
        Nslider = new JSlider(20,50,20);
        Sslider = new JSlider (1,10,1);
        
        currMSliderVal = 5;
        currNSliderVal = 20;
        currSSliderVal = 0.1f;
        
        /*
            Change listeners to update labels when sliders are moved
        */
        /*
        *
        *
        *  obfuscated
        *
        */
        
        /*
        *
        *
        *  obfuscated
        *
        */
        
        /*
        *
        *
        *  obfuscated
        *
        */
        
        // *****size of square***********
        normalSize = new JRadioButton("Normal",true);
        enlargedSize = new JRadioButton("Enlarged");
        squareSizeRadioButtonGroup = new ButtonGroup();
        squareSizeRadioButtonGroup.add(normalSize);
        squareSizeRadioButtonGroup.add(enlargedSize);
        //obfuscated
        //obfuscated
        sizeRadioButtonsPanel.add(enlargedSize);
        
        
        // *******wnos and hnos*********
        wnosDefault = 10;
        horizontalNoOfSquaresField = new JFormattedTextField(NumberFormat.getInstance().format(wnosDefault));
        //obfuscated
        //obfuscated
        horizontalNoOfSquaresField.addPropertyChangeListener("value", (PropertyChangeEvent e) -> {
            if(horizontalNoOfSquaresField.getText().trim().length()==0){//empty field
                horizontalNoOfSquaresField.setValue(wnosDefault);
                verticalNoOfSquaresField.setText(Integer.toString(wnosDefault* 2));
            }else{
                if((int)horizontalNoOfSquaresField.getValue()<8){
                    horizontalNoOfSquaresField.setValue(8);
                    verticalNoOfSquaresField.setText("16");
                }else if((int)horizontalNoOfSquaresField.getValue()>20){
                    horizontalNoOfSquaresField.setValue(20);
                    verticalNoOfSquaresField.setText("40");
                }else
                    verticalNoOfSquaresField.setText(Integer.toString((int)horizontalNoOfSquaresField.getValue()* 2));
            }
        });
        
        verticalNoOfSquaresField = new JTextField("20",2);
        verticalNoOfSquaresField.setEnabled(false);
        horizontalNoOfSquaresLabel = new JLabel("Horizontal");
        verticalNoOfSquaresLabel = new JLabel("Vertical");
        noOfSquaresPanel = new JPanel();
        //obfuscated
        //obfuscated
        noOfSquaresPanel.add(horizontalNoOfSquaresField);
        //obfuscated
        //obfuscated
        noOfSquaresPanel.add(Box.createRigidArea(new Dimension(2,0)));
        noOfSquaresPanel.add(verticalNoOfSquaresField);
        
        scoringFactorLabel = new JLabel("Scoring Factor (1-10) : "+Integer.toString(currMSliderVal));
        //obfuscated
        speedFactorLabel = new JLabel("Speed Factor (0.1-1) : "+Float.toString(currSSliderVal));
        
        startButton = new JButton("START");
        startButton.setToolTipText("Press to start the game");
        
        startButton.addActionListener(this);
        
        this.setLayout(new BoxLayout(this.getContentPane() , PAGE_AXIS));
        this.add(scoringFactorLabel);
        this.add(Mslider);
        this.add(Box.createRigidArea(new Dimension(0,10)));
        //obfuscated
        //obfuscated
        this.add(Box.createRigidArea(new Dimension(0,10)));
        this.add(speedFactorLabel);
        this.add(Sslider);
        //obfuscated
        //obfuscated
        this.add(sizeRadioButtonsPanel);
        this.add(Box.createRigidArea(new Dimension(0,10)));
        //obfuscated
        //obfuscated
        this.add(Box.createRigidArea(new Dimension(0,10)));
        this.add(startButton);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.toFront();
    }
    /*following mandated by design guidelines used in class mainGameFrame
    static int M;       //SCORING FACTOR: 1-10
    static int N;       //NO. OF ROWS REQUIRED FOR EACH LEVEL OF DIFFICULTY: 20-50
    static float S;     //SPEED FACTOR: 0.1-1.0
    */
    JSlider Mslider;
    JSlider Nslider;
    JSlider Sslider;
    JButton startButton;
    JRadioButton normalSize;
    //obfuscated
    //obfuscated
    JPanel sizeRadioButtonsPanel;
    float fl_sideOfSquare;
    int horizontalNoOfSquares; //wnos
    int verticalNoOfSqaures;   //hnos
    int wnosDefault;
    JFormattedTextField horizontalNoOfSquaresField;
    JTextField verticalNoOfSquaresField;
    JPanel noOfSquaresPanel;
    //obfuscated
    //obfuscated
    int currMSliderVal;
    int currNSliderVal;
    //obfuscated
    //obfuscated
    JLabel rowsForDiffLabel;
    JLabel speedFactorLabel;

    /**
     * Method to validate inputs, set parameters of game as per user's choice, and launch game.
     * Event handler for press of start button.
     * @param event generated by press of start button
     */
    @Override
    public void actionPerformed(ActionEvent e){
        /*
            Called when start button in JDialog is pressed
        */
        //Suitably set values
        fl_sideOfSquare = (normalSize.isSelected())? 4.0f : 8.0f;
        horizontalNoOfSquares = (horizontalNoOfSquaresField.getText().trim().length()==0)?wnosDefault:(int)horizontalNoOfSquaresField.getValue();
        if(horizontalNoOfSquares == wnosDefault)
            horizontalNoOfSquaresField.setText(Integer.toString(wnosDefault));

        verticalNoOfSquaresField.setText(Integer.toString((int)horizontalNoOfSquaresField.getValue()* 2));
        
        //Start the game
        listeners.stream().forEach((hl) -> {
                //obfuscated
            });

        this.dispose();
    }
    
    private final List<myListener> listeners = new ArrayList<>();

    public void addListener(myListener toAdd) {
        listeners.add(toAdd);
    }
}
