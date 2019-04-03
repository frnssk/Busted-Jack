package client;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;

public class StartClient {
	public static void main(String[] args) throws IOException {
		UserClient client = new UserClient("localhost", 1200);
		UserController controller = new UserController(client);
		UserInterface gui = new UserInterface(controller);
		JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(1000,600));
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
		frame.add(gui);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
