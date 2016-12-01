package s0550635_KeomaTrippner;

import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;

public class Knoten  {
	
	private ArrayList<Knoten> nachbarn;
	
	public double momentaneKosten;
	public ArrayList<Double> nachbarnWegKosten;
	public Knoten vorgangerK;
	private int xWert;
	private int yWert;
	
	private Point position;
	
	
	




	public Knoten(Point point){
		nachbarn = new ArrayList<>();
		nachbarnWegKosten = new ArrayList<>();
		xWert = point.x;
		yWert = point.y;
		position = point;
	
		
	}
	
	/*public Knoten getGuenstigsterNachbarKnoten(){
		int indexZahl=-1;
		double kleinsterKosten=Double.MAX_VALUE;
		for(int i = 0; i<nachbarnWegKosten.length;i++){
			if(nachbarnWegKosten[i]<kleinsterKosten){
				indexZahl = i;
				kleinsterKosten = nachbarnWegKosten[i];
			}	
		}
		return nachbarn.get(indexZahl);
	}*/
	




	public ArrayList<Knoten> getNachbarn() {
		return nachbarn;
	}



	public void setNachbarn(ArrayList<Knoten> nachbarn) {
		this.nachbarn = nachbarn;
	}







	public int getxWert() {
		return xWert;
	}



	public void setxWert(int xWert) {
		this.xWert = xWert;
	}



	public int getyWert() {
		return yWert;
	}



	public void setyWert(int yWert) {
		this.yWert = yWert;
	}



	public Point getPosition() {
		return position;
	}



	public void setPosition(Point position) {
		this.position = position;
	}


	
}
