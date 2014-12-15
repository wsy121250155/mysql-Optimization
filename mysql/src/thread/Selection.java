package thread;

import optimization.Dbconnect;
import optimization.SelectByGroup;

public class Selection extends Thread{
	public void run(){
		Dbconnect db=new Dbconnect();
		SelectByGroup sbg=new SelectByGroup(db.getConnection());
		sbg.selectCol1();
		sbg.selectCol1_2();
	}
}
