package ie.gmit.sw.ai.enemies;

import ie.gmit.sw.ai.Command;
import ie.gmit.sw.ai.GameModel;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class FuzzyEnemies implements Command  {
	private static final String FILE = ".././G00347577/src/ie/gmit/sw/ai/fcl/Ghost.fcl";

	FIS fis;
	Variable chase;
	GameModel model;
	
	double hp = 0;
	double stamina = 0;
	double confidence = 0;
	
	public FuzzyEnemies() {
		
	}

	public double getHp() {
		return hp;
	}
	
	public double getConfiedence() {
		return stamina;
	}
	
	public double getStamina() {
		return stamina;
	}

	public void setHp(double hp) {
		this.hp = hp;
	}

	public void setStamina(double stamina) {
		this.stamina = stamina;
	}
	
	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}
	
	public FuzzyEnemies(double hp, double stamina, double confidence) {
		this.hp = hp;
		this.stamina = stamina;
		this.confidence = confidence;
	}
	
	public void loadFuzz() {
		fis = FIS.load(FILE, true);
		fis.getFunctionBlock("ghost");
		fis.setVariable("hp", getHp());
		fis.setVariable("stamina", getStamina());
		//Add Number of enemies
		fis.evaluate();
		chase = fis.getVariable("catchChance");
		System.out.println("chase is " + chase.getValue());
		

	}
	
	@Override
	public void execute() {		
		if(chase.getValue() > 30) {
			System.out.println("Fuzzy Hit");
			attackPlayer();
		}
		else if(chase.getValue() < 25)
		{
			System.out.println("Rest");
		}
		else {
			System.out.println("Retreat");
		}
		
		
		
	}


	public void attackPlayer(){
		setStamina(getStamina() - 15);	 
		System.out.println("Stamina: " + getStamina());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void Rest(){
		setStamina(getStamina() + 5);	 
		setHp(getHp() + 5);	 
		System.out.println("Resting,\n Stamina: " + getStamina()+" \n Hp: "+getHp());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	

}
