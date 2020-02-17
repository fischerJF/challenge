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
import testset.AppGUITest;


public class Challenge {
	
	public  void executeJunitTestCase(ProductGeneration tools) {
		int cont = 0;
		Record record = new Record();
		for (Combination combination : tools.getCombsForTest()) {
			for (FeatureCombination f : combination.getListFeatures()) {
				if (f.getName().equals("SKINS")) {
					Configuration.skins = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("PLAYER_CONTROL")) {
					Configuration.player_control = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("REORDER_PLAYLIST")) {
					Configuration.reorder_playlist = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("TITLE_TIME")) {
					Configuration.title_time = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("SKIP_TRACK")) {
					Configuration.skip_track = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("PROGRESS")) {
					Configuration.progress = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("CLEAR_PLAYLIST")) {
					Configuration.clear_playlist = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("PROGRESS_BAR")) {
					Configuration.progress_bar = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("VOLUME_CONTROL")) {
					Configuration.volume_control = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("REMOVE_TRACK")) {
					Configuration.remove_track = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("LIGHT")) {
					Configuration.light = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("DARK")) {
					Configuration.dark = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("SHUFFLE_REPEAT")) {
					Configuration.shuffle_repeat = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("SHOW_COVER")) {
					Configuration.show_cover = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("OGG")) {
					Configuration.ogg = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("MP3")) {
					Configuration.mp3 = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("SAVE_LOAD_PLAYLIST")) {
					Configuration.save_load_playlist = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("BASE_FEATUREAMP")) {
					Configuration.base_featureamp = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("LOAD_FOLDER")) {
					Configuration.load_folder = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("QUEUE_TRACK")) {
					Configuration.queue_track = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("FILE_SUPPORT")) {
					Configuration.file_support = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("PLAYER_BAR")) {
					Configuration.player_bar = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("MUTE")) {
					Configuration.mute = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("ID3_TITLE")) {
					Configuration.id3_title = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("PLAYLIST")) {
					Configuration.playlist = (f.getState().equals("0") ? false : true);
				}
			}
			System.err.println("Configuration:");
			Configuration.productPrint();
			System.out.println("");
			if (Configuration.validProduct()) {
				cont++;
				/* Chama a bibioteca core do junit para rodar a suite de testes */
				JUnitCore junit = new JUnitCore();
				junit.addListener(new RunListernerReport(Configuration.returnProduct(), record));
				org.junit.runner.Result result = junit.run(
						AppGUITest.class
				    );
				/* fim core junit */
				System.err.println("cont: " + cont + "((( apos os testes))) ");
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
		System.out.println("Contador de produtos:" + cont);

	}
	public void run(TargetSystem tg,String path) {
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

			
			
			String path = "C:/Users/Fischer_PC/Documents/2019_CSS_Journal/Challenge/workspace_IncLing/Tools/All_valid_conf/FeatureAMP4/products";
    	
		
		challenge.run(TargetSystem.FeatureAMP4, path);
		finishTime = System.currentTimeMillis() - startTime;
		index++;
	}
	
	float average = finishTime / index;

	System.out.println("Total time (ms): " + finishTime + " time average (s): " + (average/1000)
			+ " number of times performed:" + index);

 }
}