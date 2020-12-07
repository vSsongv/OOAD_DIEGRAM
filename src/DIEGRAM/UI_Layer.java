package DIEGRAM;

import java.util.*;

public class UI_Layer {

	public static DayDietSheet[] sheetList;
	public static int sheetNum;
	public static int year;
	public static int month;
	public static int day;
	public static Goal g;

	public static void main(String[] args) {

		Diegram.systemStart();
		Diegram.makeDummyData();
		printSYSInfo();

		Scanner in = new Scanner(System.in);
		
		g=DB_Handler.getUserGoal();
		if(g==null) {
			System.out.println("시스템을 사용하기 위해, 목표 설정이 필요합니다.");
			setGoal(in);
			g=DB_Handler.getUserGoal();
		}

		Calendar cal = Calendar.getInstance();
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;
		getSheetInfo(year, month);
		printSheetInfo(year, month);

		String input;
		int num;
		while (true) {

			System.out.println("원하시는 서비스를 선택해주세요.");
			System.out.println("\t1.일별 식단 기록표 보기");
			System.out.println("\t2.목표 수정");
			System.out.println("\t3.이전달 식단기록표 보기");
			System.out.println("\t4.다음달 식단기록표 보기");
			System.out.println("\t5.시스템 종료\n");

			input = in.nextLine();

			try {
				num = Integer.parseInt(input);
			} catch (Exception e) {
				System.out.println("\n실행시키고 싶으신 서비스를 숫자로 입력해주세요.\n");
				continue;
			}

			if (num == 1) {
				chooseDay(in);
				while (true) {
					getSheetInfo(year, month);
					printDaySheetInfo(sheetList[day - 1]);
					System.out.println("원하시는 서비스를 선택해주세요.");
					System.out.println("\t1.식단 기록");
					System.out.println("\t2.뒤로가기");
					input = in.nextLine();

					try {
						num = Integer.parseInt(input);
					} catch (Exception e) {
						System.out.println("\n실행시키고 싶으신 서비스를 숫자로 입력해주세요.\n");
						continue;
					}

					if (num == 1) {
						recordDiet(in);
					} else if (num == 2) {
						getSheetInfo(year, month);
						printSheetInfo(year, month);
						break;
					} else {
						System.out.println("\n실행시키고 싶으신 서비스의 번호를 올바르게 입력해주세요.\n");
					}
				}
			} else if (num == 2) {
				System.out.println("\n목표설정 실행\n");
				setGoal(in);
				g=DB_Handler.getUserGoal();
			} else if (num == 3) {
				month--;
				if (month == 0) {
					year--;
					month = 12;
				}
				getSheetInfo(year, month);
				printSheetInfo(year, month);
			} else if (num == 4) {
				month++;
				if (month == 13) {
					year++;
					month = 1;
				}
				getSheetInfo(year, month);
				printSheetInfo(year, month);
			} else if (num == 5) {
				System.out.println("\n시스템을 종료합니다.\n");
				break;
			} else {
				System.out.println("\n실행시키고 싶으신 서비스의 번호를 올바르게 입력해주세요.\n");
			}

		}

		in.close();
		
	}

	static void printSYSInfo() {

		System.out.println("***********************DIEGRAM*************************");
		System.out.println("*      달력에 먹은 식단을 입력하고, 섭취한 음식의 통계치를 볼 수 있 는              *");
		System.out.println("*                  당신을 위한 식단 관리 서비스                                       *");
		System.out.println("*******************************************************");

	}

	static void getSheetInfo(int year, int month) {

		sheetList = new DayDietSheet[31];
		sheetNum = 0;
		for (int i = 0; i < 31; i++) {
			sheetList[i] = DB_Handler.getSheet(year, month, i + 1);
			if (sheetList[i] != null) {
				sheetNum++;
			}
		}
	}

	static void printSheetInfo(int year, int month) {

		System.out.println("***************송진영님의 " + year + "년 " + month + "월 식단기록표*****************");
		System.out.println("권장 칼로리 : "+ g.getCalorieTarget()+"kcal");

		for (int i = 0; i < 31; i++) {
			if (sheetList[i] != null) {
				System.out.println((i + 1) + "일\t" + sheetList[i].getDayCalorieTotal() + "kcal");
			}
		}
		if (sheetNum == 0)
			System.out.println("기록 없음");

		System.out.println("*******************************************************");
	}

	static void chooseDay(Scanner in) {
		String input;
		while (true) {
			System.out.println("식단 기록표를 확인할 일자를 입력해주세요.");
			input = in.nextLine();

			try {
				day = Integer.parseInt(input);
				if (day < 1 || day > 31) {
					System.out.println("\n올바른 일자를 입력해주세요.\n");
					continue;
				}
			} catch (Exception e) {
				System.out.println("\n일자를 숫자로 입력해주세요.\n");
				continue;
			}

			break;
		}
	}

	static void printDaySheetInfo(DayDietSheet ds) {
		System.out.println("*******************************************************");
		System.out.println(year + "/" + month + "/" + day);
		if(sheetList[day - 1]==null) {
			System.out.println("(기록없음)");
		}
		else {
			for (Diet d : sheetList[day - 1].getD()) {
				if (d == null)
					break;
				printDiet(d);
			}
			System.out.println("총 섭취 칼로리 : " + ds.getDayCalorieTotal());
			System.out.println("총 섭취 나트륨 : " + ds.getDaysodiumTotal());
			System.out.println("총 섭취 당류 : " + ds.getDaysaccharideTotal());
			int sum = ds.getDaycarbohydrateTotal() * 4 + ds.getDayProteinTotal() * 4
					+ 9 * ds.getDayfatTotal();
			System.out.println("탄수화물   " + (ds.getDaycarbohydrateTotal() * 4 * 100 / sum) + "%" + " / 단백질   "
					+ (ds.getDayProteinTotal() * 4 * 100 / sum) + "%" + " / 지방   "
					+ (ds.getDayfatTotal() * 9 * 100 / sum) + "%");
		}
		System.out.println("*******************************************************");
	}

	static void printDiet(Diet d) {
		System.out.println("=======================================================");
		System.out.println("<" + d.getMealType() + ">");
		System.out.println("음식이름\t\t칼로리(kcal)\t섭취량(n인분)");
		for (Food f : d.getF()) {
			if (f == null)
				break;
			System.out.println(f.getName() + "\t\t" + f.getCalories() + "\t\t" + f.getAmount());
		}
		System.out.println("-------------------------------------------------------");
		System.out.println("섭취 칼로리 : " + d.getCalorieTotal());
		System.out.println("=======================================================");
	}

	static void printRecordDiet(Diet d) {

		System.out.println("*******************************************************");
		printDiet(d);
		int sum =d.getCarbohydrateTotal() * 4 + d.getProteinTotal() * 4 + d.getFatTotal()*9;
		System.out.println("총 섭취 칼로리 : " + d.getCalorieTotal());
		System.out.println("총 섭취 나트륨 : " + d.getSodiumTotal());
		System.out.println("총 섭취 당류 : " + d.getSaccharideTotal());
		if(sum==0) {
			System.out.println("탄수화물   0% / 단백질   0% / 지방   0%");
		}
		else {
			System.out.println("탄수화물 "+(d.getCarbohydrateTotal()*4*100/sum) +"%" 
								+ " / 단백질 " + (d.getProteinTotal()*4*100/sum) + "%" 
								+ " / 지방" +(d.getFatTotal()*9*100/sum) + "%");
		}
		System.out.println("*******************************************************");
	}

	static void recordDiet(Scanner in) {

		String input;

		DayDietSheet currentRecordSheet = Diegram.requestDayDietSheet(year, month, day);

		String mealType;

		while (true) {

			System.out.println("기록할 식단의 타입을 선택해주세요.");
			System.out.println("\t1.아침");
			System.out.println("\t2.점심");
			System.out.println("\t3.저녁");
			System.out.println("\t4.간식");

			input = in.nextLine();
			int num;
			try {
				num = Integer.parseInt(input);
			} catch (Exception e) {
				System.out.println("\n알맞은 번호를 입력해주세요.\n");
				continue;
			}
			if (num == 1) {
				mealType = "아침";
				break;
			} else if (num == 2) {
				mealType = "점심";
				break;
			} else if (num == 3) {
				mealType = "저녁";
				break;
			} else if (num == 4) {
				mealType = "간식";
				break;
			} else {
				System.out.println("\n알맞은 번호를 입력해주세요.\n");
				continue;
			}
		}

		Diegram.makeDiet(mealType);
		printRecordDiet(currentRecordSheet.getD()[currentRecordSheet.getCurrent()]);
		
		while(true) {
			System.out.println("음식을 추가하고 싶으시면 [음식이름/양(인분)]을, 기록을 완료하고 싶으시면 [완료]를 입력해주세요.");
			input = in.nextLine();
			
			String[] array;
			String foodName; int foodAmount;
			
			if(input.equals("완료")) {
				Diegram.endRecord();
				break;
			}
			else {
				try {
					array = input.split("/");
					foodName = array[0];
					foodAmount = Integer.parseInt(array[1]);
				} 
				catch (Exception e) {
					System.out.println("\n입력 형식을 확인하시고, 다시 입력해주세요.\n");
					continue;
				}
				Diegram.enterFood(foodName, foodAmount);
				printRecordDiet(currentRecordSheet.getD()[currentRecordSheet.getCurrent()]);
			}
			
			
		}

	}

	static void setGoal(Scanner in) {
		
		Diegram.makeSetGoal();
		
		int height, weight;
		String input;
		String[] array;
		while(true){
			System.out.println("키와 몸무게 정보를 입력해주세요. 입력형식: [키(cm)/몸무게(kg)], 정수형");
			try {
				input = in.nextLine();
				array = input.split("/");
				height = Integer.parseInt(array[0]);
				weight = Integer.parseInt(array[1]);
			} 
			catch (Exception e) {
				System.out.println("\n입력 형식을 확인해주세요.\n");
				continue;
			}
			break;
		}
		Diegram.registerInfo(height, weight);
		
		int activeVolume;
		while(true){
			System.out.println(" ------------------활동량 입력가이드-------------------");
			System.out.println("|  앉아서 주로 생활하거나 가벼운 움직임만 하며 활동량이 적은 경우 : 25  |");
			System.out.println("|  규칙적인 생활로 보통의 활동량을 가진 경우 : 30 ~ 35          |");
			System.out.println("|  육체노동 등 평소 신체 활동량이 많은 경우 : 40                |");
			System.out.println(" ------------------------------------------------");
			System.out.println("활동량 정보를 입력해주세요. 입력형식: [활동량], 정수형");
			try {
				input = in.nextLine();
				activeVolume = Integer.parseInt(input);
			} 
			catch (Exception e) {
				System.out.println("\n입력 형식을 확인해주세요.\n");
				continue;
			}
			break;
		}
		Diegram.registerActiveVolume(activeVolume);
		
		int cal = Diegram.analyzeRecommendedCalorie();
		System.out.println("당신의 권장 칼로리는 "+cal+"kcal 입니다.");
		System.out.println("이대로 체중 유지를 원하시면 위의 계산된 권장 칼로리를 입력해주시고,");
		System.out.println("수정을 원하시면 원하시는 권장 칼로리를 입력해주세요!");
		while(true){
			try {
				System.out.println("목표 칼로리를 입력해주세요. 입력형식 : [목표칼로리(kcal)], 정수형");
				input = in.nextLine();
				cal = Integer.parseInt(input);
			} 
			catch (Exception e) {
				System.out.println("\n입력 형식을 확인해주세요.\n");
				continue;
			}
			break;
		}
		Diegram.adjustCalorie(cal);
		
		int sodiumTarget, sugarTarget;
		while(true){
			try {
				System.out.println("당류와 나트륨 섭취량을 제한해야 한다면, 제한량을 입력해 주세요. (없으면 0을 입력해 주세요)");
				System.out.println("입력 형식 : [당류 제한량(g)/나트륨 제한량(mg)], 정수형");
				input = in.nextLine();
				array = input.split("/");
				sugarTarget = Integer.parseInt(array[0]);
				sodiumTarget = Integer.parseInt(array[1]);
			} 
			catch (Exception e) {
				System.out.println("\n입력 형식을 확인해주세요.\n");
				continue;
			}
			break;
		}
		Diegram.enterTarget(sodiumTarget, sugarTarget);
		
		Diegram.endSetGoal();
		
		System.out.println("\n목표 설정이 완료되었습니다!\n");
		
	}
}
