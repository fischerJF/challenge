package experiment;

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

	public void executeJunitTestCase(ProductGeneration tools) {
		int count = 0;
		Record record = new Record();
		for (Combination combination : tools.getCombsForTest()) {
			for (FeatureCombination f : combination.getListFeatures()) {
				if (f.getName().equals("SkipTrack")) {
					Configuration.skiptrack = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("Metadata")) {
					Configuration.metadata = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("RemoveTrack")) {
					Configuration.removetrack = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("Album")) {
					Configuration.album = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("WAV")) {
					Configuration.wav = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("NiceToHave")) {
					Configuration.nicetohave = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("Playlist")) {
					Configuration.playlist = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("JumpPosition")) {
					Configuration.jumpposition = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("Light")) {
					Configuration.light = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("OpenFolder")) {
					Configuration.openfolder = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("GUI")) {
					Configuration.gui = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("FeatureAMP")) {
					Configuration.featureamp = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("QueueTrack")) {
					Configuration.queuetrack = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("Mute")) {
					Configuration.mute = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("TagEditor")) {
					Configuration.tageditor = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("Tracknumber")) {
					Configuration.tracknumber = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("Codecs")) {
					Configuration.codecs = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("Progress")) {
					Configuration.progress = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("AAC")) {
					Configuration.aac = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("PlaylistControls")) {
					Configuration.playlistcontrols = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("MultiplePlaylists")) {
					Configuration.multipleplaylists = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("RandomColor")) {
					Configuration.randomcolor = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("SaveAndLoad")) {
					Configuration.saveandload = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("ShuffleRepeat")) {
					Configuration.shufflerepeat = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("Base")) {
					Configuration.base = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("OGG")) {
					Configuration.ogg = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("YouTube")) {
					Configuration.youtube = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("MP3")) {
					Configuration.mp3 = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("OSColors")) {
					Configuration.oscolors = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("Reorder")) {
					Configuration.reorder = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("Cover")) {
					Configuration.cover = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("Volume")) {
					Configuration.volume = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("Skins")) {
					Configuration.skins = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("Dark")) {
					Configuration.dark = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("ClearPlaylist")) {
					Configuration.clearplaylist = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("RemeberStatus")) {
					Configuration.remeberstatus = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("Progressbar")) {
					Configuration.progressbar = (f.getState().equals("0") ? false : true);
				} else if (f.getName().equals("Titlebar")) {
					Configuration.titlebar = (f.getState().equals("0") ? false : true);
				}

			}
			System.err.println("Configuration:");
			Configuration.productPrint();
			System.out.println("");
			if (Configuration.validProduct()) {
				count++;
				JUnitCore junit = new JUnitCore();
				junit.addListener(new RunListernerReport(Configuration.returnProduct(), record));
				org.junit.runner.Result result = junit.run(AppGUITest.class);
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
			String path = "C:/Users/Fischer_PC/Documents/2019_CSS_Journal/workspace_IncLing/Tools/All_valid_conf/FeatureAMP6/products";
			challenge.run(TargetSystem.FeatureAMP6, path);
			finishTime = System.currentTimeMillis() - startTime;
			index++;
		}

		float average = finishTime / index;

		System.out.println("Total time (ms): " + finishTime + " time average (s): " + (average / 1000) + average
				+ " number of times performed:" + index);

	}
}