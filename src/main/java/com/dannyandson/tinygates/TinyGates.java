package com.dannyandson.tinygates;

import com.dannyandson.tinygates.setup.ClientSetup;
import com.dannyandson.tinygates.setup.Registration;
import com.dannyandson.tinygates.network.ModNetworkHandler;
import com.dannyandson.tinygates.setup.ModSetup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TinyGates.MODID)
public class TinyGates
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "tinygates";

    public TinyGates() {

        Registration.register();

        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModSetup::init);

        if(FMLEnvironment.dist.isClient()) {
            FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::init);
        }

        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // register everything
        Registration.registerPanelCells();
        ModNetworkHandler.registerMessages();
    }

}
