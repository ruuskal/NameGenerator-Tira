Tällä hetkellä projektissa on keskitytty vain yksikkötestaamiseen, jota
 seurataan JaCoCon avulla. On pyritty testaamaan sellaisia tilanteita, jotka
 potentiaalisesti voisivat kaataa ohjelman (nullpointterit). Lisäksi on
 pyritty varmistamaan, että generoidut nimet ovat sen hetken sääntöjen
 mukaisia. Säännöt muuttunevat vielä projektin edetessä (esimerkiksi
 generoitavan nimen lopetuksen ja aloituksen suhteen), joten testejä
 päivitetään aina toiminnallisuuden muuttuessa.

Testit on laadittu noudattaen seuraavia sääntöjä:
- trie tallentaa kaikki mahdolliset 2 >= mittaiset merkkijonot, jotka ovat 
myös löydettävisää haku-toiminnolla. Eli syöte "kari" tuottaa merkkijonot 
"kari", "ari" ja "ri". Esim. jonoa "ar" ei tallenneta.
- Suosituimman noden palauttava metodi palauttaa suosituimman noden indeksin tai
 tasatilanteessa sen, jolla on suurempi indeksiluku (eli a:n ja b:n ollessa yhtä 
suosittuja valitaan b).
- Jos trien suosituin oksa ei ole tarpeeksi syvä halutulle asteelle, niin 
nimen generoiva metodi palauttaa niin pitkän merkkijonon, kuin se löytää.
- Jos trie on tyhjä (tai siihen on syötetty vain yhden merkin mittaisia jonoja),
 niiin generointimetodi palauttaa tyhjän merkkijonon.

![raportti](https://github.com/ruuskal/NameGenerator-Tira/blob/main/Dokumentaatio/jacocoreport.pdf)
