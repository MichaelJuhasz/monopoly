import javax.swing.*;
import java.awt.*;

class Go extends Corner
{
   public Go()
   {
      super(1,"Go");
   }

   private void landedOn(Player p)
   {
      p.payment(200);
   }
}