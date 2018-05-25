import javax.swing.*;
import java.awt.*;

class MenuBGPanel extends JLayeredPane{
  private int xVal;
  private int yVal;
  MenuBGPanel(int xVal, int yVal){
    setFocusable(true);
    this.xVal = xVal;
    this.yVal = yVal;
    Dimension panelSize= new Dimension (xVal, yVal);
    this.setPreferredSize(panelSize);
  }
  @Override
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Image menuBg = Toolkit.getDefaultToolkit().getImage("../res/bgPlaceholder.jpg");
    g.drawImage(menuBg,0,0,xVal,yVal,this);
  }
    public void refresh(){
    this.repaint();
  }
}