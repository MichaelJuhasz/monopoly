import javax.swing.*;
import java.awt.*;

class Jail extends Corner
{
   private CornerPanel jailPanel;
   public Jail()
   {
      super(11,"JAIL");
      jailPanel = new CornerPanel(this);
   }

   public void landedOn()
   {}

   private void updateGraphics()
   {
      jailPanel.update(guests);
   }

   public JPanel getPanel()
   {
      return jailPanel;
   }
}