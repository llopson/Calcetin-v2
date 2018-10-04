package control;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import modelo.*;
import vista.*;

public class ControlGestionDatosEliminar implements ActionListener{
	//ATRIBUTOS
	private JPanel tableDeleteWarningView;
	private PanelGestionTablas elmarco;
	private DefaultTableModel clearModel;
	ModeloGestionDatosEliminar menu=new ModeloGestionDatosEliminar();
	
	//CONSTRUCTOR
	public ControlGestionDatosEliminar(PanelGestionTablas elmarco) {
		this.elmarco=elmarco;
	}
	
	//METODOS
	@Override
	public void actionPerformed(ActionEvent e) {
		
		int answer=JOptionPane.showConfirmDialog(tableDeleteWarningView, new Label("Se procederá a borrar la tabla, está seguro?"), "Borrado de tabla", JOptionPane.YES_NO_OPTION);
		
		if(answer == JOptionPane.YES_OPTION) {
			
		String seleccionado=" ";
		seleccionado=(String) elmarco.getTablas().getSelectedItem();
		menu.deleteTableFile(seleccionado);
		menu.removeTableLineFromIndex(seleccionado);
		menu.removeBlancSpaces();
		
		elmarco.getModel();
		clearModel= elmarco.getModel();
		clearModel.setColumnCount(0);
		clearModel.setRowCount(0);
		elmarco.setModel(clearModel);
		
		//elmarco.getTablas().removeItem(seleccionado);//STANd BY HASTA VER QUÉ HA HECHO DIEGO 
				//PORQUE TENGO QUE ELIMINAR SOLO LO QUE ÉL CARGUE EN SELECCIÓN Y NECESITO VER QUE HA HECHO?
		
		JOptionPane.showMessageDialog(tableDeleteWarningView, new JLabel("Tabla Borrada"),"Borrado de tabla" , JOptionPane.INFORMATION_MESSAGE);
		
		}
		
		else if(answer == JOptionPane.NO_OPTION) {
			JOptionPane.showMessageDialog(tableDeleteWarningView, new JLabel("No se ha borrado la tabla"),"Borrado de tabla" , JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
