package controlador;

public abstract class Controlador {
	public int id;
	public String controla;
	protected DataBaseManager manager;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getControla() {
		return controla;
	}

	public void setControla(String controla) {
		this.controla = controla;
	}
	
	/*This method is used for one action to do*/
	public abstract void iniAction(String ... args);
	/*This is the method to transfer data from View to controller*/
	public abstract void dataToController(String ... args);
	/*This method could use for bidirectional communication without objects methods*/
	public abstract void dataToView(String ... args);

	@Override
	public String toString() {
		return "Controlador [id=" + id + ", controla=" + controla + "]";
	}

	public DataBaseManager getManager() {
		return manager;
	}

	public void setManager(DataBaseManager manager) {
		this.manager = manager;
	}

}
