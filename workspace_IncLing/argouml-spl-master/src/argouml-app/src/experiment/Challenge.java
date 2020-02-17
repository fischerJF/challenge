package experiment;

import org.argouml.application.api.GUITestCommandLineInterface;
import org.argouml.application.api.GUITestInitSubsystem;
import org.argouml.cognitive.TestCritic;
import org.argouml.cognitive.TestDecision;
import org.argouml.cognitive.TestGoal;
import org.argouml.cognitive.TestGoalModel;
import org.argouml.cognitive.TestItemUID;
import org.argouml.cognitive.TestResolvedCritic;
import org.argouml.cognitive.TestStandardCM;
import org.argouml.cognitive.TestToDoItem;
import org.argouml.cognitive.TestToDoItemList;
import org.argouml.cognitive.checklist.TestCheckItem;
import org.argouml.cognitive.checklist.TestChecklist;
import org.argouml.cognitive.checklist.TestChecklistStatus;
import org.argouml.cognitive.checklist.ui.TestTabChecklist;
import org.argouml.cognitive.critics.TestSnoozeOrder;
import org.argouml.kernel.TestProject;
import org.argouml.kernel.TestProjectSettings;
import org.argouml.kernel.TestProjectWithProfiles;
import org.argouml.model.TestActivityGraphsFactory;
import org.argouml.model.TestActivityGraphsHelper;
import org.argouml.model.TestCollaborationsFactory;
import org.argouml.model.TestCollaborationsHelper;
import org.argouml.model.TestCommonBehaviorFactory;
import org.argouml.model.TestCommonBehaviorHelper;
import org.argouml.model.TestCopyHelper;
import org.argouml.model.TestCoreFactory;
import org.argouml.model.TestCoreHelper;
import org.argouml.model.TestDataTypesFactory;
import org.argouml.model.TestExtensionMechanismsFactory;
import org.argouml.model.TestExtensionMechanismsHelper;
import org.argouml.model.TestModel2;
import org.argouml.model.TestModelEventPump;
import org.argouml.model.TestModelFacade2;
import org.argouml.model.TestModelManagementFactory;
import org.argouml.model.TestModelManagementHelper;
import org.argouml.model.TestStateMachinesFactory;
import org.argouml.model.TestStateMachinesHelper;
import org.argouml.model.TestUml;
import org.argouml.model.TestUmlActor;
import org.argouml.model.TestUmlExtend;
import org.argouml.model.TestUmlFactory;
import org.argouml.model.TestUmlFactoryBuildNode;
import org.argouml.model.TestUmlGeneralization;
import org.argouml.model.TestUmlModel;
import org.argouml.model.TestUmlModelElement;
import org.argouml.model.TestUmlUseCase;
import org.argouml.model.TestUseCasesFactory;
import org.argouml.model.TestUseCasesHelper;
import org.argouml.moduleloader.GUITestModuleLoader2;
import org.argouml.moduleloader.ModuleInterfaceForTesting1;
import org.argouml.notation.TestNotationProvider;
import org.argouml.notation.TestNotationSettings;
import org.argouml.persistence.TestPersistenceManager;
import org.argouml.persistence.TestProfileConfigurationFilePersister;
import org.argouml.persistence.TestXmiFilePersister;
import org.argouml.persistence.TestZargoFilePersister;
import org.argouml.profile.TestCoreProfileReference;
import org.argouml.profile.TestProfileManager;
import org.argouml.profile.TestProfileMother;
import org.argouml.profile.TestProfileReference;
import org.argouml.profile.TestReaderModelLoader;
import org.argouml.profile.TestSubsystemInit;
import org.argouml.profile.TestUserDefinedProfile;
import org.argouml.profile.TestUserProfileReference;
import org.argouml.specifications.Configuration;
import org.argouml.ui.GUITestMultiEditorPane;
import org.argouml.ui.GUITestProjectBrowser;
import org.argouml.ui.TestDetailsPane;
import org.argouml.ui.TestStylePanel;
import org.argouml.ui.TestTabText;
import org.argouml.uml.TestProfileDefault;
import org.argouml.uml.TestUUIDManager;
import org.argouml.uml.cognitive.critics.AbstractTestCrTooMany;
import org.argouml.uml.cognitive.critics.AbstractTestMissingName;
import org.argouml.uml.cognitive.critics.TestCrAssocNameConflict;
import org.argouml.uml.cognitive.critics.TestCrDupParamName;
import org.argouml.uml.cognitive.critics.TestCrInvalidHistory;
import org.argouml.uml.cognitive.critics.TestCrInvalidInitial;
import org.argouml.uml.cognitive.critics.TestCrMissingAttrName;
import org.argouml.uml.cognitive.critics.TestCrMissingClassName;
import org.argouml.uml.cognitive.critics.TestCrMissingOperName;
import org.argouml.uml.cognitive.critics.TestCrMissingStateName;
import org.argouml.uml.cognitive.critics.TestCrNameConflict;
import org.argouml.uml.cognitive.critics.TestCrNoAssociations;
import org.argouml.uml.cognitive.critics.TestCrNoInitialState;
import org.argouml.uml.cognitive.critics.TestCrTooManyAssoc;
import org.argouml.uml.cognitive.critics.TestCrTooManyAttr;
import org.argouml.uml.cognitive.critics.TestCrTooManyOper;
import org.argouml.uml.cognitive.critics.TestCrTooManyStates;
import org.argouml.uml.cognitive.critics.TestCrTooManyTransitions;
import org.argouml.uml.cognitive.critics.TestCrUnconventionalAttrName;
import org.argouml.uml.cognitive.critics.TestCrUnconventionalClassName;
import org.argouml.uml.cognitive.critics.TestCrUnconventionalOperName;
import org.argouml.uml.cognitive.critics.TestCrUnconventionalPackName;
import org.argouml.uml.diagram.TestDiagramFactory;
import org.argouml.uml.diagram.TestDiagramSettings;
import org.argouml.uml.diagram.activity.ui.TestUMLActivityDiagram;
import org.argouml.uml.diagram.collaboration.ui.TestUMLCollaborationDiagram;
import org.argouml.uml.diagram.deployment.ui.TestUMLDeploymentDiagram;
import org.argouml.uml.diagram.sequence.ui.TestUMLSequenceDiagram;
import org.argouml.uml.diagram.state.ui.TestFigClonable;
import org.argouml.uml.diagram.state.ui.TestUMLStateDiagram;
import org.argouml.uml.diagram.static_structure.ui.TestUMLClassDiagram;
import org.argouml.uml.diagram.ui.TestActionAddAllClassesFromModel;
import org.argouml.uml.diagram.ui.TestTabDiagram;
import org.argouml.uml.diagram.use_case.ui.TestUMLUseCaseDiagram;
import org.argouml.uml.ui.AbstractTestActionAddDiagram;
//import org.argouml.uml.ui.AbstractUMLModelElementListModel2Test;
import org.argouml.uml.ui.GUITestActionOpenProject;
import org.argouml.uml.ui.GUITestActionSaveGraphics;
import org.argouml.uml.ui.MockUMLUserInterfaceContainer;
import org.argouml.uml.ui.TestActionActivityGraphDiagram;
import org.argouml.uml.ui.TestActionClassDiagram;
import org.argouml.uml.ui.TestActionCollaborationDiagram;
import org.argouml.uml.ui.TestActionDeploymentDiagram;
import org.argouml.uml.ui.TestActionStateDiagram;
import org.argouml.uml.ui.TestActionUseCaseDiagram;
import org.argouml.uml.ui.TestSourcePathController;
import org.argouml.uml.ui.TestTabStyle;
import org.argouml.uml.ui.TestTabs;
import org.argouml.uml.ui.TestUMLTagDefinitionComboBoxModel;
import org.argouml.uml.ui.behavior.collaborations.TestUMLAssociationEndRoleBaseListModel;
import org.argouml.uml.ui.behavior.collaborations.TestUMLAssociationRoleAssociationEndRoleListModel;
import org.argouml.uml.ui.behavior.collaborations.TestUMLAssociationRoleBaseComboBoxModel;
import org.argouml.uml.ui.behavior.collaborations.TestUMLAssociationRoleMessageListModel;
import org.argouml.uml.ui.behavior.collaborations.TestUMLClassifierRoleAvailableContentsListModel;
import org.argouml.uml.ui.behavior.collaborations.TestUMLClassifierRoleAvailableFeaturesListModel;
import org.argouml.uml.ui.behavior.collaborations.TestUMLClassifierRoleBaseListModel;
import org.argouml.uml.ui.behavior.collaborations.TestUMLCollaborationConstraintListModel;
import org.argouml.uml.ui.behavior.collaborations.TestUMLCollaborationRepresentedClassifierComboBoxModel;
import org.argouml.uml.ui.behavior.collaborations.TestUMLCollaborationRepresentedOperationComboBoxModel;
import org.argouml.uml.ui.behavior.collaborations.TestUMLInteractionCollaborationListModel;
import org.argouml.uml.ui.behavior.collaborations.TestUMLInteractionMessagesListModel;
import org.argouml.uml.ui.behavior.collaborations.TestUMLMessageActionListModel;
import org.argouml.uml.ui.behavior.collaborations.TestUMLMessageActivatorComboBoxModel;
import org.argouml.uml.ui.behavior.collaborations.TestUMLMessagePredecessorListModel;
import org.argouml.uml.ui.behavior.collaborations.TestUMLMessageReceiverListModel;
import org.argouml.uml.ui.behavior.collaborations.TestUMLMessageSenderListModel;
import org.argouml.uml.ui.behavior.common_behavior.TestActionNewReception;
import org.argouml.uml.ui.behavior.common_behavior.TestUMLReceptionSignalComboBoxModel;
import org.argouml.uml.ui.behavior.use_cases.TestUMLExtendExtensionPointListModel;
import org.argouml.uml.ui.behavior.use_cases.TestUMLExtensionPointExtendListModel;
import org.argouml.uml.ui.behavior.use_cases.TestUMLExtensionPointLocationDocument;
import org.argouml.uml.ui.behavior.use_cases.TestUMLExtensionPointUseCaseListModel;
import org.argouml.uml.ui.behavior.use_cases.TestUMLUseCaseExtendListModel;
import org.argouml.uml.ui.behavior.use_cases.TestUMLUseCaseExtensionPointListModel;
import org.argouml.uml.ui.behavior.use_cases.TestUMLUseCaseIncludeListModel;
import org.argouml.uml.ui.foundation.core.TestActionAddAttribute;
import org.argouml.uml.ui.foundation.core.TestActionAddDependency;
import org.argouml.uml.ui.foundation.core.TestActionAddEnumerationLiteral;
import org.argouml.uml.ui.foundation.core.TestUMLCollaborationInteractionListModel;
import org.argouml.uml.ui.foundation.core.TestUMLFeatureOwnerScopeCheckBox;
import org.argouml.uml.ui.foundation.core.TestUMLGeneralizationPowertypeComboBoxModel;
import org.argouml.uml.ui.foundation.core.TestUMLModelElementClientDependencyListModel;
import org.argouml.uml.ui.foundation.core.TestUMLModelElementConstraintListModel;
import org.argouml.uml.ui.foundation.core.TestUMLModelElementElementResidenceListModel;
import org.argouml.uml.ui.foundation.core.TestUMLModelElementNameDocument;
import org.argouml.uml.ui.foundation.core.TestUMLModelElementNamespaceListModel;
import org.argouml.uml.ui.foundation.core.TestUMLModelElementSourceFlowListModel;
import org.argouml.uml.ui.foundation.core.TestUMLModelElementSupplierDependencyListModel;
import org.argouml.uml.ui.foundation.core.TestUMLModelElementTargetFlowListModel;
import org.argouml.uml.ui.foundation.core.TestUMLStructuralFeatureTypeComboBoxModel;
import org.argouml.uml.util.namespace.TestStringNamespace;
import org.argouml.util.CheckMain;
import org.argouml.util.CheckResourceBundle;
import org.argouml.util.DoAllTests;
import org.argouml.util.TestMyTokenizer;
import org.argouml.util.ThreadHelper;
import org.argouml.util.osdep.TestOsUtil;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

import org.argouml.model.TestCommonBehaviorHelper;
import org.argouml.model.TestDataTypesFactory;
import org.argouml.model.TestExtensionMechanismsFactory;
import org.argouml.model.TestModel2;
import org.argouml.model.TestModelManagementFactory;
import org.argouml.model.TestUmlGeneralization;
import org.argouml.specifications.Configuration;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import Combinations.Combination;
import Combinations.FeatureCombination;
import Main.ProductGeneration;
import Main.TargetSystem;
import report.Record;
import report.RunListernerReport;

public class Challenge {

	public void executeJunitTestCase(ProductGeneration tools) {
		int count = 0;
		Record record = new Record();
		for (Combination combination : tools.getCombsForTest()) {
			for (FeatureCombination f : combination.getListFeatures()) {
				if (f.getName().equals("LOGGING")) {
					Configuration.LOGGING = (f.getState().equals("0") ? false : true);
				}
				else if (f.getName().equals("COGNITIVE")) {
					Configuration.COGNITIVE = (f.getState().equals("0") ? false : true);
				}
				else if (f.getName().equals("ACTIVITYDIAGRAM")) {
					Configuration.ACTIVITYDIAGRAM = (f.getState().equals("0") ? false : true);
				}
				else if (f.getName().equals("COLLABORATIONDIAGRAM")) {
					Configuration.COLLABORATIONDIAGRAM = (f.getState().equals("0") ? false : true);
				}
				else if (f.getName().equals("DEPLOYMENTDIAGRAM")) {
					Configuration.DEPLOYMENTDIAGRAM = (f.getState().equals("0") ? false : true);
				}
				else if (f.getName().equals("SEQUENCEDIAGRAM")) {
					Configuration.SEQUENCEDIAGRAM = (f.getState().equals("0") ? false : true);
				}
				else if (f.getName().equals("STATEDIAGRAM")) {
					Configuration.STATEDIAGRAM = (f.getState().equals("0") ? false : true);
				}
				else if (f.getName().equals("USECASEDIAGRAM")) {
					Configuration.USECASEDIAGRAM = (f.getState().equals("0") ? false : true);
				}

			}
			//

			System.err.println("Configuration:");
			Configuration.productPrint();
			System.out.println("");
			if (Configuration.validProduct()) {
				count++;
				/* JUnit core*/
				JUnitCore junit = new JUnitCore();
				junit.addListener(new RunListernerReport(Configuration.returnProduct(), record));
				org.junit.runner.Result result = junit.run(

						TestModel2.class, TestModelManagementFactory.class, TestUmlModelElement.class,
						TestModelFacade2.class, TestUmlFactoryBuildNode.class, TestUmlFactoryBuildNode.class,
						TestCommonBehaviorFactory.class, TestUmlUseCase.class, TestUmlModel.class, TestUmlActor.class,
						TestUmlFactory.class, TestUseCasesFactory.class, GUITestCommandLineInterface.class,
						TestTabChecklist.class, TestCheckItem.class, TestChecklist.class, TestChecklistStatus.class,
						TestSnoozeOrder.class, TestDecision.class, TestGoal.class, TestGoalModel.class,
						TestPersistenceManager.class, TestProfileMother.class, TestReaderModelLoader.class,
						TestUMLCollaborationConstraintListModel.class, TestUMLInteractionCollaborationListModel.class,
						TestUMLInteractionMessagesListModel.class, TestUMLMessageActionListModel.class,
						TestUMLMessagePredecessorListModel.class, TestUMLMessageReceiverListModel.class,
						TestUMLMessageSenderListModel.class, TestActionNewReception.class,
						TestUMLCollaborationConstraintListModel.class, TestUMLInteractionCollaborationListModel.class,
						TestUMLInteractionMessagesListModel.class, TestUMLMessagePredecessorListModel.class,
						TestUMLMessageReceiverListModel.class, TestUMLExtensionPointLocationDocument.class,
						TestUMLExtensionPointUseCaseListModel.class, TestUMLUseCaseExtensionPointListModel.class,
						TestUMLUseCaseIncludeListModel.class, TestUMLModelElementClientDependencyListModel.class,
						TestUMLModelElementConstraintListModel.class, TestUMLModelElementElementResidenceListModel.class, TestUMLModelElementNameDocument.class,
						TestUMLModelElementNamespaceListModel.class, TestUMLModelElementSourceFlowListModel.class,
						TestUUIDManager.class, TestUml.class);
				/* end JUnit core*/
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
			String path = "C:/Users/Fischer_PC/Documents/2019_CSS_Journal/Challenge/workspace_IncLing/Tools/All_valid_conf/ArgoUML/products";

			challenge.run(TargetSystem.ARGO, path);
			finishTime = System.currentTimeMillis() - startTime;
			index++;
		}

		float average = finishTime / index;

		System.out.println("Total time (ms): " + finishTime + " time average (s): " + (average / 1000) + average
				+ " number of times performed:" + index);

	}
}