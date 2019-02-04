package dotandboxes;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class DrawingComponent extends JComponent{
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.drawOval(100, 100, 10, 10);
		g2.drawOval(200, 100, 10, 10);
		g2.drawOval(300, 100, 10, 10);
		g2.drawOval(100, 200, 10, 10);
		g2.drawOval(200, 200, 10, 10);
		g2.drawOval(300, 200, 10, 10);
		g2.drawOval(100, 300, 10, 10);
		g2.drawOval(200, 300, 10, 10);
		g2.drawOval(300, 300, 10, 10);
		
		
		
	}

}
