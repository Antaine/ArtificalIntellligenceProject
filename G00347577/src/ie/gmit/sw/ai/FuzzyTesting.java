package ie.gmit.sw.ai;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class FuzzyTesting {

	private static final String FCL_FILE = ".././G00347577/src/ie/gmit/sw/ai/fcl/Chance.fcl";
	
	public  double getChance(int playerDistance, int numOfEnemies) {
		//Get FCL FILE
		FIS fis = FIS.load(FCL_FILE, true);
		//Get FunctionBLock
		FunctionBlock fb = fis.getFunctionBlock("getCatchChance");
		//Display Chart
		//JFuzzyChart.get().chart(fb);
		////Set Variables
		fis.setVariable("playerDistance", playerDistance);
		fis.setVariable("numOfEnemies", numOfEnemies);
		//Run Fuzzy Logic
		fis.evaluate();
		//Store Result
		Variable chase = fb.getVariable("catchChance");
		//Display Data
		//JFuzzyChart.get().chart(chase, chase.getDefuzzifier(),true);
		//Return Salary
		return (float) chase.getValue();
	}
	public static void main(String[] args) {
		FuzzyTesting f = new FuzzyTesting();
		double chance = f.getChance(1, 6);
		System.out.println(chance);
	}


}
