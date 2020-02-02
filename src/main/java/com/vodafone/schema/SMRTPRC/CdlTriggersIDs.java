package com.vodafone.schema.SMRTPRC;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class CdlTriggersIDs implements Serializable {
	
	@Id
	@Column (name = "IDI")
	private String IDI;
	
	@Id
	@Column (name = "IDD")
	private String IDD;
	
	@Id
	@Column (name = "IDV")
	private String IDV;
	

	public String getIDI() {
		return this.IDI;
	}
	public void setIDI(String iDI) {
		this.IDI = iDI;
	}
	
	public String getIDD() {
		return this.IDD;
	}
	public void setIDD(String iDD) {
		this.IDD = iDD;
	}
	
	public String getIDV() {
	    return this.IDV;
	}
	public void setIDV(String IDV) {
	    this.IDV = IDV;
	}
	
	public CdlTriggersIDs(String IDI,String IDD,String IDV){
		this.IDD = IDD;
		this.IDI = IDI;
		this.IDV = IDV;
	}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CdlTriggersIDs)) return false;
        CdlTriggersIDs that = (CdlTriggersIDs) o;
        return  Objects.equals(getIDD(), that.getIDD()) &&
                Objects.equals(getIDV(), that.getIDV()) && 
                Objects.equals(getIDI(), that.getIDI());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIDD(), getIDV(),getIDI());
    }
}