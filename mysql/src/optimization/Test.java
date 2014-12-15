package optimization;

public class Test {
	public static void main(String[] args){
		Dbconnect db=new Dbconnect();
//		InsertOneMillion i=new InsertOneMillion(db.getConnection());
//		i.insert2();
		
		//4000 000
		SelectByGroup sbg=new SelectByGroup(db.getConnection());
		sbg.selectCol1();//2.585s
		sbg.selectCol1_2();//28.456s
//		sbg.selectCol3();//33.719s
//		sbg.selectCol3_2();//49.536s
//		sbg.selectCol2();//14.911s
//		sbg.selectCol2_2();//14.83s
		sbg.selectCol22();//30.751s
		sbg.selectCol22_2();//23.416s
	}
}
