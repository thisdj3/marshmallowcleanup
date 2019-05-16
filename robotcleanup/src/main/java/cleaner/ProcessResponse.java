package cleaner;

import org.json.JSONArray;

import org.json.JSONObject;

public class ProcessResponse {
	private static int areaSizeX = 0;
	private static int areaSizeY = 0;

	private static int startingPositionX = 0;
	private static int startingPositionY = 0;

	private static int numberOfOilPatches;
	private static int[][] oilPatches;

	private static int cleanerX;
	private static int cleanerY;
	private static final int OIL = 1;
	private static int[][] locationArray;
	private static int cntOilPatchesCleaned = 0;

	public static void processAreaSize(String jsonResponse) throws Exception {
		try {
			JSONObject jsonObject = new JSONObject(jsonResponse);
			String jsonAreaSize = jsonObject.getString("areaSize");

			JSONArray array = new JSONArray(jsonAreaSize);
			areaSizeX = array.getInt(0);

			areaSizeY = array.getInt(1);

		} catch (Exception e) {
			throw new Exception("Invalid areaSize" + "-Error:" + e);
		}

	}

	public static void processStartingPosition(String jsonResponse) throws Exception {
		try {
			JSONObject jsonObject = new JSONObject(jsonResponse);

			String jsonStartingPosition = jsonObject.getString("startingPosition");

			JSONArray array = new JSONArray(jsonStartingPosition);
			startingPositionX = array.getInt(0);

			startingPositionY = array.getInt(1);

		} catch (Exception e) {
			throw new Exception("Invalid startingPosition" + e.getMessage());
		}

	}

	public static void processOilPatches(String jsonResponse) throws Exception {
		try {

			JSONObject jsonObject = new JSONObject(jsonResponse);
			String jsonOilPatches = jsonObject.getString("oilPatches");
			JSONArray array = new JSONArray(jsonOilPatches);
			numberOfOilPatches = array.length() / 2;
			oilPatches = new int[numberOfOilPatches][2];
			int k = 0;
			locationArray = new int[areaSizeX][areaSizeY];
			for (int i = 0; i < areaSizeX; i++) {
				for (int j = 0; j < areaSizeY; j++) {
					locationArray[i][j] = 0;
				}

			}
			// populate locationArray with oil patch co-ordinates using "OIL" identifier
			for (int i = 0; i < numberOfOilPatches * 2 && k < numberOfOilPatches; i += 2) {
				oilPatches[k][0] = array.getInt(i);

				oilPatches[k][1] = array.getInt(i + 1);

				locationArray[oilPatches[k][0]][oilPatches[k][1]] = OIL;
				k++;
			}
		} catch (Exception e) {
			throw new Exception("Invalid oilPatches" + e.getMessage());
		}
	}

	public static void processNavigationInstructions(String navigationInstructions) throws Exception {
		try {
			String choice;
			cleanerX = startingPositionX;
			cleanerY = startingPositionY;
			cntOilPatchesCleaned = 0;

			for (int i = 0; i < (navigationInstructions.length()); i++) {// exclude square bracket at both ends

				choice = Character.toString(navigationInstructions.charAt(i)).toUpperCase();

				// Navigate through grid based on navigation instructions
				switch (choice) {

				case "N":
					cleanerY++;
					if (!validateLocation()) {
						cleanerY--;
						throw new Exception();
					}
					break;

				case "E":
					cleanerX++;

					if (!validateLocation()) {
						cleanerX--;
						throw new Exception();
					}
					break;
				case "S":
					cleanerY--;
					if (!validateLocation()) {
						cleanerY++;
						throw new Exception();
					}
					break;

				case "W":
					cleanerX--;
					if (!validateLocation()) {
						cleanerY--;
						throw new Exception();
					}
					break;

				default:

					throw new Exception();
				}
				checkOilcleaned(cleanerX, cleanerY);
			}
		} catch (Exception e) {
			if (!validateLocation())
				throw new Exception(
						"Direction is invalid as it will take the cleaner robot outside area" + "-Error:" + e);
			else
				throw new Exception("Invalid Navigation Instructions" + "-Error:" + e);
		}
	}

	public static boolean validateLocation() {
		boolean valid = true;
		if ((cleanerX < 0) || (cleanerX > areaSizeX) || (cleanerY < 0) || (cleanerY > areaSizeY)) {
			System.out.println("your direction is invalid as it will take the cleaner robot outside area");
			System.out.println("CleanerX:" + cleanerX + "cleanerY:" + cleanerY);
			valid = false;
		}

		return valid;

	}

	public static void checkOilcleaned(int x, int y) {
		for (int i = 0; i < areaSizeX; i++) {
			for (int j = 0; j < areaSizeY; j++) {
				if ((locationArray[i][j] == OIL) && (x == i) && (y == j)) {
					locationArray[i][j] = 0;
					cntOilPatchesCleaned++;

				}

			}

		}

	}

	public static String getFinalPosition() {
		return "[" + cleanerX + "," + cleanerY + "]";
	}

	public static int getOilPatchesCleaned() {
		return cntOilPatchesCleaned;
	}

}