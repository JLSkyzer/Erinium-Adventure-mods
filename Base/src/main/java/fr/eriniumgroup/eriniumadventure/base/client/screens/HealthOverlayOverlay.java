
package fr.eriniumgroup.eriniumadventure.base.client.screens;

import fr.eriniumgroup.eriniumadventure.base.procedures.*;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import org.checkerframework.checker.units.qual.h;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.Minecraft;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.platform.GlStateManager;

@Mod.EventBusSubscriber({Dist.CLIENT})
public class HealthOverlayOverlay {
	@SubscribeEvent
	public static void renderOverlay(RenderGuiOverlayEvent.Pre event) {
		Player entity = Minecraft.getInstance().player;
		if (VanillaGuiOverlay.PLAYER_HEALTH.type() == event.getOverlay())
		{
			event.setCanceled(true);
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void eventHandler(RenderGuiEvent.Pre event) {
		int w = event.getWindow().getGuiScaledWidth();
		int h = event.getWindow().getGuiScaledHeight();
		Level world = null;
		double x = 0;
		double y = 0;
		double z = 0;
		Player entity = Minecraft.getInstance().player;
		if (entity != null) {
			world = entity.level();
			x = entity.getX();
			y = entity.getY();
			z = entity.getZ();
		}
		RenderSystem.disableDepthTest();
		RenderSystem.depthMask(false);
		RenderSystem.enableBlend();
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		RenderSystem.setShaderColor(1, 1, 1, 1);

		double HealthBarIndex = (88 / ReturnMaxHealthInProcedure.execute(entity));
		int HealthBarValue = (int) (HealthBarIndex * ReturnHealthIntProcedure.execute(entity));

		if (IsCreativeModProcedure.execute(entity)) {

			event.getGuiGraphics().blit(new ResourceLocation("erinium_adventure:textures/screens/health_bar_empty.png"), w / 2 + -91, h - 39 + ((int) PlayerHaveArmorProcedure.execute(entity) * -1), 0, 0, 90, 9, 90, 9);

			event.getGuiGraphics().blit(new ResourceLocation("erinium_adventure:textures/screens/health_bar.png"), w / 2 + -90, h - 38 + ((int) PlayerHaveArmorProcedure.execute(entity) * -1), 0, 0, HealthBarValue, 7, 88, 7);

			event.getGuiGraphics().drawString(Minecraft.getInstance().font,

					ReturnHealthProcedure.execute(entity), w / 2 + -45 - (Minecraft.getInstance().font.width(ReturnHealthProcedure.execute(entity)) / 2), h - 46 + ((int) PlayerHaveArmorProcedure.execute(entity) * -1), -1, false);
		}
		RenderSystem.depthMask(true);
		RenderSystem.defaultBlendFunc();
		RenderSystem.enableDepthTest();
		RenderSystem.disableBlend();
		RenderSystem.setShaderColor(1, 1, 1, 1);
	}
}
