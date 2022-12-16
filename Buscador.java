package Buscador;

import java.io.*;
import java.util.*;


public class Buscador extends Thread{
	private String ruta, nombreArch;
	public static String salida;
	private File arch;

	public Buscador(){

	}
	public void setRuta(String ruta){
		this.ruta = ruta;

	}
	public String getRuta(){
		return ruta;
	}
	public void setNombreArch(String nombreArch){
		this.nombreArch = nombreArch;

	}
	public String getNombreArch(){
		return nombreArch;
	}

	public void setSalida(String salida){
		this.salida = salida;

	}
	public String getSalida(){
		return salida;
	}


	public void validaDirec(){
		arch = new File(ruta);

		if (!arch.exists()){
			return;
		}
		if(arch.isFile()){
			arch = new File(arch.getAbsolutePath());

			buscar(arch);

		}
	}
	public void run(){
		validaDirec();
		//String nom=Thread.getName();
		String nom= getName();
		File nuevo = new File(nom);
		buscar(nuevo);
	}
	public void buscar(File ruta ){
		String res;
		File[] direc = ruta.listFiles();
		for(File act : direc){
			System.out.println(act.getAbsolutePath());
			if(act.isDirectory()){
				try{
					Buscador hilo = new Buscador();
					hilo.setRuta(act.getAbsolutePath());
					hilo.setNombreArch(nombreArch);
					hilo.start();
					hilo.join();

				}
				catch(InterruptedException ex){
					System.out.println("Error hilo");

				}
			}else {
				if(nombreArch.equals(act.getName())){
					System.out.println("\nENCONTRADO: " +Thread.currentThread().getName()+"\n");
					salida += act.getAbsolutePath() + "\n";
					System.out.println("\n SALIDA: \n" + salida);

				}
			}
		} 
	}

}