package com.humbertocaetano.fogless;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.humbertocaetano.fogless.command.FogLessCommand;

public class FogLessMain implements ClientModInitializer {
    public static final String MOD_ID = "fogless";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static FogLessConfig config;

    @Override
    public void onInitializeClient() {

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->
                FogLessCommand.register(dispatcher));

        LOGGER.info("🌟 Initializing FogLess mod - Making Minecraft beautiful again!");

        // Carrega configuração padrão otimizada
        config = new FogLessConfig();

        // Registra o key handler para a tecla J
//        FogLessKeyHandler.register();

        LOGGER.info("✅ FogLess loaded successfully!");
        LOGGER.info("📋 Configuration:");
        LOGGER.info("   - Terrain Fog: {} (Start: {}%, End: {}%)",
                config.removeTerrainFog ? "DISABLED" : "enabled",
                config.terrainFogStart, config.terrainFogEnd);
        LOGGER.info("   - Atmospheric Fog: {} (Start: {}%, End: {}%)",
                config.removeAtmosphericFog ? "DISABLED" : "enabled",
                config.atmosphericFogStart, config.atmosphericFogEnd);
        LOGGER.info("   - Water Fog: {} (Start: {}%, End: {}%)",
                config.improveWaterFog ? "IMPROVED" : "default",
                config.waterFogStart, config.waterFogEnd);
        LOGGER.info("   - Nether Fog: {} (Start: {}%, End: {}%)",
                config.improveNetherFog ? "IMPROVED" : "default",
                config.netherFogStart, config.netherFogEnd);
        LOGGER.info("   - Rain Fog: {} (Start: {}%, End: {}%)",
                config.improveRainFog ? "IMPROVED" : "default",
                config.rainFogStart, config.rainFogEnd);

        LOGGER.info("🎮 FogLess is ready! Press 'J' to toggle on/off");
        LOGGER.info("🎮 Enjoy your beautiful, fog-free Minecraft experience!");
    }
}