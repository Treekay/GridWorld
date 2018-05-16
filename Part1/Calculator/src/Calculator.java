import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator extends JFrame {

    public static void setUIFont() {
        Font f = new Font("宋体",Font.PLAIN,16);
        String names[]={ "JButton","JTextField"};
        for (String item : names) {
            UIManager.put(item+ ".font",f);
        }
    }

    private JTextField num1, num2, operation, equation, result;
    private JButton addButton, subButton, mulButton, divButton, okButton;

    public static void main(String[] args) {
        setUIFont();
        Calculator calculator = new Calculator();
        calculator.setVisible(true);
    }

    public Calculator() {
        this.setLayout(new GridLayout(2, 5));
        this.setTitle("Calculator");
        this.setSize(300, 150);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        num1 = new JTextField();
        num1.setHorizontalAlignment(JTextField.CENTER);
        this.add(num1);

        operation = new JTextField();
        operation.setEnabled(false);
        operation.setHorizontalAlignment(JTextField.CENTER);
        this.add(operation);

        num2 = new JTextField();
        num2.setHorizontalAlignment(JTextField.CENTER);
        this.add(num2);

        equation = new JTextField("=");
        equation.setEnabled(false);
        equation.setHorizontalAlignment(JTextField.CENTER);
        this.add(equation);

        result = new JTextField();
        result.setEnabled(false);
        result.setHorizontalAlignment(JTextField.CENTER);
        this.add(result);

        OperatorListener opl = new OperatorListener();
        ResultListener rsl = new ResultListener();

        addButton = new JButton("+");
        addButton.addActionListener(opl);
        this.add(addButton);

        subButton = new JButton("-");
        subButton.addActionListener(opl);
        this.add(subButton);

        mulButton = new JButton("*");
        mulButton.addActionListener(opl);
        this.add(mulButton);

        divButton = new JButton("/");
        divButton.addActionListener(opl);
        this.add(divButton);

        okButton = new JButton("OK");
        okButton.addActionListener(rsl);
        this.add(okButton);
    }

    private class OperatorListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String operator = e.getActionCommand();
            operation.setText(operator);
        }
    }

    private class ResultListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String operator = operation.getText();
            if (!judgeNum()){
                result.setText("NaN");
                return;
            }
            int numa = Integer.parseInt(num1.getText());
            int numb = Integer.parseInt(num2.getText());
            int res = 0;

            switch(operator) {
                case "+":
                    res = numa + numb;
                    break;
                case "-":
                    res = numa - numb;
                    break;
                case "*":
                    res = numa * numb;
                    break;
                case "/":
                    if (numb != 0) {
                        res = numa / numb;
                    }
                    else {
                        result.setText("NaN");
                        return;
                    }
                    break;
                default: break;
            }

            result.setText(String.valueOf(res));
        }
    }

    private boolean judgeNum() {
        for (int i = num1.getText().length(); --i >= 0; ) {
            if (!Character.isDigit(num1.getText().charAt(i))) {
                return false;
            }
        }
        for (int i = num2.getText().length(); --i >= 0; ) {
            if (!Character.isDigit(num2.getText().charAt(i))) {
                return false;
            }
        }
        return true;
    }
}