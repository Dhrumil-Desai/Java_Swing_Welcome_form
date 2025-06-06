import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class sampleframe extends JFrame {
    final private Font mainFont = new Font("Tahoma",Font.PLAIN, 18);
    final private Font formPanel = new Font("Times New Roman", Font.BOLD, 18);
    final private Font buttonsPanel = new Font("Segoe Print", Font.BOLD,18);

    JTextField tffirstname, tfLastName, tfEmail, tfPhone;
    JLabel lbWelcome, lbDateTime;
    JPanel mainPanel;
    JRadioButton male, female;
    JComboBox<String> cbDay, cbMonth, cbYear;

    public void initialize() {
        // ----------- FORM PANEL -------------
        JLabel lbFirstName = new JLabel("First Name: ");
        lbFirstName.setFont(formPanel);

        tffirstname = new JTextField();
        tffirstname.setFont(formPanel);

        JLabel lbLastName = new JLabel("Last Name: ");
        lbLastName.setFont(formPanel);

        tfLastName = new JTextField();
        tfLastName.setFont(formPanel);

        JLabel lbemail = new JLabel("Email: ");
        lbemail.setFont(formPanel);

        tfEmail = new JTextField();
        tfEmail.setFont(formPanel);

        JLabel lbphone= new JLabel("Phone Number: ");
        lbphone.setFont(formPanel);

        tfPhone=new JTextField();
        tfPhone.setFont(formPanel);


        // Gender selection
        male = new JRadioButton("Male");
        female = new JRadioButton("Female");
        male.setFont(formPanel);
        female.setFont(formPanel);
        male.setOpaque(false);
        female.setOpaque(false);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);

        JLabel lbBirthdate = new JLabel("Birthdate: ");
        lbBirthdate.setFont(formPanel);

        // Day
        String[] days = new String[31];
        for (int i = 1; i <= 31; i++) days[i - 1] = String.valueOf(i);
        cbDay = new JComboBox<>(days);
        cbDay.setFont(formPanel);

        // Month
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                        "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        cbMonth = new JComboBox<>(months);
        cbMonth.setFont(formPanel);

        // Year
        String[] years = new String[100];
        int currentYear = java.time.Year.now().getValue();
        for (int i = 0; i < 100; i++) years[i] = String.valueOf(currentYear - i);
        cbYear = new JComboBox<>(years);
        cbYear.setFont(formPanel);

        // Add to panel
        JPanel birthdatePanel = new JPanel();
        birthdatePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        birthdatePanel.setOpaque(false);
        birthdatePanel.add(cbDay);
        birthdatePanel.add(cbMonth);
        birthdatePanel.add(cbYear);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 1, 5, 5));
        formPanel.setOpaque(false);
        formPanel.add(lbFirstName);
        formPanel.add(tffirstname);
        formPanel.add(lbLastName);
        formPanel.add(tfLastName);
        formPanel.add(lbemail);
        formPanel.add(tfEmail);
        formPanel.add(lbphone);
        formPanel.add(tfPhone);
        formPanel.add(lbBirthdate);
        formPanel.add(birthdatePanel);
        formPanel.add(male);
        formPanel.add(female);

        // ------------ Welcome Panel --------------
        lbWelcome = new JLabel();
        lbWelcome.setFont(mainFont);
        lbWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        lbWelcome.setForeground(Color.BLACK);

        // ------------ Buttons Panel ----------------
        JButton btnok=new JButton("OK");
        btnok.setFont(buttonsPanel);
        btnok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName=tffirstname.getText();
                String lastName=tfLastName.getText();
                String email = tfEmail.getText();
                String phone = tfPhone.getText();
                String day = (String) cbDay.getSelectedItem();
                String month = (String) cbMonth.getSelectedItem();
                String year = (String) cbYear.getSelectedItem();
                String birthdate = day + " " + month + ", " + year;
                String gender = male.isSelected() ? "Male" : female.isSelected() ? "Female" : "Unspecified";
                lbWelcome.setText("<html>Hello " + firstName + " " + lastName +
    "<br>Email: " + email +
    "<br>Phone: " + phone +
    "<br>Gender: " + gender +
    "<br>Birthdate: " + birthdate + "</html>");
            }
        
        });

        JButton btnClear = new JButton("Reset");
        btnClear.setFont(buttonsPanel);
        btnClear.addActionListener(e -> {
            tffirstname.setText("");
            tfLastName.setText("");
            lbWelcome.setText("");
            tfEmail.setText("");
            tfPhone.setText("");
            cbDay.setSelectedIndex(0);
            cbMonth.setSelectedIndex(0);
            cbYear.setSelectedIndex(0);
            genderGroup.clearSelection();
        });

        JButton btnExit = new JButton("Exit");
        btnExit.setFont(buttonsPanel);
        btnExit.addActionListener(e -> {
            int confirmed = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to exit?", "Exit Confirmation",
                    JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        JButton btnMode = new JButton("Mode");
        btnMode.setFont(buttonsPanel);
        btnMode.setBackground(UIManager.getColor("Button.background"));
        btnMode.setForeground(Color.BLACK);
        btnMode.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JPopupMenu modePopup = new JPopupMenu();
        JMenuItem darkModeItem = new JMenuItem("Dark Mode");
        JMenuItem lightModeItem = new JMenuItem("Light Mode");
        JMenuItem defaultModeItem = new JMenuItem("Default Mode");

        // Same fonts and actions
        Font menuFont = new Font("Segoe Print", Font.PLAIN, 16);
        darkModeItem.setFont(menuFont);
        lightModeItem.setFont(menuFont);
        defaultModeItem.setFont(menuFont);

        // Add actions as you already have
        darkModeItem.addActionListener(e -> {
            Color bgColor = Color.BLUE;
            Color fgColor = Color.YELLOW;
            Color textFieldBg = new Color(44, 44, 60);
            Color buttonBg = new Color(60, 60, 76);

            mainPanel.setBackground(bgColor);
            lbWelcome.setForeground(Color.ORANGE);
            lbDateTime.setForeground(fgColor);

            applyTheme(mainPanel, bgColor, fgColor, textFieldBg);
            updateButtonColors(mainPanel, buttonBg, fgColor);
        });

        lightModeItem.addActionListener(e -> {
            Color bgColor = Color.YELLOW;
            Color fgColor = Color.BLUE;
            Color textFieldBg = Color.WHITE;
            Color buttonBg = new Color(187, 222, 251);

            mainPanel.setBackground(bgColor);
            lbWelcome.setForeground(Color.BLACK);
            lbDateTime.setForeground(fgColor);

            applyTheme(mainPanel, bgColor, fgColor, textFieldBg);
            updateButtonColors(mainPanel, buttonBg, fgColor);
        });

        defaultModeItem.addActionListener(e -> {
            Color bgColor = Color.WHITE;
            Color fgColor = Color.BLACK;
            Color textFieldBg = Color.WHITE;
            Color buttonBg = UIManager.getColor("Button.background");

            mainPanel.setBackground(bgColor);
            lbWelcome.setForeground(Color.BLACK);
            lbDateTime.setForeground(fgColor);

            applyTheme(mainPanel, bgColor, fgColor, textFieldBg);
            updateButtonColors(mainPanel, buttonBg, fgColor);
        });

        // Add menu items to popup
        modePopup.add(darkModeItem);
        modePopup.add(lightModeItem);
        modePopup.add(defaultModeItem);

        btnMode.addActionListener(e -> {
            modePopup.show(btnMode, 0, btnMode.getHeight());
        });

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 4, 10, 10));
        buttonsPanel.setOpaque(false);
        buttonsPanel.add(btnok);
        buttonsPanel.add(btnClear);
        buttonsPanel.add(btnMode); // ✅ add this
        buttonsPanel.add(btnExit);

        // ------------ Main Panel -------------------
        // --- Set Small Application Icon (Top-Left) ---
        ImageIcon appIcon = new ImageIcon("C:/Users/Dhrumil Desai/OneDrive/Desktop/JAVA/Practice_Programs/Bright Logo with Handshake Icon.png");
        Image image = appIcon.getImage();
        Image smallImage = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        setIconImage(smallImage);
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(lbWelcome, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        add(mainPanel);

        setTitle("Welcome Form");
        setSize(700, 600);
        setMinimumSize(new Dimension(400, 300));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center the frame
        setVisible(true);
    }


    private void updateButtonColors(JPanel panel, Color buttonBg, Color fgColor) {
    for (Component comp : panel.getComponents()) {
        if (comp instanceof JPanel) {
            updateButtonColors((JPanel) comp, buttonBg, fgColor);
        } else if (comp instanceof JButton || comp instanceof JToggleButton) {
            comp.setBackground(buttonBg);
            comp.setForeground(fgColor);
            ((AbstractButton) comp).setBorder(BorderFactory.createLineBorder(fgColor));
        }
    }
}

    private void applyTheme(JPanel panel, Color bg, Color fg, Color textFieldBg) {
    for (Component comp : panel.getComponents()) {
        if (comp instanceof JPanel) {
            applyTheme((JPanel) comp, bg, fg, textFieldBg);
        } else {
            comp.setForeground(fg);
            if (comp instanceof JTextField) {
                comp.setBackground(textFieldBg);
                ((JTextField) comp).setCaretColor(fg);
            } else {
                comp.setBackground(bg);
            }
        }
    }
}

    public static void main(String[] args) {
        sampleframe myFrame = new sampleframe();
        myFrame.initialize();
    }
}