import java.util.Collection;

public class Vector<E> extends Base{

    //private int size = super.size();
    private int capacity;

    Vector(Base<E> vec){
        capacity = vec.size() * 2;
    }


    //Пусть a исходный вектор
    // Pre: true
    //Post: (size==size'+ 1) && (capacity>=size'+ 1) && (a[size-1]=e)
    public boolean add(E e){
        if (capacity == size) capacity++;
        add(size-1, e);
        return true;
    }

    //Pre: (0<=index<size)
    //Post: (size==size'+ 1) && (capacity>=size'+ 1) && (a[size-1]=e)
    public void add(int index, Object element){
        size ++;
        if (capacity <  size + 1) capacity++;
        add(index,element);
    }

    //Pre: true
    //Post: (a.size==a.size+b.size) && (capacity>=a.size+b.size) && (a==a'+b)
    public boolean addAll(Vector<? extends E> c){
        size += c.size;
        if (capacity <  size + c.size) capacity = size + c.size;
        else addAll(size, (Collection) c);
        return true;
    }

    //Pre: true
    //Post: (size==size'+1) && (a.[size-1] = obj)
    public void addElement(E obj){
        size++;
        if (capacity < size) capacity *= 2; //не нашла как правильно увеличивать capacity(должен быть отдельный метод)
        add(size-1,obj);
    }

    //Pre: true
    //Post: capacity > size
    public int capacity(){return capacity;}

    //Pre: true
    //Post: size == 0
    public void clear(){removeAll();}

    //Pre: true
    //Post: a == b
    public Object clone(){
        Vector<E> b = new Vector<>(new Base<E>());
        subBase(0, this, size - 1, b);
        return b;
    }

    //Pre: true
    //Post: true (не знаю, какое условие)
    public Boolean contains(Object o){
        if (search(o, false) != -1) return true;
        else return false;
    }

    //Pre: arr.capacity < arr.size + b.size
    //Post: (arr.length >= b.size) && (arr==b)
    public void copyInto(Object[] anArray){
        //TODO
    }

    //Pre: (0<=index<size)
    //Post:true
    public E elementAt(int index){return get(index);}

    //Pre: 0 < minCapacity
    //Post: capacity < minCapacity
    public void ensureCapacity(int minCapacity){
        capacity = minCapacity;
    }

    //Pre: size > 0
    //Post: true
    public E firstElement(){return get(0);}

    //Pre: (size > 0) && (0<=index<size)
    //Post: true
    public E get(int index){return get(index);}

    //Pre: true
    //Post: element is in vector?
    public int indexOf(Object o){return search(o,false);}

    //Pre: (0<=index<size)
    //Post: true
    public int indexOf(Object o, int index){
        Vector<E> b = new Vector<>(new Base<E>());
        subBase(index, this, size-1, b);
        int index1 = b.search(o,false);
        if (index1 == -1) return -1;
        else return(index+index1);
    }

    //Pre: (0<=index<size)
    //Post: capacity>=size' + 1)&&(size==size' + 1) && (a[index]=obj)
    public void insertElementAt(E obj, int index){
        size++;
        if (capacity <  size + 1) ensureCapacity(size + 1);
        else add(index,obj);
    }

    //Pre: true
    //Post: true
    public Boolean isEmpty(){return size == 0;}

    //Pre: size != 0
    //Post: a.lastElement() = a[a.size - 1]
    public E lastElement(){return get(size - 1);}

    //Pre: size != 0
    //Post: a.lastElement(o) = a[a.size - 1]
    public int lastIndexOf(Object o){return search(o,true);}

    //Pre:(0<=index<size)
    //Post:true
    public int lastIndexOf(Object o, int index){
        Vector<E> b = new Vector<>(new Base<E>());
        subBase(index, this, size-1, b);
        int index1 = b.search(o,true);
        if (index1 == -1) return -1;
        else return(index+index1);
    }

    //Pre:(0<=index<size)
    //Post:(size=size' -1) && (if o in a: a[index]!=a'[index])
    public void remove(int index){remove(index);}

    //Pre:true
    //Post:(size=size' -1) && (if o in a: a[index]!=a'[index])
    public Boolean remove(Object o){
        int index = indexOf(o);
        if(index != -1) {
            remove(index);
            return true;}
        else return false;
    }

    //Pre:true
    //Post: size == 0;
    public Boolean removeAll(Vector<?> c){
        c.removeAll();
        if (c.size() == 0) return true;
        else return false;
    }

    //Pre:true
    //Post: size == 0
    public void removeAllElements(){removeAll();}

    //Pre:true
    //Post: (size==size' -1) && (obj not in a )
    public Boolean removeElement(Object obj){
        int index = indexOf(obj);
        if(index != -1) {remove(index); return true;}
        else return false;
    }

    //Pre:(0<=index<size)
    //Post:(size=size' -1) && (a[index]!=a'[index])
    public void removeElementAt(int index){remove(index);}

    //Pre: (0<=fromIndex<=toIndex<size)
    //Post: (size=size'-(toIndex-fromIndex+1)) && (deleted elements not in a)
    protected void removeRange(int fromIndex, int toIndex){
        Vector<E> b = new Vector<>(new Base<E>());
        Vector<E> d = new Vector<>(new Base<E>());
        subBase(toIndex, this, size-1, b);
        subBase(0, this, fromIndex - 1, d);
        removeAll();
        this.addAll(b);
        this.addAll(d);
    }

    //Pre: (0<=index<size)
    //Post: (size==size')&&(a[index]==element)
    public E set(int index, E element){
        add(index, element);
        E res = get(index + 1);
        remove(index + 1);
        return res;
    }

    //Pre: (0<=index<size)
    //Post: (size==size')&&(a[index]==element)
    public void setElementAt(E obj, int index){
        add(index, obj);
        remove(index + 1);
    }

    //Pre: true
    //Post: size = newSize
    public void  setSize(int newSize){
        if (newSize < size) removeRange(newSize,size);
        else {
            Vector<E> b = new Vector<>(new Base<E>());
            b.size = size - newSize;
            add((E) b);}
    }

//       //Pre: true
//    //Post: size==size'
//    public int size(){return size();}
//
//    //Pre: (0<=fromIndex<=toIndex<size)
//    //Post: size == toIndex-fromIndex+1
//    public List<E> subList(int fromIndex, int toIndex){
//        Vector<E> b = new Vector<>(new Base<E>());
//        subBase(fromIndex, this, toIndex, this);
//        removeAll();
//        addAll(b);
//        return (List<E>) this;
//    }

    //Pre: true
    //Post: capacity = size
    public void trimToSize(){capacity = size;}

    public Object[] toArray(){
        //TODO
        return null;
    }
    public<T> T[] toArray(T[] a){
        //TODO
        return null;
    }
    public String toString(){
        //TODO
        return null;
    }
    public boolean	 retainAll(Collection<?> c){
        //TODO
        return false;
    }
//    public ListIterator<E>	 listIterator(){
//        //TODO
//        return null;
//    }
//    public ListIterator<E>	listIterator(int index){
//        //TODO
//        return null;
//    }
//    public Iterator<E>	iterator(){
//        //TODO
//        return null;
//    }







}




