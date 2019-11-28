import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FrameMain implements ActionListener {
    private JButton ButtonReadFile, ButtonLottery, ButtonSetting;
    private JCheckBox[] Box_AwardNum = {new JCheckBox("一等奖"), new JCheckBox("二等奖"), new JCheckBox("三等奖")};
    private JLabel[] Label_awards = {new JLabel("一等奖个数："), new JLabel("二等奖个数："), new JLabel("三等奖个数：")};
    private JComboBox[] Combo_awards = {addNum(new JComboBox<>()), addNum(new JComboBox<>()), addNum(new JComboBox<>())};

    private JComboBox addNum(JComboBox<Integer> jComboBox) {
        for (int x = 0; x < 10; x++) {
            jComboBox.addItem(x);
        }
        return jComboBox;
    }

    FrameMain() {
        JFrame frame = new JFrame("幸运观众手机号码抽取器");
        Container MainFrame = frame.getContentPane();
        frame.setBounds(50, 50, 800, 400);
        JPanel settingArea = new JPanel();
        settingArea.setLayout(new BoxLayout(settingArea, BoxLayout.Y_AXIS));
        JPanel displayArea = new JPanel();
        JPanel award = new JPanel();
        award.setLayout(new BoxLayout(award, BoxLayout.Y_AXIS));
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, settingArea, displayArea);
        JLabel Label_award = new JLabel("请选择要产生的奖项");
        ButtonReadFile = new JButton("读取文件");
        ButtonSetting = new JButton("信息录入");
        ButtonLottery = new JButton("抽奖!");
        ButtonReadFile.addActionListener(this);
        ButtonSetting.addActionListener(this);
        ButtonLottery.addActionListener(this);
        JPanel topArea=new JPanel();
        topArea.setLayout(new BoxLayout(topArea, BoxLayout.X_AXIS));
        topArea.add(ButtonReadFile);
        topArea.add(ButtonSetting);
        settingArea.add(topArea);
        settingArea.add(Label_award);
        for (JCheckBox tmp : Box_AwardNum) {
            tmp.addActionListener(this);
            award.add(tmp);
        }
        settingArea.add(award);
        JPanel awards = new JPanel();
        awards.setLayout(new BoxLayout(awards, BoxLayout.Y_AXIS));
        JPanel[] LabelCamboAward = {new JPanel(), new JPanel(), new JPanel()};
        for (int i = 0; i < 3; i++) {
            LabelCamboAward[i].setLayout(new BoxLayout(LabelCamboAward[i], BoxLayout.X_AXIS));
            LabelCamboAward[i].add(Label_awards[i]);
            Label_awards[i].setVisible(false);
            LabelCamboAward[i].add(Combo_awards[i]);
            Combo_awards[i].setVisible(false);
            awards.add(LabelCamboAward[i]);
        }
        settingArea.add(awards);
        settingArea.add(ButtonLottery);
        MainFrame.add(splitPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        splitPane.setDividerLocation(0.3);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == ButtonReadFile) {
            JOptionPane.showMessageDialog(null, "读取文件成功", "提示", JOptionPane.INFORMATION_MESSAGE);
        }
        if (actionEvent.getSource() == ButtonLottery) {
            JOptionPane.showMessageDialog(null, "抽奖成功", "提示", JOptionPane.INFORMATION_MESSAGE);
        }
        if (actionEvent.getSource()==ButtonSetting){
            //打开新的窗口
        }
        if (actionEvent.getSource() == Box_AwardNum[0] || actionEvent.getSource() == Box_AwardNum[1] || actionEvent.getSource() == Box_AwardNum[2]) {
            for (int x = 0; x < 3; x++) {
                Label_awards[x].setVisible(Box_AwardNum[x].isSelected());
                Combo_awards[x].setVisible(Box_AwardNum[x].isSelected());
            }
        }
    }
}
