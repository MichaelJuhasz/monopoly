import java.awt.*;
import java.util.Random;

import javax.swing.*;

class Dice extends JPanel
{
   private int roll1, roll2;
   private ImageIcon one = createImageIcon("images/one.png");
   private ImageIcon two = createImageIcon("images/two.png");
   private ImageIcon three = createImageIcon("images/three.png");
   private ImageIcon four = createImageIcon("images/four.png");
   private ImageIcon five = createImageIcon("images/five.png");
   private ImageIcon six = createImageIcon("images/six.png");
   JLabel dieOne;
   JLabel dieTwo;
   
   public Dice()
   {  
	  dieOne = new JLabel();
	  dieTwo = new JLabel();
      setLayout(new GridLayout(1,2));
      add(dieOne);
      add(dieTwo);
   }

   public static int roll(Player p)
   {
      Random rand = new Random();
      int roll1 = rand.nextInt(6) + 1;
      int roll2 = rand.nextInt(6) + 1;

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