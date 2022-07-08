import java.util.ArrayList;
import java.util.Collection;

public class Base<E> {
    private ArrayList<E> list;
    int size;

    public ArrayList<E> collection() {
        return list;
    }

    /**
     * base functions
     *
     */
    public void add(int index, Object element) {} //добавляет элемент в список по индексу, сдвигает все последующие элементы
    public void remove(int index) {} //удаляет элемент по индексу, сдвигает все элементы
    public int search(Object element, Boolean reverse) {return 1;}
    //возвращает первый индекс элемента, если его нет в списке, возвращает -1. Если reverse = true, то возвращает последний!!
    public E get(int index) {return list.get(index);} //возвращает элемент по индексу
    public int size() {return size;} //возвращает размер списка
    public void sort() {} //сортирует все элементы по возрастанию
    public boolean addAll(int index, Collection<? extends E> c) {return false;} //добавляет все элементы из указанной коллекции
    public Base<E> removeAll() {return new Base<>();} //удаляет все элементы в списке
    public Base<E> subBase(E fromElement, E fromInclusive, E toElement,   E toInclusive) {return new Base<>();}
    //возвращает новый сет, который состоит из элементов, находящихся между fromElement и toElement
    public E getNearest(E e, boolean lower) {return e;}
    //возвращает элемент строго меньше(больше (в зависимости от параметра lower)) данного (если его нет), либо возвращает этот элемент.
}
