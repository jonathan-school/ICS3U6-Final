import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

class HallwayTile extends WalkableTile{
  Image hallway;
  HallwayTile(Color minimapColor){
    super(minimapColor);
  }
  public void drawTile(Graphics g, int x, int y, int width, int height, GamePanel gamePanel, boolean focus){
    if(focus){
      hallway = Toolkit.getDefaultToolkit().getImage("../res/FloorTile.png");
    } else{
      hallway = Toolkit.getDefaultToolkit().getImage("../res/FloorTileDark.png");
    }
    g.drawImage(hallway, x,y,width,height,gamePanel);
  }
}