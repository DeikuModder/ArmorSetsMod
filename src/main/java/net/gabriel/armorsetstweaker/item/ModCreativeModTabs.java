package net.gabriel.armorsetstweaker.item;

import net.gabriel.armorsetstweaker.ArmorSets;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ArmorSets.MODID);

    public static final RegistryObject<CreativeModeTab> ANO_TAB = CREATIVE_MODE_TABS.register("ano_tab", () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.ANOSTEEl.get()))
            .title(Component.translatable("creativetab.ano_tab"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(ModItems.ANOPALO.get());
                output.accept(ModItems.ANOSTEEl.get());
                output.accept(ModItems.NETHERITE_DETECTOR.get());
                output.accept(ModItems.ANOSWORD.get());
            })
            .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
