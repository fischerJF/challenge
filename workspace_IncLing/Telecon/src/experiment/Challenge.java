package experiment;

import org.junit.runner.JUnitCore;
import Combinations.Combination;
import Combinations.FeatureCombination;
import Main.ProductGeneration;
import Main.TargetSystem;
import report.Record;
import report.RunListernerReport;
import specifications.Configuration;
import testset.AbstractSimulationTest;
import testset.BasicSimulationTest;
import testset.CallTest;
import testset.CustomerTest;
import testset.HistoricTest;
import testset.LocalTest;
import testset.LongDistanceTest;
import testset.TimerTest;

public class Challenge {

	public void executeJunitTestCase(ProductGeneration tools) {
		int count = 0;
		Record record = new Record();
		for (Combination combination : tools.getCombsForTest()) {
			for (FeatureCombination f : combination.getListFeatures()) {
				if (f.getName().equals("HISTORIC")) {
					Configuration.HISTORIC = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("TIMING")) {
					Configuration.TIMING = (f.getState().equals("0") ? false : true);
				}
			}
			Configuration.Telecon = true;
			System.err.println("Configuration:");
			Configuration.productPrint();
			System.out.println("");
			if (Configuration.validProduct()) {
				count++;
				JUnitCore junit = new JUnitCore();
				junit.addListener(new RunListernerReport(Configuration.returnProduct(), record));
				org.junit.runner.Result result = junit.run(AbstractSimulationTest.class, BasicSimulationTest.class,
						CallTest.class, CustomerTest.class, HistoricTest.class, LocalTest.class, LongDistanceTest.class,
						TimerTest.class);
				Configuration.productPrint();
				System.out.println("\n\n ----------------------- \n\n");
			} else {
				System.err.println("****** Invalid ******");
			}
		}
		try {
			record.record2();
		} catch (Exception e) {
		}
		System.out.println("Configurations count:" + count);

	}

	public void run(TargetSystem tg, String path) {
		ProductGeneration products = new ProductGeneration();
		products.run(tg, path);
		executeJunitTestCase(products);
	}

	public static void main(String[] args) {
		long startTime = 0;
		long finishTime = 0;
		int totalExecution = 5;
		int index = 0;
		finishTime = 0;
		startTime = System.currentTimeMillis();
		Challenge challenge = new Challenge();
		while (index < totalExecution) {
			/** all_valid_conf **/
			String path = "C:/Users/Fischer_PC/Documents/2019_CSS_Journal/Challenge_github/workspace_IncLing/Tools/All_valid_conf/Telecom/products";
			challenge.run(TargetSystem.TELECOM, path);
			finishTime = System.currentTimeMillis() - startTime;
			index++;
		}

		float average = finishTime / index;
		System.out.println("Total time (ms): " + finishTime + " time average (s): " + (average / 1000)
				+ " number of times performed:" + index);

	}
}