package net.gabriel.armorsetstweaker.menu;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class ArmorSetsContainer extends AbstractContainerMenu {
    private final Container[] armorSets = new Container[4]; // 4 sets of armor

    public ArmorSetsContainer(int containerId) {
        super(null, containerId);

        // Initialize 4 armor sets, each with 4 slots
        for (int i = 0; i < 4; i++) {
            armorSets[i] = new SimpleContainer(4); // Each set has 4 slots

            // Add slots for each armor piece (helmet, chestplate, leggings, boots)
            for (int j = 0; j < 4; j++) {
                this.addSlot(new Slot(armorSets[i], j, 0, 0)); // X and Y positions will be set later
            }
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        // Handle shift-clicking items between slots
        Slot slot = this.slots.get(index);
        if (slot == null || !slot.hasItem()) {
            return ItemStack.EMPTY; // No item to transfer
        }

        ItemStack stack = slot.getItem().copy();
        if (index < 16) { // Slots in the custom container (4 sets * 4 slots = 16 slots)
            // Try to move the item to the player's inventory
            if (!this.moveItemStackTo(stack, 16, this.slots.size(), true)) {
                return ItemStack.EMPTY; // Failed to move the item
            }
        } else {
            // Try to move the item to the custom container
            if (!this.moveItemStackTo(stack, 0, 16, false)) {
                return ItemStack.EMPTY; // Failed to move the item
            }
        }

        if (stack.isEmpty()) {
            slot.set(ItemStack.EMPTY); // Clear the slot if the stack is empty
        } else {
            slot.setChanged(); // Mark the slot as changed
        }

        return stack;
    }

    @Override
    public boolean stillValid(Player player) {
        return true; // Always valid
    }

    public Container getArmorSet(int index) {
        return armorSets[index];
    }
}