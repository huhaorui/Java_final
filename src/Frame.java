import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame implements ActionListener {
    private JButton Button_ReadFile, Button_Lottery;

    Frame() {
        JFrame frame = new JFrame("幸运观众手机号码抽取器");
        Container c = frame.getContentPane();
        frame.setBounds(50, 50, 800, 400);
        JPanel left = new JPanel();
        JPanel right = new JPanel();
        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right);
        Button_ReadFile = new JButton("读取文件");
        Button_Lottery = new JButton("抽奖!");
        Button_ReadFile.addActionListener(this);
        Button_Lottery.addActionListener(this);
        left.add(Button_ReadFile);
        left.add(Button_Lottery);
        c.add(sp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        sp.setDividerLocation(0.3);
        Button_ReadFile.setSize(200, 50);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == Button_ReadFile)
            JOptionPane.showMessageDialog(null, "读取文件成功", "提示", JOptionPane.INFORMATION_MESSAGE);
        if (actionEvent.getSource() == Button_Lottery) {
            JOptionPane.showMessageDialog(null, "抽奖成功", "提示", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
