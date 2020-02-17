package experiment;

import org.junit.runner.JUnitCore;
import Combinations.Combination;
import Combinations.FeatureCombination;
import Main.ProductGeneration;
import Main.TargetSystem;
import report.Record;
import report.RunListernerReport;
import specifications.Configuration;
import testset.ATMTest;
import testset.ATMUserInterfaceTest;
import testset.ATMUserInterfaceTest2;
import testset.AccountTest;
import testset.BalanceInquiryTest;
import testset.BankDatabaseTest;
import testset.CashDispenserTest;
import testset.DepositTest;
import testset.LoggerTest;
import testset.WithdrawalTest;

public class Challenge {

	public void executeJunitTestCase(ProductGeneration tools) {
		int count = 0;
		Record record = new Record();
		for (Combination combination : tools.getCombsForTest()) {
			for (FeatureCombination f : combination.getListFeatures()) {
				if (f.getName().equals("LOGGING")) {
					Configuration.LOGGING = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("DEPOSITING")) {
					Configuration.DEPOSITING = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("WITHDRAWING")) {
					Configuration.WITHDRAWING = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("BALANCE_INQUIRY")) {
					Configuration.BALANCE_INQUIRY = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("ADMIN_CONTROL")) {
					Configuration.ADMIN_CONTROL = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("USER_INTERFACE")) {
					Configuration.USER_INTERFACE = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("WITHDRAWING_ALL_VALUES")) {
					Configuration.WITHDRAWING_ALL_VALUES = (f.getState().equals("0") ? false : true);
				}

			}

			System.err.println("Configuration:");
			Configuration.productPrint();
			System.out.println("");
			if (Configuration.validProduct()) {
				count++;
				JUnitCore junit = new JUnitCore();
				junit.addListener(new RunListernerReport(Configuration.returnProduct(), record));
				org.junit.runner.Result result = junit.run(AccountTest.class, ATMTest.class, ATMUserInterfaceTest.class,
						ATMUserInterfaceTest2.class, BalanceInquiryTest.class, BankDatabaseTest.class,
						CashDispenserTest.class, DepositTest.class, LoggerTest.class, WithdrawalTest.class);
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
			String path = "C:/Users/Fischer_PC/Documents/2019_CSS_Journal/workspace_IncLing/Tools/IncLing/ATM/products";
			challenge.run(TargetSystem.ATM, path);
			finishTime = System.currentTimeMillis() - startTime;
			index++;
		}

		float average = finishTime / index;

		System.out.println("Total time (ms): " + finishTime + " time average (s): " + (average / 1000)
				+ " number of times performed:" + index);

	}
}