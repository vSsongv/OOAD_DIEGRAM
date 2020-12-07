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
			System.out.println("�ý����� ����ϱ� ����, ��ǥ ������ �ʿ��մϴ�.");
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

			System.out.println("���Ͻô� ���񽺸� �������ּ���.");
			System.out.println("\t1.�Ϻ� �Ĵ� ���ǥ ����");
			System.out.println("\t2.��ǥ ����");
			System.out.println("\t3.������ �Ĵܱ��ǥ ����");
			System.out.println("\t4.������ �Ĵܱ��ǥ ����");
			System.out.println("\t5.�ý��� ����\n");

			input = in.nextLine();

			try {
				num = Integer.parseInt(input);
			} catch (Exception e) {
				System.out.println("\n�����Ű�� ������ ���񽺸� ���ڷ� �Է����ּ���.\n");
				continue;
			}

			if (num == 1) {
				chooseDay(in);
				while (true) {
					getSheetInfo(year, month);
					printDaySheetInfo(sheetList[day - 1]);
					System.out.println("���Ͻô� ���񽺸� �������ּ���.");
					System.out.println("\t1.�Ĵ� ���");
					System.out.println("\t2.�ڷΰ���");
					input = in.nextLine();

					try {
						num = Integer.parseInt(input);
					} catch (Exception e) {
						System.out.println("\n�����Ű�� ������ ���񽺸� ���ڷ� �Է����ּ���.\n");
						continue;
					}

					if (num == 1) {
						recordDiet(in);
					} else if (num == 2) {
						getSheetInfo(year, month);
						printSheetInfo(year, month);
						break;
					} else {
						System.out.println("\n�����Ű�� ������ ������ ��ȣ�� �ùٸ��� �Է����ּ���.\n");
					}
				}
			} else if (num == 2) {
				System.out.println("\n��ǥ���� ����\n");
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
				System.out.println("\n�ý����� �����մϴ�.\n");
				break;
			} else {
				System.out.println("\n�����Ű�� ������ ������ ��ȣ�� �ùٸ��� �Է����ּ���.\n");
			}

		}

		in.close();
		
	}

	static void printSYSInfo() {

		System.out.println("***********************DIEGRAM*************************");
		System.out.println("*      �޷¿� ���� �Ĵ��� �Է��ϰ�, ������ ������ ���ġ�� �� �� �� ��              *");
		System.out.println("*                  ����� ���� �Ĵ� ���� ����                                       *");
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

		System.out.println("***************���������� " + year + "�� " + month + "�� �Ĵܱ��ǥ*****************");
		System.out.println("���� Į�θ� : "+ g.getCalorieTarget()+"kcal");

		for (int i = 0; i < 31; i++) {
			if (sheetList[i] != null) {
				System.out.println((i + 1) + "��\t" + sheetList[i].getDayCalorieTotal() + "kcal");
			}
		}
		if (sheetNum == 0)
			System.out.println("��� ����");

		System.out.println("*******************************************************");
	}

	static void chooseDay(Scanner in) {
		String input;
		while (true) {
			System.out.println("�Ĵ� ���ǥ�� Ȯ���� ���ڸ� �Է����ּ���.");
			input = in.nextLine();

			try {
				day = Integer.parseInt(input);
				if (day < 1 || day > 31) {
					System.out.println("\n�ùٸ� ���ڸ� �Է����ּ���.\n");
					continue;
				}
			} catch (Exception e) {
				System.out.println("\n���ڸ� ���ڷ� �Է����ּ���.\n");
				continue;
			}

			break;
		}
	}

	static void printDaySheetInfo(DayDietSheet ds) {
		System.out.println("*******************************************************");
		System.out.println(year + "/" + month + "/" + day);
		if(sheetList[day - 1]==null) {
			System.out.println("(��Ͼ���)");
		}
		else {
			for (Diet d : sheetList[day - 1].getD()) {
				if (d == null)
					break;
				printDiet(d);
			}
			System.out.println("�� ���� Į�θ� : " + ds.getDayCalorieTotal());
			System.out.println("�� ���� ��Ʈ�� : " + ds.getDaysodiumTotal());
			System.out.println("�� ���� ��� : " + ds.getDaysaccharideTotal());
			int sum = ds.getDaycarbohydrateTotal() * 4 + ds.getDayProteinTotal() * 4
					+ 9 * ds.getDayfatTotal();
			System.out.println("ź��ȭ��   " + (ds.getDaycarbohydrateTotal() * 4 * 100 / sum) + "%" + " / �ܹ���   "
					+ (ds.getDayProteinTotal() * 4 * 100 / sum) + "%" + " / ����   "
					+ (ds.getDayfatTotal() * 9 * 100 / sum) + "%");
		}
		System.out.println("*******************************************************");
	}

	static void printDiet(Diet d) {
		System.out.println("=======================================================");
		System.out.println("<" + d.getMealType() + ">");
		System.out.println("�����̸�\t\tĮ�θ�(kcal)\t���뷮(n�κ�)");
		for (Food f : d.getF()) {
			if (f == null)
				break;
			System.out.println(f.getName() + "\t\t" + f.getCalories() + "\t\t" + f.getAmount());
		}
		System.out.println("-------------------------------------------------------");
		System.out.println("���� Į�θ� : " + d.getCalorieTotal());
		System.out.println("=======================================================");
	}

	static void printRecordDiet(Diet d) {

		System.out.println("*******************************************************");
		printDiet(d);
		int sum =d.getCarbohydrateTotal() * 4 + d.getProteinTotal() * 4 + d.getFatTotal()*9;
		System.out.println("�� ���� Į�θ� : " + d.getCalorieTotal());
		System.out.println("�� ���� ��Ʈ�� : " + d.getSodiumTotal());
		System.out.println("�� ���� ��� : " + d.getSaccharideTotal());
		if(sum==0) {
			System.out.println("ź��ȭ��   0% / �ܹ���   0% / ����   0%");
		}
		else {
			System.out.println("ź��ȭ�� "+(d.getCarbohydrateTotal()*4*100/sum) +"%" 
								+ " / �ܹ��� " + (d.getProteinTotal()*4*100/sum) + "%" 
								+ " / ����" +(d.getFatTotal()*9*100/sum) + "%");
		}
		System.out.println("*******************************************************");
	}

	static void recordDiet(Scanner in) {

		String input;

		DayDietSheet currentRecordSheet = Diegram.requestDayDietSheet(year, month, day);

		String mealType;

		while (true) {

			System.out.println("����� �Ĵ��� Ÿ���� �������ּ���.");
			System.out.println("\t1.��ħ");
			System.out.println("\t2.����");
			System.out.println("\t3.����");
			System.out.println("\t4.����");

			input = in.nextLine();
			int num;
			try {
				num = Integer.parseInt(input);
			} catch (Exception e) {
				System.out.println("\n�˸��� ��ȣ�� �Է����ּ���.\n");
				continue;
			}
			if (num == 1) {
				mealType = "��ħ";
				break;
			} else if (num == 2) {
				mealType = "����";
				break;
			} else if (num == 3) {
				mealType = "����";
				break;
			} else if (num == 4) {
				mealType = "����";
				break;
			} else {
				System.out.println("\n�˸��� ��ȣ�� �Է����ּ���.\n");
				continue;
			}
		}

		Diegram.makeDiet(mealType);
		printRecordDiet(currentRecordSheet.getD()[currentRecordSheet.getCurrent()]);
		
		while(true) {
			System.out.println("������ �߰��ϰ� �����ø� [�����̸�/��(�κ�)]��, ����� �Ϸ��ϰ� �����ø� [�Ϸ�]�� �Է����ּ���.");
			input = in.nextLine();
			
			String[] array;
			String foodName; int foodAmount;
			
			if(input.equals("�Ϸ�")) {
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
					System.out.println("\n�Է� ������ Ȯ���Ͻð�, �ٽ� �Է����ּ���.\n");
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
			System.out.println("Ű�� ������ ������ �Է����ּ���. �Է�����: [Ű(cm)/������(kg)], ������");
			try {
				input = in.nextLine();
				array = input.split("/");
				height = Integer.parseInt(array[0]);
				weight = Integer.parseInt(array[1]);
			} 
			catch (Exception e) {
				System.out.println("\n�Է� ������ Ȯ�����ּ���.\n");
				continue;
			}
			break;
		}
		Diegram.registerInfo(height, weight);
		
		int activeVolume;
		while(true){
			System.out.println(" ------------------Ȱ���� �Է°��̵�-------------------");
			System.out.println("|  �ɾƼ� �ַ� ��Ȱ�ϰų� ������ �����Ӹ� �ϸ� Ȱ������ ���� ��� : 25  |");
			System.out.println("|  ��Ģ���� ��Ȱ�� ������ Ȱ������ ���� ��� : 30 ~ 35          |");
			System.out.println("|  ��ü�뵿 �� ��� ��ü Ȱ������ ���� ��� : 40                |");
			System.out.println(" ------------------------------------------------");
			System.out.println("Ȱ���� ������ �Է����ּ���. �Է�����: [Ȱ����], ������");
			try {
				input = in.nextLine();
				activeVolume = Integer.parseInt(input);
			} 
			catch (Exception e) {
				System.out.println("\n�Է� ������ Ȯ�����ּ���.\n");
				continue;
			}
			break;
		}
		Diegram.registerActiveVolume(activeVolume);
		
		int cal = Diegram.analyzeRecommendedCalorie();
		System.out.println("����� ���� Į�θ��� "+cal+"kcal �Դϴ�.");
		System.out.println("�̴�� ü�� ������ ���Ͻø� ���� ���� ���� Į�θ��� �Է����ֽð�,");
		System.out.println("������ ���Ͻø� ���Ͻô� ���� Į�θ��� �Է����ּ���!");
		while(true){
			try {
				System.out.println("��ǥ Į�θ��� �Է����ּ���. �Է����� : [��ǥĮ�θ�(kcal)], ������");
				input = in.nextLine();
				cal = Integer.parseInt(input);
			} 
			catch (Exception e) {
				System.out.println("\n�Է� ������ Ȯ�����ּ���.\n");
				continue;
			}
			break;
		}
		Diegram.adjustCalorie(cal);
		
		int sodiumTarget, sugarTarget;
		while(true){
			try {
				System.out.println("����� ��Ʈ�� ���뷮�� �����ؾ� �Ѵٸ�, ���ѷ��� �Է��� �ּ���. (������ 0�� �Է��� �ּ���)");
				System.out.println("�Է� ���� : [��� ���ѷ�(g)/��Ʈ�� ���ѷ�(mg)], ������");
				input = in.nextLine();
				array = input.split("/");
				sugarTarget = Integer.parseInt(array[0]);
				sodiumTarget = Integer.parseInt(array[1]);
			} 
			catch (Exception e) {
				System.out.println("\n�Է� ������ Ȯ�����ּ���.\n");
				continue;
			}
			break;
		}
		Diegram.enterTarget(sodiumTarget, sugarTarget);
		
		Diegram.endSetGoal();
		
		System.out.println("\n��ǥ ������ �Ϸ�Ǿ����ϴ�!\n");
		
	}
}
