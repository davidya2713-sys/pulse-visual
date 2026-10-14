package com.pulse.visual;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;

import java.util.Locale;

public final class HudRenderer {
    private HudRenderer() {}

    public static void render(GuiGraphics g, DeltaTracker delta) {
        Minecraft mc = Minecraft.getInstance();
        if (!VisualConfig.hudEnabled) return;
        if (mc.player == null || mc.options.hideGui) return;

        Font font = mc.font;
        int x = VisualConfig.x;
        int y = VisualConfig.y;
        int lineH = font.lineHeight + 2;

        if (PulseVisualClient.WATERMARK.enabled) {
            g.drawString(font, "Pulse Visual v1.0", x, y, VisualConfig.ACCENT, true);
            y += lineH;
        }

        if (PulseVisualClient.FPS.enabled) {
            g.drawString(font, "FPS: " + mc.getFps(), x, y, VisualConfig.GREEN, true);
            y += lineH;
        }

        if (PulseVisualClient.COORDS.enabled) {
            LocalPlayer p = mc.player;
            g.drawString(font, String.format(Locale.ROOT,
                    "XYZ: %.1f / %.1f / %.1f", p.getX(), p.getY(), p.getZ()),
                    x, y, VisualConfig.YELLOW, true);
            y += lineH;
            g.drawString(font, String.format(Locale.ROOT,
                    "Yaw: %.1f  Pitch: %.1f", p.getYRot(), p.getXRot()),
                    x, y, VisualConfig.YELLOW, true);
            y += lineH;
            int hp = (int) Math.ceil(p.getHealth());
            g.drawString(font, "HP: " + hp, x, y,
                    hp > 10 ? VisualConfig.GREEN : VisualConfig.RED, true);
            y += lineH;
        }

        if (PulseVisualClient.MODULE_LIST.enabled) {
            int my = 4;
            int right = g.guiWidth() - 4;
            for (HudModule m : PulseVisualClient.MODULES) {
                if (!m.enabled) continue;
                int w = font.width(m.name);
                g.drawString(font, m.name, right - w, my, VisualConfig.ACCENT, true);
                my += lineH;
            }
        }

        if (PulseVisualClient.KEYSTROKES.enabled) {
            drawKeystrokes(g, mc, font);
        }
    }

    private static void drawKeystrokes(GuiGraphics g, Minecraft mc, Font font) {
        Options o = mc.options;
        int box = 20;
        int gap = 2;
        int step = box + gap;
        int left = 8;
        int top = g.guiHeight() - (step * 3) - 8;

        // Ряд 1: W по центру
        key(g, font, left + step, top, box, "W", o.keyUp);
        // Ряд 2: A S D
        key(g, font, left,            top + step, box, "A", o.keyLeft);
        key(g, font, left + step,     top + step, box, "S", o.keyDown);
        key(g, font, left + step * 2, top + step, box, "D", o.keyRight);
        // Ряд 3: Space / Shift / Ctrl(sprint)
        key(g, font, left,            top + step * 2, box, "Spc", o.keyJump);
        key(g, font, left + step,     top + step * 2, box, "Sh",  o.keyShift);
        key(g, font, left + step * 2, top + step * 2, box, "Ctl", o.keySprint);
    }

    private static void key(GuiGraphics g, Font font,
                            int x, int y, int size, String label, KeyMapping km) {
        boolean down = km.isDown();
        int fill   = down ? 0x994DB3FF : 0x4D000000;
        int stroke = down ? VisualConfig.ACCENT : 0xCC999999;

        g.fill(x, y, x + size, y + size, fill);
        // Рамка из четырёх линий
        g.fill(x, y, x + size, y + 1, stroke);                 // верх
        g.fill(x, y + size - 1, x + size, y + size, stroke);   // низ
        g.fill(x, y, x + 1, y + size, stroke);                 // лево
        g.fill(x + size - 1, y, x + size, y + size, stroke);   // право

        int tx = x + (size - font.width(label)) / 2;
        int ty = y + (size - font.lineHeight) / 2 + 1;
        g.drawString(font, label, tx, ty, VisualConfig.WHITE, true);
    }
}
