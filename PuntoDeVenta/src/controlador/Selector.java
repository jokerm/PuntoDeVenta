package controlador;

public class Selector extends Controlador{
	
	public Selector() {
		vista.Selector selector  = new vista.Selector();
		selector.setControlador(this);
		selector.setVisible(true);
	}
	
	private void initialize(char opc) {
		switch(opc) {
			case '1':
				Administrador admin = new Administrador(null);
				vista.Administrador administrador = new vista.Administrador(admin);
				administrador.setControlador(admin);
				administrador.setVisible(true);
				break;
			case '2':
				vista.GUI.displayMessage("2");
				break;
			default:
				vista.GUI.displayError("Opcion no valida", "");
		}			
	}

	@Override
	public void iniAction(String... args) {
		if(args[0].charAt(0) == 'A') {
			initialize('1');
		} else {
			initialize('2');
		}
	}

	@Override
	public void dataToController(String... args) {
		
	}

	@Override
	public void dataToView(String... args) {
		
	}

}
