package net.gabriel.armorsetstweaker.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.gabriel.armorsetstweaker.menu.ArmorSetsMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ArmorSetsScreen extends AbstractContainerScreen<ArmorSetsMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("armorsetstweaker:textures/gui/armor_sets_gui.png");

    public ArmorSetsScreen(ArmorSetsMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 176;
        this.imageHeight = 200; // Ajuste de altura para más slots
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        guiGraphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);

        // Dibujar contornos de los slots para hacerlos visibles
        int startX = this.leftPos + 80;
        int startY = this.topPos + 18;
        int slotSize = 18;
        int setCount = 3;
        int armorSlotCount = 4;
        int itemSlotCount = 4;

        for (int set = 0; set < setCount; set++) {
            int yOffset = startY + (set * (armorSlotCount + itemSlotCount) * slotSize);

            // Dibujar slots de armadura
            for (int i = 0; i < armorSlotCount; i++) {
                guiGraphics.fill(startX - 1, yOffset + (i * slotSize) - 1, startX + slotSize, yOffset + (i * slotSize) + slotSize, 0x80FFFFFF);
            }

            // Dibujar slots de ítems
            for (int i = 0; i < itemSlotCount; i++) {
                guiGraphics.fill(startX + 20 - 1, yOffset + (i * slotSize) - 1, startX + 20 + slotSize, yOffset + (i * slotSize) + slotSize, 0x80FFFFFF);
            }
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
