class Button {
  
    float positionX;
    float positionY;
    float sizeX;
    float sizeY;
    String label;
    boolean wasPressed= false;
    boolean buttonDown =false;
    boolean isActive= true;
    int cR=0;
    int cG=0;
    int cB=0;
    public Button(float PosX,float PosY,float SizeX,float SizeY,String Label){
        positionX= PosX-SizeX/2;
        positionY= PosY-SizeY/2;//vieleicht +
        this.sizeX = SizeX;
        this.sizeY = SizeY;
        this.label = Label;
    }
    
    public void setColor(int rot, int grun, int blau){
      cR=rot;
      cG=grun;
      cB=blau;  
    }
    
    public boolean isButtonPressed(){
      if(mouseX>positionX && mouseX <(positionX+sizeX) && mouseY>positionY && mouseY< positionY+sizeY && isActive){
         if(mousePressed){
             buttonDown =true;
           }
         if(buttonDown && !mousePressed){
             buttonDown= false;
             isActive =false;
             return true;
           }
         else{
             return false;
         }
       }
       else{return false;}
    }
    
    
    
    
    public void drawButton(){
      stroke(cR,cG,cB);
      strokeWeight(5);
      fill(128);
      rect(positionX,positionY,sizeX,sizeY);
      textAlign(CENTER,CENTER);
      fill(cR,cG,cB);
      text(this.label,positionX+(sizeX/2),positionY+(sizeY/2));
      stroke(0,0,0);
      strokeWeight(1);
      fill(255);
    }
    
}