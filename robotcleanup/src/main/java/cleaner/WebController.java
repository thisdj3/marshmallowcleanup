package cleaner;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

	// JSONObject jsonObject = new JSONObject();

	// variables for areaSize
	private static String templateAreaSize = "{\"areaSize\":\"%s\"}";
	private static String stringAreaSize = null;

	// variables for startingPosition
	private static String templateStartingPosition = "{\"startingPosition\":\"%s\"}";
	private static String stringStartingPosition = null;

	// variables for OilPatches
	private static String templateOilPatches = "{\"oilPatches\":\"%s\"}";
	private static String stringOilPatches = null;

	// variables for navigationInsructions
	private static String templateNavigationInstructions = "{\"navigationInstructions\":\"%s\"}";
	private static String stringNavigationInstructions = null;

	// variables for output
	private static String templateOutput = "{\"finalPosition\":\"%s\", \"oilPatchesCleaned\":%d}";
	private static String stringOutput = null;

	private static String response = null;

	@RequestMapping("/cleaner")
	public String response(@RequestParam(value = "areaSize", required = true) String areaSize,
			@RequestParam(value = "startingPosition", required = true) String startingPosition,
			@RequestParam(value = "oilPatches", required = true) String oilPatches,
			@RequestParam(value = "navigationInstructions", required = true) String navigationInstructions)
					throws Exception {
		try {
			// process input for areaSize
			Response inputAreaSize = new Response(areaSize);
			inputAreaSize.setAreaSize(areaSize);
			stringAreaSize = String.format(templateAreaSize, inputAreaSize.getAreaSize());
			ProcessResponse.processAreaSize(stringAreaSize);
			// process input for startingPosition
			Response inputStartingPosition = new Response(startingPosition);
			inputStartingPosition.setStartingPosition(startingPosition);
			stringStartingPosition = String.format(templateStartingPosition,
					inputStartingPosition.getStartingPosition());
			ProcessResponse.processStartingPosition(stringStartingPosition);
			// process input for oilPatches
			Response inputOilPatches = new Response(oilPatches);
			inputOilPatches.setOilPatches(oilPatches);
			stringOilPatches = String.format(templateOilPatches, inputOilPatches.getOilPatches());
			ProcessResponse.processOilPatches(stringOilPatches);

			// process input for navigationInstructions
			Response inputNavigationInstructions = new Response(navigationInstructions);
			inputNavigationInstructions.setNavigationInstructions(navigationInstructions);
			stringNavigationInstructions = String.format(templateNavigationInstructions,
					inputNavigationInstructions.getNavigationInstructions());
			ProcessResponse.processNavigationInstructions(navigationInstructions);
			stringOutput = String.format(templateOutput, ProcessResponse.getFinalPosition(),
					ProcessResponse.getOilPatchesCleaned());
			response = stringAreaSize.replace('}', ',') + (stringStartingPosition.replace('{', ' ')).replace('}', ',')
					+ (stringOilPatches.replace('{', ' ')).replace('}', ',')
					+ stringNavigationInstructions.replace('{', ' ') + stringOutput;
			// return response;
			return stringOutput;
			// return stringAreaSize;
		} catch (Exception e) {
			return e.getMessage();
		}
	}

}