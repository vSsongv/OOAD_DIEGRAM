package DIEGRAM;

import java.util.ArrayList;

public class DB_Handler {
	
	private static class DataBase{
		static Goal userGoal = null;
		static ArrayList<DayDietSheet> DayDietSheetList = new ArrayList<DayDietSheet>();
	}
	
	public static DayDietSheet getSheet(int year, int month, int day) {
		for(DayDietSheet dds : DataBase.DayDietSheetList)
		{
			if(dds.getYear() == year && dds.getMonth() == month && dds.getDay() == day)
			{
				return dds;
			}
		}
		return null;
	}
	
	public static Goal getUserGoal() {
		return DataBase.userGoal;
	}
	
	public static void getSheetInfo(DayDietSheet ds) //����� DayDietSheet������ �ҷ��´�.
	{
		for(DayDietSheet dds : DataBase.DayDietSheetList)
		{
			if(dds.getYear() == ds.getYear() && dds.getMonth() == ds.getMonth() && dds.getDay() == ds.getDay())
			{
				ds.setDayCalorieTotal(dds.getDayCalorieTotal());
				ds.setDaycarbohydrateTotal(dds.getDaycarbohydrateTotal());
				ds.setDayProteinTotal(dds.getDayProteinTotal());
				ds.setDayfatTotal(dds.getDayfatTotal());
				ds.setDaysaccharideTotal(dds.getDaysaccharideTotal());
				ds.setDaysodiumTotal(dds.getDaysodiumTotal());
				ds.setD(dds.getD());
				ds.setCurrent(dds.getCurrent());
				return;
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
		for(DayDietSheet dds : DataBase.DayDietSheetList)
		{
			if(dds.getYear() == ds.getYear() && dds.getMonth() == ds.getMonth() && dds.getDay() == ds.getDay())
			{
				DataBase.DayDietSheetList.remove(dds);
				break;
			}
		}
		DataBase.DayDietSheetList.add(ds);		
		
		return ds;
	}
	
	public static void FDinsert()
	{
		//�̸� , ��, Į�θ�, �ܹ���, ����, ź��ȭ��, ��, ��Ʈ��
		Food Food1 = new Food("�������",1,395,20,15,30,3,670);
		Food Food2 = new Food("�����",1,200,6,3,8,0,635);
		Food Food3 = new Food("���̴�",1,250,0,0,0,22,0);
		Food Food4 = new Food("��ġ",1,30,1,1,1,36,183);
		Food Food5 = new Food("����",1,238,0,	27,21,15,2);
		Food Food6 = new Food("�����",1,200,3,1,40,0,0);
		
		FoodData.FoodDataList.add(Food1);
		FoodData.FoodDataList.add(Food2);
		FoodData.FoodDataList.add(Food3);
		FoodData.FoodDataList.add(Food4);
		FoodData.FoodDataList.add(Food5);
		FoodData.FoodDataList.add(Food6);
	}
	
}
