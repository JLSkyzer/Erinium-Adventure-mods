package fr.eriniumgroups.erinium.ericonomy.procedures;

import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import javax.annotation.Nullable;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

import fr.eriniumgroups.erinium.ericonomy.configuration.ServerConfigConfiguration;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

@Mod.EventBusSubscriber
public class JoinTheWorldProcedure {
	@SubscribeEvent
	public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		execute(event, event.getEntity().level(), event.getEntity());
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		File file = new File("");
		com.google.gson.JsonObject jsonObject = new com.google.gson.JsonObject();
		file = new File((FMLPaths.GAMEDIR.get().toString() + "/Ericonomy/accounts/"), File.separator + (entity.getUUID().toString() + ".json"));
		if (!file.exists()) {
			try {
				file.getParentFile().mkdirs();
				file.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			jsonObject.addProperty("money", (double) ServerConfigConfiguration.START_MONEY.get());
			jsonObject.addProperty("ericonomy.admin", false);
			{
				Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
				try {
					FileWriter fileWriter = new FileWriter(file);
					fileWriter.write(mainGSONBuilderVariable.toJson(jsonObject));
					fileWriter.close();
				} catch (IOException exception) {
					exception.printStackTrace();
				}
			}
			if (ModList.get().isLoaded("erinium_logs")) {
				if (world instanceof ServerLevel _level)
					_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(0, 0, 0), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
							"erilog " + "Ericonomy" + " " + "info" + " " + ("First joined player " + entity.getDisplayName().getString() + "(" + entity.getUUID().toString() + ") created economy file"));
			} else {
				System.out.println("Erilog is not installed ! install here : https://github.com/JLSkyzer/Erinium-Adventure-mods/releases/tag/Erilog");
			}
		}
	}
}
