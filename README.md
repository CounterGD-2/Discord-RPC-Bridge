# DiscordMobileBridge

Starter project for Minecraft Java Edition **26.2 + NeoForge**, targeting Java 25.

## Included
- Presence activity model, source arbitration, and source lifecycle.
- Basic client-side menu/world activity collector.
- JSON config at `config/discordmobilebridge.json` and `config.schema.json`.
- Cloth Config screen via NeoForge's config-screen extension point.
- Simple RPC, DiscordPresence, and mobile-transport adapter interfaces.
- GitHub Actions workflow at `.github/workflows/build.yml`.
- Dependency-free Presence Core smoke test.

## Important integration status
This is an integration scaffold, not a finished Discord Mobile transport. The Simple RPC and DiscordPresence adapters deliberately do not guess undocumented APIs. They remain inert until their public APIs are verified. Mobile transport is also a disabled interface: do not assume Discord desktop IPC/RPC works with the Android app. The official supported Android SDK/API, packaging constraints, and authorization model must be validated before implementing it.

No Discord credentials or tokens belong in this mod's config. The defaults avoid exposing server addresses and world names.

## Build
Use Java 25 and Gradle:
```bash
gradle clean build smokeTest
```
GitHub Actions runs this and uploads the mod JAR as an artifact.

## Runtime dependencies
- Minecraft 26.2
- NeoForge 26.2
- Cloth Config API `26.2.155+neoforge`

Install the NeoForge build of Cloth Config in your `mods` folder. It is compile-only and is not bundled into this mod.

## Configuration
Created on first launch at `config/discordmobilebridge.json`. The config screen is exposed through NeoForge's mod config-screen hook.

## Next integration milestones
1. Verify and implement the documented Simple RPC public API.
2. Verify and implement the documented DiscordPresence public API.
3. Verify whether an official Discord-supported Android integration can be embedded in a NeoForge client mod.
4. Test on Minecraft 26.2 NeoForge and the target Android launcher.
