//
// MODELO_CREACION_TABLAS:
/* This class uses CONTROL_CREACION_TABLAS and PANEL_CREACION_TABLAS classes,
 * putting both together and giving them the needed functionality,
 * so that a table with the desired name and data field can be created.*/
//
//
package control;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import modelo.ModeloCreacionTablas;
import vista.PanelCreacionTablas;
import vista.VentanaPrincipal;

public class ControlCreacionTablas implements ActionListener{

    private ModeloCreacionTablas myTable;
    private PanelCreacionTablas myWindow;

    public ControlCreacionTablas(PanelCreacionTablas panel){
        this.myWindow = panel;
        myTable = new ModeloCreacionTablas();
        myWindow.getbuttonCreate().addActionListener(this);
        myWindow.getAddLineButton().addActionListener(this);
        myWindow.getbuttonFinish().addActionListener(this);
        myWindow.getReturnManagTablesButton2().addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == myWindow.getAddLineButton()){
            myWindow.addExtraLine();
        }
        if((e.getSource() == myWindow.getbuttonCreate()) && (!myWindow.getTextFieldTableName().getText().isEmpty())) {
        	if(myTable.existTable(myWindow.getTextFieldTableName().getText())){
                JOptionPane.showMessageDialog(new JPanel(),"Ya existe una tabla con ese nombre");
            }else{
                myWindow.getscroll().setVisible(true);
                myWindow.setLabelsVisibility(true);
                myWindow.getbuttonCreate().setEnabled(false);
                myWindow.getbuttonFinish().setEnabled(true);
                myWindow.getAddLineButton().setEnabled(true);
                myWindow.generateComponents();
            }
        }else if((e.getSource() == myWindow.getbuttonCreate()) && (myWindow.getTextFieldTableName().getText().isEmpty())){
            JOptionPane.showMessageDialog(new JPanel(),"Es necesario introducir un nombre");
        }
        // Boton finish
        if(e.getSource() == myWindow.getbuttonFinish()){
            if(!myTable.existTable(myWindow.getTextFieldTableName().getText())){
                if(checkIfNotEmptyFields()){
                    writeFieldData();
                }else{
                    JOptionPane.showMessageDialog(new JPanel(),"Al menos debes llenar un campo");
                }
            }else{
                JOptionPane.showMessageDialog(new JPanel(),"Ya existe una tabla con ese nombre");
            }
            myWindow.emptyComponents();
            myWindow.getbuttonFinish().setEnabled(false);
            myWindow.getAddLineButton().setEnabled(false);
            myWindow.getscroll().setVisible(false);
            myWindow.setLabelsVisibility(false);
            myWindow.getbuttonCreate().setEnabled(true);
            myWindow.getTextFieldTableName().setText("");
            myWindow.resetArrayOfComponents();
        }
        // Boton return
        if(e.getSource() == myWindow.getReturnManagTablesButton2()){
            myWindow.getbuttonFinish().setEnabled(false);
            myWindow.getAddLineButton().setEnabled(false);
            myWindow.getscroll().setVisible(false);
            myWindow.setLabelsVisibility(false);
            myWindow.getTextFieldTableName().setText("");
            myWindow.getbuttonCreate().setEnabled(true);
            myWindow.emptyComponents();
            myWindow.resetArrayOfComponents();
        }
    }

    @SuppressWarnings("unchecked")
	private void writeFieldData(){
        //Las llaves van primero en el string junto con algun simbolo
        String tableKey = "";
        String tableFields = "";
        String tableIsFine = "";
        String tableIsFineKey = "";
        boolean checkKey = false;
        JCheckBox checkBox = new JCheckBox();
        JTextField textField = new JTextField();
        JComboBox<String> comboBox = new JComboBox<String>();

        for(Component e: myWindow.getArrayComponents()){
            //Comprobamos el checkbox primero
            if(e.getClass() == checkBox.getClass()){
                checkBox = (JCheckBox)e;
                if(checkBox.isSelected()){
                    checkKey = true;
                }
            }
            //Comprobamos textfield segundo
            else if(e.getClass() == textField.getClass()){
                textField = (JTextField) e;
                //Si el JCheckBox estaba seleccionado se añade el texto a tableKey de lo contrario se añade a tableFields
                if((checkKey) && !(textField.getText().isEmpty())){
                    if(!textField.getText().isEmpty()){
                        tableKey = "::" +"<K>"+textField.getText();
                    }
                }else if(!(checkKey) && !(textField.getText().isEmpty())){
                    tableFields = "::" + textField.getText();
                }
            }
            //Por ultimo comprobamos el JComboBox
            else if(e.getClass() == comboBox.getClass()){
                comboBox = (JComboBox<String>) e;
                if(!comboBox.getSelectedItem().equals("Null")){
                    if((checkKey)){
                        tableIsFineKey = tableIsFineKey + tableKey + "::" + comboBox.getSelectedItem().toString();
                        tableKey = "";
                        checkKey = false;
                    }else if(!(checkKey)){
                        tableIsFine = tableIsFine + tableFields + "::" + comboBox.getSelectedItem().toString();
                        tableFields = "";
                    }
                }
            }
        }
        if(!myTable.existTable(myWindow.getTextFieldTableName().getText())) {
            myTable.makeTable(myWindow.getTextFieldTableName().getText());
            myTable.addLine(myWindow.getTextFieldTableName().getText() + tableIsFineKey + tableIsFine, myTable.getMyRoot());
        }
    }

    //Comprueba que los elementos no esten vacios
    private boolean checkIfNotEmptyFields(){
        boolean check = false;
        JTextField test = new JTextField();
        for(Component e: myWindow.getArrayComponents()){
            if(e.getClass() == test.getClass()){
                test = (JTextField) e;
                if(test.getText().isEmpty() == false){
                    check = true;
                }
            }
        }
        return check;
    }
}
