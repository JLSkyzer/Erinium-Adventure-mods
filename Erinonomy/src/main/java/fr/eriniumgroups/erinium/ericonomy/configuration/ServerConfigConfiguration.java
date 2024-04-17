package fr.eriniumgroups.erinium.ericonomy.configuration;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ServerConfigConfiguration {
	public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
	public static final ModConfigSpec SPEC;
	public static final ModConfigSpec.ConfigValue<Double> START_MONEY;
	public static final ModConfigSpec.ConfigValue<Double> COBBLEVOID_PRICEUNIT;
	static {
		BUILDER.push("Basic");
		START_MONEY = BUILDER.comment("Start money when first join").define("Start money", (double) 500);
		COBBLEVOID_PRICEUNIT = BUILDER.comment("The prince of one stones on the cobble void").define("Cobble Void price for one stone", (double) 1);
		BUILDER.pop();

		SPEC = BUILDER.build();
	}

}
