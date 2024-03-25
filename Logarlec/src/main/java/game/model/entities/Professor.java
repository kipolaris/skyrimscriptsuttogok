package game.model.entities;

import game.model.logging.Suttogo;

//#todo: implement class
public class Professor extends Character{
    public void doRound(){
        Suttogo.info("doRound()");
        //ezt akkor kéne megcsinálni, amikor már a grafikus interfésszel kapcsolatos event kezelés is tálalékon lesz,
        //hiszen egy kör az tulajdonképpen egy loop lesz, eseményekkel.
        throw new UnsupportedOperationException();
    }
}
