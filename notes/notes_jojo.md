# Minecraft Water Problem
- Abstrahierte Version des Spiels Minecraft
- Die Welt kann als 3D-Array von Blöcken gesehen werden
- Der Spieler kann nicht eingreifen
- Die Welt wird in zyklischen Ticks geupdatet, Änderungen die während eines Ticks passieren, passieren "gleichzeitig"
    - wir arbeiten in Wasser "Updates" (1 Tick = Wasser-Updatet)
    - Blöcke interagieren pro Tick miteinander und können so die Welt verändern
    - Block-Update
        - wir fragen alle Blöcke wie sie die Welt verändern wollen? (wo will Wasser hinfließen, wo will Sand hinfallen)
        - entscheiden des Outcomes anhand von Regeln, wenn mehrere Änderungen an einen Block stattfinden
            - Block ersetzt immer Wasser
            - Wasser ersetzt immer Tripwire
            - Höchstes Wasserlevel gewinnt
            - 2 Flusswasser auf Max Level => statisches Wasser
        - Anzahl der Blöcke die bei Update berücksichtigt werden ist konstant
- Zulässige Blöcke
    - Wolle (in 16 Farben) [wiki](https://minecraft.fandom.com/de/wiki/Wolle)
    - Glas [wiki](https://minecraft.fandom.com/de/wiki/Glas)
    - Goldblock [wiki](https://de.minecraft.wiki/w/Goldblock)
    - Lapislazuliblock
    - Stolperdraht [wiki](https://de.minecraft.wiki/w/Stolperdraht)
        - wird von wasser zerstört, wenn es auf gleiche Koordinate fließt
    - Sand 
        - fällt nach unten
        - Annehmen, dass Sand bei einem Block-Update einen Block nach unten fällt => keine Entities
        - Fallen aus der Welt berücksichtigen => verschwindet
    - Wasser
        - zerfließt (umbedingt beschreiben)
        - [Wiki Wasser](https://minecraft.fandom.com/wiki/Water)
        - [Wiki Fließen](https://minecraft.fandom.com/wiki/Fluid#Spread)
        - Beschreiben: wie wird Fluss aus der Welt berücksichtigt?
    - Wolle, Glas, Goldblock und Lapislazuliblock haben keine besonderen Nebeneffekte, die in Kombination mit anderen Blöcken und unseren Umständen auftreten könnten.


## Language WATERGOLD:
- Gegebene Welt mit den Maßen x_range * y_range * z_range = n Blöcke
- Eine Welt X ist teil der Sprache WATERGOLD, wenn es eine Teilmenge der Lapislazuliblöcke gibt, sodass alle Goldblöcke mit Wasser bedeckt (= Block direkt darüber), wenn
    - man auf allen Lapislazuliblöcken in dieser Teilmenge gleichzeitig Wasser platziert und dieses so lange zerfließen lässt, bis sich bei einem Update nichts mehr verändert

# WATERGOLD is in NP
- Welt aus n = x_range * y_range * z_range Blöcken
- Laufzeit eines Ticks:
    - Update von n Blöcken
    - Berücksichtgung der anliegenden Blöcken (Anzahl der Blöcke die bei Update berücksichtigt werden ist konstant)
    - lineare Laufzeit
- Beweisen, dass sich, wenn sich bei einem Update nichts mehr ändert, nie wieder etwas ändern wird? (Alle Wechselwirkungen zwischen Blöcken sind determinisitisch => wiederholung des Update auf selbem Ausgangszustand liefert gleiches Ergebnis)
- Maximale Anzahl an Ticks bis das Wasser nach einer Veränderung von soliden Blöcken ausgeflossen ist
    - 7 * y_range
    - Veränderungen nurch durch Tripwire möglich
    - t < n Tripwire => maximal t veränderungen
- Worst-Case: Wasser triggert in letztem Tick, wo sich etwas ändern würde eine Veränderung
    - Worst-case Tick Laufzeit: t * 7 * y_range
    - => O(n^2)

Daraus folgt:
- Zertifikat: Assigment der Lapis-Blöcke
    - Boolean Array, abhängig von den Lapis-Blöcken
    - => länge polynomiell abhängig von der länge des Inputs
- Verifier: Platzieren eines Assignments und laufen lassen
    - Wasser fließt maximal polynomiell

Wenn der Verifier polynomiell ist das Problem des Finden des Assignments auf jeden Fall in EXP und somit in NP => Brutofrocing





- Gebene 3D-Welt mit den Maßen x_range * y_range * z_range und l Lapislazuliblöcken (<=> Variablen) und g Goldblöcken (<=> Clauses) darin, mit folgenden Abhängigkeiten
    - x_range = linear(l)
    - y_range = linear(l, g)
    - z_range = linear(g)
    - => die Weltgröße ist polynomiell abhängig von der Anzahl der Gold- und Lapisblöcke
        Anzahl Blöcke n = poly(l, g)

    
