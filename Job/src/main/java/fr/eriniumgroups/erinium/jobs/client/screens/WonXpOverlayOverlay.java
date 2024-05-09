
package fr.eriniumgroups.erinium.jobs.client.screens;

import com.mojang.blaze3d.platform.Window;
import org.checkerframework.checker.units.qual.h;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import net.neoforged.api.distmarker.Dist;

import net.neoforged.fml.common.Mod;

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
		int posX = event.getWindow().getGuiScaledWidth();
		int posY = event.getWindow().getGuiScaledHeight();
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
			EriniumjobsModVariables.PlayerVariables capability = entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES);
			int fontheight = Minecraft.getInstance().font.lineHeight;
			int finalX = (int) (posX * capability.won_xp_percent_x) / 100;
			int finalY = (int) (posY * capability.won_xp_percent_y) / 100;

			if (entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES).won_xp_percent_x >= 51) {
				finalX = finalX - 120; // Taille de mon élément
			}
			if (entity.getData(EriniumjobsModVariables.PLAYER_VARIABLES).won_xp_percent_y >= 51) {
				finalY = finalY - 44; // Taille de mon élément
			}

			if (entity != null)
				//(entity.getCapability(EriniumjobsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new EriniumjobsModVariables.PlayerVariables())).won_xp_x + 1
				//capability.won_xp_x = _setval;

				event.getGuiGraphics().blit(new ResourceLocation("eriniumjobs:textures/screens/won_xp_overlay_screen.png"), finalX, finalY, 0, 0, 120, 40, 120, 40);

				event.getGuiGraphics().blit(new ResourceLocation("eriniumjobs:textures/screens/barre_3_seconds.png"), finalX, (int) (finalY + 40), 0, 0, (int) (2 * capability.won_xp_timer), 4, 120, 4);

				event.getGuiGraphics().drawCenteredString(Minecraft.getInstance().font,
						ReturnMessage1Procedure.execute(entity), (int) (finalX + 2 + 58), (int) (finalY + 5), -1);

				event.getGuiGraphics().drawCenteredString(Minecraft.getInstance().font,
						ReturnMessage2Procedure.execute(entity), (int) (finalX + 2 + 58), (int) (finalY + 34 - fontheight), -1);

				capability.syncPlayerVariables(entity);
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
