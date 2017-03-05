import java.util.ArrayList;
import java.util.Random;



	public class Worker {
		private static ArrayList list1 = new ArrayList();
		private static ArrayList list2 = new ArrayList();
		Random r = new Random();
		Object obj1 = new Object();
		Object obj2 = new Object();
		private void partOne(){
			synchronized (obj1){
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			list1.add(r.nextInt(100));
			}
			
		}
		private void partTwo(){
			synchronized(obj2){
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			list2.add(r.nextInt(100));	
			}
			
		}
		private void proceed(){
		for (int i=0;i<1000;i++){
			partOne();
			partTwo();
		}
		}
		public void start(){
			System.out.println("Начинаем ...");
		long startTime = System.currentTimeMillis();
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
			proceed();
				
			}			
		});
		Thread t2 = new Thread(new Runnable(){

			@Override
			public void run() {
			proceed();
				
			}			
		});
		t1.start();
		t2.start();
		
			try {
				t1.join();
				t2.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
		long endTime = System.currentTimeMillis();
		System.out.println("Время операций: " + (endTime - startTime) + "\n" 
							+ "Лист 1: " + list1.size() + "\n" 
							+ "Лист 2: " + list2.size() + "\n");
		}
		
	}