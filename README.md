# WeatherForYou 🌦️

**WeatherForYou** is a modern Android showcase application designed to demonstrate a robust, scalable, and testable app architecture. It leverages the latest Jetpack libraries and industry-standard patterns to provide a seamless weather forecasting experience.

## 🏗️ Architecture & Tech Stack

This project follows **Clean Architecture** principles and implements the **MVI (Model-View-Intent)** pattern for the UI layer, ensuring a predictable and unidirectional data flow.

### Core Components:
*   **Dependency Injection**: [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for robust and scoped dependency management.
*   **Networking**: [Retrofit](https://square.github.io/retrofit/) + [Kotlinx Serialization](https://kotlinlang.org/docs/serialization.html) for type-safe API communication.
*   **Local Persistence**: 
    *   **Room Database**: For caching weather forecasts and managing saved locations.
    *   **DataStore**: For lightweight storage of user preferences (units, permissions).
*   **Navigation**: [Navigation 3](https://developer.android.com/guide/navigation/navigation-new-concepts) (Experimental) for a state-driven, declarative navigation system.
*   **UI Layer**: 
    *   **Jetpack Compose**: 100% declarative UI.
    *   **MVI Architecture**: Centralized State and Intent management in ViewModels.
*   **Reactive Programming**: Extensive use of **Kotlin Coroutines** and **Flow** for asynchronous data streams.
*   **Permissions**: Seamless handling of Android Location Permissions via standard Activity Result APIs.

---

## ✨ Features

*   📍 **Location-Aware**: Automatically retrieves weather for your current location upon permission grant.
*   🔍 **Smart Search**: Real-time city suggestions and management of multiple saved locations.
*   🎨 **Dynamic Theming**: Dynamic UI color schemes and backgrounds that adapt to current weather conditions (Sunny, Cloudy, Night, etc.).
*   ⚙️ **Customizable**: Toggle between Celsius/Fahrenheit and 12h/24h time formats via DataStore-backed settings.
*   🔒 **Onboarding**: A dedicated permission-first onboarding flow that gracefully handles user interactions.

---

## 🚀 Getting Started

To run the project locally, you will need an API key from [WeatherAPI.com](https://www.weatherapi.com/).

1.  **Get your API Key**: Sign up at WeatherAPI and retrieve your key from the dashboard.
2.  **Configure `local.properties`**: Add your key to the `local.properties` file in the root directory:
    ```properties
    WEATHER_API_KEY=your_api_key_here
    ```
3.  **Build & Run**: Open the project in Android Studio (Ladybug or newer) and run the `:app` module.

---

## 🛠️ Project Structure
*   `data`: Implementation of repositories, local (Room/DataStore) and remote (Retrofit) data sources.
*   `domain`: Core business logic, entities, and use cases (Pure Kotlin).
*   `ui`: Jetpack Compose screens, ViewModels (MVI), and dynamic theme components.

---

## ⏭️ Coming Next
*   **Advanced Error Handling**: Moving from raw exceptions to a domain-driven sealed error hierarchy.
*   **Unit Testing**: Full coverage for UseCases, ViewModels (MVI), and Repositories.
*   **UI Testing**: Integration tests using Compose Test Rule and Hilt testing.
*   **Offline Mode**: Enhanced reconciliation logic between local cache and remote data.

---
*Developed as a technical showcase for modern Android development, assisted by AI code tools (Gemini).*
