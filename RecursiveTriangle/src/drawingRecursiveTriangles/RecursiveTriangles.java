package drawingRecursiveTriangles;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public class RecursiveTriangles extends JPanel {
	static final double SIN_SIXTY= Math.sin(Math.toRadians(60));
	int height;
	int width = (int)Math.round(height/SIN_SIXTY);
	int degree = 10;
	Color colorArray[];
	
	
	
	public RecursiveTriangles(int height) {
		this.height=height;
	}


	public void paintComponent(Graphics g){
		super.paintComponent(g);
		colorArray = new Color[degree+1];
		fillColorArray();
		this.setBackground(Color.WHITE);
		g.setColor(Color.BLUE);
		drawBaseTriangle(g,0,getHeight(), getHeight()/SIN_SIXTY,getHeight(),(getHeight()/SIN_SIXTY)/2, 0);
		
	}
	
	
private void fillColorArray() {
	Random random = new Random();
	for(int i = 0; i<colorArray.length; i++){
			
			int r = random.nextInt(256);
		    int	g = random.nextInt(256);
			int b = random.nextInt(256);
			
			colorArray[i] = new Color(r, g , b);
		}
		
	}


public void drawBaseTriangle(Graphics g, double x1, double y1, double x2, double y2, double x3, double y3){
	g.drawLine((int)x1,(int)y1,(int)x2, (int)y2);
	g.drawLine((int)x1,(int)y1,(int)x3, (int)y3);
	g.drawLine((int)x3,(int)y3,(int)x2, (int)y2);
	drawUpSideDownTriangles(g,1,(x1+x2)/2, (y1+y2)/2, (x2+x3)/2, (y2+y3)/2, (x3+x1)/2, (y3+y1)/2 );

	
	}

public void drawUpSideDownTriangles(Graphics g,int recursiveDegree, double x1, double y1, double x2, double y2, double x3, double y3 ){
	//Random Color festlegen abhängig vom Grad der Rekursion
	 g.setColor(colorArray[recursiveDegree-1]);
	 //Dreiecke füllen
	 int xPoints[] = {(int)Math.round(x1),(int)Math.round(x2),(int)Math.round(x3)};
	 int yPoints[] = {(int)Math.round(y1),(int)Math.round(y2),(int)Math.round(y3)};
	 g.fillPolygon(xPoints, yPoints, 3);
	 //Zeichnen des auf dem Kopf stehenden Dreiecks
	 g.drawLine((int)x1,(int)y1,(int)x2, (int)y2);
	 g.drawLine((int)x1,(int)y1,(int)x3, (int)y3);
	 g.drawLine((int)x3,(int)y3,(int)x2, (int)y2);
	
	
	
	if(recursiveDegree < degree){
		
		drawUpSideDownTriangles(g,recursiveDegree+1,(x1 + x2) / 2 + (x2 - x3) / 2,(y1 + y2) / 2 + (y2 - y3) / 2,(x1 + x2) / 2 + (x1 - x3) / 2,(y1 + y2) / 2 + (y1 - y3) / 2,(x1 + x2) / 2,(y1 + y2) / 2);
		drawUpSideDownTriangles(g,recursiveDegree+1,(x3 + x2) / 2 + (x2 - x1) / 2,(y3 + y2) / 2 + (y2 - y1) / 2,(x3 + x2) / 2 + (x3 - x1) / 2,(y3 + y2) / 2 + (y3 - y1) / 2,(x3 + x2) / 2,(y3 + y2) / 2);
		drawUpSideDownTriangles(g,recursiveDegree+1,(x1 + x3) / 2 + (x3 - x2) / 2,(y1 + y3) / 2 + (y3 - y2) / 2,(x1 + x3) / 2 + (x1 - x2) / 2,(y1 + y3) / 2 + (y1 - y2) / 2,(x1 + x3) / 2,(y1 + y3) / 2);
	}
}
/*public void recursiveDraw2(Graphics g, double x, double y, double width, double height){
	drawSierpinksiTriangle(g,(int)x,(int)y,(int)width,(int)height);
	if((x<100) || (height<100)){return;}
			
	double h2 = height/2;
	double w2 = width/2;
	recursiveDraw2(g,x,y,w2,h2);
	recursiveDraw2(g,x+w2/2,y+h2,w2,h2);
	recursiveDraw2(g,x+w2,y,w2,h2);
	}*/
}

