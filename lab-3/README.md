# Lab 3 - Kalkulator 🖩

## Læringsmål  
- Forstå og bruke **interface**  
- Anvende **polymorfisme** for bedre kodekvalitet  

## Introduksjon  

I denne labben skal du refaktorere koden for en kalkulatorapplikasjon.  

>:question: **Refaktorisering** er prosessen med å forbedre kvaliteten, vedlikeholdbarheten og ytelsen til eksisterende kode uten å endre dens eksterne oppførsel. ([thecodest](https://thecodest.co/nb/ordbok/refaktorisering-av-kode/))  

Kalkulatoren fungerer som den er nå, men den er designet på en måte som gjør det tungvint å utvide funksjonaliteten. Din oppgave er å forbedre kodekvaliteten ved å bruke **interface** og **polymorfisme**.  

## Før du starter  

1. **Klon og kjør programmet** – bekreft at kalkulatoren fungerer som forventet. Foreløpig støttes kun addisjon (`+`).  
2. **Sjekk testene** i `ExpressionTest.java` – alle skal passere ✅
3. **Forstå konsepter**:  
   - Hva er et *interface*? ([Les her](https://inf101v23.stromme.me/notat/grensesnitt/))  
   - Hva er polymorfisme? ([Les her](https://inf101v23.stromme.me/notat/grensesnitt/#polymorfisme-med-grensesnitt))  

## Guide  

Følg stegene nedenfor for å fullføre oppgaven:  

1. [Legg til `*` og `-`](./guide/01-operators.md)  
2. [Legg til beskrivelser](./guide/02-descriptions.md)  
3. [Forbedre kodekvaliteten med interface](./guide/03-operatorInterface.md)  
4. [La `Calculator.java` bruke `Operator.java`](./guide/04-poymorphism.md)  
5. [Legg til eksponent-operator](./guide/05-exponent.md)  

## Fullføring  

✅ Oppgaven er ferdig når alle testene passerer. Dette gjelder også de utkommenterte testene.  
📌 **Husk å levere på CodeGrade!**  