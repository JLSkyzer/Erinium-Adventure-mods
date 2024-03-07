package fr.eriniumgroups.erinium.auctionhouse.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.nbt.StringTag;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

import com.google.gson.JsonObject;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

public class JoinTheWorldProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		File file = new File("");
		ItemStack temp_item = ItemStack.EMPTY;
		double idCount = 0;
		double lore_count = 0;
		com.google.gson.JsonObject JsonObject = new com.google.gson.JsonObject();
		file = new File((FMLPaths.GAMEDIR.get().toString() + "/EriniumAH/"), File.separator + (new java.text.DecimalFormat("##").format(idCount) + ".json"));
		while (file.exists()) {
			idCount = idCount + 1;
			file = new File((FMLPaths.GAMEDIR.get().toString() + "/EriniumAH/"), File.separator + (new java.text.DecimalFormat("##").format(idCount) + ".json"));
		}
		file = new File((FMLPaths.GAMEDIR.get().toString() + "/EriniumAH/"), File.separator + (new java.text.DecimalFormat("##").format(idCount) + ".json"));
		temp_item = new ItemStack(Blocks.DIRT);
		java.util.List<net.minecraft.network.chat.Component> componentList = new java.util.ArrayList<>();
		componentList.add(net.minecraft.network.chat.Component.literal("\u00A7bFirst lore"));
		componentList.add(net.minecraft.network.chat.Component.literal("\u00A76By \u00A76\u00A7lJLSkyzer"));
		if (componentList.size() > 0) {
			net.minecraft.nbt.CompoundTag display = temp_item.getOrCreateTagElement("display");
			net.minecraft.nbt.ListTag loreItems = new net.minecraft.nbt.ListTag();
			for (net.minecraft.network.chat.Component l : componentList) {
				if (l instanceof net.minecraft.network.chat.MutableComponent) {
					((net.minecraft.network.chat.MutableComponent) l).withStyle(style -> style.withItalic(style.isItalic()));
				}
				loreItems.add(StringTag.valueOf(net.minecraft.network.chat.Component.Serializer.toJson(l)));
			}
			display.put("Lore", loreItems);
		}
		temp_item.enchant(Enchantments.FLAMING_ARROWS, 1);
		temp_item.enchant(Enchantments.UNBREAKING, 10);
		temp_item.getOrCreateTag().putString("player", (entity.getDisplayName().getString()));
		temp_item.getOrCreateTag().putString("uuid", entity.getUUID().toString());
		temp_item.getOrCreateTag().putDouble("quantity", 1);
		temp_item.getOrCreateTag().putDouble("price", 1250);
		try {
			file.getParentFile().mkdirs();
			file.createNewFile();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		JsonObject.addProperty("item", (ForgeRegistries.ITEMS.getKey(temp_item.getItem()).toString()));
		if (temp_item != null) {
			String result = temp_item.getOrCreateTagElement("display").get("Lore").toString();
			result = result.replaceAll("'", "");
			com.google.gson.JsonArray jsonArray = com.google.gson.JsonParser.parseString(result).getAsJsonArray();
			for (int i = 0; i < jsonArray.size(); i++) {
				com.google.gson.JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
				String text = jsonObject.get("text").getAsString();
				JsonObject.addProperty(("lore" + new java.text.DecimalFormat("##").format(lore_count)), text);
				lore_count = lore_count + 1;
			}
		}
		JsonObject.addProperty("enchants", temp_item.getEnchantmentTags().toString());
		JsonObject.addProperty("damage", (temp_item.getDamageValue()));
		JsonObject.addProperty("player", (temp_item.getOrCreateTag().getString("player")));
		JsonObject.addProperty("uuid", (temp_item.getOrCreateTag().getString("uuid")));
		JsonObject.addProperty("quantity", (temp_item.getOrCreateTag().getDouble("quantity")));
		JsonObject.addProperty("price", (temp_item.getOrCreateTag().getDouble("price")));
		{
			Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
			try {
				FileWriter fileWriter = new FileWriter(file);
				fileWriter.write(mainGSONBuilderVariable.toJson(JsonObject));
				fileWriter.close();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
	}
}
