import javax.swing.*;
import java.awt.*;

abstract class Property extends Tile
{
   private int price;
   public Player owner;
   private String name;

   public Property(int p, String n)
   {
      price = p;
      name = n;
   }

   private landedOn(Player p)
   {
      if (owner == null) 
      {
         int response = JOptionPane.showOptionDialog(
         	               null,
         	               "Do you want to buy "+name+" for "+price+"?",
         	               name,
         	               JOptionPane.YES_NO_OPTION,
         	               JOptionPane.PLAIN_MESSAGE,
         	               null,
         	               {"Buy", "Pass"},
         	               "Buy"
         	            );
         if (response == 0)
         {
            if (p.getFunds() < price) JOptionPane.showMessageDialog(null,"Not enough funds!","Not enough funds!", JOptionPane.ERROR_MESSAGE);
            else 
            {
               p.payment(price * -1);
               p.addAsset(price);
               p.addDeed(this);
               owner = p;
               JOptionPane.showMessageDialog("Congratulations, you now own "+name, name+" purchased", JOptionPane.INFORMATION_MESSAGE);
            }
         }
      }

      else if (owner != p) 
      {
         payRent(p);
      }
   }

   private abstract void payRent(Player p);
}