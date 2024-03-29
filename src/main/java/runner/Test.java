package runner;

public class Test {

	public static void main(String[] args) {
		
		String price1= "116.97";
		String price2=  "110.0";
		double priceone = Double.parseDouble(price1);
		double pricetwo =Double.parseDouble(price2);
		double totalprice= priceone+pricetwo;
		String Payable = " 226.97";
		double totalPayable = Double.parseDouble(Payable);
		
		System.out.println(totalprice);
		System.out.println(totalPayable);
	}
}
