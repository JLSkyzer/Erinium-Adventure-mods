package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.ModList;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import java.util.function.Supplier;
import java.util.Map;

import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import fr.eriniumgroup.eriniumadventure.base.network.EriniumAdventureModVariables;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

public class FlameBtnActionProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		{
			double _setval = (entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumAdventureModVariables.PlayerVariables())).fire_reduction + 0.1;
			entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.fire_reduction = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		if (ModList.get().isLoaded("ericonomy")) {
			// Procedure here
			java.io.File eriFile = new java.io.File("");
			com.google.gson.JsonObject eriJsonObject = new com.google.gson.JsonObject();
			eriFile = new File((FMLPaths.GAMEDIR.get().toString() + "/Ericonomy/accounts/"), File.separator + (entity.getUUID().toString() + ".json"));
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(eriFile));
				StringBuilder jsonstringbuilder = new StringBuilder();
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					jsonstringbuilder.append(line);
				}
				bufferedReader.close();
				eriJsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
				if (eriJsonObject.get("money").getAsDouble() >= ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY)
						.getOrCreateTag().getDouble("price"))) {
					eriJsonObject.addProperty("money",
							(eriJsonObject.get("money").getAsDouble()
									- ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof Supplier _splr && _splr.get() instanceof Map _slt ? ((Slot) _slt.get(0)).getItem() : ItemStack.EMPTY).getOrCreateTag()
											.getDouble("price"))));
				} else {
					eriJsonObject.addProperty("money", 0);
				}
				{
					Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
					try {
						FileWriter fileWriter = new FileWriter(eriFile);
						fileWriter.write(mainGSONBuilderVariable.toJson(eriJsonObject));
						fileWriter.close();
					} catch (IOException exception) {
						exception.printStackTrace();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			if (ModList.get().isLoaded("erinium_logs")) {
				if (world instanceof ServerLevel _level)
					_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(0, 0, 0), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
							"erilog " + "GameLoading" + " " + "info" + " " + "Mod Ericonomy not exist");
			} else {
				System.out.println("Erilog is not installed ! install here : https://github.com/JLSkyzer/Erinium-Adventure-mods/releases/tag/Erilog");
			}
		}
		{
			boolean _setval = false;
			entity.getCapability(EriniumAdventureModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.stat_initialised = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
