import javax.swing.*;
import java.awt.*;

class GoToJail extends Corner
{
   public GoToJail()
   {
      super(31,"GO TO JAIL");
   }
   private void landedOn(Player p)
   {
      p.goToJail();
   }
}