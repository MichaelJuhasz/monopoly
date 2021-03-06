import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

class CornerPanel extends JPanel
{
   private int width, height, angle;
   public String name;
   private ArrayList<Player> guests = new ArrayList<Player>();

   public CornerPanel(Corner c)
   {
      width = 100;
      height = 100;
      name = c.name;
      angle = ((c.getNumber()-1) / 10) * 90;
      setPreferredSize(new Dimension(100,100));
   }

   public void update(ArrayList<Player> g)
   {
      guests = g;
      super.repaint();
   }

   public void paintComponent (Graphics g2)
   {
   //   super.paintComponent(g2);
      Graphics2D g = (Graphics2D) g2;
      g.setRenderingHint ( RenderingHints.KEY_ANTIALIASING,
                           RenderingHints.VALUE_ANTIALIAS_ON);

      if (angle == 90) g.translate(height, 0);
      else if (angle == 180) g.translate(width, height);
      else if (angle == 270) g.translate(0, width);
      g.rotate(Math.toRadians(angle));

      // Draw white box
      g.setColor(Color.WHITE);
      g.fillRect(0, 0, width, height);

      // Draw border around tile
      g.setColor(Color.BLACK);
      g.setStroke(new BasicStroke(3));
      g.drawRect(0,0,width,height);
  //    UtilFunctions.drawBox(g, width, height, angle);

      // Draw any player tokens that are sitting on this tile
      if (!guests.isEmpty())
      {
         for (int i = 0; i < guests.size(); i++)
         {
            ImageIcon icon = guests.get(i).getIcon();
            g.drawImage(icon.getImage(), 5, height/4 + (i * 20), null);
         }
      }

      // Write name of tile
      g.rotate(Math.toRadians(-45));
      g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
	  String [] parts = name.split("\\s");
	  for (int i = 0; i < parts.length; i++)
	  {
		 int pY = height/4+20 + i * g.getFontMetrics().getHeight() + g.getFontMetrics().getLeading();
		 g.drawString(parts[i], -15, pY);
      }

      // g.rotate(-(Math.toRadians(angle)));
   }

}