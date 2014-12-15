package optimization;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import output.Output;

public class InsertOneMillion {
	//������� ���ղ���һ�жԱ��Ҫ����� 100 ������¼����¼ִ�е�ʱ��
	//�������ṩ�˼���ʵ������Ҫ��ķ���
	private Connection connection;
	public InsertOneMillion(Connection c){
		connection=c;
	}
	@SuppressWarnings("deprecation")
	public void insert2(){
		PreparedStatement statement=null ;
		int seconds=0;
		Date date1;
		Date date2;
		try {
			connection.setAutoCommit(false);
			StringBuffer sqlBuffer=new StringBuffer("insert into test(col1,col2,col3) values");
			sqlBuffer.append("(?,?,?)");
			for(int j=2; j<=10000; j++){
				sqlBuffer.append(",(?,?,?)");
			}
			sqlBuffer.append(";");
			String sql=new String(sqlBuffer);
			statement = connection.prepareStatement(sql);
			RandomData rd=new RandomData();
			for(int i=0; i<100; i++){
				for(int j=0; j<10000; j++){
					statement.setString(3*j+1, rd.randomName());
					statement.setString(3*j+2, rd.randomString());
					statement.setInt(3*j+3, rd.randomInt());
				}
				date1=new Date();
				statement.execute();
				date2=new Date();
				seconds+=(date2.getMinutes()-date1.getMinutes())*60+date2.getSeconds()-date1.getSeconds();
			}
			System.out.println("insert2():\t"+seconds);//33s
			Output.getInstance().write("step2:\t"+seconds+"\n");
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void insert1(){
		//attention:mysql ��֧����������,�����������û��ʵ���������Ż�
		PreparedStatement statement=null ;
		int seconds=0;
		Date date1;
		Date date2;
		try {
			connection.setAutoCommit(false);
			statement = connection.prepareStatement("INSERT INTO test VALUES(?, ?, ?, ?)");
			RandomData rd=new RandomData();
			for(int i=0; i<100; i++){
				for(int j=0; j<10000; j++){
					statement.setString(1, null);
					statement.setString(2, rd.randomName());
					statement.setString(3, rd.randomString());
					statement.setInt(4, rd.randomInt());
					statement.addBatch();
				}
				date1=new Date();
				statement.executeBatch();
				date2=new Date();
				seconds+=(date2.getMinutes()-date1.getMinutes())*60+date2.getSeconds()-date1.getSeconds();
			}
			System.out.println("insert1():\t"+seconds);//214s
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@SuppressWarnings("deprecation")
	public void insert0(){
		PreparedStatement statement=null ;
		int seconds=0;
		Date date1;
		Date date2;
		try {
			connection.setAutoCommit(false);
			statement = connection.prepareStatement("INSERT INTO test VALUES(?, ?, ?, ?)");
			RandomData rd=new RandomData();
			for(int i=0; i<1000000; i++){
				statement.setString(1, null);
				statement.setString(2, rd.randomName());
				statement.setString(3, rd.randomString());
				statement.setInt(4, rd.randomInt());
				statement.addBatch();
			}
			date1=new Date();
			statement.executeBatch();
			date2=new Date();
			seconds+=(date2.getMinutes()-date1.getMinutes())*60+date2.getSeconds()-date1.getSeconds();
			System.out.println("insert0():\t"+seconds);//193s
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
