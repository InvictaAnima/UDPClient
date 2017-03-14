package udp.client;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		UDPClient t = new UDPClient(1,"localhost",69,7);
		int i=0;
		String d;
		Scanner scan = new Scanner(System.in);	
		
		while(true){
			t.send(""+i);
			i++;
			//d = t.receive();
			
			//if(d!=null){
			//	System.out.println(d);			
			//}	
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
