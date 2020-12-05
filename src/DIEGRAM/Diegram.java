package DIEGRAM;

import java.util.*;

public abstract class Diegram {
	
	private static DayDietSheet[] ds = new DayDietSheet[10];
	private static int current = 0;
	
	public static DayDietSheet requestDayDietSheet(int year, int mounth, int day) {
		
		ds[current] = new DayDietSheet(year, mounth, day);
		
		return ds[current];
	}
	
	public static abstract void makeDiet(String mealType);
	
	public static abstract void D();
	
	

}