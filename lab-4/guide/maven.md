# **Installering av Maven**

I lab5 er vi avhengig av å bruke *Maven*, et verktøy for å bygge og administrere Java-prosjekter. Maven hjelper med å håndtere avhengigheter og bygge prosjektene dine på en enkel og konsistent måte.

✅ **Målet er å installere Maven og legge det til i systemets PATH-variabel, slik at det kan brukes fra terminalen.**

Du har installert Maven riktig om du kan kjøre denne kommandoen i terminalen:
```sh
mvn -version
```
og få output som ligner dette (windows):
```sj
Apache Maven 3.9.9 (8e15439a9e76f7d015el5ec7bfcdc97d26014627)
Maven home: C:\Program Files\apache-maven-3.9.9
...
```

---

## **1️⃣ Installer Maven på macOS**

### **Metode 1: Homebrew (Anbefalt)**
Hvis du har [Homebrew](https://brew.sh/) installert, kan du enkelt installere Maven med en kommando:
```sh
brew install maven
```

For å sjekke at installasjonen var vellykket, kjør:
```sh
mvn -version
```
Du skal se informasjon om Maven-versjonen og Java-versjonen.

### **Metode 2: Manuell installasjon**
1. Gå til [Apache Maven nedlastingssiden](https://maven.apache.org/download.cgi).
2. Last ned den nyeste **binære zip- eller tar.gz-filen**.
3. Pakk ut filen til en katalog, for eksempel `/opt/maven`.
4. Åpne terminalen og rediger din shell-konfigurasjonsfil:
   - For **zsh** (standard fra macOS Catalina og nyere):
     ```sh
     nano ~/.zshrc
     ```
   - For **bash**:
     ```sh
     nano ~/.bash_profile
     ```
5. Legg til følgende linjer nederst i filen:
   ```sh
   export M2_HOME=/opt/maven
   export PATH=$M2_HOME/bin:$PATH
   ```
6. Lagre filen og lukk editoren (Ctrl+X, deretter Y og Enter).
7. Kjør følgende for å laste inn endringene:
   ```sh
   source ~/.zshrc  # Eller source ~/.bash_profile for bash
   ```
8. Bekreft installasjonen ved å kjøre:
   ```sh
   mvn -version
   ```

---

## **2️⃣ Installer Maven på Windows**

### **Metode 1: Chocolatey (Anbefalt)**
Hvis du har [Chocolatey](https://chocolatey.org/) installert, kan du enkelt installere Maven med:
```powershell
choco install maven
```

Sjekk installasjonen med:
```powershell
mvn -version
```

### **Metode 2: Manuell installasjon**
1. Gå til [Apache Maven nedlastingssiden](https://maven.apache.org/download.cgi).
2. Last ned den nyeste **binære zip-filen**.
3. Pakk ut zip-filen til en katalog, for eksempel `C:\Program Files\Maven`.
4. Åpne **Søk** og skriv `Miljøvariabler`, og klikk på **Rediger systemmiljøvariabler**.
5. Klikk på **Miljøvariabler**-knappen.
6. Under **Systemvariabler**, finn **Path** og klikk **Rediger**.
7. Klikk **Ny** og legg til:
   ```
   C:\Program Files\Maven\bin
   ```
8. Klikk **OK** på alle vinduer for å lagre endringene.
9. Åpne en ny **Kommandoprompt** og kjør:
   ```cmd
   mvn -version
   ```
   Du skal se informasjon om Maven-versjonen og Java-versjonen.

---

🎉 **Gratulerer! Nå kan du bruke Maven til å håndtere Java-prosjektene dine!**

