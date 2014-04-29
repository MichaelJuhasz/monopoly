import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *  ControlPanel serves as a view/controller for Player and 
 *  also manages gameplay, enabling players to take actions 
 *  and passing the turn from Player to Player. 
 * @author:  Michael Juhasz
 * @version: Last modified 4/28/14
 */

class ControlPanel extends JPanel implements ActionListener
{
   private JLabel label = new JLabel();
   private JLabel funds = new JLabel("Funds: ");
   private JButton roll = new JButton ("Roll Dice");
   private JButton upgrade = new JButton ("Upgrade Property");
   private JButton fine = new JButton ("Pay Fine");
   private JButton end = new JButton ("End Turn");
   private Player player;
   private CircularLinkedList players;
   private Node playerNode;
   private static ControlPanel instance = null;
   
   /**
    *  ControlPanel is a singleton.
    */
   public synchronized static ControlPanel getInstance() 
   {
      if (instance == null) 
      {
         instance = new ControlPanel();
      }
      return instance;
   }

   /**
    *  Constructor sets up the various components within the JPanel,
    *  including Dice.
    */
   public ControlPanel()
   {
      setBackground(Color.WHITE);
      setLayout(new GridBagLayout());
      
      GridBagConstraints labelC = new GridBagConstraints();
      labelC.fill = GridBagConstraints.HORIZONTAL;
      labelC.gridx = 0;
      labelC.gridy = 0;
      labelC.gridwidth = 2;
      labelC.ipady = 20;

      label.setFont(new Font("TimesRoman", Font.BOLD, 20));
      add(label, labelC);

      GridBagConstraints fundsC = new GridBagConstraints();
      fundsC.fill = GridBagConstraints.HORIZONTAL;
      fundsC.gridx = 0;
      fundsC.gridy = 1;
      fundsC.gridwidth = 2;
      fundsC.ipady = 20;
      add(funds, fundsC);

      GridBagConstraints rollC = new GridBagConstraints();
      rollC.fill = GridBagConstraints.HORIZONTAL;
      rollC.gridx = 0;
      rollC.gridy = 2;
      rollC.gridwidth = 2;
      roll.addActionListener(this);
      add(roll, rollC);

      GridBagConstraints upgradeC = new GridBagConstraints();
      upgradeC.fill = GridBagConstraints.HORIZONTAL;
      upgradeC.gridx = 0;
      upgradeC.gridy = 3;
      upgrade.addActionListener(this);
      add(upgrade, upgradeC);

      GridBagConstraints fineC = new GridBagConstraints();
      fineC.fill = GridBagConstraints.HORIZONTAL;
      fineC.gridx = 1;
      fineC.gridy = 3;
      fine.addActionListener(this);
      add(fine, fineC);

      GridBagConstraints endC = new GridBagConstraints();
      endC.fill = GridBagConstraints.HORIZONTAL;
      endC.gridx = 0;
      endC.gridy = 4;
      endC.gridwidth = 2;
      end.addActionListener(this);
      add(end, endC);
      
      GridBagConstraints diceC = new GridBagConstraints();
      diceC.fill = GridBagConstraints.HORIZONTAL;
      diceC.gridx = 0;
      diceC.gridy = 5;
      diceC.gridwidth = 2;
      diceC.ipady = 60;
      JPanel diceP = Dice.getInstance();
      add(diceP, diceC);
   }

   /**
    *  Called only once by main, setList sets the initial CircularLinkedList
    *  of Players
    *
    *  @param ps  a CircularLinkedList of Players
    */
   public void setList(CircularLinkedList ps)
   {
      players = ps;
      playerNode = players.getNodeAt(0);
   }

   /**
    *  Sets the Player referenced by the graphical components.  Called
    *  first by main and thereafter only when a player ends his or her
    *  turn.  Also checks to see if there's only one Player left in 
    *  play and if so, informs him or her of this fact.
    *
    *  @param p   the Player referenced by the buttons and labels 
    */
   public void takeATurn(Player p)
   {
      player = p;
      label.setText(p.getName());
      updateFunds();
      player.beginTurn();

      if (players.getSize() == 1) 
      {
         JOptionPane.showMessageDialog(null, player.getName()+ "has won!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
      }
   }

   /**
    *  Sets the text of funds label.  Also checks to see if a Player's 
    *  funds have dropped below 0.  If so, drops that Player from the game. 
    */
   public void updateFunds()
   {
      funds.setText("Funds: "+player.getFunds());
      if (player.getFunds() < 0) 
      {
         JOptionPane.showMessageDialog(null, player.getName()+" is out of the game.","Game Over", JOptionPane.ERROR_MESSAGE);
         player.endTurn();
         playerNode = players.getNext(playerNode);
         takeATurn((Player)playerNode.getData());
         players.remove(playerNode);
      }
   }

   /**
    *  Action listeners.  Listens for the buttons and calls corresponding
    *  Player methods.  End only works if the player can't roll and if 
    *  the player okays the end of turn.  Thereupon it gets the next
    *  Player in the players list and feeds it into takeATurn.
    *
    * @param e  the action event being listened for.
    */
   public void actionPerformed(ActionEvent e)
   {
      JButton b = (JButton)e.getSource();
      if (b == roll)
      {
         if (player.getCanRoll()) player.rollDice();
      }

      if (b == upgrade)
      {
         if (player.getCanUpgrade()) player.upgrade();
         else JOptionPane.showMessageDialog(null,"You have no properties to upgrade.","Nothing to upgrade!", JOptionPane.ERROR_MESSAGE);
      }

      if (b == fine)
      {
         if (player.getCanPayFine()) player.getOutOfJail();
         else JOptionPane.showMessageDialog(null,"You're not in jail.","No fines to pay!", JOptionPane.ERROR_MESSAGE);
      }

      if (b == end)
      {
         if (player.getCanRoll()) JOptionPane.showMessageDialog(null,"You haven't finished rolling.","Keep rolling!", JOptionPane.ERROR_MESSAGE);
         else if (player.endTurn())
         {
            playerNode = players.getNext(playerNode);
            takeATurn((Player)playerNode.getData());
         } 
      }
   }
}