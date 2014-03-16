import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.*;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import javax.swing.event.MouseInputAdapter;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.io.Reader;
import java.io.FileReader;
import java.io.BufferedReader;

public class Screen extends JPanel{

	int [] x = new int[1000000];
	int[] y = new int[1000000];
	int[] begin = new int[1000000];
	private static int pointCount, click;
	BufferedImage img;
	
	public Screen(){
		pointCount = 0;
		begin[0] = 0;
		click = 0;
		img = new BufferedImage(600, 600 , BufferedImage.TYPE_INT_RGB);
		MyListener myListener = new MyListener();
		addMouseListener(myListener);
		addMouseMotionListener(myListener);
	}
	
	@Override
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		int w, h;
		
		w=getWidth();
		h=getHeight();
		
		updateImage();
		
		g2d.drawImage(img, 0, 0, null);
	}

	public void updateImage(){
	
		Graphics2D g2d = img.createGraphics();
		
		int w, h, firstx, firsty;
	
		firstx = randomX(g2d);
		firsty = randomY(g2d);
		w = getWidth();
		h = getHeight();
		
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0,0,getWidth(),getHeight());
		
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(1.0f));
		
		try{
			for(int n = 0; n < begin[click] - 1; n++){
				for(int i = begin[n]; i < begin[n+1] - 1; i++){
					g2d.drawLine(x[i], y[i], x[i+1], y[i+1]);
				}
			}

		
			for(int n = begin[click]; n < pointCount - 1; n++){
				g2d.drawLine(x[n], y[n], x[n+1], y[n+1]);
			}
		}catch(Exception e){}

	}
	
	public void printArray(){
	
		int count;
		count = 0;
		
		try{
			File file = new File("datapoints.txt");
		
			if(!file.exists()){
				file.createNewFile();
			}
			
			Writer out = null;
			out = new BufferedWriter(new FileWriter(file));
			
			/*System.out.println("Here are the x values");*/
			
			while(x[count] != 0){
				out.write(String.valueOf(x[count]) + ":");
				count++;
			}
			count = 0;
			out.write("x");
			while(y[count] != 0){
				out.write(String.valueOf(y[count]) + ":");
				count++;
			}
			
			Reader in = null;
			in = new BufferedReader(new FileReader(file));
			
			out.close();
		}catch(IOException e){
			e.printStackTrace();
		};
		
		/*try{*/
			/*Reader in = null;
			in = new BufferedReader(new FileReader(file));*/
	}
			
	
	private class MyListener extends MouseInputAdapter {
	
		public void mouseDragged(MouseEvent m){
			x[pointCount] = m.getX();
			y[pointCount] = m.getY();
			pointCount++;
			if(pointCount > 1)
				repaint();
		}

		public void mouseReleased(MouseEvent m){
			click++;
			reinitializeArray();
			printArray();
		}
	}
	
	public void reinitializeArray(){
		begin[click] = pointCount;
	}
	
	public int randomY(Graphics2D g2d){
		return (int)(Math.random() * getHeight());
	}
	
	public int randomX(Graphics2D g2d){
		return (int)(Math.random() * getWidth());
	}
}

		
	