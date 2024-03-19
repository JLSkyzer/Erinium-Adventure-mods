package fr.eriniumgroup.eriniumadventure.base.configuration;

import net.minecraftforge.common.ForgeConfigSpec;

public class ServerConfigConfiguration {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;
	public static final ForgeConfigSpec.ConfigValue<Double> HEALTH_MAX;
	static {
		BUILDER.push("Global");
		HEALTH_MAX = BUILDER.comment("The maximum of health upgrade a player can buy (note 1 level give 10 of health)").define("Health Max", (double) 18);
		BUILDER.pop();

		SPEC = BUILDER.build();
	}

}
