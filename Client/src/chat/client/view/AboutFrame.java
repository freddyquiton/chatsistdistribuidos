package chat.client.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.Box;

public class AboutFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public AboutFrame() {
		setTitle("Acerca de...");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 224, 238);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		Box verticalBox = Box.createVerticalBox();
		contentPane.add(verticalBox);
		
		JLabel lblProgramaDesarrolladoPor = new JLabel("Programa desarrollado por:");
		verticalBox.add(lblProgramaDesarrolladoPor);
		
		JLabel lblSandraIsabelLara = new JLabel("Sandra Isabel Lara Navia");
		verticalBox.add(lblSandraIsabelLara);
		
		JLabel lblPabloSanabriaQuispe = new JLabel("Pablo Sanabria Quispe");
		verticalBox.add(lblPabloSanabriaQuispe);
	}

}
