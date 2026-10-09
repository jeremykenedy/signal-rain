# Device verification

Device checks distinguish app rendering from the vendor's screensaver manager. An emulator preview does not prove automatic idle activation, vendor settings behavior, native 4K composition, sustained thermal behavior, or performance on physical hardware.

| Platform | Device and OS | Result |
| --- | --- | --- |
| Android TV emulator | `sdk_google_atv64_arm64`, Android 12/API 31, 1920x1080 | App installed and launched. Settings activity, exported settings-provider schema/update, and animated full-screen renderer were checked. The emulator has no `dream` system service, so DreamService activation through system settings was not available. |
| Android TV | Physical device not available | Not verified. We are looking for an Android TV owner to test installation, selection, activation, settings, and reboot/sleep behavior and report model, OS/API, resolution and results in the [issue tracker](https://github.com/jeremykenedy/signal-rain/issues). |
| Fire TV | Physical device not tested for this release | Not verified. We are looking for a Fire TV owner to test installation, screensaver selection and activation, and remote settings, then report the device model, Fire OS/API, resolution, and results in the [issue tracker](https://github.com/jeremykenedy/signal-rain/issues). |
| Google TV | Physical device not tested for this release | Not verified. We are looking for a Google TV owner to run the same checks and report the device model, OS/API, resolution, and results in the [issue tracker](https://github.com/jeremykenedy/signal-rain/issues). |

The [Signal Rain preview](screenshots/signalrain-preview.png) and [settings screen](screenshots/signal-rain-settings-api31.png) were captured from the running app on the Android TV emulator. The screensaver's in-app animation preview uses the same renderer. The built APK declares the `android.service.dreams.DreamService` action and dream metadata, and the provider schema was queried and updated; this does not establish support in vendor screensaver menus.

The Android TV emulator exposes a 1920x1080 surface. Native 4K output has not been measured. Frame pacing, CPU/GPU use, memory, power draw, and sustained temperature have not been measured on a physical television. Do not infer device performance from the emulator.

Screenshots captured on emulator-5586 at 1920x1080 after more than 60 seconds of animation. Each source capture was opened and visually inspected before inclusion; the first capture showed another launcher overlay and was discarded. Green preview uses balanced density, normal speed, standard glow and fragments. Cyan preview uses blue palette and bright glow. No overlays, readable words, or watermarks are present in the retained scene captures.

## Verification commands and results

- Installed and updated the final signed APK with `python3 install.py --serial emulator-5586 --apk build/signal-rain.apk --yes`; existing visual preferences remained intact.
- Queried the schema/settings URIs and updated palette/glow through the provider; invalid palette values raised `IllegalArgumentException` without changing preferences.
- Used D-pad Down and Select to open density choices and change Balanced to Dense. A provider query confirmed `density=dense` persisted. Fire TV UI accessibility interception was temporarily unbound for this isolated test and its exact prior value was restored afterward. No system screensaver selection or timers changed.
- Opened the preview at multiple times and inspected distinct frames, then returned to settings. The full-screen renderer contains no readable text, watermark, loading frame or unrelated menu.
- `aapt dump badging` confirms package `com.jeremykenedy.signalrain`, version 1.0.0/code 1, min SDK 23 and target SDK 36. `aapt dump permissions` lists no permissions. No app network calls or third-party runtime dependencies are present.
- Host JaCoCo report: 51/51 lines and 50/50 branches for settings resolution/validation. Installer coverage: 226/226 statements and 80/80 branches. Actual source formatting is checked from the repository-root Gradle project.

The emulator test does not establish automatic idle activation, reboot persistence, physical performance or vendor rollback prevention. The installer deliberately leaves system settings unchanged.
