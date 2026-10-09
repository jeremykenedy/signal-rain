package com.jeremykenedy.signalrain;

import java.util.Random;

public final class SignalRainOptions {
  public final int count;
  public final float speed;
  public final int palette;
  public final float brightness;
  public final int glyphs;

  private SignalRainOptions(int count, float speed, int palette, float brightness, int glyphs) {
    this.count = count;
    this.speed = speed;
    this.palette = palette;
    this.brightness = brightness;
    this.glyphs = glyphs;
  }

  public static SignalRainOptions resolve(
      String density,
      String motion,
      String palette,
      String brightness,
      String glyphSetting,
      boolean randomizeAll,
      Random random) {
    String selectedDensity =
        choose(density, randomizeAll, random, "balanced", "sparse", "dense", "packed");
    String selectedMotion = choose(motion, randomizeAll, random, "normal", "slow", "fast");
    String selectedPalette = choose(palette, randomizeAll, random, "green", "blue", "amber");
    String selectedBrightness =
        choose(brightness, randomizeAll, random, "standard", "dim", "bright");
    String selectedGlyphs =
        choose(glyphSetting, randomizeAll, random, "fragments", "bars", "circuits");
    return new SignalRainOptions(
        countFor(selectedDensity),
        speedFor(selectedMotion),
        paletteFor(selectedPalette),
        brightnessFor(selectedBrightness),
        glyphStyle(selectedGlyphs));
  }

  static String choose(String selected, boolean randomizeAll, Random random, String... values) {
    if (randomizeAll || "random".equals(selected)) return values[random.nextInt(values.length)];
    for (String value : values) if (value.equals(selected)) return selected;
    return values[0];
  }

  static int countFor(String density) {
    if ("sparse".equals(density)) return 32;
    if ("dense".equals(density)) return 96;
    if ("packed".equals(density)) return 140;
    return 64;
  }

  static float speedFor(String motion) {
    if ("slow".equals(motion)) return 0.55f;
    if ("fast".equals(motion)) return 1.7f;
    return 1f;
  }

  static int paletteFor(String palette) {
    if ("blue".equals(palette)) return 1;
    if ("amber".equals(palette)) return 2;
    return 0;
  }

  static float brightnessFor(String brightness) {
    if ("dim".equals(brightness)) return 0.48f;
    if ("bright".equals(brightness)) return 1.35f;
    return 0.9f;
  }

  static int glyphStyle(String setting) {
    if ("fragments".equals(setting)) return 2;
    if ("circuits".equals(setting)) return 5;
    return 0;
  }
}
