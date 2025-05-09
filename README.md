# SubPilot

![Build](https://github.com/docishoon/SubPilot/actions/workflows/android.yml/badge.svg)
![Lint](https://github.com/docishoon/SubPilot/actions/workflows/lint.yml/badge.svg)
![Tests](https://github.com/docishoon/SubPilot/actions/workflows/test.yml/badge.svg)
![License](https://img.shields.io/github/license/docishoon/SubPilot)
![Platform](https://img.shields.io/badge/platform-Android-blue)
![Release](https://img.shields.io/github/v/release/docishoon/SubPilot?include_prereleases)

---

**SubPilot** is a powerful Android app for subtitle editing and translation.

It supports `.srt` and `.ass` formats, video syncing, dual-language side-by-side editing, persistent sessions, and works entirely offline using Jetpack Compose.

## Features

- **Subtitle editing**: `.srt` & `.ass`, full timeline + text editor
- **Video support**: `.mp4`, `.mkv` with preview syncing
- **Dual subtitle view**: reference (non-editable) & translation (editable)
- **Timeline editor**: zoomable, draggable blocks for start/end
- **Session resume**: optionally continue where you left off
- **No internet needed**: 100% offline functionality
- **Dark & Light theme**: auto-detected from system
- **Tablet & phone layouts**: fully responsive UI

---

## Getting Started

### Prerequisites
- Android Studio (Giraffe+)
- JDK 17
- Git

### Clone & Open
```bash
git clone https://github.com/docishoon/SubPilot.git
cd SubPilot
```
Open in Android Studio → Sync Gradle → Run on emulator/device

### Build APK
From Android Studio:
> Build → Build APK(s)
Output: `app/build/outputs/apk/debug/app-debug.apk`

### Build Release APK
> Build → Generate Signed Bundle / APK → Choose `release`

---

## GitHub Releases
Visit [Releases](https://github.com/docishoon/SubPilot/releases) to download latest APK.

---

## Screenshots
_Add your app screenshots or demo gif here._

---

## Contributing
We welcome pull requests and issues:
- Bugs → `bug_report.md`
- Features → `feature_request.md`

---

## License
MIT License © 2025 Docishoon
