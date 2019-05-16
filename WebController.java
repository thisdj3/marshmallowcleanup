package hello;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;



@RestController
public class WebController {

	
	 //JSONObject jsonObject = new JSONObject();

	 //variables for areaSize
	 private static  String templateAreaSize = "{\"areaSize\":\"%s\"}";
	 private static String stringAreaSize=null;
	 
	 //variables for startingPosition
	 private static  String templateStartingPosition = "{\"startingPosition\":\"%s\"}";
	 private static String stringStartingPosition=null;
	 
	 //variables for OilPatches
	 private static  String templateOilPatches = "{\"oilPatches\":\"%s\"}";
	 private static String stringOilPatches=null;
	 
	//variables for navigationInsructions
		 private static  String templateNavigateInstructions = "{\"navigateInstructions\":\"%s\"}";
		 private static String stringNavigateInstructions=null;
		 
	 
	 //variables for output
		 private static  String templateOutput="{\"finalPosition\":\"%s\", \"oilPatchesCleaned\":%d}";
		 private static String stringOutput=null;
		 
	 private static String response=null;
	@RequestMapping("/test1")
	public String response(@RequestParam(value="areaSize", defaultValue="3,4") String areaSize,
			@RequestParam(value="startingPosition", defaultValue="3,4") String startingPosition,
			@RequestParam(value="oilPatches", defaultValue="3,4") String oilPatches,
			@RequestParam(value="navigateInstructions", defaultValue="EW") String navigateInstructions) throws JSONException {
		//process input for areaSize
		Response inputAreaSize = new Response(areaSize);
		inputAreaSize.setAreaSize(areaSize);
		stringAreaSize=String.format(templateAreaSize, inputAreaSize.getAreaSize());
	    ProcessResponse.processAreaSize(stringAreaSize);
	  //process input for startingPosition
	  		Response inputStartingPosition = new Response(startingPosition);
	  		inputStartingPosition.setStartingPosition(startingPosition);
	  		stringStartingPosition=String.format(templateStartingPosition, inputStartingPosition.getStartingPosition());
	  	    ProcessResponse.processStartingPosition(stringStartingPosition);
	    // process input for oilPatches
		Response inputOilPatches = new Response(oilPatches);
		inputOilPatches.setOilPatches(oilPatches);
		stringOilPatches=String.format(templateOilPatches, inputOilPatches.getOilPatches());
		ProcessResponse.processOilPatches(stringOilPatches);

	  //process input for navigateInstructions
  		Response inputNavigateInstructions = new Response(navigateInstructions);
  		inputNavigateInstructions.setNavigateInstructions(navigateInstructions);
  		stringNavigateInstructions=String.format(templateNavigateInstructions, inputNavigateInstructions.getNavigateInstructions());
  	    ProcessResponse.processNavigateInstructions(navigateInstructions);
  	    stringOutput=String.format(templateOutput, ProcessResponse.getFinalPosition(),ProcessResponse.getOilPatchesCleaned());
  	    response=stringAreaSize.replace('}', ',')
	    		+(stringStartingPosition.replace('{', ' ')).replace('}', ',')
	    		+(stringOilPatches.replace('{', ' ')).replace('}', ',')
	    		+stringNavigateInstructions.replace('{',' ')
	    		+stringOutput;
		return response;
		//return stringAreaSize;
		
	}
	
 
	
	
	/*@RequestMapping(value = "/test2", method = RequestMethod.POST)
	public PostResponse Test(@RequestBody PostRequest inputPayload) {
		PostResponse response = new PostResponse();
		//response.setAreaSize("Updated:"+inputPayload.getAreaSize());
		response.setAreaSize("Updated:");
		return response;
	}
     */              
}