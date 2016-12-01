package s0550635_KeomaTrippner;

import java.awt.Point;
import java.awt.Polygon;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;

import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import lenz.htw.ai4g.ai.AI;
import lenz.htw.ai4g.ai.DriverAction;
import lenz.htw.ai4g.ai.Info;

public class AI_Class extends AI {
	int zielradi = 50;
	float knotenverschiebungVonObs = 25;
	Area areaAllerObs = new Area();
	float gebrauchterWinkel;
	float drehbeschleunigung;
	float orientierung;
	float orientZuObsMid;
	float beschleunigung;
	double wunschgeschwindigkeit= info.getMaxVelocity();
	double wunschdrehgeschwindigkeit = info.getMaxAngularVelocity();
	double distance;
	double distanceToObsMid;
	public Polygon[] obstacles = info.getTrack().getObstacles();
	public ArrayList<Knoten> qKnoten;
	public ArrayList<Knoten> fKnoten;
	public Knoten route;
	public Knoten absturzrettung;
	private Knoten derZielKnoten;
	private boolean	searchingWay = true;
	ArrayList<Integer> obstacleMidPoints;// 0 = x, 1 = y, 2= x, 3 = y....
	public static ArrayList<Knoten> KNOTENPUNKTE = new ArrayList<>();
	
	
	public AI_Class(Info info) {
		super(info);
		
		
	
		obstacleMidPoints = new ArrayList<>(); 
		for(Polygon p : obstacles){
			int xMid=0;
			int yMid=0;
			for(int i= 0; i< p.npoints; i++ ){
				xMid +=	p.xpoints[i];
				yMid +=	p.ypoints[i];
				//knotenPunkte.add(new Knoten(p.xpoints[i],p.ypoints[i],p));
			}
			xMid = xMid/p.npoints;
			yMid = yMid/p.npoints;
			obstacleMidPoints.add(xMid);
			obstacleMidPoints.add(yMid);
		}
		for(Polygon o : obstacles){
			//if(!o.equals(obstacles[0]) && !o.equals(obstacles[1])){
				areaAllerObs.add(new Area(o));
				for(int i = 0; i< o.npoints; i++){
					 int i2 = ((i+1)%(o.npoints));// i+1
					 int i3 = ((i+2)%(o.npoints));// i+2
					 Vector2f  v1 = new Vector2f((o.xpoints[i2]-o.xpoints[i]),(o.ypoints[i2]-o.ypoints[i]));
					 Vector2f  v2 = new Vector2f((o.xpoints[i3]-o.xpoints[i2]),(o.ypoints[i3]-o.ypoints[i2]));
					 float angle = Vector2f.angle(v1, v2);
					if(angle < Math.PI){
						Vector2f normalV1 = new Vector2f(v1.y,-v1.x);
						Vector2f normalV2 = new Vector2f(v2.y,-v2.x);
						Vector2f normal = new Vector2f((normalV1.getX()+normalV2.getX())/2 , (normalV1.getY()+normalV2.getY())/2 ); // durchschnitt der normalen von v1 und v2
						normal.normalise();
						normal.scale(knotenverschiebungVonObs);
						Point neuerKnoten = new Point(o.xpoints[i2],o.ypoints[i2]);
						neuerKnoten.translate((int)normal.x, (int)normal.y);
						KNOTENPUNKTE.add(new Knoten(neuerKnoten));
					}
				}
			//}
		}
		for(Knoten k : KNOTENPUNKTE){
			findeRichtigeNachbarn(k);
			}
		for(Knoten k : KNOTENPUNKTE){
			setKostenZuNachbarn(k);
		}
		derZielKnoten = findWay(new Point((int)info.getX(),(int)info.getY()));
	}
	

	
		
	
		
		// TODO Auto-generated constructor stub
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "KeO";
	}
	@Override
	public DriverAction update(boolean zurueck) {
		double MeinX= info.getX();
		double MeinY= info.getY();
		if(derZielKnoten.getyWert()!=info.getCurrentCheckpoint().getY() && route.vorgangerK== null)
		{
			if(searchingWay){
				route = null;
				searchingWay =false;
				derZielKnoten = findWay(new Point((int)MeinX,(int)MeinY));
			}
		}
		
		if(route!=null){
			double ZielX = route.getxWert();
			double ZielY = route.getyWert();
			if(istImBereich(MeinX, MeinY,ZielX,ZielY,10)&& route.vorgangerK != null){
				route = route.vorgangerK;
			}
		
			if(info.getVelocity().length()== 0){route = absturzrettung;}
			
			distance = info.getCurrentCheckpoint().distance(route.getxWert(),route.getyWert(),MeinX, MeinY);
			orientierung= info.getOrientation();
			gebrauchterWinkel = (float) Math.atan2(ZielY-MeinY,ZielX-MeinX)-orientierung;
			
			gebrauchterWinkel = winkelProblemfix(gebrauchterWinkel);
			
			//Arrive
			if(distance < zielradi){wunschgeschwindigkeit = distance*info.getMaxVelocity()/zielradi;}
			else{wunschgeschwindigkeit= info.getMaxVelocity();}
			
			if(gebrauchterWinkel < 0.2){wunschdrehgeschwindigkeit= (float) ((gebrauchterWinkel*info.getMaxAngularVelocity())/0.2);}
			else{wunschdrehgeschwindigkeit = info.getMaxAngularVelocity();}
	
			//Matching
			drehbeschleunigung = (float) ((wunschdrehgeschwindigkeit-info.getAngularVelocity()));
			beschleunigung = (float) ((wunschgeschwindigkeit-info.getVelocity().length())/6);
		}
	
		
		//obstacleAvoid(MeinX, MeinY);
		
		
		return new DriverAction(beschleunigung,drehbeschleunigung);
	}
	
	public float winkelProblemfix( float winkel){
		if(winkel>Math.PI)winkel = (float) (winkel - 2* Math.PI);
		if(winkel<-Math.PI)winkel= (float) (winkel + 2*Math.PI);
		return winkel;
	}
	
	public void obstacleAvoid( double meinX, double meinY){
				for(int mid = 4; mid< obstacleMidPoints.size(); mid = mid+2){
				orientZuObsMid = 	(float) (orientierung-Math.atan2(obstacleMidPoints.get(mid+1)-meinY,obstacleMidPoints.get(mid)-meinX));
				orientZuObsMid = winkelProblemfix(orientZuObsMid);
				distanceToObsMid = 	info.getCurrentCheckpoint().distance(obstacleMidPoints.get(mid), obstacleMidPoints.get(mid+1),meinX, meinY);
				if(distanceToObsMid <140 && Math.abs(orientZuObsMid)<1.9 ){
					drehbeschleunigung = (float) (((orientZuObsMid*info.getMaxAngularVelocity())-info.getAngularVelocity())/0.5);
						beschleunigung = info.getMaxAcceleration()/2;
					}
				}
	}

	@Override
	public String getTextureResourceName() {
		// TODO Auto-generated method stub
		return "/s0550635_KeomaTrippner/car.png";
	}
	
	public Knoten findWay(Point myposition){
		qKnoten = new ArrayList<>();
		fKnoten = new ArrayList<>();
		Knoten zielKnoten = new Knoten(info.getCurrentCheckpoint());
		Knoten startKnoten = new Knoten(myposition);
		fugeKnotenhinzu(startKnoten);
		fugeKnotenhinzu(zielKnoten);

		startKnoten.momentaneKosten = 0;
		qKnoten.add(startKnoten);
		
		while(!qKnoten.isEmpty()){

			Knoten v = qKnoten.remove(findKleinsteKostenInList(qKnoten));
			for(Knoten n : v.getNachbarn()){
				if(!fKnoten.contains(n)){
					if(!qKnoten.contains(n)){
						qKnoten.add(n);
						n.momentaneKosten=Double.MAX_VALUE;
					}
				if((v.momentaneKosten+v.nachbarnWegKosten.get(v.getNachbarn().indexOf(n)))< n.momentaneKosten){
					n.momentaneKosten = v.momentaneKosten+v.nachbarnWegKosten.get(v.getNachbarn().indexOf(n));
					n.vorgangerK = v;
				}
				}
			}
			fKnoten.add(v);
			if(fKnoten.contains(zielKnoten))break;
		}

		route = createRoute(zielKnoten).vorgangerK;
		absturzrettung = route;
		searchingWay =true;

		return zielKnoten;
	}
	
	public Knoten createRoute(Knoten node){
		 if (node == null || node.vorgangerK == null) {
	         return node;
	     }

	     Knoten remaining = createRoute(node.vorgangerK);
	     node.vorgangerK.vorgangerK = node;
	     node.vorgangerK = null;
	    return remaining;
	 }
	
	private Knoten createRadomeRoute(Knoten k){
		Knoten newRoute = k;
		Random rand = new Random();
		int randomNum = rand.nextInt(k.getNachbarn().size() - 1);
		newRoute.vorgangerK = k.getNachbarn().get(randomNum);
		return newRoute;
	}
		 
	

	
	public void findeRichtigeNachbarn(Knoten k){
		for(Knoten p : KNOTENPUNKTE){
			if(!k.equals(p)){
			Polygon poly = new Polygon();
			poly.addPoint(k.getxWert()+27, k.getyWert()+27);
			poly.addPoint(k.getxWert()-27, k.getyWert()-27);
			poly.addPoint(p.getxWert()+27, p.getyWert()+27);
			poly.addPoint(p.getxWert()-27, p.getyWert()-27);

			Area area = new Area(poly);
					area.intersect(areaAllerObs);
					if(area.isEmpty()){
						k.getNachbarn().add(p);}
			}
			}
	}
	
	public void setKostenZuNachbarn(Knoten k){
		for(int i = 0; i<k.getNachbarn().size();i++){
			double kosten = info.getCurrentCheckpoint().distance(k.getxWert(), k.getyWert(),k.getNachbarn().get(i).getxWert(), k.getNachbarn().get(i).getyWert());
			k.nachbarnWegKosten.add(kosten);
		}
	}
	public void setMomentaneKostenZuNachbarn(Knoten start){
		for(Knoten k : qKnoten){
			if(start.getNachbarn().contains(k)){
				k.momentaneKosten=start.nachbarnWegKosten.get(start.getNachbarn().indexOf(k));
			}
		}
	}
	
	
	public int findKleinsteKostenInList(ArrayList<Knoten> q){
		double minKosten = Double.MAX_VALUE;
		int guenstigsterIndex= -1;
		for(int k = 0; k<q.size();k++){
			if(q.get(k).momentaneKosten<=minKosten){
				minKosten = q.get(k).momentaneKosten;
				guenstigsterIndex = k;
			}
		}
		return guenstigsterIndex;
	}
	
	private void fugeKnotenhinzu(Knoten k){
		
		KNOTENPUNKTE.add(k);
		for(Knoten p : KNOTENPUNKTE){
			if(!k.equals(p)){
			Polygon poly = new Polygon();
			poly.addPoint(k.getxWert()+7, k.getyWert()+7);
			poly.addPoint(k.getxWert()-7, k.getyWert()-7);
			poly.addPoint(p.getxWert()+7, p.getyWert()+7);
			poly.addPoint(p.getxWert()-7, p.getyWert()-7);
			Area area = new Area(poly);
					area.intersect(areaAllerObs);
					if(area.isEmpty()){
						double kosten = info.getCurrentCheckpoint().distance(k.getxWert(), k.getyWert(),p.getxWert(), p.getyWert());
						k.getNachbarn().add(p);
						k.nachbarnWegKosten.add(kosten);
						p.getNachbarn().add(k);
						p.nachbarnWegKosten.add(kosten);
					}
			}}
	}
	
	private boolean istImBereich(double xMobile, double yMobile,double x, double y, int radius){
		if((xMobile<=x+radius) && (xMobile>= x-radius) && (yMobile<= y+radius) && (yMobile>=y-radius)){
			return true;
		} 
		return false;
	}
	
	@Override
	public void doDebugStuff() {
		
		GL11.glBegin(GL11.GL_LINES);
		for(int k = 0; k<KNOTENPUNKTE.size();k++){ 
			for(Knoten p : KNOTENPUNKTE.get(k).getNachbarn()){
				GL11.glVertex2i(KNOTENPUNKTE.get(k).getxWert(),KNOTENPUNKTE.get(k).getyWert());
				GL11.glVertex2i(p.getxWert(),p.getyWert());

			}
		}
		GL11.glEnd();
		
		//System.out.println(distanceToObsMid);
		//System.out.println(orientZuObsMid);

		
	}
	
}
