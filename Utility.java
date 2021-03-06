import javax.swing.*;
import java.awt.*;
import java.util.*;

class Utility extends Property
{
   // public Player owner;
// public String name;
   private int price;
   private PropertyPanel utilityPanel;
   private ArrayList<Utility> group = new ArrayList<Utility>();

   public Utility(int num, String n)
   {
      super(num, 150, n);
      utilityPanel = new PropertyPanel(this);
   }

   public void setGroup(Utility s1, Utility s2)
   {
      if (group.isEmpty())
      {
         group.add(s1);
         group.add(s2);
      }
   }

   public void payRent(Player p)
   {
      ArrayList<Property> properties = owner.getDeeds();
      int utils = 0;

      for (int i = 0; i < properties.size(); i++)
      {
         Property prop = properties.get(i);
         if (prop instanceof Utility) utils++;
      }

      int toll;
      if (utils == 2)
      {
       toll = p.getRoll() * 10;
      }

      else toll = p.getRoll() * 4;
      
      JOptionPane.showMessageDialog(null, "You must pay $"+toll+" to "+owner.getName(), "Pay Rent.", JOptionPane.ERROR_MESSAGE);
      p.payment(toll * -1);
      owner.payment(toll);
   }

   public void updateGraphics()
   {
      utilityPanel.update(guests);
   }

   public JPanel getPanel()
   {
      return utilityPanel;
   }
}
