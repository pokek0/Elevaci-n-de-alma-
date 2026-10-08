package com.example.data

data class ScheduleSlot(
    val slotId: String,
    val timeLabel: String,
    val periodName: String,
    val title: String,
    val subtitle: String,
    val startHour: Int,
    val endHour: Int,
    val prayersList: List<SchedulePrayerItem>,
    val actionType: ScheduleActionType = ScheduleActionType.READ_IN_PLACE
)

enum class ScheduleActionType {
    READ_IN_PLACE,
    OPEN_ROSARY,
    OPEN_CORONILLA,
    OPEN_LITURGIA_LAUDES,
    OPEN_LITURGIA_VISPERAS,
    OPEN_LITURGIA_COMPLETAS
}

data class SchedulePrayerItem(
    val name: String,
    val text: String,
    val guideVsResponse: Boolean = false
)

object ScheduleData {

    val slots: List<ScheduleSlot> = listOf(
        ScheduleSlot(
            slotId = "DESPERTAR",
            timeLabel = "06:00 AM",
            periodName = "Al Despertar",
            title = "Ofrecimiento del Día y Alianza Matutina",
            subtitle = "Consagrar el día que nace al Señor y a la Virgen María",
            startHour = 5,
            endHour = 8,
            prayersList = listOf(
                SchedulePrayerItem(
                    name = "Por la Señal de la Cruz",
                    text = "Por la señal † de la Santa Cruz, de nuestros enemigos † líbranos, Señor Dios nuestro. En el nombre del Padre, y del Hijo, y del Espíritu Santo. Amén."
                ),
                SchedulePrayerItem(
                    name = "Ofrecimiento de Obras del Día",
                    text = "Te ofrezco, Dios mío, en unión con el Santísimo Corazón de Jesús y por medio del Inmaculado Corazón de María, todas las oraciones, obras, alegrías y sufrimientos de este día, en reparación de nuestros pecados y por todas las intenciones del Santo Padre. Amén."
                ),
                SchedulePrayerItem(
                    name = "Acción de Gracias por el Nuevo Día",
                    text = "Gracias te doy, Dios mío, por haberme creado, redimido y conservado durante la noche. Te pido que guíes mis pasos hoy para que no peque ni te ofenda, sino que sirva a mis hermanos con caridad y alegría. Amén."
                ),
                SchedulePrayerItem(
                    name = "Oración al Ángel de la Guarda",
                    text = "Ángel de Dios, que eres mi custodio, pues la piedad celestial me encomendó a ti: ilumíname, guárdame, rígeme y gobiérname en este día. Amén."
                )
            ),
            actionType = ScheduleActionType.READ_IN_PLACE
        ),
        ScheduleSlot(
            slotId = "MANANA_LAUDES",
            timeLabel = "08:30 AM",
            periodName = "Mañana / Alabanza",
            title = "Invocación al Espíritu Santo y Laudes",
            subtitle = "Luz divina para iniciar las labores y santificar el trabajo",
            startHour = 8,
            endHour = 11,
            prayersList = listOf(
                SchedulePrayerItem(
                    name = "Invocación al Espíritu Santo",
                    text = "Ven, Espíritu Santo, llena los corazones de tus fieles y enciende en ellos el fuego de tu amor. Envía tu Espíritu y serán creados, y renovarás la faz de la tierra.\n\nOh Dios, que has iluminado los corazones de tus hijos con la luz del Espíritu Santo: haz que, guiados por este mismo Espíritu, saboreemos la dulzura del bien y gocemos siempre de sus consuelos. Por Jesucristo, nuestro Señor. Amén."
                ),
                SchedulePrayerItem(
                    name = "Cántico de Zacarías (Benedictus)",
                    text = "Bendito sea el Señor, Dios de Israel, porque ha visitado y redimido a su pueblo... Por la entrañable misericordia de nuestro Dios, nos visitará el Sol que nace de lo alto, para iluminar a los que viven en tinieblas y en sombra de muerte, para guiar nuestros pasos por el camino de la paz. Amén."
                ),
                SchedulePrayerItem(
                    name = "Oración para Santificar el Trabajo",
                    text = "Señor, bendice las tareas que emprenda en este día. Que mi labor sea honesta, alegre y fructífera, y que en cada persona con la que trate reconozca a un hermano amado por Ti. Amén."
                )
            ),
            actionType = ScheduleActionType.OPEN_LITURGIA_LAUDES
        ),
        ScheduleSlot(
            slotId = "MEDIODIA_ANGELUS",
            timeLabel = "12:00 PM",
            periodName = "Mediodía",
            title = "El Ángelus y Bendición de los Alimentos",
            subtitle = "La Encarnación del Verbo y pausa orante en la jornada",
            startHour = 11,
            endHour = 14,
            prayersList = listOf(
                SchedulePrayerItem(
                    name = "El Ángelus Domini",
                    text = "V. El Ángel del Señor anunció a María.\nR. Y concibió por obra y gracia del Espíritu Santo. (Dios te salve, María...)\n\nV. He aquí la esclava del Señor.\nR. Hágase en mí según tu palabra. (Dios te salve, María...)\n\nV. Y el Verbo se hizo carne.\nR. Y habitó entre nosotros. (Dios te salve, María...)\n\nV. Ruega por nosotros, Santa Madre de Dios.\nR. Para que seamos dignos de alcanzar las promesas de Jesucristo. Amén."
                ),
                SchedulePrayerItem(
                    name = "Oración del Ángelus",
                    text = "Derrama, Señor, tu gracia sobre nuestros corazones; para que habiendo conocido por el anuncio del Ángel la encarnación de tu Hijo Jesucristo, por los méritos de su Pasión y de su Cruz seamos llevados a la gloria de su Resurrección. Por el mismo Jesucristo, nuestro Señor. Amén."
                ),
                SchedulePrayerItem(
                    name = "Bendición de la Mesa",
                    text = "Bendícenos, Señor, y bendice estos alimentos que por tu infinita bondad vamos a recibir. Da pan al que tiene hambre y hambre de Dios al que tiene pan. Por Cristo, nuestro Señor. Amén."
                )
            ),
            actionType = ScheduleActionType.READ_IN_PLACE
        ),
        ScheduleSlot(
            slotId = "HORA_MISERICORDIA",
            timeLabel = "03:00 PM",
            periodName = "Hora de la Pasión",
            title = "Coronilla de la Divina Misericordia",
            subtitle = "La hora sagrada de la entrega de Cristo en el Calvario",
            startHour = 14,
            endHour = 17,
            prayersList = listOf(
                SchedulePrayerItem(
                    name = "Oración de las Tres de la Tarde",
                    text = "Expiraste, Jesús, pero la fuente de vida brotó para las almas y un mar de misericordia se abrió para el mundo entero. ¡Oh fuente de vida, insondable Misericordia Divina!, abarca al mundo entero y derrámate sobre nosotros."
                ),
                SchedulePrayerItem(
                    name = "Jaculatoria de la Sangre y el Agua",
                    text = "¡Oh Sangre y Agua que brotaste del Corazón de Jesús como una fuente de misericordia para nosotros, en Ti confío!"
                ),
                SchedulePrayerItem(
                    name = "Padre Eterno",
                    text = "Padre Eterno, te ofrezco el Cuerpo y la Sangre, el Alma y la Divinidad de tu amadísimo Hijo, nuestro Señor Jesucristo, como propiciación de nuestros pecados y los del mundo entero."
                ),
                SchedulePrayerItem(
                    name = "Trisagio Santo Dios",
                    text = "Santo Dios, Santo Fuerte, Santo Inmortal, ten piedad de nosotros y del mundo entero. (x3)\n\n¡Jesús, en Ti confío!"
                )
            ),
            actionType = ScheduleActionType.OPEN_CORONILLA
        ),
        ScheduleSlot(
            slotId = "TARDE_ROSARIO",
            timeLabel = "06:30 PM",
            periodName = "Atardecer / Vísperas",
            title = "El Santo Rosario y Vísperas",
            subtitle = "Contemplar los misterios de Cristo con la Santísima Virgen",
            startHour = 17,
            endHour = 20,
            prayersList = listOf(
                SchedulePrayerItem(
                    name = "Cántico del Magníficat",
                    text = "Proclama mi alma la grandeza del Señor, se alegra mi espíritu en Dios, mi Salvador; porque ha mirado la humillación de su esclava. Desde ahora me felicitarán todas las generaciones, porque el Poderoso ha hecho obras grandes por mí: su nombre es santo y su misericordia llega a sus fieles de generación en generación. Amén."
                ),
                SchedulePrayerItem(
                    name = "Santo Rosario del Día",
                    text = "Rezar los 5 Misterios que corresponden al día de hoy (Gozosos, Luminosos, Dolorosos o Gloriosos), pidiendo por las intenciones del hogar, de la Iglesia y del mundo entero."
                ),
                SchedulePrayerItem(
                    name = "Oración por los Difuntos",
                    text = "Dales, Señor, el descanso eterno, y brille para ellos la luz perpetua. Que las almas de todos los fieles difuntos, por la misericordia de Dios, descansen en paz. Amén."
                )
            ),
            actionType = ScheduleActionType.OPEN_ROSARY
        ),
        ScheduleSlot(
            slotId = "NOCHE_COMPLETAS",
            timeLabel = "09:30 PM",
            periodName = "Noche / Antes de Dormir",
            title = "Completas y Examen de Conciencia",
            subtitle = "Revisión sincera de la jornada, perdón y reposo en Dios",
            startHour = 20,
            endHour = 24,
            prayersList = listOf(
                SchedulePrayerItem(
                    name = "Examen de Conciencia Breve",
                    text = "¿Qué bendiciones recibí hoy de Dios para agradecerle?\n¿En qué falté contra Dios, contra mi prójimo o contra mí mismo en pensamientos, palabras u omisiones?\n¿Qué propósito de enmienda hago para mañana con la ayuda de la gracia?"
                ),
                SchedulePrayerItem(
                    name = "Acto de Contrición",
                    text = "Señor mío Jesucristo, Dios y Hombre verdadero, Creador, Padre y Redentor mío; por ser Tú quien eres, bondad infinita, y porque te amo sobre todas las cosas, me pesa de todo corazón haberte ofendido. Propongo firmemente nunca más pecar, confesarme y cumplir la penitencia impuesta. Amén."
                ),
                SchedulePrayerItem(
                    name = "Cántico de Simeón (Nunc Dimittis)",
                    text = "Ahora, Señor, según tu promesa, puedes dejar a tu siervo irse en paz. Porque mis ojos han visto a tu Salvador, a quien has presentado ante todos los pueblos: luz para alumbrar a las naciones y gloria de tu pueblo Israel. Amén."
                ),
                SchedulePrayerItem(
                    name = "Oración de Encomienda Nocturna",
                    text = "En tus manos, Señor, encomiendo mi espíritu. Visita esta morada nuestra, Señor, y aparta de ella las asechanzas del enemigo; que tus santos ángeles moren en ella y nos guarden en paz, y que tu bendición permanezca siempre con nosotros. Por Cristo nuestro Señor. Amén."
                ),
                SchedulePrayerItem(
                    name = "Bajo Tu Amparo",
                    text = "Bajo tu amparo nos acogemos, Santa Madre de Dios; no deseches las súplicas que te dirigimos en nuestras necesidades, antes bien, líbranos de todo peligro, ¡oh Virgen gloriosa y bendita! Amén."
                )
            ),
            actionType = ScheduleActionType.OPEN_LITURGIA_COMPLETAS
        )
    )

    fun getCurrentSlot(hourOfDay: Int): ScheduleSlot {
        return slots.find { hourOfDay in it.startHour..it.endHour } ?: slots.first()
    }
}
