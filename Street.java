import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Street extends Property
{
   private int number, rent, oneHs, twoHs, threeHs, fourHs, hotel, price, bldgCost, houses;
  // public Player owner;
 // public String name;
   public Color color;
   private ArrayList<Street> group = new ArrayList<Street>();
   private boolean monopoly = false;

   private StreetPanel streetpanel;

   public Street(int num, int r, int one, int two, int three, int four,
    int htl, int p, int bldgC, String n, Color c)
   {
    super(num, p, n);

      rent = r;
      oneHs = one;
      twoHs = two;
      threeHs = three;
      fourHs = four;
      hotel = htl;
      //price = p;
      bldgCost = bldgC;
      //name = n;
      color = c;
      houses = 0;
      streetpanel = new StreetPanel(this);
   }

   public void setGroup(Street s1, Street s2, Street s3)
   {
      if (group.isEmpty())
      {
         group.add(s1);
         group.add(s2);
         group.add(s3);
      }
   }

   public void setGroup(Street s1, Street s2)
   {
      if (group.isEmpty())
      {
         group.add(s1);
         group.add(s2);
      }
   }

   public void payRent(Player p)
   {
    int toll = 0;
      if (!checkMonopoly()) toll = rent;
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
      JOptionPane.showMessageDialog(null, "You must pay $"+toll+" to "+owner.getName(), "Pay Rent.", JOptionPane.ERROR_MESSAGE);
      p.payment(-toll);
      owner.payment(toll);
   }

   private boolean checkMonopoly()
   {
      if (group.size() == 2)
      {
         if (group.get(0).owner == group.get(1).owner) return true;
      }
      else if (group.size() == 3)
      {
         if (group.get(0).owner == group.get(1).owner &&
          group.get(1).owner == group.get(2).owner) return true;
      }
      return false;
   }

   // You can only upgrade a tile if you have a monopoly on that group
   // and you must upgrade all properties equally - i.e. a property cannot
   // be upgraded if it has more houses than any other property in the group
   public void upgradeStreet(Player p)
   {
      if (houses >= 5) JOptionPane.showMessageDialog(null, name+" is already fully upgraded.", "Cannot Upgrade.", JOptionPane.ERROR_MESSAGE);
      
      else
      {
         if (checkMonopoly())
         {
            int i = group.indexOf(this);
            boolean upgradable = true;

            for (Street prop : group)
            {
               if (prop.houses < this.houses)
               {
                  upgradable = false;
                  JOptionPane.showMessageDialog(null, "Properties must be upgraded equally.", "Cannot Upgrade.", JOptionPane.ERROR_MESSAGE);
               }
            }

            if (upgradable)
            {
               if (p.getFunds() >= bldgCost)
               {
                  JOptionPane.showMessageDialog(null, name+" upgraded!", "Success!", JOptionPane.INFORMATION_MESSAGE);
                  houses++;
                  p.payment(bldgCost * -1);
                  updateGraphics();
               }

               else JOptionPane.showMessageDialog(null,"Not enough funds!","Not enough funds!", JOptionPane.ERROR_MESSAGE);
            }
         }

         else JOptionPane.showMessageDialog(null, "You must own all properties of this color before any can be upgraded.", "Cannot Upgrade.", JOptionPane.ERROR_MESSAGE);
      }
   }

   public void updateGraphics()
   {
      streetpanel.update(guests, houses);
   }

   // Used by main to instantiate StreetPanel objects.
   public JPanel getPanel()
   {
      return streetpanel;
   }
}