
package simulation.domain;

public class Player {
    private String name;
    
    public Player(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public String toString(){
        return this.name;
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj == null) {
            return false;
        }
        Player other = (Player) obj;
        if(this.getName().equals(other.getName())) {
            return true;
        }
        return false;
    }
}
