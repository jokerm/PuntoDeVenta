package vista;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;
import java.io.IOException;

import com.alee.extended.statusbar.WebMemoryBar;
import com.alee.laf.button.WebButton;
import com.alee.laf.desktoppane.WebDesktopPane;
import com.alee.laf.menu.MenuBarStyle;
import com.alee.laf.menu.WebMenu;
import com.alee.laf.menu.WebMenuBar;
import com.alee.laf.menu.WebMenuItem;
import com.alee.laf.panel.WebPanel;
import com.alee.managers.notification.NotificationManager;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import modelo.Dolar;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

import javax.swing.JSeparator;

public class Administrador extends JFrame {
	private static final long serialVersionUID = -7107275893341583544L;
	private WebPanel contentPane;
	private WebDesktopPane desktopPane;
	private controlador.Controlador controlador;

	/**
	 * Create the frame.
	 */
	public Administrador(controlador.Controlador cont) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				showDolar();
			}
			@Override
			public void windowClosing(WindowEvent arg0) {
				controlador.dataToController("close");
			}
		});
		setTitle("Administrador");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Administrador.class.getResource("/vista/recursos/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		
		WebMenuBar menuBar = new WebMenuBar();
		menuBar.setMenuBarStyle ( MenuBarStyle.standalone );
		setJMenuBar(menuBar);
		
		WebMenu mntmArchivo = new WebMenu("Archivo");
		menuBar.add(mntmArchivo);
		
		WebMenuItem mntmEmpresa = new WebMenuItem("Empresa");
		mntmArchivo.add(mntmEmpresa);
		
		JSeparator separator = new JSeparator();
		mntmArchivo.add(separator);
		
		WebMenuItem mntmRespaldar = new WebMenuItem("Respaldar");
		mntmArchivo.add(mntmRespaldar);
		
		WebMenuItem mntmImportar = new WebMenuItem("Importar");
		mntmArchivo.add(mntmImportar);
		
		WebMenuItem mntmExportar = new WebMenuItem("Exportar");
		mntmArchivo.add(mntmExportar);
		
		JSeparator separator_1 = new JSeparator();
		mntmArchivo.add(separator_1);
		
		WebMenuItem mntmSalirM = new WebMenuItem("Salir");
		mntmSalirM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.dataToController("close");
			}
		});
		mntmArchivo.add(mntmSalirM);
		
		WebMenu mnInventarios = new WebMenu("Inventarios");
		menuBar.add(mnInventarios);
		
		WebMenuItem mntmConsultar = new WebMenuItem("Consultar");
		mnInventarios.add(mntmConsultar);
		
		WebMenuItem mntmCcargar = new WebMenuItem("Cargar");
		mnInventarios.add(mntmCcargar);
		
		WebMenuItem mntmCongelarExistencia = new WebMenuItem("Congelar existencia");
		mnInventarios.add(mntmCongelarExistencia);
		
		WebMenu mnReportes = new WebMenu("Reportes");
		menuBar.add(mnReportes);
		
		WebMenuItem mntmGenerar = new WebMenuItem("Generar");
		mnReportes.add(mntmGenerar);
		
		WebMenuItem mntmGuardarYEnviar = new WebMenuItem("Guardar y enviar");
		mnReportes.add(mntmGuardarYEnviar);
		
		WebMenu mnCatalogos = new WebMenu("Catalogos");
		menuBar.add(mnCatalogos);
		
		WebMenuItem mntmClientes = new WebMenuItem("Clientes");
		mnCatalogos.add(mntmClientes);
		
		WebMenuItem mntmProveedores = new WebMenuItem("Proveedores");
		mnCatalogos.add(mntmProveedores);
		
		WebMenuItem mntmCajeros = new WebMenuItem("Cajeros");
		mnCatalogos.add(mntmCajeros);
		
		JSeparator separator_5 = new JSeparator();
		mnCatalogos.add(separator_5);
		
		WebMenuItem mntmProductos = new WebMenuItem("Productos");
		mnCatalogos.add(mntmProductos);
		
		WebMenuItem mntmDepartamentos = new WebMenuItem("Departamentos");
		mnCatalogos.add(mntmDepartamentos);
		
		WebMenuItem mntmServicios = new WebMenuItem("Servicios");
		mnCatalogos.add(mntmServicios);
		
		JSeparator separator_2 = new JSeparator();
		mnCatalogos.add(separator_2);
		
		WebMenuItem mntmPromociones = new WebMenuItem("Promociones");
		mnCatalogos.add(mntmPromociones);
		
		WebMenuItem mntmRestriccionesDeVenta = new WebMenuItem("Restricciones de venta");
		mnCatalogos.add(mntmRestriccionesDeVenta);
		
		WebMenu mnConfiguracion = new WebMenu("Configuracion");
		menuBar.add(mnConfiguracion);
		
		WebMenuItem mntmDispositivos = new WebMenuItem("Dispositivos");
		mnConfiguracion.add(mntmDispositivos);
		
		WebMenu mnTicket = new WebMenu("Ticket");
		mnConfiguracion.add(mnTicket);
		
		WebMenuItem mntmDeCompra = new WebMenuItem("De compra");
		mnTicket.add(mntmDeCompra);
		
		WebMenuItem mntmDevolucion = new WebMenuItem("Devoluci\u00F3n");
		mnTicket.add(mntmDevolucion);
		
		WebMenuItem mntmCancelacin = new WebMenuItem("Cancelaci\u00F3n");
		mnTicket.add(mntmCancelacin);
		
		WebMenuItem mntmDeCorte = new WebMenuItem("De corte");
		mnTicket.add(mntmDeCorte);
		
		WebMenuItem mntmPersonalizado = new WebMenuItem("Personalizado");
		mnTicket.add(mntmPersonalizado);
		
		JSeparator separator_3 = new JSeparator();
		mnConfiguracion.add(separator_3);
		
		WebMenuItem mntmFormasDePago = new WebMenuItem("Formas de pago");
		mnConfiguracion.add(mntmFormasDePago);
		
		WebMenuItem mntmPrecioDelDolar = new WebMenuItem("Precio del dolar");
		mnConfiguracion.add(mntmPrecioDelDolar);
		
		WebMenuItem mntmUnidadesDeMedida = new WebMenuItem("Unidades de medida y peso");
		mnConfiguracion.add(mntmUnidadesDeMedida);
		
		JSeparator separator_4 = new JSeparator();
		mnConfiguracion.add(separator_4);
		
		WebMenuItem mntmImpuestos = new WebMenuItem("Impuestos");
		mntmImpuestos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				impuesto();
			}
		});
		mnConfiguracion.add(mntmImpuestos);
		
		WebMenu mnUtilerias = new WebMenu("Utilerias");
		menuBar.add(mnUtilerias);
		
		WebMenuItem mntmCalculoImpuestos = new WebMenuItem("Calculo impuestos");
		mnUtilerias.add(mntmCalculoImpuestos);
		
		WebMenuItem mntmDolarHoy = new WebMenuItem("Dolar Hoy");
		mntmDolarHoy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showDolar();
			}
		});
		mnUtilerias.add(mntmDolarHoy);
		
		WebMenuItem mntmImpresinCdigoDe = new WebMenuItem("Impresi\u00F3n c\u00F3digo de barras");
		mnUtilerias.add(mntmImpresinCdigoDe);
		
		WebMenu mntmAyuda = new WebMenu("Ayuda");
		menuBar.add(mntmAyuda);
		
		WebMenuItem mntmSoporteTcnico = new WebMenuItem("Soporte t\u00E9cnico");
		mntmAyuda.add(mntmSoporteTcnico);
		
		WebMenuItem mntmManualUsuario = new WebMenuItem("Manual usuario");
		mntmAyuda.add(mntmManualUsuario);
		
		WebMenuItem mntmAcercaDe = new WebMenuItem("Acerca de");
		mntmAyuda.add(mntmAcercaDe);
		
		contentPane = new WebPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		desktopPane = new WebDesktopPane();
		desktopPane.setOpaque ( false );
		contentPane.add(desktopPane);
		
		this.setControlador(cont);
		//Create the mouse adapter to icons and pass the dbManager
		//DesktopPaneIconMoveAdapte ma1 = new DesktopPaneIconMoveAdapte(controlador.getManager());
		
		WebButton usersButton = new WebButton("Usuarios");
		usersButton.setIcon(new ImageIcon(Administrador.class.getResource("/vista/recursos/group49.png")));
		usersButton.setHorizontalTextPosition(SwingConstants.CENTER);
		usersButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		usersButton.setRolloverDecoratedOnly(true);
		usersButton.setBounds(25, 125, 100, 75);
		usersButton.addMouseListener((MouseListener) this.controlador);
		usersButton.addMouseMotionListener((MouseMotionListener) this.controlador);
		desktopPane.add(usersButton);
		
		WebButton productsButton = new WebButton("Usuarios");
		productsButton.setIcon(new ImageIcon(Administrador.class.getResource("/vista/recursos/compras3.png")));
		productsButton.setText("Productos");
		productsButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		productsButton.setRolloverDecoratedOnly(true);
		productsButton.setHorizontalTextPosition(SwingConstants.CENTER);
		productsButton.setBounds(150, 125, 100, 75);
		desktopPane.add(productsButton);
		
		WebButton providersButton = new WebButton("Usuarios");
		providersButton.setIcon(new ImageIcon(Administrador.class.getResource("/vista/recursos/person101.png")));
		providersButton.setText("Proveedores");
		providersButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		providersButton.setRolloverDecoratedOnly(true);
		providersButton.setHorizontalTextPosition(SwingConstants.CENTER);
		providersButton.setBounds(25, 325, 100, 75);
		providersButton.addMouseListener((MouseListener) controlador);
		providersButton.addMouseMotionListener((MouseMotionListener) controlador);
		desktopPane.add(providersButton);
		
		WebButton clientsButton = new WebButton("Clientes");
		clientsButton.setIcon(new ImageIcon(Administrador.class.getResource("/vista/recursos/client.png")));
		clientsButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		clientsButton.setRolloverDecoratedOnly(true);
		clientsButton.setHorizontalTextPosition(SwingConstants.CENTER);
		clientsButton.setBounds(150, 225, 100, 75);
		clientsButton.addMouseListener((MouseListener) controlador);
		clientsButton.addMouseMotionListener((MouseMotionListener) controlador);
		desktopPane.add(clientsButton);
		
		WebButton reportsButton = new WebButton("Usuarios");
		reportsButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		reportsButton.setText("Reportes");
		reportsButton.setRolloverDecoratedOnly(true);
		reportsButton.setHorizontalTextPosition(SwingConstants.CENTER);
		reportsButton.setBounds(275, 125, 100, 75);
		desktopPane.add(reportsButton);
		
		WebButton departamentsButton = new WebButton("Usuarios");
		departamentsButton.setIcon(new ImageIcon(Administrador.class.getResource("/vista/recursos/depa.png")));
		departamentsButton.setToolTipText("Departamentos");
		departamentsButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		departamentsButton.setText("Dptos");
		departamentsButton.setRolloverDecoratedOnly(true);
		departamentsButton.setHorizontalTextPosition(SwingConstants.CENTER);
		departamentsButton.setBounds(275, 225, 100, 75);
		departamentsButton.addMouseListener((MouseListener) controlador);
		departamentsButton.addMouseMotionListener((MouseMotionListener) controlador);
		desktopPane.add(departamentsButton);
		
		WebButton taxButton = new WebButton("Impuestos");
		taxButton.setIcon(new ImageIcon(Administrador.class.getResource("/vista/recursos/tax.png")));
		taxButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		taxButton.setRolloverDecoratedOnly(true);
		taxButton.setHorizontalTextPosition(SwingConstants.CENTER);
		taxButton.setBounds(25, 225, 100, 75);
		taxButton.addMouseListener((MouseListener) controlador);
		taxButton.addMouseMotionListener((MouseMotionListener) controlador);
		desktopPane.add(taxButton);
		
		WebButton cutButton = new WebButton("Usuarios");
		cutButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		cutButton.setText("Corte");
		cutButton.setRolloverDecoratedOnly(true);
		cutButton.setHorizontalTextPosition(SwingConstants.CENTER);
		cutButton.setBounds(150, 325, 100, 75);
		desktopPane.add(cutButton);
		
		WebButton bckupButton = new WebButton("Respaldo");
		bckupButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		bckupButton.setRolloverDecoratedOnly(true);
		bckupButton.setHorizontalTextPosition(SwingConstants.CENTER);
		bckupButton.setBounds(275, 325, 100, 75);
		//bckupButton.addMouseListener(ma1);
		//bckupButton.addMouseMotionListener(ma1);
		desktopPane.add(bckupButton);
		
        // Memory bar that displays allocated and used memory
        WebMemoryBar memoryBar2 = new WebMemoryBar ();
        memoryBar2.setShowMaximumMemory ( false );
        memoryBar2.setPreferredWidth ( memoryBar2.getPreferredSize ().width + 20 );
        contentPane.add(memoryBar2, BorderLayout.SOUTH);
		
		/*Efectos visuales*/
		setLocationRelativeTo(null);
	}
	
	public void setControlador(controlador.Controlador cont) {
		this.controlador = cont;
	}
	
	public void showDolar() {
		new Thread(new Runnable(){
			public void run() {
				NotificationManager.setLocation(NotificationManager.NORTH_EAST);
				NotificationManager.setMargin(20);
				try {
					NotificationManager.showNotification ( "<html><b>El dolar Hoy:</b> " + new Dolar().getDolar() + "<br>" + "<i>Proveido por Google</i></html>");
				} catch (IOException e) {
					NotificationManager.showNotification ( "<html><font color='red'>Error al obtener el dolar</font></html>" );
				}
			}
		}).start();
	}
	
	public void impuesto() {
		controlador.Impuesto imp = new controlador.Impuesto(desktopPane);
		imp.setManager(controlador.getManager());
		imp.updateTable();
	}
}
