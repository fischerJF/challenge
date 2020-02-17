package experiment;

import org.junit.runner.JUnitCore;
import Combinations.Combination;
import Combinations.FeatureCombination;
import Main.ProductGeneration;
import Main.TargetSystem;
import report.Record;
import report.RunListernerReport;
import specifications.Configuration;
import testset.InTest;
import testset.StopwatchTest;
import testset.TestPerformance;
import testset.TestUF;
import testset.UnionFindTest;

public class Challenge {

	public void executeJunitTestCase(ProductGeneration tools) {
		int count = 0;
		Record record = new Record();
		for (Combination combination : tools.getCombsForTest()) {
			for (FeatureCombination f : combination.getListFeatures()) {
				if (f.getName().equals("WQU_ByHeight")) {
					Configuration.wqu_byheight = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("QuickFind")) {
					Configuration.quickfind = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("QU_Weighted_Modifications")) {
					Configuration.qu_weighted_modifications = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("UnionFind")) {
					Configuration.unionfind = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("QU_Weighted")) {
					Configuration.qu_weighted = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("UnionFindSPL")) {
					Configuration.unionfindspl = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("QuickUnion")) {
					Configuration.quickunion = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("WQU_Halfing")) {
					Configuration.wqu_halfing = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("WQU_PathCompression")) {
					Configuration.wqu_pathcompression = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("Tests")) {
					Configuration.tests = (f.getState().equals("0") ? false : true);
				}
				// mandatory
				Configuration.unionfind = true;
				Configuration.tests = true;
				Configuration.unionfindspl = true;
			}
			System.err.println("Configuration:");
			Configuration.productPrint();
			System.out.println("");
			if (Configuration.validProduct()) {
				count++;
				JUnitCore junit = new JUnitCore();
				junit.addListener(new RunListernerReport(Configuration.returnProduct(), record));
				org.junit.runner.Result result = junit.run(InTest.class, StopwatchTest.class, TestPerformance.class,
						TestUF.class, UnionFindTest.class);
				Configuration.productPrint();
				System.out.println("\n\n ----------------------- \n\n");
			} else {
				System.err.println("****** Invalid ******");
			}
		}
		try {
			record.record2();
		} catch (Exception e) {
			// TODO: handle exception
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
		int totalExecution = 10;
		int index = 0;
		finishTime = 0;
		startTime = System.currentTimeMillis();
		Challenge challenge = new Challenge();
		while (index < totalExecution) {
			/** all_valid_conf **/
			 String path =
			 "C:/Users/Fischer_PC/Documents/2019_CSS_Journal/Challenge/workspace_IncLing/Tools/All_valid_conf/UnionFind/products";
			challenge.run(TargetSystem.UNIONFIND, path);
			finishTime = System.currentTimeMillis() - startTime;
			index++;
		}

		float average = finishTime / index;

		System.out.println("Total time (ms): " + finishTime + " time average (s): " + (average / 1000)
				+ " number of times performed:" + index);

	}
}