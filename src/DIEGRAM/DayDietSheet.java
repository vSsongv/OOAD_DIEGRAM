package DIEGRAM;

public class DayDietSheet extends Diegram{
	
	private Diet[] d = new Diet[10];
	private int current = 0;
	private int year = 0;
	private int month = 0;
	private int day = 0;
	private int dayCalorieTotal = 0;
	private int dayfatTotal = 0;
	private int daysaccharideTotal = 0;
	private int daysodiumTotal = 0;
	private int daycarbohydrateTotal = 0;
	
	public DayDietSheet(int year, int month,int day)//creator
	{ 
		this.year = year; this.month = month; this.day = day;
		DB_Handler.getSheetInfo(this);
	} 
	
	@Override
	public void makeDiet(String mealType)
	{
	   this.d[current] = new Diet(mealType);
	   return;
	}
	
	public void enterFood(String name, int amount) { }

	public Diet[] getD() {
		return d;
	}

	public void setD(Diet[] d) {
		this.d = d;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getDayCalorieTotal() {
		return dayCalorieTotal;
	}

	public void setDayCalorieTotal(int dayCalorieTotal) {
		this.dayCalorieTotal = dayCalorieTotal;
	}

	public int getDayfatTotal() {
		return dayfatTotal;
	}

	public void setDayfatTotal(int dayfatTotal) {
		this.dayfatTotal = dayfatTotal;
	}

	public int getDaysaccharideTotal() {
		return daysaccharideTotal;
	}

	public void setDaysaccharideTotal(int daysaccharideTotal) {
		this.daysaccharideTotal = daysaccharideTotal;
	}

	public int getDaysodiumTotal() {
		return daysodiumTotal;
	}

	public void setDaysodiumTotal(int daysodiumTotal) {
		this.daysodiumTotal = daysodiumTotal;
	}

	public int getDaycarbohydrateTotal() {
		return daycarbohydrateTotal;
	}

	public void setDaycarbohydrateTotal(int daycarbohydrateTotal) {
		this.daycarbohydrateTotal = daycarbohydrateTotal;
	};		
}
