package com.by.petrfeldsherov.indprogr.ui;

public enum FormatType {
    TXT("txt"), JSON("json"), XML("xml");

    private String formatSuffix;

    private FormatType(String formatSuffix) {
	this.formatSuffix = formatSuffix;
    }

    public String getFormatSuffix() {
	return formatSuffix;
    }
}