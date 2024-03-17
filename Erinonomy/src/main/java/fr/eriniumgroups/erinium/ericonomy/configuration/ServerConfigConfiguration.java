package fr.eriniumgroups.erinium.ericonomy.configuration;

import net.minecraftforge.common.ForgeConfigSpec;

public class ServerConfigConfiguration {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;
	public static final ForgeConfigSpec.ConfigValue<Double> START_MONEY;
	public static final ForgeConfigSpec.ConfigValue<Double> COBBLEVOID_PRICEUNIT;
	static {
		BUILDER.push("Basic");
		START_MONEY = BUILDER.comment("Start money when first join").define("Start money", (double) 500);
		COBBLEVOID_PRICEUNIT = BUILDER.comment("The prince of one stones on the cobble void").define("Cobble Void price for one stone", (double) 1);
		BUILDER.pop();

		SPEC = BUILDER.build();
	}

}
