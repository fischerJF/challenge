package experiment;

import org.junit.runner.JUnitCore;
import Combinations.Combination;
import Combinations.FeatureCombination;
import Main.ProductGeneration;
import Main.TargetSystem;
import report.Record;
import report.RunListernerReport;
import specifications.Configuration;
import testset.AI_PlayerTest;
import testset.BishopTest;
import testset.BoardPanelTest;
import testset.BoardTest;
import testset.KingTest;
import testset.KnightTest;
import testset.MVCTest;
import testset.ModelTest;
import testset.PawnTest;
import testset.PieceTest;
import testset.PlayerTest;
import testset.QueenTest;
import testset.RookTest;
import testset.SpaceTest;
import testset.ViewTest;

public class Challenge {

	public void executeJunitTestCase(ProductGeneration tools) {
		int count = 0;
		Record record = new Record();
		for (Combination combination : tools.getCombsForTest()) {
			for (FeatureCombination f : combination.getListFeatures()) {
				if (f.getName().equals("AI_PLAYER")) {
					Configuration.AI_PLAYER = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("ONLINE_PLAYER")) {
					Configuration.ONLINE_PLAYER = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("OFFLINE_PLAYER")) {
					Configuration.OFFLINE_PLAYER = (f.getState().equals("0") ? false : true);
				}
			}
			System.err.println("Configuration:");
			Configuration.productPrint();
			System.out.println("");
			if (Configuration.validProduct()) {
				count++;
				JUnitCore junit = new JUnitCore();
				junit.addListener(new RunListernerReport(Configuration.returnProduct(), record));
				org.junit.runner.Result result = junit.run(AI_PlayerTest.class, BishopTest.class, BoardPanelTest.class,
						BoardTest.class, KingTest.class, KnightTest.class, ModelTest.class, MVCTest.class,
						PawnTest.class, PieceTest.class, PlayerTest.class, QueenTest.class, RookTest.class,
						SpaceTest.class, ViewTest.class);
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
			String path = "C:/Users/Fischer_PC/Documents/2019_CSS_Journal/workspace_IncLing/Tools/All_valid_conf/Chess/products";
			challenge.run(TargetSystem.CHESS, path);
			finishTime = System.currentTimeMillis() - startTime;
			index++;
		}

		float average = finishTime / index;

		System.out.println("Total time (ms): " + finishTime + " time average (s): " + (average / 1000) + average
				+ " number of times performed:" + index);

	}
}