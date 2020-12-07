 package DIEGRAM;

import java.util.*;

public class UI_Layer {
	
	public static DayDietSheet[] sheetList;
	public static int sheetNum;
	public static int year;
	public static int month;
	public static int day;

	public static void main(String[] args) {
		
		 Diegram.systemStart();
		 Diegram.makeDummyData();
		 printSYSInfo();
		 
	      Calendar cal = Calendar.getInstance();
	  	  year = cal.get(Calendar.YEAR);
	  	  month = cal.get(Calendar.MONTH) + 1;
	  	  getSheetInfo(year,month);
	      printSheetInfo(year, month);
	      
	      String input;
	      int num;
	      Scanner in = new Scanner(System.in);
	      while(true) {
	         
	         System.out.println("���Ͻô� ���񽺸� �������ּ���.");
	         System.out.println("\t1.�Ϻ� �Ĵ� ���ǥ ����");
	         System.out.println("\t2.��ǥ ����");
	         System.out.println("\t3.������ �Ĵܱ��ǥ ����");
	         System.out.println("\t4.������ �Ĵܱ��ǥ ����");
	         System.out.println("\t5.�ý��� ����\n");
	         
	         input = in.nextLine();
	         
	         try {
	            num=Integer.parseInt(input);
	         }
	         catch(Exception e) {
	            System.out.println("\n�����Ű�� ������ ���񽺸� ���ڷ� �Է����ּ���.\n");
	            continue;
	         }
	         
	         if(num==1) {
	        	chooseDay(in);
	        	printDaySheetInfo(day);
	        	while(true) {
			         System.out.println("���Ͻô� ���񽺸� �������ּ���.");
			         System.out.println("\t1.�Ĵ� ���");
			         System.out.println("\t2.�ڷΰ���");
			         input = in.nextLine();
			         
			         try {
			            num=Integer.parseInt(input);
			         }
			         catch(Exception e) {
			            System.out.println("\n�����Ű�� ������ ���񽺸� ���ڷ� �Է����ּ���.\n");
			            continue;
			         }
			         
			         if(num==1) {
				            //recordDiet(in);			        	 
			         }
			         else if(num==2) {
			        	 break;
			         }
			         else {
				            System.out.println("\n�����Ű�� ������ ������ ��ȣ�� �ùٸ��� �Է����ּ���.\n");
			         }
	        	}
	         }
	         else if(num==2) {
	            System.out.println("\n��ǥ���� ����\n");
	            setGoal();
	         }
	         else if(num==3) {
	        	 month--;
	        	 if(month==0) {
	        		 year--;
	        		 month = 12;
	        	 }
	        	 getSheetInfo(year,month);
	        	 printSheetInfo(year, month);
	         }
	         else if(num==4) {
	        	 month++;
	        	 if(month==13) {
	        		 year++;
	        		 month = 1;
	        	 }
	        	 getSheetInfo(year,month);
	        	 printSheetInfo(year, month);
	         }
	         else if(num==5) {
	        	 System.out.println("\n�ý����� �����մϴ�.\n");
		            break;
	         }
	         else {
	            System.out.println("\n�����Ű�� ������ ������ ��ȣ�� �ùٸ��� �Է����ּ���.\n");
	         }
	         
	      }
	      
	      
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
		      for(int i=0;i<31;i++) {
		    	  sheetList[i] = DB_Handler.getSheet(year,month,i+1);
		    	  if(sheetList[i]!=null) {
		    		  sheetNum++;
		    	  }
		      }
	   }
	   
	   static void printSheetInfo(int year, int month) {
		   
		      System.out.println("***************���������� "+ year +"�� "+ month +"�� �Ĵܱ��ǥ*****************");
		      
		      for(int i=0;i<31;i++) {
		    	  if(sheetList[i]!=null) {
		    		  System.out.println((i+1)+"��\t"+sheetList[i].getDayCalorieTotal()+"kcal");
		    	  }
		      }
		      if(sheetNum==0)
		    	  System.out.println("��� ����");
		      
		      System.out.println("*******************************************************");
	   }
	   
	   static void chooseDay(Scanner in) {
		   String input;
		   while(true) {
			   System.out.println("�Ĵ� ���ǥ�� Ȯ���� ���ڸ� �Է����ּ���.");
			   input = in.nextLine();
			   
			   try {
				   day = Integer.parseInt(input);
				   if(day<1||day>31) {
					   System.out.println("\n�ùٸ� ���ڸ� �Է����ּ���.\n");
					   continue;
				   }
			   }
			   catch(Exception e) {
				   System.out.println("\n���ڸ� ���ڷ� �Է����ּ���.\n");
		           continue;
			   }
			   
			   break;
		   }
	   }
	   
	   static void printDaySheetInfo(int day) {
		   System.out.println("*******************************************************");
		   System.out.println(year+"/"+month+"/"+day);
		   for(Diet d : sheetList[day-1].getD()) {
			   if(d==null)
				   break;
			   printDiet(d);
		   }
		   System.out.println("�� ���� Į�θ� : " + sheetList[day-1].getDayCalorieTotal());
		   System.out.println("�� ���� ��Ʈ�� : " + sheetList[day-1].getDaysodiumTotal());
		   System.out.println("�� ���� ��� : " + sheetList[day-1].getDaysaccharideTotal());
		   int sum = sheetList[day-1].getDaycarbohydrateTotal()*4+sheetList[day-1].getDayProteinTotal()*4+9*sheetList[day-1].getDayfatTotal();
		   System.out.println("ź��ȭ��   "+(sheetList[day-1].getDaycarbohydrateTotal()*4*100/sum) + "%"
				   				+ "\t�ܹ���   " + (sheetList[day-1].getDayProteinTotal()*4*100/sum) + "%"
				   				+ "\t\t����   " + (sheetList[day-1].getDayfatTotal()*9*100/sum) + "%");
		   System.out.println("*******************************************************");
	   }
	   
	   static void printDiet(Diet d) {
		   System.out.println("=======================================================");
		   System.out.println("<"+ d.getMealType() +">");
		   System.out.println("�����̸�\t\tĮ�θ�(kcal)\t���뷮(n�κ�)");
		   for(Food f : d.getF()) {
			   if(f==null)
				   break;
			   System.out.println(f.getName() + "\t\t" + f.getCalories() + "\t\t" + f.getAmount());
		   }
		   System.out.println("-------------------------------------------------------");
		   System.out.println("���� Į�θ� : " + d.getCalorieTotal());
		   System.out.println("=======================================================");
	   }
	   
		/*
		 * System.out.println("�� ���� Į�θ� : " + d.getCalorieTotal());
		 * System.out.println("�� ���� ��Ʈ�� : " + d.getSodiumTotal());
		 * System.out.println("�� ���� ��� : " + d.getSaccharideTotal());
		 * System.out.println("ź��ȭ�� "+(d.getCarbohydrateTotal()/d.getCalorieTotal()*4) +
		 * "%" + "�ܹ��� " + (d.getProteinTotal()/d.getCalorieTotal()*4) + "%" + "����" +
		 * (d.getFatTotal()/d.getCalorieTotal()*9) + "%");
		 */
	   
	   static void recordDiet(Scanner in) {
		
		   String input;
		   
		   DayDietSheet currentRecordSheet = Diegram.requestDayDietSheet(year, month, day);
		   
		   String mealType;
		   
		   while(true) {
			   System.out.println("����� �Ĵ��� Ÿ����");
			   input = in.nextLine();
			   
			   try {
				   String[] inputArray = input.split("-");
				   if(inputArray.length != 3) {
					   System.out.println("\n��¥�� �ùٸ� �������� �Է����ּ���.\n");
			           continue;
				   }
				   year = Integer.parseInt(inputArray[0]);
				   month = Integer.parseInt(inputArray[1]);
				   day = Integer.parseInt(inputArray[2]);
			   }
			   catch(Exception e) {
				   System.out.println("\n��¥�� �ùٸ� �������� �Է����ּ���.\n");
		           continue;
			   }
			   
			   break;
		   }
		   
		   //Diegram.makeDiet(mealType);
		   String foodName; int foodAmount;
		  // Diegram.enterFood(foodName, foodAmount);
		   //Diegram.endRecord();
	   }

	   
	   static void setGoal() {
	      
	      
	   }
	}
