import java.awt.*;
import java.util.Random;
import javax.swing.*;

class Dice extends JPanel
{
   private ImageIcon one = new ImageIcon("images/one.png");
   private ImageIcon two = new ImageIcon("images/two.png");
   private ImageIcon three = new ImageIcon("images/three.png");
   private ImageIcon four = new ImageIcon("images/four.png");
   private ImageIcon five = new ImageIcon("images/five.png");
   private ImageIcon six = new ImageIcon("images/six.png");
   JLabel dieOne;
   JLabel dieTwo;
   
   // this is a singleton
   private static Dice instance = null;
   
   public synchronized static Dice getInstance() 
   {
      if (instance == null) 
      {
         instance = new Dice();
      }
      return instance;
   }
   
   protected Dice()
   {  
      setBackground(Color.WHITE);
	    dieOne = new JLabel(one);
	    dieTwo = new JLabel(six);
      setLayout(new GridLayout(1,2));
      add(dieOne);
      add(dieTwo);
   }

   public int roll(Player p)
   {
      Random rand = new Random();
      int roll1 = rand.nextInt(6) + 1;
      int roll2 = rand.nextInt(6) + 1;

      System.out.println(roll1+"\t"+roll2);
      if (roll1 == roll2) p.doubleRoll();

      setImage(roll1, dieOne);
      setImage(roll2, dieTwo);

      return(roll1 + roll2);
   }

   private void setImage(int roll, JLabel die)
   {
      switch (roll)
      {
         case 1: die.setIcon(one);
                 break;
         case 2: die.setIcon(two);
                 break;
         case 3: die.setIcon(three);
                 break;
         case 4: die.setIcon(four);
                 break;
         case 5: die.setIcon(five);
                 break;
         case 6: die.setIcon(six);
                 break;                    
       }
    }
}