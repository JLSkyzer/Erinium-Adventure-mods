package fr.eriniumgroup.eriniumadventure.base.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.MenuProvider;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import java.io.File;

import io.netty.buffer.Unpooled;

import fr.eriniumgroup.eriniumadventure.base.world.inventory.StatGui0Menu;

public class TestCmdProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		File File = new File("");
		//ItemStack item = ItemStack.EMPTY;
		//ItemStack item2 = ItemStack.EMPTY;

		entity.animateHurt(1.5F);

		{
			if (world.isClientSide()){
				if (entity instanceof LivingEntity livingEntity) {
					livingEntity.hurtDuration = 10;
					livingEntity.hurtTime = livingEntity.hurtDuration;
				}
			}

		}
	}
}
