import java.util.Collection;

public class AbstractQueue<E> extends Base{

    int capacity;
    AbstractQueue(int c) {
        int capacity = c;
    }

    // Pre: true
    //Post: (size==size'+ 1) && (a[0]..a[size-2] == a'[0]..a'[size'-1]) && (a[size-1]=e)
    public Boolean add(E e) throws Exception {
        if (size == capacity) throw new Exception();// должно быть newIllegalStateException
        else {
            size++;
            add(size - 1, e);
            return true;
        }
    }

    //Pre: true
    //Post: (a.size==a.size+с.size) && (capacity>=a.size+с.size) && (a==a'+bс)
    public boolean addAll(Collection<E> c){
        addAll( c);
        return true;}

    //Pre:true
    //Post:(size == 0)
    public void clear(){removeAll();}

    //Pre:true
    //Post: (a = a' ) && (e=a[0])
    public E element(){
        E res = (E) get(0);
        return res;
    }

    //Pre: size > 0
    //Post:(size=size' -1) && (a[0]..a[size-1] == a'[1]..a'[size'-1])&&(res==a'[0])
    // (size == size' - 1) && (a[0] != a'[0])
    public E remove(){
        E res = (E) get(0);
        remove(0);
        return res;
    }
}
