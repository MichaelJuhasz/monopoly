import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Monopoly 
{
   public static void main (String[] args)
   {
   	  Color purple = new Color(195,67,238);
   	  Color lightBlue = new Color(165,225,232);
   	  Color pink = new Color(230,0,176);
   	  Color oragne = new Color(255,150,31);
   	  Color red = new Color (255,31,31);
   	  Color yellow = new Color (255,236,31);
   	  Color green = new Color(6,125,8);
   	  Color darkBlue = new Color(6,48,125);

      ArrayList<Tile> tileList = new ArrayList<Tile>();
      // Bottom of the baord
      tileList.add(Go tile1 = new Go());
      tileList.add(Street tile2 = new Street(2,2,10,30,90,160,250,60,50,"MEDDITERRANEAN AVENUE",purple));
      tileList.add(CardTile tile3 = new CardTile("COMMUNITY CHEST",3));
      tileList.add(Street tile4 = new Street(4,4,20,60,180,320,450,60,50,"BALTIC AVENUE", purple));
      tileList.add(TaxTile tile5 = new TaxTile(5,"INCOME TAX",TaxTile.TaxType.INCOME));
      tileList.add(RailRoad tile6 = new RailRoad(6,"READING RAILROAD"));
      tileList.add(Street tile7 = new Street(7,6,30,90,270,400,550,100,50,"ORIENTAL AVENUE",lightBlue));
      tileList.add(CardTile tile8 = new CardTile("CHANCE",8));
      tileList.add(Street tile9 = new Street(9,6,30,90,270,400,550,100,50,"VERMONT AVENUE",lightBlue));
      tileList.add(Street tile10 = new Street(10,8,40,100,300,450,600,120,50,"CONNECTICUT AVENUE",lightBlue));
      
      // Left side of the board 
      tileList.add(Jail tile11 = new Jail());
      tileList.add(Street tile12 = new Street(12,10,50,150,450,625,750,140,100,"ST. CHARLES PLACE",pink));
      tileList.add(Utility tile13 = new Utility(13,"ELECTRITC COMPANY"));
      tileList.add(Street tile14 = new Street(14,10,50,150,450,625,750,140,100,"STATES AVENUE",pink));
      tileList.add(Street tile15 = new Street(15,12,60,180,500,700,900,160,100,"VIRGINIA AVENUE",pink));
      tileList.add(RailRoad tile16 = new RailRoad(16,"PENNSYLVANIA RAILROAD"));
      tileList.add(Street tile17 = new Street(17,14,70,200,550,750,950,180,100,"ST. JAMES PLACE",orange));
      tileList.add(CardTile tile18 = new CardTile("COMMUNITY CHEST",18));
      tileList.add(Street tile19 = new Street(19,14,70,200,550,750,950,180,100,"TENNESSEE AVENUE", orange));
      tileList.add(Street tile20 = new Street(20,16,80,220,600,800,1000,200,100,"NEW YORK AVENUE",orange));

      // Top of the board
      tileList.add(FreeParking tile21 = new FreeParking());
      tileList.add(Street tile22 = new Street(22,18,90,250,700,875,1050,220,150,"KENTUCKY AVENUE",red));
      tileList.add(CardTile tile23 = new CardTile(23,"CHANCE"));
      tileList.add(Street tile24 = new Street(24,18,90,250,700,875,1050,220,150,"INDIANA AVENUE",red));
      tileList.add(Street tile25 = new Street(25,20,100,300,750,935,1100,240,150,"ILLINOIS AVENUE",red));
      tileList.add(RailRoad tile26 = new RailRoad(26,"B \& O RAILROAD"));
      tileList.add(Street tile27 = new Street(27,22,110,330,800,975,1150,260,150,"ATLANTIC AVENUE",yellow));
      tileList.add(Street tile28 = new Street(28,22,110,330,800,975,1150,260,150,"VENTNOR AVENUE",yellow));
      tileList.add(Utility tile29 = new Utility(29,"WATER WORKS"));
      tileList.add(Street tile30 = new Street(30,24,120,360,850,1025,1200,280,150,"MARVIN GARDENS",yellow));

      // Right side of board
      tileList.add(GoToJail tile31 = new GoToJail());
      tileList.add(Street tile32 = new Street(32,26,130,390,900,1100,1275,300,200,"PACIFIC AVENUE",green));
      tileList.add(Street tile33 = new Street(33,26,130,390,900,1100,1275,300,200,"NORTH CAROLINA AVENUE",green));
      tileList.add(CardTile tile34 = new CardTile("COMMUNITY CHEST",34));
      tileList.add(Street tile35 = new Street(35,28,140,450,1000,1200,1400,320,200,"PENNSYLVANIA AVENUE",green));
      tileList.add(RailRoad tile36 = new RailRoad(36,"SHORT LINE"));
      tileList.add(CardTile tuke37 = new CardTile(37,"CHANCE"));
      tileList.add(Street tile38 = new Street(38,35,175,500,1100,1300,1500,350,200,"PARK PLACE",darkBlue));
      tileList.add(TaxTile tile39 = new TaxTile(39,"LUXORY TAX",TaxTile.TaxType.LUXORY));
      tileList.add(Street tile40 = new Street(40,50,200,600,1400,1700,2000,400,200,"BOARDWALK",darkBlue));
   }
}