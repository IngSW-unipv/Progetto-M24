# Software Requirement Specification

**Versione 1.0**

**Opinione360**

## 1.0. Introduzione

### 1.1. Scopo del documento

Lo scopo di questo documento è quello di presentare una descrizione dettagliata del sistema Opinione360. Spiegherà lo scopo e le funzionalità del sistema, cio che farà e i vincoli sotto i quali deve operare. Questo documento è pensato sia per gli stakeholder sia per gli sviluppatori del sistema.

### 1.2. Scopo del prodotto
Questo sistema software sarà una piattaforma di votazione per società private o pubbliche. Sarà progettato per garantire la sicurezza dei dati e l'anonimato degli utenti che partecipano alle consultazioni permettendo nel contempo alle società di ottenere i risultati in modo più efficiente, evitando di dover utilizzare un processo manuale di votazione che richiede una lunga preparazione.
Più nello specifico il sistema permetterà a degli amministratori, scelti all'interno della società, di creare delle consultazioni secondo le indicazioni fornitegli di volta in volta. 
Il sistema conterrà inoltre un database relazionale per garantire la persistenza dei dati.

### 1.3 Glossario
| Termine | Definizione | 
| ---         |     ---     |
| **Consultazione** | Termine generico che identifica un sondaggio o una votazione |
| **Sondaggio**| Consultazione che non richiede un'autenticazione sicura a chi partecipa e permette la scelta multipla. La scelta viene memorizzata in uno storico|
| **Votazione**| Consultazione sicura utilizzabile per scelte vincolanti che garantisce l'anonimato dei votanti. Richiede l'autenticazione sicura, non memorizza lo storico e permette solo la scelta singola|
| **Utente**| Termine generico che identifica gli attori che accedono alla piattaforma|
| **Votante**| Utente selezionato dall'amministratore che ha accesso ad una specifica consultazione|
| **Candidato**| Utente selezionato dall'amministratore che può caricare contenuti sulla vetrina di una specifica consultazione|
| **Vetrina**| Spazio collegato alla consultazione nel quale i candidati caricano i contenuti|
| **Registro**| Entità che conserva le informazioni rilevanti delle consultazioni concluse|
| **Storico**| Entità collegata al votante che memorizza le scelte fatte nei sondaggi|
| **Compagnia**| É la società pubblica o l'ente privato che utilizza la piattaforma|
| **GCP**| Google Cloud Platform|


### 1.4 Descrizione del resto del documento
Il capitolo successivo dà una visione delle funzionalità del prodotto. Descrive i requisiti informali ed è usato per stabilire un contesto per la specifica dei requisiti tecnici del capitolo successivo.

Il terzo capitolo di questo documento è scritto principalmente per gli sviluppatori e descrive in termini tecnici i dettagli delle funzionalità del prodotto.

Entrambe le sezioni del documento descrivono lo stesso sistema software nella sua interezza ma sono pensati per un pubblico diverso e quindi usano un linguaggio differente.

## 2.0 Descrizione generale
### 2.1 Funzioni del prodotto
1.   Il sistema deve permettere la creazione di nuovi sondaggi e votazioni selezionando un bacino di utenti che viene notificato, la durata e l’oggetto della votazione.
2.    Il sistema deve essere affidabile, garantire la privacy degli utenti e l’accuratezza dei risultati prevenendo manipolazioni da parte di utenti e amministratori.
3.    Gli amministratori devono poter creare due tipi di consultazioni: sondaggi e votazioni. Ciascuno di essi può avere come oggetto un quesito o l’elezione di candidati.
4.    Il sistema deve memorizzare le informazioni rilevanti e i risultati delle consultazioni concluse. I risultati devono essere notificati agli utenti coinvolti.
5.    Per accedere al sondaggio è sufficiente autenticarsi utilizzando le credenziali della piattaforma. Per i sondaggi il sistema permette ai votanti di visualizzare in uno storico le scelte effettuate in precedenza e permette agli amministratori di impostare la scelta multipla per l’oggetto della consultazione.
6.   Per accedere alle votazioni è necessario utilizzare le credenziali della piattaforma e un sistema di autenticazione univoco. Le votazioni permettono di selezionare una sola opzione e garantiscono l’anonimato del voto.
7.    Il sistema crea una vetrina per ogni consultazione accessibile solo ai candidati. Gli amministratori inseriscono i candidati quando creano le consultazioni.

## 3.0 Specifica dei requisiti
Questa sezione descrive i requisiti funzionali e non funzionali. I riferimenti ai requisiti utente corrispettivi sono indicati tra parentesi a fine riga.
### 3.1 Requisiti di interfaccia esterna
L'unico collegamento ad un sistema esterno è quello al sistema di autenticazione sicuro per riconoscere l’utente in caso di votazione.
### 3.2 Requisiti funzionali
* L'amministratore può creare una nuova consultazione. (RU1)
* L'amministratore seleziona gli utenti che possono partecipare alla consultazione. (RU1)
* Gli utenti ricevono una notifica dal sistema quando sono aggiunti a una consultazione. (RU1)
* L'amministratore seleziona i candidati della consultazione. (RU7)
* I candidati caricano i contenuti desiderati sulla vetrina della consultazione. (RU7)
* L'amministratore imposta la data di inizio e la data di fine della consultazione. (RU1)
* La vetrina viene generata al momento della creazione della consultazione. (RU7)
* La consultazione può essere di due tipi: sondaggio o votazione. (RU1,RU3) 
* L'amministratore imposta l'oggetto della consultazione in formato di scelta singola. (RU1)
* Per il sondaggio l'amministratore imposta alternativamente una scelta multipla. (RU5) 
* Per le consultazioni concluse l'amministratore opzionalmente amplia la lista di utenti che possono visualizzare i risultati di una consultazione. (RU2)
* Per le consultazioni concluse il registro memorizza il quesito, data di inizio e fine, i risultati. (RU4)
* Per le consultazioni concluse i votanti e i candidati ricevono una notifica dal sistema con i risultati. (RU4)
* Per utilizzare la piattaforma l'utente deve essere registrato. (RU5,RU6)
* Per partecipare alla consultazione l'utente si autentica, accede alla pagina relativa ed esprime la propria scelta. (RU5,RU6)
* L'utente può visualizzare lo storico delle scelte effettuate nei sondaggi. (RU5)
* Per la votazione il votante deve anche autenticarsi tramite un servizio esterno sicuro. (RU6)

### 3.3 Requisiti non funzionali
* Requisiti del prodotto
  * Il sistema prevede un livello di persistenza per le consultazioni attive. (RU2)
  * Il software utilizza massimo  2 GB con 100 consultazioni attive

* Requisiti organizzativi
  * Il sistema utilizza il protocollo HTTPS per la trasmissione di informazioni sensibili. (RU2)
* Il sistema è realizzato utilizzando il linguaggio Java 21

* Requisiti esterni
  * Il sistema utilizza il servizio GCP per per implementare il livello di persistenza.
  * Il sistema non memorizza l'associazione fra votante e scelta. (RU6)
  * L’utente, dopo aver visualizzato l'informativa della privacy, autorizza il sistema a salvare ed elaborare i propri dati al momento della registrazione. (RU2)
  * Il sistema gestisce i dati degli utenti in conformità dell’art. 13 del Regolamento UE n. 2016/679 (“General Data Protection Regulation” o “GDPR”) (RU2)

## 4.0 Indice

1.0 Introduzione
> 1.1 Scopo del documento

> 1.2 Scopo del prodotto

> 1.3 Glossario

> 1.4 Descrizione del resto del documento
  
2.0 Descrizione generale
> 2.1 Funzioni del prodotto
  
3.0 Specifica dei requisiti
> 3.1 Requisiti di interfaccia esterna

> 3.2 Requisiti funzionali

> 3.3 Requisiti non funzionali


