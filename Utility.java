import javax.swing.*;
import java.awt.*;

class Utility extends Property
{
   public Player owner;
   public String name;
   private int price;

   public Utility(String n)
   {
      name = n;
      price = 150;
   }

   private void payRent(Player p)
   {
   	  int toll;
      if (owner.getDeeds().contains("Electric Company") &&
      	  owner.getDeeds().contains("Water Works"))
      {
      	 toll = p.getRoll() * 10;
      }

      else toll = p.getRoll() * 4;

      p.payment(toll * -1);
      owner.payment(toll);
   }
}

