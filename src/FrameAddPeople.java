import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class FrameAddPeople implements TableModelListener {
    private ArrayList<People> people;
    private FrameMain frameMain;
    private JTable display = new JTable(0, 2);
    ;
    private JFrame frame = new JFrame("信息录入");

    private void saveFile() {
        //TODO
    }

    private void setArrayList() {
        people.clear();
        int rows = display.getRowCount();
        for (int i = 0; i < rows; i++) {
            if (!((String) display.getValueAt(i, 0)).equals("") && !((String) display.getValueAt(i, 1)).equals("")) {
                people.add(new People((String) display.getValueAt(i, 0), (String) display.getValueAt(i, 1)));
            }
        }
        frameMain.people = people;
    }

    private void initFrame() {
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

        frame.setBounds(70, 70, 300, 400);
        frame.add(displayArea);
        frame.setVisible(true);
    }

    FrameAddPeople(ArrayList<People> people, FrameMain frameMain) {
        this.frameMain = frameMain;
        this.people = people;
        initFrame();
    }


    @Override
    public void tableChanged(TableModelEvent tableModelEvent) {
        saveFile();
        setArrayList();
        System.out.println("change");
    }
}
