import java.awt.Graphics;

//Remove shots per turn
public abstract class RangedWeapon extends Weapon{
  private int shotsPerTurn;
  
  RangedWeapon(int dbty) {
    super(dbty);
  }
  
  public void setSPT(int spt){
    this.shotsPerTurn = spt;
  }
  
  public int returnSPT() {
    return this.shotsPerTurn;
  }
  abstract public void drawItem(Graphics g, int x, int y, int width, int height, GamePanel gamePanel);
}