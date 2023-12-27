# Requisiti utente

1.   Il sistema deve permettere la creazione di nuovi sondaggi e votazioni selezionando un bacino di utenti che viene notificato, la durata e l’oggetto della votazione.
2.    Il sistema deve essere affidabile, garantire la privacy degli utenti e l’accuratezza dei risultati prevenendo manipolazioni da parte di utenti e amministratori.
3.    Gli amministratori devono poter creare due tipi di consultazioni: sondaggi e votazioni. Ciascuno di essi può avere come oggetto un quesito o l’elezione di candidati.
4.    Il sistema deve memorizzare le informazioni rilevanti e i risultati delle consultazioni concluse. I risultati devono essere notificati agli utenti coinvolti.
5.    Per accedere al sondaggio è sufficiente autenticarsi utilizzando le credenziali della piattaforma. Per i sondaggi il sistema permette ai votanti di visualizzare in uno storico le scelte effettuate in precedenza e permette agli amministratori di impostare la scelta multipla per l’oggetto della consultazione.
6.   Per accedere alle votazioni è necessario utilizzare le credenziali della piattaforma e un sistema di autenticazione univoco. Le votazioni permettono di selezionare una sola opzione e garantiscono l’anonimato del voto.
7.    Il sistema crea una vetrina per ogni consultazione accessibile solo ai candidati. Gli amministratori inseriscono i candidati quando creano le consultazioni.





# Requisiti funzionali
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
	
# Requisiti non funzionali

* Requisiti del prodotto
  * Il sistema prevede un livello di persistenza per le consultazioni attive. (RU2)

* Requisiti organizzativi
  * Il sistema utilizza il protocollo HTTPS per la trasmissione di informazioni sensibili. (RU2)

* Requisiti esterni
  * Il sistema non memorizza l'associazione fra votante e scelta. (RU6)
  * L’utente, dopo aver visualizzato l'informativa della privacy, autorizza il sistema a salvare ed elaborare i propri dati al momento della registrazione. (RU2)
  * Il sistema gestisce i dati degli utenti in conformità dell’art. 13 del Regolamento UE n. 2016/679 (“General Data Protection Regulation” o “GDPR”) (RU2)

