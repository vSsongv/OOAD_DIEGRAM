package DIEGRAM;

import java.util.*;

public class UI_Layer {

	public static void main(String[] args) {
		
		 printSYSInfo();
	      
	      
	      String input;
	      int num;
	      Scanner in = new Scanner(System.in);
	      while(true) {
	         
	         System.out.println("���Ͻô� ���񽺸� �������ּ���.");
	         System.out.println("\t1.�Ĵ� �Է�");
	         System.out.println("\t2.��ǥ ����");
	         System.out.println("\t3.�ý��� ����\n");
	         
	         input = in.nextLine();
	         
	         try {
	            num=Integer.parseInt(input);
	         }
	         catch(Exception e) {
	            System.out.println("\n�����Ű�� ������ ���񽺸� ���ڷ� �Է����ּ���.\n");
	            continue;
	         }
	         
	         if(num==1) {
	            System.out.println("\n�Ĵ��Է� ����\n");
	            recordDiet();
	         }
	         else if(num==2) {
	            System.out.println("\n��ǥ���� ����\n");
	            setGoal();
	         }
	         else if(num==3) {
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
	      System.out.println("*           �޷¿� ���� �Ĵ��� �Է��ϰ�, ���ġ�� �� �� �ִ�                      *");
	      System.out.println("*                  ����� ���� �Ĵ� ���� ����                                     *");
	      System.out.println("*                                                     *");
	      System.out.println("*******************************************************");
	      
	      
	   }
	   
	   static void recordDiet() {
	      
	   }

	   
	   static void setGoal() {
	      
	      
	   }
	}
