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
