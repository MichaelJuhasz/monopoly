import javax.swing.*;
import java.awt.*;

class GoToJail extends Corner
{
   private CornerPanel goToJailPanel;
   
   public GoToJail()
   {
      super(31,"GO TO JAIL");
      goToJailPanel = new CornerPanel(this);
   }
   
   public void landedOn(Player p)
   {
      guests.add(p);
      updateGraphics();
      JOptionPane.showMessageDialog(null,"GO TO JAIL!","GO TO JAIL!", JOptionPane.ERROR_MESSAGE);
      p.goToJail();
   }

   public void updateGraphics()
   {
      goToJailPanel.update(guests);
   }

   public JPanel getPanel()
   {
      return goToJailPanel;
   }
}