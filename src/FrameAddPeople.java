import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameAddPeople implements ActionListener {
    FrameAddPeople() {
        JFrame frame = new JFrame("信息录入");
        Container MainFrame = frame.getContentPane();
        frame.setBounds(70, 70, 800, 400);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
