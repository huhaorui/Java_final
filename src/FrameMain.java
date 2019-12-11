import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class FrameMain extends JFrame implements ActionListener {
    private JButton ButtonReadFile, ButtonLottery, ButtonSetting, ButtonChooseHistory;
    private JCheckBox[] Box_AwardNum = {new JCheckBox("一等奖"), new JCheckBox("二等奖"), new JCheckBox("三等奖")};
    private JLabel[] LabelAwards = {new JLabel("一等奖个数："), new JLabel("二等奖个数："), new JLabel("三等奖个数：")};
    private JComboBox[] ComboAwards = {addNum(new JComboBox<>()), addNum(new JComboBox<>()), addNum(new JComboBox<>())};
    private JTable display = new JTable(0, 3);
    private JPanel[] LabelComboAward = {new JPanel(), new JPanel(), new JPanel()};
    private JTextField filedProject;
    ArrayList<People> people = new ArrayList<>();

    private JComboBox addNum(JComboBox<Integer> jComboBox) {
        for (int x = 1; x <= 255; x++) {
            jComboBox.addItem(x);
        }
        return jComboBox;
    }

    private void readFile() throws FileNotFoundException {
        people.clear();
        String[] readline;
        File file = new File("database.dat");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            readline = scanner.nextLine().split(",");
            people.add(new People(readline[0], readline[1]));
        }
        scanner.close();
    }

    private void saveLog() throws IOException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date(System.currentTimeMillis());
        String time = formatter.format(date);
        File newFile = new File(time + ".dat");
        File listFile = new File("filelist.dat");
        if (!listFile.exists()) {
            if (!listFile.createNewFile()) {
                throw new IOException();
            }
        }
        FileWriter fileWriter = new FileWriter(listFile.getName(), true);
        fileWriter.write(time + '\n');
        fileWriter.close();
        if (!newFile.exists()) {
            if (!newFile.createNewFile()) {
                throw new IOException();
            }
        }
        fileWriter = new FileWriter(newFile.getName());
        fileWriter.write(filedProject.getText() + '\n');
        for (People tmp : people) {
            if (tmp.getAward() != 0) {
                fileWriter.write(tmp.getName() + ',' + tmp.getTel() + ',' + tmp.getAward() + '\n');
            }
        }
        fileWriter.close();
    }

    private int getPrize(int n) {
        if (Box_AwardNum[n].isSelected()) {
            return (int) ComboAwards[n].getSelectedItem();
        } else {
            return 0;
        }
    }

    private String awardToString(int n) {
        switch (n) {
            case 1:
                return "一等奖";
            case 2:
                return "二等奖";
            case 3:
                return "三等奖";
            default:
                return "未获奖";
        }
    }

    private String telEncode(String tel) {
        if (tel.length() < 2) {
            return "**";
        }
        return tel.substring(0, tel.length() - 2) + "**";
    }

    private void lottery() {
        ((DefaultTableModel) display.getModel()).getDataVector().clear();
        Pool pool = new Pool(getPrize(0), getPrize(1), getPrize(2), people.size());
        for (People tmp : people) {
            tmp.setAward(pool.getPrize());
        }
        for (People tmp : people) {
            if (tmp.getAward() == 1) {
                DefaultTableModel model = (DefaultTableModel) display.getModel();//获取defaulttablemodel
                model.addRow(new String[]{tmp.getName(), telEncode(tmp.getTel()), awardToString(tmp.getAward())});
            }
        }
        for (People tmp : people) {
            if (tmp.getAward() == 2) {
                DefaultTableModel model = (DefaultTableModel) display.getModel();//获取defaulttablemodel
                model.addRow(new String[]{tmp.getName(), telEncode(tmp.getTel()), awardToString(tmp.getAward())});
            }
        }
        for (People tmp : people) {
            if (tmp.getAward() == 3) {
                DefaultTableModel model = (DefaultTableModel) display.getModel();//获取defaulttablemodel
                model.addRow(new String[]{tmp.getName(), telEncode(tmp.getTel()), awardToString(tmp.getAward())});
            }
        }
        try {
            saveLog();
        } catch (IOException ignored) {
        }
    }

    FrameMain(int x, int y) {
        setTitle("幸运观众手机号码抽取器");
        Container MainFrame = getContentPane();
        setBounds(x, y, 800, 400);
        JPanel settingArea = new JPanel();
        settingArea.setLayout(new BoxLayout(settingArea, BoxLayout.Y_AXIS));
        JScrollPane displayArea = new JScrollPane(display);
        MainFrame.add(displayArea);
        display.setBorder(BorderFactory.createEtchedBorder());
        display.getColumnModel().getColumn(0).setHeaderValue("姓名");
        display.getColumnModel().getColumn(1).setHeaderValue("电话");
        display.getColumnModel().getColumn(2).setHeaderValue("奖项");
        display.setEnabled(false);
        display.setRowHeight(30);
        display.setCellSelectionEnabled(false);
        JPanel award = new JPanel();
        award.setLayout(new BoxLayout(award, BoxLayout.X_AXIS));
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, settingArea, displayArea);
        JLabel labelAward = new JLabel("请选择要产生的奖项");
        ButtonReadFile = new JButton("读取文件");
        ButtonSetting = new JButton("信息录入");
        ButtonLottery = new JButton("抽奖!");
        ButtonChooseHistory = new JButton("历史记录");
        ButtonReadFile.addActionListener(this);
        ButtonSetting.addActionListener(this);
        ButtonLottery.addActionListener(this);
        ButtonChooseHistory.addActionListener(this);
        JPanel topArea = new JPanel();
        topArea.setLayout(new BoxLayout(topArea, BoxLayout.X_AXIS));
        topArea.add(ButtonReadFile);
        topArea.add(ButtonSetting);
        topArea.add(ButtonChooseHistory);
        JPanel nextArea = new JPanel();
        nextArea.setMaximumSize(new Dimension(240, 30));
        nextArea.setLayout(new BoxLayout(nextArea, BoxLayout.X_AXIS));
        JLabel labelProject = new JLabel("主题：");
        filedProject = new JTextField("抽奖");
        nextArea.add(labelProject);
        nextArea.add(filedProject);
        settingArea.add(topArea);
        settingArea.add(nextArea);
        settingArea.add(labelAward);
        for (JCheckBox tmp : Box_AwardNum) {
            tmp.addActionListener(this);
            award.add(tmp);
        }
        settingArea.add(award);
        JPanel awards = new JPanel();
        awards.setLayout(new BoxLayout(awards, BoxLayout.Y_AXIS));
        for (int i = 0; i < 3; i++) {
            LabelComboAward[i].setLayout(new BoxLayout(LabelComboAward[i], BoxLayout.X_AXIS));
            LabelComboAward[i].setMaximumSize(new Dimension(240, 0));
            LabelComboAward[i].add(LabelAwards[i]);
            LabelAwards[i].setVisible(false);
            LabelComboAward[i].add(ComboAwards[i]);
            ComboAwards[i].setVisible(false);
            awards.add(LabelComboAward[i]);
        }
        settingArea.add(awards);
        settingArea.add(ButtonLottery);
        MainFrame.add(splitPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        splitPane.setDividerLocation(0.35);
        try {
            readFile();
            JOptionPane.showMessageDialog(null, "读取文件成功", "提示", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException ignored) {
            JOptionPane.showMessageDialog(null, "请点击 信息录入 添加抽奖者", "提示", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == ButtonReadFile) {
            try {
                readFile();
                JOptionPane.showMessageDialog(null, "读取文件成功", "提示", JOptionPane.INFORMATION_MESSAGE);
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "读取文件错误", "提示", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (actionEvent.getSource() == ButtonLottery) {
            lottery();
            JOptionPane.showMessageDialog(null, "抽奖成功", "提示", JOptionPane.INFORMATION_MESSAGE);
        }
        if (actionEvent.getSource() == ButtonSetting) {
            new FrameAddPeople(people, this, getX(), getY());
        }
        if (actionEvent.getSource() == ButtonChooseHistory) {
            new FrameChooseHistory(getX(), getY());
        }
        if (actionEvent.getSource() == Box_AwardNum[0] || actionEvent.getSource() == Box_AwardNum[1] || actionEvent.getSource() == Box_AwardNum[2]) {
            for (int x = 0; x < 3; x++) {
                LabelAwards[x].setVisible(Box_AwardNum[x].isSelected());
                LabelComboAward[x].setMaximumSize(new Dimension(240, Box_AwardNum[x].isSelected() ? 30 : 0));
                ComboAwards[x].setVisible(Box_AwardNum[x].isSelected());
            }
        }
    }
}