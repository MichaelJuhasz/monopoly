import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/** 
 *  CardTile, a subclass of Tile models and controls the Chance and Community
 *  Chest tiles.  As these tiles' functionality has not been implemented, 
 *  this class doesn't do much.
 *
 * @author:  Michael Juhasz
 * @version: Last modified 4/28/14 
 */
class CardTile extends Tile
{
   private PropertyPanel cardPanel;

   /**
    *  Constructor is passed in a name and a number and in turn 
    *  instantiates a PropertyPanel to which it passes itself.
    *
    *  @param n  the Tile's name
    *  @param num the Tile's number
    */
   public CardTile(String n, int num)
   {
      name = n;
      number = num;
      cardPanel = new PropertyPanel(this);
   }

   /**
    *  Called by inherited landedOn method.  Updates corresponding Panel.
    */
   public void updateGraphics()
   {
      cardPanel.update(guests);
   }

   /**
    *  Used in setUpBoard to grab the attached Panel
    *
    * @return cardPanel   the attached PropertyPanel 
    */
   public JPanel getPanel()
   {
      return cardPanel;
   }
}