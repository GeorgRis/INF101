Dette er en Java-implementering av det populære 2048-spillet. Spillet består av et 4x4 brett hvor spilleren kombinerer like tall ved å skyve brikker i fire retninger (opp, ned, venstre, høyre). Målet er å nå brikken med verdien 2048.

Funksjonalitet
Spillmekanikk: Kombiner like tall ved å skyve dem sammen

Animeringer: Glatte animasjoner når brikker flyttes

Poengsystem: Poengsum beregnes basert på kombinasjoner

Highscore: Lagrer topp 3 resultater med spillernavn

Grafikk: Fargekodet brikker med tydelig visning av verdier med samme som orginal spill

Teknisk Beskrivelse
Klasser
Main: Starter applikasjonen

MainFrame: Hovedvindu med spillbrett og meny

GamePanel: Tegner spillet og håndterer input

Controller: Styrer spilllogikk og regler

Board: Representerer spillbrettet

Tile/TileMove: Representerer brikker og deres bevegelser

AnimatedTile: Håndterer animasjoner

HighscoreEntry: Lagrer highscore-data

Brukerveiledning
Styring: Bruk piltastene (↑ ↓ ← →) for å flytte brikker

Restart: Trykk ENTER når spillet er over for å starte på nytt

Highscore: Ved game over kan du legge til navn ditt hvis du har fått nok poeng

Spillregler
Brikker flyttes i valgt retning så langt som mulig

Like brikker som kolliderer slås sammen til en brikke med summen av verdiene

For hver kombinasjon får du poeng lik den nye brikkens verdi

Spillet er over når brettet er fullt og ingen flere kombinasjoner er mulig

Målet er å nå 2048-brikken (men du kan fortsette å spille)

Georg Risøy