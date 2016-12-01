

/**********************************/
/* Name: KeomaTrippner            */
/* Matrikelnummer: s0550635      */
/* Datum: 02.11.2016              */
/**********************************/

float kometPosXStart = -175000f; 
float kometPosYStart = 80000f;
float kometPosY;
float kometPosX;
float kometDX = 10000f;
float kometDY = 10000f;

float raketeStartPosX=0f;
float raketeStartPosY=0f;
float raketePosY;
float raketePosX;
float raketeH=2500f;
float raketeW=10000f;

float raketeV =1000;
float kometV = 8888f;
float vY;
float vX;
float raketeXV0;
float raketeYV0;
float raketeWinkel;
float einschlagWinkel = 10;

float g = 9.81;
float M = 0.002;// px/m
float t = 0;
float raketeTime=0;
float ground = 0;
float deltaT = 0.02;
boolean started = false ;
Controls c;

float countdown=0;

void setup() {
  c= new Controls();
  size(800, 600);
  frameRate(50);
  calkRandomOf10();
  vY = (-1*kometV) *sin(radians(einschlagWinkel));
  vX = kometV *cos(radians(einschlagWinkel));

}

void draw() {
  background(255);
 
  pushMatrix();//vor Y Koordinaten muss ein "-" wegen dem KoSys
  translate((width/2), 500);
  
  if (!started) {// Start und BewegungsPhase
  
      setupBeforStart();
       }
  else {
      
      raketeAfterStart();
  }
  // Berechnung der Komet Position pro Zeit
  kometPosY = kometPosYStart + vY*t-(g/2*pow(t, 2)); 
  kometPosX = kometPosXStart + vX*t;

  countdown = -(raketeStartPosY+kometPosYStart)/(raketeYV0-vY);
  float temp  = kometPosYStart + vY*(-countdown)-(g/2*pow((-countdown), 2)); 


  //Komet zeichnen
  drawKomet();
  //Erde
  //fill(255,0,0);
  line(-400, ground, 400, ground);//Ground
  
  
  popMatrix();
   // abhandlung von Button und UI Controlls
  c.update();
}

void drawRakete() {
  
  pushMatrix();
  translate((raketePosX-raketeW/2)*M, (-raketePosY-raketeH)*M);
  rotate(-raketeWinkel); 
  //Rakete
  fill(0, 200, 255);
  rect((0-raketeW/2)*M, (0-raketeW)*M, raketeH*M, raketeW*M);
  popMatrix();
}

void drawKomet() {
  //Komet
   fill(255, 0, 0);
  ellipse(kometPosX*M, -kometPosY*M, kometDX*M, kometDY*M);
}

void setupBeforStart() {
  //transate auf 0 Punkt
  //set Winkel

  kometPosY = kometPosYStart; 
  kometPosX = kometPosXStart;
  raketePosX = raketeStartPosX;
  raketePosY = raketeStartPosY;
  raketeWinkel =  c.raketeAngelBar.value;
  raketeWinkel = radians(raketeWinkel);
  println(raketeWinkel);
  raketeXV0 = raketeV * cos(raketeWinkel);
  raketeYV0 = raketeV * sin(raketeWinkel);

  drawRakete();

}

void raketeAfterStart(){
// Berechnung der Raketen Position pro Zeit
      raketeTime += deltaT;
      raketePosY = raketeStartPosY + raketeYV0*raketeTime-(g/2*pow(raketeTime, 2));
      raketePosX = raketeStartPosX + raketeXV0*raketeTime;
      //Berechnung vom RaketenWinkel während des Fluges
      float raketeVY = raketeYV0-g*raketeTime;
      float raketeVX = raketeXV0;
      raketeWinkel = atan2(raketeVY,raketeVX);
      println(raketeWinkel);
      //Zeichnen der Rakete
      
      drawRakete();

}

void calkRandomOf10() {

  float random = random(kometPosXStart/5)-kometPosXStart/10;
  kometPosXStart += random;
  random = random(kometPosYStart/5)-kometPosYStart/10;
  kometPosYStart += random;
  random = random(kometV/5)-kometV/10;
  kometV += random;
  random = random(einschlagWinkel/5)-einschlagWinkel/10;
}