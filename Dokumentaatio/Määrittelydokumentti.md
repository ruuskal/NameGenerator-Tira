# Määrittelydokumentti

Opintohjelma: tietojenkäsittelytieteen kandidaatti
Projektin kieli: java (mieluiten vertaisarvioisin vain muita java-projekteja)
Dokumentaation kieli: suomi

## Aihe
Tarkoituksena on rakentaa nimi-generaattori. Generaattori muodastaa annetun nimi-korpuksen avulla 
nimen. Käyttäjä saa joko syöttää (tekstinä) oman korpuksensa tai valita jo olemassa olevista korpuksista mieleisensä. Olen jo löytänyt 8000 nimen aineiston englanninkielisistä etunimistä ja uskon löytäväni myös muita laajoja aineistoja. Nimet generoidaan Markovin ketjun avulla ja käyttäjä saa itse valita ketjun asteen, mikä määrittää generoitavan nimen satunnaisuutta suhteessa aineistoon. Tarkoitus siis on, että generoiduissa nimissä on havaittavissa eroja sekä eri asteiden välillä (ensimmäisen asteen nimi vaikuttaa satunnaisemmalta kuin kolmannen asteen nimi) kuin myös valitun aineiston välillä (suomenkielinen aineisto tuottaa selvästi erilaisia nimiä kuin englanninkielinen aineisto).

Valitsin kyseisen aiheen, sillä olen yleisesti ottaen kiinnostunut laskennallisesta luovuudesta ja luonnollisen kielen koneellisesta käsittelystä. Aiheesta vaikuttaa olevan suhteellisen paljon tietoa saatavilla ja esimerkkiprojekteja on paljon, suurin osa tosin python-kirjastoilla toteutettuna. Harkitsin myös kokonaisten lauseiden generoimista, mutta koska tämä on ensimmäinen kosketukseni aiheeseen, tuntui lyhyempien syötteiden käsittely turvallisemmalta vaihtoehdolta.

## Tietorakenteet ja algoritmit
Nimikorpus tallennetaan trie-rakenteeseen niin, että jokainen solmu vastaa yhtä kirjainta. Kirjain löytyy aina sen unicode-arvon mukaisesta indeksistä (esim. a -> 97). Jokaiseen solmuun voi nähdäkseni tallentaa myös tiedon seuraavien mahdollisten solmujen todennköisyyksistä, joten muita tietorakenteita ei tarvinne toteuttaa. Kun uusi solmu luodaan, sen kauttakulkujen arvoksi alustetaan 1, minkä jälkeen arvoa korotetaan jokaisen "kauttakulun" myötä.

Ajatukseni on tällä hetkellä tallentaa kaikki korpuksen nimet kaikkina mahdollisina vähintään kahden merkin pituisina jonoina, jolloin esimerkiksi nimi 'Anni' tallennettaisiin kolmena eri syvyisenä instanssina: 'juuri - A - n - n - i', 'juuri - n - n - i' ja 'juuri - n - i'. Tämä syö tilaa, mutta tällä ratkaisulla ei liene olisi tarvetta toteuttaa muita tietorakenteita.

Oletetaan, että puussa on nimet 'Anni', 'Anton' ja 'Antti' ja käyttäjä haluaa muodostaa nimen käyttäen ensimmäisen asteen Markovin ketjua. Tällöin ohjelma valitsee suosituimman ison kirjaimen ja tämän lapsista suosituimman solmun ('juuri - A - n'), minkä jälkeen palataan juureen ja kuljetaan puussa aiemmin valitun solmun kautta, jonka perusteella valitaan todennäköisin seuraava solmu tai aakkosissa ensin tuleva kirjain ('juuri - n - t') ja jatketaan tätä, kunnes valittu solmu on merkattu viimeiseksi kirjaimeksi ('juuri - t - i'). Jos valittu kirjain on merkattu viimeiseksi kirjaimeksi, niin ohjelma päättyy ja palauttaa generoidun nimen ('Anti'). Lisäksi ohjelma tarkistaisi, että generoitava nimi on tarpeeksi pitkä, eli ohjelma ei saa pysähtyä joka kerta, kun se kohtaa mahdollisen sanan lopun.

Triessä käytetyn akkoston koko vaikuttaa sen tilan kompleksisuuteen. Oletuskena kaikki aineistot ovat latinalaisia aakkosia, joten tavitaan ainakin merkit a-ö ja A-Ö. Javan 'charAt()'-metodi palauttaa merkin unicode-arvon 'int'-muodossa. Suomenkielisten nimien kirjaamiseen vaadittaisiin tällöin indeksit 0-255. Suurin osa näistä jää tyhjiksi, joten tiivistäminen olisi kai teoriassa mahdollista. Tilan kompleksisuus on luokkaa O(N*K), missä N on kaikkien trien solmujen määrä ja K on indeksien määrä yhdessä solmussa, eli tässä tapauksessa K=255. Huonona puolena on myös se, että ohjelman toimivuus on riippuvainen syötteen unicode-arvoista, eli esimerkiksi kyrilliset kirjaimet eivät kelpaa syötteeksi.

Koska ohjelma käsittelee aina rajallisen määrän aineistoa kerrallaa, on mahdolliset tilasiirtynätkin rajalliset. Kyseessä on siis diskreetti Markovin ketju. Oletuksena Markovin ketjulle on se, että vain edellinen (tai edelliset, ketjun asteesta riippuen) tila vaikuttaa seuraavaan tilaan.  

Trie-rakenteen aikavaativuus on yleisesti ottaen hyvä, luokkaa O(M), missä M on syötteen maksimipituus. Omassa toteutuksessani tämä tulee tosin olemaan suurempi, sillä jokainen yksittäinen M-pituinen nimi syötetään M-1 kertaa. Yhden sanan lisääminen trie-puuhun tapahtuu yhden 'for'-silmukan sisällä, eli aikavaativuus on luokkaa O(M). Tämän jälkeen otetaan nimestä ensimmäinen merkki pois ja lisätään yhtä lyhyempi syöte puuhun ja tämä toistetaan M-1 kertaa. Rekursion takia aikavaatimus nousee O(M)*O(M-1)=O(M^2-M)=O(M^2). Koska tarkoituksena on käsitellä suhteellisen lyhyitä merkkijonoja (nimet ovat harvoin yli 10 merkkiä pitkiä), olisi tämä hyväksyttävä aikavaativuus tähän algoritmiin, vaikka yleisesti ottaen se on huono. Voi olla, että päädyn kuitenkin toteuttamaan projektin käyttäen hyväksi jotain muuta keinoa.

### Lähteet:
[Character-luokka](https://docs.oracle.com/javase/7/docs/api/java/lang/Character.html)
[Unicode wiki](https://en.wikipedia.org/wiki/List_of_Unicode_characters)
[Markovin ketju wiki](https://en.wikipedia.org/wiki/Markov_chain)
[Trie-rakenne](https://www.baeldung.com/trie-java)
TiRa-moniste 2020

