package com.teamaurora.better_badlands.core;

import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;
import com.teamaurora.better_badlands.core.other.BetterBadlandsData;
import com.teamaurora.better_badlands.core.other.BetterBadlandsEvents;
import com.teamaurora.better_badlands.core.other.BetterBadlandsRender;
import com.teamaurora.better_badlands.core.registry.BetterBadlandsFeatures;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.teamaurora.better_badlands.core.BetterBadlands.MODID;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MODID)
@Mod.EventBusSubscriber(modid = MODID)
@SuppressWarnings("deprecation")
public class BetterBadlands
{
    public static final String MODID = "better_badlands";
    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MODID);

    public BetterBadlands() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        REGISTRY_HELPER.getDeferredBlockRegister().register(eventBus);
        REGISTRY_HELPER.getDeferredItemRegister().register(eventBus);
        REGISTRY_HELPER.getDeferredEntityRegister().register(eventBus);

        MinecraftForge.EVENT_BUS.register(this);

        eventBus.addListener(this::commonSetup);
        DistExecutor.runWhenOn(Dist.CLIENT, ()->()-> {
            eventBus.addListener(this::clientSetup);
        });
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(new BetterBadlandsEvents());
        DeferredWorkQueue.runLater(() -> {
            BetterBadlandsData.registerFlammables();
            BetterBadlandsFeatures.generateFeatures();
        });
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        DeferredWorkQueue.runLater(() -> {
            BetterBadlandsRender.setupRenderLayer();
        });
    }
}
