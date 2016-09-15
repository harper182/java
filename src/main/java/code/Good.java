package code;

import java.awt.Stroke;


public class Good {
	private String barcode;
	private String name;
	private String unit;
	private String category;
	private String subCategory;
	private double price;
	
	public Good(String _barcode, String _name, String _unit, 
			String _category, String _subCat, double _price  ){
		barcode = _barcode;
		name = _name;
		unit = _unit;
		category = _category;
		subCategory = _subCat;
		price = _price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public double getPrice() {
		return price;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
