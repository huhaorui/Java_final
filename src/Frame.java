import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Frame implements ActionListener {
    private JButton Button_ReadFile, Button_Lottery;
    private JCheckBox[] Box_AwardNum = {new JCheckBox("一等奖"), new JCheckBox("二等奖"), new JCheckBox("三等奖")};
    private JLabel[] Label_awards = {new JLabel("一等奖个数："), new JLabel("二等奖个数："), new JLabel("三等奖个数：")};
    private JComboBox[] Combo_awards = {addNum(new JComboBox<>()), addNum(new JComboBox<>()), addNum(new JComboBox<>())};

    private JComboBox addNum(JComboBox<Integer> jComboBox) {
        for (int x = 0; x < 10; x++) {
            jComboBox.addItem(x);
        }
        return jComboBox;
    }

    Frame() {
        JFrame frame = new JFrame("幸运观众手机号码抽取器");
        Container c = frame.getContentPane();
        frame.setBounds(50, 50, 800, 400);
        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel right = new JPanel();
        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right);
        JLabel Label_award = new JLabel("请选择要产生的奖项");
        Button_ReadFile = new JButton("读取文件");
        Button_Lottery = new JButton("抽奖!");
        Button_ReadFile.addActionListener(this);
        Button_Lottery.addActionListener(this);
        left.add(Button_ReadFile);

        left.add(Label_award);
        for (JCheckBox tmp : Box_AwardNum) {
            tmp.addActionListener(this);
            left.add(tmp);
        }
        for (int i = 0; i < 3; i++) {
            left.add(Label_awards[i]);
            Label_awards[i].setVisible(false);
            left.add(Combo_awards[i]);
            Combo_awards[i].setVisible(false);
        }
        left.add(Button_Lottery);
        c.add(sp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        sp.setDividerLocation(0.3);
        Button_ReadFile.setSize(200, 50);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == Button_ReadFile) {
            JOptionPane.showMessageDialog(null, "读取文件成功", "提示", JOptionPane.INFORMATION_MESSAGE);
        }
        if (actionEvent.getSource() == Button_Lottery) {
            JOptionPane.showMessageDialog(null, "抽奖成功", "提示", JOptionPane.INFORMATION_MESSAGE);
        }
        if (actionEvent.getSource() == Box_AwardNum[0] || actionEvent.getSource() == Box_AwardNum[1] || actionEvent.getSource() == Box_AwardNum[2]) {
            for (int x = 0; x < 3; x++) {
                Label_awards[x].setVisible(Box_AwardNum[x].isSelected());
                Combo_awards[x].setVisible(Box_AwardNum[x].isSelected());
            }
        }
    }
}
