package drawingRecursiveTriangles;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class DrawRecursiveTriangles {
	
	public static void main(String[] args){
		Toolkit t = Toolkit.getDefaultToolkit();
		  Dimension dim = t.getScreenSize();
		  int width = (int)dim.getWidth();
		  int height = (int)dim.getHeight();
		  		
		JFrame jf = new JFrame("Unsere Canvas");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//RecurTri rt = new RecurTri();
		RecursiveTriangles k = new RecursiveTriangles(height);
		jf.add(k);
		jf.setResizable(true);
		jf.setSize(width,height);
		jf.setVisible(true);
		
		
		
	}
}
