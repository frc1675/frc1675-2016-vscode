package org.usfirst.frc.team1675.robot.utils;

import java.util.ArrayList;

public class Zamboni {

	private static ArrayList<ClearDuringDisable> stuffToClear = new ArrayList<ClearDuringDisable>();
	
	public static void add(ClearDuringDisable cdd){
		stuffToClear.add(cdd);
	}
	
	public static void clear(){
		for(ClearDuringDisable cdd : stuffToClear){
			cdd.clearWhileDisabled();
		}
	}
	
}
