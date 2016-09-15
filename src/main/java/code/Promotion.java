package code;

import java.util.Vector;


public class Promotion {
	
	private String offerType;
	private Vector<String> barcodes;
	
	public Promotion(String offerType, Vector<String> barcodes) {
		super();
		this.offerType = offerType;
		this.barcodes = barcodes;
	}

	public String getOfferType() {
		return offerType;
	}

	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}

	public Vector<String> getBarcodes() {
		return barcodes;
	}

	public void setBarcodes(Vector<String> barcodes) {
		this.barcodes = barcodes;
	}
	
	
}
