class Controls{
  
    public Button start;
    public Button reset;
    public Button raketestart; 
    public HScrollbar raketeAngelBar;

    public Controls(){
    
      initButtons();
    }


    public void initButtons(){
      raketestart = new Button(500f, 520f, 50f, 30f, "Feuer");
      start = new Button(400f, 520f, 50f, 30f, "Start");
      reset = new Button(400f, 520f, 50f, 30f, "Reset");
      raketeAngelBar = new HScrollbar(100, 550, 600, 20, 180, 1);
      start.setColor(0, 255, 0);
      reset.setColor(255, 0, 0);
      raketestart.setColor(0, 0, 255);
    }

  public void update(){
    raketeAngelBar.update();
    raketeAngelBar.display();
    manageRaketenStartButton();
    manageStartAndResetButton();

  }
  
  private void manageRaketenStartButton(){
       if (raketestart.isButtonPressed()) {
          started =true;
        }
  }
  
   private void manageStartAndResetButton(){
     if (start.isButtonPressed() || !start.isActive) {
          reset.isActive=true;
          raketestart.isActive = true;
          raketestart.drawButton();
          reset.drawButton();
          t+=deltaT;
      } else {
          start.isActive =true; 
          raketestart.isActive = false;
          start.drawButton();
          started = false;
          raketeTime=0;
          t=0;
      }
      if (reset.isButtonPressed() || !reset.isActive) {
          started = false;
          start.isActive=true;
          start.drawButton();
          raketeTime=0;
          t=0;
      }
    
    
   }
}