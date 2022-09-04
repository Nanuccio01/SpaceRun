# SpaceRun

## Descrizione:
SpaceRun è un avventura testuale con ambientazione horror fantascientifica, il cui obiettivo è la fuga verso la salvezza. Accompagnato da un interfaccia grafica simil terminale tecnlogico anni 80', l'utente dovrà muoversi tra le stanze di una navicella aliena in cerca di indizi ed oggetti, mai banali, per sbloccare eventi e nuove parti inizialmente inaccessibili della mappa con la speranza di rimanere vivo.

Attraverso la struttura della navicella è stato volutamente spinto l'utente a farsi assalire da un senso di curiosità in modo che tutte le stanze alla fine del gioco risultassero ben osservate, dando anche la possibilità però, di effettuare una speed-run per gli user che più si immergono nella corsa per la salvezza, non badando ad extra.

Il linguaggio è più descrittivo possibile in modo da sostituire la parte visiva non presente, con la creazione di mondi nella propria immaginazione. I vari messaggi per ogni azione compiuta sono a tratti ironici in un certo senso, per far si di non cadere mai nel noioso/frustante ed infine non c'è la presenza musica e o immagini ad eccezione della mappa reale del gioco disegnata ad-hoc, sbloccabile in una delle sezioni intermedie della storia.

Il gameplay con l'aiuto dell'interfaccia grafica risulta essere molto semplice ed intuitivo. Tramite appositi bottoni ben predisposti sullo schermo, l’utente può effettuare quasi tutte le azioni principali, che sono: le direzioni, indicate tramite i tasti dei punti cardinali (Nord, Sud, Ovest, Est), il tasto osserva per avere maggiori descrizioni legate alle stanze, il tasto invio per eseguire altre azioni scritte nell'apposita sezione, ed i tasti di salvataggio e caricamento partita.

Oltre i tasti e le sezioni precedentemente descritte, l'interfaccia presenta un largo Display legato alla visualizzazione dello svolgimento del gioco con accanto un piccolo schermo legato a contenere gli oggetti raccolti e riposti nell'inventario, una sezione costantemente aggiornata con l'orario per risolvere qualche grattacapo e la presenza di qualche pop-up attivabile giocando e/o eseguendo operazioni di impostazione.

Non è utilizzabile un menù di help con la lista di tutti i comandi visto che la stragande maggioranza di essi è esplicitata graficamente, lasciando grandi sorprese e soddisfazioni all'utente quando scoprirà funzioni nascoste. Proprio per sopperire questa mancanza, c'è la possibilità di incontrare un piccolo esserino verde inizialmente non comprensibile, che avrà la funzione di guida sulla navicella.

Il resto è tutto presente nel gioco, cosa aspettate??

## Tecniche utilizzate: 
- Programmazione ad oggetti
- File: utilizzati per l’inizializzazione delle stanze, degli oggetti e dei rispettivi parametri; Utili per implementare facilmente altre avventure tesuali e per facilitare la futura manutenzione di esse.
- Thread: utilizzato constantemente dall'avvio alla fine dell'esecuzione, per mantenere costantemente aggiornato l'orario attuale, utile al giocatore.
- Swing: per costruire l’interfaccia grafica dell’applicazione precedentemente e abbondantemente descritta.
- Rest/Servlet:
- Collection: 
    - Arraylist per organizzare i dati delle stanze, degli oggetti, dell'inventario e dei comandi.
    - Iterator per le ricerche negli Arraylist.
    - Tipo enumerativo per classificare i comandi inseribili dell’utente.
    - Set per creare gli alias, semplificando il riferimento ad oggetti, stanze e comandi.
- Database H2 utilizzato per il salvataggio e il caricamento della partita.
- Lambda Expression: 

## Diagramma delle classi:

## Specifica algebrica:

## Informazioni aggiuntive sulle problematiche e sulle scelte sviluppative:

## Autori e sviluppatori del gioco:
-	Gaetano Schiralli
-	Dafne Spaccavento
