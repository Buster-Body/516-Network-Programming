import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class FactorialClient {
    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
    	
    	Scanner input = new Scanner(System.in);
    	Factorial factorial = (Factorial) Naming.lookup("rmi://localhost/Factorial");
    	System.out.println("Enter a number to calculate x!");
    	while (true) 
    	{
    		String n = input.nextLine();
    		
    		if (n.equals("bye"))
    			break;
    		
    		else 
    		{
    			try 
    			{
    				long start = System.currentTimeMillis();
    				BigInteger result = factorial.factorial(new BigInteger(n));
    				long finish = System.currentTimeMillis() - start;
    				System.out.println("Factorial of " + n + " is " + result);
    				System.out.println("Result takes " + result.bitLength() + " bits and " + finish + "ms to produce");
    			} 
    			
    			catch (Exception e) 
    			{
    				System.out.println("Invalid input. Try again");
    				continue;
    			}
    		}
    	}
    	input.close();
    }
}
