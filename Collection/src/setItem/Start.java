package setItem;

public class Start {
    public static void main(String[] arg){
        Item gun = new Item(0,10,"Пистолет");
        Item pen = new Item(5,10,"Рурчка");
        Item apple = new Item(4,10,"Машина");
        Box box1 = new Box(1,"Коробка1",100);
        Box box2 = new Box(2,"Коробка2",100);
        box1.add(pen);
        box1.add(apple);
        box2.add(gun);
        System.out.println(box2.getInfo());
        System.out.println(box1.getInfo());
        box1.add(box2);
        System.out.println(box1.getInfo());
        box1.extract(gun);
        System.out.println(box1.getInfo());
        System.out.println(box2.getInfo());

    }
}
