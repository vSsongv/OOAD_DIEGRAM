package DIEGRAM;

import java.util.*;

public class Goal {

	private int weight;
	private int height;
	private int calorieTarget;
	private int activeVolume;
	private int sodiumTarget;
	private int sugarTarget;
	
	public Goal() //creator
	{
		initialzizeGoal();
	} 

	public void initialzizeGoal() //initialze attributes of Goal
	{
		this.weight = 0;
		this.height = 0;
		this.calorieTarget = 0;
		this.activeVolume = 0;
		this.sodiumTarget = 0;
		this.sugarTarget = 0;
		return;
	}
	
	public void registerInfo(int height, int weight) //User�� height,weight�� �Է¹ް� Class���� height,weight�� ����. 
	{
		this.height = height;
		this.weight = weight;
		return;
	}
	
	public void registerActiveVolume(int activeVolume) //User�� activeVolume�� �Է¹ް� Class���� activeVolume�� ����. 
	{
		this.activeVolume = activeVolume;
		return; 
	}
	
	public int analyzeRecommendedCalorie() //User�� ����Į�θ��� ����Ͽ� User���� ������.
	{
		int adjustWeiht = Integer.parseInt(String.valueOf(Math.round((height - 100) * 0.9)));
		calorieTarget = Integer.parseInt(String.valueOf(Math.round( adjustWeiht + ((weight - adjustWeiht) * 0.25))));
		return calorieTarget;
	}
	
	public void adjustCalorie(int calorie) //User���� User�� ���ϴ� Į�θ� �Է��� �޴´�.
	{
		calorieTarget = calorie;
		return;
	}
	
	
	public void eneterTarget(int sodiumTarget, int sugarTarget) //���̿�� ȯ�ڵ� ���� ���, ��Ʈ�� ���� �����ؾ� �Ǵ� User���� �ش� ���Ѱ��� �Է¹޴´�.
	{
		this.sodiumTarget = sodiumTarget;
		this.sugarTarget = sugarTarget;
		return;
	}
	
	public void endSetGoal() //User�� ������ Goal�� DataBase�� ������.
	{
		DB_Handler.addGoal(this);
		return;
	}
}
