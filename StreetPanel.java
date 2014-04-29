import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

class StreetPanel extends JPanel
{
   public String name;
   private Color color;
   private Street street;
   private int width, height, angle, houses;
   private ArrayList<Player> guests = new ArrayList<Player>();

   public StreetPanel(Street s)
   {
      street = s;
      color = s.color;
      name = s.name;
      width = 50;
      height = 100;
      houses = 0;
      angle = ((s.getNumber()-1) / 10) * 90;
      setPreferredSize(new Dimension(50,100));
   }

   public void update(ArrayList<Player> g, int h)
   {
      houses = h;
      guests = g;
      super.repaint();
   }

   public void paintComponent (Graphics g2)
   {
      //super.paintComponent(g2);
      Graphics2D g = (Graphics2D) g2;
      g.setRenderingHint ( RenderingHints.KEY_ANTIALIASING,
                           RenderingHints.VALUE_ANTIALIAS_ON);
      
      if (angle == 90) g.translate(height, 0);
      else if (angle == 180) g.translate(width, height);
      else if (angle == 270) g.translate(0, width);

      // Rotate tile according to position on the board (i.e. its number)
      g.rotate(Math.toRadians(angle));

      // Draw white box
      g.setColor(Color.WHITE);
      g.fillRect(0, 0, width, height);

      // Draw colored box
      g.setColor(color);
      g.fillRect(0,0,width,height/4);


      // Draw border around tile
      g.setColor(Color.BLACK);
      g.setStroke(new BasicStroke(3));
      g.drawRect(0,0,width,height);
      g.drawLine(0, height/4, width, height/4);

      // Write name of tile
      g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 8));
	  String [] parts = name.split("\\s");
	  for (int i = 0; i < parts.length; i++)
	  {
		 int pY = height/4 + 20 + i * g.getFontMetrics().getHeight() + g.getFontMetrics().getLeading();
		 g.drawString(parts[i], 5, pY);
      }

      // Draw houses or hotel
      g.setStroke(new BasicStroke(1));
      int houseWidth = (width - 10) / 4;
      if (houses > 0 && houses < 5)
      {
         for (int i = 0; i < houses; i++)
         {
            g.setColor(Color.BLACK);
            g.drawRect((5 + (i * houseWidth)), 0, houseWidth, (height/4)- 5);
            g.setColor(Color.GREEN);
            g.fillRect((5 + (i * houseWidth)), 0, houseWidth, (height/4)- 5);
         }
      }
      else if (houses == 5)
      {
         g.setColor(Color.BLACK);
         g.drawRect((5 + houseWidth), 0, houseWidth * 2, (height/4)- 5);
         g.setColor(Color.RED);
         g.fillRect((5 + houseWidth), 0, houseWidth * 2, (height/4)- 5);
      }

      // Draw any player tokens that are sitting on this tile
      if (!guests.isEmpty())
      {
         for (int i = 0; i < guests.size(); i++)
         {
            ImageIcon icon = guests.get(i).getIcon();
            g.drawImage(icon.getImage(), 5, height/3 + (i * 20), null);
         }
      }

      g.rotate(-(Math.toRadians(angle)));
   }

}