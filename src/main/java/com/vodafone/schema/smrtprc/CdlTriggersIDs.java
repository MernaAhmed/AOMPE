package com.vodafone.schema.smrtprc;

public class CdlTriggersIDs {
	
	private int IDI;
	private int IDD;
	private int IDV;
	

	public int getIDI() {
		return this.IDI;
	}
	public void setIDI(int iDI) {
		this.IDI = iDI;
	}
	
	public int getIDD() {
		return this.IDD;
	}
	public void setIDD(int iDD) {
		this.IDD = iDD;
	}
	
	public int getIDV() {
	    return this.IDV;
	}
	public void setIDV(int IDV) {
	    this.IDV = IDV;
	}
	
	public CdlTriggersIDs(int IDI,int IDD,int IDV){
		this.IDD = IDD;
		this.IDI = IDI;
		this.IDV = IDV;
	}
}