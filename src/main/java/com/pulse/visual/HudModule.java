package com.pulse.visual;

import net.minecraft.client.KeyMapping;

/** Один модуль HUD с клавишей включения. */
public final class HudModule {
    public final String name;
    public boolean enabled = true;
    public final KeyMapping keyMapping;

    public HudModule(String name, KeyMapping keyMapping) {
        this.name = name;
        this.keyMapping = keyMapping;
    }
}
