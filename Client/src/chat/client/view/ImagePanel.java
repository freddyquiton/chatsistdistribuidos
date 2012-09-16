package chat.client.view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	private Image image;

	public ImagePanel(Image theImage) {
		setImage(theImage);
	}
	
	public void setImage(Image theImage) {
		image = theImage;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}

}
