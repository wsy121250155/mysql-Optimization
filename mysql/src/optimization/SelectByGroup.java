package optimization;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import output.Output;

public class SelectByGroup {
	private Connection connection;
	public SelectByGroup(Connection c){
		connection=c;
	}
	public void selectCol22(){
		//步骤六：找出所有第二列以ab开头的记录，记录执行时间
//		PreparedStatement pst;
		try {
//			 col2是随机值时，在col2上建立的索引属于非聚集索引，而当col2是按照一定的顺序存放时，
//			  在col2上建立的索引属于聚集索引。对于聚集数据，当查询时，通过索引查找到数据在磁盘中的位置，
//			  因为其的聚集特性，可以从这个位置开始顺序的读取大量的符合要求的信息。但是非聚集则不然，
//			  非聚集索引查询到的结果分布在磁盘的各个地方，需要频繁的移动磁头才能够获取这些信息，自然查找所需的时间就较长。
			 Statement statement=(Statement)connection.createStatement();
//			 String sql="select * from test where col2 like 'ab%'";
			 Long startTime=System.currentTimeMillis();
//			 ResultSet resultSet=statement.executeQuery(sql);
			 Long endTime=System.currentTimeMillis();
			 System.out.println("without index on test(col2):\t"+(endTime-startTime)/1000.0+"s");//1.661s
//			 StringBuffer sb=new StringBuffer();
//			 sb.append("step6:\t without index on test(col2)\t"+(endTime-startTime)/1000.0+"s\n");
//			 while(resultSet.next()){
////				 System.out.print("\t"+resultSet.getInt(1));
//				 sb.append(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getString(3)+"\t"+resultSet.getInt(4)+"\n");
//			 }
//			 Output.getInstance().write(sb.toString());
			 statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void selectCol22_2(){
		//步骤六：找出所有第二列以ab开头的记录，记录执行时间
		PreparedStatement pst;
		try {
//			 col2是随机值时，在col2上建立的索引属于非聚集索引，而当col2是按照一定的顺序存放时，
//			  在col2上建立的索引属于聚集索引。对于聚集数据，当查询时，通过索引查找到数据在磁盘中的位置，
//			  因为其的聚集特性，可以从这个位置开始顺序的读取大量的符合要求的信息。但是非聚集则不然，
//			  非聚集索引查询到的结果分布在磁盘的各个地方，需要频繁的移动磁头才能够获取这些信息，自然查找所需的时间就较长。
			
			 pst=connection.prepareStatement("create index indcol2 on test(col2)");
			 pst.execute();
			 pst.close();
			 
			 Statement statement=(Statement)connection.createStatement();
			 String sql="select * from test where col2 like 'ab%'";
			 Long startTime=System.currentTimeMillis();
			 ResultSet resultSet=statement.executeQuery(sql);
			 Long endTime=System.currentTimeMillis();
			 System.out.println("with index on test(col2):\t"+(endTime-startTime)/1000.0+"s");//1.181s
//			 StringBuffer sb=new StringBuffer();
//			 sb.append("step6:\t with index on test(col2)\t"+(endTime-startTime)/1000.0+"s\n");
//			 while(resultSet.next()){
////				 System.out.print("\t"+resultSet.getInt(1));
//				 sb.append(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getString(3)+"\t"+resultSet.getInt(4)+"\n");
//			 }
//			 Output.getInstance().write(sb.toString());
			 statement.close();
			 
			 pst= connection.prepareStatement("drop index indcol2 on test");
			 pst.execute();
			 pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void selectCol2(){
		//步骤五：找出所有第二列包含ab的记录，记录执行时间
//		PreparedStatement pst;
		try {	 
			 Statement statement=(Statement)connection.createStatement();
			 String sql="select * from test where col2 like '%ab%'";
			 Long startTime=System.currentTimeMillis();
			 ResultSet resultSet=statement.executeQuery(sql);
			 Long endTime=System.currentTimeMillis();
			 System.out.println("without index on test(col2):\t"+(endTime-startTime)/1000.0+"s");//2.564s
			 StringBuffer sb=new StringBuffer();
			 sb.append("step5:\t without index on test(col2)\t"+(endTime-startTime)/1000.0+"s\n");
			 while(resultSet.next()){
//				 System.out.print("\t"+resultSet.getInt(1));
				 sb.append(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getString(3)+"\t"+resultSet.getInt(4)+"\n");
			 }
			 Output.getInstance().write(sb.toString());
			 statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void selectCol2_2(){
		//步骤五：找出所有第二列包含ab的记录，记录执行时间
		PreparedStatement pst;
		try {	 
			 pst=connection.prepareStatement("create index indcol2 on test(col2)");
			 pst.execute();
			 pst.close();
			 
			 Statement statement=(Statement)connection.createStatement();
			 String sql="select * from test where col2 like '%ab%'";
			 Long startTime=System.currentTimeMillis();
			 ResultSet resultSet=statement.executeQuery(sql);
			 Long endTime=System.currentTimeMillis();
			 System.out.println("with index on test(col2):\t"+(endTime-startTime)/1000.0+"s");//3.019s
			 StringBuffer sb=new StringBuffer();
			 sb.append("step5:\t with index on test(col2)\t"+(endTime-startTime)/1000.0+"s\n");
			 while(resultSet.next()){
//				 System.out.print("\t"+resultSet.getInt(1));
				 sb.append(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getString(3)+"\t"+resultSet.getInt(4)+"\n");
			 }
			 Output.getInstance().write(sb.toString());
			 statement.close();
			 pst= connection.prepareStatement("drop index indcol2 on test");
			 pst.execute();
			 pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void selectCol3(){
		//步骤四：找出所有col3不等于10的记录，记录执行时间
		PreparedStatement pst;
		try {			 
			 Statement statement=(Statement)connection.createStatement();
			 String sql="select * from test where col3!=10";
			 Long startTime=System.currentTimeMillis();
			 ResultSet resultSet=statement.executeQuery(sql);
			 Long endTime=System.currentTimeMillis();
			 System.out.println("do the selection without inserting index:\t"+(endTime-startTime)/1000.0+"s");//9.653s
//			 StringBuffer sb=new StringBuffer();
//			 sb.append("step4:\t without index on test(col3)\t"+(endTime-startTime)/1000.0+"s\n");
//			 while(resultSet.next()){
//				 System.out.print("\t"+resultSet.getInt(1));
//				 sb.append(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getString(3)+"\t"+resultSet.getInt(4)+"\n");
//			 }
//			 Output.getInstance().write(sb.toString());
			 statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void selectCol3_2(){
		//步骤四：找出所有col3不等于10的记录，记录执行时间
		PreparedStatement pst;
		try {
			 pst=connection.prepareStatement("create index indcol3 on test(col3)");
			 pst.execute();
			 pst.close();
 
			 Statement statement=(Statement)connection.createStatement();
			 String sql="select * from test where col3!=10";
			 Long startTime=System.currentTimeMillis();
			 ResultSet resultSet=statement.executeQuery(sql);
			 Long endTime=System.currentTimeMillis();
			 System.out.println("after inserting an index on test(col3):\t"+(endTime-startTime)/1000.0+"s");//30.866s
//			 StringBuffer sb=new StringBuffer();
//			 sb.append("step4:\t with index on test(col3)\t"+(endTime-startTime)/1000.0+"s\n");
//			 while(resultSet.next()){
//				 System.out.print("\t"+resultSet.getInt(1));
//				 sb.append(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getString(3)+"\t"+resultSet.getInt(4)+"\n");
//			 }
//			 Output.getInstance().write(sb.toString());
			 statement.close();
			 
			 pst= connection.prepareStatement("drop index indcol3 on test");
			 pst.execute();
			 pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void selectCol1(){
		//步骤三:执行 select count(*) from test where col3>=5 group by col1 order by count(*); 记录执行时间
		PreparedStatement pst;
		try {
			 pst=connection.prepareStatement("create index indcol on test(col1)");
			 pst.execute();
			 pst.close();
			 
			 Statement statement=(Statement)connection.createStatement();
			 String sql="select count(*) from test group by col1 order by count(*)";
			 Long startTime=System.currentTimeMillis();
			 ResultSet resultSet=statement.executeQuery(sql);
			 Long endTime=System.currentTimeMillis();
			 System.out.println("after insert an index on test(col1):\t"+(endTime-startTime)/1000.0+"s");//1.094s
			 StringBuffer sb=new StringBuffer();
			 sb.append("step3:\twith index on test(col1)\t"+(endTime-startTime)/1000.0+"s\n");
//			 while(resultSet.next()){
//				 System.out.println(resultSet.getInt(1));
//				 sb.append(resultSet.getInt(1)+"\n");
//			 }
			 Output.getInstance().write(sb.toString());
			 statement.close();
			 
			 pst= connection.prepareStatement("drop index indcol on test");
			 pst.execute();
			 pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void selectCol1_2(){
		//步骤三:执行 select count(*) from test where col3>=5 group by col1 order by count(*); 记录执行时间
		PreparedStatement pst;
		try {
			 Statement statement=(Statement)connection.createStatement();
			 String sql="select count(*) from test group by col1 order by count(*)";
			 Long startTime=System.currentTimeMillis();
			 ResultSet resultSet=statement.executeQuery(sql);
			 Long endTime=System.currentTimeMillis();
			 System.out.println("do the select without inserting an index:\t"+(endTime-startTime)/1000.0+"s");//2.697s
			 StringBuffer sb=new StringBuffer();
			 sb.append("step3:\twithout index\t"+(endTime-startTime)/1000.0+"s\n");
//			 while(resultSet.next()){
//				 sb.append(resultSet.getInt(1)+"\n");
//				 System.out.println(resultSet.getInt(1));
//			 }
			 Output.getInstance().write(sb.toString());
			 statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
