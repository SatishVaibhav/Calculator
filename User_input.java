import java.util.Scanner; 
public class User_input {
	public static void main(String[] args) 
	{	
		}
	public static void get_input() {
		boolean is_runing = true;

		while(is_runing)
		{
			Scanner console = new Scanner(System.in); 
			System.out.println("Enter your equation: ");
			String input = console.next();
			System.out.println(calculator.translate(input));
			console.close(); 
	  }
	}
}
