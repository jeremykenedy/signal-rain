package com.jeremykenedy.signalrain;

import java.util.Random;

public final class SignalRainOptionsTest {
    public static void main(String[] args) {
        verifyDensityAndSpeed();
        verifyPaletteBrightnessAndGlyphs();
        verifyExplicitAndRandomSelection();
        verifySettingsValidation();
        System.out.println("SignalRain settings tests passed.");
    }

    private static void verifyDensityAndSpeed() {
        check(SignalRainOptions.countFor("sparse") == 32, "sparse density");
        check(SignalRainOptions.countFor("balanced") == 64, "balanced density");
        check(SignalRainOptions.countFor("unknown") == 64, "density fallback");
        check(SignalRainOptions.countFor("dense") == 96, "dense density");
        check(SignalRainOptions.countFor("packed") == 140, "packed density");
        check(SignalRainOptions.speedFor("slow") == 0.55f, "slow speed");
        check(SignalRainOptions.speedFor("normal") == 1f, "normal speed");
        check(SignalRainOptions.speedFor("unknown") == 1f, "speed fallback");
        check(SignalRainOptions.speedFor("fast") == 1.7f, "fast speed");
    }

    private static void verifyPaletteBrightnessAndGlyphs() {
        check(SignalRainOptions.paletteFor("green") == 0, "green palette");
        check(SignalRainOptions.paletteFor("blue") == 1, "blue palette");
        check(SignalRainOptions.paletteFor("amber") == 2, "amber palette");
        check(SignalRainOptions.paletteFor("unknown") == 0, "palette fallback");
        check(SignalRainOptions.brightnessFor("dim") == 0.48f, "dim brightness");
        check(SignalRainOptions.brightnessFor("standard") == 0.9f, "standard brightness");
        check(SignalRainOptions.brightnessFor("unknown") == 0.9f, "brightness fallback");
        check(SignalRainOptions.brightnessFor("bright") == 1.35f, "bright brightness");
        check(SignalRainOptions.glyphStyle("bars") == 0, "glyphs off");
        check(SignalRainOptions.glyphStyle("unknown") == 0, "meteor fallback");
        check(SignalRainOptions.glyphStyle("fragments") == 2, "fragments glyphs");
        check(SignalRainOptions.glyphStyle("circuits") == 5, "circuits glyphs");
    }

    private static void verifyExplicitAndRandomSelection() {
        SignalRainOptions explicit = SignalRainOptions.resolve(
                "packed", "fast", "amber", "dim", "circuits", false, new Random(1));
        check(explicit.count == 140 && explicit.speed == 1.7f && explicit.palette == 2
                && explicit.brightness == 0.48f && explicit.glyphs == 5, "explicit settings");
        SignalRainOptions fallback = SignalRainOptions.resolve(
                "invalid", "invalid", "invalid", "invalid", "invalid", false, new Random(1));
        check(fallback.count == 64 && fallback.speed == 1f && fallback.palette == 0
                && fallback.brightness == 0.9f && fallback.glyphs == 2, "safe fallback settings");
        SignalRainOptions random = SignalRainOptions.resolve(
                "random", "random", "random", "random", "random", false, new FixedRandom(2));
        check(random.count == 96 && random.speed == 1.7f && random.palette == 2
                && random.brightness == 1.35f && random.glyphs == 5, "per-setting random choices");
        SignalRainOptions all = SignalRainOptions.resolve(
                "sparse", "slow", "green", "dim", "bars", true, new FixedRandom(1));
        check(all.count == 32 && all.speed == 0.55f && all.palette == 1
                && all.brightness == 0.48f && all.glyphs == 0, "randomize all selections");
    }

    private static void verifySettingsValidation() {
        check(!SettingsValues.isSupported(null, "dark"), "null key rejected");
        check(!SettingsValues.isSupported("density", null), "null value rejected");
        for (String value : new String[] {"sparse", "balanced", "dense", "packed", "random"})
            check(SettingsValues.isSupported("density", value), "density accepted: " + value);
        for (String value : new String[] {"slow", "normal", "fast", "random"})
            check(SettingsValues.isSupported("motion", value), "motion accepted: " + value);
        for (String value : new String[] {"green", "blue", "amber", "random"})
            check(SettingsValues.isSupported("palette", value), "palette accepted: " + value);
        for (String value : new String[] {"dim", "standard", "bright", "random"})
            check(SettingsValues.isSupported("brightness", value), "brightness accepted: " + value);
        for (String value : new String[] {"bars", "fragments", "circuits", "random"})
            check(SettingsValues.isSupported("glyphs", value), "meteor setting accepted: " + value);
        check(SettingsValues.isSupported("randomize_all", "true"), "randomize on accepted");
        check(SettingsValues.isSupported("randomize_all", "false"), "randomize off accepted");
        check(!SettingsValues.isSupported("density", "impossible"), "invalid density rejected");
        check(!SettingsValues.isSupported("motion", "instant"), "invalid motion rejected");
        check(!SettingsValues.isSupported("palette", "violet"), "invalid palette rejected");
        check(!SettingsValues.isSupported("brightness", "blinding"), "invalid brightness rejected");
        check(!SettingsValues.isSupported("glyphs", "all"), "invalid meteor setting rejected");
        check(!SettingsValues.isSupported("randomize_all", "yes"), "invalid boolean rejected");
        check(!SettingsValues.isSupported("unknown", "value"), "unknown setting rejected");
    }

    private static void check(boolean result, String message) {
        if (!result) throw new AssertionError(message);
    }

    private static final class FixedRandom extends Random {
        private final int value;

        FixedRandom(int value) {
            this.value = value;
        }

        @Override
        public int nextInt(int bound) {
            return Math.min(value, bound - 1);
        }
    }
}
