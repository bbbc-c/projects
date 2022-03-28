package listItem;

import java.util.ArrayList;

public class Box extends Item{

    private ArrayList<Item> items = new ArrayList<Item>();
    private float maxVolume; // объем = масса

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

    public Item extract(int index) {
        if ((index < 0) || (index >= items.size()))
            throw new IllegalArgumentException("Элемента с таким индексом не существует!");
        volume -= items.get(index).volume;
        items.get(index).isPut = false;
        Item item = items.get(index);
        items.remove(index);
        return item;
    }
    public boolean extract(Item item) {
       if (!item.isPut)
           throw new IllegalArgumentException("Объект нигде не лежит!");
       if(items.contains(item)) {
           volume -= item.volume;
           item.isPut = false;
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
