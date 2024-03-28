import java.math.BigInteger;

public class BigN {
	
	
	public static BigInteger fibonacci(long n) throws InterruptedException {
	    if (n <= 1) {
	        return BigInteger.valueOf(n);
	    }

	    BigInteger fib1 = BigInteger.ZERO;
	    BigInteger fib2 = BigInteger.ONE;
	    BigInteger fib = BigInteger.ZERO;

	    long startTime = System.currentTimeMillis();
	    
	    for (long i = 2; i <= n; i++) {
	        fib = fib1.add(fib2);
	        fib1 = fib2;
	        fib2 = fib;
	        
	        if (i % 1000000 == 0) {
	            long endTime = System.currentTimeMillis();
	            long elapsedTime = endTime - startTime;

	            // Format elapsed time in seconds with 3 decimal places
	            String formattedTime = String.format("%.3f", elapsedTime / 1000.0);
	            System.out.println("Calc Fibs' " + (i - 1000000) + "-" + i + " in " + formattedTime + "s");
	            startTime = System.currentTimeMillis();
	        }
	    }

	    return fib;
	}

	
	
    public static BigInteger factorial(BigInteger n) {
    	
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

	public static void main (String[] args) throws InterruptedException {
		long n = Long.MAX_VALUE;
		fibonacci(n);
	}
}
