import java.math.BigInteger;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class FactorialServer extends UnicastRemoteObject implements Factorial {

	public FactorialServer() throws RemoteException {
        super();
    }

    public BigInteger factorial(BigInteger n) throws RemoteException {
    	
    	if (n.equals(BigInteger.ZERO) || n.equals(BigInteger.ONE)) {
            return BigInteger.ONE;
        } else {
    	
	    	BigInteger result = n;
	        while (!n.equals(BigInteger.ONE)) {
	        	result = result.multiply(n.subtract(BigInteger.ONE));
	        	n = n.subtract(BigInteger.ONE);
	        }
	        return result;
        }
    }

    public static void main(String[] args) {
        try {
            FactorialServer factorialServer = new FactorialServer();
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            java.rmi.Naming.rebind("Factorial", factorialServer);
            System.out.println("FactorialServer is ready.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
