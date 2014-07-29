/**
 * This class is the controler for window administrator and control the mousemotion and mousemotionlistener
 */
package controlador;

import java.awt.Container;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.sql.SQLException;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import com.alee.laf.button.WebButton;
import com.alee.laf.desktoppane.WebDesktopPane;

public class Administrador extends Controlador implements MouseListener, MouseMotionListener{
    public static final String DRAGGED_MARK = "was.dragged";

    private boolean dragging = false;
    private Point startPoint = null;
    private Rectangle startBounds = null;
	
	public Administrador(DataBaseManager db, String emp) {
		this.manager = db;
		vista.Administrador admin = new vista.Administrador(this,emp);
		admin.setLocationRelativeTo(null);
		admin.setVisible(true);
	}

	@Override
	public void iniAction(String... args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dataToController(String... args) {
		switch(args[0]) {
			case "close":
				try {
					System.out.print(this.manager.saveAndClose());
					System.exit(1);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case "impuesto":
				break;
		}
		
		
	}

	@Override
	public void dataToView(String... args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent evt) {
		// TODO Auto-generated method stub
    	if(evt.getClickCount() >= 1) {
	    	WebButton x = (WebButton)evt.getSource();
	    	switch(x.getText()) {
	    	case "Usuarios":
				Usuario usr = new Usuario((WebDesktopPane)x.getParent());
				usr.setManager(this.manager);
				usr.updateTable();
	    		break;
	    	case "Impuestos":
	    		Impuesto imp = new Impuesto((WebDesktopPane)x.getParent());
	    		imp.setManager(this.manager);
	    		imp.updateTable();
	    		break;
	    	case "Clientes":
	    		Cliente cl = new Cliente((WebDesktopPane)x.getParent());
	    		cl.setManager(this.manager);
	    		cl.updateTable();
	    		break;
	    	case "Proveedores":
	    		Proveedor prov = new Proveedor((WebDesktopPane)x.getParent());
	    		prov.setManager(this.manager);
	    		prov.updateTable();
	    		break;
	    	case "Dptos":
	    		Departamento dep = new Departamento((WebDesktopPane)x.getParent());
	    		dep.setManager(this.manager);
	    		dep.updateTable();
	    		break;
	    	case "Productos":
	    		Producto prod = new Producto((WebDesktopPane)x.getParent(),this.manager);
	    		prod.updateTable();
	    		break;
	    	}
    	}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
    public void mousePressed ( MouseEvent e )
    {
        if ( SwingUtilities.isLeftMouseButton ( e ) )
        {
            dragging = true;
            startPoint = e.getLocationOnScreen ();
            startBounds = e.getComponent ().getBounds ();
        }
    }

    @Override
    public void mouseDragged ( MouseEvent e )
    {
        if ( dragging )
        {
            e.getComponent ().setBounds ( new Rectangle ( startBounds.x + e.getLocationOnScreen ().x - startPoint.x,
                    startBounds.y + e.getLocationOnScreen ().y - startPoint.y, startBounds.width, startBounds.height ) );
            if ( e.getComponent () instanceof JComponent )
            {
                ( ( JComponent ) e.getComponent () ).putClientProperty ( DRAGGED_MARK, true );
            }
        }
    }

    @Override
    public void mouseReleased ( MouseEvent e )
    {
        if ( SwingUtilities.isLeftMouseButton ( e ) && dragging )
        {
            Rectangle bounds = e.getComponent ().getBounds ();

            Container parent = e.getComponent ().getParent ();
            boolean setBounds = false;
            for ( int i = 25; i < parent.getWidth (); i += 125 )
            {
                for ( int j = 25; j < parent.getHeight (); j += 100 )
                {
                    Rectangle cell = new Rectangle ( i, j, 100, 75 );
                    if ( cell.intersects ( bounds ) )
                    {
                        Rectangle intersection = cell.intersection ( bounds );
                        if ( intersection.width * intersection.height >= bounds.width * bounds.height / 8 )
                        {
                            e.getComponent ().setBounds ( cell );
                            setBounds = true;
                            break;
                        }
                    }
                }
                if ( setBounds )
                {
                    break;
                }
            }

            if ( e.getComponent () instanceof JComponent )
            {
                ( ( JComponent ) e.getComponent () ).putClientProperty ( DRAGGED_MARK, null );
            }
            dragging = false;
        }
    }

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}	



}
