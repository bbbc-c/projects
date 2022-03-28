package mapItem;

import java.util.HashMap;

public class Box extends Item{

    private HashMap<Integer,Item> items = new HashMap<Integer,Item>();
    private float maxVolume;

    public Box(int id, String name, float maxVolume) {
        super(id, 0, name);
        this.maxVolume = maxVolume;
    }
    public boolean add(Item item) {
        if (this.isPut)
            throw new IllegalArgumentException("Сначало нужно вынуть!");
        if (item.isPut)
            throw new IllegalStateException("Это предмет уже где то лежит!");
        if (this == item)
            throw new IllegalArgumentException("Нельзя положить коробку саму в себя!");
        if (maxVolume-volume <= item.volume)
            throw new IllegalArgumentException("Этот предмет слишком велик!");
        items.put(item.getId(),item);
        volume += item.volume;
        item.isPut = true;
        return true;
    }
    public String getInfo() {
       return super.getInfo()+" maxVolume:"+maxVolume+" \n";
    }
    public Item extract(int id) {
        if (items.containsKey(id)){
            volume -= items.get(id).volume;
            Item item = items.get(id);
            item.setPut(false);
            items.remove(id);
            return item;
        }
        for(Integer i: items.keySet()){
            if (items.get(i) instanceof Box) {
                Item item = ((Box) items.get(i)).extract(id);
                if (item != null) {
                    volume -= item.volume;
                    return item;
                }
            }
        }
        return null;
    }
    public boolean extract(Item item) {
       if (!item.isPut)
           throw new IllegalArgumentException("Объект нигде не лежит!");
       if(items.containsValue(item)) {
           volume -= item.volume;
           Item i = items.remove(item.getId());
           if( i == null) System.out.println("Не удалился!");
           else System.out.println("Удалился");
           return true;
       }
       for(Integer i: items.keySet())
           if(items.get(i) instanceof Box)
               if (((Box)items.get(i)).extract(item)){
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
