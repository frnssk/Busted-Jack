package server;

import java.awt.Dimension;

import javax.swing.JFrame;

public class StartServer {
	public static void main(String[] args) {
		Server server = new Server(1200);
		ServerUI ui = new ServerUI(server);
		JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(600,600));
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
		frame.add(ui);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
