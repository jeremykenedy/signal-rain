# Architecture

Signal Rain uses an Android Canvas renderer with a fixed pool of vertical streams. Each stream has a seeded depth, trail length, offset, and geometric mark pattern. Frame time drives the falling position; depth varies speed and size. Fading tails and a bright head create the rain effect without fonts or readable text.

The view schedules frames at approximately 30 frames per second and removes pending callbacks when stopped. Rendering uses an existing Paint and stream pool rather than allocating objects each frame. Output follows the display surface; native 4K performance is unverified.

The DreamService and preview share the same renderer. Preferences resolve once per session, with validated provider input and safe defaults. The settings provider exposes only app-owned visual preferences. There are no runtime dependencies, network permissions, services, or requests beyond Android framework APIs.
