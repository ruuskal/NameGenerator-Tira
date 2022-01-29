# Viikko 2

Käytettyjä tunteja: 10h.

En ole aiemmin luonut oikeastaan mitään projektia aivan alusta alkaen näin
 vähäisillä ohjeistuksilla, joten kaikenlaiseen alkuihmettelyyn meni aikaa.
 Projektin luomisen jälkeen huomasin, että käytössäni on aika vanha Gradlen
 versio (4.4.1), minkä takia ohjeita mm. JaCoCon ja Checkstylen lisäämisestä
 sai etsiä kauan. Lisäksi en saanut aikaiseksi Gradle-wrapperia. Pidin tätä
 tutkimista kuitenkin erittäin opettavaisena kokemuksena, sillä tuli paremmin
 tutustutta näiden käyttämiseen ja dokumentaatioon.

Aloin toisaalta epäilemään, voinko jatkaa projektia näin vanhalla Gradlen versiolla,
 vai tuleeko siitä ongelmia esim. vertaisarvioinnin vaiheessa. Ja onko Gradle
 wrapper syytä saada aikaiseksi?

Varsinaisen algoritmin puolella sain tehtyä trie-rakennelman ja sanan
 lisäämis- ja etsimismetodit. Hahmottelin myös ensimmäisen asteen Markovin
 ketjun generoivaa metodia. Ongelmia tuotti nullPointereiden välttäminen,
 niitä tuli aluksi jatkuvasti, jos seuraavaksi käsittelyyn otettavalla
 nodella ei ollutkaan lapsia. Algoritmissä on ainakin muutama kohta, jotka
 vaativat vielä ratkaisua:
* Sanan aloitus: tällä hetkellä nimen generointi aloitetaan aina ensimmäisestä
 ei-tyhjästä indeksistä, eli käytännössä a:sta. Tätä voisi muuttaa niin, että alkuun voisi valita aina
 suosituimman tai satunnaisen kirjaimen.
* Sanan lopetus on hieman sekava, algoritmi tarkistaa onko kirjaimesta
 mahdollista jatkaa ja onko se merkattu viimeiseksi kirjaimeksi, joten lopetus
 voi tapahtua hyvinkin nopeasti. Viimeiseksi merkattu kirjain ei myöskään aina
 lopeta nimen generoimista, sillä silmukassa on kaksi vaihetta. Tähän täytyy
 keksiä ratkaisu, jossa nimen lopettaminen tapahtuu (kuten viime palautteessa
 vinkattiin) useamman viimeisen kirjaimen perusteella. 

En oikein keksinyt testattavaa nykyisestä algoritmistä, sillä aion sitä
 kuitenkin muokata pian.

Ensi viikolla aion jatkaa näiden asioiden parissa ja kehitellä lukijan, joka
 lukee nimet tiedostosta, jotta saadaan suurempi aineisto jo tässä vaiheessa
 käyttöön. Ohjelman rakenteen voisi myös laittaa kuntoon, ettei kaikki koodi mene
 samaan luokkaan. 

