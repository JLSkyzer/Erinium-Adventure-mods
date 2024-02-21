package fr.eriniumgroups.erinium.jobs.client.gui;

import fr.eriniumgroups.erinium.jobs.procedures.*;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.GuiGraphics;

import java.util.HashMap;

import fr.eriniumgroups.erinium.jobs.world.inventory.RankInfoMenu;

import com.mojang.blaze3d.systems.RenderSystem;

public class RankInfoScreen extends AbstractContainerScreen<RankInfoMenu> {
	private final static HashMap<String, Object> guistate = RankInfoMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;

	public RankInfoScreen(RankInfoMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 427;
		this.imageHeight = 240;
	}

	private static final ResourceLocation texture = new ResourceLocation("eriniumjobs:textures/screens/rank_info.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		guiGraphics.blit(new ResourceLocation("eriniumjobs:textures/screens/rank_background.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 427, 240, 427, 240);

		guiGraphics.blit(new ResourceLocation("eriniumjobs:textures/screens/rank_bar_empty.png"), this.leftPos + ((427 - 250) / 2), this.topPos + 120 + Minecraft.getInstance().font.lineHeight + 1, 0, 0, 250, 6, 250, 6);

		guiGraphics.blit(new ResourceLocation("eriniumjobs:textures/screens/rank_bar_fill.png"), this.leftPos + ((427 - 250) / 2) + 1, this.topPos + 120 + Minecraft.getInstance().font.lineHeight + 1 + 1, 0, 0, (int) ((248 / ReturnCapXpProcedure.execute(entity)) * ReturnPlayerXpProcedure.execute(entity)), 4, 248, 4);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawCenteredString(this.font,
				ReturnJobIdProcedure.execute(entity), 0 + 214, 0, -1);
		guiGraphics.drawCenteredString(this.font,
				ReturnPlayerLevelStringProcedure.execute(entity), 0 + 214, 120 - ((this.font.lineHeight * 2) - 1), -1);
		guiGraphics.drawCenteredString(this.font,
				"§e" + new java.text.DecimalFormat("#,###.##").format(ReturnPlayerXpProcedure.execute(entity)) + "§r/ §6" + new java.text.DecimalFormat("#,###.##").format(ReturnCapXpProcedure.execute(entity)) + " §b(" + new java.text.DecimalFormat("###").format((ReturnPlayerXpProcedure.execute(entity) * 100) / ReturnCapXpProcedure.execute(entity)) + "%)", 0 + 214, 120 - ((this.font.lineHeight * 1) - 1), -1);
		guiGraphics.drawCenteredString(this.font,
				ReturnNextLevelInProcedure.execute(entity), 0 + 214, 120, -1);
	}

	@Override
	public void onClose() {
		super.onClose();
	}

	@Override
	public void init() {
		super.init();
	}
}
