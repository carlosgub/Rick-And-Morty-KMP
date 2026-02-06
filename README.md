# Curso de Kotlin Multiplatform desde cero 🚀

Este repositorio contiene el proyecto práctico del **Curso de Kotlin Multiplatform (KMP) desde cero** disponible en mi canal de YouTube. A lo largo del curso, construimos una aplicación completa de Rick and Morty utilizando las últimas tecnologías y mejores prácticas de desarrollo multiplataforma.

## 📱 Funcionalidades Implementadas

- **Listado de Personajes**: Visualización de todos los personajes con scroll infinito (paginación).
- **Detalle de Personaje**: Información detallada de cada personaje, incluyendo episodios en los que aparece.
- **Listado de Episodios**: Exploración de los episodios de la serie.
- **Listado de Ubicaciones**: Exploración de las diferentes localizaciones.
- **Navegación**: Implementación de Bottom Navigation con Jetpack Compose Navigation.
- **Screenshot Testing**: Pruebas visuales automatizadas para asegurar la consistencia de la UI usando Roborazzi y Composable Preview Scanner.
- **Unit Testing**: Pruebas unitarias robustas utilizando Fakes para los repositorios y Ktor Mock Engine para simular las respuestas de la API.
- **Splash Screen**: Implementación de pantalla de inicio nativa para Android e iOS.

## 🛠️ Tecnologías y Librerías

El proyecto utiliza un stack tecnológico moderno para KMP:

- **[Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html)**: Compartición de lógica de negocio entre Android e iOS.
- **[Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/)**: UI compartida con Jetpack Compose.
- **[Orbit MVI](https://github.com/orbit-mvi/orbit-mvi)**: Patrón de arquitectura para el manejo de estado y efectos secundarios.
- **[Koin](https://insert-koin.io/)**: Inyección de dependencias multiplataforma.
- **[Ktor](https://ktor.io/)**: Cliente HTTP para el consumo de la API de Rick and Morty.
- **[Coil 3](https://coil-kt.github.io/coil/)**: Carga de imágenes optimizada para multiplataforma.
- **[Jetpack Navigation](https://developer.android.com/guide/navigation)**: Navegación nativa de Compose adaptada a KMP.
- **[Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization)**: Serialización de datos JSON.
- **[Roborazzi](https://github.com/takahirom/roborazzi)**: Librería para Screenshot Testing.
- **[Composable Preview Scanner](https://github.com/sergio-sastre/ComposablePreviewScanner)**: Escaneo automático de Previews para tests visuales.

## 🏗️ Arquitectura

El proyecto sigue los principios de **Clean Architecture**:

- **Data Layer**: Repositorios, Mappers y Data Sources (Ktor).
- **Domain Layer**: Modelos de dominio.
- **Presentation Layer**: ViewModels (Orbit MVI) y Screens (Compose Multiplatform).

## 🧪 Testing

El proyecto incluye pruebas de captura de pantalla para verificar la interfaz de usuario:

- **Grabar capturas (Golden Images)**: `./gradlew :composeApp:recordRoborazziDebug`
- **Verificar cambios (Regresiones)**: `./gradlew :composeApp:verifyRoborazziDebug`
- **Comparar y generar reportes**: `./gradlew :composeApp:compareRoborazziDebug`

## 🚀 Cómo empezar

1. Clonar el repositorio.
2. Asegurarte de tener instalado **Android Studio** (Ladybug o superior) y **Xcode** (para iOS).
3. Configurar el entorno siguiendo la [guía oficial de KMP](https://kotlinlang.org/docs/multiplatform-quickstart.html#set-up-the-environment).
4. Ejecutar la app en Android o iOS desde Android Studio.

## 📌 Sobre el curso

Este curso está pensado para developers que ya conocen Kotlin y quieren dominar el desarrollo multiplataforma de forma profesional.

- **YouTube**: [CarlosGub - Suscríbete](https://www.youtube.com/@carlosgub)

---
#Kotlin #KotlinMultiplatform #KMP #ComposeMultiplatform #Ktor #Koin #OrbitMVI #Roborazzi #Android #iOS #RickAndMorty