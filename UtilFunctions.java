import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class UtilFunctions
{
	public static void drawTitle(Graphics2D g, String title)
	{
		String [] parts = title.split("\\s");
		for (String part: parts)
		{
			
		}
	}
	public static void drawTile(Graphics2D g, int width, int height, int angle)
	{
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
	}

 
}