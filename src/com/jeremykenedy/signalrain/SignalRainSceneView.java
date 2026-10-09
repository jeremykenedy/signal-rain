package com.jeremykenedy.signalrain;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.view.View;
import java.util.Random;

final class SignalRainSceneView extends View {
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final SignalRainOptions options;
    private final Stream[] streams;
    private long startedAt;
    private boolean running;
    private final Runnable invalidator = new Runnable() {
        @Override public void run() { if (running) invalidate(); }
    };

    SignalRainSceneView(Context context) {
        super(context);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        options = SignalRainOptions.resolve(preferences.getString("density", "balanced"),
                preferences.getString("motion", "normal"), preferences.getString("palette", "green"),
                preferences.getString("brightness", "standard"), preferences.getString("glyphs", "fragments"),
                preferences.getBoolean("randomize_all", false), new Random());
        Random random = new Random(92814);
        streams = new Stream[options.count];
        for (int i = 0; i < streams.length; i++) {
            streams[i] = new Stream((i + random.nextFloat() * .6f) / streams.length,
                    random.nextFloat() * 2f, .3f + random.nextFloat() * .7f,
                    8 + random.nextInt(22), random.nextInt(4096));
        }
    }

    void start() {
        if (!running) { running = true; startedAt = SystemClock.uptimeMillis(); postInvalidateOnAnimation(); }
    }

    void stop() { running = false; removeCallbacks(invalidator); }

    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(0xff020606);
        float width = getWidth(), height = getHeight();
        if (width <= 0 || height <= 0) return;
        float time = (SystemClock.uptimeMillis() - startedAt) / 1000f;
        float cell = height / 48f;
        int color = options.palette == 1 ? 0xff30d8ff : options.palette == 2 ? 0xffffb850 : 0xff25ed77;
        paint.setStrokeCap(Paint.Cap.SQUARE);
        for (Stream stream : streams) {
            float head = (stream.offset * height + time * options.speed * (45f + stream.depth * 80f))
                    % (height + cell * stream.length);
            float x = stream.x * width;
            for (int row = 0; row < stream.length; row++) {
                float y = head - row * cell;
                if (y < -cell || y > height + cell) continue;
                float fade = 1f - (float) row / stream.length;
                float size = cell * (.32f + stream.depth * .12f);
                int alpha = Math.min(255, (int) (fade * fade * options.brightness * 210));
                paint.setColor(row == 0 ? 0xffe4fff2 : color);
                paint.setAlpha(alpha / 8);
                canvas.drawCircle(x, y, size * 1.5f, paint);
                paint.setAlpha(alpha);
                paint.setStrokeWidth(Math.max(1f, cell * .07f));
                int pattern = (stream.seed + row * 131 + (int) (time * 3)) & 15;
                if (options.glyphs == 0) {
                    canvas.drawLine(x - size * .5f, y, x + size * .5f, y, paint);
                } else {
                    if ((pattern & 1) != 0) canvas.drawLine(x - size, y - size, x - size, y + size, paint);
                    if ((pattern & 2) != 0) canvas.drawLine(x - size, y, x + size, y, paint);
                    if ((pattern & 4) != 0) canvas.drawLine(x, y - size, x + size, y - size, paint);
                    if ((pattern & 8) != 0) canvas.drawLine(x + size, y, x + size, y + size, paint);
                    if (options.glyphs == 5) canvas.drawCircle(x, y + size, size * .25f, paint);
                }
            }
        }
        paint.setAlpha(255);
        if (running) postDelayed(invalidator, 33L);
    }

    private static final class Stream {
        final float x, offset, depth;
        final int length, seed;
        Stream(float x, float offset, float depth, int length, int seed) {
            this.x = x; this.offset = offset; this.depth = depth; this.length = length; this.seed = seed;
        }
    }
}
