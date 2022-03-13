# Viikko 6

Käytettyjä tunteja 10h.

Alkuviikosta tein ohjelmaan vielä viimeisen toiminnallisuuden, eli ohjelma osaa nyt etsiä sellaisen 
lopetuksen nimelle, että viimeinen kirjain on aineistossakin esiintynyt viimeisenä kirjaimena vastaavssa 
paikassa. Toteutin tämän laajentamalla historiataulukkoa kaksikolmuniseksi, jolloin ensimmäisessä kolumnissa 
on suosituimman kirjaimen indeksi ja toisessa vastaavaan kohtaan sopiva lopetusindeksi. Jollei sopivaa
 lopetusindeksiä löydetä historian viimeisestä indeksistä, niin historiaa peruutetaan, kunnes sellainen löytyy.

Muutin myös ohjelman rakennetta niin, että kolmen generointimetodin sijaan on vain yksi, joka generoi nimen
 eri tavalla parametrien mukaan. Jaoin sitten tämän yhden ison metodin pienempiin osiin, jolloin yksikkötestausta 
piti myös muuttaa. Ensi viikolla hoidan dokumentoinnin kuntoon. Projekti on nyt sisällön puolesta valmis, jollei
 jollakin lisäyksellä ehtisi vielä 
vaikuttamaan kurssin loppuarvosanaan korottavasti?

Aloitin suorituskykytestauksen. Tein silmukan, jossa joka kierroksella arvotaan kirjain ja generoidaan 25-merkkinen
 nimi. Soutti on sen takia, että näen ohjelman tosiaan tekevän jotain. (Keskiarvo oli 0.35 ms, mutta jostain syystä 
ensimmäisellä kierroksella nopeus oli luokkaa 26ms ja lopuilla 0.20ms. Kuulostaako tällainen uskottavalta?)
 

`{

	for (int i=0; i<100; i++) 

            int r = (int) ((Math.random() * (122 - 97)) + 97);
	    
            String letter = Character.toString((char) r);
	    
            Long start = System.nanoTime();
	    
            String name = g.generateName(3, 25, letter, true);
	    
            Long stop = System.nanoTime();
	    
            Long t = stop-start;
	    
	    timeTotal = timeTotal + t;
	    
            System.out.println(t);
	    
            System.out.println(name);
	    
            
        }`

 Tein vastaavan mittauksen trie-puuhun
tallentamisesta. Onkohan tällainen suorituskykymittaus riittävä, vai pitäisikö tulokset vielä esittää taulukossa?
Ja olisiko syytä mitata suorituskykyä vielä jotenkin muutoin?
