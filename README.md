# Login Application

Android login app with instrumented tests (AndroidJUnit4 + Espresso).

## Prerequisites

- **JDK 17** or higher
- **Android SDK** with:
  - API level **29** (minSdk) through **36** (compileSdk/targetSdk)
  - Android SDK Build-Tools
  - Android Emulator and/or a physical device with USB debugging enabled
- **Android Studio** (recommended) or command-line tools only

## Setup

1. **Clone the repository** (if not already):
   ```bash
   git clone https://github.com/hangchonghow89/login.git
   cd LoginApplication
   ```

2. **Open in Android Studio**  
   Open the project folder. Android Studio will sync Gradle and download dependencies.

   Or **from the command line**, ensure `ANDROID_HOME` is set and run:
   ```bash
   ./gradlew tasks
   ```
   to verify the project builds.

3. **Connect a device or start an emulator**
   - Physical device: enable **Developer options** and **USB debugging**, then connect via USB.
   - Emulator: create an AVD with API 29+ in **Device Manager** and start it.

## Build the app

- **Debug APK** (default):
  ```bash
  ./gradlew assembleDebug
  ```
  Output: `app/build/outputs/apk/debug/app-debug.apk`

- **Release APK**:
  ```bash
  ./gradlew assembleRelease
  ```
  Output: `app/build/outputs/apk/release/app-release.apk`

## Run instrumented tests

Instrumented tests run on a device or emulator. They use the **test instrumentation runner** (`AndroidJUnitRunner`) and install both the app APK and the **instrumented test APK** on the device.

### From the command line

1. Ensure one device/emulator is connected and visible to `adb`:
   ```bash
   adb devices
   ```

2. Run all instrumented tests:
   ```bash
   ./gradlew connectedAndroidTest
   ```

   This will:
   - Build the app and the **instrumented test APK**
   - Install the app and test APK on the connected device
   - Execute tests (e.g. `ExampleInstrumentedTest`)
   - Write reports to `app/build/reports/androidTests/connected/`

3. Run instrumented tests for a specific build type (e.g. debug):
   ```bash
   ./gradlew connectedDebugAndroidTest
   ```

### From Android Studio

1. Open **app → src → androidTest** and find the test class (e.g. `ExampleInstrumentedTest`).
2. Right-click the class or the `androidTest` folder → **Run 'Tests in...'** (or use the green run icon next to the test).
3. Select the target device when prompted. Android Studio will install the app and the instrumented test APK and run the tests.

### Test APK location (for reference)

After building instrumented tests, the test APK is generated at:
- **Debug:** `app/build/outputs/apk/androidTest/debug/app-debug-androidTest.apk`
- **Release:** `app/build/outputs/apk/androidTest/release/app-release-androidTest.apk`

You do not normally install this APK by hand; `connectedAndroidTest` (or Android Studio) installs both the app and this test APK and runs the tests.

## Step to install and run the instrumentation test APK
1. **Install the app APK:**
   adb install app/build/outputs/apk/debug/app-debug.apk
2. **Install the instrumentation test APK:**
   adb install app/build/outputs/apk/androidTest/debug/app-debug-androidTest.apk
3. **Run the instrumentation test:**
   adb shell am instrument -w com.hch.loginapplication.test/androidx.test.runner.AndroidJUnitRunner

## Project structure

- **App code:** `app/src/main/`
- **Instrumented tests:** `app/src/androidTest/` (e.g. `ExampleInstrumentedTest.kt`)
- **Unit tests:** `app/src/test/`

## Troubleshooting

- **No devices found:** Run `adb devices` and ensure one device shows as `device` (not `unauthorized`). For emulator, start the AVD first.
- **Build fails:** Ensure JDK 11+ is in use (`java -version`) and `ANDROID_HOME` points to your Android SDK.
- **Tests fail on device:** Confirm the device/emulator API level is at least **29** (minSdk).