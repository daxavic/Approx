import java.util.Collection;

public class LinkedBlockingQueue<E> extends Base {

    int capacity;
    Base<E> queue;

    //Pre:true
    //Post:size ==0
    public void  clear(){queue.removeAll();}

    //Pre:true
    //Post: (??)
    public boolean	contains(Object o){
        if (queue.search(o, false) != -1) return true;
        else return false;
    }

    //Pre:true
    //Post: (a.size == 0) && (c == c + a')
    public int	drainTo(Collection<E> c){
        c.addAll((Collection<? extends E>) queue);
        int res = queue.size();
        queue.removeAll();
        return  res;
    }

    //Pre:true
    //Post:{(capacity <= maxElements)&&(a.size == 0) && (c == c + a')} ||
    //     {(capacity >  maxElements) && (a.size= a'.size-maxElements) && (c == c + a)}
    public int	drainTo(Collection<Base<E>> c, Integer maxElements){
        if (capacity <= maxElements || queue.size() < maxElements) return drainTo((Collection<E>) c);
        else{
            Base<E> b = new Base<E>();
            b.addAll(0, (Collection<?>) queue);
            queue.subList(0, false, maxElements, true);
            c.add(b);
            subList(maxElements, true,  size(), true);
            removeAll();
            addAll(size(), (Collection) b);
            return maxElements;
        }
    }

//    public Iterator<E>	iterator(){
//        //TODO
//    }

    //Pre:true
    //Post:{(a=a') && (size < capacity)} || {(size<capacity)&&(size=size'+1)&&(a[size-1]=e)}
    public boolean offer(E e){
        if (queue.size() < capacity) {
            add(queue.size()-1, e);
            return true;
        }
        else return false;
    }

    //непонятно, как работать с TimeUnit
    //Pre:true
    //Post: {(a[size-1]=e)}
//    public boolean	offer(E e, long timeout, TimeUnit unit){
//        //TODO
//    }


    //Pre:true
    //Post:(size == 0) || (el = a[0])
    public E peek(){if (queue.size() == 0) return null; else return (E) get(0);}

    //Pre:true
    //Post:(size == 0) || {(el = a'[0]) && (size=size'-1) && ((el != a[0]))}
    public E poll(){
        if (queue.size() == 0) return null;
        else {
            E res = (E) get(0);
            remove(0);
            return res;
        }
    }

    //непонятно, как работать с TimeUnit и timeout, нужны циклы
    //Pre:true
    //Post:(size == 0) || {(el = a'[0]) && (size=size'-1) && ((el != a[0]))}
//    public E poll(long timeout, TimeUnit unit)

    //Pre:true
    //Post:
   /* public void	put(E e)*/
//Pre:true
    //Pre:true
    //Post: capacity==capacity'
    public int	remainingCapacity(){return capacity;}


    //Pre:true
    //Post:(size=size' -1) && (if o in a: a[index]!=a'[index])
    public boolean	remove(Object o){
        int index = search(o, false);
        if(index != -1) {
            remove(index);
            return true;
        }
        else return false;
    }


    //Pre:true
    //Post:size==size'
    public int size(){return queue.size();}

    public E take(){
        //TODO
        return null;
    }

    public Object[]	toArray(){
        //TODO
        return null;
    }
    public <T> T[]	toArray(T[] a){
        //TODO
        return null;
    }
   public String	toString(){
        //TODO
        return null;
    }
}
