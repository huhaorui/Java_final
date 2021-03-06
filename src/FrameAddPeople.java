import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FrameAddPeople implements TableModelListener {
    private ArrayList<People> people;
    private FrameMain frameMain;
    private JTable display = new JTable(0, 2);
    private JFrame frame = new JFrame("信息录入");

    private void saveFile() {
        File file = new File("database.dat");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ignored) {
            }
        }
        try {
            FileWriter fileWriter = new FileWriter(file.getName());
            for (People tmp : people) {
                fileWriter.write(tmp.getName() + ',' + tmp.getTel() + '\n');
            }
            fileWriter.close();
        } catch (IOException ignored) {
        }
    }

    private void setArrayList() {
        people.clear();
        int rows = display.getRowCount();
        for (int i = 0; i < rows; i++) {
            if (!display.getValueAt(i, 0).equals("") && !display.getValueAt(i, 1).equals("")) {
                people.add(new People((String) display.getValueAt(i, 0), (String) display.getValueAt(i, 1)));
            }
        }
        frameMain.people = people;
    }

    private void initFrame(int x, int y) {
        int RowCount = display.getRowCount();
        for (int i = 0; i < RowCount; i++) {
            display.remove(1);
        }
        DefaultTableModel model = (DefaultTableModel) display.getModel();//获取defaulttablemodel
        for (People tmp : people) {
            model.addRow(new String[]{tmp.getName(), tmp.getTel()});
        }
        model.addRow(new String[]{"", ""});
        display.getModel().addTableModelListener(this);
        JScrollPane displayArea = new JScrollPane(display);
        display.getColumnModel().getColumn(0).setHeaderValue("姓名");
        display.getColumnModel().getColumn(1).setHeaderValue("电话");
        display.setRowHeight(30);
        frame.setBounds(x + 20, y + 20, 300, 400);
        frame.add(displayArea);
        frame.setVisible(true);
    }

    FrameAddPeople(ArrayList<People> people, FrameMain frameMain, int x, int y) {
        this.frameMain = frameMain;
        this.people = people;
        initFrame(x, y);
    }

    @Override
    public void tableChanged(TableModelEvent tableModelEvent) {
        setArrayList();
        saveFile();
        if (!(display.getValueAt(display.getRowCount() - 1, 0).equals("") && display.getValueAt(display.getRowCount() - 1, 1).equals(""))) {
            ((DefaultTableModel) display.getModel()).addRow(new String[]{"", ""});
        }
        System.out.println("change");
    }
}
