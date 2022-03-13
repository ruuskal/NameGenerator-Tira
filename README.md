# NameGenerator-Tira

NameGenerator on Tietorakenteet ja algoritmit -kurssin harjaoitussovellus. 
Ohjelman on tarkoitus generoida valmiiksiannetusta nimi-aineistosta nimiä 
Markovin ketjuja mukaillen. Sovellus on toteutettu Javalla ja Gradlella (4.4.1).

## Dokumentointi
[Viikkoraportit](https://github.com/ruuskal/NameGenerator-Tira/tree/main/Dokumentaatio)

[Määrittelydokumentti](https://github.com/ruuskal/NameGenerator-Tira/blob/main/Dokumentaatio/M%C3%A4%C3%A4rittelydokumentti.md)

[Testausdokumentti](https://github.com/ruuskal/NameGenerator-Tira/blob/main/Dokumentaatio/Testausdokumentti.md)

[Toteutusraportti](https://github.com/ruuskal/NameGenerator-Tira/blob/main/Dokumentaatio/Toteutusraportti.md)

[Käyttöohje](https://github.com/ruuskal/NameGenerator-Tira/blob/main/Dokumentaatio/K%C3%A4ytt%C3%B6ohje.md)
## JaCoCo ja Checkstyle

Testiti saa ajettua komennolla `gradle test.` Testikattavuusraportti löytyy
 polusta */build/jacocoHtml/index.html* ja Checkstylen raportti polusta
 */build/reports/checkstyle/main.html*.

## Nimiaineisto

Nimiaineisto on Digi- ja väestöviraston ylläpitämä rekisteri Suomessa 
esiintyvistä nimistä, ja se on ladattu 3.2.2022  
[Avoin data -palvelusta](https://www.avoindata.fi/data/fi/dataset/none).
