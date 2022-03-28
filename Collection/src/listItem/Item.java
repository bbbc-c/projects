package listItem;

public class Item{
    protected int id;
    protected float volume;
    protected String name;
    protected boolean isPut;

    public Item(int id, float volume, String name) {
        this.id = id;
        this.volume = volume;
        this.name = name;
        isPut = false;
    }

    public String getInfo(){
        return "id:"+id+" volume:"+volume+" name:"+ name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPut() {
        return isPut;
    }

}
