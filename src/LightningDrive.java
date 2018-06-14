import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;
class LightningDrive extends Drive{
  private Image lightningDrive;
  LightningDrive(){
    this.setName("Lightning Drive");
  }
  public Item upgrade (Item chosenEquip){
    if (chosenEquip instanceof Armor){
      ((Armor)(chosenEquip)).setLightningDefense(((Armor)(chosenEquip)).getLightningDefense()+10);
    }else if (chosenEquip instanceof Weapon){
      ((Weapon)(chosenEquip)).setLightningChance(((Weapon)(chosenEquip)).getLightningChance()+10);
    }
    return (chosenEquip);
  }
  
  
  public void drawItem(Graphics g, int x, int y, int width, int height, GamePanel gamePanel){
    lightningDrive = Toolkit.getDefaultToolkit().getImage("../res/LightningDrive.png");
    if (this.getItemSelected()){
      g.setColor(new Color(255, 255, 255, 100)); 
      g.fillRect (x,y,width,height);
    }
    g.drawImage(lightningDrive, x,y,width,height,gamePanel);
  }
  public String getEffectDescription(){
    return ("This drive increases paralyze chance by 10% for weapons and grants paralyze immunity with armors");
  }
}