# Toteutusraportti

Ohjelma koostuu trie-tietorakenteesta, lukijasta, generaattorista ja käyttöliittymästä.

`Loader`-olio, tallentaa 
tekstitedoston sisältämät sanat trie-rakenteeseen. Käyttöliittymän avulla 
käyttäjä voi määritellä minkälaisia nimiä haluaa generoida
 generaattorin avulla. Muuttuvia parametrejä ovat Markovin ketjun aste,
 nimen maksimipituus, ensimmäinen kirjain ja boolean arvo sille, halutaanko
nimen viimeisen kirjaimen olevan sellainen, joka opetusdatassakin esiintyy 
viimeisenä kirjaimena. Ohjelma toimii komentoriviltä. 

Trie-rakenne on toteutettu siten, että jokaisella solmulla N on K kokoinen 
 array-lista integer-arvoja, jossa jokaisen lapsisolmun indeksi toimii sen
 avaimena. Trie vie tilaa O(N*K), eli tällä data-aineistolla N=57 690 ja
 K=256. Aineistossa on yhteensä 45 308 ei-uniikkia nimeä. Yksi integer vie 
4 tavua, joten 57690*256*4 tavua = 59 074 560 tavua?

## Parannusehdotuksia

Ohjelman toimintaa voisi parantaa lisäämällä satunnaisuutta. Nyt ohjelma 
valitsee aina suosituimman aloituskirjaimen, jos sille ei erikseen syötetä 
alkukirjainta. Nimen ensimmäiset kirjaimet myös valitaan esimerkiksi 4. asteen
 ketjussa siten, että ensin valitaan suosituin solmu, sitten kyseisen solmun 
suosituin solmu ja näin edelleen neljänteen solmuun asti. 3. asteen ketjussa 
valitaan ensimmäiset kirjaimet täsmälleen samalla tavalla, jolloin 3. ja 4. 
asteen nimimillä on täsmälleen samat 3 aloituskirjainta. Koska nimet ovat 
tyypillisesti lyhyitä, niin tämä johtaa hyvin ennalta-arvattaviin nimiin. Tätä
 arvattavuutta voisi vähentää arpomalla toisinaan jonkin muun sopivista 
solmuista. 

Toinen parannusehdotus liittyy aineistoon. Ohjelmaan voitaisiin luoda 
 mahdollisuus käyttäjälle lisätä oman aineistonsa, jolloin aineiston kasvaessa 
myös generoitavat nimet olisivat mielenkiintoisimpia. 

Tilavaativuutta voitaisiin pienentää, sillä arrayn 97 ensimmäistä indeksiä 
(unicode merkit 0-96) jätetään tyhjiksi.  


## Lähteet

[Character-luokka](https://docs.oracle.com/javase/7/docs/api/java/lang/Character.html)

[Unicode-wiki](https://en.wikipedia.org/wiki/List_of_Unicode_characters)

[Markovin ketju -wiki](https://en.wikipedia.org/wiki/Markov_chain)

[Trie-rakenne](https://www.baeldung.com/trie-java)

[Nimiaineisto](https://www.avoindata.fi/data/fi/dataset/none)
