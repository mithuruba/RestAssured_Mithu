package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class InputMyData {
	
	public AddPlace inputData(String name, String language, String address) {
		AddPlace inputPayload = new AddPlace();
		inputPayload.setAccuracy(50);
		inputPayload.setName(name);
		inputPayload.setPhone_number("(+91) 983 893 3937");
		inputPayload.setAddress(address);
		inputPayload.setLanguage(language);
		inputPayload.setWebsite("http://google.com");
		

		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		inputPayload.setLocation(location);

		List<String> li = new ArrayList<String>();
		li.add("shoe park");
		li.add("shop");
		inputPayload.setTypes(li);
		return inputPayload;
	}
	
	

	public static String deletePlacePayload(String placeid) {
		
		return "{\r\n\"place_id\":\""+placeid+"\"\r\n}";
	}

}
