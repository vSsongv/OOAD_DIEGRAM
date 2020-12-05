package DIEGRAM;

public class Diet {
	
	private String mealType;
	private int calorieTotal;
	private int fatTotal;
	private int proteinTotal;
	private int saccharideTotal;
	private int sodiumTotal;
	private int carbohydrateTotal;
	
	public Diet(String mealType) {
	    this.mealType = mealType;
		this.calorieTotal = 0;
		this.fatTotal = 0;
		this.proteinTotal = 0;
		this.saccharideTotal = 0;
		this.sodiumTotal = 0;
		this.carbohydrateTotal = 0;
	}
	
	public void enterFood(String name, int amount) //f를 만들고, f attributes내용들을 찾아 
	{
		Food f = new Food(name, amount);
		
		return;
	}
	
	public int getCalorieTotal() {
		 return this.calorieTotal;
	}
}
