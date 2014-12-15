package optimization;

public class RandomData {
	private static String[] names={"Mike","Bob","Jack","Alice","Cathy","Ann","Betty","Cindy","Mary","Jane"};
	public String randomName(){
		int len=names.length;
		int random=(int)((Math.random())*len);
		return names[random];
	}
	private static char[] letters={'a','b','c','d','e'};
	public String randomString(){
		int len=letters.length;
		String reStr="";
		for(int i=0; i<5; i++){
			reStr+=letters[(int)(Math.random()*len)];
		}
		return reStr;
	}
	public int randomInt(){
		return (int)(Math.random()*1000)+1;
	}
}
