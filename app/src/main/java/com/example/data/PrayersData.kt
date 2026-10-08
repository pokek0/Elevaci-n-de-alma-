package com.example.data

import com.example.data.model.MysteryItem
import com.example.data.model.MysteryType

object PrayersData {

    val mysteries: Map<MysteryType, List<MysteryItem>> = mapOf(
        MysteryType.GOZO to listOf(
            MysteryItem(
                number = 1,
                title = "La Encarnación del Hijo de Dios",
                scripture = "Lucas 1, 26-38",
                fruit = "La humildad",
                meditation = "El ángel Gabriel entra donde está María y le anuncia que será la Madre del Salvador. María responde: 'He aquí la esclava del Señor, hágase en mí según tu palabra'."
            ),
            MysteryItem(
                number = 2,
                title = "La Visitación de Nuestra Señora a su prima Santa Isabel",
                scripture = "Lucas 1, 39-56",
                fruit = "La caridad fraterna",
                meditation = "María va presurosa a la montaña de Judea para servir a su prima Isabel, quien llena del Espíritu Santo exclama: '¡Bendita tú entre las mujeres y bendito el fruto de tu vientre!'."
            ),
            MysteryItem(
                number = 3,
                title = "El Nacimiento del Hijo de Dios en Belén",
                scripture = "Lucas 2, 1-20",
                fruit = "El desapego y la pobreza de espíritu",
                meditation = "En la pobreza de un pesebre nace el Salvador del mundo. Los ángeles cantan: 'Gloria a Dios en el cielo y en la tierra paz a los hombres de buena voluntad'."
            ),
            MysteryItem(
                number = 4,
                title = "La Presentación del Niño Jesús en el Templo",
                scripture = "Lucas 2, 22-40",
                fruit = "La pureza y la obediencia",
                meditation = "María y José presentan al Niño en el templo de Jerusalén. El anciano Simeón lo toma en brazos y alaba a Dios, profetizando una espada que traspasará el alma de María."
            ),
            MysteryItem(
                number = 5,
                title = "El Niño Jesús perdido y hallado en el Templo",
                scripture = "Lucas 2, 41-52",
                fruit = "La búsqueda constante de Dios",
                meditation = "Tras tres días de angustiosa búsqueda, sus padres lo encuentran en medio de los doctores de la Ley, escuchándolos y haciéndoles preguntas."
            )
        ),
        MysteryType.LUZ to listOf(
            MysteryItem(
                number = 1,
                title = "El Bautismo de Jesús en el Río Jordán",
                scripture = "Mateo 3, 13-17",
                fruit = "La fidelidad a nuestras promesas bautismales",
                meditation = "Jesús desciende a las aguas del Jordán. El Espíritu Santo desciende como paloma y la voz del Padre proclama: 'Este es mi Hijo amado, en quien me complazco'."
            ),
            MysteryItem(
                number = 2,
                title = "La Autorrevelación de Jesús en las Bodas de Caná",
                scripture = "Juan 2, 1-12",
                fruit = "La confianza en la intercesión de María",
                meditation = "A petición de María, quien dice a los sirvientes: 'Hagan lo que Él les diga', Jesús realiza su primer signo milagroso transformando el agua en vino excelente."
            ),
            MysteryItem(
                number = 3,
                title = "El Anuncio del Reino de Dios y la llamada a la Conversión",
                scripture = "Marcos 1, 14-15",
                fruit = "La conversión sincera del corazón",
                meditation = "Jesús proclama: 'El tiempo se ha cumplido y el Reino de Dios está cerca; conviértanse y crean en el Evangelio', perdonando los pecados y sanando las heridas."
            ),
            MysteryItem(
                number = 4,
                title = "La Transfiguración del Señor en el Monte Tabor",
                scripture = "Lucas 9, 28-36",
                fruit = "El deseo del cielo y la oración contemplativa",
                meditation = "El rostro de Jesús resplandece como el sol y sus vestiduras se vuelven blancas y fulgurantes. De la nube se oye: 'Este es mi Hijo, el Elegido; escúchenlo'."
            ),
            MysteryItem(
                number = 5,
                title = "La Institución de la Santísima Eucaristía",
                scripture = "Mateo 26, 26-29",
                fruit = "El amor ferviente a la Eucaristía",
                meditation = "En la Última Cena, Jesús toma el pan y el vino y nos da su propio Cuerpo y Sangre como alimento de vida eterna: 'Hagan esto en memoria mía'."
            )
        ),
        MysteryType.DOLOR to listOf(
            MysteryItem(
                number = 1,
                title = "La Oración y Agonía de Jesús en el Huerto de Getsemaní",
                scripture = "Marcos 14, 32-42",
                fruit = "La conformidad con la voluntad de Dios",
                meditation = "Jesús experimenta profunda angustia y suda gotas de sangre, orando: 'Padre, si es posible, aparta de mí este cáliz; pero no se haga mi voluntad, sino la tuya'."
            ),
            MysteryItem(
                number = 2,
                title = "La Flagelación de Nuestro Señor Jesucristo",
                scripture = "Marcos 15, 1-15",
                fruit = "La mortificación de los sentidos",
                meditation = "Atado a la columna, el Salvador es despiadadamente azotado por nuestros pecados. 'Fue traspasado por nuestras rebeliones, triturado por nuestros crímenes'."
            ),
            MysteryItem(
                number = 3,
                title = "La Coronación de Espinas",
                scripture = "Marcos 15, 16-20",
                fruit = "El valor moral y la humildad de mente",
                meditation = "Los soldados tejen una corona de espinas y la hunden en su venerable cabeza; le colocan una caña y una túnica púrpura burlándose de su realeza."
            ),
            MysteryItem(
                number = 4,
                title = "Jesús con la Cruz a Cuestas camino al Calvario",
                scripture = "Marcos 15, 21-28",
                fruit = "La paciencia para sobrellevar nuestras cruces",
                meditation = "Cargando el pesado madero del patíbulo sobre sus hombros llagados, Jesús sube al Calvario y en el camino se encuentra con su afligida Madre."
            ),
            MysteryItem(
                number = 5,
                title = "La Crucifixión y Muerte de Nuestro Señor Jesucristo",
                scripture = "Lucas 23, 33-46",
                fruit = "El perdón a los enemigos y la salvación de las almas",
                meditation = "Clavado en la cruz, Jesús perdona a sus verdugos, entrega a María como Madre al discípulo amado y diciendo 'Todo está cumplido', entrega su Espíritu."
            )
        ),
        MysteryType.GLORIA to listOf(
            MysteryItem(
                number = 1,
                title = "La Gloriosa Resurrección del Hijo de Dios",
                scripture = "Mateo 28, 1-8",
                fruit = "La fe viva y renovada",
                meditation = "Al tercer día, Jesús vence a la muerte y sale triunfante del sepulcro. '¿Por qué buscan entre los muertos al que vive? No está aquí, ¡ha resucitado!'."
            ),
            MysteryItem(
                number = 2,
                title = "La Admirable Ascensión de Jesús a los Cielos",
                scripture = "Hechos 1, 6-11",
                fruit = "La esperanza cristiana y la alegría celestial",
                meditation = "Bendiciendo a sus apóstoles, el Señor se eleva a la vista de ellos y se sienta a la derecha del Padre, prometiendo estar con nosotros hasta el fin del mundo."
            ),
            MysteryItem(
                number = 3,
                title = "La Venida del Espíritu Santo en Pentecostés",
                scripture = "Hechos 2, 1-13",
                fruit = "El celo apostólico y los dones del Espíritu",
                meditation = "Reunidos en oración con la Virgen María, lenguas de fuego descienden sobre ellos, llenándolos de valentía para anunciar el Evangelio a todas las naciones."
            ),
            MysteryItem(
                number = 4,
                title = "La Asunción de la Santísima Virgen María al Cielo",
                scripture = "Apocalipsis 12, 1",
                fruit = "La gracia de una santa muerte",
                meditation = "Terminado el curso de su vida terrena, la Madre de Dios es asunta en cuerpo y alma a la gloria celestial por el amor infinito de su Hijo."
            ),
            MysteryItem(
                number = 5,
                title = "La Coronación de la Santísima Virgen como Reina Universal",
                scripture = "Apocalipsis 12, 1-6",
                fruit = "La perseverancia final y la devoción a María",
                meditation = "María es coronada por la Santísima Trinidad como Reina de los cielos y de la tierra, de los ángeles y de los santos, Madre e Intercesora nuestra."
            )
        )
    )

    // ORACIONES INICIALES DEL FOLLETO (Pág. 13)
    const val SENAL_CRUZ_GUIA = "Por la señal † de la Santa Cruz, de nuestros † enemigos líbranos, † Señor Dios nuestro. En el nombre del Padre, † y del Hijo y del Espíritu Santo. Amén."

    const val ACTO_CONTRICION_GUIA = "Señor mío Jesucristo."
    const val ACTO_CONTRICION_TODOS = "Dios y Hombre verdadero, me pesa de todo corazón de haber pecado, porque he merecido el infierno y perdido el cielo, y sobre todo, porque te ofendí a Ti, que eres Bondad infinita, a quien amo sobre todas las cosas. Propongo firmemente, con tu gracia, enmendarme y alejarme de las ocasiones de pecar, confesarme y cumplir la penitencia. Confío me perdonarás por tu infinita misericordia. Amén."

    // INVOCACIONES DE PREPARACIÓN (Pág. 13)
    const val INVOCACION_LABIOS_GUIA = "Abre, Señor, mis labios."
    const val INVOCACION_LABIOS_TODOS = "Para alabar tu nombre, y el de tu Santa Madre."

    const val INVOCACION_GLORIA_GUIA = "Gloria al Padre, al Hijo y al Espíritu Santo."
    const val INVOCACION_GLORIA_TODOS = "Como era en el principio, ahora y siempre por los siglos de los siglos. Amén."

    // ORACIONES DEL MISTERIO (Pág. 14)
    const val PADRENUESTRO_GUIA = "Padre nuestro, que estás en el cielo, santificado sea tu Nombre; venga a nosotros tu Reino; hágase tu voluntad en la tierra como en el cielo."
    const val PADRENUESTRO_TODOS = "Danos hoy nuestro pan de cada día; perdona nuestras ofensas, como también nosotros perdonamos a los que nos ofenden; no nos dejes caer en la tentación, y líbranos del mal. Amén."

    const val AVEMARIA_GUIA = "Dios te salve, María, llena eres de gracia, el Señor es contigo; bendita tú eres entre todas las mujeres, y bendito es el fruto de tu vientre, Jesús."
    const val AVEMARIA_TODOS = "Santa María, Madre de Dios, ruega por nosotros, pecadores, ahora y en la hora de nuestra muerte. Amén."

    const val GLORIA_GUIA = "Gloria al Padre, y al Hijo, y al Espíritu Santo."
    const val GLORIA_TODOS = "Como era en el principio, ahora y siempre, por los siglos de los siglos. Amén."

    const val JACULATORIA_MADRE_GUIA = "María, Madre de gracia, Madre de misericordia."
    const val JACULATORIA_MADRE_TODOS = "En la vida y en la muerte, ampárame, gran Señora."

    const val JACULATORIA_FATIMA = "¡Oh Jesús mío! Perdona nuestros pecados, presérvanos del fuego del infierno, lleva al cielo a todas las almas especialmente a las más necesitadas de tu misericordia."

    // ORACIONES FINALES DEL FOLLETO (Pág. 15)
    const val SOBERANO_SANTUARIO_GUIA = "Oh Soberano Santuario, Sagrario del Verbo Eterno."
    const val SOBERANO_SANTUARIO_TODOS = "Libra, Virgen, del infierno a los que rezan tu Rosario."

    const val EMPERATRIZ_PODEROSA_GUIA = "Emperatriz poderosa de los mortales consuelo."
    const val EMPERATRIZ_PODEROSA_TODOS = "Ábrenos, Virgen, el cielo con una muerte dichosa."

    const val PADRENUESTRO_PAPA_TITULO = "Por las intenciones del Santo Padre"
    const val PADRENUESTRO_PAPA_DESCRIPCION = "Se reza un Padrenuestro completo por las intenciones del Santo Padre el Papa."

    // LAS TRES AVEMARÍAS DE PUREZA (Pág. 15)
    val PUREZA_MARIA = listOf(
        Pair(
            "Dios te salve, María Santísima, Hija de Dios Padre, Virgen purísima y castísima antes del parto, en tus manos encomiendo mi fe para que la alumbres, llena eres de gracia...",
            "Santa María, Madre de Dios, ruega por nosotros..."
        ),
        Pair(
            "Dios te salve, María, Madre de Dios Hijo, Virgen purísima y castísima en el parto, en tus manos encomiendo mi esperanza para que la alientes, llena eres de gracia...",
            "Santa María, Madre de Dios, ruega por nosotros..."
        ),
        Pair(
            "Dios te salve, María, Esposa del Espíritu Santo, Virgen purísima y castísima después del parto, en tus manos encomiendo mi caridad para que la inflames, llena eres de gracia...",
            "Santa María, Madre de Dios, ruega por nosotros..."
        )
    )

    // LA SALVE (Pág. 15)
    const val SALVE_GUIA = "Dios te salve, María, Templo, Trono y Sagrario de la Santísima Trinidad, Virgen concebida sin la culpa original, Dios te salve."
    const val SALVE_TODOS = "Reina y Madre de misericordia, vida, dulzura y esperanza nuestra, Dios te salve. A ti llamamos los desterrados hijos de Eva, a ti suspiramos, gimiendo y llorando, en este valle de lágrimas. Ea, pues, Señora, abogada nuestra, vuelve a nosotros esos tus ojos misericordiosos, y después de este destierro muéstranos a Jesús, fruto bendito de tu vientre. ¡Oh clemente, oh piadosa, oh dulce Virgen María! Ruega por nosotros, Santa Madre de Dios, para que seamos dignos de alcanzar las promesas de nuestro Señor Jesucristo. Amén."

    // LETANÍAS LAURETANAS (Pág. 16)
    val LETANIAS = listOf(
        "Santa María" to "Ruega por nosotros",
        "Santa Madre de Dios" to "Ruega por nosotros",
        "Santa Virgen de las vírgenes" to "Ruega por nosotros",
        "Madre de Jesucristo" to "Ruega por nosotros",
        "Madre de la divina gracia" to "Ruega por nosotros",
        "Madre purísima" to "Ruega por nosotros",
        "Madre castísima" to "Ruega por nosotros",
        "Madre intacta" to "Ruega por nosotros",
        "Madre sin mancha" to "Ruega por nosotros",
        "Madre amable" to "Ruega por nosotros",
        "Madre del buen consejo" to "Ruega por nosotros",
        "Madre del Creador" to "Ruega por nosotros",
        "Madre del Salvador" to "Ruega por nosotros",
        "Madre de la Iglesia" to "Ruega por nosotros",
        "Virgen prudentísima" to "Ruega por nosotros",
        "Virgen venerable" to "Ruega por nosotros",
        "Virgen digna de alabanza" to "Ruega por nosotros",
        "Virgen poderosa" to "Ruega por nosotros",
        "Virgen misericordiosa" to "Ruega por nosotros",
        "Virgen fiel" to "Ruega por nosotros",
        "Espejo de justicia" to "Ruega por nosotros",
        "Trono de la Sabiduría" to "Ruega por nosotros",
        "Causa de nuestra alegría" to "Ruega por nosotros",
        "Vaso espiritual" to "Ruega por nosotros",
        "Vaso honorable" to "Ruega por nosotros",
        "Rosa Mística" to "Ruega por nosotros",
        "Torre de David" to "Ruega por nosotros",
        "Torre de Marfil" to "Ruega por nosotros",
        "Casa de Oro" to "Ruega por nosotros",
        "Arca de la alianza" to "Ruega por nosotros",
        "Puerta del cielo" to "Ruega por nosotros",
        "Estrella de la mañana" to "Ruega por nosotros",
        "Salud de los enfermos" to "Ruega por nosotros",
        "Refugio de los pecadores" to "Ruega por nosotros",
        "Consoladora de los afligidos" to "Ruega por nosotros",
        "Auxilio de los cristianos" to "Ruega por nosotros",
        "Reina de los Ángeles" to "Ruega por nosotros",
        "Reina de los Patriarcas" to "Ruega por nosotros",
        "Reina de los Profetas" to "Ruega por nosotros",
        "Reina de los Apóstoles" to "Ruega por nosotros",
        "Reina de los Mártires" to "Ruega por nosotros",
        "Reina de los Confesores" to "Ruega por nosotros",
        "Reina de las Vírgenes" to "Ruega por nosotros",
        "Reina de todos los Santos" to "Ruega por nosotros",
        "Reina concebida sin pecado original" to "Ruega por nosotros",
        "Reina subida al cielo en cuerpo y alma" to "Ruega por nosotros",
        "Reina del Santísimo Rosario" to "Ruega por nosotros",
        "Reina de la paz" to "Ruega por nosotros"
    )

    const val CORDERO_1 = "Cordero de Dios, que quitas el pecado del mundo: Perdónanos, Señor."
    const val CORDERO_2 = "Cordero de Dios, que quitas el pecado del mundo: Escúchanos, Señor."
    const val CORDERO_3 = "Cordero de Dios, que quitas el pecado del mundo: Ten piedad y misericordia de nosotros."

    // CONCLUSIÓN Y OFRECIMIENTO (Pág. 17)
    const val BAJO_TU_AMPARO = "Bajo tu amparo nos acogemos, Santa Madre de Dios, no desprecies las súplicas que te hacemos en nuestras necesidades, antes bien, líbranos de todos los peligros, oh Virgen gloriosa y bendita."

    const val ORACION_FINAL_SACERDOTAL = "Oh Dios, cuyo Unigénito Hijo, con su vida, muerte y resurrección, nos alcanzó el premio de la vida eterna: concédenos, a los que recordamos estos misterios del Santo Rosario, imitar lo que contienen y alcanzar lo que prometen. Por el mismo Jesucristo, Nuestro Señor. Amén."

    const val OFRECIMIENTO_FOLLETO = "Por estos misterios santos de que hemos hecho recuerdo, te pedimos, ¡oh María!, de la fe santa el aumento; la exaltación de la Iglesia; del Papa el mejor acierto; de la nación mexicana, la unión y feliz gobierno. Que el no cristiano conozca a Dios, y el que se ha alejado reconozca sus errores. Que todos los pecadores tengamos arrepentimiento. Que los cristianos perseguidos puedan practicar su fe. Goce puerto el navegante y de salud los enfermos. Que en el purgatorio logren las ánimas refrigerio. Y que este santo ejercicio tenga efecto tan completo en toda la cristiandad, que alcancemos por su medio, el ir a alabar a Dios en tu compañía en el cielo. Amén."

    // DESPEDIDA DEL FOLLETO (Pág. 17)
    const val DESPEDIDA_1_GUIA = "Reina del Santísimo Rosario."
    const val DESPEDIDA_1_TODOS = "Ruega por nosotros."

    const val DESPEDIDA_2_GUIA = "Viva la gracia."
    const val DESPEDIDA_2_TODOS = "Muera el pecado."

    const val DESPEDIDA_3_GUIA = "Ave María Purísima."
    const val DESPEDIDA_3_TODOS = "En gracia de Dios concebida."
}
