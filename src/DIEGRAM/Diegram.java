package DIEGRAM;

public class Diegram {
	
	private static DayDietSheet[] ds = new DayDietSheet[10];
	private static int current = 0;
	private static Goal g;
	
	public static DayDietSheet requestDayDietSheet(int year, int month, int day) {
		
		ds[current] = new DayDietSheet(year, month, day);
		
		return ds[current];
	}

	public void makeDiet(String mealType) {}
	
	public void enterFood(String name,int amount) {}
	
	public void endRecord() {}
	
	public void makeSetGoal() {
		g = new Goal();
	}
	
	public void registerInfo(int height,int weight) {
		g.registerInfo(height, weight);
	}
	
	public void registerActiveVolume(int activeVolume) {
		g.registerActiveVolume(activeVolume);
	}
	
	public void adjustCalorie(int calorie){
		g.adjustCalorie(calorie);
	}
	
	public void enterTarget(int sodiumTarget,int sugarTarget) {
		g.enterTarget(sodiumTarget, sugarTarget);
	}
	
	public void endSetGoal() {}

}