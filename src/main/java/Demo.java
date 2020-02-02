import java.io.FileNotFoundException;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.SharedSessionContract;
import org.hibernate.Transaction;
import org.json.JSONObject;
import com.vodafone.api.HttpResponse;
import com.vodafone.api.gift.Gift;
import com.vodafone.core.ApiConnection;
import com.vodafone.core.SheetHandle;
import com.vodafone.schema.SMRTPRC.*;

public class Demo {

	public static void main(String[] args) throws FileNotFoundException, Exception {
//		Gift gift = new Gift();
//		SheetHandle sheet = new SheetHandle("offline-trigger-params-one-row.xlsx");
//		sheet.read();
//		String [] urlParams = sheet.getRows();
//		String offerURL = gift.getURL(urlParams);
//	
//		String url = offerURL;
//		ApiConnection apiConnectionObj = new ApiConnection(url);
//		apiConnectionObj.get();
//		String str = apiConnectionObj.getResponseAsString();
//		HttpResponse httpResponse = new HttpResponse();
//		JSONObject responseObject = httpResponse.parse(str);
//		System.out.println(responseObject.get("statusId"));;
//		System.out.println(responseObject.get("failed"));
		
		SessionFactory s = SessionFactoryUtility.getSessionFactory();
		org.hibernate.Session session = s.openSession();
		
	    Transaction tx = null;
	    try {

	        tx = session.beginTransaction();        
	       CdlTriggersIDs cdIDS = new CdlTriggersIDs("999","888","777");
	       CdlTriggers cdl = new CdlTriggers("01110555540",cdIDS);
	       session.save(cdl);
//	       System.out.println(session.createSQLQuery("select table_name from user_tables").list());
	       tx.commit();

	    } catch (HibernateException ex) {
	        if (tx != null) {
	            tx.rollback();
	        }            
	        System.out.println("Exception: " + ex.getMessage());
	        ex.printStackTrace(System.err);
	    } finally {
	        s.close(); 
	    }
	}
	

}
