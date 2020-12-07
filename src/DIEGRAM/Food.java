package DIEGRAM;

public class Food {
	
	private int amount;
	private String name;
	private int calories;
	private int fat;
	private int carbohydrate;
	private int protein;
	private int saccharide;
	private int sodium;
	
	public Food(String name, int amount) //creator
	{
		this.name = name;
		this.amount = amount;
		Food f = FoodData.getFoodInfo(name);
		this.calculate(f);
	}
	
	//이름 , 양, 칼로리, 단백질, 지방, 탄수화물, 당, 나트륨
	public Food(String name, int amount, int calories, int protein,
			int fat, int carbohydrate, int saccharide, int sodium)
	{
		this.name = name;
		this.amount = amount;
		this.calories = calories;
		this.protein = protein;
		this.fat = fat;
		this.carbohydrate = carbohydrate;
		this.saccharide = saccharide;
		this.sodium = sodium;
	}
	
	private void calculate(Food f)
	{
		this.calories = (f.calories * this.amount);
		this.carbohydrate = (f.carbohydrate * this.amount);
		this.fat = (f.fat * this.amount);
		this.protein = (f.protein * this.amount);
		this.saccharide =(f.saccharide * this.amount);
		this.sodium = (f.sodium * this.amount);
	}


	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getcarbohydrate() {
		return carbohydrate;
	}

	public void setcarbohydrate(int carbohydrate) {
		this.carbohydrate = carbohydrate;
	}
	
	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public int getFat() {
		return fat;
	}

	public void setFat(int fat) {
		this.fat = fat;
	}

	public int getProtein() {
		return protein;
	}

	public void setProtein(int protein) {
		this.protein = protein;
	}

	public int getSaccharide() {
		return saccharide;
	}

	public void setSaccharide(int saccharide) {
		this.saccharide = saccharide;
	}

	public int getSodium() {
		return sodium;
	}

	public void setSodium(int sodium) {
		this.sodium = sodium;
	}

}
