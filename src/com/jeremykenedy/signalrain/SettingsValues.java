package com.jeremykenedy.signalrain;

public final class SettingsValues {
    private SettingsValues() {}

    public static boolean isSupported(String key, String value) {
        if (key == null || value == null) return false;
        if ("density".equals(key)) return oneOf(value, "sparse", "balanced", "dense", "packed", "random");
        if ("motion".equals(key)) return oneOf(value, "slow", "normal", "fast", "random");
        if ("palette".equals(key)) return oneOf(value, "green", "blue", "amber", "random");
        if ("brightness".equals(key)) return oneOf(value, "dim", "standard", "bright", "random");
        if ("glyphs".equals(key)) return oneOf(value, "bars", "fragments", "circuits", "random");
        if ("randomize_all".equals(key)) return oneOf(value, "true", "false");
        return false;
    }

    private static boolean oneOf(String value, String... allowed) {
        for (String option : allowed) if (option.equals(value)) return true;
        return false;
    }
}
