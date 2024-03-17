package fr.eriniumgroup.eriniumadventure.base.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.GuiGraphics;

import java.util.HashMap;

import fr.eriniumgroup.eriniumadventure.base.world.inventory.StatGui0Menu;
import fr.eriniumgroup.eriniumadventure.base.procedures.HeartReturnYesProcedure;
import fr.eriniumgroup.eriniumadventure.base.procedures.HeartReturnNoProcedure;
import fr.eriniumgroup.eriniumadventure.base.procedures.FlammeReturnYesProcedure;
import fr.eriniumgroup.eriniumadventure.base.procedures.FlameReturnNoProcedure;
import fr.eriniumgroup.eriniumadventure.base.network.StatGui0ButtonMessage;
import fr.eriniumgroup.eriniumadventure.base.EriniumAdventureMod;

import com.mojang.blaze3d.systems.RenderSystem;

public class StatGui0Screen extends AbstractContainerScreen<StatGui0Menu> {
	private final static HashMap<String, Object> guistate = StatGui0Menu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	ImageButton imagebutton_buy_green_x;
	ImageButton imagebutton_buy_green_x1;

	public StatGui0Screen(StatGui0Menu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 427;
		this.imageHeight = 240;
	}

	private static final ResourceLocation texture = new ResourceLocation("erinium_adventure:textures/screens/stat_gui_0.png");

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
		if (FlameReturnNoProcedure.execute(world, entity)) {
			guiGraphics.blit(new ResourceLocation("erinium_adventure:textures/screens/buy_red_x.png"), this.leftPos + 27, this.topPos + 27, 0, 0, 18, 18, 18, 18);
		}
		if (HeartReturnNoProcedure.execute(world, entity)) {
			guiGraphics.blit(new ResourceLocation("erinium_adventure:textures/screens/buy_red_x.png"), this.leftPos + 27, this.topPos + 54, 0, 0, 18, 18, 18, 18);
		}
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
		guiGraphics.drawString(this.font, Component.translatable("gui.erinium_adventure.stat_gui_0.label_stats"), 189, 0, -16777216, false);
	}

	@Override
	public void onClose() {
		super.onClose();
	}

	@Override
	public void init() {
		super.init();
		imagebutton_buy_green_x = new ImageButton(this.leftPos + 27, this.topPos + 27, 18, 18, 0, 0, 18, new ResourceLocation("erinium_adventure:textures/screens/atlas/imagebutton_buy_green_x.png"), 18, 36, e -> {
			if (FlammeReturnYesProcedure.execute(world, entity)) {
				EriniumAdventureMod.PACKET_HANDLER.sendToServer(new StatGui0ButtonMessage(0, x, y, z));
				StatGui0ButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (FlammeReturnYesProcedure.execute(world, entity))
					super.render(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_buy_green_x", imagebutton_buy_green_x);
		this.addRenderableWidget(imagebutton_buy_green_x);
		imagebutton_buy_green_x1 = new ImageButton(this.leftPos + 27, this.topPos + 54, 18, 18, 0, 0, 18, new ResourceLocation("erinium_adventure:textures/screens/atlas/imagebutton_buy_green_x1.png"), 18, 36, e -> {
			if (HeartReturnYesProcedure.execute(world, entity)) {
				EriniumAdventureMod.PACKET_HANDLER.sendToServer(new StatGui0ButtonMessage(1, x, y, z));
				StatGui0ButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}) {
			@Override
			public void render(GuiGraphics guiGraphics, int gx, int gy, float ticks) {
				if (HeartReturnYesProcedure.execute(world, entity))
					super.render(guiGraphics, gx, gy, ticks);
			}
		};
		guistate.put("button:imagebutton_buy_green_x1", imagebutton_buy_green_x1);
		this.addRenderableWidget(imagebutton_buy_green_x1);
	}
}
