package desktopsearcher;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import specifications.Configuration;


public class History_Indexer implements ActionListener {

	Index_History_Selector frame;

	public History_Indexer(Index_History_Selector frame,
			JComboBox indexerpath_combobox) {
		if (Configuration.GUI) {
		if (Configuration.INDEX_HISTORY) {
			this.frame = frame;
		}
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (Configuration.GUI) {
			if (Configuration.INDEX_HISTORY) {
				if (e.getSource().equals(frame.abortButton)) {
					frame.setVisible(false);
				} else if (e.getSource().equals(frame.okButton)) {

					if (frame.browseRadioButton.isSelected()) {
						if (!frame.newRootTextField.getText().equals("")) {
							frame.selectedPath = frame.newRootTextField
									.getText();
						}
					}
					if (frame.historyRadioButton.isSelected()) {
						frame.selectedPath = (String) frame.historyList
								.getSelectedValue();
					}
					frame.setVisible(false);

				} else if (e.getSource().equals(frame.browseRadioButton)) {
					Component[] ar = frame.historyGroupBox.getComponents();
					frame.historyGroupBox.setEnabled(false);
					setEnable(ar, false);

					ar = frame.browseGroupBox.getComponents();
					frame.browseGroupBox.setEnabled(true);
					setEnable(ar, true);

				} else if (e.getSource().equals(frame.historyRadioButton)) {
					Component[] ar = frame.historyGroupBox.getComponents();
					frame.historyGroupBox.setEnabled(true);
					setEnable(ar, true);

					ar = frame.browseGroupBox.getComponents();
					frame.browseGroupBox.setEnabled(false);
					setEnable(ar, false);

				} else if (e.getSource().equals(frame.startBrowseButton)) {
					JFileChooser fileChooser = new JFileChooser();
					fileChooser
							.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					if (JFileChooser.APPROVE_OPTION == fileChooser
							.showOpenDialog(frame)) {
						String path = fileChooser.getSelectedFile().getPath();
						frame.newRootTextField.setText(path);

					}
				}
			}
		}
	}

	public void setEnable(Component[] ar, boolean enable) {
		if (Configuration.GUI) {
			if (Configuration.INDEX_HISTORY) {
				for (int i = 0; i < ar.length; i++) {
					ar[i].setEnabled(enable);
				}
			}
		}
	}

}
