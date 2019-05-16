package cleaner;

public class Response {
	private String areaSize;
	private String startingPosition;
	private String oilPatches;
	private String navigationInstructions;
	private final String content;

	public void setAreaSize(String areaSize) {
		this.areaSize = "[" + areaSize + "]";
	}

	public String getAreaSize() {
		return areaSize;
	}

	public void setStartingPosition(String startingPosition) {
		this.startingPosition = "[" + startingPosition + "]";
	}

	public String getStartingPosition() {
		return startingPosition;
	}

	public void setOilPatches(String oilPatches) {
		this.oilPatches = "[" + oilPatches + "]";
	}

	public String getOilPatches() {
		return oilPatches;
	}

	public void setNavigationInstructions(String navigationInstructions) {
		this.navigationInstructions = "[" + navigationInstructions + "]";
	}

	public String getNavigationInstructions() {
		return navigationInstructions;
	}

	public Response(String content) {
		this.content = content;
	}

}