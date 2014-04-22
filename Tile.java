import javax.swing.JPanel;

abstract class Tile 
{
   public int number;
   
   abstract void landedOn(Player p);

   public int getNumber()
   {
      return number;
   }

   abstract public JPanel getPanel();
}