package com.planit.core.framework;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.FileAppender;

public class NewFileOnRebootAppender extends FileAppender {

    public NewFileOnRebootAppender() {
    }

    @Override
    public void setFile(String file) {
        super.setFile(prependDate(file));
    }

    private static String prependDate(String filename) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_hhmmss");
	    System.setProperty("current.date", dateFormat.format(new Date()));
        return dateFormat.format(new Date()) + "_" + filename;
    }
}