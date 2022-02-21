import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.function.Consumer;

public class vCalc implements ActionListener {

    JFrame frame;
    JComboBox<String> comboCalcType, comboTheme;
    JTextField textfield;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[9];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton, negButton;
    JPanel panel;
    String version = "1.0";
    Font myFont = new Font("Consolas", Font.BOLD, 25);


    double num1 = 0, num2 = 0, result = 0;

    char operator;


    vCalc() {
        frame = new JFrame("vCalc || v" + version);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 550);
        frame.setLayout(null);
        comboTheme = initCombo(new String[]{"Light", "Colored", "Dark"}, 360, 20, "Theme", themeSwitchEventConsumer);

        textfield = new JTextField();
        textfield.setBounds(50, 25, 300, 50);
        textfield.setFont(myFont);
        textfield.setEditable(false);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Del");
        clrButton = new JButton("Clr");
        negButton = new JButton("(-)");


        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;


        for (int i = 0; i < 9; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
        }


        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }


        negButton.setBounds(50, 430, 100, 50);
        delButton.setBounds(150, 430, 100, 50);
        clrButton.setBounds(250, 430, 100, 50);


        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));


        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        frame.add(panel);
        frame.add(negButton);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textfield);
        frame.setVisible(true);
    }

    private JComboBox<String> initCombo(String[] items, int x, int y, String toolTip, Consumer consumerEvent) {
        JComboBox<String> combo = new JComboBox<>(items);
        combo.setBounds(x, y, 140, 25);
        combo.setToolTipText(toolTip);
        combo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        combo.addItemListener(consumerEvent::accept);
        frame.add(combo);

        return combo;
    }

    public static void main(String[] args) {
        vCalc calc = new vCalc();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textfield.setText(textfield.getText().concat(String.valueOf(i)));
            }
        }

        if (e.getSource() == decButton) {
            textfield.setText(textfield.getText().concat("."));
        }

        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '+';
            textfield.setText("");

        }

        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '-';
            textfield.setText("");

        }

        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '*';
            textfield.setText("");

        }

        if (e.getSource() == divButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '/';
            textfield.setText("");

        }

        if (e.getSource() == equButton) {
            num2 = Double.parseDouble(textfield.getText());

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;

                case '-':
                    result = num1 - num2;
                    break;

                case '*':
                    result = num1 * num2;
                    break;

                case '/':
                    result = num1 / num2;
                    break;
            }
            textfield.setText(String.valueOf(result));

            num1 = result;

        }

        if (e.getSource() == clrButton) {
            textfield.setText("");
        }

        if (e.getSource() == delButton) {
            String string = textfield.getText();
            textfield.setText(" ");

            for(int i = 0; i < string.length() - 1; i++){
                String strnew = string.substring(0, string.length()-1);
                textfield.setText(strnew);
            }
        }

        if (e.getSource() == negButton) {
            double temp = Double.parseDouble(textfield.getText());
            temp *= -1;

            textfield.setText(String.valueOf(temp));
        }
    }

    private Consumer<ItemEvent> themeSwitchEventConsumer = event -> {
        if (event.getStateChange() != ItemEvent.SELECTED) return;

        String selectedTheme = (String) event.getItem();
        switch (selectedTheme) {
            case "Light" -> {
                frame.getContentPane().setBackground(null);
                clrButton.setBackground(null);
                mulButton.setBackground(null);
                divButton.setBackground(null);
                subButton.setBackground(null);
                addButton.setBackground(null);
                delButton.setBackground(null);
                negButton.setBackground(null);
                equButton.setBackground(null);
                numberButtons[0].setBackground(null);
                numberButtons[1].setBackground(null);
                numberButtons[2].setBackground(null);
                numberButtons[3].setBackground(null);
                numberButtons[4].setBackground(null);
                numberButtons[5].setBackground(null);
                numberButtons[6].setBackground(null);
                numberButtons[7].setBackground(null);
                numberButtons[8].setBackground(null);
                numberButtons[9].setBackground(null);
                decButton.setBackground(null);
                clrButton.setForeground(null);
                divButton.setForeground(null);
                mulButton.setForeground(null);
                subButton.setForeground(null);
                addButton.setForeground(null);
                equButton.setForeground(null);
                negButton.setForeground(null);
            }
            case "Colored" -> {
                frame.getContentPane().setBackground(null);
                clrButton.setBackground(Color.RED);
                delButton.setBackground(Color.GREEN);
                negButton.setBackground(Color.PINK);
                divButton.setBackground(Color.PINK);
                mulButton.setBackground(Color.PINK);
                subButton.setBackground(Color.PINK);
                addButton.setBackground(Color.PINK);
                equButton.setBackground(Color.BLUE);
                decButton.setBackground(Color.GREEN);
                numberButtons[0].setBackground(Color.WHITE);
                numberButtons[1].setBackground(Color.WHITE);
                numberButtons[2].setBackground(Color.WHITE);
                numberButtons[3].setBackground(Color.WHITE);
                numberButtons[4].setBackground(Color.WHITE);
                numberButtons[5].setBackground(Color.WHITE);
                numberButtons[6].setBackground(Color.WHITE);
                numberButtons[7].setBackground(Color.WHITE);
                numberButtons[8].setBackground(Color.WHITE);
                numberButtons[9].setBackground(Color.WHITE);
                clrButton.setForeground(Color.WHITE);
                divButton.setForeground(Color.WHITE);
                mulButton.setForeground(Color.WHITE);
                subButton.setForeground(Color.WHITE);
                addButton.setForeground(Color.WHITE);
                equButton.setForeground(Color.WHITE);
                negButton.setForeground(Color.WHITE);
                decButton.setForeground(Color.WHITE);
            }
            case "Dark" -> {
                final Color primaryDarkColor = new Color(75, 73, 73);
                final Color secondaryDarkColor = new Color(154, 154, 154);
                frame.getContentPane().setBackground(new Color(35, 35, 35));
                numberButtons[0].setBackground(secondaryDarkColor);
                numberButtons[1].setBackground(secondaryDarkColor);
                numberButtons[2].setBackground(secondaryDarkColor);
                numberButtons[3].setBackground(secondaryDarkColor);
                numberButtons[4].setBackground(secondaryDarkColor);
                numberButtons[5].setBackground(secondaryDarkColor);
                numberButtons[6].setBackground(secondaryDarkColor);
                numberButtons[7].setBackground(secondaryDarkColor);
                numberButtons[8].setBackground(secondaryDarkColor);
                numberButtons[9].setBackground(secondaryDarkColor);
                decButton.setBackground(secondaryDarkColor);
                clrButton.setForeground(secondaryDarkColor);
                divButton.setForeground(secondaryDarkColor);
                mulButton.setForeground(secondaryDarkColor);
                subButton.setForeground(secondaryDarkColor);
                addButton.setForeground(secondaryDarkColor);
                equButton.setForeground(secondaryDarkColor);
                clrButton.setBackground(primaryDarkColor);
                divButton.setBackground(primaryDarkColor);
                mulButton.setBackground(primaryDarkColor);
                subButton.setBackground(primaryDarkColor);
                addButton.setBackground(primaryDarkColor);
                equButton.setBackground(primaryDarkColor);
                delButton.setBackground(primaryDarkColor);
                negButton.setBackground(primaryDarkColor);
            }
        }
    };
}
