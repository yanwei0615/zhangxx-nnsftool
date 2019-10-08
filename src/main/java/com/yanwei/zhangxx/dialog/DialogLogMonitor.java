package com.yanwei.zhangxx.dialog;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

/**
 * Description:日志信息监听窗口<br>
 * Copyright: Copyright (c) 2015<br>
 * Company: 河南电力科学研究院智能电网所<br>
 *
 * @author shangbingbing 2015-01-01编写
 * @version 1.0
 */
public class DialogLogMonitor extends JDialog {
    private static final long serialVersionUID = 1L;
    private JTextArea txtLogInfo;

    public static void main(String[] args) {
        try {
            //设置系统观感器
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            DialogLogMonitor dialog = new DialogLogMonitor();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 日志信息变更监听处理（关键点）
     */
    private void init() {
        LogMonitor.addLogChangedListener(new LogChangedListener() {
            @Override
            public void EventActivated(LogChangedEvent me) {
                txtLogInfo.setText(LogMonitor.getLogs().toString());
                txtLogInfo.setCaretPosition(txtLogInfo.getText().length());
                txtLogInfo.paintImmediately(txtLogInfo.getBounds());
            }
        });
    }

    public DialogLogMonitor() {
        setResizable(true);
        setTitle("日志监控器");
        setBounds(100, 100, 1000, 500);
        JScrollPane scrollPane = new JScrollPane();
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scrollPane)
                                .addContainerGap())
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                                .addContainerGap())
        );
        txtLogInfo = new JTextArea();
        txtLogInfo.setEditable(false);
        txtLogInfo.setLineWrap(true);
        scrollPane.setViewportView(txtLogInfo);
        getContentPane().setLayout(groupLayout);
        this.init();
    }
}