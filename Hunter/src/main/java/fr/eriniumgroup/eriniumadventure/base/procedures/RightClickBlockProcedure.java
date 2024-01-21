package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.RemotePlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.util.datafix.fixes.PlayerUUIDFix;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import org.spongepowered.asm.mixin.Final;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.UUID;

@Mod.EventBusSubscriber
public class RightClickBlockProcedure {
	@SubscribeEvent
	public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
		if (event.getHand() != event.getEntity().getUsedItemHand())
			return;
		execute(event);
	}

	public static void execute() {
		execute(null);
	}

	private static void execute(@Nullable Event event) {
		String s;

		s = Minecraft.getInstance().level.getPlayerByUUID(java.util.UUID.fromString("380df991-f603-344c-a090-369bad2a924a")).getName().getString();

		Minecraft.getInstance().level.getPlayerByUUID(java.util.UUID.fromString("380df991-f603-344c-a090-369bad2a924a")).getName();
	}
}
