# Viikkoraportti 1

Käytettyjä tunteja: 8h (+1,5h luento).

Tällä viikolla olen valinnut projektin aiheen ja tutustunut sen mahdolliseen toteuttamistapaan Aiheena on nimien generointi Markovin ketjujen avulla. Valitsin aiheen kurssimateriaalin aiheiden joukosta ja lähdin suunnittelemaan ohjelman toteutusta sen pohjalta. Ensin tutustuin yleisesti siihen, mitä ovat Markovin ketjut ja trie-puu. Kokeilin trie-rakenteen toteuttamista varsinaisen projektikansion ulkopuolella, mihin meni suurin osa tämän viikon työajasta. Etsin myös mahdollisia nimi-korpuksia, ja vaikuttaa siltä, että aineiston hankkiminen ei tule olemaan suuri haaste. 

Kurssilla matriisilaskennan sovelluksia esiteltiin lyhyesti Markovin prosessi, joten aihe ei ole täysin uusi, joskin trie-raknnelman liittäminen Markovin ketjuun on vaatinut aiheen tarkastelua matriisilaskennan ulkopuolelta.  

Suunnittelen totetuttavani ohjelman vain yhtä tietorakennelmaa käyttäen, eli trie-puuna, johon on tallennettu kaikki vähintään kahden merkin mittaiset jonot. Eli yksi nimi tallennettaisiin puuhun 'nimen_pituus-1' kertaa. Epäselväksi on jäänyt se, onko tämä ollenkaan hyväksyttävä tapa toteuttaa ohjelma, sillä sekä aika-, että tilavaativuudet kasvavat tämän ratkaisun myötä. Toisaalta taas toinen vaihtoehto olisi luoda taulukko, johon kaikki mahdolliset kirjainyhdistelmät ja niitä seuraava kirjain tallennettaisiin todennäköisyyksineen, jolloin aikaa ja tilaa menisi tähän toteutukseen. Tämä vaikuttaa olevan se tapa, jolla netistä löytämäni tekstigeneraattorit on toteutettu. 

Mietin myös, onko työ riittävän laaja. Tarkoitus on hyödyntää javan 'Character'-luokkaa, mutta pyrin pärjäämään ilman luokkia 'Hashtable' tai 'ArrayList'.

Trie-rakennelma on joka tapauksessa seuraava asia, jonka aion projektissa toteuttaa. Ensi viikon aikana olisi tarkoitus saada aikaan sanojen lukeminen tiedostosta sekä sanojen tallentaminen ja hakeminen trie-puusta. Hakeminen vaaditaan ainakin testausta varten.


