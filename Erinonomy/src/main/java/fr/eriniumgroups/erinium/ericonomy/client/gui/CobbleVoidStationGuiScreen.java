package fr.eriniumgroups.erinium.ericonomy.client.gui;

import net.neoforged.neoforge.network.PacketDistributor;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import java.util.HashMap;

import fr.eriniumgroups.erinium.ericonomy.world.inventory.CobbleVoidStationGuiMenu;
import fr.eriniumgroups.erinium.ericonomy.procedures.ReturnStonePriceProcedure;
import fr.eriniumgroups.erinium.ericonomy.network.CobbleVoidStationGuiButtonMessage;

import com.mojang.blaze3d.systems.RenderSystem;

public class CobbleVoidStationGuiScreen extends AbstractContainerScreen<CobbleVoidStationGuiMenu> {
	private final static HashMap<String, Object> guistate = CobbleVoidStationGuiMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_sell;

	public CobbleVoidStationGuiScreen(CobbleVoidStationGuiMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("ericonomy:textures/screens/cobble_void_station_gui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
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
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.ericonomy.cobble_void_station_gui.label_cobble_void_station"), 6, 7, -12829636, false);
		guiGraphics.drawString(this.font,

				ReturnStonePriceProcedure.execute(), 6, 16, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		button_sell = Button.builder(Component.translatable("gui.ericonomy.cobble_void_station_gui.button_sell"), e -> {
			if (true) {
				PacketDistributor.SERVER.noArg().send(new CobbleVoidStationGuiButtonMessage(0, x, y, z));
				CobbleVoidStationGuiButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 69, this.topPos + 52, 36, 20).build();
		guistate.put("button:button_sell", button_sell);
		this.addRenderableWidget(button_sell);
	}
}
