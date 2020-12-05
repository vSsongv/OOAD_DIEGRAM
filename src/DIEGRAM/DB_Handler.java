package DIEGRAM;

import java.util.ArrayList;

public class DB_Handler {
	
	private static class DataBase{
		static ArrayList<Goal> GoalList = new ArrayList<Goal>();
		static ArrayList<DayDietSheet> DayDietSheetList = new ArrayList<DayDietSheet>();
	}
	
	public static void addGoal(Goal g) //save Goal
	{
		DataBase.GoalList.add(g);
		return;
	}
	
	public static DayDietSheet saveDayDietSheet(DayDietSheet ds) //save DayDietSheet
	{
		DataBase.DayDietSheetList.add(ds);
		return ds;
	}
}
