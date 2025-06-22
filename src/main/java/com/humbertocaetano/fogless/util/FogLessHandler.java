package com.humbertocaetano.fogless.util;

import com.humbertocaetano.fogless.FogLessMain;

public class FogLessHandler {
    private static boolean modEnabled = true;

    public static boolean isModEnabled() {
        return modEnabled;
    }

    public static void setModEnabled(boolean enabled) {
        if (modEnabled != enabled) {
            modEnabled = enabled;

            // Log no console
            FogLessMain.LOGGER.info("🎛️ FogLess toggled via command: {}",
                    enabled ? "ENABLED" : "DISABLED");

            // Log adicional para debug
            if (enabled) {
                FogLessMain.LOGGER.info("🌫️ Fog removal is now ACTIVE");
            } else {
                FogLessMain.LOGGER.info("🌫️ Fog removal is now INACTIVE - vanilla fog restored");
            }
        }
    }

    public static void toggle() {
        setModEnabled(!modEnabled);
    }

    public static String getStatusString() {
        return modEnabled ? "ENABLED" : "DISABLED";
    }
}