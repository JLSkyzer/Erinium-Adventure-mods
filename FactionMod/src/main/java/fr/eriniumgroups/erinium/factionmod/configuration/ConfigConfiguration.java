package fr.eriniumgroups.erinium.factionmod.configuration;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.common.ForgeConfigSpec;

import net.minecraft.world.item.Items;

public class ConfigConfiguration {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;
	public static final ForgeConfigSpec.ConfigValue<String> ITEM_NEED_CREATE;
	public static final ForgeConfigSpec.ConfigValue<Double> ITEM_NUMBER_CREATE;
	public static final ForgeConfigSpec.ConfigValue<Double> MAX_MEMBER;
	static {
		BUILDER.push("Common");
		ITEM_NEED_CREATE = BUILDER.comment("Item needed to create a faction").define("Item need", ForgeRegistries.ITEMS.getKey(Items.DIAMOND).toString());
		ITEM_NUMBER_CREATE = BUILDER.comment("Iten needed number").define("Number item needed", (double) 100);
		MAX_MEMBER = BUILDER.comment("Max member in a faction").define("Max member", (double) 20);
		BUILDER.pop();

		SPEC = BUILDER.build();
	}

}
