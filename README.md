# Opinione360

![Logo_Opinione360_small.svg](..%2Fresources%2FLogo_Opinione360_small.svg)

![Static Badge](https://img.shields.io/badge/Progetto_M24-Gruppo_MRPL-darkblue?style=for-the-badge&labelColor=green)

![Static Badge](https://img.shields.io/badge/java-%2523ED8B00?style=for-the-badge&logo=openjdk&logoColor=white&color=orange)
![Static Badge](https://img.shields.io/badge/mysql-%252300f?style=for-the-badge&logo=mysql&logoColor=white&color=darkblue)
![Static Badge](https://img.shields.io/badge/markdown-%2523000000?style=for-the-badge&logo=markdown&logoColor=white&color=black)
![Static Badge](https://img.shields.io/badge/git-%25?style=for-the-badge&logo=git&logoColor=white&color=orange)
![Static Badge](https://img.shields.io/badge/github-%25?style=for-the-badge&logo=github&logoColor=white&color=black)
![Static Badge](https://img.shields.io/badge/Google_Cloud_Platform-%25?style=for-the-badge&logo=googlecloud&color=white)

## Descrizione generale 
Opinione360 è un sistema di consultazione basato su un'architettura client-server che permette ad aziende o enti pubblici di creare votazioni o sondaggi rivolti a gruppi di persone selezionate di volta in volta (che devono essere preventivamente registrate con una propria e-mail, una password e un nome utente). Per un maggior livello di sicurezza le votazioni richiedono al votante di autenticarsi tramite un servizio esterno che ne permette l'identificazione.
Gli utenti del sistema sono divisi in tre categorie: amministratore (si occupa della creazione delle consultazioni), candidato o votante.

****
## Tecnologie utilizzate
- Java 21
- Java Swing 21
- MYSQL 8.0

> [!NOTE]
> Versione minima Java 11

***
## Dipendenze
Il sistema utilizza le seguenti librerie:
* [MySQL Connector/J 8.3.0](https://dev.mysql.com/downloads/connector/j/) per il collegamento al DB
* [Sqlite-jdbc 3.43.0.0](https://github.com/xerial/sqlite-jdbc) per il DB locale del client
* [JUnit 4.13.1](https://junit.org/junit4/) per lo svolgimento dei test 
* [Google Gson 2.10.1](https://github.com/google/gson) per lo scambio di oggetti client/server
* [JFreeChart 1.5.4](https://github.com/jfree/jfreechart) per disegnare i grafici
* [FlatLaf 3.4](https://github.com/JFormDesigner/FlatLaf) per il look an feel della GUI 

***
## Getting started 
* [Documentazione](https://github.com/IngSW-unipv/Progetto-M24/tree/main/Documentazione)
* [Javadoc](https://github.com/IngSW-unipv/Progetto-M24/tree/main/javaDoc)

# Guida all'utilizzo del progetto
>[!TIP]
> Si consiglia l'utilizzo di IntelliJ Idea o di Eclipse per visualizzare i file del progetto

1. Clonare il progetto o farne un fork tramite git, github o un IDE che integri git
2. Scaricare le librerie indicate nella sezione Dipendenze del README
3. Preparare un istanza di database Mysql e creare lo schema tramite lo script
4. Creare un file properties.poperties in una directory Server/properties che includa i seguenti dati:
> port.number = <server_port><br>
> backlog = <max_num_of_client> <br>
> DBDRIVER = com.mysql.cj.jdbc.Driver<br>
> DBURL = jdbc:mysql://<dbms_host>:<dbms_port>/%s<br>
> db_usn = <db_username><br>
> db_psw = <db_password><br>
> [!NOTE]
> É possibile utilizzare un DBMS differente rispetto a Mysql; in quel caso occorre utilizzare il driver specifico
5. Creare un file properties.properties in una directory Client/properties che includa i seguenti dati:
> uri = <http://server_address:server_port>
6. La classe contenente il main per il server è model.Opinione360App; la classe contenente il main per il client è opinione360.Opinione360App

***
## Licenza
Tutto il codice di Opinione360 è rilasciato sotto licenza MIT

