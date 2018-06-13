import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;
import java.awt.Color;

public class GameSaver {
  private int fileCode;
  
  GameSaver() {
    Scanner calibrator = new Scanner(("SaveCalibrator.txt"));
    this.fileCode = calibrator.nextInt();
  }
  
  public void saveGame(char[][] charMap, Entity[][] entityMap, Item[][] itemMap, Item[][] inventory) throws Exception{
    
    File saveFile = new File("concordia_savfile_" + fileCode + ".txt");
    
    PrintWriter writer = new PrintWriter(new File("concordia_savfile_" + fileCode + ".txt"));
    PrintWriter calibrationUpdater = new PrintWriter( new File("SaveCalibrator"));
    calibrationUpdater.println(fileCode + 1);
    
    writer.println("%map save:");
    writer.println(charMap.length + " " + charMap[0].length);
    for (int i = 0; i < charMap.length; i++) {
      for (int j = 0; j < charMap[0].length; j++) {
        writer.print(charMap[i][j]);
        //writer.print(",");     
      }
      writer.println("");
    }
    
    int type;
    int recHealth;
    boolean[] states = new boolean[3];
    int xCoord;
    int yCoord;
    
    writer.println("%entity save:");
    writer.println(entityMap.length + " " + entityMap[0].length);
    
    for (int i = 0; i < entityMap.length; i++) {
      for (int j = 0; j < entityMap[0].length; j++) {
        if (entityMap[i][j] instanceof Entity) {
          type = -1;
          if (entityMap[i][j] instanceof Enemy) {
            type = 1;            
          } else if (entityMap[i][j] instanceof Character) {
            type = 0;
          } 
          recHealth = entityMap[i][j].getHealth();
          states[0] = entityMap[i][j].getFlame();
          states[1] = entityMap[i][j].getFreeze();
          states[2] = entityMap[i][j].getLightning();
          yCoord = i;
          xCoord = j;                                    
          
          writer.print(type + ",");
          writer.print(recHealth + ",");
          writer.print(states[0] + "," + states[1] + "," + states[2]);
          writer.print(xCoord + "," + yCoord);   
          writer.println("");
        }
      } 
    }
    
    
    writer.println("%item mapping save:");
    writer.println(itemMap.length + " " + itemMap[0].length);
    
    String itemType;
    int durability;
    
    for (int i = 0; i < entityMap.length; i++) {
      for (int j = 0; j < entityMap[0].length; j++) {
        if (itemMap[i][j] instanceof Item) {
          itemType = itemMap[i][j].getName();
          
          if (itemMap[i][j] instanceof Equipment) { 
            durability = ((Equipment)itemMap[i][j]).getDurability();
          } else {
            durability = -1;
          }
          
          writer.print(itemType + ",");
          writer.print(durability + ",");
          writer.print(i + " " + j);
          writer.println("");
        }
      }
    }        
    
    
    writer.println("%inventory save:");
    for (int i = 0; i < inventory.length; i++) {
      for (int j = 0; j < inventory[0].length; j++) {
        if (itemMap[i][j] instanceof Item) {
          itemType = itemMap[i][j].getName();
          
          if (itemMap[i][j] instanceof Equipment) { 
            durability = ((Equipment)itemMap[i][j]).getDurability();
          } else {
            durability = -1;
          }
          
          writer.print(itemType + ",");
          writer.print(durability + ",");
          writer.print(i + " " + j);
          writer.println("");
        }
      }
    }        
  }
  
  
  public LoadFile loadGame(String fileName) throws Exception {
    File loadSource = new File(fileName + ".txt");
    Scanner reader = new Scanner(loadSource);
    String lineReader;
    
    char[][] loadedCharMap;
    Entity[][] loadedEntityMap;
    Item[][] loadedItemMap;
    Item[][] loadedInventory;
    
    int sizeX, sizeY;
    
    lineReader = reader.nextLine();    
    sizeY = reader.nextInt();
    sizeX = reader.nextInt();
    lineReader = reader.nextLine();    
    lineReader = reader.nextLine();  
    
    loadedCharMap = new char[sizeY][sizeX];    
    
    for (int i = 0; i < sizeY; i++) {
      for (int j = 0; j < lineReader.length(); j++) {
        loadedCharMap[i][j] = lineReader.charAt(j);
      }
      lineReader = reader.nextLine(); 
    }
    
    lineReader = reader.nextLine();    
    sizeY = reader.nextInt();
    sizeX = reader.nextInt();
    lineReader = reader.nextLine();    
    lineReader = reader.nextLine();  
    
    loadedEntityMap = new Entity[sizeY][sizeX];
    
    int type;
    int recHealth;
    int xCoord;
    int yCoord;
    
    while (lineReader.charAt(0) != '%') {
      type = reader.nextInt();
      recHealth = reader.nextInt();
      xCoord = reader.nextInt();
      yCoord = reader.nextInt();
      
      if (type == 1) {
        Brute loadedBrute = new Brute(recHealth,100,100,1,false,false,false,Color.PINK,false);
        loadedEntityMap[yCoord][xCoord] = loadedBrute;
      } else if (type == 0){
        Character loadedCharacter = new Character(recHealth,100,100,1,false,false,false,Color.BLUE);
      }
      
      lineReader = reader.nextLine();
    }
    
    lineReader = reader.nextLine();    
    sizeY = reader.nextInt();
    sizeX = reader.nextInt();
    lineReader = reader.nextLine();    
    lineReader = reader.nextLine();
    
    loadedItemMap = new Item[sizeY][sizeX];
    String loadedItemType; 
    int loadedDurability;
    
    while (lineReader.charAt(0) != '%') {
      int nameCapture = 0;
      
      do {
        nameCapture++;
      } while(lineReader.charAt(nameCapture) != ',');
      
      loadedItemType = lineReader.substring(0,nameCapture);
      loadedDurability = reader.nextInt();
      
      xCoord = reader.nextInt();
      yCoord = reader.nextInt();
      
      
      if (loadedItemType.equals("Gamma Hammer")) {
        GammaHammer loadedItem = new GammaHammer(loadedDurability);
        loadedItemMap[yCoord][xCoord] = loadedItem;
      } else {}
                              
      lineReader = reader.nextLine();
    }
    
    lineReader = reader.nextLine();    
    lineReader = reader.nextLine();    

   
    loadedInventory = new Item[4][6];
    loadedItemType = "";
    loadedDurability = 0;
    
    do {
      int nameCapture = 0;
      
      do {
        nameCapture++;
      } while(lineReader.charAt(nameCapture) != ',');
      
      loadedItemType = lineReader.substring(0,nameCapture);
      loadedDurability = reader.nextInt();
      
      xCoord = reader.nextInt();
      yCoord = reader.nextInt();
      
      
      if (loadedItemType.equals("Gamma Hammer")) {
        GammaHammer loadedItem = new GammaHammer(loadedDurability);
        loadedInventory[yCoord][xCoord] = loadedItem;
      } else {}
                              
      lineReader = reader.nextLine();
    } while (reader.hasNext());
    
    LoadFile loadFile = new LoadFile(loadedCharMap,loadedEntityMap,loadedItemMap,loadedInventory);
    
    return loadFile;
    
  }
}