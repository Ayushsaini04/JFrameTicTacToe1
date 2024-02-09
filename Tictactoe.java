import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Tictactoe {

    public static String current = "X";
    public static JButton[] buttons = new JButton[9]; // Array to hold buttons

    static public void main(String... rk) {
        JFrame f = new JFrame("Tic Tac Toe");
        f.setSize(400, 400);
        f.getContentPane().setBackground(new Color(240, 240, 240));
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating a JPanel for holding the Buttons
        JPanel p = new JPanel(new GridLayout(3, 3, 5, 5));
        p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int i = 0; i < 9; i++) {
            JButton button = new JButton();
            button.setFont(new Font("Arial", Font.BOLD, 40));
            button.addActionListener(new MyListener());
            buttons[i] = button;
            p.add(button);
        }

        f.add(p, BorderLayout.CENTER);
        f.setVisible(true);

    }

    static class MyListener implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            JButton button = (JButton) ae.getSource();
            if (button.getText().equals("")) {
                if (current.equals("X")) {
                    current = "O";
                    button.setText(current);
                    button.setForeground(Color.BLUE);
                } else {
                    current = "X";
                    button.setText(current);
                    button.setForeground(Color.RED);
                }
                button.setEnabled(false);
                if (checkWin()) {
                    JOptionPane.showMessageDialog(null, "Player " + current + " wins!");
                    resetGame();
                } else if (checkDraw()) {
                    JOptionPane.showMessageDialog(null, "It's a draw!");
                    resetGame();
                }
            }
        }

        public boolean checkWin() {
            String[] board = new String[9];
            for (int i = 0; i < 9; i++) {
                board[i] = buttons[i].getText();
            }

            int[][] winningCombinations = {
                    {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // rows
                    {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // columns
                    {0, 4, 8}, {2, 4, 6}              // diagonals
            };
            for (int[] combination : winningCombinations) {
                if (board[combination[0]].equals(current)
                        && board[combination[1]].equals(current)
                        && board[combination[2]].equals(current)) {
                    return true;
                }
            }
            return false;
        }

        public boolean checkDraw() {
            for (int i = 0; i < 9; i++) {
                if (buttons[i].getText().equals("")) {
                    return false;
                }
            }
            return true;
        }

        public void resetGame() {
            for (int i = 0; i < 9; i++) {
                buttons[i].setText("");
                buttons[i].setEnabled(true);
            }
            current = "X";
        }
    }
}
