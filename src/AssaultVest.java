/*
 * [AssaultVest.java];
 * 
 * The specific class for the assault vest, one of the more common armors the player can find. It is not
 * that effective at negating damage. It sets all the stats in the constructor and has code for graphics
 * 
 * Developed by: Will, Artem, Jonathan
 * 
 */ 

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;

class AssaultVest extends Armor {
  Image assaultVest;
  AssaultVest(int dbty){
    super(dbty); // pass durability to Armor Superclass
    this.setRarity(2); // set rarity
    this.setDefense(10); // set defense
    this.setName("Assault Vest"); // set name
  }
  public void drawItem(Graphics g, int x, int y, int width, int height, GamePanel gamePanel){
    assaultVest = Toolkit.getDefaultToolkit().getImage("../res/AssaultVest.png");
    if (this.getItemSelected()){
      g.setColor(new Color(255, 255, 255, 100)); 
      g.fillRect (x,y,width,height);
    }
    g.drawImage(assaultVest, x,y,width,height,gamePanel);
  }
}