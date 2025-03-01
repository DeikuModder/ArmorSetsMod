package net.gabriel.armorsetstweaker.registry;

import net.gabriel.armorsetstweaker.ArmorSets;
import net.gabriel.armorsetstweaker.menu.ArmorSetsMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    // Crear el DeferredRegister para los menús
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, ArmorSets.MODID);

    // Registrar el menú para los sets de armadura
    public static final RegistryObject<MenuType<ArmorSetsMenu>> ARMOR_SETS_MENU =
            MENUS.register("armor_sets_menu",
                    () -> IForgeMenuType.create(ArmorSetsMenu::new));

    // Método para registrar los menús en el evento de Forge
    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
