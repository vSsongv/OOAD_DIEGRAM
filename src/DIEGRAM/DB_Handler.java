package DIEGRAM;

import java.util.ArrayList;

public class DB_Handler {
	
	private static class DataBase{
		static Goal userGoal = new Goal();
		static ArrayList<DayDietSheet> DayDietSheetList = new ArrayList<DayDietSheet>();
	}
	
	public static void getSheetInfo(DayDietSheet ds) //저장된 DayDietSheet정보를 불러온다.
	{
		DayDietSheet findds = null;
		for(DayDietSheet dds : DataBase.DayDietSheetList)
		{
			if(dds.getYear() == ds.getYear() && dds.getMonth() == ds.getMonth() && dds.getDay() == ds.getDay())
			{
				ds.setDayCalorieTotal(dds.getDayCalorieTotal());
				ds.setDaycarbohydrateTotal(dds.getDaycarbohydrateTotal());
				ds.setDayfatTotal(dds.getDayfatTotal());
				ds.setDaysaccharideTotal(dds.getDaysaccharideTotal());
				ds.setDaysodiumTotal(dds.getDaysodiumTotal());
				ds.setD(dds.getD());
			}
		}
	}
	
	public static void addGoal(Goal g) //save Goal
	{
		DataBase.userGoal = g;
		return;
	}
	
	public static DayDietSheet saveDayDietSheet(DayDietSheet ds) //save DayDietSheet
	{
		DataBase.DayDietSheetList.add(ds);
		return ds;
	}
}
