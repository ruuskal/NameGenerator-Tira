# Testaus

Yksikkötestaamista
 seurataan JaCoCon avulla. On pyritty testaamaan sellaisia tilanteita, jotka
 potentiaalisesti voisivat kaataa ohjelman (nullpointterit). Lisäksi on
 pyritty varmistamaan, että generoidut nimet ovat sen hetken sääntöjen
 mukaisia.

## Yksikkötestaus

Testit on laadittu noudattaen seuraavia sääntöjä:

### Trie

- Trie tallentaa kaikki mahdolliset 2 >= mittaiset merkkijonot, ja ne ovat 
myös löydettävissä haku-toiminnolla. Eli syöte "kari" tuottaa merkkijonot 
"kari", "ari" ja "ri". Esim. jonoa "ar" ei tallenneta



### Generaattori

- Suosituimman noden palauttava metodi palauttaa suosituimman noden indeksin tai
 tasatilanteessa sen, jolla on suurempi indeksiluku (eli a:n ja b:n ollessa yhtä 
suosittuja valitaan b).
- Jos trien suosituin oksa ei ole tarpeeksi syvä halutulle asteelle, niin 
nimen generoiva metodi palauttaa niin pitkän merkkijonon, kuin se löytää.
- Jos trie on tyhjä (tai siihen on syötetty vain yhden merkin mittaisia jonoja),
 niin generointimetodi palauttaa tyhjän merkkijonon.

![raportti](https://github.com/ruuskal/NameGenerator-Tira/blob/main/Dokumentaatio/jacocoreport.pdf)

## Suorituskykytestaus
Trie-rakenteeseen tallentamisen nopeutta testattiin tallentamalla 
tiedosto "lastnames" triehen kymmenen kertaa. Tulokseksi saatiin: 
(169+170+164+177+166+192+165+159+158+160)/10 = 168 ms. Tämä on aika odotettu 
tulos. 

Koko ohjelman suorituskykyä testattiin kutsumalla metodia `generateName()` 
sata kertaa silmukan sisällä. Metodikutsun parametreilla ei vaikuttanut 
olevan yhteyttä metodin tehokkuuteen. Ensimmäisen nimen luomisesa kesti noin
 x sekuntia ja muiden nimien luominen q sekuntia. 
Nimen luomisessa kesti noin 25-40 millisekuntia riippumatta ketjun asteesta
 tai muista parametreistä. 

