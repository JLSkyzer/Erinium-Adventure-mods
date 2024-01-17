package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.client.Minecraft;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class RightClickBlockProcedure {
	@SubscribeEvent
	public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
		if (event.getHand() != event.getEntity().getUsedItemHand())
			return;
		execute(event, event.getLevel(), event.getPos().getX(), event.getPos().getZ(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double z, Entity entity) {
		execute(null, world, x, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(new Object() {
				private String getChunkX(int x, int z) {
					BlockPos blockpos = Minecraft.getInstance().getCameraEntity().blockPosition();
					ChunkPos chunkpos = new ChunkPos(new BlockPos(40, 0, 23));
					ChunkPos chunkpos2 = new ChunkPos(new BlockPos(-13, 0, -30));
				}
			}.getChunkX((int) x, (int) z)), false);
	}


}
