package DIEGRAM;

import java.util.*;

public class UI_Layer {

	public static void main(String[] args) {
		
		 printSYSInfo();
	      
	      
	      String input;
	      int num;
	      Scanner in = new Scanner(System.in);
	      while(true) {
	         
	         System.out.println("원하시는 서비스를 선택해주세요.");
	         System.out.println("\t1.식단 입력");
	         System.out.println("\t2.목표 설정");
	         System.out.println("\t3.시스템 종료\n");
	         
	         input = in.nextLine();
	         
	         try {
	            num=Integer.parseInt(input);
	         }
	         catch(Exception e) {
	            System.out.println("\n실행시키고 싶으신 서비스를 숫자로 입력해주세요.\n");
	            continue;
	         }
	         
	         if(num==1) {
	            System.out.println("\n식단입력 실행\n");
	            recordDiet();
	         }
	         else if(num==2) {
	            System.out.println("\n목표설정 실행\n");
	            setGoal();
	         }
	         else if(num==3) {
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
	      System.out.println("*           달력에 먹은 식단을 입력하고, 통계치를 볼 수 있는                      *");
	      System.out.println("*                  당신을 위한 식단 관리 서비스                                     *");
	      System.out.println("*                                                     *");
	      System.out.println("*******************************************************");
	      
	      
	   }
	   
	   static void recordDiet() {
	      
	   }

	   
	   static void setGoal() {
	      
	      
	   }
	}
