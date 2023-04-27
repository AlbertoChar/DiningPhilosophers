package FilosofiACena;

import java.util.concurrent.Semaphore;

public class FilosofiACena {
	
	static Semaphore[] fork = new Semaphore[5];
	


	class Philosopher extends Thread {
		
		int i;
		
		Philosopher(int i) {
			this.i = i;
		}
		
		public void run() {
			for( int j = 0; j < 10; j++) {
				//think
				try {
					fork[i].acquire();
				System.out.println("Philosopher " + i + " takes fork " + i);
				fork[(i+1) % 5].acquire();
				System.out.println("Philosopher " + i + " takes fork " + (i+1)%5);
				} catch (InterruptedException e) {
				}
				//eat
				System.out.println("Philosopher " + i + " is eating");
				fork[i].release();
				System.out.println("Philosopher " + i + " releases fork " + i);
				fork[(i+1)%5].release();
				System.out.println("Philosopher " + i + " releases fork " + (i+1)%5);
			}
		}
	}
	
	class PhilosopherReversed extends Thread {
		
		int i;
		
		PhilosopherReversed(int i){
			this.i = i;
		}
		
		public void run() {
			for( int j = 0; j < 10; j++) {
				//think
				try {
					fork[(i+1)%5].acquire();
				System.out.println("Philosopher " + i + " takes fork " + (i+1)%5);
				fork[i].acquire();
				System.out.println("Philosopher " + i + " takes fork " + i);
				} catch (InterruptedException e) {
				}
				//eat
				System.out.println("Philosopher " + i + " is eating");
				fork[(i+1)%5].release();
				System.out.println("Philosopher " + i + " releases fork " + (i+1)%5);
				fork[i].release();
				System.out.println("Philosopher " + i + " releases fork " + i);
			}
		}
	}
	
	
	FilosofiACena(){
		for(int i = 0; i < 5; i++) {
			fork[i] = new Semaphore(1);
		}
		Philosopher P0 = new Philosopher(0);
		Philosopher P1 = new Philosopher(1);
		Philosopher P2 = new Philosopher(2);
		Philosopher P3 = new Philosopher(3);
		PhilosopherReversed P4 = new PhilosopherReversed(4);
		
		P0.start();
		P1.start();
		P2.start();
		P3.start();
		P4.start();
		
		try {
			P0.join();
			P1.join();
			P3.join();
			P4.join();
		} catch (InterruptedException e) {	
		};
		System.out.println("Program terminated correctly!");
	}
	
	public static void main(String[] args) {
		
		new FilosofiACena();
		
	}
	
}
