package modelo;

import java.io.*;
import java.util.*;

public class ModeloGestionDatos {
	private Object[][] datosRegistros;
	private String [] datosFila;
	private Object[] campos;
	private Object[] nombreTablas;
	private ArrayList<String[]> arrayDatos=new ArrayList<String[]>();
	private ArrayList<String[]> arrayIndex=new ArrayList<String[]>();
	
	public void importDatosTxt(String seleccionado) {
		File archivo = null;
	    FileReader fr = null;
	    BufferedReader br = null;

	      try {
	         archivo = new File ("src/archivos/txt/"+seleccionado+".txt");
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);
	         arrayDatos.clear();
	         String linea=br.readLine();
	         while(linea!=null) {
	        	 arrayDatos.add(linea.split("::"));
	        	 linea=br.readLine();
	         }
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }finally{
	       try{                    
	            if( null != fr ){   
	               fr.close();
	               br.close();
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }
	}
	public void importIndexTxt() {
		File archivo = null;
	    FileReader fr = null;
	    BufferedReader br = null;

	      try {
	         archivo = new File ("src/archivos/txt/index.txt");
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);
	         arrayIndex.clear();
	         String linea=br.readLine();
	         while(linea!=null) {
	        	 arrayIndex.add(linea.split("::"));
	        	 linea=br.readLine();
	         }
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }finally{
	       try{                    
	            if( null != fr ){   
	               fr.close();
	               br.close();
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }
	    
	  
	}
	public Object[]darNombreTablas (){
		importIndexTxt();
		nombreTablas=new Object[arrayIndex.size()];
			for(int i=0;i<arrayIndex.size();i++) {
				for(int j=0;j<2;j++) {
					if(j==1) {
						nombreTablas[i]=arrayIndex.get(i)[j];
					}
				
				}
			}
		return nombreTablas;
	}
	public Object[]darCamposTabla (String seleccionado){
		importIndexTxt();
		int count=0;
		for(int i=0;i<arrayIndex.size();i++) {
				for(int j=0;j<arrayIndex.get(i).length && arrayIndex.get(i)[1].equals(seleccionado);j++) {
						if(j>1 && j%2==0) {
							count++;
						}
				}
		}
		campos=new Object[count];
		int countDos=0;
		for(int i=0;i<arrayIndex.size();i++) {
			for(int j=0;j<arrayIndex.get(i).length && arrayIndex.get(i)[1].equals(seleccionado);j++) {
					if(j>1 && j%2==0) {
						campos[countDos]=arrayIndex.get(i)[j];
						countDos++;
					}
			}
		}
		return campos;
	}
	public Object[][] darDatosTabla(String seleccionado) {
		importDatosTxt(seleccionado);
		datosRegistros=new Object[arrayDatos.size()][arrayDatos.get(0).length];
		for(int i=0;i<arrayDatos.size();i++) {
			for(int j=0;j<arrayDatos.get(0).length;j++) {
				datosRegistros[i][j]=arrayDatos.get(i)[j];
			}
		}
		
		return datosRegistros;
	}
	public String[] darDatosFila(String seleccionado, int i) {
		importDatosTxt(seleccionado);
		datosFila = new String[arrayDatos.get(i).length];
		for(int x=0;x<arrayDatos.get(i).length;x++) {
			datosFila[x]=arrayDatos.get(i)[x];
		}
		return datosFila;
	}
	

}