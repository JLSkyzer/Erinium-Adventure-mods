package fr.eriniumgroup.eriniumadventure.base.configuration;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ServerConfigConfiguration {
	public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
	public static final ModConfigSpec SPEC;
	public static final ModConfigSpec.ConfigValue<Double> HEALTH_MAX;
	static {
		BUILDER.push("Global");
		HEALTH_MAX = BUILDER.comment("The maximum of health upgrade a player can buy (note 1 level give 10 of health)").define("Health Max", (double) 18);
		BUILDER.pop();

		SPEC = BUILDER.build();
	}

}
