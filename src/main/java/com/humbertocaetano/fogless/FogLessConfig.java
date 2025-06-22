package com.humbertocaetano.fogless;

public class FogLessConfig {

    // Configurações gerais
    public boolean enableMod = true;

    // Configurações de terreno - valores otimizados para máxima visibilidade
    public boolean removeTerrainFog = true;
    public int terrainFogStart = 100;  // Aumentado para quase eliminar o fog
    public int terrainFogEnd = 100;   // 100% = sem fog

    // Configurações atmosféricas - remove o fog cinza feio
    public boolean removeAtmosphericFog = true;
    public int atmosphericFogStart = 100;  // Muito alto para eliminar fog
    public int atmosphericFogEnd = 100;   // Sem fog atmosférico

    // Configurações de água - melhora visibilidade subaquática
    public boolean improveWaterFog = true;
    public int waterFogStart = 20;    // Permite ver mais longe na água
    public int waterFogEnd = 85;      // Boa visibilidade

    // Configurações do Nether - mantém atmosfera mas melhora visibilidade
    public boolean improveNetherFog = true;
    public int netherFogStart = 30;   // Fog mais distante
    public int netherFogEnd = 90;     // Boa visibilidade

    // Configurações de chuva - reduz o fog durante tempestades
    public boolean improveRainFog = true;
    public int rainFogStart = 50;     // Fog mais distante na chuva
    public int rainFogEnd = 85;       // Boa visibilidade mesmo na chuva

    public FogLessConfig() {
        // Configurações padrão já definidas acima
    }
}