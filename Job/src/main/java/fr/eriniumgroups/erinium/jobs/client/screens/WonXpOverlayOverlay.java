
package fr.eriniumgroups.erinium.jobs.client.screens;

import com.mojang.blaze3d.platform.Window;
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

import fr.eriniumgroups.erinium.jobs.procedures.WonXpOverlayConditionProcedure;
import fr.eriniumgroups.erinium.jobs.procedures.ReturnMessage2Procedure;
import fr.eriniumgroups.erinium.jobs.procedures.ReturnMessage1Procedure;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.world.entity.Entity;
import fr.eriniumgroups.erinium.jobs.network.EriniumjobsModVariables;

@Mod.EventBusSubscriber({Dist.CLIENT})
public class WonXpOverlayOverlay {
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void eventHandler(RenderGuiEvent.Pre event) {
		int w = event.getWindow().getGuiScaledWidth();
		int h = event.getWindow().getGuiScaledHeight();
		int posX = 0;
		int posY = 0;
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
		if (WonXpOverlayConditionProcedure.execute(entity)) {
			if (entity != null)
				//(entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumjobsModVariables.PlayerVariables())).won_xp_x + 1
				entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					//capability.won_xp_x = _setval;
					int overlayx = (int) ((Minecraft.getInstance().getWindow().getGuiScaledWidth() * capability.won_xp_x) / 100);
					int overlayy = (int) ((Minecraft.getInstance().getWindow().getGuiScaledHeight() * capability.won_xp_y) / 100);
					int fontheight = Minecraft.getInstance().font.lineHeight;

					if((capability.won_xp_x) >= 51){
						overlayx = overlayx - 120;
					}
					if((capability.won_xp_y) >= 51){
						overlayy = overlayy - 44;
					}

					event.getGuiGraphics().blit(new ResourceLocation("eriniumjobs:textures/screens/won_xp_overlay_screen.png"), posX + overlayx, posY + overlayy, 0, 0, 120, 40, 120, 40);

					event.getGuiGraphics().blit(new ResourceLocation("eriniumjobs:textures/screens/barre_3_seconds.png"), posX + overlayx, posY + overlayy + 40, 0, 0, (int) (2 * capability.won_xp_timer), 4, 120, 4);

					event.getGuiGraphics().drawString(Minecraft.getInstance().font,

							ReturnMessage1Procedure.execute(entity), posX + overlayx + 2, posY + overlayy + 5, -1, false);
					event.getGuiGraphics().drawString(Minecraft.getInstance().font,

							ReturnMessage2Procedure.execute(entity), posX + overlayx + 2, posY + overlayy + 34 - fontheight, -1, false);

					capability.syncPlayerVariables(entity);
				});
			{
				return;
			}
		}
		RenderSystem.depthMask(true);
		RenderSystem.defaultBlendFunc();
		RenderSystem.enableDepthTest();
		RenderSystem.disableBlend();
		RenderSystem.setShaderColor(1, 1, 1, 1);
	}
}
