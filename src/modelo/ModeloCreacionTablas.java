//
// MODELO_CREACION_TABLAS:
/* This class checks if a given table name already exists or not.
 * If given name exists, then the program ends by warning the user that said name is in use.
 * If given name doesn't exists, then the program creates a .txt file with said name. */
//
//
package modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ModeloCreacionTablas {
    private File myFile;
    private File myRoot = new File("src/archivos/txt/index.txt");
    private ArrayList<String> arrayHoldData = new ArrayList<String>();

    //Constructor vacio
    public ModeloCreacionTablas() {
        //  �\_(._.;)_/�
    }

    //Crea una tabla si no existe y la a�ade al registro. De existir te avisa de que ya hay una tabla con ese nombre
    public void makeTable(String nombreTabla) {

        if (!existTable(nombreTabla)) {
            myFile = new File("src/archivos/txt/" + nombreTabla + ".txt");
            try {
                myFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            //System.out.println("Ya existe esta tabla");
        }
    }

    //Comprueba si existe una tabla con ese nombre
    public boolean existTable(String nombreTabla) {
        Scanner myScanner = null;
        boolean myBool = false;

        String[] arrayLineParts;
        String tableName;

        try {
            myScanner = new Scanner(myRoot);

            while (myScanner.hasNextLine()) {
                tableName = myScanner.nextLine();
                arrayLineParts = tableName.split("::");
                tableName = arrayLineParts[1];

                if (tableName.equals(nombreTabla)) {
                    myBool = true;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } finally {
            try {
                myScanner.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return myBool;
    }


    //A�adir nueva linea al registro
    public void addLine(String myData,File rootPath) {
        Scanner myScanner = null;
        String lastLine = null;

        try {
            myScanner = new Scanner(rootPath);

            while (myScanner.hasNextLine()) {
                lastLine = myScanner.nextLine(); //Guarda la siguiente linea
                arrayHoldData.add(lastLine); //Guarda_todo en un ArrayList
            }

            //Escribir una nueva linea
            FileWriter fr = null;
            BufferedWriter br = null;
            //System.out.println("Ultima: " + lastLine); //Prueba

            try {
                fr = new FileWriter(rootPath);
                br = new BufferedWriter(fr);
                for (int i = 0; i < arrayHoldData.size(); i++) {
                    br.write(arrayHoldData.get(i) + System.getProperty("line.separator"));
                }
                lastLine = arrayHoldData.size() + 1 + "::" + myData;
                br.write(lastLine); //Se escribe la nueva linea
                arrayHoldData.add(lastLine); //Se añade la nueva linea al array

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                arrayHoldData.clear();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } finally {
            try {
                if (myScanner != null) {
                    myScanner.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //arrayHoldData.clear();
        }
    }

    public File getMyRoot() {
        return myRoot;
    }

    public ArrayList<String> getData(){ //��Lo necesita Diego??
        return arrayHoldData;
    }
}