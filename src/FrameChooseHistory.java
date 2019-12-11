import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
class FrameChooseHistory implements ActionListener {
    private JFrame frame;
    private JComboBox<String> historyList = new JComboBox<>();

    FrameChooseHistory(int x, int y) {
        frame = new JFrame("选择你要查看的记录");
        frame.setBounds(x + 20, y + 20, 400, 100);
        frame.setVisible(true);
        JLabel label = new JLabel("请选择你要查看的记录：");
        JButton button = new JButton("确定");
        button.addActionListener(this);
        try {
            File file = new File("filelist.dat");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String name = scanner.nextLine();
                historyList.addItem(name);
            }
            scanner.close();
        } catch (FileNotFoundException ignored) {
        }
        frame.add("North", label);
        frame.add("Center", historyList);
        frame.add("South", button);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        new FrameViewHistory((String) historyList.getSelectedItem(), frame.getX(), frame.getY());
    }
}
