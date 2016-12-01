

/**********************************/
/* Name: KeomaTrippner            */
/* Matrikelnummer: s0550635      */
/* Datum: 02.11.2016              */
/**********************************/

float kometPosXStart = -175000; 
float kometPosYStart = 80000;
float kometPosY;
float kometPosX;
float kometDX = 10000;
float kometDY = 10000;
float raketePosX=0;
float raketePosY=0;
float raketeH=10000;
float raketeW=2500;
float M = 0.002;// px/m
float t = 0;
float ground = 0;
float deltaT = 0.02;
float startBuX= 400;
float startBuY= 520;
float startBuH= 30;
float startBuW= 50;
float vY =-8888*sin(3.14/18);
float vX =8888*cos(3.14/18);
boolean startIsPressed =false;


void setup(){
  size(800,600);
  frameRate(50);
}



void draw(){
  background(255);
  fill(255);
  // 800 Pixel = 800km = 800.000m
    
  // println(t);
  kometPosX = kometPosXStart + vX*t;
  kometPosY = kometPosYStart + vY*t;
    
    

//Scene
  pushMatrix();//vor Y Koordinaten muss ein "-" wegen dem KoSys
  translate((width/2),500);
  
  //Komet
  ellipse(kometPosX*M,-kometPosY*M,kometDX*M,kometDY*M);//Komet
  
  //Erde
  line(-400,ground,400,ground);//Ground
  
  //Rakete
  rect((raketePosX-raketeW/2)*M,(-raketePosY-raketeH)*M,raketeW*M,raketeH*M);
  
  popMatrix();
    
//UI  
    buttonPressed();
  
 if(startIsPressed){
   drawStopButton();
   t+=deltaT;
 }
 else{
  drawStartButton();
  t=0;
 }
  
  
}

void drawStartButton(){
 //Button
  stroke(0,255,0);
  strokeWeight(5);
  fill(128);
  rect(startBuX,startBuY,startBuW,startBuH);
  textAlign(CENTER,CENTER);
  fill(0,255,0);
  text("Start",startBuX+(startBuW/2),startBuY+(startBuH/2));
  stroke(0,0,0);
  strokeWeight(1);
  fill(255);
}

void drawStopButton(){
 //Button
  stroke(255,0,0);
  strokeWeight(5);
  fill(128);
  rect(startBuX,startBuY,startBuW,startBuH);
  textAlign(CENTER,CENTER);
  fill(255,0,0);
  text("Stop",startBuX+(startBuW/2),startBuY+(startBuH/2));
  stroke(0,0,0);
  strokeWeight(1);
  fill(255);
}

void buttonPressed(){
 if(mousePressed){
    if(mouseX>startBuX && mouseX <(startBuX+startBuW) && mouseY>startBuY && mouseY< startBuY+startBuH){
       if(!startIsPressed){
         startIsPressed= true;
       }
       else{
         startIsPressed =false;
       }
  }
 }
 }