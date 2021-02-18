package com.stal111.valhelsia_structures.setup;

import com.stal111.valhelsia_structures.init.other.CompostableRegistry;
import com.stal111.valhelsia_structures.init.other.FlammableRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * Common Setup
 * Valhelsia Structures - com.stal111.valhelsia_structures.setup.CommonSetup
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-02-14
 */
public class CommonSetup {

    public static void setup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            FlammableRegistry.register();
            CompostableRegistry.register();
        });
    }
}
