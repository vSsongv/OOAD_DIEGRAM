package DIEGRAM;

import java.util.ArrayList;

public class FoodData {
	
	static ArrayList<Food> FoodDataList = new ArrayList<Food>();
	
	public static void FDinsert()
	{
		Food Food1 = new Food("±î¸£º¸³ª¶ó",1,395,20,15,30,3,670);
		Food Food2 = new Food("µÈÀåÂî°³",1,200,6,3,8,0,635);
		Food Food3 = new Food("»çÀÌ´Ù",1,250,0,0,0,22,0);
		Food Food4 = new Food("±èÄ¡",1,30,1,1,1,36,183);
		Food Food5 = new Food("½ÄÇý",1,238,0,	27,21,15,2);
		Food Food6 = new Food("°ø±â¹ä",1,200,3,1,40,0,0);
		
		FoodData.FoodDataList.add(Food1);
		FoodData.FoodDataList.add(Food2);
		FoodData.FoodDataList.add(Food3);
		FoodData.FoodDataList.add(Food4);
		FoodData.FoodDataList.add(Food5);
		FoodData.FoodDataList.add(Food6);
	}
	
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
