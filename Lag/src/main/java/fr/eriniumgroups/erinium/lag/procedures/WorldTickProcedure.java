package fr.eriniumgroups.erinium.lag.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Comparator;
import java.util.ArrayList;

import fr.eriniumgroups.erinium.lag.network.EriniumLagModVariables;

@Mod.EventBusSubscriber
public class WorldTickProcedure {
	@SubscribeEvent
	public static void onWorldTick(TickEvent.LevelTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.level);
		}
	}

	public static void execute(LevelAccessor world) {
		execute(null, world);
	}

	private static void execute(@Nullable Event event, LevelAccessor world) {
		List<Object> coord_list = new ArrayList<>();
		double player_x = 0;
		double player_z = 0;
		double index = 0;
		if (EriniumLagModVariables.MapVariables.get(world).timer > 0) {
			if (EriniumLagModVariables.MapVariables.get(world).timer == 12000) {
				if (!world.isClientSide() && world.getServer() != null)
					world.getServer().getPlayerList().broadcastSystemMessage(Component.literal("\u00A76[\u00A7fErinium Lag\u00A76] \u00A7cItem and entity will be destroy in 10 minutes..."), false);
			} else if (EriniumLagModVariables.MapVariables.get(world).timer == 6000) {
				if (!world.isClientSide() && world.getServer() != null)
					world.getServer().getPlayerList().broadcastSystemMessage(Component.literal("\u00A76[\u00A7fErinium Lag\u00A76] \u00A7cItem and entity will be destroy in 5 minutes..."), false);
			} else if (EriniumLagModVariables.MapVariables.get(world).timer == 1200) {
				if (!world.isClientSide() && world.getServer() != null)
					world.getServer().getPlayerList().broadcastSystemMessage(Component.literal("\u00A76[\u00A7fErinium Lag\u00A76] \u00A7cItem and entity will be destroy in 1 minute..."), false);
			} else if (EriniumLagModVariables.MapVariables.get(world).timer == 600) {
				if (!world.isClientSide() && world.getServer() != null)
					world.getServer().getPlayerList().broadcastSystemMessage(Component.literal("\u00A76[\u00A7fErinium Lag\u00A76] \u00A7cItem and entity will be destroy in 30 seconds..."), false);
			} else if (EriniumLagModVariables.MapVariables.get(world).timer == 200) {
				if (!world.isClientSide() && world.getServer() != null)
					world.getServer().getPlayerList().broadcastSystemMessage(Component.literal("\u00A76[\u00A7fErinium Lag\u00A76] \u00A7cItem and entity will be destroy in 10 seconds..."), false);
			}
			EriniumLagModVariables.MapVariables.get(world).timer = EriniumLagModVariables.MapVariables.get(world).timer - 1;
			EriniumLagModVariables.MapVariables.get(world).syncData(world);
		} else {
			index = 0;
			for (Entity entityiterator : new ArrayList<>(world.players())) {
				coord_list.add((entityiterator.getX()));
				coord_list.add((entityiterator.getZ()));
			}
			while (coord_list.size() > 0) {
				{
					final Vec3 _center = new Vec3((coord_list.get(0) instanceof Double _d ? _d : 0), (-64), (coord_list.get(1) instanceof Double _d ? _d : 0));
					List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(32, 384, 32), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
							.collect(Collectors.toList());
					for (Entity entityiterator : _entfound) {
						if (!(entityiterator instanceof Player) || !(entityiterator instanceof ServerPlayer) || !(entityiterator instanceof WitherBoss) || !(entityiterator instanceof Villager)) {
							if (!entityiterator.level().isClientSide())
								entityiterator.discard();
							index = index + 1;
							coord_list.remove(0);
							coord_list.remove(0);
						}
					}
				}
			}
			if (!world.isClientSide() && world.getServer() != null)
				world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("\u00A76[\u00A7fErinium Lag\u00A76] \u00A7b" + new java.text.DecimalFormat("#,###").format(index) + " \u00A7aEntities as been deleted !")), false);
			EriniumLagModVariables.MapVariables.get(world).timer = 18000;
			EriniumLagModVariables.MapVariables.get(world).syncData(world);
		}
	}
}
