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

import fr.eriniumgroups.erinium.factionmod.world.inventory.BlacklistItemGuiMenu;
import fr.eriniumgroups.erinium.factionmod.procedures.BlackListItemGetPageProcedure;
import fr.eriniumgroups.erinium.factionmod.network.BlacklistItemGuiButtonMessage;
import fr.eriniumgroups.erinium.factionmod.EriniumFactionMod;

import com.mojang.blaze3d.systems.RenderSystem;

public class BlacklistItemGuiScreen extends AbstractContainerScreen<BlacklistItemGuiMenu> {
	private final static HashMap<String, Object> guistate = BlacklistItemGuiMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_prev;
	Button button_next;

	public BlacklistItemGuiScreen(BlacklistItemGuiMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("erinium_faction:textures/screens/blacklist_item_gui.png");

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
		guiGraphics.drawString(this.font, Component.translatable("gui.erinium_faction.blacklist_item_gui.label_blacklist_item"), 6, 7, -16777216, false);
		guiGraphics.drawString(this.font,

				BlackListItemGetPageProcedure.execute(entity), 87, 7, -16777216, false);
	}

	@Override
	public void init() {
		super.init();
		button_prev = Button.builder(Component.translatable("gui.erinium_faction.blacklist_item_gui.button_prev"), e -> {
			if (true) {
				EriniumFactionMod.PACKET_HANDLER.sendToServer(new BlacklistItemGuiButtonMessage(0, x, y, z));
				BlacklistItemGuiButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 6, this.topPos + 61, 46, 20).build();
		guistate.put("button:button_prev", button_prev);
		this.addRenderableWidget(button_prev);
		button_next = Button.builder(Component.translatable("gui.erinium_faction.blacklist_item_gui.button_next"), e -> {
			if (true) {
				EriniumFactionMod.PACKET_HANDLER.sendToServer(new BlacklistItemGuiButtonMessage(1, x, y, z));
				BlacklistItemGuiButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 123, this.topPos + 61, 46, 20).build();
		guistate.put("button:button_next", button_next);
		this.addRenderableWidget(button_next);
	}
}
