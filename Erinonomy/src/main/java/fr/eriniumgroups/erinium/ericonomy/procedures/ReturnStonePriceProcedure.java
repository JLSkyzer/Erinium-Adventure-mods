package fr.eriniumgroups.erinium.ericonomy.procedures;

import fr.eriniumgroups.erinium.ericonomy.configuration.ServerConfigConfiguration;

public class ReturnStonePriceProcedure {
	public static String execute() {
		return "\u00A7e1 stone = \u00A72" + new java.text.DecimalFormat("#,###.##").format((double) ServerConfigConfiguration.COBBLEVOID_PRICEUNIT.get()) + "$";
	}
}
