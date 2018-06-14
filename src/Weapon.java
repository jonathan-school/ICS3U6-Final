import java.awt.Graphics;
public abstract class Weapon extends Equipment {
  private int damage;
  private int freezeChance;
  private int flameChance;
  private int lightningChance;
  private boolean weaponSelect;
  
  Weapon(int dbty) {
    super(dbty);
  }
  
  public int getDamage() {
    return this.damage;
  }
  
  public void setDamage(int dm) {
    this.damage = dm;
  }
  public int getFreezeChance(){
    return (freezeChance);
  }
  public void setFreezeChance(int freezeChance){
    if (freezeChance>100){
      this.freezeChance=100;
    }else{
      this.freezeChance = freezeChance;
    }
  }
  public int getFlameChance(){
    return (flameChance);
  }
  public void setFlameChance(int flameChance){
    if (flameChance>100){
      this.flameChance=100;
    }else{
      this.flameChance = flameChance;
    }
  }
  public int getLightningChance(){
    return (lightningChance);
  }
  public void setLightningChance(int lightningChance){
    if (lightningChance>100){
      this.lightningChance=100;
    }else{
      this.lightningChance = lightningChance;
    }
  }
  public boolean getWeaponSelect(){
    return (weaponSelect);
  }
  public void setWeaponSelect(boolean weaponSelect){
    this.weaponSelect = weaponSelect;
  }
  abstract public void drawItem(Graphics g, int x, int y, int width, int height, GamePanel gamePanel);
}