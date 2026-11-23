# Developer uv Installation Guide

> **For Everyday Users**: You do **not** need `uv`, Python, Android Studio, or command-line tools to install `AutoSpanish`. Download `AutoSpanish-Android.apk` from [Latest Releases](https://github.com/DontKwo/AutoSpanish/releases/latest) and follow the [Android APK Installation Guide](APK_INSTALLATION.md).

Developers running strict repository lint checks and Markdown link validation (`ai-dev-tools`) use [Astral `uv`](https://docs.astral.sh/uv/).

## Platform Instructions

### Windows

```powershell
powershell -ExecutionPolicy ByPass -c "irm https://astral.sh/uv/install.ps1 | iex"
winget install --id=astral-sh.uv -e
```

### macOS

```bash
curl -LsSf https://astral.sh/uv/install.sh | sh
brew install uv
```

### Linux

```bash
curl -LsSf https://astral.sh/uv/install.sh | sh
```

Confirm installation with `uv --version`.

## Related Documentation

- [Project Overview](../README.md)
- [Developer Guide](DEVELOPMENT.md)
- [Android APK Installation Guide](APK_INSTALLATION.md)
- [Security Policy](../SECURITY.md)
