package DIEGRAM;

public abstract class Diegram {
	
	private static DayDietSheet[] ds = new DayDietSheet[10];
	private static int current = 0;
	
	public static DayDietSheet requestDayDietSheet(int year, int month, int day) {
		
		ds[current] = new DayDietSheet(year, month, day);
		
		return ds[current];
	}
	
	public void makeDiet(String mealType) { };
	
	public void enterFood(String name, int amount) { };

}