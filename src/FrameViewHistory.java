import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class FrameViewHistory {
    private String history;
    String subject;
    private ArrayList<People> people = new ArrayList<>();

    private int awardToInt(String award) {
        switch (award) {
            case "1":
                return 1;
            case "2":
                return 2;
            case "3":
                return 3;
            default:
                return 0;
        }
    }


    private void readFile() {
        try {
            File file = new File(history + ".dat");
            Scanner scanner = new Scanner(file);
            subject = scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] readline = scanner.nextLine().split(",");
                people.add(new People(readline[0], readline[1], awardToInt(readline[2])));
            }
        } catch (FileNotFoundException ignored) {
        }
    }

    FrameViewHistory(String history) {
        this.history = history;
        readFile();
        JFrame frame = new JFrame(history);
        frame.setBounds(150, 150, 500, 300);
        frame.setVisible(true);
        JLabel labelSubject = new JLabel("主题:" + subject);
        JTable display = new JTable(0, 3);
        display.setEnabled(false);
        JScrollPane displayArea = new JScrollPane(display);
        display.setBorder(BorderFactory.createEtchedBorder());
        display.getColumnModel().getColumn(0).setHeaderValue("姓名");
        display.getColumnModel().getColumn(1).setHeaderValue("电话");
        display.getColumnModel().getColumn(2).setHeaderValue("奖项");
        DefaultTableModel model = (DefaultTableModel) display.getModel();
        for (People tmp : people) {
            model.addRow(new String[]{tmp.getName(), tmp.getTel(), awardToString(tmp.getAward())});
        }
        frame.add("North", labelSubject);
        frame.add(displayArea);
    }

    private String awardToString(int award) {
        switch (award) {
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
}
