package experiment;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import Combinations.Combination;
import Combinations.FeatureCombination;
import Main.ProductGeneration;
import Main.TargetSystem;
import report.Record;
import report.RunListernerReport;
import specifications.Configuration;
import testset.ActionsTest;
import testset.ElevatorTest;
import testset.EnvironmentTest;
import testset.FloorTest;
import testset.MainTest;
import testset.PersonTest;

public class Challenge {

	public void executeJunitTestCase(ProductGeneration tools) {
		int count = 0;
		Record record = new Record();
		for (Combination combination : tools.getCombsForTest()) {
			for (FeatureCombination f : combination.getListFeatures()) {
				if (f.getName().equals("Base")) {
					Configuration.base = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("Weight")) {
					Configuration.weight = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("Empty")) {
					Configuration.empty = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("TwoThirdsFull")) {
					Configuration.twothirdsfull = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("ExecutiveFloor")) {
					Configuration.executivefloor = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("Overloaded")) {
					Configuration.overloaded = (f.getState().equals("0") ? false : true);
				}
			}
			System.err.println("Configuration:");
			Configuration.printProduct();
			System.out.println("");
			if (Configuration.validProduct()) {
				count++;

				JUnitCore junit = new JUnitCore();
				junit.addListener(new RunListernerReport(Configuration.returnProduct(), record));
				org.junit.runner.Result result = junit.run(ActionsTest.class, ElevatorTest.class, EnvironmentTest.class,
						FloorTest.class, MainTest.class, PersonTest.class);

				Configuration.printProduct();
				System.out.println("\n\n ----------------------- \n\n");
			} else {
				System.err.println("****** Invalid ******");
			}
		}
		try {
			record.record2();
		} catch (Exception e) {
		}
		System.out.println("product counter:" + count);

	}

	public void run(TargetSystem tg,String path) {
		ProductGeneration products = new ProductGeneration();
		products.run(tg, path);
		executeJunitTestCase(products);
	}

	public static void main(String[] args) {
		long startTime = 0;
	    long finishTime = 0;
	    int totalExecution = 10;
	    int index = 0;
	    finishTime = 0;
	    startTime = System.currentTimeMillis();
	    Challenge challenge = new Challenge();
	    while (index < totalExecution) {
		
		/** Chvatal **/
//		 String path =
//		 "C:/Users/Fischer_PC/Documents/2019_CSS_Journal/workspace_IncLing/Tools/Chvatal/elevator/products";

		/** ICPL **/
//		 String path =
//		 "C:/Users/Fischer_PC/Documents/2019_CSS_Journal/workspace_IncLing/Tools/ICPL/elevator/products";

		/** IncLing **/
//		 String path =
//		 "C:/Users/Fischer_PC/Documents/2019_CSS_Journal/workspace_IncLing/Tools/IncLing/elevator/products";

		/** Random **/
//		 String path =
//		 "C:/Users/Fischer_PC/Documents/2019_CSS_Journal/workspace_IncLing/Tools/RANDOM/elevator/products";

		/** all_valid_conf **/
		String path = "C:/Users/Fischer_PC/Documents/2019_CSS_Journal/workspace_IncLing/Tools/All_valid_conf/elevator/products";

		challenge.run(TargetSystem.ELEVATOR, path);
		finishTime = System.currentTimeMillis() - startTime;
	      index++;
	    }

	    float average = finishTime / index;

	    System.out.println("Total time (ms): " + finishTime + " time average (ms): "
	        + average + " number of times performed:" + index);

	}
}