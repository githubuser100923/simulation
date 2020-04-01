
package simulation.domain;

/*

Functionalities

*/


public class Service {
    Player player;
    
    //public Service() {
    //    player = null;
    //}
    
    public String joinButtonPressed(String username){
        if(username.length() < 3) {
            return "Type at least 3 characters";
        }
        return null;
    }
    
    public void createNewPlayer(String username){
        player = new Player(username);
    }
    
    public Player getPlayer() {
        return player;
    }
}
