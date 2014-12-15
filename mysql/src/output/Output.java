package output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Output {
	private static FileOutputStream fos;
	private static Output op;
	private Output(){
		try {
			File file=new File("result.txt");
			fos=new FileOutputStream (file, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Output getInstance(){
		if(op==null)
			op=new Output();
		return op;
	}
	public void write(String in){
		 OutputStreamWriter osw = new OutputStreamWriter(fos);
		 BufferedWriter bw = new BufferedWriter(osw);
		 try {
			bw.write(in);
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		Output.getInstance().write("abc");
	}
}
