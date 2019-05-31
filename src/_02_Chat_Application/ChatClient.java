package _02_Chat_Application;

import java.net.*;

import javax.swing.JOptionPane;

import java.io.*;

public class ChatClient {

	public static void main(String[] args) {
		// 1. Create a String for the ip address of the server.
		// If you don't know how to find a computer's ip address, ask about ifconfig on
		// linux/mac and ipconfig on windows.
		String ip = "192.168.1.174";
		// 2. Create an integer for the server's port number
		int port = 8001;
		// 3. Surround steps 4-9 in a try-catch block that catches any IOExceptions.
		try {
			// 4. Create an object of the Socket class. When initializing the object, pass
			// in the ip address and the port number

			Socket connection = new Socket(ip, port);
			JOptionPane.showMessageDialog(null, "Connected to Server.");
			// 5. Create a DataOutputStream object. When initializing it, use the Socket
			// object you created in step 4 to call the getOutputStream() method.
			DataOutputStream dataOutput = new DataOutputStream(connection.getOutputStream());
			// 6. Use the DataOutputStream object to send a message to the server using the
			// writeUTF(String message) method
			String output = JOptionPane.showInputDialog("What would you like to say?");
			dataOutput.writeUTF(output);
			// 7. Create a DataInputStream object. When initializing it, use the Server
			// object you created in step 4 to call the getInputStream() method.
			DataInputStream dataInput = new DataInputStream(connection.getInputStream());
			// 8. Use the DataInputStream object to print a message from the server using
			// the readUTF() method.
			JOptionPane.showMessageDialog(null, dataInput.readUTF());
			// 9. Close the client's server object
			connection.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
