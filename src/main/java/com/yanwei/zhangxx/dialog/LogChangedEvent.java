package com.yanwei.zhangxx.dialog;

/**
 * Description:日志信息事件<br>
 * Copyright: Copyright (c) 2015<br>
 * Company: 河南电力科学研究院智能电网所<br>
 *
 * @author shangbingbing 2015-01-01编写
 * @version 1.0
 */
public class LogChangedEvent extends java.util.EventObject {
    private static final long serialVersionUID = 7573194493258326711L;
    public LogChangedEvent(Object source) {
        super(source);
    }
}