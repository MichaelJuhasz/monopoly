import javax.swing.*;
import java.awt.*;

class Street extends Property
{
   private int rent, oneHs, twoHs, threeHs, fourHs, hotel, price, bldgCost, houses;
   public Player owner;
   private String name;
   private Color color;
   private ArrayList<Street> group = new ArrayList<Street>();
   private boolean monopoly = false;

   public Street(int r, int one, int two, int three, int four, 
   	               int htl, int p, int bldgC, String n, Color c, 
   	               Street g1, Street g2, Street g3)
   {
      rent = r;
      oneHs = one;
      twoHs = two;
      threeHs = three;
      fourHs = four;
      hotel = htl;
      price = p;
      bldgCost = bldgC;
      name = n;
      color = c;
      group.add(g1);
      group.add(g2);
      group.add(g3);

      houses = 0;
   }

   // private landedOn(Player p)
   // {
   //    if (owner == null) 
   //    {
   //       int response = JOptionPane.showOptionDialog(
   //       	               null,
   //       	               "Do you want to buy "+name+" for "+price+"?",
   //       	               name,
   //       	               JOptionPane.YES_NO_OPTION,
   //       	               JOptionPane.PLAIN_MESSAGE,
   //       	               null,
   //       	               {"Buy", "Pass"},
   //       	               "Buy"
   //       	            );
   //       if (response == 0)
   //       {
   //          if (p.getFunds() < price) JOptionPane.showMessageDialog(null,"Not enough funds!","Not enough funds!", JOptionPane.ERROR_MESSAGE);
   //          else 
   //          {
   //             p.payment(price);
   //             owner = p;
   //             JOptionPane.showMessageDialog("Congratulations, you now own "+name, name+" purchased", JOptionPane.INFORMATION_MESSAGE);
   //          }
   //       }
   //    }

   //    else if (owner != p) 
   //    {
   //       payRent(p);
   //    }
   // }

   private void payRent(Player p)
   {
   	  int toll;
      if (!monopoly) toll = rent;
      else
      {
         switch (houses)
         {
            case 0: toll = (rent * 2);
                    break;
            case 1: toll = oneHs;
                    break;
            case 2: toll = twoHs;
                    break;
            case 3: toll = threeHs;
                    break;
            case 4: toll = fourHs;
                    break;
            case 5: toll = hotel;
                    break;                                
         }

      }
      p.payment(toll * -1);
      owner.payment(toll);
   }

   private void checkMonopoly()
   {
      if (group.size() == 2)
      {
         if (group.get(0).owner == group.get(1).owner) monopoly = true;
      }
      else if (group.size() ==3)
      {
         if (group.get(0).owner == group.get(1).owner &&
         	 group.get(1).owner == group.get(2).owner) monopoly == true;
      } 
   }
}