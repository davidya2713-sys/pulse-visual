package com.pulse.visual;

/** Настройки визуала (аналог struct VisualConfig из C++). */
public final class VisualConfig {
    public static boolean hudEnabled = true;

    public static int x = 4;
    public static int y = 4;

    public static final int ACCENT = 0xFF4DB3FF; // голубой
    public static final int GREEN  = 0xFF80FF80;
    public static final int YELLOW = 0xFFFFFF66;
    public static final int RED    = 0xFFFF4D4D;
    public static final int WHITE  = 0xFFFFFFFF;

    private VisualConfig() {}
}
