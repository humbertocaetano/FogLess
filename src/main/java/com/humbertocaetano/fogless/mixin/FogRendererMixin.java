package com.humbertocaetano.fogless.mixin;

import com.humbertocaetano.fogless.FogLessMain;
import com.humbertocaetano.fogless.util.FogLessHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Usa o nome obfuscado intermediary que sempre funciona
@Mixin(targets = "net.minecraft.class_898") // FogRenderer no intermediary
public class FogRendererMixin {

    @Inject(method = "method_3214", at = @At("TAIL")) // setupFog no intermediary
    private static void modifyFog(CallbackInfo ci) {
//        if (FogLessMain.config == null || !FogLessMain.config.enableMod) return;
        if (!FogLessHandler.isModEnabled()) return;

        try {
            // Usa reflection para chamar RenderSystem de forma segura
            Class<?> renderSystemClass = Class.forName("com.mojang.blaze3d.systems.RenderSystem");

            // Obtém os valores atuais de fog
            float currentStart = (Float) renderSystemClass.getMethod("getShaderFogStart").invoke(null);
            float currentEnd = (Float) renderSystemClass.getMethod("getShaderFogEnd").invoke(null);

            // Se o fog está ativo, modifica os valores
            if (currentEnd > 0 && FogLessMain.config.removeTerrainFog) {
                float startMultiplier = FogLessMain.config.terrainFogStart / 100.0f;
                float endMultiplier = FogLessMain.config.terrainFogEnd / 100.0f;

                // Aumenta drasticamente as distâncias do fog
                float newStart = Math.max(currentStart * startMultiplier * 5.0f, currentEnd * 0.9f);
                float newEnd = currentEnd * endMultiplier * 3.0f;

                // Aplica os novos valores
                renderSystemClass.getMethod("setShaderFogStart", float.class).invoke(null, newStart);
                renderSystemClass.getMethod("setShaderFogEnd", float.class).invoke(null, newEnd);

                // Log ocasional para verificar funcionamento
                if (Math.random() < 0.001) {
                    FogLessMain.LOGGER.info("🌫️ FogLess: Fog {} -> {}, End {} -> {}",
                            currentStart, newStart, currentEnd, newEnd);
                }
            }

        } catch (Exception e) {
            // Falha silenciosa para evitar crashes, log apenas ocasionalmente
            if (Math.random() < 0.01) {
                FogLessMain.LOGGER.debug("FogLess reflection attempt failed: {}", e.getMessage());
            }
        }
    }
}