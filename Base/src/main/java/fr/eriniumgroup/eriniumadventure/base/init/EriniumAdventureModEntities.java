
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.eriniumadventure.base.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.registries.Registries;

import fr.eriniumgroup.eriniumadventure.base.entity.RocketHeadEntity;
import fr.eriniumgroup.eriniumadventure.base.entity.RocketBoosterEntity;
import fr.eriniumgroup.eriniumadventure.base.EriniumAdventureMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EriniumAdventureModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(Registries.ENTITY_TYPE, EriniumAdventureMod.MODID);
	public static final DeferredHolder<EntityType<?>, EntityType<RocketBoosterEntity>> ROCKET_BOOSTER = register("rocket_booster",
			EntityType.Builder.<RocketBoosterEntity>of(RocketBoosterEntity::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(32).setUpdateInterval(3).fireImmune().sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<RocketHeadEntity>> ROCKET_HEAD = register("rocket_head",
			EntityType.Builder.<RocketHeadEntity>of(RocketHeadEntity::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(32).setUpdateInterval(3).fireImmune().sized(0.6f, 1.8f));

	private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			RocketBoosterEntity.init();
			RocketHeadEntity.init();
		});
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(ROCKET_BOOSTER.get(), RocketBoosterEntity.createAttributes().build());
		event.put(ROCKET_HEAD.get(), RocketHeadEntity.createAttributes().build());
	}
}
