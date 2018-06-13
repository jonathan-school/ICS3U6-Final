import java.awt.Color;
import java.io.File;
import java.io.PrintWriter;
import javax.sound.sampled.*; //Wildcard

///Make it so that you click again to cancel the upgrade
class Main{
  //These variables are required to be able to set starting position of the character
  public static void main (String[] args) throws Exception{
    //Finds memory usage before program starts
    int mb = 1024*1024;
    Runtime runtime = Runtime.getRuntime();
    double maxMem = runtime.maxMemory();
    double usedMem;
    //Music vars
    //File mappo = new File ("map.txt");
    //PrintWriter output = new PrintWriter (mappo);
    /*
    File audioFile = new File("../res/spacebackround.wav");
    AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
    DataLine.Info info = new DataLine.Info(Clip.class, audioStream.getFormat());
    Clip clip = (Clip) AudioSystem.getLine(info);
    */
   
    //Creates the map generator object
    MapGen2_8 gen = new MapGen2_8();
    //A tile map will be created based off the tile map
    char[][] charMap = gen.charMap(gen.generateMap(12,12));
    //char[][] charMap = gen.createBossRoom();
    //Converts the map into a tile map
    Color POO = new Color(168,107,23);
    
    int playerStartingX=0, playerStartingY =0;
    Tile [][] map = new Tile [charMap.length][charMap[0].length];

    for (int i = 0; i < charMap.length; i++){
      for(int j = 0; j < charMap[0].length; j++){
        if (charMap[i][j] == 'J'){ //
          map[i][j]= new WallTile(POO, "../res/WallTile", "garbage");
        } else if (charMap[i][j] == 'C'|| charMap[i][j] == 'S'){
          map[i][j]= new ChestTile(Color.LIGHT_GRAY, "../res/WallSafe", "wall safe"); //Wall Safe
        } else if (charMap[i][j] == 'X'){//Hallway floor
          map[i][j]= new HallwayTile(Color.WHITE, "hallway floor");
        } else if (charMap[i][j] == 'R' || charMap[i][j] == '!' || charMap[i][j] == '&' || charMap[i][j] == '%' || charMap[i][j] == '=') { //Room floor
          map[i][j]= new FloorTile(Color.GREEN, "../res/FloorTile", "room floor");
        } else if (charMap[i][j] == 'D') { //Door
          map[i][j]= new DoorTile(Color.RED, "door");
        } else if (charMap[i][j] == '~') {
          map[i][j]= new WallTile(Color.DARK_GRAY,"../res/WallTile", "hallway wall"); //Hallway wall
        } else if (charMap[i][j] == '|') {
          map[i][j]= new WallTile(Color.LIGHT_GRAY, "../res/WallTile", "room wall"); //Room wall
        } else if (charMap[i][j] == 'A') {
          map[i][j]= new HallwayTile(Color.ORANGE, "airlock"); //Airlock
        }
        //Biological Breakout
        else if (charMap[i][j] == 'B'){//BrokenSpecimen Container
          map[i][j]= new WallTile(Color.GREEN.darker(), "../res/SpecimenBroken", "broken specimen");
        } else if (charMap[i][j] == 'U'){//Specimen Container
          map[i][j]= new WallTile(Color.GREEN.darker(), "../res/Specimen", "specimen");
        } else if (charMap[i][j] == 'O'){//Arm
          map[i][j]= new FloorTile(Color.GREEN, "../res/Arm", "arm");
        } else if (charMap[i][j] == 'H'){//Blood
          map[i][j]= new FloorTile(Color.GREEN, "../res/BloodFloor", "blood");
        }
        //Lab
        else if (charMap[i][j] == 't'){ //Lab table
          map[i][j]= new WallTile(Color.LIGHT_GRAY, "../res/Lab", "lab");
        } else if (charMap[i][j] == 'm' ){ //Lab table w/ chemicals
          map[i][j]= new WallTile(Color.LIGHT_GRAY, "../res/LabTube", "lab");
        } else if (charMap[i][j] == 's'){ // Lab table w/ splatter
          map[i][j]= new WallTile(Color.LIGHT_GRAY, "../res/LabSpill", "lab");
        } else if (charMap[i][j] == 'p'){//Lab table w/ computer
          map[i][j]= new WallTile(Color.LIGHT_GRAY, "../res/LabComputer", "lab");
        } else if (charMap[i][j] == 'n'){//Lab table w/ notes
          map[i][j]= new WallTile(Color.LIGHT_GRAY, "../res/LabNotes", "lab");
        }
        //Reactor
        else if(charMap[i][j] == 'r'){//Reactor Core
          map[i][j]= new WallTile(Color.LIGHT_GRAY, "../res/ReactorCore", "reactor");
        }else if(charMap[i][j] == 'h'){ //Horizontal Walkway
          map[i][j]= new FloorTile(Color.GREEN, "../res/Walkway", 0.5 * Math.PI, "walkway");
        }else if(charMap[i][j] == 'v'){ //Vertical Walkway
          map[i][j]= new FloorTile(Color.GREEN, "../res/Walkway", "walkway");
        } else if (charMap[i][j] == 'a') { // reactor corner 1
          map[i][j]= new FloorTile(Color.GREEN, "../res/WalkwayEdge", 0.5 * Math.PI,"reactor");
        } else if (charMap[i][j] == 'b') { // reactor corner 2
          map[i][j]= new FloorTile(Color.GREEN, "../res/WalkwayEdge", 1.0 * Math.PI, "reactor");
        } else if (charMap[i][j] == 'c') { // reactor corner 3
          map[i][j]= new FloorTile(Color.GREEN, "../res/WalkwayEdge", 1.5 * Math.PI, "reactor");
        } else if (charMap[i][j] == 'd') { // reactor corner 4
          map[i][j]= new FloorTile(Color.GREEN, "../res/WalkwayEdge", 0.0 * Math.PI, "reactor");
        }
        // Crew's Quarters
        else if (charMap[i][j] == 'Q') {
          map[i][j] = new FloorTile(Color.GREEN, "../res/CrewFloor", "crew");
        } else if (charMap[i][j] == '1') {
          map[i][j] = new WallTile(Color.YELLOW, "../res/CrewBedTop", "crew");
        } else if (charMap[i][j] == '2') {
          map[i][j] = new WallTile(Color.YELLOW, "../res/CrewBedBottom", "crew");
        } else if (charMap[i][j] == 'I') {
          map[i][j] = new FloorTile(Color.GREEN, "../res/CrewFloorShirt", "crew");
        } else if (charMap[i][j] == 'P') {
          map[i][j] = new FloorTile(Color.GREEN, "../res/CrewFloorChess", "crew");
        } else if (charMap[i][j] == '%'){
          map[i][j] = new FloorTile(Color.RED, "../res/CrewFloorChess", "crew");;
        }
        
        //Key points
        else if (charMap[i][j] == '@'){
          map[i][j]= new FloorTile(Color.GREEN, "../res/FloorTile", "spawn"); //Spawn
          playerStartingX = j;
          playerStartingY = i;
        } else if (charMap[i][j] == '#'){ //Stair down
          map[i][j]= new WallTile(Color.BLACK, "../res/StairDown", "stair down");
        } else{
          map[i][j]= null;
        }
      }
    }
    
   //   output.close();
      //Plays music
    /*
    try {
      clip.open(audioStream);
      clip.start();
      clip.loop(Clip.LOOP_CONTINUOUSLY);
    }catch (Exception e) {
      e.printStackTrace();
    }
    */
    //The display frame is created, and the player x and y are found
    Display disp = new Display ();
    //The Clock time keeps track of the fps
    Clock time = new Clock ();
    disp.setMap(map);
    disp.setPlayerLocation (playerStartingX, playerStartingY);
    while (true){
      time.setTime();  
      if (time.getFramePassed()){
        //Finds memory usage after code execution    
        usedMem = runtime.totalMemory() - runtime.freeMemory();
        disp.setMem(maxMem/mb, usedMem/mb);
        disp.getListen();
        disp.refreshAll();
      }
      if (time.getSecondPassed()){
        disp.setFps(time.getFrame());
        time.setFrame(0);
      }
      /*
      if(clip.getMicrosecondLength() == clip.getMicrosecondPosition()){
        clip.close();
      }
      */
    }
  }
}