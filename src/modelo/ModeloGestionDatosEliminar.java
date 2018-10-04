package modelo;

import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ModeloGestionDatosEliminar {
	private File myFile;
	private File indexRoot = new File("src/archivos/txt/index.txt");
	private File myFields = new File("src/raiz/tableFields.txt");
	
	public void deleteTableFile(String seleccionado) {
		
		try{
			File tableFile = new File("src/archivos/txt/"+seleccionado+".txt");
			boolean deleted = tableFile.delete();
			
			//BORRAR ESTO LUEGO:
			if (!deleted) {System.out.println("NO SE HA BORRADO EL ARCHIVO .txt");}
			else{System.out.println("SE HA BORRADO EL ARCHIVO .txt!!");}

        }
		catch(Exception e){System.out.println(e);}
	}
	
	public void removeTableLineFromIndex(String seleccionado){
        try{
        	BufferedReader indexFile = new BufferedReader(new FileReader("src/archivos/txt/index.txt"));
            String line;
            String input = "";
            	while ((line = indexFile.readLine()) != null) {
                    
                    if (line.contains(seleccionado)){
                        line = "";
                        System.out.println("LINEA BORRADA!!!!!!!!!!!!!");//BORRAR ESTO LUEGO
                    }
                    input += line + '\n';
                }
            	
                FileOutputStream outputFile = new FileOutputStream("src/archivos/txt/index.txt");
                outputFile.write(input.getBytes());
                indexFile.close();
                outputFile.close();
        }
        catch (Exception e){System.out.println("ALGO VA MAL");}//CAMBIAR POR e
    }
	
	public void removeBlancSpaces() {
		Scanner index;
        PrintWriter tempFile;

        try {
        	index = new Scanner(new File("src/archivos/txt/index.txt"));
        	tempFile = new PrintWriter("src/archivos/txt/indextemp.txt");

            while (index.hasNext()) {
                String line = index.nextLine();
                
                if (!line.isEmpty()) {
                	tempFile.write(line);
                	tempFile.write("\n");
                }
            }

            index.close();
            tempFile.close();
        } 
        
        catch (Exception e){System.out.println("HOUSTON WE HAVE A PROBLEM");}//CAMBIAR POR e.printStackTrace();
        
        File newindex = new File("src/archivos/txt/index.txt");
        File newTempFile = new File("src/archivos/txt/indextemp.txt");

        newindex.delete();
        newTempFile.renameTo(newindex);
	}
	
	
}
	
	