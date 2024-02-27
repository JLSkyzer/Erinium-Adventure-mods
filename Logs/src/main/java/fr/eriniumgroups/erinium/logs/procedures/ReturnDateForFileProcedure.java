package fr.eriniumgroups.erinium.logs.procedures;

import java.util.Calendar;

import java.io.File;

public class ReturnDateForFileProcedure {
	public static String execute() {
		String time = "";
		File file = new File("");
		if (Calendar.getInstance().get(Calendar.MINUTE) >= 30) {
			time = new java.text.DecimalFormat("##").format(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) + "-" + new java.text.DecimalFormat("##").format(Calendar.getInstance().get(Calendar.MONTH) + 1) + "-"
					+ new java.text.DecimalFormat("##").format(Calendar.getInstance().get(Calendar.YEAR)) + "-" + new java.text.DecimalFormat("##").format(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) + "h30";
		} else {
			time = new java.text.DecimalFormat("##").format(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) + "-" + new java.text.DecimalFormat("##").format(Calendar.getInstance().get(Calendar.MONTH) + 1) + "-"
					+ new java.text.DecimalFormat("##").format(Calendar.getInstance().get(Calendar.YEAR)) + "-" + new java.text.DecimalFormat("##").format(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) + "h00";
		}
		return time;
	}
}
