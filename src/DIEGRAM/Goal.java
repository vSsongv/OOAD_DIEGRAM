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
	
	public void registerInfo(int height, int weight) //User의 height,weight를 입력받고 Class내의 height,weight에 저장. 
	{
		this.height = height;
		this.weight = weight;
		return;
	}
	
	public void registerActiveVolume(int activeVolume) //User의 activeVolume를 입력받고 Class내의 activeVolume에 저장. 
	{
		this.activeVolume = activeVolume;
		return; 
	}
	
	public int analyzeRecommendedCalorie() //User의 권장칼로리를 계산하여 User에게 보여줌.
	{
		int adjustWeiht = Integer.parseInt(String.valueOf(Math.round((height - 100) * 0.9)));
		calorieTarget = Integer.parseInt(String.valueOf(Math.round( adjustWeiht + ((weight - adjustWeiht) * 0.25))));
		return calorieTarget;
	}
	
	public void adjustCalorie(int calorie) //User에게 User가 원하는 칼로리 입력을 받는다.
	{
		calorieTarget = calorie;
		return;
	}
	
	
	public void eneterTarget(int sodiumTarget, int sugarTarget) //식이요법 환자들 같은 당류, 나트륨 량을 조절해야 되는 User에게 해당 제한값을 입력받는다.
	{
		this.sodiumTarget = sodiumTarget;
		this.sugarTarget = sugarTarget;
		return;
	}
	
	public void endSetGoal() //User가 설정한 Goal을 DataBase에 저장함.
	{
		DB_Handler.addGoal(this);
		return;
	}
}
