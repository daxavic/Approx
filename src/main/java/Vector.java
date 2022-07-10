import java.util.Collection;

// предусловие по дефолту у всех методов: (size >= 0) && (capacity>=size)
// где capacity- исходная ёмкость, size - исходный размер
//
// постусловие по дефолту: capacity>=size, где capacity - выходная емкость, size - выходной размер
public class Vector<E>{

    int capacity;
    Base<E> vect;

//    Vector(Base<E> vec){
//        capacity = vec.size() * 2;
//    }



    // Pre: true
    //Post: (size==size'+ 1) && (a[0]..a[size-2] == a'[0]..a'[size'-1]) && (a[size-1]=e)

    public boolean add(E e){
        if (capacity == vect.size()) capacity++;
        vect.add(vect.size()-1, e);
        return true;
    }

    //Pre: (0<=index<size)
    //Post: (size==size'+ 1) && (a[0]..a[index-1]a[index+1]..a[size-2] == a'[0]..a'[size'-1]) && (a[index]==element)
    public void add(int index, Object element){
        vect.add(index,element);
    }

    //Pre: true
    //Post: (a.size==a.size+b.size) && (capacity>=a.size+b.size) && (a==a'+b)
    public boolean addAll(Vector<? extends E> c){
        if (capacity <  vect.size() + c.size()) capacity = vect.size() + c.size();
        else vect.addAll(size(), (Collection) c);
        return true;
    }

    //Pre: true
    //Post: (size==size'+1) && (a[0]..a[size-2] == a'[0]..a'[size'-1]) && (a[size-1] = obj)
    public void addElement(E obj){
        if (capacity < vect.size()) capacity *= 2; //не нашла как правильно увеличивать capacity(должен быть отдельный метод)
        vect.add(size()-1,obj);
    }

    //Pre: true
    //Post: true
    public int capacity(){return capacity;}

    //Pre: true
    //Post: size == 0
    public void clear(){vect.removeAll();}

    //Pre: true
    //Post: a == b
    public Object clone(){
        Base<E> b = new Base<>();
        b.addAll(0, (Collection<?>) vect);
        b.subList(0, false, vect.size() - 1, false);
        return b;
    }

    //Pre: true
    //Post: true (не знаю, какое условие)
    public Boolean contains(Object o){
        if (vect.search(o, false) != -1) return true;
        else return false;
    }

    //Pre: arr.capacity >= arr.length + b.size (??)
    //Post: (arr.length >= b.size) && (arr[0]..arr[b.size-1]==b[0]..b[b.size-1])
    public void copyInto(Object[] anArray){
        //TODO
    }

    // Пусть res - результат метода
    //Pre: (0<=index<size)
    //Post: (a=a')&&(res==a[index])
    public E elementAt(int index){return vect.get(index);}

    //Pre:  minCapacity > 0
    //Post: capacity < minCapacity
    public void ensureCapacity(int minCapacity){
        capacity = minCapacity;
    }

    //Pre: size > 0
    //Post: (a[0] == element) && (a == a') {или просто a[0] == element?}
    public E firstElement(){return vect.get(0);}

    //Pre: (0<=index<size)
    //Post: (res == a[index]) && (a == a') (или (res == a[index])?)
    public E get(int index){return vect.get(index);}

    //Pre: true
    //Post: {(a[res] == o) && (0<res<size) && (a[0]..a[res-1] != o)} || {(a[0]..a[size - 1] != o) || (res = -1)}
    public int indexOf(Object o){return vect.search(o,false);}

    //Pre: (0<=index<size)
    //Post: {(a[res] == o) && (index <= res<size) && (a[index]..a[res-1] != o)} || {a[index]..a[size - 1] != o) && (res = -1)}
    public int indexOf(Object o, int index){
        Base<E> b = new Base<E>();
        b.addAll(0, (Collection<?>) vect);
        b.subList(index, true, size() -1, true);
        int index1 = b.search(o,false);
        if (index1 == -1) return -1;
        else return(index+index1);
    }

    //Pre: (0<=index<size)
    //Post: (size==size'+ 1) && (a[0]..a[index-1]a[index+1]..a[size-2] == a'[0]..a'[size'-1]) && (a[index]==obj)
    public void insertElementAt(E obj, int index){
        if (capacity <  vect.size() + 1) ensureCapacity(size() + 1);
        else add(index,obj);
    }

    //Pre: true
    //Post: {(size == 0) && (res == true }|| {(size != 0) && (res == false)}
    // {или Post: true}
    public Boolean isEmpty(){return vect.size() == 0;}

    //Pre: size > 0
    //Post: (res = a[size - 1]) && (a == a')
    public E lastElement(){return get(vect.size() - 1);}

    //Pre: size != 0
    //Post: {(a[res] == o) && (a[res + 1]..a[size-1] != o)} || (o not in a)
    public int lastIndexOf(Object o){return vect.search(o,true);}

    //Pre: size != 0
    //Post: {(a[res] == o) && (index<=res<size) && (a[res + 1]..a[size-1] != o)} || (a[index]..a[size - 1]!= o)
    public int lastIndexOf(Object o, int index){
        Base<E> b = new Base<E>();
        b.addAll(0, (Collection<?>) vect);
        b.subList(index, true, b.size()-1, true);
        int index1 = b.search(o,true);
        if (index1 == -1) return -1;
        else return(index+index1);
    }

    //Pre:(0<=index<size)
    //Post:(size=size' -1) && (a[0]..a[size-1] == a'[0]..a'[index-1][index+1]..a'[size'-1])
    public void remove(int index){vect.remove(index);}

    //Pre:true
    //Post:{(a'[index]==o)&&(size=size' -1) && (a[0]..a[size-1)==a'[0]..a'[index-1]a'[index+1}..a'[size'-1]} ||
    // || {o not in a}, где index - идекс o в векторе a
    public Boolean remove(Object o){
        int index = indexOf(o);
        if(index != -1) {
            vect.remove(index);
            return true;}
        else return false;
    }

    //Pre:true
    //Post: size == 0;
    public Boolean removeAll(Base<?> c){
        c.removeAll();
        if (c.size() == 0) return true;
        else return false;
    }

    //Pre:  true
    //Post: size == 0
    public void removeAllElements(){vect.removeAll();}

    //Pre:true
    //Post:{(a'[index]==obj)&&(size=size' -1) && (a[0]..a[size-1]==a'[0]..a'[index-1]a'[index+1}..a'[size'-1])} ||
    // || {obj not in a}, где index - идекс o в векторе a
    public Boolean removeElement(Object obj){
        int index = indexOf(obj);
        if(index != -1) {vect.remove(index); return true;}
        else return false;
    }

    //Pre:(0<=index<size)
    //Post:(size=size' -1) && (a[0]..a[size-1]==a'[0]..a'[index-1]a'[index+1}..a'[size'-1])
    public void removeElementAt(int index){vect.remove(index);}

    //Pre: (0<=fromIndex<=toIndex<size)
    //Post: (size=size'-(toIndex-fromIndex+1)) && (a[0]..a[size-1]==a'[0]..a[fromIndex-1]a'[toIndex]..a'[size'-1])
    protected void removeRange(int fromIndex, int toIndex){
        Base<E> b = new Base<E>();
        Base<E> d = new Base<E>();
        b.addAll(0, (Collection<?>) vect);
        d.addAll(0, (Collection<?>) b);
        b.subList(toIndex, true, b.size()-1, true);
        d.subList(0, true, fromIndex - 1, true);
        vect.removeAll();
        vect.addAll(0, (Collection<?>) b);
        vect.addAll(vect.size(), (Collection<?>) d);
    }

    //Pre: (0<=index<size)
    //Post: (size==size')&&(a[index]==element)&&
    // &&(a[0]..a[index-1]a[index+1]..a[size-1]==a'[0]..a'[index-1]a'[index+1]..a'[size'-1])
    public E set(int index, E element){
        vect.add(index, element);
        E res = vect.get(index + 1);
        vect.remove(index + 1);
        return res;
    }

    //Pre: (0<=index<size)
    //Post: (size==size')&&(a[index]==obj)&&
    // &&(a[0]..a[index-1]a[index+1]..a[size-1]==a'[0]..a'[index-1]a'[index+1]..a'[size'-1])
    public void setElementAt(E obj, int index){
        vect.add(index, obj);
        vect.remove(index + 1);
    }

    //Pre: true
    //Post: size = newSize
    public void  setSize(int newSize){
        int size;
        if (newSize < vect.size()) removeRange(newSize, vect.size());
        else {
            Base<E> b = new Base<E>();
            size = vect.size() - newSize;
            add((E) b);}
    }

    //Pre: true
    //Post: size==size'
    public int size(){return vect.size();}

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
    public void trimToSize(){capacity = vect.size();}

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




