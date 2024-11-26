import java.util.ArrayList;
public class Messenger_Owl implements Contract {
    /**
     *    A messenger owl takes message to people
     *    It grabs() (the parcel then fly() to the specified location
     *    It examines() if the dropbox name is the same as the specified destination then drops() the parcel
     * Drop energy level for everything
     */
    /* Attributes*/
    String owl_name;
    int x_location;
    int y_location;
    String ownername;
    boolean lifestatus=true;
    boolean isflying;
    int energy_level=100;
    ArrayList<String> items=new ArrayList<>();

    /**
     * Constructor for the class
     * @param owl_name takes in the owl name
     * @param x_location takes in the initial x location of the messenger owl
     * @param y_location takes in the initial y location of the messenger owl
     * @param ownername takes in the name of the owner of the messenger owl
     */
    public Messenger_Owl(String owl_name, int x_location, int y_location, String ownername) {
        this.owl_name = owl_name;
        this.x_location = x_location;
        this.y_location = y_location;
        this.ownername = ownername;
    }

    /**
     * method to check whether the owl is still alive
     * @return turns true if it is alive or throws an exception if it dead
     * it is called by every other method before they get executed
     */
    boolean isalive(){
        if(lifestatus){
            return true;
        } else {
            throw new RuntimeException("Accept it, the owl is dead. Buy a new one");
        }
    }

    /**
     * method when the messenger owl is grabbing an item
     * @param item takes in the item to be grabbed
     * First verifies if the owl is still alive
     */
    public void grab(String item){
        if(isalive()){
            if(item.contains("package")){
                System.out.println(this.owl_name + " grabbed " + item +" and is ready to fly. Call fly() next");
            } else{
                System.out.println(this.owl_name+"grabbed "+item);
            }
            items.add(item);
            this.energy_level-=7;
        }
    }

    /**
     * method to examine whether the name on the place to drop the package matches the real one
     * @param dropboxmane takes in the name on the dropbox
     */
    public void examine(String dropboxmane){
        if(isalive()){
            if(dropboxmane.equals(this.ownername)){
                System.out.println(this.owl_name + " found the right dropdox.Call drop()");
            } else{
                System.out.println(this.owl_name + " didn't find the right dropbox.");
            }
            this.energy_level-=5;
        }
    }

    /**
     * method for dropping a certain item
     * @param item takes in item to be dropped
     *             verifies whether the item is the items it has
     * @return item name
     */
    public String drop(String item){
        if(isalive()){
            if(items.contains(item)){
                System.out.println(this.owl_name + " dropped " + item);
                items.remove(item);
            } else{
                System.out.println(this.owl_name + " didn't have " + item);
            }
            this.energy_level=this.energy_level-5;
        }
        return item;
    }

    /**
     * method for flying to certain coordinates from the initial locations
     * @param x takes in the x location to go to
     * @param y takes in the y location to go to
     * @return true when the process is done
     */
    public boolean fly(int x, int y){
        if(isalive()){
            if(y>=0){
                System.out.println(this.owl_name + " flew to " + x + " x distance, " + y+ " y distance");
                this.x_location = x;
                this.y_location = y;
                this.isflying = true;
                shrink();
                return true;
            } else {
                throw new RuntimeException("Cannot fly underground");
            }
        }
        return true;
    }

    /**
     * method for the messenger owl to drop vertically to a coordinated y.
     * @param y takes in the y location to drop to
     *          if it is negative, an exception if thrown
     */
    public void drop (int y){
        if(isalive()){
            if(y>this.y_location){
                throw new RuntimeException("Can't drop to a higher height than before. That's called ascending");
            } else {
                fly(this.x_location, y-y_location);
                String s = this.owl_name + " dropped to " + y;
                System.out.println(s);
                shrink();
            }
        }
    }

    /**
     * method for
     * @param direction takes in the direction for the owl to walk in
     * @return true if the process is done
     */
    public boolean walk(String direction){
        if(isalive()){
            if(this.y_location!=0){
                throw new RuntimeException("Can't walk in the sky. Please drop() first.");
            } else{
                if(direction.equals("forward")){
                    System.out.println( this.owl_name + " walks for 10 units in " + direction);
                    this.energy_level = energy_level-25;
                } else if(direction.equals("backward")){
                    System.out.println (this.owl_name + " walked to " + this.y_location+" in " + direction);
                    this.energy_level = energy_level-25;
                } else if(direction.equals("left")){
                    this.x_location = this.x_location - 10;
                    System.out.println( this.owl_name + " walks for 10 units in " + direction);
                    this.energy_level = energy_level-25;
                }else if(direction.equals("right")){
                    this.x_location = this.x_location + 10;
                    System.out.println( this.owl_name + " walks for 10 units in " + direction);
                    this.energy_level = energy_level-25;
                } else{
                    System.out.println("left, backward, right, forward");
                }
            }
        }
        return true;
    }

    /**
     * method for the messenger owl to graw
     * @return the current level of energy for the owl symbolizing growth
     */
    public Number grow(){
        if(isalive()){
            this.energy_level = energy_level+25;
            System.out.println( this.owl_name + " ate and has grown");
        }
        return this.energy_level;
    }

    /**
     * method for shrinking which reduces the energy level of the owl
     * @return the energy level of the messenger owl
     */
    public  Number shrink(){
        if(isalive()){
            this.energy_level = energy_level-25;
            System.out.println( this.owl_name + " has shrunk. Feed it to grow again");
        }
        return this.energy_level;
    }

    /**
     * method for using a given item
     * @param item takes in the item to be read
     */
    public void use(String item){
        if(isalive()){
            if (items.contains(item)){
            System.out.println("I am using this "+ item +" as a decoration from now on");
            } else{
                System.out.println("First let " + this.owl_name + " grab() " +item );
            }
        }
    }

    /**
     * method for undoing the process
     */
    public void undo(){
        if(isalive()){
            System.out.println();
            System.out.println(this.owl_name + " is flying back up from (" +this.x_location+","+this.y_location+")");
            System.out.println("Ouch!! I got hit by a stone. Ouch! It is painful");
            System.out.println(this.owl_name + " had died.");
            System.out.println();
            this.lifestatus=false;
        }
    }

    /**
     * method for resting
     */
    public void rest(){
        if(isalive()){
            System.out.println(this.owl_name + " rested");
            this.energy_level = energy_level+25;
        }
    }

    /**
     * tostring method to print the class components
     * @return a prepared statement instead of the memory location
     */
    public String toString(){
        String s;
        if(lifestatus){
            s=" it is still alive.";
        } else{
            s=" it is now dead.";
        }
        return this.owl_name + " is owned by " + this.ownername+ " and" + s + "and currently has "+ this.energy_level+" kcal energy";
    }
    public static void main(String[] args) {
        Messenger_Owl hedwig=new Messenger_Owl("Hedwig", 3,5,"Harry Potter");
        System.out.println(hedwig);
        hedwig.grab("Toothbrush");
        hedwig.grab("Toothpaste");
        hedwig.drop("Toothbrush");
        hedwig.drop("Colgate");
        hedwig.examine("Harry Potter");
        hedwig.examine("Ron");
        hedwig.use("Toothpaste");
        System.out.println(hedwig);
        hedwig.drop(5);
        hedwig.walk("forward");
        hedwig.fly(7,9);
        hedwig.shrink();
        hedwig.grow();
        hedwig.rest();
        hedwig.undo();
        hedwig.grab("Hat");
    }
}
/**
 * A messenger owl takes message to people
 * It grabs() (the parcel then fly() to the specified location
 * It examines() if the dropbox name is the same as the specified destination then drops() the parcel
 * Drop energy level for everything
 */
