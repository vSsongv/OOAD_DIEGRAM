package DIEGRAM;

public class Diet{
   
	   private Food[] f = new Food[15];
	   
	   private String mealType;
	   private int numOfFood;
	   private int calorieTotal;
	   private int fatTotal;
	   private int proteinTotal;
	   private int saccharideTotal;
	   private int sodiumTotal;
	   private int carbohydrateTotal;
	   
	   public Diet(String mealType) {
	       this.mealType = mealType;
	       this.numOfFood = 0;
	      this.calorieTotal = 0;
	      this.fatTotal = 0;
	      this.proteinTotal = 0;
	      this.saccharideTotal = 0;
	      this.sodiumTotal = 0;
	      this.carbohydrateTotal = 0;
	   }
	   
	   public void enterFood(String name, int amount) //f를 만들고, f attributes내용들을 찾아 Total nutrients 값을 갱신.
	   {
	      f[numOfFood] = new Food(name, amount);
	      this.calorieTotal += f[numOfFood].getCalories();
	      this.proteinTotal += f[numOfFood].getProtein();
	      this.fatTotal += f[numOfFood].getFat();
	      this.saccharideTotal += f[numOfFood].getSaccharide();
	      this.sodiumTotal += f[numOfFood].getSodium();	      
	      this.numOfFood++;
	      return;
	   }
	   
	   public int getCalorieTotal() {
	       return this.calorieTotal;
	   }

}
