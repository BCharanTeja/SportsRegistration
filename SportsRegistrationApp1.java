package Exam;

import javax.swing.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;

public class SportsRegistrationApp1 extends JFrame implements ActionListener {

    private JTextField nameField;
    private JComboBox<String> yearComboBox;
    private JComboBox<String> courseComboBox;
    private JComboBox<String> branchComboBox;
    private JComboBox<String> branchComboBox1;
    private JTextField dobField;
    private JTextField name2Field;
    private JTextField dob2Field;
    private JTextField branch2;
    private JComboBox<String> sportComboBox;
    private JComboBox<String> participationComboBox;
    private JButton registerButton;

    private String[] years = {"1", "2", "3", "4"};
    private String[] courses = {"B Tech", "Degree"};
    private String[] bTechBranches = {"ECE", "CSE", "AI&DS"};
    private String[] degreeBranches = {"BBA", "BCA"};
    private String[] sports = {"Basketball", "Volleyball", "Badminton", "Tennis", "Table Tennis"};
    private String[] participationTypes = {"Single", "Double"};

    public SportsRegistrationApp1() {
        setTitle("Sports Registration App");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        nameField = new JTextField();
        nameField.setBounds(120, 20, 200, 25);
        add(nameField);

        yearComboBox = new JComboBox<>(years);
        yearComboBox.setBounds(120, 50, 100, 25);
        add(yearComboBox);

        courseComboBox = new JComboBox<>(courses);
        courseComboBox.setBounds(120, 80, 100, 25);
        add(courseComboBox);

        courseComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedCourse = (String) courseComboBox.getSelectedItem();
                if (selectedCourse.equals("B Tech")) {
                    branchComboBox.setModel(new DefaultComboBoxModel<>(bTechBranches));
                    branchComboBox1.setModel(new DefaultComboBoxModel<>(bTechBranches));
                } else if (selectedCourse.equals("Degree")) {
                    branchComboBox.setModel(new DefaultComboBoxModel<>(degreeBranches));
                    branchComboBox1.setModel(new DefaultComboBoxModel<>(degreeBranches));
                }
            }
        });

        branchComboBox = new JComboBox<>(bTechBranches);
        branchComboBox.setBounds(120, 110, 100, 25);
        add(branchComboBox);

        dobField = new JTextField();
        dobField.setBounds(200, 140, 120, 25);
        add(dobField);

        name2Field = new JTextField();
        name2Field.setBounds(200, 170, 120, 25);
        add(name2Field);
        name2Field.setVisible(false);

        dob2Field = new JTextField();
        dob2Field.setBounds(200, 200, 120, 25);
        add(dob2Field);
        dob2Field.setVisible(false);
        
        branchComboBox1 = new JComboBox<>(bTechBranches);
        branchComboBox1.setBounds(120, 110, 100, 25);
        add(branchComboBox1);
        branchComboBox1.setVisible(false);
          
        sportComboBox = new JComboBox<>(sports);
        sportComboBox.setBounds(120, 230, 150, 25);
        add(sportComboBox);

        participationComboBox = new JComboBox<>(participationTypes);
        participationComboBox.setBounds(200, 260, 100, 25);
        add(participationComboBox);

        participationComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selected = (String) participationComboBox.getSelectedItem();
                if (selected.equals("Double")) {
                    name2Field.setVisible(true);
                    dob2Field.setVisible(true);
                    branchComboBox1.setVisible(true);
                } else {
                    name2Field.setVisible(false);
                    dob2Field.setVisible(false);
                    branchComboBox1.setVisible(false);
                }
            }
        });

        registerButton = new JButton("Register");
        registerButton.setBounds(150, 300, 100, 30);
        registerButton.addActionListener(this);
        add(registerButton);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 20, 80, 25);
        add(nameLabel);

        JLabel yearLabel = new JLabel("Year:");
        yearLabel.setBounds(20, 50, 80, 25);
        add(yearLabel);

        JLabel courseLabel = new JLabel("Course:");
        courseLabel.setBounds(20, 80, 80, 25);
        add(courseLabel);

        JLabel branchLabel = new JLabel("Branch:");
        branchLabel.setBounds(20, 110, 80, 25);
        add(branchLabel);

        JLabel dobLabel = new JLabel("Date of Birth (dd/mm/yyyy):");
        dobLabel.setBounds(20, 140, 180, 25);
        add(dobLabel);

        JLabel nameLabel2 = new JLabel("Name (Second Person):");
        nameLabel2.setBounds(20, 170, 150, 25);
        add(nameLabel2);

        JLabel dobLabel2 = new JLabel("Date of Birth (Second Person) (dd/mm/yyyy):");
        dobLabel2.setBounds(20, 200, 250, 25);
        add(dobLabel2);

        JLabel sportLabel = new JLabel("Select Sport:");
        sportLabel.setBounds(20, 230, 100, 25);
        add(sportLabel);

        JLabel participationLabel = new JLabel("Select Participation Type:");
        participationLabel.setBounds(20, 260, 180, 25);
        add(participationLabel);
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

            saveToCSV(name, year, course, branch, dob, sport, participationType, participationType.equals("Double") ? name2Field.getText() : "", participationType.equals("Double") ? dob2Field.getText() : "");

            JOptionPane.showMessageDialog(this, message);
        }
    }

    private boolean validateInputs() {
        String name = nameField.getText();
        String dob = dobField.getText();
        String participationType = (String) participationComboBox.getSelectedItem();

        if (name.isEmpty() || dob.isEmpty() || !dob.matches("\\d{2}/\\d{2}/\\d{4}")) {
            return false;
        }

        if (participationType.equals("Double")) {
            String name2 = name2Field.getText();
            String dob2 = dob2Field.getText();
            if (name2.isEmpty() || dob2.isEmpty() || !dob2.matches("\\d{2}/\\d{2}/\\d{4}")) {
                return false;
            }
        }

        return true;
    }

    private void saveToCSV(String name, String year, String course, String branch, String dob, String sport, String participationType, String name2, String dob2) {
        try (FileWriter writer = new FileWriter("registration_data.csv", true)) {
            writer.append(name).append(',')
                  .append(year).append(',')
                  .append(course).append(',')
                  .append(branch).append(',')
                  .append(dob).append(',')
                  .append(sport).append(',')
                  .append(participationType).append(',');
            if (participationType.equals("Double")) {
                writer.append(name2).append(',').append(dob2);
            }
            writer.append('\n');
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SportsRegistrationApp1().setVisible(true);
            }
        });
    }
}
