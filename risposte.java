
public class risposte {
    /*RISPOSTA INPUT DATI giugno2019
     * m_AA: A
     *
     * m_AB: A
     *
     * m_AA: A
     * m_BA: B
     *
     * m_AB: A
     *
     * m_AA:A
     * m_BA:B
     *
     * m_AA:A  --> I aa = new B(); -> aa.m(new B())
     * m_BA:B      in questo caso tutte le variiabili che le passo sono considerate
     *             variabili A perché è un interfaccia che implementa solo il metodo A
     *
     * m_AA:A
     * m_BA:B
     *
     * m_AB:A
     *
     *
     *
     *
     * Esercizio 9settmebre 2020
     *  CODA FIFO
     * stato iniziale {[c1,c2,p1,p2],[],false}
     * {[c2,p1,p2],[c1],false}    consumer C1
     * {[p1,p2],[c1,c2],false}    consumer C2
     * {[p2,c1],[c2],true}    producer P1,C1 riceve notify e si rimette in coda
     *                        per il lock
     * {[c1],[c2,p2],true}        producer P2 perchè C2 e non P2? perchè la coda di wait
     * è solo una, quindi chi riceve il notify è il primo della coda cioè C2
     * {[c2],[p2],false}          Consumer C1
     * {[],[p2,c2],false}         DEADLOCK
     *
     *
     *
     *Prova 19 settembre 2019
     *
     * {[p1,p2,p3,c1,c2,c3],[],false} partenza
     * {[p2,p3,c1,c2,c3],[],1,true} "put"
     * {[p3,c1,c2,c3],[p2],1,true}
     * {[c1,c2,c3],[p2,p3],1,true}
     * {[c2,c3,p2],[p3],1,false} "get"
     * {[c3,p2],[p3,c2],1,false}
     * {[c3,p2],[p3,c2],1,false}
     * {[p2],[p3,c2,c3],1,false}
     * {[p3],[c2,c3],2,true}
     * [],[c2,c3,p3],2,true} DEADLOCK
     * * */

}
