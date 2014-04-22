import javax.swing.*;
import java.awt.*;

class Jail extends Corner
{
   private JailPanel jailPanel;
   public Jail()
   {
      super(11,"JAIL");
      jailPanel = new JailPanel(this);
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