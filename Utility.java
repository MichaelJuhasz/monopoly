import javax.swing.*;
import java.awt.*;

class Utility extends Property
{
   // public Player owner;
// public String name;
   private int price;
   private PropertyPanel utilityPanel;

   public Utility(int num, String n)
   {
      super(num, 150, n);
      utilityPanel = new PropertyPanel(this);
   }

   public void payRent(Player p)
   {
    int toll;
      if (owner.getDeeds().contains("Electric Company") &&
       owner.getDeeds().contains("Water Works"))
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
