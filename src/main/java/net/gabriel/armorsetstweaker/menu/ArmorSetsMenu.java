package net.gabriel.armorsetstweaker.menu;

import net.gabriel.armorsetstweaker.registry.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class ArmorSetsMenu extends AbstractContainerMenu {
    public static final int ARMOR_SLOT_COUNT = 4;
    public static final int ITEM_SLOT_COUNT = 4;
    public static final int SET_COUNT = 3;
    private final Container container;

    public ArmorSetsMenu(int id, Inventory playerInventory, FriendlyByteBuf extraData) {
        this(id, playerInventory, new SimpleContainer(SET_COUNT * (ARMOR_SLOT_COUNT + ITEM_SLOT_COUNT)));
    }

    public ArmorSetsMenu(int id, Inventory playerInventory, Container container) {
        super(ModMenuTypes.ARMOR_SETS_MENU.get(), id);
        this.container = container;

        int startX = 80;
        int startY = 18;
        int slotIndex = 0;

        // Crear 3 sets de equipamiento
        for (int set = 0; set < SET_COUNT; set++) {
            int yOffset = startY + (set * (ARMOR_SLOT_COUNT + ITEM_SLOT_COUNT) * 18);

            // Slots de armadura para cada set
            for (int i = 0; i < ARMOR_SLOT_COUNT; i++) {
                this.addSlot(new Slot(container, slotIndex++, startX, yOffset + (i * 18)));
            }

            // Slots de items para cada set
            for (int i = 0; i < ITEM_SLOT_COUNT; i++) {
                this.addSlot(new Slot(container, slotIndex++, startX + 20, yOffset + (i * 18)));
            }
        }

        // Agregar el inventario del jugador (3 filas de 9 slots)
        int inventoryStartY = startY + (SET_COUNT * (ARMOR_SLOT_COUNT + ITEM_SLOT_COUNT) * 18) + 10;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, startX - 40 + col * 18, inventoryStartY + row * 18));
            }
        }

        // Agregar la hotbar del jugador (1 fila de 9 slots)
        int hotbarStartY = inventoryStartY + 58;
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInventory, col, startX - 40 + col * 18, hotbarStartY));
        }
    }

    public void equipSet(Player player, int setIndex) {
        if (setIndex < 0 || setIndex >= SET_COUNT) return;

        int startSlot = setIndex * (ARMOR_SLOT_COUNT + ITEM_SLOT_COUNT);

        // Equipar armadura
        for (int i = 0; i < ARMOR_SLOT_COUNT; i++) {
            ItemStack stack = container.getItem(startSlot + i);
            if (!stack.isEmpty()) {
                player.setItemSlot(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, i), stack);
            }
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            slot.set(ItemStack.EMPTY);
            return stack;
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}
