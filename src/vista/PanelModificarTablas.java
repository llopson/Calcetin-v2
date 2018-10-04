package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class PanelModificarTablas extends JFrame{

	public PanelModificarTablas(String tableName, Object [] fields, String [] dataRow) {
		initComponents(tableName,fields,dataRow);
		setUndecorated(false);
		dialogPane.setBackground(new Color(45,63,45));
		contentPanel.setBackground(new Color(45,63,45));
		buttonBar.setBackground(new Color(45,63,45));
		getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(45,63,45)));
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(PanelModificarTablas.DISPOSE_ON_CLOSE);
	}

	private void initComponents(String tableName, Object [] fields, String [] dataRow) {
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		titleLabel = new JLabel();
		for (int i = 0; i < fields.length; i++) {
			fieldLabelArray[i] = new JLabel((String)fields[i]);
			fieldArray[i] = new JTextField();
			fieldArray[i].setText(dataRow[i]);
		}

		buttonBar = new JPanel();
		okButton = new JButton();
		cancelButton = new JButton();

		//======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== dialogPane ========
		{
			dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
			dialogPane.setLayout(new BorderLayout());
			//======== contentPanel ========
			{
				contentPanel.setLayout(new GridBagLayout());
				((GridBagLayout)contentPanel.getLayout()).columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
				((GridBagLayout)contentPanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
				((GridBagLayout)contentPanel.getLayout()).columnWeights = new double[] {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0E-4};
				((GridBagLayout)contentPanel.getLayout()).rowWeights = new double[] {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0E-4};

				//---- titleLabel ----
				titleLabel.setText("Modificar Tabla "+tableName);
				titleLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
				titleLabel.setForeground(new Color(255,255,246));
				contentPanel.add(titleLabel, new GridBagConstraints(2, 1, 20, 3, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));

				//---- fieldLabelArray ----
				for (int i=0; i < fields.length; i++) {
					fieldLabelArray[i].setFont(new Font("Lucida Grande", Font.BOLD, 12));
					fieldLabelArray[i].setForeground(new Color(255,255,246));
					contentPanel.add(fieldLabelArray[i], new GridBagConstraints(2, 5+i, 4, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 5, 5), 0, 0));
					contentPanel.add(fieldArray[i], new GridBagConstraints(6, 5+i, 13, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
						new Insets(0, 0, 5, 5), 0, 0));
				}

			}
			dialogPane.add(contentPanel, BorderLayout.CENTER);

			//======== buttonBar ========
			{
				buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
				buttonBar.setLayout(new GridBagLayout());
				((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 0, 0, 0};
				((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 1.0, 1.0, 1.0};

				//---- okButton ----
				okButton.setText("Aceptar");
				buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 5), 0, 0));

				//---- cancelButton ----
				cancelButton.setText("Cancelar");
				buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 0), 0, 0));
			}
			dialogPane.add(buttonBar, BorderLayout.SOUTH);
		}
		contentPane.add(dialogPane, BorderLayout.CENTER);
		setSize(350, 350);
		setLocationRelativeTo(getOwner());
	}

	private JPanel dialogPane;
	private JPanel contentPanel;
	private JLabel titleLabel;
	private JLabel [] fieldLabelArray = new JLabel[10];
	private JTextField [] fieldArray = new JTextField[10];
	private JPanel buttonBar;
	private JButton okButton;
	private JButton cancelButton;
}