package com.project.derby.action;

import java.awt.Color;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import com.project.derby.bean.Entries;
import com.project.derby.bean.Player;
import com.project.derby.components.Components;
import com.project.derby.dao.PlayerDao;

@SuppressWarnings("serial")

public class RegistrationForm extends JFrame implements ActionListener {
	JLabel title, idLabel, nameLabel, genderLabel, contactLabel, emailIdLabel, numberOfEntriesLabel;
	JTextField idField, nameField, genderField, contactField, emailField, numberOfEntriesField;
	JButton registerButton, exitButton, playerButton;
	JRadioButton male, female;
	ButtonGroup bg;
	JPanel panel;
	JTable table;
	String gender = "";
	private JPanel jpanel;
	private List<JTextField> weightTextsFields = new ArrayList<JTextField>();;
	// Returns a column class of Object
	DefaultTableModel model;
	Components components = new Components();
	private static RegistrationForm rf;

	RegistrationForm() {

		setSize(1000, 700);
		setTitle("::::-Registration Form-::::");
		setLayout(null);

		// Defining Labels
		title = components.getLabel("Register new person", 60 + 40, 7, 200, 30);

		playerButton = components.getButton("view Registered Players", 340 + 85,  40, 200, 30);
		playerButton.addActionListener(this);

		nameLabel = components.getLabel("Name", 30 + 40, 85, 60, 30);
		nameField = components.getTextField(30 + 165, 85, 150, 30, "name");

		genderLabel = components.getLabel("Gender", 30 + 40, 120, 60, 30);

		// Defining Gender Buttons
		male = new JRadioButton("Male");
		male.setBounds(165, 120, 60, 30);
		male.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gender = "Male";
			}

		});
		female = new JRadioButton("Female");
		female.setBounds(205, 120, 70, 30);
		female.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gender = "Female";
			}

		});
		bg = new ButtonGroup();
		bg.add(male);
		bg.add(female);

		contactLabel = components.getLabel("Contact", 30 + 40, 155, 60, 30);
		contactField = components.getTextField(30 + 165, 155, 150, 30, "7675");

		contactField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}

		});

		emailIdLabel = components.getLabel("EmailId", 30 + 40, 205, 60, 30);
		emailField = components.getTextField(30 + 165, 205, 150, 30, "myemail@gmail");

		numberOfEntriesLabel = components.getLabel("Number of Entries", 30 + 40, 250, 120, 50);
		numberOfEntriesField = components.getTextField(30 + 165, 255, 150, 30, "3");
		numberOfEntriesField.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET);

		numberOfEntriesField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		numberOfEntriesField.addActionListener(this);

		// Defining Exit Button
		exitButton = components.getButton("Exit", 85 + 85, 320 + 40, 80, 30);
		exitButton.addActionListener(this);

		// Defining Register Button
		registerButton = components.getButton("Register", 180 + 85, 320 + 40, 100, 30);
		registerButton.addActionListener(this);

		// fixing all Label,TextField,Button
		add(title);
		// add(idLabel);
		add(nameLabel);
		add(genderLabel);
		add(contactLabel);
		add(emailIdLabel);
		add(numberOfEntriesLabel);
		// add(idField);
		add(nameField);
		add(male);
		add(female);
		add(contactField);
		add(emailField);
		add(numberOfEntriesField);
		add(exitButton);
		add(registerButton);
		add(playerButton);

		model = new DefaultTableModel();
		table = new JTable(model);
		table.setEnabled(true);

		// Defining Column Names on model
		// model.addColumn("ID");
		model.addColumn("Name");
		model.addColumn("Gender");
		model.addColumn("Contact");
		model.addColumn("EmailId");
		model.addColumn("Number Of Entries");

		JFrame f = new JFrame();
		f.getContentPane().add(table).setBackground(Color.RED);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setVisible(true);

	}

	public void actionPerformed(ActionEvent ae) {
		Player player = new Player();
		Entries entries = new Entries();
		PlayerDao playerDao = new PlayerDao();
		List<JTextField> addedTextsFields;
		List<Entries> entriesList;

		if (ae.getSource() == numberOfEntriesField) {
			int numberOfTextFields = Integer.parseInt(ae.getActionCommand());

			rf.setSize(999, 700);
			jpanel = new JPanel();
			jpanel.setLayout(null);
			jpanel.setBorder(Components.addBorders());
			JLabel label = components.getLabel("Weights(lbs)", 10, 5, 60, 30);
			jpanel.add(label);

			addTexFieldsToJPanel(numberOfTextFields, jpanel);
			JScrollPane scrollPane = null;

			scrollPane = new JScrollPane(jpanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setBounds(530, 100, 35 * numberOfTextFields, 50 * numberOfTextFields);
			rf.add(scrollPane);
			revalidate();
			validate();

		}

		if (ae.getSource() == exitButton) {
			System.exit(0);
		}

		if (ae.getSource() == registerButton) {

			if (nameField.getText().equals("") || emailField.getText().equals("")
					|| contactField.getText().equals("")) {
				JOptionPane.showMessageDialog(idField, "Fields will not be blank");
			} else {
				addedTextsFields = getAddedTexFieldsToJPanel();
				player.setUname(nameField.getText());
				player.setGender(gender);
				player.setContact(contactField.getText());
				player.setEmailId(emailField.getText());
				Entries entries1;
				entriesList = new ArrayList<Entries>();
				for (int i = 0; i < addedTextsFields.size(); i++) {
					entries1 = new Entries();
					System.out.println(" TextFileds ************" + addedTextsFields.get(i).getText());
					entries1.setDerbyWeight(Double.parseDouble(addedTextsFields.get(i).getText()));
					entries1.setPlayer(player);

					entriesList.add(entries1);
				}
				player.setEntriesList(entriesList);

				playerDao.insertPlayer(player);

				JOptionPane.showMessageDialog(this, "Successfully Registered");
				idField.setText("");
				nameField.setText("");
				gender = "";
				bg.clearSelection();
				contactField.setText("");
				emailField.setText("");
			}
		}
	}

	public static void main(String[] args) {
		setDefaultLookAndFeelDecorated(true);
		rf = new RegistrationForm();
	}

	private void addTexFieldsToJPanel(int numberOfTextFields, JPanel panel) {

		for (int i = 0; i < numberOfTextFields; i++) {
			JTextField jf = new JTextField("0.0");
			panel.add(jf);
			jf.setBounds(10, (i + 1) * 30, 50, 28);
			jf.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)
							|| (c == KeyEvent.VK_PERIOD))) {
						e.consume();
					}
				}
			});
			this.weightTextsFields.add(jf);
		}
	}

	private List<JTextField> getAddedTexFieldsToJPanel() {

		return this.weightTextsFields;
	}

}