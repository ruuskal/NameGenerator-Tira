# Testaus

Yksikkötestaamista
 seurataan JaCoCon avulla. On pyritty testaamaan sellaisia tilanteita, jotka
 potentiaalisesti voisivat kaataa ohjelman (kuten nullpointterit). Lisäksi on
 pyritty varmistamaan, että generoidut nimet ovat sen hetken sääntöjen
 mukaisia. Luokat `UI` ja `Loader` jätettiin testauksen ulkopuolelle.

## Yksikkötestaus

Testit on laadittu noudattaen seuraavia sääntöjä:

### Trie

- Trie tallentaa kaikki mahdolliset 2 >= mittaiset merkkijonot, ja ne ovat 
myös löydettävissä haku-toiminnolla. Eli syöte "kari" tuottaa merkkijonot 
"kari", "ari" ja "ri". Esim. jonoa "ar" ei tallenneta

### Generaattori

- Suosituimman lapsisolmun palauttava metodi palauttaa suosituimman solumun indeksin tai
 tasatilanteessa sen, jolla on suurempi indeksiluku (eli a:n ja b:n ollessa yhtä 
suosittuja valitaan b).
- Jos trien suosituin oksa ei ole tarpeeksi syvä halutulle asteelle, niin 
nimen generoiva metodi palauttaa niin pitkän merkkijonon, kuin se löytää.
- Jos trie on tyhjä (tai siihen on syötetty vain yhden merkin mittaisia jonoja),
 niin generointimetodi palauttaa tyhjän merkkijonon.

![raportti](https://github.com/ruuskal/NameGenerator-Tira/blob/main/Dokumentaatio/testreport.html)

## Suorituskykytestaus
Trie-rakenteeseen tallentamisen nopeutta testattiin tallentamalla 
tiedosto "lastnames.txt" triehen kymmenen kertaa. Tiedostossa on 22909 uniiikkia
 nimeä. Tulokseksi saatiin:
 
(169+170+164+177+166+192+165+159+158+160)ms/10 = 168 ms.

Koko ohjelman suorituskykyä testattiin kutsumalla metodia `generateName()` 
kymmenen kertaa silmukan sisällä. Metodikutsun parametreilla ei vaikuttanut 
olevan yhteyttä metodin tehokkuuteen, mutta annetu tulos saatiin generoimalla 
25 merkkisiä nimiä neljännen asteen ketjulla ja joka kierroksella 
vaihtuvalla satunnaisella alkukirjaimella. Tulokseksi saatiin:

(29.026996+0.281273+0.300946+0.222576+0.222623+0.234783+0.246808+0.224557
+0.126380+0.122208)ms/10 = 31.00 ms
 
Ensimmäisen nimen luomisessa kesti noin 29 ms, jonka jälkeen jokaisen 
nimen luomiseen meni huomattavasti vähemmän aikaa, noin 0.22 ms. Tämän 
perusteella algoritmi toimii nopeasti, kuten trie-rakenteen oletetaankin
toimivan.


