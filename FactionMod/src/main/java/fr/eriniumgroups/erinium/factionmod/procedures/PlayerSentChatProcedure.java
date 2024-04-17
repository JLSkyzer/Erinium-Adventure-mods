package fr.eriniumgroups.erinium.factionmod.procedures;

import net.neoforged.neoforge.event.ServerChatEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import javax.annotation.Nullable;

import java.util.ArrayList;

import fr.eriniumgroups.erinium.factionmod.network.EriniumFactionModVariables;

@Mod.EventBusSubscriber
public class PlayerSentChatProcedure {
	@SubscribeEvent
	public static void onChat(ServerChatEvent event) {
		execute(event, event.getPlayer().level(), event.getPlayer().getX(), event.getPlayer().getY(), event.getPlayer().getZ(), event.getPlayer(), event.getRawText());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, String text) {
		execute(null, world, x, y, z, entity, text);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity, String text) {
		if (entity == null || text == null)
			return;
		String temp_text = "";
		if (EriniumFactionModVariables.MapVariables.get(world).custom_chat) {
			if (event instanceof ICancellableEvent _cancellable) {
				_cancellable.setCanceled(true);
			}
			temp_text = text;
			for (Entity entityiterator : new ArrayList<>(world.players())) {
				if (temp_text.contains(entityiterator.getDisplayName().getString())) {
					temp_text = temp_text.replace(entityiterator.getDisplayName().getString(), "\u00A76\u00A7l" + entityiterator.getDisplayName().getString());
					if (entityiterator instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(
								Component.literal(("<\u00A7a" + entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_displayname + " " + entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).rank + " \u00A7e"
										+ entity.getDisplayName().getString() + "\u00A7r> " + (text.replace("&", "\u00A7")).replace(entityiterator.getDisplayName().getString(), "\u00A76\u00A7l" + entityiterator.getDisplayName().getString()))),
								false);
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
								("playsound erinium_faction:ding ambient " + entityiterator.getDisplayName().getString()));
				} else {
					if (entityiterator instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal(("<\u00A7a" + entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).faction_displayname + " " + entity.getData(EriniumFactionModVariables.PLAYER_VARIABLES).rank + " \u00A7e"
								+ entity.getDisplayName().getString() + "\u00A7r> " + text.replace("&", "\u00A7"))), false);
				}
			}
		}
	}
}
