import javax.swing.*;
import java.awt.*;

class GoToJail extends Corner
{
   private GoToJailPanel goToJailPanel;
   public GoToJail()
   {
      super(31,"GO TO JAIL");
      goToJailPanel = new GoToJailPanel();
   }
   private void landedOn(Player p)
   {
      guests.add(p);
      updateGraphics();
      JOptionPane.showMessageDialog(null,"GO TO JAIL!","GO TO JAIL!", JOptionPane.ERROR_MESSAGE);
      p.goToJail();
   }

   private void updateGraphics()
   {
      goToJailPanel.update(guests);
   }
}