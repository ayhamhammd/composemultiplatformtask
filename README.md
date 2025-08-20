# Compose Multiplatform Meals App

A cross-platform mobile application built with Compose Multiplatform that displays meals from different categories. The app fetches data from TheMealDB API and supports both Android and iOS platforms.

## Features

- Browse meals by category (Seafood and Beef)
- Swipe between categories using horizontal pager
- View detailed meal information including ingredients and instructions
- Responsive grid layout for meal cards
- Error handling with retry functionality
- Multilingual support (English and Arabic)

## Architecture

The project follows Clean Architecture principles with clear separation of concerns:

### Data Layer
- **API Service**: HTTP client using Ktor for network requests
- **DTOs**: Data transfer objects for API responses
- **Repository Implementation**: Handles API calls and data mapping
- **Mappers**: Convert DTOs to domain entities

### Domain Layer
- **Entities**: Business models (Meal, MealDetail, Ingredient)
- **Repository Interface**: Abstract data access
- **Use Cases**: Single-responsibility business logic

### Presentation Layer
- **ViewModels**: State management using StateFlow
- **UI State**: Sealed class for Loading/Success/Error states
- **Navigation**: Compose Navigation with dependency injection

### UI Layer
- **Screens**: Main composables for different views
- **Components**: Reusable UI elements
- **Theme**: Material 3 design system

### Utils Layer
- **Constants**: API endpoints, navigation routes, UI constants
- **Extensions**: Helper functions and extension methods

## Tech Stack

- **Kotlin Multiplatform Mobile (KMP)**: Shared business logic
- **Compose Multiplatform**: UI framework
- **Ktor**: HTTP client for API calls
- **Kotlinx Serialization**: JSON parsing
- **Koin**: Dependency injection
- **Coil**: Image loading
- **Material 3**: Design system

## Project Structure

```
composeApp/src/
├── commonMain/kotlin/org/example/cmp/
│   ├── data/
│   │   ├── models/          # DTOs and API response models
│   │   ├── network/         # HTTP client configuration
│   │   ├── remote/          # API service implementation
│   │   ├── repositories/    # Repository implementations
│   │   └── mappers/         # DTO to domain mapping
│   ├── domain/
│   │   ├── entities/        # Business models
│   │   ├── repositories/    # Repository interfaces
│   │   └── usecases/        # Business logic
│   ├── presentation/
│   │   ├── components/      # Reusable UI components
│   │   ├── navigation/      # Navigation setup
│   │   ├── screens/         # Screen composables with ViewModels
│   │   ├── states/          # UI state definitions
│   │   └── theme/           # Material theme configuration
│   ├── utils/               # Constants and extension functions
│   └── di/                  # Dependency injection modules
├── androidMain/             # Android-specific code
└── iosMain/                 # iOS-specific code
```

## Getting Started

### Prerequisites

- Android Studio or IntelliJ IDEA
- JDK 17 or higher
- Xcode (for iOS development)

## API Integration

The app uses [TheMealDB API](https://www.themealdb.com/api.php) endpoints:
- `GET /filter.php?c={category}` - Get meals by category
- `GET /lookup.php?i={id}` - Get meal details by ID

## Key Implementation Details

### State Management
- ViewModels manage UI state using `StateFlow`
- `UiState<T>` sealed class handles Loading/Success/Error states
- State hoisting pattern for better testability

### Navigation
- ViewModels are co-located with their screens
- Screen composables receive states and callbacks as parameters
- This approach enables Compose previews and better testing

### Error Handling
- Network errors are wrapped in `Result<T>`
- Repository layer handles API failures
- UI displays user-friendly error messages with retry options

### Code Organization
- Constants centralized in utils package
- Extension functions for common operations
- Reusable `UiStateWrapper` for consistent state handling

### Internationalization
- String resources support English and Arabic
- Uses Compose Resources for multiplatform string handling

## Dependencies

Key dependencies are managed in `gradle/libs.versions.toml`:
- Compose Multiplatform
- Kotlin Multiplatform
- Ktor client
- Kotlinx Serialization
- Koin for dependency injection
- Coil for image loading
