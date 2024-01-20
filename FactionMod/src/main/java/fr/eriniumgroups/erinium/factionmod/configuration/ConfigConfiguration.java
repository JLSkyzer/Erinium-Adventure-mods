package fr.eriniumgroups.erinium.factionmod.configuration;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.common.ForgeConfigSpec;

import net.minecraft.world.item.Items;

public class ConfigConfiguration {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;
	public static final ForgeConfigSpec.ConfigValue<Double> MAX_MEMBER;
	public static final ForgeConfigSpec.ConfigValue<String> ITEM_NEED_CREATE;
	public static final ForgeConfigSpec.ConfigValue<Double> ITEM_NUMBER_CREATE;
	static {
		BUILDER.push("Factions");
		MAX_MEMBER = BUILDER.define("Max Members", (double) 20);
		ITEM_NEED_CREATE = BUILDER.define("Item needed to create faction", ForgeRegistries.ITEMS.getKey(Items.DIAMOND).toString());
		ITEM_NUMBER_CREATE = BUILDER.define("Number of items needed to create faction", (double) 100);
		BUILDER.pop();

		SPEC = BUILDER.build();
	}

}
