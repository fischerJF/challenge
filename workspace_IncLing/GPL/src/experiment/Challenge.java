package experiment;

import org.junit.runner.JUnitCore;

import Combinations.Combination;
import Combinations.FeatureCombination;
import Main.*;
import report.Record;
import report.RunListernerReport;
import specifications.Configuration;
import tests.ConnectedTests_Caio;
import tests.CycleRelated_Caio;
import tests.CycleWorkSpaceTest;
import tests.GraphReturnTests_Caio;
import tests.GraphTest;
import tests.JavaUtilityTest;
import tests.MainTest;
import tests.MultiFeatureTest_Caio;
import tests.NetworkGeneratorTest;
import tests.NumberTests_Caio;
import tests.TestSuite_NEW;
import tests.TreeGeneratorTest;

public class Challenge {

	public void executeJunitTestCase(ProductGeneration tools) {
		int count = 0;
		Record record = new Record();
		for (Combination combination : tools.getCombsForTest()) {
			for (FeatureCombination f : combination.getListFeatures()) {
				if (f.getName().equals("BASE")) {
					Configuration.base = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("DIRECTED")) {
					Configuration.DIRECTED = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("UNDIRECTED")) {
					Configuration.UNDIRECTED = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("WEIGHTED")) {
					Configuration.WEIGHTED = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("SEARCH")) {
					Configuration.SEARCH = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("BFS")) {
					Configuration.BFS = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("NUMBER")) {
					Configuration.NUMBER = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("CONNECTED")) {
					Configuration.CONNECTED = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("STRONGLYCONNECTED")) {
					Configuration.STRONGLYCONNECTED = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("CYCLE")) {
					Configuration.CYCLE = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("MSTPRIM")) {
					Configuration.MSTPRIM = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("MSTKRUSKAL")) {
					Configuration.MSTKRUSKAL = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("SHORTEST")) {
					Configuration.SHORTEST = (f.getState().equals("0") ? false : true);
				}
			}
			System.err.println("Configuration:");
			Configuration.productPrint();
			System.out.println("");
			if (Configuration.validProduct()) {
				count++;

				JUnitCore junit = new JUnitCore();
				junit.addListener(new RunListernerReport(Configuration.returnProduct(), record));
				org.junit.runner.Result result = junit.run(ConnectedTests_Caio.class, CycleRelated_Caio.class,
						ConnectedTests_Caio.class, CycleRelated_Caio.class, CycleWorkSpaceTest.class,
						GraphReturnTests_Caio.class, GraphTest.class, JavaUtilityTest.class, MainTest.class,
						MultiFeatureTest_Caio.class, NetworkGeneratorTest.class, NumberTests_Caio.class,
						TestSuite_NEW.class, TreeGeneratorTest.class);
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
		int totalExecution = 1;
		int index = 0;
		finishTime = 0;
		startTime = System.currentTimeMillis();
		Challenge challenge = new Challenge();
		while (index < totalExecution) {

			/** all_valid_conf **/
			String path = "C:/Users/Fischer_PC/Documents/2019_CSS_Journal/workspace_IncLing/Tools/All_valid_conf/GPL/products";
			challenge.run(TargetSystem.GPL, path);
			finishTime = System.currentTimeMillis() - startTime;
			index++;
		}
		float average = finishTime / index;
		System.out.println("Total time (ms): " + finishTime + " time average (s): " + (average / 1000)
				+ " number of times performed:" + index);
	}
}