package com.daniel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Calculator extends JFrame {
    JPanel mainPanel;
    JPanel bottomPanel;
    JTextField display;
    List<String> arrayOfDigits = new ArrayList<>(List.of("del", "AC", "%", "+", "7", "8", "9", "-", "4", "5", "6", "x", "1", "2", "3", "/"));
    double number1 = 0;
    double number2 = 0;
    String operator = "";

    private JButton createButton(String name) {
        JButton button = new JButton(name);
        button.setBackground(Color.darkGray);
        button.setFont(new Font("Arial", Font.PLAIN, 40));
        button.setForeground(Color.lightGray);
        return button;
    }

    private void buttonClick(String userDigit) {
        switch (userDigit) {
            case "AC":
                display.setText("");
                number1 = 0;
                number2 = 0;
                operator = "";
                break;

            case "del":
                display.setText(display.getText().substring(0, display.getText().length() - 1));
                break;

            case "+", "-", "x", "/":
                if (!display.getText().isEmpty() && operator.isEmpty()) {
                    number1 = Double.parseDouble(display.getText());
                    operator = userDigit;
                    display.setText(display.getText() + userDigit);
                }
                break;

            case "=":
                if (!operator.isEmpty()) {
                    try {
                        int operatorIndex = display.getText().indexOf(operator);
                        String textAfterOperator = display.getText().substring(operatorIndex + 1);
                        if (textAfterOperator.isEmpty()) {
                            return;
                        }
                        number2 = Double.parseDouble(textAfterOperator);

                        double result = switch (operator) {
                            case "+" -> number1 + number2;
                            case "-" -> number1 - number2;
                            case "x" -> number1 * number2;
                            case "/" -> {
                                if (number2 == 0) {
                                    throw new ArithmeticException("Can't divide by zero");
                                }
                                yield number1 / number2;
                            }
                            default -> throw new IllegalArgumentException("Invalid operator");
                        };

                        if (result == (long) result) {
                            display.setText(String.format("%d", (long) result));
                        } else {
                            display.setText(String.valueOf(result));
                        }

                        number1 = result;
                        operator = "";
                        number2 = 0;

                    } catch (ArithmeticException | IllegalArgumentException e) {
                        display.setText("Error: " + e.getMessage());
                        number1 = 0;
                        number2 = 0;
                        operator = "";
                    }
                }
                break;

            default:
                if (userDigit.length() == 1 && Character.isDigit(userDigit.charAt(0)) ||
                        userDigit.equals(".")) {
                    if (userDigit.equals(".") && display.getText().contains(".")) {
                        break;
                    }
                    display.setText(display.getText() + userDigit);
                }
        }
    }

    Calculator() {
        display = new JTextField();
        display.setEditable(false); // prevents the user from typing with keyboard
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Arial", Font.PLAIN, 100));
        display.setVisible(true);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 4, 5, 5));

        bottomPanel = new JPanel(new BorderLayout(5, 5));
        bottomPanel.setBorder(BorderFactory.createLineBorder(Color.white, 1));

        JPanel leftBottonPanel = new JPanel(new GridLayout(1, 2, 4, 5));
        leftBottonPanel.setPreferredSize(new Dimension(400, 130));
        leftBottonPanel.setBorder(BorderFactory.createLineBorder(Color.white, 2));

        JButton zeroBtn = createButton("0");
        zeroBtn.addActionListener(e -> buttonClick("0"));

        JButton dotBtn = createButton(".");
        dotBtn.addActionListener(e -> buttonClick("."));

        JButton equalsBtn = createButton("=");
        equalsBtn.setBackground(Color.BLACK);
        equalsBtn.setForeground(Color.WHITE);
        equalsBtn.addActionListener(e -> buttonClick("="));

        leftBottonPanel.add(zeroBtn);
        leftBottonPanel.add(dotBtn);

        bottomPanel.add(leftBottonPanel, BorderLayout.WEST);
        bottomPanel.add(equalsBtn, BorderLayout.CENTER);

        for (String digit : arrayOfDigits) {
            JButton button = createButton(digit);
            button.addActionListener(e -> buttonClick(button.getText()));
            mainPanel.add(button);
        }

        setTitle("Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 900);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        add(mainPanel, BorderLayout.CENTER);
        add(display, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
