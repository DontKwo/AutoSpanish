# Developer Guide

Build, test, lint, and package AutoSpanish from source across Windows, macOS, and Linux.

## Prerequisites

- **Java Development Kit (JDK) 17+**
- **Android SDK** (`compileSdk 34`, `minSdk 26`) via Android Studio or command-line tools
- **[Astral `uv`](UV_INSTALLATION.md)** (for strict repository linting)

---

### Windows

```powershell
$env:JAVA_HOME = "$PWD\.toolchain\jdk-17.0.20.1+1"
.\gradlew.bat test
.\gradlew.bat assembleDebug
```

---

### macOS

```bash
chmod +x ./gradlew
./gradlew test
./gradlew assembleDebug
```

---

### Linux

```bash
chmod +x ./gradlew
./gradlew test
./gradlew assembleDebug
```

---

## Building Signed Release APKs

Signed release builds require environment variables `ANDROID_KEYSTORE_PATH`, `ANDROID_KEYSTORE_PASSWORD`, `ANDROID_KEY_ALIAS`, and `ANDROID_KEY_PASSWORD` matching a local `.jks` keystore (never commit keystore files or credentials).

```bash
./gradlew assembleRelease
```

## Verification & Strict Linting

```bash
./gradlew test
uv run --no-project python ../ai-dev-tools/scripts/claudemd_linter.py . --level strict --full
uv run --no-project python ../ai-dev-tools/scripts/link_checker.py .
```

## Related Documentation

- [Project Overview](../README.md)
- [Developer uv Installation Guide](UV_INSTALLATION.md)
- [Android APK Installation Guide](APK_INSTALLATION.md)
- [AI Agent Reference](../AGENTS.md)
- [Third-Party Notices](../THIRD_PARTY_NOTICES.md)
- [Security Policy](../SECURITY.md)
