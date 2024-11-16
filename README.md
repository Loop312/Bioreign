
<h1 align="center">
  <img src="composeApp/src/commonMain/composeResources/drawable/BioreignTempLogo.png" alt="Temporary Logo"/>
</h1>

<p align="center">
  Bioreign is a 2D-Topdown RPG, Kotlin Multiplatform project targeting Android, iOS, Web, Desktop.
</p>

<p align="center">
  <a href="https://discord.gg/KjBDzSj5af">
    <img src="https://dcbadge.limes.pink/api/server/KjBDzSj5af" alt="Discord"/>
  </a>
  <br>
  <br>
  <a href="https://github.com/Loop312/Bioreign/releases/latest">
    <img src="https://img.shields.io/badge/Download-Latest_Release-2ea44f?logo=github&logoColor=white" alt="Download - Latest Release"/>
  </a>
</p>

# About the project
This is our most ambitious project yet, making a fully fleshed-out RPG available on all platforms using just
a graphics library to make it happen. This means that we are creating our own engine as
we create our game.

***Help with art and spritesheets is very much appreciated, and feel free to help us out!***

# Downloads

### Stable Releases
N/A

### Alpha Builds
| Platform   | Status                                                                                                                                                                                  | Download                                                                                                        |
|------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------|
| 🪟 Windows | [![Qt Build](https://github.com/Loop312/Bioreign/actions/workflows/build-gradle-project.yml/badge.svg)](https://github.com/Loop312/Bioreign/actions/workflows/build-gradle-project.yml) | [Windows EXE](https://nightly.link/Loop312/Bioreign/workflows/build-gradle-project/master/Bioreign-Windows.zip) |
| 📱 Android | [![Qt Build](https://github.com/Loop312/Bioreign/actions/workflows/build-gradle-project.yml/badge.svg)](https://github.com/Loop312/Bioreign/actions/workflows/build-gradle-project.yml) | [APK](https://nightly.link/Loop312/Bioreign/workflows/build-gradle-project/master/Bioreign-Android.zip)         |
| 🌐 Web     | [![Qt Build](https://github.com/Loop312/Bioreign/actions/workflows/web.yml/badge.svg)](https://github.com/Loop312/Bioreign/actions/workflows/web.yml)                              | [Website](https://loop312.github.io/Bioreign/)                                                                  |


‎


### USELESS INFO FOR MOST (came with the file structure)
* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

* `/server` is for the Ktor server application.

* `/shared` is for the code that will be shared between all targets in the project.
  The most important subfolder is `commonMain`. If preferred, you can add code to the platform-specific folders here too.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html),
[Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/#compose-multiplatform),
[Kotlin/Wasm](https://kotl.in/wasm/)…

We would appreciate your feedback on Compose/Web and Kotlin/Wasm in the public Slack channel [#compose-web](https://slack-chats.kotlinlang.org/c/compose-web).
If you face any issues, please report them on [GitHub](https://github.com/JetBrains/compose-multiplatform/issues).

You can open the web application by running the `:composeApp:wasmJsBrowserDevelopmentRun` Gradle task.
