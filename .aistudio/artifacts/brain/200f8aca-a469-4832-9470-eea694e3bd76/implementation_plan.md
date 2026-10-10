# Pantalla Interactiva del Santo Rosario: Misterios y Contador Visual de Avemarías

Pantalla interactiva y devocional para rezar el Santo Rosario con controles dedicados de navegación entre los 5 misterios, un contador visual de cuentas de rosario circulares que se incrementa al tocar la pantalla o las cuentas, respuesta de vibración háptica suave por cuenta y especial al completar la decena, y presentación guiada del Gloria, Jaculatoria y avance fluido al siguiente misterio.

## Decisiones Críticas y Preferencias Confirmadas

> [!IMPORTANT]
> Decisiones de diseño y experiencia confirmadas por el usuario durante la fase interactiva:
> - **Visualización de Cuentas:** Cuentas circulares interactivas de rosario organizadas visualmente con progreso numerado (1 a 10) y animaciones de pulsación.
> - **Flujo de Conclusión de Decena:** Al completar las 10 Avemarías, se despliega de forma animada el Gloria al Padre, la Jaculatoria mariana y la Oración de Fátima, junto con un botón prominente para avanzar al siguiente misterio.
> - **Respuesta Háptica:** Vibración suave (45 ms) en cada Avemaría rezada y un patrón de vibración distintivo (doble pulso celebratorio) al finalizar las 10 Avemarías del misterio.

---

## 1. Visión General y Concepto

La experiencia de oración del Santo Rosario pasa de ser un texto estático a un instrumento devocional táctil e inmersivo:
- **Navegación de Misterios:** Selector y botones dedicados de misterio anterior (`<`) y siguiente (`>`), además de un selector rápido de misterios (1 al 5) con el misterio del día marcado ("Gozosos", "Luminosos", "Dolorosos" o "Gloriosos").
- **Meditación y Fruto:** Tarjeta de contemplación con pasaje bíblico, meditación y fruto espiritual del misterio, adaptada a la escala de texto preferida por el usuario.
- **Decenario Interactivo:** Cuentas circulares de rosario que se llenan con animación de escala y color litúrgico, con un pulsador grande táctil "Rezar Avemaría" para facilitar la oración con una sola mano.
- **Cierre del Misterio:** Despliegue del Gloria, Jaculatoria de la Virgen ("María, Madre de gracia...") y la jaculatoria de Fátima ("Oh Jesús mío...") al completar la decena, con transición guiada hacia el siguiente misterio.

---

## 2. Experiencia de Usuario y Diseño Visual

### Flujo de Interacción
```
┌────────────────────────────────────────────────────────┐
│  Cabecera: Misterio 1 de 5 • Botones Anterior / Siguiente│
├────────────────────────────────────────────────────────┤
│  Tarjeta de Meditación: Título, Cita Bíblica y Fruto   │
├────────────────────────────────────────────────────────┤
│  Padrenuestro (Guía y Respuesta comunitaria)           │
├────────────────────────────────────────────────────────┤
│  Decenario Interactivo:                                │
│    [1] [2] [3] [4] [5]  (Fila superior)                │
│    [6] [7] [8] [9] [10] (Fila inferior)                │
│    Progreso: "6 de 10 Avemarías (60%)"                 │
│    [ Botón Pulsador: "Rezar Avemaría (6/10) →" ]       │
├────────────────────────────────────────────────────────┤
│  Al completar la 10ª Avemaría:                         │
│    ✓ Vibración especial                                │
│    ✓ Despliegue animado: Gloria + Jaculatorias         │
│    ✓ [ Botón: "Continuar al Misterio 2 de 5 →" ]       │
└────────────────────────────────────────────────────────┘
```

### Identidad Visual y Estilo
- **Color Litúrgico Dinámico:** Las cuentas activas, bordes y botones primarios adoptan el color litúrgico del día (Verde, Blanco/Oro, Morado o Rojo) respetando el sistema de temas recién implementado.
- **Micro-interacciones:**
  - Animación de rebote suave (`spring`) al tocar cada cuenta.
  - Indicador de cuenta actual con brillo dorado (`SacredGold`) o resplandor del color litúrgico.
  - Barra de progreso horizontal redondeada sincronizada con el decenario.
- **Accesibilidad y Ergonomía:**
  - Área de toque mínima de 48.dp en cada cuenta circular.
  - Botón principal de tamaño generoso (56.dp de altura) para avanzar cómodamente con el pulgar.
  - Compatibilidad completa con el control de tamaño de fuente del usuario (`fontScale`).

---

## 3. Decisiones Técnicas y Arquitectura

```
┌─────────────────────────────────────────────────────────────────┐
│                      RosaryViewModel                            │
│  - currentMysteryIndex: StateFlow<Int> (0..4)                   │
│  - aveMariaCount: StateFlow<Int> (0..10)                        │
│  - countAveMaria() -> incrementa + vibra + verifica final       │
│  - nextMystery() / prevMystery() / setMysteryIndex(i)           │
│  - setAveMariaCount(i) / resetDecade()                          │
└────────────────┬────────────────────────────────────────────────┘
                 │ StateFlow (RosaryUiState)
                 ▼
┌─────────────────────────────────────────────────────────────────┐
│                      RosaryScreen                               │
│  ├── MysteryNavigatorHeader (Botones < Anterior / Siguiente >)  │
│  ├── MysteryMeditationCard (Título, pasaje bíblico, fruto)      │
│  ├── InteractiveRosaryDecade (Cuentas 1..10, pulsador, progreso)│
│  └── CompletedDecadeSection (Gloria, Jaculatoria y botón avance)│
└─────────────────────────────────────────────────────────────────┘
```

### Módulos a Modificar / Reforzar:
1. **`RosaryViewModel.kt`:**
   - Métodos refinados para avanzar y retroceder misterios con reinicio limpio del contador de Avemarías.
   - Disparo háptico optimizado para cuenta individual y cuenta número 10.
2. **`BeadTracker.kt` y `RosaryScreen.kt`:**
   - Integración pulida del decenario interactivo con botones dedicados de cambio de misterio anterior/siguiente.
   - Cuentas circulares con numeración 1..10 y efecto visual de completado.
   - Sección animada (`AnimatedVisibility`) con Gloria y Jaculatorias al llegar a 10.
3. **Tests Unitarios (`ExampleRobolectricTest.kt`):**
   - Prueba unitaria para el ciclo de conteo de Avemarías (0 a 10).
   - Prueba unitaria para la transición entre misterios con reinicio de cuentas.

---

## 4. Plan de Verificación

- **Compilación Android:** Ejecutar `compile_applet` para validar que todas las pantallas y recursos compilen sin errores.
- **Pruebas Unitarias Robolectric:** Ejecutar `gradle :app:testDebugUnitTest` para certificar la máquina de estados del rosario interactivo.
- **Inspección Visual:** Verificar que los botones de misterios anterior/siguiente respondan con fluidez y que las 10 cuentas reaccionen al toque.
