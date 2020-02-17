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
import testset.ApplicationGUITest;
import testset.ID3TagTest;
import testset.MP3PlayerTest;
import testset.ZuordnungTest;

public class Challenge {

	public void executeJunitTestCase(ProductGeneration tools) {
		int cont = 0;
		Record record = new Record();
		for (Combination combination : tools.getCombsForTest()) {
			for (FeatureCombination f : combination.getListFeatures()) {
				if (f.getName().equals("VolumeControl")) {
					Configuration.volumecontrol = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("SkipTrack")) {
					Configuration.skiptrack = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("PlayEngine")) {
					Configuration.playengine = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("RemoveTrack")) {
					Configuration.removetrack = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("WAV")) {
					Configuration.wav = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("ReorderPlaylist")) {
					Configuration.reorderplaylist = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("Playlist")) {
					Configuration.playlist = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("Control")) {
					Configuration.control = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("Light")) {
					Configuration.light = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("SaveAndLoadPlaylist")) {
					Configuration.saveandloadplaylist = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("GUI")) {
					Configuration.gui = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("FeatureAMP")) {
					Configuration.featureamp = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("FileSupport")) {
					Configuration.filesupport = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("QueueTrack")) {
					Configuration.queuetrack = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("ProgressBar")) {
					Configuration.progressbar = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("Mute")) {
					Configuration.mute = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("ShowTime")) {
					Configuration.showtime = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("LoadFolder")) {
					Configuration.loadfolder = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("TrackTime")) {
					Configuration.tracktime = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("ShuffleRepeat")) {
					Configuration.shufflerepeat = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("OGG")) {
					Configuration.ogg = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("MP3")) {
					Configuration.mp3 = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("Skins")) {
					Configuration.skins = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("Dark")) {
					Configuration.dark = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("ChooseFile")) {
					Configuration.choosefile = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("ClearPlaylist")) {
					Configuration.clearplaylist = (f.getState().equals("0") ? false : true);
				}
				if (f.getName().equals("showCover")) {
					Configuration.showcover = (f.getState().equals("0") ? false : true);
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
				org.junit.runner.Result result = junit.run(ApplicationGUITest.class, ID3TagTest.class,
						MP3PlayerTest.class, ZuordnungTest.class);
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
			String path = "C:/Users/Fischer_PC/Documents/2019_CSS_Journal/workspace_IncLing/Tools/All_valid_conf/FeatureAMP8/products";
			challenge.run(TargetSystem.FeatureAMP8, path);
			finishTime = System.currentTimeMillis() - startTime;
			index++;
		}

		float average = finishTime / index;

		System.out.println("Total time (ms): " + finishTime + " time average (ms): " + average
				+ " number of times performed:" + index);
	}
}