package controller;

import java.util.concurrent.Semaphore;

public class ThreadIngresso extends Thread {

	private int idThreadComprador;

	private static int totalIngresso = 100;

	private Semaphore semaforo;

	public ThreadIngresso(int idThreadComprador, Semaphore semaforo) {

		this.idThreadComprador = idThreadComprador;
		this.semaforo = semaforo;

	}

	@Override
	public void run() {

		validarOperacao();
		

	}

	private void validarOperacao() {
		int tempoProcessoLogin = (int) ((Math.random() * 1951) + 50);

		if (tempoProcessoLogin > 1000) {
			try {
				sleep(tempoProcessoLogin);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("O comprador #" + idThreadComprador + " ultrapassou tempo de login em "
					+ (tempoProcessoLogin - 1000) + ".ms");

		} else if (tempoProcessoLogin <= 1000) {
			int tempoProcessoCompra = (int) ((Math.random() * 2001) + 1000);

			if (tempoProcessoCompra > 2500) {
				System.out
						.println("O comprador #" + idThreadComprador + " ultrapassou o tempo de processo de compra");
				try {
					sleep(tempoProcessoCompra);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				
				
				//sessão crítica aqui//
				
				try {
					semaforo.acquire();
					int qtdeIngresso = (int) ((Math.random() * 3.1) + 1);
					
					if (qtdeIngresso > totalIngresso) {
						
						System.out.println("A quantidade de interesse do comprador #"+idThreadComprador+" está indisponível");
						System.out.println("Comprador #"+idThreadComprador+" teve a sessão encerrada");}
						else{
							totalIngresso -=qtdeIngresso;
							System.out.println("Comprador #"+idThreadComprador+"concluir a compra de "+qtdeIngresso);
							System.out.println("A QUANTIDADE DE INGRESSO DISPONÍVEL AINDA É "+totalIngresso);
						}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally { // linha que ocorre ao final do try ou do catch
					semaforo.release();
				

				}
				

			}
		}
		
	}

	
}

	