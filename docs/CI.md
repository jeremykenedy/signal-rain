# Continuous integration

GitHub Actions builds the APK with Android SDK 36, runs host behavior tests, verifies 100% line and branch coverage for the pure settings modules, and checks the repository for syntax and generated artifacts. CI has no runtime credentials and does not publish a signed release. See `.github/workflows/` for the active checks.

The Android application has no third-party runtime dependencies, ads, analytics, telemetry, or network permission. The standalone installer contacts GitHub only when the user requests a release download. External quality providers are only shown as badges when their workflows and access are configured and verified.

Spotless formats and checks every Java source and test under `src`, `tests`, and `coverage-tests`. JaCoCo enforces a 1.0 covered ratio for both lines and branches of the pure settings modules. Installer coverage separately requires 100% lines and branches. Drawing and Android lifecycle code require platform checks and are outside the host coverage metric, rather than counted as covered. No whole-application coverage claim is made.

Aikido and Scrutinizer are intentionally not integrated. SonarCloud, Codacy, CodeFactor and token-based scanners are omitted because no configured access was available for this project; no badges claim their scores. GitHub security scans may run independently when enabled by the owner.
