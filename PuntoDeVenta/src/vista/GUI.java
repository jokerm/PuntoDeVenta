package vista;

import javax.swing.*;

import com.alee.laf.optionpane.WebOptionPane;
import com.alee.managers.notification.NotificationManager;

public class GUI {
   private static String salida = "";
   
   public static String getString(String mensaje) {
      return WebOptionPane.showInputDialog(mensaje);
   }
   
   public static char getChar(String mensaje) {
      String s = WebOptionPane.showInputDialog(mensaje);
      
      if (s.length() == 1)
         return s.charAt(0);
      else {
         WebOptionPane.showMessageDialog(null, s + " no es un caracter - vuelva a teclear", "Error", WebOptionPane.ERROR_MESSAGE);
         return getChar(mensaje);
      }
   }
   
   public static byte getByte(String mensaje, byte min, byte max) {
      String n = WebOptionPane.showInputDialog(mensaje);
      
      try {
         byte i = Byte.parseByte(n);
         
         if (i >= min && i <= max)
            return i;
         else {
            WebOptionPane.showMessageDialog(null, i + " esta fuera del intervalo " + min + " a " + max + " - vuelva a teclear", "Error", WebOptionPane.ERROR_MESSAGE);
            return getByte(mensaje, min, max);
         }
      }
      catch (NumberFormatException e) {
         WebOptionPane.showMessageDialog(null, n + " es un byte invalido - vuelva a teclear", "Error", WebOptionPane.ERROR_MESSAGE);
         return getByte(mensaje, min, max);
      }
   }

   public static byte getByte(String mensaje) {
      String n = WebOptionPane.showInputDialog(mensaje);
      
      try {
         byte i = Byte.parseByte(n);
		 return i;
      }
      catch (NumberFormatException e) {
         WebOptionPane.showMessageDialog(null, n + " es un byte invalido - vuelva a teclear", "Error", WebOptionPane.ERROR_MESSAGE);
         return getByte(mensaje);
      }
   }

   public static int getInt(String mensaje, int min, int max) {
      String n = WebOptionPane.showInputDialog(mensaje);
      
      try {
         int i = Integer.parseInt(n);
         
         if (i >= min && i <= max)
            return i;
         else {
            WebOptionPane.showMessageDialog(null, i + " esta fuera del intervalo " + min + " a " + max + " - vuelva a teclear", "Error", WebOptionPane.ERROR_MESSAGE);
            return getInt(mensaje, min, max);
         }
      }
      catch (NumberFormatException e) {
         WebOptionPane.showMessageDialog(null, n + " es un int invalido - vuelva a teclear", "Error", WebOptionPane.ERROR_MESSAGE);
         return getInt(mensaje, min, max);
      }
   }

   public static int getInt(String mensaje) {
      String n = WebOptionPane.showInputDialog(mensaje);
      
      try {
         int i = Integer.parseInt(n);
		 return i;
      }
      catch (NumberFormatException e) {
         WebOptionPane.showMessageDialog(null, n + " es un int invalido - vuelva a teclear", "Error", WebOptionPane.ERROR_MESSAGE);
         return getInt(mensaje);
      }
   }

   public static short getShort(String mensaje, short min, short max) {
      String n = WebOptionPane.showInputDialog(mensaje);
      
      try {
         short i = Short.parseShort(n);
         
         if (i >= min && i <= max)
            return i;
         else {
            WebOptionPane.showMessageDialog(null, i + " esta fuera del intervalo " + min + " a " + max + " - vuelva a teclear", "Error", WebOptionPane.ERROR_MESSAGE);
            return getShort(mensaje, min, max);
         }
      }
      catch (NumberFormatException e) {
         WebOptionPane.showMessageDialog(null, n + " es un short invalido - vuelva a teclear", "Error", WebOptionPane.ERROR_MESSAGE);
         return getShort(mensaje, min, max);
      }
   }

   public static short getShort(String mensaje) {
      String n = WebOptionPane.showInputDialog(mensaje);
      
      try {
         short i = Short.parseShort(n);
		 return i;
      }
      catch (NumberFormatException e) {
         WebOptionPane.showMessageDialog(null, n + " es un short invalido - vuelva a teclear", "Error", WebOptionPane.ERROR_MESSAGE);
         return getShort(mensaje);
      }
   }

   public static long getLong(String mensaje, long min, long max) {
      String n = WebOptionPane.showInputDialog(mensaje);
      
      try {
         long i = Long.parseLong(n);
         
         if (i >= min && i <= max)
            return i;
         else {
            WebOptionPane.showMessageDialog(null, i + " esta fuera del intervalo " + min + " a " + max + " - vuelva a teclear", "Error", WebOptionPane.ERROR_MESSAGE);
            return getLong(mensaje, min, max);
         }
      }
      catch (NumberFormatException e) {
         WebOptionPane.showMessageDialog(null, n + " es un long invalido - vuelva a teclear", "Error", WebOptionPane.ERROR_MESSAGE);
         return getLong(mensaje, min, max);
      }
   }

   public static long getLong(String mensaje) {
      String n = WebOptionPane.showInputDialog(mensaje);
      
      try {
         long i = Long.parseLong(n);
		 return i;
      }
      catch (NumberFormatException e) {
         WebOptionPane.showMessageDialog(null, n + " es un long invalido - vuelva a teclear", "Error", WebOptionPane.ERROR_MESSAGE);
         return getLong(mensaje);
      }
   }

   public static float getFloat(String mensaje, float min, float max) {
      String n = WebOptionPane.showInputDialog(mensaje);
      
      try {
         float d = (new Float(n)).floatValue();
         
         if (d >= min && d <= max)
            return d;
         else {
            WebOptionPane.showMessageDialog(null, d + " esta fuera del intervalo " + min + " a " + max + " - vuelva a teclear", "Error", WebOptionPane.ERROR_MESSAGE);
            return getFloat(mensaje, min, max);
         }
      }
      catch (NumberFormatException e) {
         WebOptionPane.showMessageDialog(null, n + " es un float invalido - vuelva a teclear", "Error", WebOptionPane.ERROR_MESSAGE);
         return getFloat(mensaje, min, max);
      }
   }
   
   public static float getFloat(String mensaje) {
      String n = WebOptionPane.showInputDialog(mensaje);
      
      try {
         float d = (new Float(n)).floatValue();
		 return d;
      }
      catch (NumberFormatException e) {
         WebOptionPane.showMessageDialog(null, n + " es un float invalido - vuelva a teclear", "Error", WebOptionPane.ERROR_MESSAGE);
         return getFloat(mensaje);
      }
   }
   
   public static double getDouble(String mensaje, double min, double max) {
      String n = WebOptionPane.showInputDialog(mensaje);
      
      try {
         double d = Double.parseDouble(n);
         
         if (d >= min && d <= max)
            return d;
         else {
            WebOptionPane.showMessageDialog(null, d + " esta fuera del intervalo " + min + " a " + max + " - vuelva a teclear", "Error", WebOptionPane.ERROR_MESSAGE);
            return getDouble(mensaje, min, max);
         }
      }
      catch (NumberFormatException e) {
         WebOptionPane.showMessageDialog(null, n + " es un double invalido - vuelva a teclear", "Error", WebOptionPane.ERROR_MESSAGE);
         return getDouble(mensaje, min, max);
      }
   }
   
   public static double getDouble(String mensaje) {
      String n = WebOptionPane.showInputDialog(mensaje);
      
      try {
         double d = (new Double(n)).doubleValue();
		 return d;
      }
      catch (NumberFormatException e) {
         WebOptionPane.showMessageDialog(null, n + " es un double invalido - vuelva a teclear", "Error", WebOptionPane.ERROR_MESSAGE);
         return getDouble(mensaje);
      }
   }

   public static boolean getBoolean(String pregunta) {
      int n = WebOptionPane.showConfirmDialog(null, pregunta, "Responda si/no", WebOptionPane.YES_NO_OPTION);

      return (n == 0);
   }
   
   public static boolean getBoolean(String pregunta, String afirmativo, String negativo) {
      Object[] opciones = {afirmativo, negativo};

      int n = WebOptionPane.showOptionDialog(null, pregunta, "Seleccione " + afirmativo + " o " + negativo, WebOptionPane.YES_NO_OPTION, WebOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

      return (n == 0);
   }
   
   public static int getChoice(String pregunta, String[] opciones) {
      int n = WebOptionPane.showOptionDialog(null, pregunta, "Seleccione una de las opciones", WebOptionPane.YES_NO_CANCEL_OPTION, WebOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

      return n;
   }
   
   public static void displayMessage(String mensaje) {
	  WebOptionPane.showMessageDialog ( null, mensaje, "Resultado", WebOptionPane.INFORMATION_MESSAGE );
   }
   
   public static void alert(String mensaje) {
      WebOptionPane.showMessageDialog(null, mensaje, "Error", WebOptionPane.ERROR_MESSAGE);
   }
   
   public static void displayNotify(String mensaje) {
	   NotificationManager.showNotification ( mensaje);
   }

   public static void displayOutput() {
      JTextArea areaDeSalida = new JTextArea(10, 30);
      areaDeSalida.setText(salida);
      areaDeSalida.setEditable(false);
      JScrollPane desplazamiento = new JScrollPane(areaDeSalida, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      WebOptionPane.showMessageDialog(null, desplazamiento, "Resultados", WebOptionPane.INFORMATION_MESSAGE);
   }
   
   public static void displayOutput(String titulo) {
      JTextArea areaDeSalida = new JTextArea(10, 30);
      areaDeSalida.setText(salida);
      areaDeSalida.setEditable(false);
      JScrollPane desplazamiento = new JScrollPane(areaDeSalida, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      WebOptionPane.showMessageDialog(null, desplazamiento, titulo, WebOptionPane.INFORMATION_MESSAGE);
   }

   public static void clearOutput() {
	salida = "";
   }
   
   public static void appendOutput(String siguiente) {
      salida += siguiente;
   }
   
   public static void appendOutput(char siguiente) {
      salida += siguiente;
   }
   
   public static void appendOutput(byte siguiente) {
      salida += siguiente;
   }
   
   public static void appendOutput(int siguiente) {
      salida += siguiente;
   }

   public static void appendOutput(short siguiente) {
      salida += siguiente;
   }
   
   public static void appendOutput(long siguiente) {
      salida += siguiente;
   }
   
   public static void appendOutput(float siguiente) {
      salida += siguiente;
   }
   
   public static void appendOutput(double siguiente) {
      salida += siguiente;
   }
   
   public static void appendOutput(boolean siguiente) {
      salida += siguiente;
   }

	public static void displayError(String mensaje, String error) {
		WebOptionPane.showMessageDialog ( null, mensaje, error, WebOptionPane.ERROR_MESSAGE );
	}   
}
