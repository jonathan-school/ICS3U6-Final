/* [DisplayGrid.java]
 * A Small program for Display a 2D String Array graphically
 * @author Mangat
 */

// Graphics Imports
import javax.swing.*;
import java.awt.*;


class DisplayGridC { 

  private JFrame frame;
  private int maxX,maxY, GridToScreenRatio;
  private int[][] world;
  private int[] fireLine = {0,0,0,0};
  private int[] firePoint = {0,0};
  private int[] firingPoint = {0,0};
  private int[] affectedTile = {-1,-1};
  private int cycles;
  
  DisplayGridC(int[][] w) { 
    this.world = w;
    this.cycles = -1;
    
    maxX = Toolkit.getDefaultToolkit().getScreenSize().width;
    maxY = Toolkit.getDefaultToolkit().getScreenSize().height;
    GridToScreenRatio = maxY / (world.length+1);  //ratio to fit in screen as square map
    
    System.out.println("Map size: "+world.length+" by "+world[0].length + "\nScreen size: "+ maxX +"x"+maxY+ " Ratio: " + GridToScreenRatio);
    
    this.frame = new JFrame("Test Chamber");
    
    GridAreaPanel worldPanel = new GridAreaPanel();
    
    frame.getContentPane().add(BorderLayout.CENTER, worldPanel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
    frame.setVisible(true);
  }
  
  
  public void refresh() { 
    frame.repaint();
  }
  
  public void setLine(int r1,int d1,int r2,int d2) {
    fireLine[0] = r1;
    fireLine[1] = d1;
    fireLine[2] = r2;
    fireLine[3] = d2;
  }
  
  public int returnLim() {
    return Toolkit.getDefaultToolkit().getScreenSize().height;
  }
    
  public void seeRatio() {
    System.out.println(GridToScreenRatio);
  }
  
  public int getRatio() {
    return GridToScreenRatio;
  }
  
  public void mark(int r, int d) {
    firePoint[0] = r;
    firePoint[1] = d;
  }
  
  public void setPos(int r, int d) {
    firingPoint[0] = r;
    firingPoint[1] = d;
  }
  
  public void setAffected(int r, int d) {
    affectedTile[0] = r;
    affectedTile[1] = d;
  }
  class GridAreaPanel extends JPanel {
    public void paintComponent(Graphics g) {        
      //super.repaint();
      //Color D_GREEN = new Color(57,118,40);
      
      setDoubleBuffered(true); 
      g.setColor(Color.BLACK);
      g.drawString(Integer.toString(GridToScreenRatio),GridToScreenRatio*12,GridToScreenRatio*1);
      g.drawOval(firePoint[0]-5,firePoint[1]-5,5,5);
      g.drawOval(firingPoint[0]-5,firingPoint[1]-5,10,10);
      
      for(int i = 0; i<world[0].length;i=i+1)
      { 
        for(int j = 0; j<world.length;j=j+1) 
        {           
          g.setColor(Color.BLACK);
          g.drawRect(j*GridToScreenRatio, i*GridToScreenRatio, GridToScreenRatio, GridToScreenRatio);
          
          if (i == affectedTile[0] && j == affectedTile[1]) {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(j*GridToScreenRatio, i*GridToScreenRatio, GridToScreenRatio, GridToScreenRatio);
          }
        }
      }
      
      g.setColor(Color.RED);
      g.drawLine(fireLine[0],fireLine[1],fireLine[2],fireLine[3]);
      g.drawLine(fireLine[0]-1,fireLine[1],fireLine[2]-1,fireLine[3]);
      g.drawLine(fireLine[0]+1,fireLine[1],fireLine[2]+1,fireLine[3]);
      
    }
  }//end of GridAreaPanel
  
} //end of DisplayGrid

