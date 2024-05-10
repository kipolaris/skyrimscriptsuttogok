package game.view;

import game.controller.ModelListener;
import game.model.logging.Suttogo;

import java.util.ArrayList;
import java.util.List;

public class CharacterView {
    private List<ModelListener> listeners = new ArrayList<>();

    public void display(){

    }

    public void addListener(ModelListener listener){
        if(listener!=null){
            listeners.add(listener);
        }else{
            Suttogo.error("A kapott listener null : CharacterView addListener()");
        }
    }
}
