package Exam;

import javax.swing.*;
import java.awt.event.*;

public class SportsRegistrationApp extends JFrame implements ActionListener {
    private JTextField nameField;
    private JComboBox<String> yearComboBox;
    private JComboBox<String> courseComboBox;
    private JComboBox<String> branchComboBox;
    private JTextField dobField;
    private JTextField name2Field;
    private JTextField dob2Field;
    private JComboBox<String> sportComboBox;
    private JComboBox<String> participationComboBox;
    private JButton registerButton;

    private String[] years = {"1", "2", "3", "4"};
    private String[] courses = {"B Tech", "Degree"};
    private String[] bTechBranches = {"ECE", "CSE", "AI&DS"};
    private String[] degreeBranches = {"BBA", "BCA"};

    private String[] sports = {"Basketball", "Volleyball", "Badminton", "Tennis", "Table Tennis"};
    private String[] participationTypes = {"Single", "Double"};

    public SportsRegistrationApp() {
        setTitle("Sports Registration App");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 20, 80, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(120, 20, 200, 25);
        add(nameField);

        JLabel yearLabel = new JLabel("Year:");
        yearLabel.setBounds(20, 50, 80, 25);
        add(yearLabel);

        yearComboBox = new JComboBox<>(years);
        yearComboBox.setBounds(120, 50, 100, 25);
        add(yearComboBox);

        JLabel courseLabel = new JLabel("Course:");
        courseLabel.setBounds(20, 80, 80, 25);
        add(courseLabel);

        courseComboBox = new JComboBox<>(courses);
        courseComboBox.setBounds(120, 80, 100, 25);
        add(courseComboBox);
        courseComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedCourse = (String) courseComboBox.getSelectedItem();
                if (selectedCourse.equals("B Tech")) {
                    branchComboBox.setModel(new DefaultComboBoxModel<>(bTechBranches));
                } else if (selectedCourse.equals("Degree")) {
                    branchComboBox.setModel(new DefaultComboBoxModel<>(degreeBranches));
                }
            }
        });

        JLabel branchLabel = new JLabel("Branch:");
        branchLabel.setBounds(20, 110, 80, 25);
        add(branchLabel);

        branchComboBox = new JComboBox<>(bTechBranches);
        branchComboBox.setBounds(120, 110, 100, 25);
        add(branchComboBox);

        JLabel dobLabel = new JLabel("Date of Birth (dd/mm/yyyy):");
        dobLabel.setBounds(20, 140, 180, 25);
        add(dobLabel);

        dobField = new JTextField();
        dobField.setBounds(200, 140, 120, 25);
        add(dobField);

        JLabel nameLabel2 = new JLabel("Name (Second Person):");
        nameLabel2.setBounds(20, 170, 150, 25);
        add(nameLabel2);
        nameLabel2.setVisible(false);

        name2Field = new JTextField();
        name2Field.setBounds(200, 170, 120, 25);
        add(name2Field);
        name2Field.setVisible(false);

        JLabel dobLabel2 = new JLabel("Date of Birth (Second Person) (dd/mm/yyyy):");
        dobLabel2.setBounds(20, 200, 250, 25);
        add(dobLabel2);
        dobLabel2.setVisible(false);

        dob2Field = new JTextField();
        dob2Field.setBounds(270, 200, 120, 25);
        add(dob2Field);
        dob2Field.setVisible(false);

        JLabel sportLabel = new JLabel("Select Sport:");
        sportLabel.setBounds(20, 230, 100, 25);
        add(sportLabel);

        sportComboBox = new JComboBox<>(sports);
        sportComboBox.setBounds(120, 230, 150, 25);
        add(sportComboBox);

        JLabel participationLabel = new JLabel("Select Participation Type:");
        participationLabel.setBounds(20, 260, 180, 25);
        add(participationLabel);

        participationComboBox = new JComboBox<>(participationTypes);
        participationComboBox.setBounds(200, 260, 100, 25);
        add(participationComboBox);
        participationComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selected = (String) participationComboBox.getSelectedItem();
                if (selected.equals("Double")) {
                    nameLabel2.setVisible(true);
                    name2Field.setVisible(true);
                    dobLabel2.setVisible(true);
                    dob2Field.setVisible(true);
                } else {
                    nameLabel2.setVisible(false);
                    name2Field.setVisible(false);
                    dobLabel2.setVisible(false);
                    dob2Field.setVisible(false);
                }
            }
        });

        registerButton = new JButton("Register");
        registerButton.setBounds(150, 300, 100, 30);
        registerButton.addActionListener(this);
        add(registerButton);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            if (!validateInputs()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields correctly.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String name = nameField.getText();
            String year = (String) yearComboBox.getSelectedItem();
            String course = (String) courseComboBox.getSelectedItem();
            String branch = (String) branchComboBox.getSelectedItem();
            String dob = dobField.getText();
            String sport = (String) sportComboBox.getSelectedItem();
            String participationType = (String) participationComboBox.getSelectedItem();

            String message = "Name: " + name + "\nYear: " + year + "\nCourse: " + course + "\nBranch: " + branch +
                    "\nDate of Birth: " + dob + "\nRegistered Sport: " + sport + " (" + participationType + ")\n\nRegistration Successful!";
            if (participationType.equals("Double")) {
                String name2 = name2Field.getText();
                String dob2 = dob2Field.getText();
                message += "\nSecond Person Name: " + name2 + "\nSecond Person Date of Birth: " + dob2;
            }

            JOptionPane.showMessageDialog(this, message);
        }
    }

    private boolean validateInputs() {
        String name = nameField.getText();
        String dob = dobField.getText();

        // Check if name contains any numbers
        if (name.matches(".*\\d.*")) {
            return false;
        }
        if (!dob.matches("\\d{2}/\\d{2}/\\d{4}")) {
            return false;
        }

        // Additional validation for Double participation type
        String participationType = (String) participationComboBox.getSelectedItem();
        if (participationType.equals("Double")) {
            String name2 = name2Field.getText();
            String dob2 = dob2Field.getText();

            if (name2.isEmpty() || !name2.matches("^[a-zA-Z]*$") || dob2.isEmpty() || !dob2.matches("\\d{2}/\\d{2}/\\d{4}")) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SportsRegistrationApp().setVisible(true);
            }
        });
    }
}

