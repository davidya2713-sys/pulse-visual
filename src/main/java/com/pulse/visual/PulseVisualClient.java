package com.pulse.visual;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;

import java.util.ArrayList;
import java.util.List;

public class PulseVisualClient implements ClientModInitializer {
    public static final String MOD_ID = "pulsevisual";

    public static final List<HudModule> MODULES = new ArrayList<>();

    public static HudModule WATERMARK;
    public static HudModule FPS;
    public static HudModule COORDS;
    public static HudModule KEYSTROKES;
    public static HudModule MODULE_LIST;

    private static KeyMapping.Category category;
    private static KeyMapping hudKey;

    @Override
    public void onInitializeClient() {
        // Перевод категории: key.category.pulsevisual.main
        category = KeyMapping.Category.register(
                Identifier.fromNamespaceAndPath(MOD_ID, "main"));

        hudKey = bind("key.pulsevisual.hud");

        WATERMARK   = register("Watermark",   "key.pulsevisual.watermark");
        FPS         = register("FPS Counter", "key.pulsevisual.fps");
        COORDS      = register("Coords",      "key.pulsevisual.coords");
        KEYSTROKES  = register("Keystrokes",  "key.pulsevisual.keystrokes");
        MODULE_LIST = register("Module List", "key.pulsevisual.modulelist");

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (hudKey.consumeClick()) {
                VisualConfig.hudEnabled = !VisualConfig.hudEnabled;
            }
            for (HudModule m : MODULES) {
                while (m.keyMapping.consumeClick()) {
                    m.enabled = !m.enabled;
                }
            }
        });

        // Рисуем поверх всех ванильных слоёв HUD
        HudElementRegistry.addLast(
                Identifier.fromNamespaceAndPath(MOD_ID, "overlay"),
                HudRenderer::render);
    }

    private static HudModule register(String name, String translationKey) {
        HudModule m = new HudModule(name, bind(translationKey));
        MODULES.add(m);
        return m;
    }

    /** Клавиша по умолчанию не назначена, игрок назначает в Настройки -> Управление. */
    private static KeyMapping bind(String translationKey) {
        return KeyBindingHelper.registerKeyBinding(new KeyMapping(
                translationKey, InputConstants.Type.KEYSYM, InputConstants.UNKNOWN.getValue(), category));
    }
}
