package com.example.data

data class ViaLucisStation(
    val number: Int,
    val romanNumeral: String,
    val title: String,
    val scriptureCitation: String,
    val scriptureText: String,
    val meditation: String,
    val prayer: String
)

object ViaLucisData {

    const val TITULO = "Santo Vía Lúcis"
    const val SUBTITULO = "El Camino de la Luz • De la Resurrección a Pentecostés"

    const val INVOCACION_GUIA = "Verdaderamente ha resucitado el Señor, ¡Aleluya!"
    const val INVOCACION_TODOS = "Como lo había anunciado, ¡Aleluya!"

    const val ORACION_PREPARATORIA = "Señor Jesús, con tu Resurrección triunfaste sobre el pecado y la muerte, e iluminaste el mundo con la luz de tu gloria. Al recorrer este Camino de la Luz contigo, enciende en nuestros corazones la llama de la esperanza pascual, para que seamos testigos gozosos de tu Evangelio en medio del mundo. Amén. ¡Aleluya!"

    const val ORACION_FINAL = "Dios Padre todopoderoso, que por la gloriosa Resurrección de tu Hijo Jesús y la efusión del Espíritu Santo has renovado la faz de la tierra: concede a tu Iglesia caminar siempre en la alegría de la Pascua, para que alcancemos la plenitud de la vida eterna en tu Reino celestial. Por Jesucristo, nuestro Señor resucitado. Amén. ¡Aleluya!"

    val stations: List<ViaLucisStation> = listOf(
        ViaLucisStation(
            number = 1,
            romanNumeral = "I",
            title = "Jesús resucita de entre los muertos",
            scriptureCitation = "Mt 28, 5-6",
            scriptureText = "El ángel dijo a las mujeres: «No temáis vosotras; sé que buscáis a Jesús, el crucificado. No está aquí: ¡ha resucitado, como había dicho! Venid a ver el sitio donde yacía».",
            meditation = "El sepulcro está abierto y la losa removida. Cristo rompió las ataduras de la muerte e inauguró para la humanidad una vida nueva que ya no tiene ocaso.",
            prayer = "Señor resucitado, resucita nuestra esperanza cuando la tristeza o la incertidumbre oscurezcan nuestro camino. ¡Aleluya! Amén."
        ),
        ViaLucisStation(
            number = 2,
            romanNumeral = "II",
            title = "Los discípulos encuentran el sepulcro vacío",
            scriptureCitation = "Jn 20, 3-8",
            scriptureText = "Salieron Pedro y el otro discípulo camino del sepulcro... El otro discípulo corrió más aprisa... vio los lienzos en el suelo y el sudario doblado en un lugar aparte. Vio y creyó.",
            meditation = "Pedro y Juan corren conmovidos. El sudario doblado y los lienzos ordenados testimonian que el cuerpo no fue robado: Cristo vive glorioso.",
            prayer = "Danos, Señor, ojos limpios y corazón despierto para reconocer los signos de tu presencia viva en cada acontecimiento de nuestra existencia. Amén."
        ),
        ViaLucisStation(
            number = 3,
            romanNumeral = "III",
            title = "Jesús resucitado se aparece a María Magdalena",
            scriptureCitation = "Jn 20, 15-18",
            scriptureText = "Jesús le dice: «¡María!» Ella se volvió y le dijo: «¡Rabbuní!», que significa «¡Maestro mío!»... María Magdalena fue y anunció a los discípulos: «¡He visto al Señor!»",
            meditation = "Una sola palabra pronunciada por el Maestro basta para enjugar el llanto de Magdalena. Ella se convierte en la apóstol de los apóstoles, portadora del gozo pascual.",
            prayer = "Jesús mío, llámame también por mi nombre cuando me sienta desolado, y dame la audacia de anunciar tu victoria a mis hermanos. Amén."
        ),
        ViaLucisStation(
            number = 4,
            romanNumeral = "IV",
            title = "Jesús en el camino de Emaús",
            scriptureCitation = "Lc 24, 15. 27",
            scriptureText = "Mientras conversaban y discutían, Jesús en persona se acercó y se puso a caminar con ellos... Y comenzando por Moisés y siguiendo por los profetas, les explicó lo que se refería a él en todas las Escrituras.",
            meditation = "Jesús se hace caminante con dos discípulos decepcionados. Con infinita paciencia escucha sus frustraciones y les revela el sentido salvífico de la Cruz.",
            prayer = "Quédate con nosotros, Señor, cuando caiga la tarde del desánimo. Haz que nuestra alma arda al escuchar tu santa Palabra. Amén."
        ),
        ViaLucisStation(
            number = 5,
            romanNumeral = "V",
            title = "Jesús es reconocido al partir el pan",
            scriptureCitation = "Lc 24, 30-31",
            scriptureText = "Sentado a la mesa con ellos, tomó el pan, pronunció la bendición, lo partió y se lo dio. A ellos se les abrieron los ojos y lo reconocieron, pero él desapareció de su vista.",
            meditation = "En el gesto eucarístico de la fracción del pan, los ojos se iluminan. En la Santa Misa, Cristo resucitado se nos dona en cuerpo, sangre, alma y divinidad.",
            prayer = "Oh Divino Huésped, que te reconozcamos siempre en el Santísimo Sacramento del Altar y en el pan compartido con el necesitado. Amén."
        ),
        ViaLucisStation(
            number = 6,
            romanNumeral = "VI",
            title = "Jesús resucitado se aparece a los discípulos reunidos",
            scriptureCitation = "Lc 24, 36. 38-40",
            scriptureText = "Jesús se presentó en medio de ellos y les dijo: «Paz a vosotros»... «Mirad mis manos y mis pies: soy yo mismo; palpadme y daos cuenta de que un espíritu no tiene carne y huesos como veis que yo tengo».",
            meditation = "El Señor disipa los temores y la incredulidad de los Once. Muestra las llagas gloriosas de sus manos y pies como prueba de su amor redentor y nos entrega su paz.",
            prayer = "Príncipe de la Paz, entra en nuestros hogares y comunidades cerradas por el miedo, e infúndenos tu serenidad y consuelo. Amén."
        ),
        ViaLucisStation(
            number = 7,
            romanNumeral = "VII",
            title = "Jesús confiere el poder de perdonar los pecados",
            scriptureCitation = "Jn 20, 21-23",
            scriptureText = "Jesús les dijo de nuevo: «Paz a vosotros. Como el Padre me ha enviado, así también os envío yo». Dicho esto, sopló sobre ellos y les dijo: «Recibid el Espíritu Santo; a quienes les perdonéis los pecados, les quedan perdonados».",
            meditation = "El primer don del Resucitado es el sacramento de la Reconciliación. Del soplo vivificante de Cristo nace el perdón que sana y renueva a las almas caídas.",
            prayer = "Te bendecimos, Señor, por el sacramento de la confesión. Haz que acudamos siempre con corazón humilde a recibir tu misericordia sanadora. Amén."
        ),
        ViaLucisStation(
            number = 8,
            romanNumeral = "VIII",
            title = "Jesús confirma la fe de Tomás",
            scriptureCitation = "Jn 20, 27-29",
            scriptureText = "Luego dijo a Tomás: «Acerca aquí tu dedo y mira mis manos; extiende tu mano y métela en mi costado; y no seas incrédulo, sino creyente». Tomás contestó: «¡Señor mío y Dios mío!»",
            meditation = "Tomás dudaba, pero Jesús no lo rechaza; viene al encuentro de su fragilidad. De la herida del costado brota la más sublime profesión de fe.",
            prayer = "¡Señor mío y Dios mío! Aumenta nuestra fe para creer firmemente en Ti, aun cuando no podamos verte con los ojos corporales. Amén."
        ),
        ViaLucisStation(
            number = 9,
            romanNumeral = "IX",
            title = "Jesús se aparece junto al lago de Tiberíades",
            scriptureCitation = "Jn 21, 4-6",
            scriptureText = "Estaba ya amaneciendo, cuando Jesús se presentó en la orilla... Les dijo: «Echad la red a la derecha de la barca y encontraréis». La echaron, y ya no podían sacarla por la gran cantidad de peces.",
            meditation = "Tras una noche estéril, la obediencia a la palabra de Jesús produce una pesca milagrosa. Sin Él nada podemos hacer; con Él la misión es fecunda.",
            prayer = "Danos docilidad, Jesús, para echar nuestras redes según tu mandato y no según nuestras solas fuerzas humanas. Amén."
        ),
        ViaLucisStation(
            number = 10,
            romanNumeral = "X",
            title = "Jesús confía a Pedro el primado sobre su rebaño",
            scriptureCitation = "Jn 21, 15-17",
            scriptureText = "Jesús le preguntó por tercera vez: «Simón, hijo de Juan, ¿me amas?» Pedro se entristeció... y le dijo: «Señor, tú lo sabes todo; tú sabes que te amo». Jesús le dijo: «Apacienta mis ovejas».",
            meditation = "Pedro repara su triple negación con una triple confesión de amor humilde. El Buen Pastor encomienda a Pedro y a sus sucesores cuidar de todo el rebaño de los creyentes.",
            prayer = "Te pedimos, Señor, por el Papa y los pastores de tu Iglesia: sostenlos en la caridad y haz que conduzcan a tu pueblo hacia los pastos de la vida eterna. Amén."
        ),
        ViaLucisStation(
            number = 11,
            romanNumeral = "XI",
            title = "Jesús envía a los discípulos al mundo entero",
            scriptureCitation = "Mt 28, 18-20",
            scriptureText = "Jesús se acercó y les dijo: «Se me ha dado todo poder en el cielo y en la tierra. Id, pues, y haced discípulos a todos los pueblos, bautizándolos en el nombre del Padre y del Hijo y del Espíritu Santo... Y sabed que yo estoy con vosotros todos los días, hasta el final de los tiempos».",
            meditation = "El mandato misionero no tiene fronteras. Todo bautizado es llamado a ser luz de las naciones, sostenido por la promesa inquebrantable de la compañía constante de Cristo.",
            prayer = "Aviva en nosotros el ardor misionero para ser heraldos de tu verdad y testigos de tu amor salvador en nuestra familia y trabajo. Amén."
        ),
        ViaLucisStation(
            number = 12,
            romanNumeral = "XII",
            title = "Jesús asciende a los cielos",
            scriptureCitation = "Hch 1, 9-11",
            scriptureText = "Dicho esto, a la vista de ellos, fue elevado al cielo, hasta que una nube se lo quitó de la vista. Dos hombres vestidos de blanco les dijeron: «Galileos, ¿qué hacéis ahí plantados mirando al cielo? El mismo Jesús volverá de la misma manera».",
            meditation = "Jesús entra en la gloria del Padre, abriendo el camino para la humanidad redimida. No nos abandona, sino que intercede permanentemente por nosotros ante el trono de Dios.",
            prayer = "Que nuestros corazones, Señor, habiten ya contigo en los cielos, anhelando tu gloriosa venida final. Amén. ¡Aleluya!"
        ),
        ViaLucisStation(
            number = 13,
            romanNumeral = "XIII",
            title = "Los apóstoles con María esperan el Espíritu Santo",
            scriptureCitation = "Hch 1, 14",
            scriptureText = "Todos ellos perseveraban unánimes en la oración, junto con algunas mujeres y María, la madre de Jesús, y con los hermanos de él.",
            meditation = "El Cenáculo se convierte en cuna de la Iglesia naciente. María, Madre de los creyentes, enseña a los apóstoles la oración vigilante y confiada que atrae los dones celestiales.",
            prayer = "Santa María, enséñanos a orar con constancia e intimidad, abriendo nuestro corazón a la gracia del Divino Consolador. Amén."
        ),
        ViaLucisStation(
            number = 14,
            romanNumeral = "XIV",
            title = "Jesús envía el Espíritu Santo en Pentecostés",
            scriptureCitation = "Hch 2, 1-4",
            scriptureText = "Al cumplirse el día de Pentecostés, estaban todos juntos... De repente vino del cielo un ruido como de viento impetuoso... Se les aparecieron lenguas como de fuego... y se llenaron todos del Espíritu Santo.",
            meditation = "La promesa del Padre se cumple con poder. El Espíritu Santo desciende con fuego y renueva los corazones, derribando los miedos y enviando a la Iglesia a transformar el mundo.",
            prayer = "¡Ven, Espíritu Santo! Llena los corazones de tus fieles y enciende en ellos el fuego de tu amor. Envía tu Espíritu y renovarás la faz de la tierra. Amén. ¡Aleluya!"
        )
    )
}
