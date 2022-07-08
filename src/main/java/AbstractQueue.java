import java.util.Collection;

public class AbstractQueue<E> extends Base{

    int capacity;
    AbstractQueue(int c) {
        int capacity = c;
    }

    //Pre:true
    //Post:size == size' + 1
    public Boolean add(E e) throws Exception {
        if (size == capacity) throw new Exception();// должно быть newIllegalStateException
        else {
            size++;
            add(size - 1, e);
            return true;
        }
    }

    //Pre:true
    //Post:size == a'.size + c.size
    public boolean addAll(Collection<E> c){
        addAll( c);
        return true;}

    //Pre:true
    //Post:(size == size' - 1)
    public void clear(){removeAll();}

    //Pre:true
    //Post: (size == size' ) && (e=a[0] = a'[0])
    public E element(){
        E res = (E) get(0);
        return res;
    }

    //Pre:true
    //Post:(size == size' - 1) && (a[0] != a'[0])
    public E remove(){
        E res = (E) get(0);
        remove(0);
        return res;
    }
}
