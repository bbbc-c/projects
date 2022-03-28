package setItem;

import java.util.HashSet;

public class Box extends Item{

    private HashSet<Item> items = new HashSet<Item>();
    private float maxVolume;

    public Box(int id, String name, float maxVolume) {
        super(id, 0, name);
        this.maxVolume = maxVolume;
    }
    public boolean add(Item item) {
        if (this.isPut) throw new IllegalArgumentException("Сначало нужно вынуть!");
        if (item.isPut) throw new IllegalStateException("Это предмет уже где то лежит!");
        if (this == item) throw new IllegalArgumentException("Нельзя положить коробку саму в себя!");
        if (maxVolume-volume <= item.volume) throw new IllegalArgumentException("Этот предмет слишком велик!");
        if (!items.add(item)) throw new IllegalStateException("Не удалось занести в коллекцию!");
        volume += item.volume;
        item.isPut = true;
        return true;
    }
    public String getInfo() {
       return super.getInfo()+" maxVolume:"+maxVolume+" \n";
    }

    public boolean extract(Item item) {
       if (!item.isPut)
           throw new IllegalArgumentException("Объект нигде не лежит!");
       if(items.contains(item)) {
           volume -= item.volume;
           items.remove(item);
           return true;
       }
       for(Item i: items)
           if(i instanceof Box)
               if (((Box)i).extract(item)){
                   volume -= item.volume;
                   return true;
               }
       return false;
    }

    public float getMaxVolume() {
        return maxVolume;
    }

    public void setMaxVolume(float maxVolume) {
        this.maxVolume = maxVolume;
    }
}
