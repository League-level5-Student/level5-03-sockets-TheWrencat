package _02_Chat_Application;

import java.net.*;

import javax.swing.JOptionPane;

import java.io.*;

public class ChatServer extends Thread {
	ServerSocket server;
	int port = 8001;

	public ChatServer() throws IOException {
		server = new ServerSocket(port, 100);
		server.setSoTimeout(15000000);
	}

	public void run() {
		boolean var = true;
		DataInputStream dataInput;
		while (var) {

			try {
				JOptionPane.showMessageDialog(null, "Waiting for a client to connect..", "Server",
						JOptionPane.PLAIN_MESSAGE);
				Socket sock = server.accept();

				JOptionPane.showMessageDialog(null, "Client Connected!", "Server", JOptionPane.PLAIN_MESSAGE);
				do {
					dataInput = new DataInputStream(sock.getInputStream());
					JOptionPane.showMessageDialog(null, dataInput.readUTF());
					DataOutputStream dataOutput = new DataOutputStream(sock.getOutputStream());
					String output = JOptionPane.showInputDialog(null, "What would you like to say?", "Server",
							JOptionPane.PLAIN_MESSAGE);
					dataOutput.writeUTF(output);
				} while (!dataInput.readUTF().equalsIgnoreCase("bye"));
				server.close();
			} catch (SocketTimeoutException e) {
				var = false;
			} catch (IOException e) {
				var = false;
			}

		}

	}

	public static void main(String[] args) {
		try {
			ChatServer hello = new ChatServer();
			hello.run();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
