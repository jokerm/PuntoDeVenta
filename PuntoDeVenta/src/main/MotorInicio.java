/**
 * This is the main class for the Point Of Sale program based in MVC
 */
package main;
import java.awt.EventQueue;
import java.io.IOException;
import java.net.ServerSocket;
import java.sql.SQLException;

import javax.swing.UIManager;

import vista.GUI;

import com.alee.laf.WebLookAndFeel;

import controlador.*;

public class MotorInicio {
	/*We use socketServer to unable open multiple window*/
	private static ServerSocket SERVER_SOCKET;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel ( WebLookAndFeel.class.getCanonicalName () );
					
					SERVER_SOCKET = new ServerSocket(1334);
					SERVER_SOCKET.isClosed();
					WebLookAndFeel.setDecorateDialogs(true);
					//new Selector();
					new Empresa();
					
				} catch(IOException e) {
					vista.GUI.displayError("No puede abrir la aplicacion mas de 1 vez",
							"Aplicacion corriendo");
					System.exit(0);
				} catch (ClassNotFoundException | SQLException e) {
					GUI.displayError("Se produjó un error con la base de datos", "DB Fail");
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
