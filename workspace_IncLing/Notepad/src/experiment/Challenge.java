package experiment;

import org.junit.runner.JUnitCore;
import Combinations.Combination;
import Combinations.FeatureCombination;
import Main.ProductGeneration;
import Main.TargetSystem;
import report.Record;
import report.RunListernerReport;
import specifications.Configuration;
import testset.*;

public class Challenge {

	public void executeJunitTestCase(ProductGeneration tools) {
		int count = 0;
		Record record = new Record();
		for (Combination combination : tools.getCombsForTest()) {
			for (FeatureCombination f : combination.getListFeatures()) {
				if (f.getName().equals("BASE")) {
					Configuration.BASE = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("BASEMENUBAR")) {
					Configuration.BASEMENUBAR = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("BASETOOLBAR")) {
					Configuration.BASETOOLBAR = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("EDITMENUBAR")) {
					Configuration.EDITMENUBAR = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("EDITTOOLBAR")) {
					Configuration.EDITTOOLBAR = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("FORMATMENUBAR")) {
					Configuration.FORMATMENUBAR = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("FORMATTOOLBAR")) {
					Configuration.FORMATTOOLBAR = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("PERSISTENCEMENUBAR")) {
					Configuration.PERSISTENCEMENUBAR = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("PERSISTENCETOOLBAR")) {
					Configuration.PERSISTENCETOOLBAR = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("PRINTMENUBAR")) {
					Configuration.PRINTMENUBAR = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("PRINTTOOLBAR")) {
					Configuration.PRINTTOOLBAR = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("SEARCHMENUBAR")) {
					Configuration.SEARCHMENUBAR = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("SEARCHTOOLBAR")) {
					Configuration.SEARCHTOOLBAR = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("UNDOREDOMENUBAR")) {
					Configuration.UNDOREDOMENUBAR = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("UNDOREDOTOOLBAR")) {
					Configuration.UNDOREDOTOOLBAR = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("WORDCOUNTTOOLBAR")) {
					Configuration.WORDCOUNTTOOLBAR = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("WORDCOUNTMENUBAR")) {
					Configuration.WORDCOUNTMENUBAR = (f.getState().equals("0") ? false : true);
				}

			}
			System.err.println("Configuration:");
			Configuration.productPrint();
			System.out.println("");
			if (Configuration.validProduct()) {
				count++;
				JUnitCore junit = new JUnitCore();
				junit.addListener(new RunListernerReport(Configuration.returnProduct(), record));
				org.junit.runner.Result result = junit.run(
						// TestEditMenuBar.class,
						TestEditToolBar.class, TestFileMenuBar.class, TestFileToolBar.class, TestFormatMenuBar.class,
						TestFormatToolBar.class, TestTextArea.class, TestHelp.class, TestWordCount.class,
						TestExample_Paulo.class, NotepadJUnitTest_lest.class);
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
		int totalExecution = 10;
		int index = 0;
		finishTime = 0;
		startTime = System.currentTimeMillis();
		Challenge challenge = new Challenge();
		while (index < totalExecution) {
			/** all_valid_conf **/
			String path = "C:/Users/Fischer_PC/Documents/2019_CSS_Journal/Challenge/workspace_IncLing/Tools/All_valid_conf/Notepad/products";
			challenge.run(TargetSystem.NOTEPAD, path);
			finishTime = System.currentTimeMillis() - startTime;
			index++;
		}

		float average = finishTime / index;
		System.out.println("Total time (ms): " + finishTime + " time average (s): " + (average/1000)
				+ " number of times performed:" + index);

	}
}