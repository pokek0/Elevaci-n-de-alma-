package com.example.data

data class ViacrucisStation(
    val number: Int,
    val romanNumeral: String,
    val title: String,
    val scriptureCitation: String,
    val scriptureText: String,
    val meditation: String,
    val prayer: String
)

object ViacrucisData {

    const val TITULO = "Santo Vía Crucis"
    const val SUBTITULO = "El Camino de la Cruz • 14 Estaciones y Resurrección"

    const val ORACION_PREPARATORIA = "Señor mío Jesucristo, que con tu muerte y resurrección nos diste la vida: mira con compasión a este pecador que desea meditar en el doloroso camino que recorriste por mi salvación. Concédeme la gracia de arrepentirme sinceramente de mis culpas y de seguir tus huellas con fidelidad y amor. Madre Dolorosa, acompáñame en este Santo Vía Crucis."

    const val INVOCACION_GUIA = "Te adoramos, oh Cristo, y te bendecimos."
    const val INVOCACION_TODOS = "Pues por tu santa Cruz redimiste al mundo."

    const val JACULATORIA_FINAL_ESTACION = "Señor, pequé; ten piedad y misericordia de mí. Pecamos y nos pesa; ten misericordia de nosotros, que por nosotros moriste en la Cruz."

    const val ORACION_FINAL = "Te damos gracias, Padre celestial, porque por la Pasión, Muerte y Resurrección de tu Hijo amado nos has devuelto la vida. Haz que, abrazando cada día nuestra cruz con fe y esperanza, participemos también de la gloria celestial de su Reino, donde vives y reinas por los siglos de los siglos. Amén."

    val stations: List<ViacrucisStation> = listOf(
        ViacrucisStation(
            number = 1,
            romanNumeral = "I",
            title = "Jesús es condenado a muerte",
            scriptureCitation = "Mt 27, 22-26",
            scriptureText = "Pilato les preguntó: «¿Y qué haré con Jesús, llamado el Mesías?» Todos contestaron: «¡Que sea crucificado!» Pilato entonces soltó a Barrabás, y a Jesús lo hizo azotar y lo entregó para ser crucificado.",
            meditation = "El Inocente por excelencia calla ante un juez cobarde. Con humilde sumisión, Jesús asume la sentencia que debió caer sobre nuestros pecados.",
            prayer = "Señor Jesús, concédeme la pureza de conciencia y la valentía para no condenar a mi prójimo ni ceder ante las falsas presiones del mundo. Amén."
        ),
        ViacrucisStation(
            number = 2,
            romanNumeral = "II",
            title = "Jesús carga con la Cruz",
            scriptureCitation = "Jn 19, 16-17",
            scriptureText = "Se apoderaron, pues, de Jesús, y cargando él mismo con la cruz, salió hacia el lugar llamado Calvario, que en hebreo se dice Gólgota.",
            meditation = "El madero tosco y pesado es puesto sobre los hombros llagados del Salvador. No es sólo la madera, es la carga de nuestras rebeldías lo que Jesús abraza por amor.",
            prayer = "Jesús mío, dame fuerzas para abrazar la cruz de cada día y seguirte con paciencia y generosidad inquebrantable. Amén."
        ),
        ViacrucisStation(
            number = 3,
            romanNumeral = "III",
            title = "Jesús cae por primera vez",
            scriptureCitation = "Is 53, 4-5",
            scriptureText = "Él soportó nuestros sufrimientos y aguantó nuestros dolores; fue traspasado por nuestras rebeliones y triturado por nuestros crímenes.",
            meditation = "Agotado por los azotes, el hambre y la corona de espinas, Jesús cae bajo el peso abrumador del madero. Pero el amor a los pecadores lo impulsa a levantarse de nuevo.",
            prayer = "Oh buen Jesús, cuando la debilidad me haga tropezar en el camino del bien, levántame con tu mano poderosa y renueva mi fe. Amén."
        ),
        ViacrucisStation(
            number = 4,
            romanNumeral = "IV",
            title = "Jesús encuentra a su afligida Madre",
            scriptureCitation = "Lc 2, 34-35",
            scriptureText = "Simeón los bendijo y dijo a María, su madre: «Este niño está puesto para ruina y resurrección de muchos... y a ti misma una espada te atravesará el alma».",
            meditation = "Dos miradas que se cruzan en medio de la multitud hostil: el Hijo sufriente y la Madre Inmaculada unidos en un mismo cáliz de dolor salvífico.",
            prayer = "Virgen Santa, Madre del Amor Hermoso, intercede por mí para que nunca me separe de tu Hijo y consuele a los que sufren en mi entorno. Amén."
        ),
        ViacrucisStation(
            number = 5,
            romanNumeral = "V",
            title = "El Cireneo ayuda a Jesús a llevar la Cruz",
            scriptureCitation = "Lc 23, 26",
            scriptureText = "Al salir, encontraron a un hombre de Cirene, llamado Simón, y lo obligaron a llevar la cruz detrás de Jesús.",
            meditation = "Simón de Cirene es obligado al principio, pero llevar la cruz del Maestro transforma su corazón. Jesús no desdeña la ayuda del ser humano para completar su obra.",
            prayer = "Concédeme, Señor, ser generoso cirineo para mis hermanos enfermos, desamparados y agobiados por sus pesares. Amén."
        ),
        ViacrucisStation(
            number = 6,
            romanNumeral = "VI",
            title = "La Verónica enjuga el rostro de Jesús",
            scriptureCitation = "Sal 27, 8-9; Is 52, 14",
            scriptureText = "Oigo en mi corazón: «Buscad mi rostro». Tu rostro buscaré, Señor, no me escondas tu rostro.",
            meditation = "Una mujer piadosa desafía a los soldados para secar el sudor y la sangre del Redentor. Jesús deja la huella de sus santas facciones impresa en aquel lienzo.",
            prayer = "Jesús bendito, graba en mi corazón tu sagrada imagen para que en cada hermano necesitado reconozca tu rostro adorable. Amén."
        ),
        ViacrucisStation(
            number = 7,
            romanNumeral = "VII",
            title = "Jesús cae por segunda vez",
            scriptureCitation = "Lam 3, 27-30",
            scriptureText = "Bueno es esperar en silencio la salvación del Señor... Ponga su boca en el polvo, que tal vez haya esperanza.",
            meditation = "Por segunda vez cae en tierra la Majestad infinita. Es la lección contra mi orgullo y soberbia, llamándome a una profunda y verdadera humildad.",
            prayer = "Perdón, Señor, por mis reiteradas faltas y recaídas. Dame la gracia de una perseverancia santa hasta el fin. Amén."
        ),
        ViacrucisStation(
            number = 8,
            romanNumeral = "VIII",
            title = "Jesús consuela a las mujeres de Jerusalén",
            scriptureCitation = "Lc 23, 27-28",
            scriptureText = "Lo seguía un gran gentío del pueblo, y de mujeres que se golpeaban el pecho y se lamentaban por él. Jesús se volvió hacia ellas y les dijo: «Hijas de Jerusalén, no lloréis por mí, llorad más bien por vosotras y por vuestros hijos».",
            meditation = "Jesús olvida sus propios tormentos para advertir con caridad pastoral sobre el destino de los pecadores impenitentes. Llorar por los pecados es la verdadera compasión.",
            prayer = "Concédeme, Señor, lágrimas de verdadero dolor por mis pecados, que fueron la causa de tu Pasión dolorosa. Amén."
        ),
        ViacrucisStation(
            number = 9,
            romanNumeral = "IX",
            title = "Jesús cae por tercera vez",
            scriptureCitation = "Sal 22, 15-16",
            scriptureText = "Estoy como agua que se derrama, todos mis huesos se descoyuntan... Mi garganta está seca como una teja y la lengua se me pega al paladar.",
            meditation = "A los pies del monte Calvario, Jesús cae por tercera vez, casi exánime. Demuestra hasta qué extremo descendió para levantar a la humanidad caída.",
            prayer = "Oh Salvador mío, que no me deje vencer jamás por el desánimo ni la desesperanza, confiando siempre en tu infinita misericordia. Amén."
        ),
        ViacrucisStation(
            number = 10,
            romanNumeral = "X",
            title = "Jesús es despojado de sus vestiduras",
            scriptureCitation = "Jn 19, 23-24",
            scriptureText = "Los soldados tomaron su ropa e hicieron cuatro partes, una para cada uno... Y echaron a suertes su túnica que era sin costura, tejida de una sola pieza.",
            meditation = "Le arrancan las ropas abriendo nuevamente sus santas llagas. Jesús soporta la vergüenza del despojo para vestirnos con el manto blanco de la gracia.",
            prayer = "Despójame, Jesús, del hombre viejo con sus pasiones y soberbias, y revísteme de tu humildad, pureza y amor fraterno. Amén."
        ),
        ViacrucisStation(
            number = 11,
            romanNumeral = "XI",
            title = "Jesús es clavado en la Cruz",
            scriptureCitation = "Lc 23, 33-34",
            scriptureText = "Llegados al lugar llamado Calvario, lo crucificaron allí a él y a los malhechores, uno a la derecha y otro a la izquierda. Jesús decía: «Padre, perdónalos, porque no saben lo que hacen».",
            meditation = "Hierros afilados perforan manos y pies bienhechores. Desde el trono de la Cruz brota la oración más sublime: el perdón a los enemigos.",
            prayer = "Señor crucificado, clava en tu Cruz mi corazón pecador, y enséñame a perdonar a quienes me ofenden como Tú me has perdonado. Amén."
        ),
        ViacrucisStation(
            number = 12,
            romanNumeral = "XII",
            title = "Jesús muere en la Cruz",
            scriptureCitation = "Jn 19, 28-30; Lc 23, 46",
            scriptureText = "Jesús, sabiendo que todo estaba cumplido, dijo: «Tengo sed»... Y clamando con voz potente: «Padre, en tus manos encomiendo mi espíritu». E inclinando la cabeza, entregó el espíritu.",
            meditation = "Silencio sagrado. La creación tiembla y el velo del Templo se rasga. El Cordero de Dios ha inmolado su vida por el rescate de todos los hombres.",
            prayer = "¡Oh buen Jesús! Por tu agonía y santa muerte en la Cruz, concédeme la gracia suprema de una santa muerte en tu paz y amistad. Amén."
        ),
        ViacrucisStation(
            number = 13,
            romanNumeral = "XIII",
            title = "Jesús es bajado de la Cruz y puesto en brazos de María",
            scriptureCitation = "Jn 19, 38; Mc 15, 43-46",
            scriptureText = "José de Arimatea pidió el cuerpo de Jesús a Pilato. Fue y lo descolgó. Y lo colocó en brazos de su Santa Madre.",
            meditation = "La Piedad: María recibe en su regazo el cuerpo inerte de su Hijo. La espada prometida por Simeón atraviesa de lleno su alma, permaneciendo en pie junto a la ofrenda.",
            prayer = "Madre Dolorosa, acógeme bajo tu manto virginal en las horas de tribulación y acompáñame hasta el umbral del cielo. Amén."
        ),
        ViacrucisStation(
            number = 14,
            romanNumeral = "XIV",
            title = "Jesús es colocado en el Santo Sepulcro",
            scriptureCitation = "Jn 19, 41-42",
            scriptureText = "En el lugar donde fue crucificado había un huerto, y en el huerto un sepulcro nuevo en el que nadie había sido sepultado todavía. Allí pusieron a Jesús.",
            meditation = "Cierran con una gran piedra el sepulcro. En el silencio del sábado santo, la semilla divina reposa en la tierra esperando la alborada gloriosa de la Pascua.",
            prayer = "Señor Jesús, haz que permanezca fiel en la oración y la esperanza vigilante, esperando el triunfo eterno de tu Resurrección. Amén."
        ),
        ViacrucisStation(
            number = 15,
            romanNumeral = "XV",
            title = "La Gloriosa Resurrección del Señor",
            scriptureCitation = "Mt 28, 5-6",
            scriptureText = "El ángel dijo a las mujeres: «No temáis vosotras, sé que buscáis a Jesús, el crucificado. No está aquí: ¡ha resucitado, como había dicho! Venid a ver el sitio donde yacía».",
            meditation = "¡La muerte ha sido vencida para siempre! Cristo vive y reina glorioso, abriéndonos las puertas del Paraíso. ¡Aleluya!",
            prayer = "Señor resucitado, llénanos con la alegría gozosa de tu Pascua y danos vida nueva en tu Espíritu Santo. ¡Amén, aleluya!"
        )
    )
}
