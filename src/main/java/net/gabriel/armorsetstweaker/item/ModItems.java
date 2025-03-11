package net.gabriel.armorsetstweaker.item;

import net.gabriel.armorsetstweaker.ArmorSets;
import net.gabriel.armorsetstweaker.item.custom.ModToolTiers;
import net.gabriel.armorsetstweaker.item.custom.NetheriteDetector;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ArmorSets.MODID);

    public static final RegistryObject<Item> ANOPALO = ITEMS.register("anopalo", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ANOSTEEl = ITEMS.register("anosteel", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ANOSWORD = ITEMS.register("anosword", () -> new SwordItem(ModToolTiers.ANOSTEEL, 4, 0, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> NETHERITE_DETECTOR = ITEMS.register("netherite_detector", () -> new NetheriteDetector(new Item.Properties().durability(5)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
