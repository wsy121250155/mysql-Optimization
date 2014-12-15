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
		//���������ҳ����еڶ�����ab��ͷ�ļ�¼����¼ִ��ʱ��
//		PreparedStatement pst;
		try {
//			 col2�����ֵʱ����col2�Ͻ������������ڷǾۼ�����������col2�ǰ���һ����˳����ʱ��
//			  ��col2�Ͻ������������ھۼ����������ھۼ����ݣ�����ѯʱ��ͨ���������ҵ������ڴ����е�λ�ã�
//			  ��Ϊ��ľۼ����ԣ����Դ����λ�ÿ�ʼ˳��Ķ�ȡ�����ķ���Ҫ�����Ϣ�����ǷǾۼ���Ȼ��
//			  �Ǿۼ�������ѯ���Ľ���ֲ��ڴ��̵ĸ����ط�����ҪƵ�����ƶ���ͷ���ܹ���ȡ��Щ��Ϣ����Ȼ���������ʱ��ͽϳ���
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
		//���������ҳ����еڶ�����ab��ͷ�ļ�¼����¼ִ��ʱ��
		PreparedStatement pst;
		try {
//			 col2�����ֵʱ����col2�Ͻ������������ڷǾۼ�����������col2�ǰ���һ����˳����ʱ��
//			  ��col2�Ͻ������������ھۼ����������ھۼ����ݣ�����ѯʱ��ͨ���������ҵ������ڴ����е�λ�ã�
//			  ��Ϊ��ľۼ����ԣ����Դ����λ�ÿ�ʼ˳��Ķ�ȡ�����ķ���Ҫ�����Ϣ�����ǷǾۼ���Ȼ��
//			  �Ǿۼ�������ѯ���Ľ���ֲ��ڴ��̵ĸ����ط�����ҪƵ�����ƶ���ͷ���ܹ���ȡ��Щ��Ϣ����Ȼ���������ʱ��ͽϳ���
			
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
		//�����壺�ҳ����еڶ��а���ab�ļ�¼����¼ִ��ʱ��
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
		//�����壺�ҳ����еڶ��а���ab�ļ�¼����¼ִ��ʱ��
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
		//�����ģ��ҳ�����col3������10�ļ�¼����¼ִ��ʱ��
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
		//�����ģ��ҳ�����col3������10�ļ�¼����¼ִ��ʱ��
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
		//������:ִ�� select count(*) from test where col3>=5 group by col1 order by count(*); ��¼ִ��ʱ��
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
		//������:ִ�� select count(*) from test where col3>=5 group by col1 order by count(*); ��¼ִ��ʱ��
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
