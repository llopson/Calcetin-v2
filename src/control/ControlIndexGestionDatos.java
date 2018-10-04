package control;

import javax.swing.*;
import modelo.*;
import vista.*;

public class ControlIndexGestionDatos {
	private PanelGestionTablas elmarco;
	private ModeloGestionDatos menu=new ModeloGestionDatos();
	private Object[] nombreTablas;
	private JComboBox<String> campos;
	
	public ControlIndexGestionDatos(PanelGestionTablas elmarco) {
		this.elmarco=elmarco;
	}
	
	public void cargarIndex() {
		campos=elmarco.getTablas();
		if(nombreTablas!=null) {
			campos.removeAllItems();
		}
		
		nombreTablas=menu.darNombreTablas();
		for(int i=0;i<nombreTablas.length;i++) {
			campos.setSelectedIndex(-1);
			campos.addItem((String) nombreTablas[i]);
		}
	}


	
}
