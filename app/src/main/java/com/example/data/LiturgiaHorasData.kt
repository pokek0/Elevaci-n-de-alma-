package com.example.data

enum class HoraLiturgica(
    val title: String,
    val subtitle: String,
    val canonicalTime: String,
    val recommendedHourRange: IntRange
) {
    LAUDES(
        title = "Laudes",
        subtitle = "Oración de la Mañana • Consagración del día",
        canonicalTime = "06:00 - 09:00",
        recommendedHourRange = 5..10
    ),
    HORA_INTERMEDIA(
        title = "Hora Intermedia",
        subtitle = "Tercia / Sexta / Nona • En el fervor del trabajo",
        canonicalTime = "12:00 - 15:00",
        recommendedHourRange = 11..16
    ),
    VISPERAS(
        title = "Vísperas",
        subtitle = "Oración de la Tarde • Acción de gracias y Magníficat",
        canonicalTime = "18:00 - 20:00",
        recommendedHourRange = 17..20
    ),
    COMPLETAS(
        title = "Completas",
        subtitle = "Oración de la Noche • Examen de conciencia y Nunc Dimittis",
        canonicalTime = "21:00 - 23:00",
        recommendedHourRange = 21..24 // also 0..4
    );

    companion object {
        fun getRecommendedHora(hourOfDay: Int): HoraLiturgica {
            return when (hourOfDay) {
                in 5..10 -> LAUDES
                in 11..16 -> HORA_INTERMEDIA
                in 17..20 -> VISPERAS
                else -> COMPLETAS
            }
        }
    }
}

data class LiturgiaHourContent(
    val hora: HoraLiturgica,
    val invocacionInicial: String,
    val himno: Pair<String, String>, // Title and text
    val salmodia: List<Triple<String, String, String>>, // Title, Antiphon, Text
    val lecturaBreve: Triple<String, String, String>, // Citation, Text, Response
    val canticoEvangelico: Triple<String, String, String>, // Title, Antiphon, Text
    val preces: List<Pair<String, String>>, // Petition, Response
    val oracionFinal: String,
    val bendicion: String
)

object LiturgiaHorasData {

    val laudes = LiturgiaHourContent(
        hora = HoraLiturgica.LAUDES,
        invocacionInicial = "V. Señor, ábreme los labios.\nR. Y mi boca proclamará tu alabanza.\n\nV. Dios mío, ven en mi auxilio.\nR. Señor, date prisa en socorrerme.\n\nGloria al Padre, y al Hijo, y al Espíritu Santo. Como era en el principio, ahora y siempre, por los siglos de los siglos. Amén. ¡Aleluya!",
        himno = Pair(
            "Himno: En esta aurora radiante",
            "En esta aurora radiante\nque nace del corazón de Dios,\nte alabamos, Cristo amante,\ncon pura y alegre voz.\n\nTú que eres la Luz del mundo,\ndisipa nuestra ceguera,\ny con tu amor tan profundo\nhaz fecunda la sementera.\n\nGloria al Padre omnipotente,\ngloria al Hijo redentor,\ny al Espíritu viviente,\npor los siglos el loor. Amén."
        ),
        salmodia = listOf(
            Triple(
                "Salmo 62, 2-9",
                "Ant. ¡Oh Dios, tú eres mi Dios, por ti madrugo, mi alma tiene sed de ti!",
                "¡Oh Dios, tú eres mi Dios, por ti madrugo,\nmi alma está sedienta de ti;\nmi carne tiene ansia de ti,\ncomo tierra reseca, agostada, sin agua.\n\n¡Cómo te contemplaba en el santuario\nviendo tu fuerza y tu gloria!\nTu gracia vale más que la vida,\nmis labios te alabarán.\n\nToda mi vida te bendeciré\ny alzaré las manos invocándote.\nMe saciaré como de manjares exquisitos,\ny mis labios te alabarán jubilosos."
            ),
            Triple(
                "Cántico de las Tres Jóvenes (Dn 3, 57-88)",
                "Ant. Criaturas todas del Señor, bendecid al Señor, ensalzadlo con himnos por los siglos.",
                "Criaturas todas del Señor, bendecid al Señor,\nensalzadlo con himnos por los siglos.\nÁngeles del Señor, bendecid al Señor;\ncielos, bendecid al Señor.\n\nAguas del espacio, bendecid al Señor;\nejércitos del Señor, bendecid al Señor.\nSol y luna, bendecid al Señor;\nastros del cielo, bendecid al Señor.\n\nLluvia y rocío, bendecid al Señor;\nvientos todos, bendecid al Señor.\nFuego y calor, bendecid al Señor;\nfríos y heladas, bendecid al Señor.\n\nBendiga la tierra al Señor,\nensálcelo con himnos por los siglos."
            )
        ),
        lecturaBreve = Triple(
            "Lectura breve (Rom 13, 11-13)",
            "Ya es hora de espabilarse, porque nuestra salvación está más cerca que cuando empezamos a creer. La noche está avanzada, el día se echa encima: dejemos las actividades de las tinieblas y pertrechémonos con las armas de la luz. Conduzcámonos como en pleno día, con dignidad.",
            "R. Cristo, Hijo de Dios vivo, ten piedad de nosotros.\nV. Tú que estás sentado a la derecha del Padre.\nR. Ten piedad de nosotros."
        ),
        canticoEvangelico = Triple(
            "Cántico de Zacarías (Benedictus - Lc 1, 68-79)",
            "Ant. Bendito sea el Señor, Dios de Israel, que ha visitado y redimido a su pueblo.",
            "Bendito sea el Señor, Dios de Israel,\nporque ha visitado y redimido a su pueblo,\nsuscitándonos una fuerza de salvación\nen la casa de David, su siervo,\nsegún lo había predicho desde antiguo\npor boca de sus santos profetas.\n\nEs la salvación que nos libra de nuestros enemigos\ny de la mano de todos los que nos odian;\nrealizando la misericordia que tuvo con nuestros padres,\nrecordando su santa alianza\ny el juramento que juró a nuestro padre Abrahán:\n\nPara concedernos que, libres de temor,\narrancados de la mano de los enemigos,\nle sirvamos con santidad y justicia,\nen su presencia, todos nuestros días.\n\nY a ti, niño, te llamarán profeta del Altísimo,\nporque irás delante del Señor a preparar sus caminos,\nanunciando a su pueblo la salvación,\nel perdón de sus pecados.\n\nPor la entrañable misericordia de nuestro Dios,\nnos visitará el Sol que nace de lo alto,\npara iluminar a los que viven en tinieblas\ny en sombra de muerte,\npara guiar nuestros pasos por el camino de la paz."
        ),
        preces = listOf(
            Pair("Agradezcamos a Dios que nos otorga este nuevo día:", "Señor, escucha nuestra oración."),
            Pair("Ilumina los pasos de todos los trabajadores y estudiantes:", "Haz que en sus obras resplandezca tu justicia."),
            Pair("Consuela a los enfermos y a los que sufren soledad:", "Sostén su fe con tu Espíritu Santo."),
            Pair("Derrama tu paz sobre las familias y sobre los pueblos en discordia:", "Reina en nuestros corazones, Príncipe de la paz.")
        ),
        oracionFinal = "Señor Dios todopoderoso, que nos has hecho llegar al comienzo de este día: sálvanos hoy con tu poder para que no caigamos en ningún pecado, sino que nuestras palabras, pensamientos y acciones se dirijan siempre al cumplimiento de tu santa voluntad. Por nuestro Señor Jesucristo, tu Hijo. Amén.",
        bendicion = "El Señor nos bendiga, nos guarde de todo mal y nos lleve a la vida eterna. Amén."
    )

    val horaIntermedia = LiturgiaHourContent(
        hora = HoraLiturgica.HORA_INTERMEDIA,
        invocacionInicial = "V. Dios mío, ven en mi auxilio.\nR. Señor, date prisa en socorrerme.\n\nGloria al Padre, y al Hijo, y al Espíritu Santo. Amén. ¡Aleluya!",
        himno = Pair(
            "Himno: Fervor del mediodía",
            "La luz del sol en su cenit ardiente\nnos llama a levantar el corazón,\ny en medio del afán y del quehacer,\nte ofrecemos, Señor, nuestra oración.\n\nSé Tú la fuerza en nuestro cansancio,\nsé Tú el oasis en la sequedad,\ny haz que este día en tu presencia santa\nsea ofrenda viva de fidelidad. Amén."
        ),
        salmodia = listOf(
            Triple(
                "Salmo 120",
                "Ant. El Señor te guarda a su sombra, el Señor es tu guardián.",
                "Levanto mis ojos a los montes:\n¿de dónde me vendrá el auxilio?\nEl auxilio me viene del Señor,\nque hizo el cielo y la tierra.\n\nNo permitirá que resbale tu pie,\ntu guardián no duerme;\nno duerme ni reposa\nel guardián de Israel.\n\nEl Señor te guarda a su sombra,\nestá a tu derecha;\nde día el sol no te hará daño,\nni la luna de noche."
            ),
            Triple(
                "Salmo 121",
                "Ant. ¡Qué alegría cuando me dijeron: «Vamos a la casa del Señor»!",
                "¡Qué alegría cuando me dijeron:\n«Vamos a la casa del Señor»!\nYa están pisando nuestros pies\ntus umbrales, Jerusalén.\n\nJerusalén está fundada\ncomo ciudad bien compacta.\nAllá suben las tribus,\nlas tribus del Señor."
            )
        ),
        lecturaBreve = Triple(
            "Lectura breve (Gál 6, 9-10)",
            "No nos cansemos de hacer el bien, que, a su tiempo, cosecharemos si no desmayamos. Por tanto, mientras tenemos tiempo, hagamos el bien a todos, especialmente a los hermanos en la fe.",
            "V. El Señor es mi pastor, nada me falta.\nR. En verdes praderas me hace recostar."
        ),
        canticoEvangelico = Triple(
            "Meditación breve del Mediodía",
            "Ant. Dichosos los que escuchan la palabra de Dios y la cumplen.",
            "«Si alguno tiene sed, venga a mí y beba el que cree en mí. Como dice la Escritura: de sus entrañas manarán ríos de agua viva.» (Jn 7, 37-38)"
        ),
        preces = listOf(
            Pair("Bendice, Señor, el trabajo de nuestras manos:", "Que sirva para la gloria de tu Nombre."),
            Pair("Da pan a los que tienen hambre:", "Y hambre de justicia a los que tienen pan.")
        ),
        oracionFinal = "Señor Jesucristo, que en la hora de la Cruz extendiste tus brazos salvadores para abrazar al mundo entero: haz que nuestras obras de este mediodía te sean gratas y sirvan para la edificación de tu Reino. Tú que vives y reinas por los siglos de los siglos. Amén.",
        bendicion = "Bendigamos al Señor. Demos gracias a Dios."
    )

    val visperas = LiturgiaHourContent(
        hora = HoraLiturgica.VISPERAS,
        invocacionInicial = "V. Dios mío, ven en mi auxilio.\nR. Señor, date prisa en socorrerme.\n\nGloria al Padre, y al Hijo, y al Espíritu Santo. Amén. ¡Aleluya!",
        himno = Pair(
            "Himno: Luz apacible de la tarde",
            "Ya la luz del sol declina en el ocaso,\ny la tarde nos convoca a la oración;\nte damos gracias, Padre, por tus dones,\ny por tu misericordia y perdón.\n\nQuédate con nosotros en la noche,\nsé nuestro huésped, dulce Redentor,\ny que la llama de la fe encendida\nbrille por siempre con divino ardor. Amén."
        ),
        salmodia = listOf(
            Triple(
                "Salmo 140, 1-9",
                "Ant. Suba mi oración como incienso en tu presencia, el alzar de mis manos como ofrenda de la tarde.",
                "Señor, te estoy llamando, ven deprisa,\nescucha mi voz cuando te llamo.\nSuba mi oración como incienso en tu presencia,\nel alzar de mis manos como ofrenda de la tarde.\n\nPon, Señor, una guardia en mi boca,\nun centinela a la puerta de mis labios;\nno dejes inclinarse mi corazón a la maldad,\na cometer delitos y crímenes."
            ),
            Triple(
                "Cántico del Nuevo Testamento (Flp 2, 6-11)",
                "Ant. Al nombre de Jesús toda rodilla se doble, en el cielo, en la tierra, en el abismo.",
                "Cristo Jesús, siendo de condición divina,\nno retuvo ávidamente el ser igual a Dios;\nal contrario, se despojó de sí mismo\ntomando la condición de siervo,\nhecho semejante a los hombres.\n\nY así, reconocido como hombre por su aspecto,\nse humilló a sí mismo,\nhecho obediente hasta la muerte,\ny una muerte de cruz.\n\nPor eso Dios lo exaltó sobre todo\ny le concedió el Nombre-sobre-todo-nombre;\nde modo que al nombre de Jesús toda rodilla se doble,\ny toda lengua proclame: «¡Jesucristo es Señor!», para gloria de Dios Padre."
            )
        ),
        lecturaBreve = Triple(
            "Lectura breve (1 Pe 5, 5-7)",
            "Revestíos todos de humildad en vuestras relaciones mutuas, porque Dios resiste a los soberbios, pero da su gracia a los humildes. Humillaos, pues, bajo la poderosa mano de Dios, para que a su tiempo os levante; descargad en él todas vuestras angustias, pues él cuida de vosotros.",
            "R. Te damos gracias, Señor, por tu inmensa fidelidad.\nV. Bendito sea tu santo Nombre.\nR. Por los siglos de los siglos."
        ),
        canticoEvangelico = Triple(
            "Cántico de la Santísima Virgen María (Magníficat - Lc 1, 46-55)",
            "Ant. Proclama mi alma la grandeza del Señor, se alegra mi espíritu en Dios, mi Salvador.",
            "Proclama mi alma la grandeza del Señor,\nse alegra mi espíritu en Dios, mi salvador;\nporque ha mirado la humillación de su esclava.\n\nDesde ahora me felicitarán todas las generaciones,\nporque el Poderoso ha hecho obras grandes por mí;\nsu nombre es santo,\ny su misericordia llega a sus fieles\nde generación en generación.\n\nÉl hace proezas con su brazo:\ndispersa a los soberbios de corazón,\nderriba del trono a los poderosos\ny enaltece a los humildes,\na los hambrientos los colma de bienes\ny a los ricos los despide vacíos.\n\nAuxilia a Israel, su siervo,\nacordándose de la misericordia\n—como lo había prometido a nuestros padres—\nen favor de Abrahán y su descendencia por siempre.\n\nGloria al Padre, y al Hijo, y al Espíritu Santo. Amén."
        ),
        preces = listOf(
            Pair("Te bendecimos, Señor, por este día que concluye:", "Danos tu perdón y tu paz."),
            Pair("Acuérdate de los que sufren en hospitales y cárceles:", "Sé su consuelo y su esperanza."),
            Pair("Recibe en tu paraíso a las benditas almas del purgatorio:", "Concédeles el descanso eterno y brille para ellas la luz perpetua."),
            Pair("Protege a la Santa Iglesia y a todas nuestras familias:", "Custódianos bajo tu manto paternal.")
        ),
        oracionFinal = "Te pedimos, Señor, que la oración vespertina de tu pueblo te sea grata, y que tu bondad infinita perdone nuestras faltas cotidianas y nos proteja de todo peligro nocturno. Por nuestro Señor Jesucristo, tu Hijo. Amén.",
        bendicion = "El Señor todopoderoso nos bendiga, guarde nuestros hogares y nos conduzca a la vida eterna. Amén."
    )

    val completas = LiturgiaHourContent(
        hora = HoraLiturgica.COMPLETAS,
        invocacionInicial = "V. El Señor todopoderoso nos conceda una noche tranquila y una santa muerte.\nR. Amén.\n\nExamen de conciencia breve:\n(Un momento de silencio sagrado repasando el día en la presencia de Dios...)\n\nYo confieso ante Dios todopoderoso y ante vosotros, hermanos, que he pecado mucho de pensamiento, palabra, obra y omisión. Por mi culpa, por mi culpa, por mi gran culpa. Por eso ruego a Santa María, siempre Virgen, a los ángeles, a los santos y a vosotros, hermanos, que intercedáis por mí ante Dios, nuestro Señor. Amén.",
        himno = Pair(
            "Himno: Antes que la luz se apague",
            "Antes que la luz se apague,\nte imploramos, Creador,\nque con tu piedad paterna\nnos guardes con santo amor.\n\nLejos los sueños oscuros,\nlas sombras de la maldad;\nguarda limpios nuestros cuerpos\nen serena castidad.\n\nEscúchanos, Padre amado,\npor Cristo nuestro Señor,\ny el Espíritu divino,\núnico y sumo Creador. Amén."
        ),
        salmodia = listOf(
            Triple(
                "Salmo 90 (Al amparo del Altísimo)",
                "Ant. Te cubrirá con sus plumas, bajo sus alas te refugiarás.",
                "Tú que habitas al amparo del Altísimo,\nque vives a la sombra del Omnipotente,\ndi al Señor: «Refugio mío, alcázar mío,\nDios mío, en quien confío».\n\nÉl te librará de la red del cazador,\nde la peste funesta.\nTe cubrirá con sus plumas,\nbajo sus alas te refugiarás:\nsu brazo es escudo y armadura.\n\nNo temerás el espanto nocturno,\nni la flecha que vuela de día,\nni la peste que avanza en las tinieblas,\nni la plaga que azota a mediodía.\n\nPorque hiciste del Señor tu refugio,\nno se te acercará la desgracia.\nPorque ha dado órdenes a sus ángeles\npara que te guarden en tus caminos."
            )
        ),
        lecturaBreve = Triple(
            "Lectura breve (1 Tes 5, 9-10)",
            "Dios no nos ha destinado al castigo, sino a obtener la salvación por medio de nuestro Señor Jesucristo, que murió por nosotros para que, velando o durmiendo, vivamos juntos con él.",
            "R. En tus manos, Señor, encomiendo mi espíritu.\nV. Tú nos redimiste, Señor, Dios de verdad.\nR. En tus manos, Señor, encomiendo mi espíritu."
        ),
        canticoEvangelico = Triple(
            "Cántico de Simeón (Nunc Dimittis - Lc 2, 29-32)",
            "Ant. Sálvanos, Señor, despiertos; protégenos mientras dormimos; para que velemos con Cristo y descansemos en paz.",
            "Ahora, Señor, según tu promesa,\npuedes dejar a tu siervo irse en paz.\n\nPorque mis ojos han visto a tu Salvador,\na quien has presentado ante todos los pueblos:\nluz para alumbrar a las naciones\ny gloria de tu pueblo Israel.\n\nGloria al Padre, y al Hijo, y al Espíritu Santo. Amén."
        ),
        preces = listOf(
            Pair("Visita, Señor, esta habitación:", "Y aleja de ella todas las insidias del enemigo."),
            Pair("Tus santos ángeles habiten en ella:", "Y nos guarden en santa paz.")
        ),
        oracionFinal = "Visita, Señor, esta morada nuestra y aleja de ella las insidias del enemigo; que tus santos ángeles habiten en ella y nos guarden en paz, y que tu bendición permanezca siempre con nosotros. Por Jesucristo, nuestro Señor. Amén.",
        bendicion = "El Señor todopoderoso nos bendiga y nos conceda un descanso tranquilo y una muerte santa. Amén.\n\n(Antífona Mariana Final:)\nBajo tu amparo nos acogemos, Santa Madre de Dios; no deseches las súplicas que te dirigimos en nuestras necesidades, antes bien, líbranos de todo peligro, ¡oh Virgen gloriosa y bendita! Amén."
    )

    fun getHourContent(hora: HoraLiturgica): LiturgiaHourContent {
        return when (hora) {
            HoraLiturgica.LAUDES -> laudes
            HoraLiturgica.HORA_INTERMEDIA -> horaIntermedia
            HoraLiturgica.VISPERAS -> visperas
            HoraLiturgica.COMPLETAS -> completas
        }
    }
}
