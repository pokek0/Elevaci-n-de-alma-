package com.example.data

import com.example.data.model.LiturgicalDay
import com.example.data.model.LiturgicalReading

object CemReadingsData {

    val liturgicalDays: List<LiturgicalDay> = listOf(
        LiturgicalDay(
            id = "today",
            dateDisplay = "Hoy • Tiempo Ordinario",
            liturgicalTitle = "Feria de la XXVII Semana del Tiempo Ordinario",
            liturgicalSeason = "Tiempo Ordinario",
            liturgicalColor = "Verde",
            cycle = "Año Par • Ciclo B (Misal Romano CEM)",
            readings = listOf(
                LiturgicalReading(
                    type = "PRIMERA LECTURA",
                    citation = "Lectura de la carta del apóstol san Pablo a los Gálatas (Gál 1, 6-12)",
                    subtitle = "El Evangelio que les he anunciado no es invención humana",
                    text = """Hermanos: Me asombra que tan pronto se hayan apartado de aquel que los llamó por la gracia de Cristo, para seguir otro evangelio. No es que haya otro evangelio; lo que pasa es que algunos los perturban a ustedes y quieren cambiar el Evangelio de Cristo.

Pero aun si nosotros mismos o un ángel bajado del cielo les anunciara un evangelio distinto del que les hemos anunciado, ¡sea anatema! Se lo dijimos antes y ahora se lo repito: si alguno les predica un evangelio distinto del que han recibido, ¡sea anatema!

¿Acaso busco ahora ganarme el favor de los hombres o el de Dios? ¿O pretendo agradar a los hombres? Si todavía tratara de agradar a los hombres, ya no sería siervo de Cristo.

Les hago saber, hermanos, que el Evangelio que les he anunciado no es invención humana; pues yo no lo recibí ni lo aprendí de hombre alguno, sino por revelación de Jesucristo.""",
                    closing = "Palabra de Dios. — Te alabamos, Señor."
                ),
                LiturgicalReading(
                    type = "SALMO RESPONSORIAL",
                    citation = "Salmo 110, 1-2. 7-8. 9 y 10c",
                    response = "R. El Señor se acuerda siempre de su alianza.",
                    text = """Doy gracias al Señor de todo corazón,
en la reunión de los justos, en la asamblea.
Grandes son las obras del Señor,
dignas de estudio para los que las aman.
R. El Señor se acuerda siempre de su alianza.

Verdad y justicia son las obras de sus manos;
seguros son todos sus preceptos,
afianzados para siempre jamás,
establecidos con verdad y rectitud.
R. El Señor se acuerda siempre de su alianza.

Envió la redención a su pueblo,
ratificó su alianza para siempre;
su nombre es sagrado y temible.
La alabanza del Señor dura por siempre.
R. El Señor se acuerda siempre de su alianza."""
                ),
                LiturgicalReading(
                    type = "ACLAMACIÓN ANTES DEL EVANGELIO",
                    citation = "Jn 13, 34",
                    response = "R. Aleluya, aleluya.",
                    text = """Les doy un mandamiento nuevo, dice el Señor:
que se amen los unos a los otros, como yo los he amado.
R. Aleluya."""
                ),
                LiturgicalReading(
                    type = "SANTO EVANGELIO",
                    citation = "Lectura del santo Evangelio según san Lucas (Lc 10, 25-37)",
                    subtitle = "¿Quién es mi prójimo? La parábola del buen samaritano",
                    text = """En aquel tiempo, se levantó un maestro de la Ley y preguntó a Jesús para ponerlo a prueba:
«Maestro, ¿qué tengo que hacer para heredar la vida eterna?».

Él le dijo: «¿Qué está escrito en la Ley? ¿Cómo lees tú?».
Él respondió: «Amarás al Señor tu Dios con todo tu corazón, con toda tu alma, con todas tus fuerzas y con toda tu mente; y a tu prójimo como a ti mismo».
Jesús le dijo: «Has respondido bien; haz esto y vivirás».

Pero él, queriendo justificar su pregunta, dijo a Jesús: «¿Y quién es mi prójimo?».

Jesús tomó la palabra y dijo:
«Un hombre bajaba de Jerusalén a Jericó y cayó en manos de unos bandidos, que lo desnudaron, lo molieron a palos y se fueron dejándolo medio muerto.

Casualmente bajaba por aquel camino un sacerdote; al verlo, dio un rodeo y pasó de largo. Igualmente un levita, al llegar a aquel lugar, al verlo, dio también un rodeo y pasó de largo.

Pero un samaritano que iba de viaje llegó adonde estaba él y, al verlo, se compadeció; se le acercó, vendó sus heridas derramando en ellas aceite y vino, lo montó en su propia cabalgadura, lo llevó a una posada y cuidó de él.

Al día siguiente sacó dos denarios, se los dio al posadero y le dijo: "Cuida de él, y lo que gastes de más te lo pagaré a la vuelta".

¿Quién de estos tres te parece que se portó como prójimo del que cayó en manos de los bandidos?».

Él contestó: «El que practicó la misericordia con él».
Jesús le dijo: «Anda y haz tú lo mismo».""",
                    closing = "Palabra del Señor. — Gloria a ti, Señor Jesús."
                )
            ),
            reflectionTitle = "Reflexión Pastoral del Episcopado: La Compasión que Transforma el Camino",
            reflectionText = """El Evangelio de hoy, proclamado en la liturgia mexicana, nos sitúa en el núcleo mismo de la fe cristiana: el amor a Dios inseparable del amor entrañable al prójimo.

El maestro de la ley busca una definición teórica: «¿Quién es mi prójimo?». Pero Jesús invierte la perspectiva: la pregunta no es quién califica como prójimo, sino de quién me hago yo prójimo cuando veo el dolor ajeno.

El sacerdote y el levita representan la tentación del culto desconectado de la vida: por temor al contagio ritual o por las prisas de sus deberes religiosos, pasan de largo ante un ser humano destrozado. En cambio, el samaritano —un extranjero despreciado por los piadosos de la época— se conmueve en sus entrañas. La compasión no se queda en un sentimiento estéril: se convierte en vendaje, vino balsámico, esfuerzo físico, gasto de dinero y tiempo entregado.

En nuestro México actual, herido tantas veces por la violencia, la indiferencia y la división familiar, el Señor nos llama a ser posaderos de esperanza y buenos samaritanos. El prójimo no es solo el que piensa como yo o me cae bien; el prójimo es cualquiera que necesite consuelo, pan, escucha o perdón.""",
            reflectionQuestions = listOf(
                "¿He pasado de largo alguna vez ante la necesidad de un familiar, vecino o compañero de trabajo?",
                "¿Cómo puedo transformar hoy mi oración en un gesto concreto de caridad?",
                "¿Estoy dispuesto a invertir mi tiempo y mis recursos para aliviar el sufrimiento de los demás?"
            ),
            dailyPrayer = """Señor Jesús, Buen Samaritano de nuestras almas, que te inclinaste sobre nuestras heridas para sanarnos con el bálsamo de tu gracia:
abre nuestros ojos ante los que sufren a la vera del camino. Líbranos de la prisa egoísta y del corazón endurecido. Haz de nuestra patria, de nuestras familias y de nuestras comunidades remansos de misericordia donde nadie quede abandonado. Que hoy sepamos amar no solo de palabra, sino con hechos y de verdad. Amén."""
        ),
        LiturgicalDay(
            id = "festividad_virgen",
            dateDisplay = "Memoria Litúrgica Mariana",
            liturgicalTitle = "Nuestra Señora del Rosario • Memoria Obligatoria",
            liturgicalSeason = "Tiempo Ordinario",
            liturgicalColor = "Blanco",
            cycle = "Misal Romano de México (CEM)",
            readings = listOf(
                LiturgicalReading(
                    type = "PRIMERA LECTURA",
                    citation = "Lectura del libro de los Hechos de los Apóstoles (Hch 1, 12-14)",
                    subtitle = "Perseveraban unánimes en la oración con María, la madre de Jesús",
                    text = """Después de subir Jesús al cielo, los apóstoles regresaron a Jerusalén desde el monte llamado de los Olivos, que dista de la ciudad poco más de un kilómetro.

Cuando llegaron, subieron a la estancia superior, donde vivían: Pedro y Juan, Santiago y Andrés, Felipe y Tomás, Bartolomé y Mateo, Santiago el de Alfeo, Simón el Celote y Judas el de Santiago.

Todos ellos perseveraban unánimes en la oración, junto con algunas mujeres y María, la madre de Jesús, y con los hermanos de éste.""",
                    closing = "Palabra de Dios. — Te alabamos, Señor."
                ),
                LiturgicalReading(
                    type = "SALMO RESPONSORIAL",
                    citation = "Cántico de María (Lc 1, 46-55)",
                    response = "R. El Poderoso ha hecho obras grandes por mí: su nombre es santo.",
                    text = """Proclama mi alma la grandeza del Señor,
se alegra mi espíritu en Dios, mi salvador;
porque ha mirado la humillación de su esclava.
Desde ahora me felicitarán todas las generaciones.
R. El Poderoso ha hecho obras grandes por mí: su nombre es santo.

Porque el Poderoso ha hecho obras grandes por mí:
su nombre es santo,
y su misericordia llega a sus fieles
de generación en generación.
R. El Poderoso ha hecho obras grandes por mí: su nombre es santo.

Auxilia a Israel, su siervo,
acordándose de la misericordia
-como lo había prometido a nuestros padres-
en favor de Abrahán y su descendencia por siempre.
R. El Poderoso ha hecho obras grandes por mí: su nombre es santo."""
                ),
                LiturgicalReading(
                    type = "SANTO EVANGELIO",
                    citation = "Lectura del santo Evangelio según san Lucas (Lc 1, 26-38)",
                    subtitle = "He aquí la esclava del Señor; hágase en mí según tu palabra",
                    text = """En aquel tiempo, el ángel Gabriel fue enviado por Dios a una ciudad de Galilea llamada Nazaret, a una virgen desposada con un hombre llamado José, de la casa de David; el nombre de la virgen era María.

El ángel, entrando en su presencia, dijo: «Alégrate, llena de gracia, el Señor está contigo».
Ella se turbó grandemente ante estas palabras y se preguntaba qué saludo era aquél.

El ángel le dijo: «No temas, María, porque has encontrado gracia ante Dios. Concebirás en tu vientre y darás a luz un hijo, y le pondrás por nombre Jesús. Será grande, se llamará Hijo del Altísimo, el Señor Dios le dará el trono de David, su padre; reinará sobre la casa de Jacob para siempre, y su reino no tendrá fin».

María dijo al ángel: «¿Cómo será esto, pues no conozco varón?».
El ángel le contestó: «El Espíritu Santo vendrá sobre ti, y la fuerza del Altísimo te cubrirá con su sombra; por eso el Santo que va a nacer será llamado Hijo de Dios... porque para Dios nada hay imposible».

María contestó: «He aquí la esclava del Señor; hágase en mí según tu palabra». Y el ángel se retiró.""",
                    closing = "Palabra del Señor. — Gloria a ti, Señor Jesús."
                )
            ),
            reflectionTitle = "Meditación: El Rosario como Escuela Evangélica de Contemplación",
            reflectionText = """El misterio de la Encarnación y la fiesta del Santo Rosario nos recuerdan que María no es el punto final, sino la puerta luminosa que nos introduce en el corazón de Cristo.

Al desgranar las cuentas del Rosario, no multiplicamos vanas palabras; nos sentamos junto a María en Nazaret, subimos con Ella a Belén, contemplamos el Huerto y la Cruz, y nos llenamos de gozo con la Resurrección.

Como nos enseña el magisterio de la Iglesia en México, el Rosario es una oración bíblica por excelencia. Cada misterio es una ventana a los Evangelios. El 'Hágase' de María es la respuesta valiente de quien confía en que los caminos de Dios siempre son mejores que nuestros planes humanos.""",
            reflectionQuestions = listOf(
                "¿Dedico tiempo diario a la contemplación silenciosa de los misterios de Jesús?",
                "¿Cómo respondo yo ante los llamados que Dios me hace en lo cotidiano: con reservas o con el 'hágase' de María?"
            ),
            dailyPrayer = """Virgen Santísima del Rosario, Madre y Maestra de la fe: enséñanos a guardar en el corazón los misterios de la vida de tu Hijo, para que aprendiendo de tu docilidad y fortaleza, seamos en medio del mundo discípulos y misioneros alegres del Evangelio. Ruega por nosotros, Santa Madre de Dios. Amén."""
        )
    )
}
