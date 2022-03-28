package mapItem;

public class Start {
    public static void main(String[] arg){
        Item gun = new Item(0,40,"Пистолет");
        Item pen = new Item(5,10,"Рурчка");
        Item apple = new Item(4,10,"Машина");
        Box box1 = new Box(1,"Коробка1",100);
        Box box2 = new Box(2,"Коробка2",100);

        box2.add(apple);
        box1.add(box2);

        System.out.println(box1.getInfo());
        box1.extract(4);
        System.out.println(box1.getInfo());
    }
}
