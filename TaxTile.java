import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class TaxTile extends Tile
{
   public enum TaxType
   {
      INCOME, LUXURY;
   }

   private TaxType type;
//   private String name; 
//   private int number;
   private PropertyPanel taxPanel;

   public TaxTile(int num, String n, TaxType tt)
   {
      number = num;
      name = n;
      type = tt;
      taxPanel = new PropertyPanel(this);
   }

   public void landedOn(Player p)
   {
      guests.add(p);
      updateGraphics();

      if (type == TaxType.LUXURY)
      {
         p.payment(-75);
      }
      else 
      {
        String [] choices = {"10%", "$200"};
	     int response = JOptionPane.showOptionDialog(
		               null,
		               "Pay 10% or $200",
		               "Income Tax",
		               JOptionPane.YES_NO_OPTION,
		               JOptionPane.PLAIN_MESSAGE,
		               null,
		               choices,
		               choices[0]
		            );
         if (response == 1) p.payment(-200);
         else 
         {
         	int worth = p.getTotalWorth();
         	int tax = (int)(worth * 0.1);
         	p.payment(tax * -1);
         	if (tax <= 200) JOptionPane.showMessageDialog(null, "Your total worth was $"+worth+" and you paid $"+tax+".\nGood move.", "Income Tax", JOptionPane.INFORMATION_MESSAGE);
            else JOptionPane.showMessageDialog(null, "Your total worth was $"+worth+" and you paid $"+tax+".\nPoor choice.", "Income Tax", JOptionPane.INFORMATION_MESSAGE);    
         }
      }
   }

   public void leave(Player p)
   {
      guests.remove(p);
      updateGraphics();
   }

   private void updateGraphics()
   {
      taxPanel.update(guests);
   }

   public JPanel getPanel()
   {
      return taxPanel;
   }
}