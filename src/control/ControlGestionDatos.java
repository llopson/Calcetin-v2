package control;

import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import modelo.*;
import vista.*;

public class ControlGestionDatos implements ActionListener{
	private PanelGestionTablas elmarco; 
	private ModeloGestionDatos menu=new ModeloGestionDatos();
	private Object[][] datosRegistros;
	private Object[] campos;
	private DefaultTableModel model;
	private String seleccionado = new String();
	
	public ControlGestionDatos(PanelGestionTablas elmarco) {
		this.elmarco=elmarco;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		seleccionado=" ";
		if (elmarco.getTablas().getSelectedItem() != null) {
			seleccionado=(String) elmarco.getTablas().getSelectedItem();
		
			campos=menu.darCamposTabla(seleccionado);
			datosRegistros=menu.darDatosTabla(seleccionado);
			
			model=elmarco.getModel();
			limpiar(model);
	
			for(int i=0;i<campos.length;i++) {
				model.setColumnIdentifiers(campos);
			}
			for(int i=0;i<datosRegistros.length;i++) {
				model.insertRow(i, datosRegistros[i]);
			}
			elmarco.setModel(model);
		}
	}
	
	public void limpiar(DefaultTableModel model) {
	
		model.setColumnCount(0);
		model.setRowCount(0);
		elmarco.setModel(model);
	}
}

