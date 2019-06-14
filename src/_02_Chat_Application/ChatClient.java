package _02_Chat_Application;

import java.net.*;

import javax.swing.JOptionPane;

import java.io.*;

public class ChatClient {

	public static void main(String[] args) {
		String ip = "192.168.1.174";
		int port = 8001;
		DataInputStream dataInput;
		try {
			Socket connection = new Socket(ip, port);
			JOptionPane.showMessageDialog(null, "Connected to Server.", "Client", JOptionPane.PLAIN_MESSAGE);
			DataOutputStream dataOutput = new DataOutputStream(connection.getOutputStream());
			
			do {
				 	
				String output = JOptionPane.showInputDialog(null, "What would you like to say?", "Client", JOptionPane.PLAIN_MESSAGE);
				dataOutput.writeUTF(output);
				dataInput = new DataInputStream(connection.getInputStream());
				JOptionPane.showMessageDialog(null, dataInput.readUTF());
			} while (!dataInput.readUTF().equalsIgnoreCase("bye"));
			connection.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
