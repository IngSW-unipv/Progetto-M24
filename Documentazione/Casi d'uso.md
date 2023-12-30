# UC1: Partecipa Consultazione
- **Portata**: Applicazione di votazione
- **Livello**: Obiettivo utente
- **Attore primario**: Votante
- **Stakeholder**:
  - Votante: Vuole partecipare a una consultazione attiva
  - Sistema di autenticazione sicuro: Vuole ricevere le credenziali dell'utente che vuole prendere parte a una votazione attiva
- **Precondizioni**: Il votante deve essere registrato e autenticato sulla piattaforma.
- **Garanzia di successo**: L'utente viene notificato della corretta ricezione del voto. Per i sondaggi lo storico delle scelte dell'utente viene aggiornato.
- **Scenario principale di successo**:
  1. Il sistema visualizza la pagina del sondaggio
  2. Il votante seleziona l’opzione desiderata e conferma
  3. Il sistema invia notifica di corretta ricezione del voto
- **Scenari alternativi**:
  - 1a. Il votante vuole partecipare a una votazione
    - Al momento dell'accesso alla pagina il sistema richiede di effettuare l'autenticazione sicura
    - Il sistema conferma la corretta autenticazione dell'utente
- **Requisiti speciali**:
  - Si vuole affidabilità garantita da un livello di persistenza
  - Il sistema non memorizza l'associazione fra votante e scelta.
- **Frequenza**: Può essere continua nel periodo fra l'inizio e la fine di una consultazione
# UC2: Crea Consultazioni
- **Portata**: Applicazione di votazione
- **Livello**: Obiettivo utente
- **Attore primario**: Amministratore
- **Stakeholder**:
  - Amministratore: Vuole una procedura semplice e comprensibile senza errori
  - Votante: Vuole essere informato quando è stato aggiunto ad una consultazione
  - Candidato: Vuole avere accesso alla vetrina
  - Compagnia: Vuole che tutti gli utenti interessati siano aggiunti. Vuole ottenere i risultati di suo interesse.
- **Precondizioni**: L'amministratore deve essere registrato e autenticato sulla piattaforma.
- **Garanzia di successo**: La consultazione è creata correttamente. Gli utenti sono aggiunti.
- **Scenario principale di successo**:
  1. L'amministratore inizia la creazione di una consultazione
  2. L'amministratore seleziona gli utenti
  3. Il sistema visualizza la lista degli utenti aggiunti
  4. L'amministratore seleziona i candidati
  5. Il sistema visualizza la lista dei candidati
  7. L'amministratore inserisce una data di inizio e di fine
  8. L'amministratore sceglie la tipologia di consultazione
  9. L'amministratore imposta l'oggetto della consultazione
  10. L'amministratore conferma la visibilità dei risultati
  11. Il sistema chiede conferma della creazione riepilogando le caratteristiche
  12. L'amministratore dà la conferma
  13. Il sistema crea la consultazione con relativa vetrina
- **Sequenze alternative**:
  - *a. L'amministratore interrompe la creazione:
  - 3a.  L'amministratore rimuove uno o più utenti
  - 10a. L'amministratore aggiunge altri utenti che possono visualizzare i risultati
  - 9a. L'amministratore sceglie la votazione
  - 9b. L'amministratore sceglie il sondaggio
      - Il sistema chiede se impostare la scelta multipla
- **Requisiti speciali**:
  - Si vuole affidabilità garantita da un livello di persistenza
- **Varianti tecnologiche e dei dati**:
  - La data può essere indicata secondo i formati dd-mm-yyyy oppure yyyy-mm-dd
- **Frequenza**: Solo quando viene richiesto
# UC3: Carica Vetrina
* **Portata**: Applicazione di votazione
* **Livello**: Obiettivo utente
* **Attore primario**: Candidato
* **Stakeholder e interessi**:
   * Candidato: Vuole comunicare ai votanti le proprie posizioni e argomentazioni relative alla consultazione.
   * Votante: Vuole avere informazioni relative alle opzioni del quesito della consultazione ed effettuare una scelta migliore.
   * Amministratore: Vuole che tutte per ogni opzione del quesito siano disponibili informazioni e argomentazioni per i votanti.
   * Compagnia: Vuole ottenere risultati attendibili, che riflettano l'opinione informata dei votanti sul quesito della consultazione.
* **Precondizioni**:
  * L'amministratore ha creato la votazione e selezionato i candidati (UC2)
  * Il candidato deve essere registrato e autenticato sulla piattaforma.
* **Garanzia di successo**: I contenuti multimediali sono caricati nella vetrina della consultazione e memorizzati dal sistema. I contenuti sono visibili ai votanti sulla pagina della consultazione.
* **Scenario principale di successo**:
   1. Il candidato si autentica sulla piattaforma.
   2. Il candidato raggiunge la pagina della consultazione
   3. Il candidato seleziona i contenuti multimediali desiderati e li carica.
   4. Il sistema memorizza i contenuti.
   5. Il sistema invia notifica di successo al candidato.
   6. Il sistema rende visibili i contenuti ai votanti che accedono alla pagina della consultazione.
* **Scenari alternativi**:
  - 3a. I contenuti multimediali selezionati non sono supportati:
      * Il sistema impedisce il caricamento del file e notifica il candidato del problema.
      * Il candidato seleziona dei nuovi contenuti.
  - 4-5a. Il caricamento dei contenuti fallisce:
      * Il sistema notifica il candidato lo stato dell'operazione.
      * Il candidato ripete l'operazione di caricamento.
  - a* Durante la memorizzazione dei contenuti, il sistema entra in stato di errore:
      * L'amministratore riavvia il sistema.
      * Il sistema verifica lo stato dei contenuti caricati.
      * Il sistema notifica il candidato dello stato della memoria dei contenuti caricati.
      * Il candidato carica nuovamente i contenuti danneggiati.
* **Requisiti speciali**:
   * Il sistema deve garantire pari visibilità ai contenuti caricati per tutte le diverse opzioni del quesito.
* **Varianti tecnologiche e dei dati**:
   * Il sistema deve garantire un sufficiente numero di formati multimediali per i contenuti della vetrina: txt, jpg, mp4.
* **Frequenza**: In qualunque momento tra la creazione della consultazione e l'apertura ai votanti della possibilità di voto.
