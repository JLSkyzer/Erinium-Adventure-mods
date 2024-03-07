package fr.eriniumgroups.erinium.auctionhouse.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.GuiGraphics;

import java.util.HashMap;

import fr.eriniumgroups.erinium.auctionhouse.world.inventory.AhMainMenuMenu;
import fr.eriniumgroups.erinium.auctionhouse.network.AhMainMenuButtonMessage;
import fr.eriniumgroups.erinium.auctionhouse.EriniumAhMod;

import com.mojang.blaze3d.systems.RenderSystem;

public class AhMainMenuScreen extends AbstractContainerScreen<AhMainMenuMenu> {
	private final static HashMap<String, Object> guistate = AhMainMenuMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox search;
	ImageButton imagebutton_search_btn;

	public AhMainMenuScreen(AhMainMenuMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 180;
	}

	private static final ResourceLocation texture = new ResourceLocation("erinium_ah:textures/screens/ah_main_menu.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		search.render(guiGraphics, mouseX, mouseY, partialTicks);
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
		if (search.isFocused())
			return search.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		search.tick();
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
	}

	@Override
	public void onClose() {
		super.onClose();
	}

	@Override
	public void init() {
		super.init();
		search = new EditBox(this.font, this.leftPos + 23, this.topPos + -29, 108, 18, Component.translatable("gui.erinium_ah.ah_main_menu.search"));
		search.setMaxLength(32767);
		guistate.put("text:search", search);
		this.addWidget(this.search);
		imagebutton_search_btn = new ImageButton(this.leftPos + 132, this.topPos + -30, 20, 20, 0, 0, 20, new ResourceLocation("erinium_ah:textures/screens/atlas/imagebutton_search_btn.png"), 20, 40, e -> {
			if (true) {
				EriniumAhMod.PACKET_HANDLER.sendToServer(new AhMainMenuButtonMessage(0, x, y, z));
				AhMainMenuButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		});
		guistate.put("button:imagebutton_search_btn", imagebutton_search_btn);
		this.addRenderableWidget(imagebutton_search_btn);
	}
}
