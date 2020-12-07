package DIEGRAM;

public class Diegram {
	
	private static DayDietSheet[] ds = new DayDietSheet[10];
	private static int current = 0;
	private static Goal g;
	
	public static void makeDummyData() {
		DayDietSheet ds = new DayDietSheet(2020, 12, 8);
		ds.makeDiet("¾ÆÄ§");
		ds.enterFood("µÈÀåÂî°³", 1);
		ds.enterFood("°ø±â¹ä", 1);
		ds.enterFood("±èÄ¡", 1);
		ds.endRecord();
	}
	
	public static void systemStart() {
		DB_Handler.FDinsert();
	}
	
	public static DayDietSheet requestDayDietSheet(int year, int month, int day) {
		
		ds[current] = new DayDietSheet(year, month, day);
		
		return ds[current];
	}

	public static void makeDiet(String mealType) {
		ds[current].makeDiet(mealType);
	}
	
	public static void enterFood(String name,int amount) {
		ds[current].enterFood(name, amount);
	}
	
	public static void endRecord() {
		ds[current].endRecord();
		current++;
	}
	
	public static void makeSetGoal() {
		g = new Goal();
	}
	
	public static void registerInfo(int height,int weight) {
		g.registerInfo(height, weight);
	}
	
	public static void registerActiveVolume(int activeVolume) {
		g.registerActiveVolume(activeVolume);
	}
	
	public static int analyzeRecommendedCalorie() {
		return g.analyzeRecommendedCalorie();
	}
	
	public static void adjustCalorie(int calorie){
		g.adjustCalorie(calorie);
	}
	
	public static void enterTarget(int sodiumTarget,int sugarTarget) {
		g.enterTarget(sodiumTarget, sugarTarget);
	}
	
	public static void endSetGoal() {
		g.endSetGoal();
	}

}