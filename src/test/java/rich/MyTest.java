package rich;

import java.math.BigDecimal;

public class MyTest {
	public static void main(String[] args) {
		BigDecimal winb = new BigDecimal("1");
        BigDecimal allb = new BigDecimal("3");
        
        BigDecimal winRateb = winb.divide(allb,2, BigDecimal.ROUND_HALF_UP);
        winRateb = winRateb.multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP) ;
        System.out.println(winRateb.toString());
	}
}
