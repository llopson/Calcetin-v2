package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import modelo.ModeloGestionDatos;
import vista.PanelGestionTablas;
import vista.PanelModificarTablas;

public class ControlModificarTablas implements ActionListener{
	private PanelModificarTablas newPanelModificarTablas;
	private ModeloGestionDatos newModeloGestionDatos;
	private PanelGestionTablas newPanelGestionTablas;
	private Object [] fields;
	private String [] dataRow;
	private int i;
	private String selectedTable = new String();
	
	public ControlModificarTablas(PanelGestionTablas managPanel) {
		this.newPanelGestionTablas = managPanel;
		newModeloGestionDatos = new ModeloGestionDatos();
	}
	
	public void actionPerformed(ActionEvent e) {
		this.i = getTableData();
		if(i != -1) {
			dataRow = newModeloGestionDatos.darDatosFila(selectedTable, i);
			modificaRegistro();
		}
		else
			JOptionPane.showMessageDialog(newPanelGestionTablas.getPanelBox(), new JLabel("No se han seleccionado datos"),"Error", JOptionPane.ERROR_MESSAGE);
	}
	
	private boolean modificaRegistro() {
		newPanelModificarTablas = new PanelModificarTablas(selectedTable,fields,dataRow);
		return true;
	}
	
	private int getTableData() {
		if (newPanelGestionTablas.getTablas().getSelectedItem() != null) {
			selectedTable=(String) newPanelGestionTablas.getTablas().getSelectedItem();
			fields=newModeloGestionDatos.darCamposTabla(selectedTable);
		}
		return newPanelGestionTablas.getLaTablaDatos().getSelectedRow();
	}
}
