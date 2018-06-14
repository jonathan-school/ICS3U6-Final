/////////////////////
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;

/////////////////////
class Wrench extends MeleeWeapon{
  Image wrench;
  Wrench(int dbty) {
    super(dbty);
    this.setRarity(1);
    this.setDamage(500);    
    this.setName("Wrench");
  }
  
  /**
   *drawItem
   *Draws the wrench
   *@param: The Graphics g, the int x, the int y, the int width, the int height, and the GamePanel gamePanel
   *@return: 
   */
  public void drawItem(Graphics g, int x, int y, int width, int height, GamePanel gamePanel) {
    wrench = Toolkit.getDefaultToolkit().getImage("../res/Wrench.png");
    if (this.getItemSelected()){
      g.setColor(new Color(255, 255, 255, 100)); 
      g.fillRect (x,y,width,height);
    }
    g.drawImage(wrench, x,y,width,height,gamePanel);
  }
}
