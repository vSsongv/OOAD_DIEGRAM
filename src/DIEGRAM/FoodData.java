package DIEGRAM;

import java.util.ArrayList;

public class FoodData {
	
	static ArrayList<Food> FoodDataList = new ArrayList<Food>();
	
	public static Food getFoodInfo(String name)
	{
		Food findf = null;
		for(Food fd : FoodData.FoodDataList)
		{
			if(fd.getName().equals(name))
			{
				findf = new Food(name,fd.getAmount(),fd.getCalories(),fd.getProtein(),fd.getFat(),
						fd.getcarbohydrate(),fd.getSaccharide(),fd.getSodium());
			}
		}
		return findf;
	}

}
