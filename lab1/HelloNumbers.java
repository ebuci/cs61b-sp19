/*prints out the cumulative sum of the integers from 0 to 9*/
public class HelloNumbers{
	public static void main(String[] args){
		int x = 0;
		int y = 0;
		while (x < 10){
			System.out.print(y + " ");
			x = x + 1;
			y = y + x;
		}
	}
}