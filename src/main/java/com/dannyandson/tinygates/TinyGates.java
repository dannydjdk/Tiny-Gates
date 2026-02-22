package com.dannyandson.tinygates;

import com.dannyandson.tinygates.setup.ClientSetup;
import com.dannyandson.tinygates.setup.Registration;
import com.dannyandson.tinygates.setup.RegistrationTinyRedstone;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(TinyGates.MODID)
public class TinyGates {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "tinygates";

    public TinyGates(IEventBus modEventBus) {

        Registration.register(modEventBus);
        if (ModList.get().isLoaded("tinyredstone"))
            RegistrationTinyRedstone.register();

        if (FMLEnvironment.dist.isClient()) {
            modEventBus.addListener(ClientSetup::init);
        }

        modEventBus.addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
        if (ModList.get().isLoaded("tinyredstone"))
            RegistrationTinyRedstone.registerPanelCells();
    }
}
