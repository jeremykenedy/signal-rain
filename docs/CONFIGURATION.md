# Configuration

Open Signal Rain from the TV launcher and use the remote to set preferences. Choices persist locally and are applied at the next screensaver start.

| Setting | Choices | Default |
| --- | --- | --- |
| Stream density | Sparse (32 streams), balanced (64), dense (96), packed (140), random | Balanced |
| Fall speed | Slow, normal, fast, random | Normal |
| Stream colors | Green, cool blue, warm amber, random | Green |
| Glow strength | Dim, standard, bright, random | Standard |
| Glyph style | Bars, fragments, circuits, random | Fragments |
| Randomize all settings each start | Off, on | Off |

Per-setting random choices are selected once when each dream starts. Randomize all selects a value for each visual setting at the same time.

Glow strength changes the luminous marks. It does not alter the television's display brightness.

## Host application settings contract

The settings provider authority is `com.jeremykenedy.signalrain.settings`:

- `content://com.jeremykenedy.signalrain.settings/schema` returns setting keys, titles, types, defaults, choices, and random support.
- `content://com.jeremykenedy.signalrain.settings/settings` returns current key/value pairs.

Update one supported choice through `ContentResolver.update()` on the `settings` URI with `ContentValues` named `key` and `value`. Unsupported keys and values throw `IllegalArgumentException`. Boolean values are the strings `true` or `false`. Query the schema rather than assuming new options exist.

The provider exposes visual preferences only. It does not expose device or account data and it does not make network requests.
