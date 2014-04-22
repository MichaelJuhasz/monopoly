import javax.swing.*;
import java.awt.*;

class Street extends Property
{
   private int number, rent, oneHs, twoHs, threeHs, fourHs, hotel, price, bldgCost, houses;
   public Player owner;
   public String name;
   public Color color;
   private ArrayList<Street> group = new ArrayList<Street>();
   private boolean monopoly = false;

   private StreetPanel streetpanel;

   public Street(int num, int r, int one, int two, int three, int four, 
   	               int htl, int p, int bldgC, String n, Color c, 
   	               Street g1, Street g2, Street g3)
   {
   	super(num, p, n);

      streetpanel = new StreetPanel(this);

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
      group.add(g1);
      group.add(g2);
      group.add(g3);

      houses = 0;
   }

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

   public void upgrade(Player p)
   {
      if (houses >= 5) JOptionPane.showMessageDialog(name+" is already fully upgraded.", "Cannot Upgrade.", JOptionPane.ERROR_MESSAGE);
      
      else 
      {
	      if (monopoly)
	      {
	         if((group.size() == 2 && Math.abs(group.get(0).houses - group.get(1).houses) > 1) || 
	         	(group.size() == 3 && Math.abs(group.get(0).houses - group.get(1).houses) > 1 || 
	         	                      Math.abs(group.get(1).houses - group.get(2).houses) > 1 ||
	         	                      Math.abs(group.get(0).houses - group.get(2).houses > 1)))
	         {
	            JOptionPane.showMessageDialog("Properties must be upgraded equally.", "Cannot Upgrade.", JOptionPane.ERROR_MESSAGE);
	         }
	         else if (p.getFunds >= bldgCost) 
	         {
	            JOptionPane.showMessageDialog(name+" upgraded!", "Success!");
	            houses++;
	            p.payment(bldgCost * -1);   
	         }

	         else if (p.getFunds < bldgCost) JOptionPane.showMessageDialog(null,"Not enough funds!","Not enough funds!", JOptionPane.ERROR_MESSAGE);
	      }

	      else JOptionPane.showMessageDialog("You must own all properties of this color before any can be upgraded.", "Cannot Upgrade.", JOptionPane.ERROR_MESSAGE);
      }
   }

   private void updateGraphics()
   {
      streetpanel.update();
   }

   public JPanel getPanel()
   {
      return streetpanel;
   }
}