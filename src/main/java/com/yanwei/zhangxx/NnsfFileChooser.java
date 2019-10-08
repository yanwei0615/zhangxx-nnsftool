package com.yanwei.zhangxx;

import com.yanwei.zhangxx.dialog.DialogLogMonitor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;

public class NnsfFileChooser implements ActionListener {
    JFrame frame = new JFrame("国家自然科学基金分析");// 框架布局
    JTabbedPane tabPane = new JTabbedPane();// 选项卡布局
    Container con = new Container();//
    JLabel label1 = new JLabel("源文件:");
    JLabel label2 = new JLabel("输出文件:");
    JTextField text1 = new JTextField();// TextField 目录的路径
    JTextField text2 = new JTextField();// 文件的路径
    JButton button1 = new JButton("...");// 选择
    JButton button2 = new JButton("...");// 选择
    JFileChooser jfc = new JFileChooser();// 文件选择器
    JButton button3 = new JButton("确定");//

    NnsfFileChooser() {
        jfc.setCurrentDirectory(new File("d://"));// 文件选择器的初始目录定为d盘

        double lx = Toolkit.getDefaultToolkit().getScreenSize().getWidth();

        double ly = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

        frame.setLocation(new Point((int) (lx / 2) - 150, (int) (ly / 2) - 150));// 设定窗口出现位置
        frame.setSize(280*4, 800);// 设定窗口大小
        frame.setContentPane(tabPane);// 设置布局
        label1.setBounds(10, 10, 280, 80);
        text1.setBounds(75, 10, 480, 80);
        button1.setBounds(600, 10, 200, 80);
        label2.setBounds(10, 100, 280, 80);
        text2.setBounds(75, 100, 480, 80);
        button2.setBounds(600, 100, 200, 80);
        button3.setBounds(280, 190, 240, 80);


        button1.addActionListener(this); // 添加事件处理
        button2.addActionListener(this); // 添加事件处理
        button3.addActionListener(this); // 添加事件处理

        con.add(label1);
        con.add(text1);
        con.add(button1);
        con.add(label2);
        con.add(text2);
        con.add(button2);
        con.add(button3);
        frame.setVisible(true);// 窗口可见
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 使能关闭窗口，结束程序
        tabPane.add("1面板", con);// 添加布局1
    }

    /**
     * 时间监听的方法
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        jfc.setFileFilter(new FileFilter() {
            @Override
            public String getDescription() {
                return ".xlsx";
            }

            @Override
            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".xlsx");
            }
        });

        // TODO Auto-generated method stub
        if (e.getSource().equals(button1)) {// 判断触发方法的按钮是哪个
            jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);// 设定只能选择到文件
            int state = jfc.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句
            if (state == 1) {
                return;
            } else {
                File f = jfc.getSelectedFile();// f为选择到的目录
                text1.setText(f.getAbsolutePath());
            }
        }
        // 绑定到选择文件，先择文件事件
        if (e.getSource().equals(button2)) {
            jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);// 设定只能选择到文件
            int state = jfc.showSaveDialog(null);// 此句是打开文件选择器界面的触发语句
            if (state == 1) {
                return;// 撤销则返回
            } else {
                File f = jfc.getSelectedFile();// f为选择到的文件
                text2.setText(f.getAbsolutePath());
            }
        }
        if (e.getSource().equals(button3)) {
            try {
                try {
                    //设置系统观感器
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                DialogLogMonitor dialogLogMonitor = new DialogLogMonitor();
                dialogLogMonitor.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialogLogMonitor.setVisible(true);

                NationalNaturalScienceFoundation.generateNnsf(text1.getText(), text2.getText());
                button3.setEnabled(false);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
