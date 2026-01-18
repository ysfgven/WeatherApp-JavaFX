# 🌦️ WeatherApp-JavaFX

[![Java](https://img.shields.io/badge/Java-17%2B-orange)](https://www.oracle.com/java/)
[![JavaFX](https://img.shields.io/badge/JavaFX-17-blue)](https://openjfx.io/)
[![Maven](https://img.shields.io/badge/Maven-3.8.1-red)](https://maven.apache.org/)

A professional-grade desktop application for real-time weather tracking, built with JavaFX. This project was developed as part of my Software Engineering 2nd-year studies to implement modern software principles, asynchronous data handling, and secure development practices.

## 🚀 Engineering Practices Applied

- **MVC Architecture:** Strict separation of concerns (Model-View-Controller). The business logic, data and UI are decoupled, ensuring maintainability and scalability.
- **Asynchronous Network Operations:** API requests are handled in a separate background thread using JavaFX `Task`. This ensures a **non-blocking UI**, providing a smooth user experience while fetching data.
- **Robust Error Handling & Logging:** A centralized `ErrorHandler` manages unexpected scenarios (network loss, invalid city input, etc.). While users receive intuitive alerts, technical details are recorded in `logs/application.log` for debugging.
- **Security (Environment Variables):** API keys are never hard-coded. The application securely retrieves credentials via system environment variables.



## 🛠️ Technical Stack & Integrations

- **Java 17+**
- **JavaFX:** For building a modern and responsive user interface.
- **GSON:** Used for parsing complex JSON responses from the API into Java POJOs.
- **WeatherAPI Integration:** Real-time data and weather icons are fetched dynamically via REST API.
- **Maven:** Project lifecycle and dependency management.

## ⚠️ Known Issues & Technical Debt

As this is a project focused on learning and architectural implementation, the following limitations apply:

1.  **Icon Latency:** Icons are fetched directly from WeatherAPI's CDN. In cases of slow internet connections, there might be a slight delay in icon rendering.
2.  **Search Logic:** City search accuracy is currently dependent on the API provider's capabilities. Client-side "fuzzy search" or "auto-complete" is not yet implemented.
3.  **Test Coverage:** The project currently lacks automated Unit Tests. Stability is ensured through manual edge-case testing (Unit Testing is in the TODO list for future updates).
4. **Current Focus:** The application currently focuses on real-time data. Future versions are planned to include extended forecast features.


## ⚙️ Setup and Security

To run this project locally:
1. Obtain an API key from [WeatherAPI](https://www.weatherapi.com/).
2. Create a system environment variable named `WEATHER_API_KEY`.
3. Set your API key as the value for this variable.
4. Clone the repository, install Maven dependencies, and run the application using `mvn javafx:run`.

## 📂 Project Structure

```text
src/main/java/com/ysfgven/weatherapp/
├── controller/      # Event handling and UI logic
├── model/           # Data models 
├── service/         # API integration and data parsing
├── view/            # JavaFX UI components
└── util/            # Helper classes (IconManager, ErrorHandler)
```


## 👨‍💻 Developer's Note
This project represents my transition from writing "functional code" to "architectural code." It was a deep dive into the JavaFX lifecycle, multithreading challenges, and the importance of decoupling components in a Java environment.

---
**Contact:** [LinkedIn](https://www.linkedin.com/in/yusuf-g%C3%BCven/) | [yusuf.guven0@hotmail.com](mailto:yusuf.guven0@hotmail.com)
