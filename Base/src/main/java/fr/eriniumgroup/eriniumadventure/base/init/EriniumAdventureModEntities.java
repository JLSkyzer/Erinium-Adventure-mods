
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.eriniumgroup.eriniumadventure.base.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;

import fr.eriniumgroup.eriniumadventure.base.entity.RocketHeadEntity;
import fr.eriniumgroup.eriniumadventure.base.entity.RocketBoosterEntity;
import fr.eriniumgroup.eriniumadventure.base.EriniumAdventureMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EriniumAdventureModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, EriniumAdventureMod.MODID);
	public static final RegistryObject<EntityType<RocketBoosterEntity>> ROCKET_BOOSTER = register("rocket_booster", EntityType.Builder.<RocketBoosterEntity>of(RocketBoosterEntity::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true)
			.setTrackingRange(32).setUpdateInterval(3).setCustomClientFactory(RocketBoosterEntity::new).fireImmune().sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<RocketHeadEntity>> ROCKET_HEAD = register("rocket_head", EntityType.Builder.<RocketHeadEntity>of(RocketHeadEntity::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(32)
			.setUpdateInterval(3).setCustomClientFactory(RocketHeadEntity::new).fireImmune().sized(0.6f, 1.8f));

	private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
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
