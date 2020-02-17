package experiment;

import org.junit.runner.JUnitCore;
import Combinations.Combination;
import Combinations.FeatureCombination;
import Main.ProductGeneration;
import Main.TargetSystem;
import report.Record;
import report.RunListernerReport;
import specifications.Configuration;
import testset.AccountTest;
import testset.ApplicationTest;
import testset.LogEntryTest;
import testset.TransactionTest;

public class Challenge {

	public void executeJunitTestCase(ProductGeneration tools) {
		int count = 0;
		Record record = new Record();
		for (Combination combination : tools.getCombsForTest()) {
			for (FeatureCombination f : combination.getListFeatures()) {
				if (f.getName().equals("TRANSACTIONLOG")) {
					Configuration.transactionlog = (f.getState().equals("0") ? false : true);
				}
				else if (f.getName().equals("TRANSACTION")) {
					Configuration.transaction = (f.getState().equals("0") ? false : true);
				}
				else if (f.getName().equals("OVERDRAFT")) {
					Configuration.overdraft = (f.getState().equals("0") ? false : true);
				}
				else if (f.getName().equals("LOGGING")) {
					Configuration.logging = (f.getState().equals("0") ? false : true);
				}
				else if (f.getName().equals("LOCK")) {
					Configuration.lock = (f.getState().equals("0") ? false : true);
				}
				else if (f.getName().equals("INTERESTESTIMATION")) {
					Configuration.interestestimation = (f.getState().equals("0") ? false : true);
				}
				else if (f.getName().equals("INTEREST")) {
					Configuration.interest = (f.getState().equals("0") ? false : true);
				}
				else if (f.getName().equals("CREDITWORTHINESS")) {
					Configuration.creditworthiness = (f.getState().equals("0") ? false : true);
				}
				else if (f.getName().equals("BANKACCOUNT")) {
					Configuration.bankaccount = (f.getState().equals("0") ? false : true);
				}
				else if (f.getName().equals("DAILYLIMIT")) {
					Configuration.dailylimit = (f.getState().equals("0") ? false : true);
				}
			}
			System.err.println("Configuration:");
			Configuration.productPrint();
			System.out.println("");
			if (Configuration.validProduct()) {
				count++;
				JUnitCore junit = new JUnitCore();
				junit.addListener(new RunListernerReport(Configuration.returnProduct(), record));
				org.junit.runner.Result result = junit.run(AccountTest.class, ApplicationTest.class,
						TransactionTest.class, LogEntryTest.class);
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
		while (index < totalExecution) {
			Challenge challenge = new Challenge();
			String path = "C:/Users/Fischer_PC/Documents/2019_CSS_Journal/workspace_IncLing/Tools/All_valid_conf/BankAccount/products";
			challenge.run(TargetSystem.BANKACCOUNT, path);
			index++;
		}
		finishTime = System.currentTimeMillis() - startTime;

		float average = finishTime / index;

		System.out.println("Total time (ms): " + finishTime + " time average (s): " + (average/1000)
				+ " number of times performed:" + index);
	}
}