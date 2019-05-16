package hello;


import java.awt.List;
import java.util.ArrayList;

import org.json.JSONArray;

import org.json.JSONObject;


public class Response {
	private  String areaSize;
	private  String startingPosition;
	private  String oilPatches;
	private  String navigateInstructions;
	private final String  content;
	
	public void setAreaSize(String areaSize) {
		this.areaSize="["+areaSize+"]";
	}
	public  String getAreaSize() {
		return areaSize;
	}
	
	public void setStartingPosition(String startingPosition) {
		this.startingPosition="["+startingPosition+"]";
	}
	
	public  String getStartingPosition() {
		return startingPosition;
	}
	

	public void setOilPatches(String oilPatches) {
	this.oilPatches="["+oilPatches+"]";
	}
	public  String getOilPatches() {
		return oilPatches;
	}
	
	public void setNavigateInstructions(String navigateInstructions) {
		this.navigateInstructions="["+navigateInstructions+"]";
	}
	
	public  String getNavigateInstructions() {
		return navigateInstructions;
	}
	
		
	 public Response( String content) {
	        this.content = content;
	    }
	 

	
	
}