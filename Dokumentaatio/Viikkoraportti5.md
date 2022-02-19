# Viikko 5

Työtunteja 19h (sis. vertaisarvioinnin)


Tällä viikolla pyrin parantamaan viimeviikon lopulla aikaansaamaani 
generointi-metodia. Halusin saada siihen hieman enemmän valinnan mahdollisuuksia, 
mutta myös selkeyttää sitä ja tehdä siitä tehokkaamman.

Sain karsittua yhden silmukan, jossa siirrettiin historiaa 
joka kierroksella "pykälä eteenpäin". Korvasin tämän yhdellä historialla, johon 
merkataan kaikkien valittujen nodien indeksit. Tämä myös selkeytti mielestäni 
metodin rakennetta, sillä nyt indeksien muuttaminen String-muotoon tapahtuu 
erillisessä apumetodissa, jota kutsutaan vain varsinaisen generointi-metodin 
päätyttyä. 

Sain tehtyä myös metodin, jolla käyttäjä voi valita, millä kirjaimella nimi 
alkaa. Tälle ei ole vielä testejä. Käytin myös paljon aikaa sen kehittämiseen, 
että saisin valittua nimen lopetuksen siten, että sille olisi olemassa oleva 
lopetus. Tämä onnistui yhden tason verran (eli tilanteesta "pekk" mennään 
tilanteeseen "pek" ja sitä esim. "peka"), mutta jos edelliselläkään nodella 
ei ole lopetusnodea lapsena, niin historian muuttaminen tämän enempää ei vielä
 onnistunut. Kurssi alkaa lähestyä 
loppuaan, joten voi olla, että tämä jää toteuttamatta tämän paremmin.

Ensi viikolla aion kokeilla vielä lopettamisen säännönmukaistamista, mutta 
pääpaino tulee olemaan testauksen ja dokumentoinnin viimeistelyssä.  
