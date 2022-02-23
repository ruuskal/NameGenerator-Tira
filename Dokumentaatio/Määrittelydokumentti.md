# Määrittelydokumentti

Opintohjelma: tietojenkäsittelytieteen kandidaatti
Projektin kieli: java (mieluiten vertaisarvioisin vain muita java-projekteja)
Dokumentaation kieli: suomi

## Aihe
Tarkoituksena on rakentaa nimi-generaattori. Generaattori muodastaa annetun nimi-korpuksen avulla 
nimen ja nimikorpuksena toimii Digi- ja väestöviraston ylläpitämä data Suomessa esiintyvistä nimistä (naisten 
ja miesten etunimet sekä sukunimet). Nimet generoidaan Markovin ketjun avulla ja käyttäjä saa itse valita
 ketjun asteen, mikä määrittää generoitavan nimen satunnaisuutta. Tarkoitus siis on, että generoiduissa nimissä
 on havaittavissa eroja eri asteiden välillä (ensimmäisen asteen nimi vaikuttaa satunnaisemmalta kuin
 kolmannen asteen nimi).

Valitsin kyseisen aiheen, sillä olen yleisesti ottaen kiinnostunut laskennallisesta luovuudesta ja luonnollisen
 kielen koneellisesta käsittelystä. Aiheesta vaikuttaa olevan suhteellisen paljon tietoa saatavilla ja
 esimerkkiprojekteja on paljon, suurin osa tosin python-kirjastoilla toteutettuna. Harkitsin myös kokonaisten
 lauseiden generoimista, mutta koska tämä on ensimmäinen kosketukseni aiheeseen, tuntui lyhyempien syötteiden
 käsittely turvallisemmalta vaihtoehdolta.

## Tietorakenteet ja algoritmit
Nimikorpus tallennetaan trie-rakenteeseen niin, että jokainen solmu vastaa yhtä kirjainta. Kirjain löytyy aina
 sen unicode-arvon mukaisesta indeksistä (esim. a -> 97). Jokaiseen solmuun tallennetaan tieto sen 
"todennäköisyydestä" ja siitä, onko se nimen viimeinen kirjain. Kun uusi solmu luodaan, sen kauttakulkujen
 arvoksi alustetaan 1, minkä jälkeen arvoa korotetaan jokaisen
 "kauttakulun" myötä.

Nimet tallennetaan puuhun kaikkina mahdollisina vähintään kahden merkin
 pituisina jonoina, jolloin esimerkiksi nimi "anni" tallennettaisiin kolmena eri syvyisenä instanssina:
 "juuri - a - n - n - i", "juuri - n - n - i" ja "juuri - n - i". Tämä syö tilaa, mutta tällä ratkaisulla ei
 liene olisi tarvetta toteuttaa muita tietorakenteita.

Oletetaan, että puussa on nimet "anni", "anton" ja "antti" ja käyttäjä haluaa muodostaa nimen käyttäen
 ensimmäisen asteen Markovin ketjua ja alkukirjainta a. Tällöin ohjelma valitsee a:n ja tämän lapsista
 suosituimman solmun ("juuri - a - n"), minkä jälkeen palataan juureen ja kuljetaan puussa aiemmin valitun
 solmun kautta, jonka perusteella valitaan todennäköisin seuraava solmu tai aakkosissa viimeisenä tuleva
 kirjain ("juuri - n - t") ja jatketaan tätä, kunnes valittu solmu on merkattu viimeiseksi kirjaimeksi
 ("juuri - t - i"). Jos valittu kirjain on merkattu viimeiseksi kirjaimeksi, niin ohjelma päättyy ja palauttaa
 generoidun nimen ("anti"). Lisäksi ohjelma tarkistaa, että generoitava nimi on tarpeeksi pitkä, eli ohjelma
 ei saa pysähtyä joka kerta, kun se kohtaa mahdollisen sanan lopun.

### Aika- ja tilavaativuudet

Projektissa käytetää indekseinä kirjainten unicode
 arvoja, jotka selvitetään Javan `charAt()`-metodilla. Indeksit ovat siis 0-255, 
jolloin saadaan tallennettua myös ä:t ja ö:t.  
 Suurin osa näistä jää tyhjiksi, joten tiivistäminen olisi mahdollista. Tilan kompleksisuus on
 luokkaa O(N*K), missä N on kaikkien trien solmujen määrä ja K on indeksien määrä yhdessä solmussa, eli tässä
 tapauksessa K=256. Huonona puolena on myös se, että ohjelman toimivuus on riippuvainen syötteen
 unicode-arvoista, eli esimerkiksi kyrilliset kirjaimet eivät kelpaa syötteeksi.

Trie-rakenteen aikavaativuus on yleisesti ottaen hyvä, luokkaa O(M), missä M on syötteen maksimipituus.
 Omassa toteutuksessani tämä tulee tosin olemaan suurempi, sillä jokainen yksittäinen M-pituinen nimi syötetään
 M-1 kertaa. Yhden sanan lisääminen trie-puuhun tapahtuu yhden for-silmukan sisällä, eli aikavaativuus on
 luokkaa O(M).
 
### Lähteet:
[Character-luokka](https://docs.oracle.com/javase/7/docs/api/java/lang/Character.html)
[Unicode wiki](https://en.wikipedia.org/wiki/List_of_Unicode_characters)
[Markovin ketju wiki](https://en.wikipedia.org/wiki/Markov_chain)
[Trie-rakenne](https://www.baeldung.com/trie-java)
TiRa-moniste 2020

