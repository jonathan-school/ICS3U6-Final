import java.awt.event.*;

class StartListener implements ActionListener{
  private boolean startCondition;
  StartListener(){
    this.startCondition = false;
  }
  public void actionPerformed(ActionEvent e){
    this.startCondition = true;
    System.out.print (startCondition);
  }
  public boolean getStart(){
    return (startCondition);
  }
  public void setStart(boolean startCondition){
    this.startCondition = startCondition;
  }
}