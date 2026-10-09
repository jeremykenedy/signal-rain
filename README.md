<p align="center">
    <picture>
        <source media="(prefers-color-scheme: dark)" srcset="art/banner-dark.svg">
        <source media="(prefers-color-scheme: light)" srcset="art/banner-light.svg">
        <img src="art/banner-light.svg" alt="Signal Rain, animated screensaver with no ads, analytics, or tracking" width="800">
    </picture>
</p>

<p align="center">An animated abstract digital-rain screensaver for Fire TV, Android TV, and Google TV.</p>

<p align="center">
    <a href="https://github.com/jeremykenedy/signal-rain/releases"><img src="https://img.shields.io/github/v/release/jeremykenedy/signal-rain?label=latest%20release" alt="Latest release"></a>
    <a href="https://github.com/jeremykenedy/signal-rain/releases"><img src="https://img.shields.io/github/downloads/jeremykenedy/signal-rain/total" alt="GitHub release downloads"></a>
    <a href="https://github.com/jeremykenedy/signal-rain/actions/workflows/ci.yml"><img src="https://github.com/jeremykenedy/signal-rain/actions/workflows/ci.yml/badge.svg" alt="CI"></a>
    <a href="https://github.com/jeremykenedy/signal-rain/actions/workflows/style.yml"><img src="https://github.com/jeremykenedy/signal-rain/actions/workflows/style.yml/badge.svg" alt="Code style"></a>
    <a href="https://github.com/jeremykenedy/signal-rain/actions/workflows/docs.yml"><img src="https://github.com/jeremykenedy/signal-rain/actions/workflows/docs.yml/badge.svg" alt="Documentation"></a>
    <a href="https://github.com/jeremykenedy/signal-rain/actions/workflows/security.yml"><img src="https://github.com/jeremykenedy/signal-rain/actions/workflows/security.yml/badge.svg" alt="Privacy and source checks"></a>
    <a href="LICENSE"><img src="https://img.shields.io/badge/License-Apache--2.0-blue.svg" alt="Apache-2.0 license"></a>
    <a href="https://github.com/jeremykenedy"><img src="https://img.shields.io/github/followers/jeremykenedy?label=Follow&style=social" alt="Follow on GitHub"></a>
    <a href="https://github.com/jeremykenedy/signal-rain" title="Open the repository and click Star"><img src="https://img.shields.io/badge/Star-this%20repo-yellow?logo=github&style=social" alt="Star this repo"></a>
    <a href="https://github.com/jeremykenedy/signal-rain/stargazers"><img src="https://img.shields.io/github/stars/jeremykenedy/signal-rain?style=social" alt="Star count"></a>
    <a href="https://github.com/sponsors/jeremykenedy"><img src="https://img.shields.io/badge/Sponsor-jeremykenedy-EA4AAA?logo=githubsponsors&logoColor=white" alt="Sponsor"></a>
</p>

Show some love by starring this repository on GitHub.

## Table of contents

- [Privacy](#privacy)
- [Features](#features)
- [Requirements](#requirements)
- [Installation](#installation)
- [Configuration](#configuration)
- [Screenshots](#screenshots)
- [Building and testing](#building-and-testing)
- [Documentation](#documentation)
- [Release notes](#release-notes)
- [License](#license)

## Privacy

The Android app requests no network permission and makes no network requests. It contains no ads, analytics, telemetry, crash reporting, tracking, or reporting code. Preferences stay on the device. The standalone installer contacts GitHub only when you ask it to fetch a release APK and checksum. That request does not include TV, device, or usage data.

## Features

- Continuous vertical streams of luminous abstract marks, with no words or watermark.
- Green, cyan-blue, and amber palettes.
- Sparse, balanced, dense, and packed stream counts.
- Slow, normal, and fast fall speeds.
- Dim, standard, and bright glow strength.
- Bars, fragments, and circuit-style geometric marks.
- Per-setting random choices and randomize-all at each start.
- Remote-friendly settings and a validated host settings provider.
- Animation stops when the preview or dream is detached.

## Requirements

- Android 6.0 (API 23) or newer with DreamService support.
- ADB for installation from a computer.
- Android SDK platform 36 and build-tools 36.0.0 for local builds.

Android TV emulator behavior has been checked at 1920x1080. Physical Fire TV and Google TV devices were not tested for this release. See [device verification](docs/VERIFICATION.md) for the test limits.

## Installation

Connect the TV to the same network as this computer, enable ADB debugging, and run:

```bash
python3 install.py --serial TV_IP:5555
```

The installer fetches the latest signed release from GitHub, verifies its published SHA-256 checksum, and installs or updates the app. Select Signal Rain in the TV's screensaver or ambient display settings. See [installation and removal](docs/INSTALLATION.md) for local APK installation, unattended use, and uninstall.

## Configuration

Open Signal Rain from the TV launcher to change settings. Each option supports its own random choice. The global random setting chooses new options when a screensaver session begins. Full defaults and values are in [configuration](docs/CONFIGURATION.md).

## Screenshots

<p align="center">
    <img src="docs/screenshots/signalrain-preview.png" alt="Green luminous geometric rain captured from the running Android TV app" width="49%">
    <img src="docs/screenshots/signalrain-cyan-preview.png" alt="Cyan luminous geometric streams captured from the running app" width="49%">
    <img src="docs/screenshots/settings-android-tv.png" alt="Signal Rain settings on an Android TV emulator" width="49%">
</p>

Captures come from the running app; device and setting details are recorded in [device verification](docs/VERIFICATION.md).

## Building and testing

```bash
bash build.sh
bash test.sh
bash scripts/test-python-coverage.sh
bash scripts/test-coverage.sh
```

The app has no third-party runtime dependencies. See [building](docs/BUILDING.md), [testing](docs/TESTING.md), and [architecture](docs/ARCHITECTURE.md).

## Documentation

- [Installation and removal](docs/INSTALLATION.md)
- [Configuration and host settings](docs/CONFIGURATION.md)
- [Building from source](docs/BUILDING.md)
- [Architecture](docs/ARCHITECTURE.md)
- [Testing and coverage](docs/TESTING.md)
- [Device verification](docs/VERIFICATION.md)
- [Continuous integration](docs/CI.md)
- [Troubleshooting](docs/TROUBLESHOOTING.md)
- [Release process](docs/RELEASING.md)

## Release notes

- [Version 1.0.0](docs/releases/v1.0.0.md)

## License

Signal Rain is licensed under the [Apache License, Version 2.0](LICENSE). See [NOTICE](NOTICE) for project notices.
