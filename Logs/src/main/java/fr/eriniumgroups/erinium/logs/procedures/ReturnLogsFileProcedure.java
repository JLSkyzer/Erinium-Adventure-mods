package fr.eriniumgroups.erinium.logs.procedures;

import net.minecraftforge.fml.loading.FMLPaths;

import java.util.Calendar;

import java.io.File;

public class ReturnLogsFileProcedure {
	public static File execute() {
		String time = "";
		File file = new File("");
		if (Calendar.getInstance().get(Calendar.MINUTE) >= 30) {
			time = new java.text.DecimalFormat("##").format(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) + "-" + new java.text.DecimalFormat("##").format(Calendar.getInstance().get(Calendar.MONTH) + 1) + "-"
					+ new java.text.DecimalFormat("##").format(Calendar.getInstance().get(Calendar.YEAR)) + "-" + new java.text.DecimalFormat("##").format(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) + "h30"
					+ new java.text.DecimalFormat("##").format(Calendar.getInstance().get(Calendar.MINUTE));
		} else {
			time = new java.text.DecimalFormat("##").format(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) + "-" + new java.text.DecimalFormat("##").format(Calendar.getInstance().get(Calendar.MONTH) + 1) + "-"
					+ new java.text.DecimalFormat("##").format(Calendar.getInstance().get(Calendar.YEAR)) + "-" + new java.text.DecimalFormat("##").format(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) + "h00";
		}
		return new File((FMLPaths.GAMEDIR.get().toString() + "/EriniumLogs/logs/"), File.separator + (time + ".erilog"));
	}
}
