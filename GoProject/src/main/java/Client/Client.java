package Client;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;

public class Client {
	public static void main(String[] args) throws Exception {
		ClientConnector connection = new ClientConnector("127.0.0.1");
		new ClientServerBridge(connection);
	}
}