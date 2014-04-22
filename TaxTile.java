import javax.swing.*;
import java.awt.*;

class TaxTile extends Tile
{
   public enum TaxType
   {
      INCOME, LUXURY;
   }

   private TaxType type;
   private String name; 
   private int number;
   private PropertyPanel taxPanel;
   private ArrayList<Player> guests = new ArrayList<Player>();

   public TaxTile(int num, String n, TaxType tt)
   {
      number = num;
      name = n;
      type = tt;
      taxPanel = new PropertyPanel(this);
   }

   private void landedOn(Player p)
   {
      guests.add(p);
      updateGraphics();

      if (type == LUXURY)
      {
         p.payment(-75);
      }
      else 
      {
	     int response = JOptionPane.showOptionDialog(
		               null,
		               "Pay 10% or $200",
		               "Income Tax",
		               JOptionPane.YES_NO_OPTION,
		               JOptionPane.PLAIN_MESSAGE,
		               null,
		               {"10%", "$200"},
		               "10%"
		            );
         if (response == 1) p.payment(-200);
         else 
         {
         	int worth = p.getTotalWorth();
         	int tax = worth * 0.1;
         	p.payment(tax * -1);
         	if (tax <= 200) JOptionPane.showMessageDialog("Your total worth was $"+worth+" and you paid $"+tax+".\nGood move.", JOptionPane.INFORMATION_MESSAGE);
            else JOptionPane.showMessageDialog("Your total worth was $"+worth+" and you paid $"+tax=".\nPoor choice.", JOptionPane.INFORMATION_MESSAGE);    
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