package code;

import java.text.DecimalFormat;

import java.text.DecimalFormat;


public class ShoppingTerm {
	private Good good;
	private int num;
	private double discount = 0;						//打折

	public ShoppingTerm(Good good, int num) {
		super();
		this.good = good;
		this.num = num;
	}

	public double getsubtotal()
	{
		return good.getPrice()*num*discount;
	}

	public double getdiscount()
	{
		return good.getPrice()*num*(1-discount);
	}

	public void printShoppingTerm()
	{
		String printStr = "";
		printStr += "名称："+good.getName()+"， ";
		printStr += "数量："+num+good.getUnit()+"，";

		DecimalFormat df = new DecimalFormat("######0.00");
		double price = good.getPrice();
		double subtotal = price * num * discount;
		printStr += "单价："+df.format(price)+"（元），";
		printStr += "小计："+df.format(subtotal)+"（元）";
		if(discount < 1){
			double offer = price*num*(1-discount);
			printStr += "，优惠："+df.format(offer)+"（元）";
		}

		System.out.println(printStr);
	}

	public void printOfferTerm()
	{
		if((1-discount) < 1e-8)
			return;
		String printStr = "";
		printStr += "名称："+good.getName()+"， ";
		printStr += "数量："+num+good.getUnit();
		System.out.println(printStr);
	}

	public Good getGood() {
		return good;
	}

	public void setGood(Good good) {
		this.good = good;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public double getOffer() {
		return discount;
	}

	public void setOffer(double offer) {
		this.discount = offer;
	}

}
