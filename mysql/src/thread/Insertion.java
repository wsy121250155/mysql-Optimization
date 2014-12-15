package thread;

import optimization.Dbconnect;
import optimization.InsertOneMillion;

public class Insertion extends Thread{
	public void run(){
		Dbconnect db=new Dbconnect();
		InsertOneMillion i=new InsertOneMillion(db.getConnection());
		i.insert2();
	}
}
