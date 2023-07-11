import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.*;

class MyFrame extends JFrame implements ActionListener{
    JTextField textField = new JTextField(20);
    JButton cancelButton = new JButton("キャンセル");
    JButton OKButton = new JButton("OK");
    JRadioButton AllDay = new JRadioButton("終日");
    JComboBox<String> monthComboBox = new JComboBox<String>();
    JComboBox<String> dayComboBox = new JComboBox<String>();
    JComboBox<String> startHourComboBox = new JComboBox<String>();
    JComboBox<String> startMinuteComboBox = new JComboBox<String>();
    JComboBox<String> endHourComboBox = new JComboBox<String>();
    JComboBox<String> endMinuteComboBox = new JComboBox<String>();

    MyFrame(){
        getComboBox(1, 13, monthComboBox);
        getComboBox(1, 32, dayComboBox);
        getComboBox(0, 24, startHourComboBox);
        getComboBox(0, 60, startMinuteComboBox);
        getComboBox(0, 24, endHourComboBox);
        getComboBox(0, 60, endMinuteComboBox);
    }
    void getComboBox(int start, int end,JComboBox<String> comboBox){
        for(int i = start; i < end; i++){
            comboBox.addItem(Integer.valueOf(i).toString());
        }
    }

    void InputTaskFrame(){
        JRadioButton AllDate = new JRadioButton("終日");
        //JLabel DateLabel = new JLabel("予定");
        getContentPane().setLayout(new GridLayout(2, 1));

        JPanel upPanel = new JPanel();
        upPanel.setLayout(new GridLayout(2, 2));
        JPanel setPanel = new JPanel();
        setPanel.setLayout(new FlowLayout());
        setPanel.add(monthComboBox);
        setPanel.add(new JLabel("月"));
        setPanel.add(dayComboBox);
        setPanel.add(new JLabel("日"));

        setPanel.add(startHourComboBox);
        setPanel.add(new JLabel("時"));
        setPanel.add(startMinuteComboBox);
        setPanel.add(new JLabel("分"));

        setPanel.add(new JLabel("～"));

        setPanel.add(endHourComboBox);
        setPanel.add(new JLabel("時"));
        setPanel.add(endMinuteComboBox);
        setPanel.add(new JLabel("分"));
        upPanel.add(setPanel);
        getContentPane().add(upPanel);

        JPanel okPanel = new JPanel();
        okPanel.add(AllDay);
        okPanel.add(BorderLayout.CENTER, textField);
        okPanel.add(cancelButton);
        okPanel.add(OKButton);
        getContentPane().add(okPanel);

        ImageIcon icon = new ImageIcon("./img/icon.jpg");
        setIconImage(icon.getImage());

        cancelButton.addActionListener(this);
        OKButton.addActionListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e){
        int startHour;
        int startMinute;
        int month;
        int day;
        int endHour;
        int endMinute;

        if (e.getSource() == cancelButton){
            textField.setText("");
        }else if (e.getSource() == OKButton){
            if (AllDay.isSelected()){
                startHour = -1;
                startMinute = -1;
                endHour = -1;
                endMinute = -1;
            }else{
                startHour = Integer.parseInt(startHourComboBox.getSelectedItem().toString());
                startMinute = Integer.parseInt(startMinuteComboBox.getSelectedItem().toString());
                endHour = Integer.parseInt(endHourComboBox.getSelectedItem().toString());
                endMinute = Integer.parseInt(endMinuteComboBox.getSelectedItem().toString());
            }
            month = Integer.parseInt(monthComboBox.getSelectedItem().toString());
            day = Integer.parseInt(dayComboBox.getSelectedItem().toString());
            String task = textField.getText();
            System.out.println("Month:" + month);
            System.out.println("Day:" + day);
            System.out.println("Hour:" + startHour + "~" + endHour);
            System.out.println("Minute:" + startMinute + "~" + endMinute);
            System.out.println("task:" + task);
            try{
                FileWriter fw = new FileWriter("./src/schedule.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);

                bw.write("month:" + month + "\n");
                bw.write("day:" + day + "\n");
                bw.write("start_hour:" + startHour + "\n");
                bw.write("end_hour:" + endHour + "\n");
                bw.write("start_minute:" + startMinute + "\n");
                bw.write("end_minute:" + endMinute + "\n");
                bw.write("task:" + task + "\n");

                bw.close();
            }catch (IOException error){
                System.out.println(error);
            }
        }

    }
}
public class Main {
    public static void main(String[] args) {

        MyFrame myframe = new MyFrame();
        myframe.InputTaskFrame();
    }


}