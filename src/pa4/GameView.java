/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa4;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GameView extends JFrame implements ActionListener{
    private JTextField guessNumField;
    private JButton enterButton;
    private JButton againButton;
    private JLabel intro;
    private JLabel corrent;
    private JLabel wrong;
    private int randomNum;
    private JPanel content; 
    
    private boolean started=false;
    
    GameView(){
        
        guessNumField = new JTextField(20);
        enterButton = new JButton("Enter");
        againButton = new JButton("Play Again");
        intro = new JLabel("I have a number between 1 and 1000. Can you guess my number?\n" +"Please enter your first guess.");
        wrong = new JLabel("Please enter number only");
        corrent = new JLabel("Correct");
        
        content = new JPanel();

         content.add(intro);
        content.add(guessNumField);
        content.add(enterButton);
       
        content.add(wrong);
        content.add(corrent);
        content.add(againButton);
        
        this.setContentPane(content);
 
        this.setSize(700,400);
        this.setTitle("Guess the number");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        enterButton.addActionListener(this);
//        againButton.addActionListener(this);
        Start();
        
    }
    public void Start(){
        started=true;
        guessNumField.setText("");
        guessNumField.setEditable(true);
        randomNum = 1 + (int)(Math.random() * 1000); 
        System.out.print("Answer:"+randomNum+"  ");
        content.setBackground(Color.white);
        againButton.setVisible(false);
        corrent.setVisible(false);
        wrong.setVisible(false);
        corrent.setBackground(Color.white);
        corrent.setOpaque(true);
        wrong.setBackground(Color.white);
        wrong.setOpaque(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        wrong.setVisible(false);
        Object obj = e.getSource();
        if (obj == enterButton ){
            
            String userInput = guessNumField.getText();
            if(userInput.matches("[0-9]+")==false){
                
                wrong.setVisible(true);
                wrong.setText("Please enter number only");
            }
            else
            {
                int input = Integer.parseInt(userInput);

                if (randomNum>input){
                    content.setBackground(Color.blue);
                    
                    wrong.setVisible(true);
                    wrong.setText("Too Low");
                }
                else if(randomNum<input){
                    content.setBackground(Color.red);
                    wrong.setVisible(true);
                    wrong.setText("Too High");
                }
                else if(randomNum==input){
                    started=false;
                    guessNumField.setEditable(false);
                    againButton.setVisible(true);
                    corrent.setVisible(true);
                    againButton.addActionListener(this);
                }
            }
            
        }
        if(obj == againButton && started ==false){
            Start();
        }
    }
}
