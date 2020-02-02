package com.vodafone.schema.SMRTPRC;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//import org.hibernate.annotations.Table;
@Entity
@Table(name ="CDL_TRIGGERS")
public class CdlTriggers implements Serializable {
	
	@Column (name = "MSISDN")
	private String MSISDN;
	
	@EmbeddedId
    private CdlTriggersIDs cdlTriggersIds;
	
	public String getMSISDN() {
	    return MSISDN;
	}
	public void setMSISDN(String MSISDN) {
	    this.MSISDN = MSISDN;
	}

    public CdlTriggersIDs getId() {
        return cdlTriggersIds;
    }

    public void setId(CdlTriggersIDs cdlTriggersIds) {
        this.cdlTriggersIds = cdlTriggersIds;
    }
    
    public String getIDI() {return this.cdlTriggersIds.getIDI();}
    public void setIDI(String IDI) {this.cdlTriggersIds.setIDI(IDI);}
    public String getIDV() {return this.cdlTriggersIds.getIDV();}
    public void setIDV(String IDV) {this.cdlTriggersIds.setIDV(IDV);}
    public String getIDD() {return this.cdlTriggersIds.getIDD();}
    public void setIDD(String IDD) {this.cdlTriggersIds.setIDD(IDD);}

   // for application use, to create new persistent objects
   public CdlTriggers(String MSISDN,CdlTriggersIDs cdlTriggersIds)
   {
      this.MSISDN = MSISDN;
      this.cdlTriggersIds =cdlTriggersIds;
   }
 
}