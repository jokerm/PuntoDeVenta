package controlador;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;


public class Caja extends Controlador implements KeyEventDispatcher {

	@Override
	public void iniAction(String... args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dataToController(String... args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dataToView(String... args) {
		// TODO Auto-generated method stub

	}

	@Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getID() == KeyEvent.KEY_PRESSED) {
            System.out.println("tester KRY >  "+ e.getKeyCode());
        }
        return false;
    }

}
