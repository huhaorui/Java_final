import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class FrameAddPeople implements TableModelListener {

    FrameAddPeople(ArrayList<People> people) {
        JTable display = new JTable(0, 2);
        for (People tmp : people) {
            DefaultTableModel model = (DefaultTableModel) display.getModel();//获取defaulttablemodel
            model.addRow(new String[]{tmp.getName(), tmp.getTel()});
        }
        display.getModel().addTableModelListener(this);
        JScrollPane displayArea = new JScrollPane(display);
        display.getColumnModel().getColumn(0).setHeaderValue("姓名");
        display.getColumnModel().getColumn(1).setHeaderValue("电话");
        display.setRowHeight(30);
        JFrame frame = new JFrame("信息录入");
        frame.setBounds(70, 70, 800, 400);
        frame.add(displayArea);
        frame.setVisible(true);
    }


    @Override
    public void tableChanged(TableModelEvent tableModelEvent) {
        //TODO Save To File
        System.out.println("change");
    }
}
