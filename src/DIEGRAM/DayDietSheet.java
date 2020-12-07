package DIEGRAM;

public class DayDietSheet {
	
	private Diet[] d = new Diet[10];
	private int current = 0;
	private int year = 0;
	private int month = 0;
	private int day = 0;
	private int dayCalorieTotal = 0;
	private int dayProteinTotal = 0;
	private int dayfatTotal = 0;
	private int daysaccharideTotal = 0;
	private int daysodiumTotal = 0;
	private int daycarbohydrateTotal = 0;
	
	public DayDietSheet(int year, int month,int day)//creator
	{ 
		this.year = year; this.month = month; this.day = day;
		DB_Handler.getSheetInfo(this);
	} 
	
	public void makeDiet(String mealType)
	{
	   this.d[current] = new Diet(mealType);
	   return;
	}
	
	public void enterFood(String name, int amount) { 
		this.d[current].enterFood(name, amount);
	}
	
	public void endRecord() {
		this.dayCalorieTotal += this.d[current].getCalorieTotal();
		this.dayProteinTotal += this.d[current].getProteinTotal();
		this.daycarbohydrateTotal += this.d[current].getCarbohydrateTotal();
		this.dayfatTotal += this.d[current].getFatTotal();
		this.daysodiumTotal += this.d[current].getSodiumTotal();
		this.daysaccharideTotal += this.d[current].getSaccharideTotal();
		current++;
		DB_Handler.saveDayDietSheet(this);
	}

	public int getCurrent() {
		return current;
	}
	
	public void setD(Diet[] d) {
		this.d = d;
	}

	public void setDayCalorieTotal(int dayCalorieTotal) {
		this.dayCalorieTotal = dayCalorieTotal;
	}

	public void setDayfatTotal(int dayfatTotal) {
		this.dayfatTotal = dayfatTotal;
	}

	public void setDaysaccharideTotal(int daysaccharideTotal) {
		this.daysaccharideTotal = daysaccharideTotal;
	}

	public void setDaysodiumTotal(int daysodiumTotal) {
		this.daysodiumTotal = daysodiumTotal;
	}

	public void setDaycarbohydrateTotal(int daycarbohydrateTotal) {
		this.daycarbohydrateTotal = daycarbohydrateTotal;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getDayProteinTotal() {
		return dayProteinTotal;
	}

	public void setDayProteinTotal(int dayProteinTotal) {
		this.dayProteinTotal = dayProteinTotal;
	}

	public Diet[] getD() {
		return d;
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}


	public int getDay() {
		return day;
	}

	public int getDayCalorieTotal() {
		return dayCalorieTotal;
	}

	public int getDayfatTotal() {
		return dayfatTotal;
	}

	public int getDaysaccharideTotal() {
		return daysaccharideTotal;
	}

	public int getDaysodiumTotal() {
		return daysodiumTotal;
	}

	public int getDaycarbohydrateTotal() {
		return daycarbohydrateTotal;
	}
	
}
