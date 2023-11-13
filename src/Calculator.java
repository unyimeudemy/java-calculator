import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator  implements ActionListener{

    JFrame frame;
    JPanel panel;
    JTextField textField;
    JButton[] numberButtons = new JButton[10];
    JButton[] funcButtons = new JButton[9];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton, negButton;

    Font myFont = new Font("Ink Free",  Font.BOLD, 30);
    Number num1 = 0, num2 = 0, result = 0;
    char operator;




    Calculator(){

        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 550);
        frame.setLayout(null);
        

        textField = new JTextField();
        textField.setBounds(50, 25, 300, 50);
        textField.setFont(myFont);
        textField.setEditable(false);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Delete");
        addButton = new JButton("+");
        clrButton = new JButton("Clear");
        negButton = new JButton("-ve");

        funcButtons[0] = addButton;
        funcButtons[1] = subButton;
        funcButtons[2] = mulButton;
        funcButtons[3] = divButton;
        funcButtons[4] = decButton;
        funcButtons[5] = equButton;
        funcButtons[6] = delButton;
        funcButtons[7] = clrButton;
        funcButtons[8] = negButton;

        for(int i = 0; i < 9; i++){
            funcButtons[i].addActionListener(this);
            funcButtons[i].setFont(myFont);
            funcButtons[i].setFocusable(false);
        }
        for(int i = 0; i < 10; i++){
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }   

        delButton.setBounds(50, 430, 100, 50);
        negButton.setBounds(150, 430, 100, 50);
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
        frame.add(delButton);
        frame.add(negButton);
        frame.add(clrButton);
        frame.add(textField);
        frame.setVisible(true);

    }

    
 
    public static void main(String[] args) throws Exception {
        Calculator cal = new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e){
           displayInputedNumber(e);
           displayDecimalSign(e, decButton, ".");
           displaySign(e, addButton, '+');
           displaySign(e, subButton, '-');
           displaySign(e, mulButton, '*');
           displaySign(e, divButton, '/');
           deleteLastInput(e);
           clearInput(e);
           performCalculation(e);

           if(e.getSource() == negButton){
            double temp = Double.parseDouble(textField.getText());
            temp = temp * -1;
            textField.setText(String.valueOf(temp));
           }
    }

    public void clearInput(ActionEvent e){
        if(e.getSource() == clrButton){
            textField.setText("");
        }
    }

    public void performCalculation(ActionEvent e) {
            if(e.getSource() == equButton){
                num2 = Double.parseDouble(textField.getText());

                switch (operator) {
                    case '+':
                        result = num1.doubleValue() + num2.doubleValue();
                        break;
                    case '-':
                        result = num1.doubleValue() - num2.doubleValue();
                        break;
                    case '*':
                        result = num1.doubleValue() * num2.doubleValue();
                        break;
                    case '/':
                        result = num1.doubleValue() /  num2.doubleValue();
                        break;
                }

                textField.setText(String.valueOf(result));
                num1 = result;

            }
    }

    public void displayInputedNumber(ActionEvent e){
        for (int i = 0; i < 10; i++){
           if( e.getSource() == numberButtons[i] ){
            textField.setText(textField.getText().concat(String.valueOf(i)));
           }
        }
    }

    public void displaySign(ActionEvent e, JButton button, char sign){
        if( e.getSource() == button ){
            num1 = Double.parseDouble(textField.getText());
            operator = sign;
            textField.setText("");

        }
    }

    public void displayDecimalSign(ActionEvent e, JButton button, String sign){
           if( e.getSource() == button ){
            textField.setText(textField.getText().concat(sign));
           }
    }

    public void deleteLastInput(ActionEvent e){
        if(e.getSource() == delButton){
            String input = textField.getText();
            textField.setText("");
            for(int i = 0; i<input.length() - 1; i++){
                textField.setText(textField.getText() + input.charAt(i));
            }   
        }
    }

}
