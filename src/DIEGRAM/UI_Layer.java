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
	         
	         System.out.println("원하시는 서비스를 선택해주세요.");
	         System.out.println("\t1.일별 식단 기록표 보기");
	         System.out.println("\t2.목표 설정");
	         System.out.println("\t3.이전달 식단기록표 보기");
	         System.out.println("\t4.다음달 식단기록표 보기");
	         System.out.println("\t5.시스템 종료\n");
	         
	         input = in.nextLine();
	         
	         try {
	            num=Integer.parseInt(input);
	         }
	         catch(Exception e) {
	            System.out.println("\n실행시키고 싶으신 서비스를 숫자로 입력해주세요.\n");
	            continue;
	         }
	         
	         if(num==1) {
	        	chooseDay(in);
	        	printDaySheetInfo(day);
	        	while(true) {
			         System.out.println("원하시는 서비스를 선택해주세요.");
			         System.out.println("\t1.식단 기록");
			         System.out.println("\t2.뒤로가기");
			         input = in.nextLine();
			         
			         try {
			            num=Integer.parseInt(input);
			         }
			         catch(Exception e) {
			            System.out.println("\n실행시키고 싶으신 서비스를 숫자로 입력해주세요.\n");
			            continue;
			         }
			         
			         if(num==1) {
				            //recordDiet(in);			        	 
			         }
			         else if(num==2) {
			        	 break;
			         }
			         else {
				            System.out.println("\n실행시키고 싶으신 서비스의 번호를 올바르게 입력해주세요.\n");
			         }
	        	}
	         }
	         else if(num==2) {
	            System.out.println("\n목표설정 실행\n");
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
	        	 System.out.println("\n시스템을 종료합니다.\n");
		            break;
	         }
	         else {
	            System.out.println("\n실행시키고 싶으신 서비스의 번호를 올바르게 입력해주세요.\n");
	         }
	         
	      }
	      
	      
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
		      for(int i=0;i<31;i++) {
		    	  sheetList[i] = DB_Handler.getSheet(year,month,i+1);
		    	  if(sheetList[i]!=null) {
		    		  sheetNum++;
		    	  }
		      }
	   }
	   
	   static void printSheetInfo(int year, int month) {
		   
		      System.out.println("***************송진영님의 "+ year +"년 "+ month +"월 식단기록표*****************");
		      
		      for(int i=0;i<31;i++) {
		    	  if(sheetList[i]!=null) {
		    		  System.out.println((i+1)+"일\t"+sheetList[i].getDayCalorieTotal()+"kcal");
		    	  }
		      }
		      if(sheetNum==0)
		    	  System.out.println("기록 없음");
		      
		      System.out.println("*******************************************************");
	   }
	   
	   static void chooseDay(Scanner in) {
		   String input;
		   while(true) {
			   System.out.println("식단 기록표를 확인할 일자를 입력해주세요.");
			   input = in.nextLine();
			   
			   try {
				   day = Integer.parseInt(input);
				   if(day<1||day>31) {
					   System.out.println("\n올바른 일자를 입력해주세요.\n");
					   continue;
				   }
			   }
			   catch(Exception e) {
				   System.out.println("\n일자를 숫자로 입력해주세요.\n");
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
		   System.out.println("총 섭취 칼로리 : " + sheetList[day-1].getDayCalorieTotal());
		   System.out.println("총 섭취 나트륨 : " + sheetList[day-1].getDaysodiumTotal());
		   System.out.println("총 섭취 당류 : " + sheetList[day-1].getDaysaccharideTotal());
		   int sum = sheetList[day-1].getDaycarbohydrateTotal()*4+sheetList[day-1].getDayProteinTotal()*4+9*sheetList[day-1].getDayfatTotal();
		   System.out.println("탄수화물   "+(sheetList[day-1].getDaycarbohydrateTotal()*4*100/sum) + "%"
				   				+ "\t단백질   " + (sheetList[day-1].getDayProteinTotal()*4*100/sum) + "%"
				   				+ "\t\t지방   " + (sheetList[day-1].getDayfatTotal()*9*100/sum) + "%");
		   System.out.println("*******************************************************");
	   }
	   
	   static void printDiet(Diet d) {
		   System.out.println("=======================================================");
		   System.out.println("<"+ d.getMealType() +">");
		   System.out.println("음식이름\t\t칼로리(kcal)\t섭취량(n인분)");
		   for(Food f : d.getF()) {
			   if(f==null)
				   break;
			   System.out.println(f.getName() + "\t\t" + f.getCalories() + "\t\t" + f.getAmount());
		   }
		   System.out.println("-------------------------------------------------------");
		   System.out.println("섭취 칼로리 : " + d.getCalorieTotal());
		   System.out.println("=======================================================");
	   }
	   
		/*
		 * System.out.println("총 섭취 칼로리 : " + d.getCalorieTotal());
		 * System.out.println("총 섭취 나트륨 : " + d.getSodiumTotal());
		 * System.out.println("총 섭취 당류 : " + d.getSaccharideTotal());
		 * System.out.println("탄수화물 "+(d.getCarbohydrateTotal()/d.getCalorieTotal()*4) +
		 * "%" + "단백질 " + (d.getProteinTotal()/d.getCalorieTotal()*4) + "%" + "지방" +
		 * (d.getFatTotal()/d.getCalorieTotal()*9) + "%");
		 */
	   
	   static void recordDiet(Scanner in) {
		
		   String input;
		   
		   DayDietSheet currentRecordSheet = Diegram.requestDayDietSheet(year, month, day);
		   
		   String mealType;
		   
		   while(true) {
			   System.out.println("기록할 식단의 타입을");
			   input = in.nextLine();
			   
			   try {
				   String[] inputArray = input.split("-");
				   if(inputArray.length != 3) {
					   System.out.println("\n날짜를 올바른 형식으로 입력해주세요.\n");
			           continue;
				   }
				   year = Integer.parseInt(inputArray[0]);
				   month = Integer.parseInt(inputArray[1]);
				   day = Integer.parseInt(inputArray[2]);
			   }
			   catch(Exception e) {
				   System.out.println("\n날짜를 올바른 형식으로 입력해주세요.\n");
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
