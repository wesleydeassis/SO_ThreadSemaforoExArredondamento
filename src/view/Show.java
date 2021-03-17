package view;

import java.util.concurrent.Semaphore;

import controller.ThreadIngresso;

public class Show {

	public static void main(String[] args) {
		
		
		Semaphore semaforo = new Semaphore(1);
		
		for(int idComp=0; idComp<300; idComp++){
			
			Thread tShow = new ThreadIngresso(idComp,semaforo);
			tShow.start();
			
			
		}
	}

}
