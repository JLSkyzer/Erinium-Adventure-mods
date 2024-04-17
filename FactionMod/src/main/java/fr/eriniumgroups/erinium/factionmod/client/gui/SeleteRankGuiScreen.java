package fr.eriniumgroups.erinium.factionmod.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import java.util.HashMap;

import fr.eriniumgroups.erinium.factionmod.world.inventory.SeleteRankGuiMenu;
import fr.eriniumgroups.erinium.factionmod.network.SeleteRankGuiButtonMessage;
import fr.eriniumgroups.erinium.factionmod.EriniumFactionMod;

import com.mojang.blaze3d.systems.RenderSystem;

public class SeleteRankGuiScreen extends AbstractContainerScreen<SeleteRankGuiMenu> {
	private final static HashMap<String, Object> guistate = SeleteRankGuiMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_recruit;
	Button button_member;
	Button button_ancient;
	Button button_officier;

	public SeleteRankGuiScreen(SeleteRankGuiMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("erinium_faction:textures/screens/selete_rank_gui.png");

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
		guiGraphics.drawString(this.font, Component.translatable("gui.erinium_faction.selete_rank_gui.label_select_the_rank_to_modify"), 24, 7, -16777216, false);
	}

	@Override
	public void init() {
		super.init();
		button_recruit = Button.builder(Component.translatable("gui.erinium_faction.selete_rank_gui.button_recruit"), e -> {
			if (true) {
				EriniumFactionMod.PACKET_HANDLER.sendToServer(new SeleteRankGuiButtonMessage(0, x, y, z));
				SeleteRankGuiButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 6, this.topPos + 43, 54, 20).build();
		guistate.put("button:button_recruit", button_recruit);
		this.addRenderableWidget(button_recruit);
		button_member = Button.builder(Component.translatable("gui.erinium_faction.selete_rank_gui.button_member"), e -> {
			if (true) {
				EriniumFactionMod.PACKET_HANDLER.sendToServer(new SeleteRankGuiButtonMessage(1, x, y, z));
				SeleteRankGuiButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 114, this.topPos + 43, 54, 20).build();
		guistate.put("button:button_member", button_member);
		this.addRenderableWidget(button_member);
		button_ancient = Button.builder(Component.translatable("gui.erinium_faction.selete_rank_gui.button_ancient"), e -> {
			if (true) {
				EriniumFactionMod.PACKET_HANDLER.sendToServer(new SeleteRankGuiButtonMessage(2, x, y, z));
				SeleteRankGuiButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}).bounds(this.leftPos + 6, this.topPos + 79, 54, 20).build();
		guistate.put("button:button_ancient", button_ancient);
		this.addRenderableWidget(button_ancient);
		button_officier = Button.builder(Component.translatable("gui.erinium_faction.selete_rank_gui.button_officier"), e -> {
			if (true) {
				EriniumFactionMod.PACKET_HANDLER.sendToServer(new SeleteRankGuiButtonMessage(3, x, y, z));
				SeleteRankGuiButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		}).bounds(this.leftPos + 114, this.topPos + 79, 54, 20).build();
		guistate.put("button:button_officier", button_officier);
		this.addRenderableWidget(button_officier);
	}
}
