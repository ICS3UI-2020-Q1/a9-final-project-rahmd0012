import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Main implements Runnable, ActionListener{

  // Class Variables
  JPanel gameScreen;
  JPanel rulesScreen;

  JPanel mainPanel;

  JLabel pictureLeft;
  ImageIcon leftRockImage;
  ImageIcon leftPaperImage;
  ImageIcon leftScissorsImage;

  JLabel pictureRight;
  ImageIcon rightRockImage;
  ImageIcon rightPaperImage;
  ImageIcon rightScissorsImage;

  JButton beginButton;
  JButton newGameButton;
  JButton startButton;

  CardLayout screens;

  JTextField rpsInput;
  JTextField winnerInput;

  JTextArea rulesArea;

  Font biggerText;

  // create an array to store the left pictures
  ImageIcon[] leftPictures = new ImageIcon[3];

  // create an array to store the right pictures
  ImageIcon[] rightPictures = new ImageIcon[3];


  // Method to assemble our GUI
  public void run(){
    // Creats a JFrame that is 800 pixels by 600 pixels, and closes when you click on the X
    JFrame frame = new JFrame("Rock, Paper, Scissors");
    // Makes the X button close the program
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // makes the windows 800 pixel wide by 600 pixels tall
    frame.setSize(800,600);
    // shows the window
    frame.setVisible(true);

    // create the main panel
    gameScreen = new JPanel();
    gameScreen.setLayout(null); 

    // initialize the text fields
    rpsInput = new JTextField("Get Ready!");
    winnerInput = new JTextField("Who will win?");

    // set the location and size of the text fields
    rpsInput.setBounds(170, 10, 600, 40);
    winnerInput.setBounds(10, 500, 530, 40);

    // set up the button to start the game 
    beginButton = new JButton("Begin!");
    beginButton.addActionListener(this);
    beginButton.setActionCommand("Begin!");

    // set up the button to start a new game
    newGameButton = new JButton("Play Again!");
    newGameButton.addActionListener(this);
    newGameButton.setActionCommand("Play Again!");

    // set the location and size of the buttons
    beginButton.setBounds(10, 10, 150, 40);
    newGameButton.setBounds(550, 500, 200, 40);

    // set up the images
    leftRockImage = new ImageIcon("Left Rock.jpg");
    rightRockImage = new ImageIcon("Right Rock.jpg");
    leftPaperImage = new ImageIcon("Left Paper.jpg");
    rightPaperImage = new ImageIcon("Right Paper.jpg");
    leftScissorsImage = new ImageIcon("Left Scissor.jpg");
    rightScissorsImage = new ImageIcon("Right Scissor.jpg");

    // create a jlabel with an image
    pictureLeft = new JLabel(leftRockImage);
    pictureLeft.setBounds(10, 60, 380, 430);
    pictureRight = new JLabel(rightRockImage);
    pictureRight.setBounds(400, 60, 380, 430);

    // store the left pictures in the array
    leftPictures[0] = leftRockImage;
    leftPictures[1] = leftPaperImage;
    leftPictures[2] = leftScissorsImage;

    // store the right pictures in the array 
    rightPictures[0] = rightRockImage;
    rightPictures[1] = rightPaperImage;
    rightPictures[2] = rightScissorsImage;

    // disable the text fields so the user cannot type in them
    rpsInput.setEnabled(false);
    winnerInput.setEnabled(false);

    // create a bigger font to use 
    biggerText = new Font("arial", Font.BOLD, 20);

    // set the font in the area I want to use it
    rpsInput.setFont(biggerText);
    winnerInput.setFont(biggerText);

    // add pieces to the screen
    gameScreen.add(beginButton);
    gameScreen.add(newGameButton);
    gameScreen.add(pictureLeft);
    gameScreen.add(pictureRight);
    gameScreen.add(rpsInput);
    gameScreen.add(winnerInput);

    // set up a rules screen
    rulesScreen = new JPanel();
    rulesScreen.setLayout(null);

    // create the button to start the game
    startButton = new JButton("START GAME");
    startButton.setBounds(300, 250, 200, 100);
    startButton.addActionListener(this);
    startButton.setActionCommand("start");

    // initialize the text area 
    rulesArea = new JTextArea();

    // create a string that holds the rules 
    String rules = "If both sides are Rock, Paper, or Scissors it is a tie.\nIf it is Rock and Paper, then Paper wins.\nIf it is Rock and Scissors, then Rock wins.\nIf it is Paper and Scissors, then Scissors wins.\nEnjoy the game and have fun!";

    // set the text in the rules area 
    rulesArea.setText(rules);

    // set the location and size of the rules 
    rulesArea.setBounds(100, 400, 600, 150);

    // disable the text area so the user cannot type in it
    rulesArea.setEnabled(false);

    // add a bigger font to the rules area
    rulesArea.setFont(biggerText);

    // add the start button and rules to the rules screen
    rulesScreen.add(startButton);
    rulesScreen.add(rulesArea);

    // create the screen jpanel manager
    screens = new CardLayout();

    mainPanel = new JPanel();
    mainPanel.setLayout(screens);

    // add the main panel to the CardLayout
    mainPanel.add(rulesScreen, "rulesScreen");
    mainPanel.add(gameScreen, "gameScreen");

    frame.add(mainPanel);

    // sets the screen to show by asking the card layout 
    screens.show(mainPanel, "rulesScreen");

  }

  // method called when a button is pressed
  public void actionPerformed(ActionEvent e){
    // get the command from the action
    String command = e.getActionCommand();

    // make something happen when the buttons are pressed
    if(command.equals("Begin!")){
      // set the top text field to count down
      rpsInput.setText("Rock, Paper, Scissors, SHOOT!");
      // create a random picture generator for the left side
      Random randLeft = new Random();

      // get the random generator to choose a random left picture
      int randLeftInt = randLeft.nextInt(3);

      // create a random picture generator for the right side
      Random randRight = new Random();

      // get the random generator to choose a random right picture
      int randRightInt = randRight.nextInt(3);
      // change the left picture to a random image
      pictureLeft.setIcon(leftPictures[randLeftInt]);
      // change the right picture to a random image 
      pictureRight.setIcon(rightPictures[randRightInt]);
      // round ends
      winnerInput.setText("What a Game! Press Play again to retry!");
      // disable the begin button
      beginButton.setEnabled(false);
    }else if(command.equals("Play Again!")){
      // reset the text for more games
      rpsInput.setText("Get Ready!");
      winnerInput.setText("Who will win?");
      // reset the pictures back to Rock
      pictureLeft.setIcon(leftRockImage);
      pictureRight.setIcon(rightRockImage);
      // reenable the beging button
      beginButton.setEnabled(true);
    }else if(command.equals("start")){
      // switch to game screen
      screens.show(mainPanel, "gameScreen");
    }


  }

  // Main method to start our program
  public static void main(String[] args){
    // Creates an instance of our program
    Main gui = new Main();
    // Lets the computer know to start it in the event thread
    SwingUtilities.invokeLater(gui);
  }
}
