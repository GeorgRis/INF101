# **Steg 1 – Implementering av Grid**  

I denne oppgaven skal du implementere klassene `Grid`, `CellPosition` og `GridCell` i pakken **no.uib.inf101.grid**. Dette gridet skal fylles opp med datatypen `Character` (objektversjonen av `char`).
✅ **Målet er at alle medfølgende tester skal kjøre uten feil når implementasjonen er fullført.**  

---

## **Oppgavebeskrivelse**  

### **1️⃣ Implementer `CellPosition`**  
Opprett en **record-klasse** `CellPosition` med følgende felt:  
📌 `row` (int) – Radnummeret i gridet.  
📌 `col` (int) – Kolonnenummeret i gridet.  

### **2️⃣ Implementer `GridCell`**  
Opprett en **record-klasse** `GridCell` med følgende felt:  
📌 `pos` (CellPosition) – En instans av `CellPosition` som angir cellens plassering.  
📌 `symbol` (Character) – Tegnet som representerer cellen.  

### **3️⃣ Implementer `Grid`**  
Opprett klassen `Grid`, som representerer rutenettet. Denne klassen må implementere **grensesnittet `IGrid`**, som allerede er definert.  

### **🔹 Fremgangsmåte:**  
1. Opprett **alle metodene som kreves av `IGrid`**. I første omgang kan disse returnere **"dummy"-verdier** (f.eks. `0` eller `null`) for å sikre at koden kompilerer.  
2. Les **Javadoc-kommentarene i `IGrid`** og undersøk **testene i `GridTest`** for å forstå hvilke metoder og konstruktører som må implementeres.  

---

## **Implementering av `Iterable<GridCell>`**  
Grensesnittet `IGrid` utvider `Iterable<GridCell>`, noe som betyr at **`Grid` må implementere en `iterator`-metode**.  

📌 **Forenklet løsning:**  
1. Opprett et nytt `ArrayList<GridCell>`-objekt i `iterator`-metoden.  
2. Bruk **nøstede for-løkker** til å gå gjennom alle posisjonene i gridet.  
3. For hver posisjon:  
   - Opprett et `GridCell`-objekt med riktig posisjon og symbol.  
   - Legg det til i listen.  
4. Returner en **iterator for listen** ved å kalle `.iterator()` på `ArrayList`-objektet.  

>:question: **Hvorfor denne løsningen?**  
Denne metoden bruker mer minne enn nødvendig, men er enkel å forstå og implementere – et godt første steg før mer optimaliserte løsninger vurderes.  

---

✅ **Oppgaven er fullført når alle testene i `CellPositionTest`, `GridCellTest` og `GridTest` passerer.**  
📌 **Husk å sjekke testene før du går videre!**  

🔙 [Tilbake til oversikt](../README.md#steg-for-steg) | 🔜 [Neste steg](./02-tegnrutenett.md)  
