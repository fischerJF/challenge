package org.argouml.specifications;

import guidsl.SATtest;
import guidsl.Tool;

public class Configuration {

	public static boolean LOGGING;
	public static boolean COGNITIVE;
	public static boolean ACTIVITYDIAGRAM;
	public static boolean COLLABORATIONDIAGRAM;
	public static boolean DEPLOYMENTDIAGRAM;
	public static boolean SEQUENCEDIAGRAM;
	public static boolean STATEDIAGRAM;
	public static boolean USECASEDIAGRAM;

	public static Tool tool = new Tool("modified-model.m");
	public static boolean makeCnfFile = true;
	public static boolean compatSelections = true;

	public static boolean validProduct() {

		SATtest t = new SATtest("test1", compatSelections, compatSelections);

		t.add(setlogging());
		t.add(setcognitive());
		t.add(setactivitydiagram());
		t.add(setcollaborationdiagram());
		t.add(setdeploymentdiagram());
		t.add(setsequencediagram());
		t.add(setstatediagram());
		t.add(setusecasediagram());
		return runTest(t, makeCnfFile);
	}

	public static boolean runTest(SATtest t, boolean compat) {
		return (tool.modelDebug(t, makeCnfFile)) ? true : false;
	}

	public static String setlogging() {
		return (LOGGING) ? "LOGGING___" : "-LOGGING___";
	}

	public static String setcognitive() {
		return (COGNITIVE) ? "COGNITIVE___" : "-COGNITIVE___";
	}

	public static String setactivitydiagram() {
		return (ACTIVITYDIAGRAM) ? "ACTIVITYDIAGRAM___" : "-ACTIVITYDIAGRAM___";
	}

	public static String setcollaborationdiagram() {
		return (COLLABORATIONDIAGRAM) ? "COLLABORATIONDIAGRAM___" : "-COLLABORATIONDIAGRAM___";
	}

	public static String setdeploymentdiagram() {
		return (DEPLOYMENTDIAGRAM) ? "DEPLOYMENTDIAGRAM___" : "-DEPLOYMENTDIAGRAM___";
	}

	public static String setsequencediagram() {
		return (SEQUENCEDIAGRAM) ? "SEQUENCEDIAGRAM___" : "-SEQUENCEDIAGRAM___";
	}

	public static String setstatediagram() {
		return (STATEDIAGRAM) ? "STATEDIAGRAM___" : "-STATEDIAGRAM___";
	}

	public static String setusecasediagram() {
		return (USECASEDIAGRAM) ? "USECASEDIAGRAM___" : "-USECASEDIAGRAM___";
	}

	public static void init(String... args) {
		int index = 0;
		LOGGING = Boolean.valueOf(args[index++]);
		COGNITIVE = Boolean.valueOf(args[index++]);
		ACTIVITYDIAGRAM = Boolean.valueOf(args[index++]);
		COLLABORATIONDIAGRAM = Boolean.valueOf(args[index++]);
		DEPLOYMENTDIAGRAM = Boolean.valueOf(args[index++]);
		SEQUENCEDIAGRAM = Boolean.valueOf(args[index++]);
		STATEDIAGRAM = Boolean.valueOf(args[index++]);
		USECASEDIAGRAM = Boolean.valueOf(args[index++]);
	}

	public static void productPrint() {
		System.out.println("logging:" + Configuration.LOGGING + "cognitive:" + Configuration.COGNITIVE
				+ "activitydiagram:" + Configuration.ACTIVITYDIAGRAM + "collaborationdiagram:"
				+ Configuration.COLLABORATIONDIAGRAM + "deploymentdiagram:" + Configuration.DEPLOYMENTDIAGRAM
				+ "sequencediagram:" + Configuration.SEQUENCEDIAGRAM + "statediagram:" + Configuration.STATEDIAGRAM
				+ "usecasediagram:" + Configuration.USECASEDIAGRAM);
	}

	public static String returnProduct() {
		return "LOGGING:" + Configuration.LOGGING + "COGNITIVE:" + Configuration.COGNITIVE + "ACTIVITYDIAGRAM:"
				+ Configuration.ACTIVITYDIAGRAM + "COLLABORATIONDIAGRAM:" + Configuration.COLLABORATIONDIAGRAM
				+ "DEPLOYMENTDIAGRAM:" + Configuration.DEPLOYMENTDIAGRAM + "SEQUENCEDIAGRAM:"
				+ Configuration.SEQUENCEDIAGRAM + "STATEDIAGRAM:" + Configuration.STATEDIAGRAM + "USECASEDIAGRAM:"
				+ Configuration.USECASEDIAGRAM;
	}
}