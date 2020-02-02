import java.io.FileNotFoundException;
import org.json.JSONObject;
import com.vodafone.api.HttpResponse;
import com.vodafone.api.gift.Gift;
import com.vodafone.core.ApiConnection;
import com.vodafone.core.SheetHandle;
import com.vodafone.schema.smrtprc.CdlTriggers;
import com.vodafone.schema.smrtprc.CdlTriggersIDs;

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
		
		CdlTriggersIDs cdtIDS = new CdlTriggersIDs(4280, 200202 , 36);
		CdlTriggers cdt = new CdlTriggers(1005331730, cdtIDS);
		cdt.get();
		System.out.println(cdt.getValueOf("GP2"));
		

	}
	

}
