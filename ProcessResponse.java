package hello;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import org.json.JSONObject;

public class ProcessResponse {
	private static int areaSizeX=0;
	private static int areaSizeY=0;
	
	private static int startingPositionX=0;
	private static int startingPositionY=0;
	
	private static int numberOfOilPatches;
	private static int[][] oilPatches;
	
	private static int cleanerX;
	private static int cleanerY;
	private static final int OIL = 1;
	private static int[][] locationArray;
	private static int cntOilPatchesCleaned=0;
	
	public static void processAreaSize(String jsonResponse) throws JSONException {
		JSONObject jsonObject = new JSONObject(jsonResponse);
		String jsonAreaSize=jsonObject.getString("areaSize");
		System.out.println("Area Size:"+jsonAreaSize);
		JSONArray array = new JSONArray(jsonAreaSize);
		areaSizeX=array.getInt(0);
		System.out.println("areaSizeX:"+areaSizeX);
		areaSizeY=array.getInt(1);
		System.out.println("areaSizeY:"+areaSizeY);
	}
	public static void processStartingPosition(String jsonResponse) throws JSONException {
		JSONObject jsonObject = new JSONObject(jsonResponse);
		String jsonStartingPosition=jsonObject.getString("startingPosition");
		System.out.println("Starting Position:"+jsonStartingPosition);
		JSONArray array = new JSONArray(jsonStartingPosition);
		startingPositionX=array.getInt(0);
		System.out.println("startingPositionX:"+startingPositionX);
		startingPositionY=array.getInt(1);
		System.out.println("startingPositionY:"+startingPositionY);
	}
	public static void processOilPatches(String jsonResponse) throws JSONException {
		JSONObject jsonObject = new JSONObject(jsonResponse);
		String jsonOilPatches=jsonObject.getString("oilPatches");
		System.out.println("Oilpatches co-ordinates:"+jsonOilPatches);
		JSONArray array = new JSONArray(jsonOilPatches);
		numberOfOilPatches=array.length()/2;
		System.out.println("Number of Oil Patches:"+numberOfOilPatches);
		oilPatches= new int[numberOfOilPatches][2];
		int k=0;
	    locationArray= new int[areaSizeX][areaSizeY];
	    for (int i = 0; i < areaSizeX; i++) {
			for (int j = 0; j < areaSizeY; j++) {
				locationArray[i][j] = 0;
			}
		}
	    
		for (int i=0; i<numberOfOilPatches*2 && k< numberOfOilPatches;i+=2) {
		oilPatches[k][0]=array.getInt(i);
		System.out.println("oilpatchRow:"+k);
		System.out.println("oilpatchX:"+oilPatches[k][0]);
		oilPatches[k][1]=array.getInt(i+1);
		System.out.println("oilpatchY:"+oilPatches[k][1]);
		
		locationArray[oilPatches[k][0]] [oilPatches[k][1]]=OIL;
		k++;
		}
	}
		public static void processNavigateInstructions(String navigateInstructions)  {
		    String choice;
		    cleanerX=startingPositionX;
		    cleanerY=startingPositionY;
		    cntOilPatchesCleaned=0;
		    

		    
		    //System.out.println("Length of instructions:"+navigateInstructions.length());
			for (int i=0; i<(navigateInstructions.length()); i++) {//exclude square bracket at both ends
			//System.out.println("i"+i);
				choice=Character.toString(navigateInstructions.charAt(i)).toUpperCase();
			System.out.println("Choice is:"+choice);
			switch (choice) {

			case "N":
				cleanerY++;
				if (!validateLocation())
					cleanerY--;
				break;
		
			case "E":
				cleanerX++;
				System.out.println("1-CleanerX:"+cleanerX+"cleanerY:"+cleanerY);
				if (!validateLocation())
					cleanerX--;
				break;
			case "S":
				cleanerY--;
				if (!validateLocation())
					cleanerY++;
				break;

			case "W":
				cleanerX--;
				if (!validateLocation())
					cleanerY--;
				break;

			default:
				System.out
						.println("Invalid choice.. your choice is not listed .. please enter valid cardinal direction");
			}
			checkOilcleaned(cleanerX, cleanerY);
		}
	}
		public static boolean validateLocation() {
			boolean valid = true;
			if ((cleanerX < 0) || (cleanerX > areaSizeX) || (cleanerY < 0)
					|| (cleanerY > areaSizeY)) {
				System.out
						.println("your direction is invalid as it will take the cleaner robot outside area");
				System.out.println("CleanerX:"+cleanerX+"cleanerY:"+cleanerY);
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
			//System.out.println("Number of oil patches cleaned:" + cntOilpatches);
			//System.out.println("x:" + x + "y:" + y);
		}
		public static String getFinalPosition() {
			return "["+cleanerX+","+cleanerY+"]";
			}
		public static int getOilPatchesCleaned() {
			return cntOilPatchesCleaned;
			}
}