# Viikko 3

Käytettyjä tunteja: 16h

Tällä viikolla loin luokan Generator, johon siirsin ensimmäisen asteen ketjulla 
sanoja tuottavan metodin. Muokkasin tätä metodia niin, että se ottaa aina
 suosituimman vaihtoehdon seuraavaksi kirjaimeksi. Loin luokkaan toisen asteen
 Markovin ketjulla nimiä tuottavan metodin, joka toimii samojen periaatteiden
 mukaan. Myöhempään ajankohtaan jäi nimien lopettamistavan ratkaiseminen,
 nyt ketju pyörii, kunnes seuraavaa kirjainta ei ole, tai ketjun pituus on 
10 merkkiä. 

Kirjoitin joitain testejä Markovin ketjujen avulla generoivalle metodille.
Yleensä testaaminen on tuntunut pakolliselta pahalta, mutta tässä proektissa
huomaan sen olevan oikeasti hyödyllistä; muuttaessani metodia on helpompi 
huomata, jos jokin aiemmin kunnossa ollut asia rikkoutuu muutoksen yhteydessä. 
Tämähän toki on testien tarkoitus, mutta on mukavaa havaita tämä käytännösä.
Tosin on haastava löytää kaikki mahdolliset ongelmakohdat, joihin testaamisen
 kohdistaisi.  

Loin Digi- ja väestöviraston ylläpitämästä tilastosta txt-tiedostot Suomessa
 esiintyvistä etu- ja sukunimistä. Alkuperäinen data oli xls-muodossa, joten
 helpoimmalta tuntui kopioida nimet kolmeen eri tekstitedostoon (miehet, naiset,
 sukunimet) niin, että jokaisella rivillä on yksi nimi. Loin resource-kansion
ja tallensin tiedostot sinne, niitä ei ole tarkoitus muokata ohjelman suorittamisen
aikana. Hämärä muistikuvani OhTusta on, että tekstitiedostoja ei saisi sellaisenaan
puskea GitHubiin, vaan ne pitäisi tehdä jotenkin propertien kautta. Jätin 
resourssit siis nyt vielä pois versionhallinnasta, koska haluaisin tähän 
varmistuksen.

Voiko resources-kansion puskea GitHubiin sellaisenaan, jos sen on tarkoitus 
pysyä staattisena?

Seuraavaksi aion parannella jo olemassa olevia metodeja; saisiko nimen loppumaan
järkevämmin ja voisiko sille asettaa ehtoja mm. pituuden suhteen. Nimen
voisi myös aloittaa esim. satunnaisesta kirjaimesta, jolloin voitaisiin generoida
useita eri nimiä samasta triestä. Luonnollisesti aion myös pävittää testejä.
 Jonkinlainen simppeli 
käyttöliittymä voisi olla myös hyvä vertaisarviota varten.
