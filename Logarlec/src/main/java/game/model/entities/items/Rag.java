package game.model.entities.items;

//#todo: implement class
public class Rag extends Item{
    @Override
    public void activate() {
        this.activated = true;
        this.location.paralyzeProfessors();
        owner.dropItem(this);
    }

    @Override
    public boolean decreaseDurability(){
        this.durability--;

        if (this.durability <= 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isPairable() {
        return false;
    }

    @Override
    public boolean protectFromKill() {
        return false;
    }

    @Override
    public boolean protectFromGas() {
        return false;
    }
}
