package server;

import java.awt.BorderLayout;

import javax.swing.*;

public class ServerUI extends JPanel {
	private Server server;
	private JTextArea taLiveUpdate = new JTextArea();
	private JScrollPane spLiveLog = new JScrollPane(taLiveUpdate);
	private JPanel pane = new JPanel();
	
	public ServerUI(Server sever) {
		this.server = server;
		
		pane.setLayout(new BorderLayout());
		taLiveUpdate.setEditable(false);
		spLiveLog.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		pane.add(spLiveLog, BorderLayout.CENTER);
	}
	
	public void logActivity(String string) {
		taLiveUpdate.append(string);
	}

}
