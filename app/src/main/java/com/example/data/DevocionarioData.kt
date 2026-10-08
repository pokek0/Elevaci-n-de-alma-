package com.example.data

import com.example.data.model.DevotionalPrayer

object DevocionarioData {

    val prayers: List<DevotionalPrayer> = listOf(
        DevotionalPrayer(
            id = "el_puente",
            pageNumber = 2,
            title = "El Puente",
            subtitle = "Oración de entrega y apostolado",
            category = "Poesía y Ofrenda",
            content = """Un anhelo ferviente hay en mi pecho,
que solo tú conoces ¡oh Señor!
el anhelo de ser en mi vida un puente entre los hombres y Tu amor.

Un puente que partiendo de mi nada llegues hasta la orilla de tu eternidad.
un puente que todos puedan pisar en busca de tu amor y tu amistad.

no importa que el dolor de mil pisadas marquen huellas ensangrentadas.
Yo solo quiero ser puente divino y que seas tú el final de este camino.

señor haz que este puente no se rompa mientras pueda servir a mis hermanos,
y cuando ya nadie lo necesite destrúyelo a tu antojo entre tus manos."""
        ),
        DevotionalPrayer(
            id = "magnificat",
            pageNumber = 3,
            title = "Magníficat",
            subtitle = "Cántico de la Santísima Virgen María (Lucas 1, 46-55)",
            category = "Mariana",
            content = """Proclama mi alma la grandeza del Señor,
se alegra mi espíritu en Dios, mi salvador;
porque ha mirado la humillación de su esclava.

Desde ahora me felicitarán todas las generaciones,
porque el Poderoso ha hecho obras grandes por mí:
su nombre es santo,
y su misericordia llega a sus fieles de generación en generación.

Él hace proezas con su brazo:
dispersa a los soberbios de corazón,
derriba del trono a los poderosos y enaltece a los humildes,
a los hambrientos los colma de bienes
y a los ricos los despide vacíos.

Auxilia a Israel, su siervo,
acordándose de la misericordia
-como lo había prometido a nuestros padres-
en favor de Abrahán y su descendencia por siempre.

Gloria al Padre, y al Hijo, y al Espíritu Santo.
Como era en el principio, ahora y siempre, por los siglos de los siglos. Amén."""
        ),
        DevotionalPrayer(
            id = "el_angelus",
            pageNumber = 4,
            title = "El Ángelus",
            subtitle = "Oración tradicional de la Encarnación (6:00, 12:00 y 18:00)",
            category = "Mariana Diaria",
            content = "",
            isInteractiveAngelus = true,
            verses = listOf(
                "V. El Ángel del Señor anunció a María." to "R. Y concibió por obra del Espíritu Santo.",
                "V. He aquí la esclava del Señor." to "R. Hágase en mí según tu palabra.",
                "V. Y el Verbo se hizo carne." to "R. Y habitó entre nosotros.",
                "V. Ruega por nosotros, Santa Madre de Dios." to "R. Para que seamos dignos de alcanzar las promesas de Nuestro Señor Jesucristo."
            ),
            finalPrayer = """Oremos:
Derrama Señor, tu gracia en nuestras almas para que los que, por el anuncio del Ángel, hemos conocido la encarnación de tu Hijo Jesucristo, por su pasión y su cruz lleguemos a la gloria de la resurrección. Por el mismo Jesucristo, Nuestro Señor.

R. Amén."""
        ),
        DevotionalPrayer(
            id = "regina_coeli",
            pageNumber = 5,
            title = "Regina Coeli",
            subtitle = "Cántico pascual a la Reina del Cielo",
            category = "Tiempo Pascual",
            content = "",
            isInteractiveReginaCoeli = true,
            verses = listOf(
                "V. Reina del cielo, alégrate; aleluya." to "R. Porque el Señor a quien has merecido llevar; aleluya.",
                "V. Ha resucitado según su palabra; aleluya." to "R. Ruega al Señor por nosotros; aleluya.",
                "V. Gózate y alégrate, Virgen María; aleluya." to "R. Porque verdaderamente ha resucitado el Señor; aleluya."
            ),
            finalPrayer = """Oremos:
Oh Dios, que por la resurrección de tu Hijo, nuestro Señor Jesucristo, has llenado el mundo de alegría, concédenos, por intercesión de su Madre, la Virgen María, llegar a alcanzar los gozos eternos. Por Jesucristo nuestro Señor.

R. Amén."""
        ),
        DevotionalPrayer(
            id = "acto_contricion",
            pageNumber = 6,
            title = "Acto de Contrición",
            subtitle = "Oración de perdón y reconciliación íntima",
            category = "Penitencial",
            content = """Señor mío Jesucristo,
porque me amas tanto y eres tan bueno que moriste por mí;
me pesa haberte ofendido.

Yo también te amo y por eso propongo enmendarme;
y te pido perdón de todos mis pecados.

Amén."""
        ),
        DevotionalPrayer(
            id = "angel_guarda",
            pageNumber = 7,
            title = "Ángel de la Guarda y San Jorge Bendito",
            subtitle = "Oraciones de amparo, protección y cuidado",
            category = "Protección",
            content = """Ángel de la guarda, dulce compañía,
no me desampares ni de noche ni de día.
No me dejes solo, sé en todo mi guía,
sin ti soy chiquito y me perdería.

✦ ✦ ✦

San Jorge Bendito,
amarra tus animalitos con un cordoncito bendito,
para que no nos piquen ni a mí ni a mis hermanitos.

Amén."""
        ),
        DevotionalPrayer(
            id = "consagracion_maria",
            pageNumber = 8,
            title = "Consagración a María",
            subtitle = "Consagración diaria de todo nuestro ser a la Virgen",
            category = "Mariana",
            content = """¡OH SEÑORA MÍA, OH MADRE MÍA!,
YO ME OFREZCO TODO A TI Y,
EN PRUEBA DE MI FILIAL AFECTO,
TE CONSAGRO EN ESTE DÍA MIS OJOS,
MIS OÍDOS, MI LENGUA, MI CORAZÓN.

EN UNA PALABRA,
TODO MI SER.

YA QUE SOY TODO TUYO,
OH MADRE DE BONDAD,
GUARDADME Y PROTEGEDME
COMO COSA Y POSESIÓN TUYA.

AMÉN."""
        ),
        DevotionalPrayer(
            id = "dame_alguien_para_amar",
            pageNumber = 9,
            title = "Dame Alguien Para Amar",
            subtitle = "Plegaria de entrega al prójimo",
            category = "Caridad y Servicio",
            author = "Santa Teresa de Calcuta",
            content = """Señor, cuando tenga hambre, dame alguien que necesite comida;
cuando tenga sed, dame alguien que precise agua;
cuando sienta frío, dame alguien que necesite calor.

Cuando sufra, dame alguien que necesite consuelo;
cuando mi cruz parezca pesada, déjame compartir la cruz de otro;
cuando me vea pobre, pon a mi lado algún necesitado.

Cuando no tenga tiempo, dame alguien que precise de alguno de mis minutos;
cuando sufra humillación, dame ocasión para elogiar a alguien;
cuando esté desanimada, dame alguien para darle nuevos ánimos.

Cuando quiera que los otros me comprendan, dame alguien que necesite mi comprensión;
cuando sienta necesidad de que cuiden de mí, dame alguien a quien pueda atender;
cuando piense en mí misma, vuelve mi atención hacia otra persona.

Haznos dignos, Señor, de servir a nuestros hermanos,
dales, a través de nuestras manos, no sólo el pan de cada día,
también nuestro amor misericordioso, imagen del tuyo.

Amén."""
        ),
        DevotionalPrayer(
            id = "angel_paz",
            pageNumber = 10,
            title = "Oración del Ángel de la Paz",
            subtitle = "Oración enseñada por el Ángel en Fátima",
            category = "Reparación y Adoración",
            content = """Oh Santísima Trinidad, Padre, Hijo y Espíritu Santo,
Te adoro profundamente.

Te ofrezco el preciosísimo Cuerpo, Sangre, Alma y Divinidad de Jesucristo,
presente en todos los sagrarios del mundo,
en reparación de los ultrajes, sacrilegios e indiferencias con que Él es ofendido.

Por los méritos infinitos del Sagrado Corazón de Jesús
y del Inmaculado Corazón de María,
suplico la conversión de los pobres pecadores.

Amén."""
        ),
        DevotionalPrayer(
            id = "comunion_espiritual",
            pageNumber = 11,
            title = "Comunión Espiritual",
            subtitle = "Deseo ferviente de recibir a Jesús en el alma",
            category = "Eucarística",
            content = """Creo, Jesús mío, que estás real y verdaderamente presente en el Santísimo Sacramento del Altar.

Te amo sobre todas las cosas y deseo ardientemente recibirte dentro de mi alma; pero no pudiendo hacerlo ahora sacramentalmente, ven al menos a mi corazón espiritualmente.

Y como si ya te hubiera recibido, me abrazo y me estrecho íntimamente a Ti.

¡Oh! Señor, no permitas que me separe de Ti.

Amén."""
        ),
        DevotionalPrayer(
            id = "padre_chino",
            pageNumber = 12,
            title = "Oración por mi hermano sacerdote fallecido",
            subtitle = "En memoria y oración por el Padre Chino",
            category = "Sacerdotal y Conmemorativa",
            content = """Señor:
Tengo un hermano que te ofrecimos desde que abrimos juntos los ojos a la vida. Tú pusiste en su corazón el deseo de seguirte, y hoy no te pido que nos lo devuelvas, pues sé que ha regresado a tu lado. Te doy gracias porque, habiéndolo llamado, lo mantuviste como un instrumento digno de tu gracia y fue para nosotros un santo sacerdote.

Señor:
Sus manos, las manos de mi hermano con las que jugué y crecí, fueron ungidas con el Óleo Santo y se transformaron en manos que sanaron y consolaron. Gracias porque a través de su ministerio dio vida a los que sufrían y devolvió la esperanza a los que habían perdido el camino. Que ahora esas manos descansen en las tuyas.

Señor:
Fue un camino de entrega total, y hoy mi corazón, aunque con nostalgia, se llena de paz al saber que ya no está frente al Altar de la tierra, sino en el Banquete del Cielo. Gracias por haberme permitido estar cerca de él en cada Eucaristía, escuchando cómo te hacía bajar del cielo con esa misma voz con la que tantas veces me llamó: ¡Hermano!

Señor:
Pongo nuevamente en tus manos mi mayor recuerdo y mi esperanza: recibe en tu gloria a mi hermano, tu sacerdote, y permítele gozar de la luz que no tiene fin.

Amén."""
        ),
        DevotionalPrayer(
            id = "bajo_tu_amparo",
            pageNumber = 17,
            title = "Bajo Tu Amparo",
            subtitle = "La oración mariana más antigua que se conserva (Sub Tuum Praesidium)",
            category = "Mariana",
            content = """Bajo tu amparo nos acogemos, Santa Madre de Dios,
no desprecies las súplicas que te hacemos en nuestras necesidades,
antes bien, líbranos de todos los peligros,
oh Virgen gloriosa y bendita.

Amén."""
        )
    )
}
