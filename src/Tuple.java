import java.lang.reflect.Array;
import java.util.*;

/***
 * @author Frederick Clark/sclark2006
 */
public class Tuple<T>  implements InmutableList<T> {
    private List<T> data;
    private Tuple(List<T> data){
        this.data = data;
    }
    private String arrayToString(Object arrayObject, String enclosers) {
        final int arrayLength = Array.getLength(arrayObject);
            final char encloserLeft = enclosers.charAt(0), encloserRight = enclosers.charAt(1);
        if (arrayLength == 0) return encloserLeft + "" + encloserRight;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(encloserLeft);
        int lastElement = arrayLength - 1;
        for (int i = 0; ; i++) {
            var value = Array.get(arrayObject,i);
            if (value instanceof String) stringBuilder.append('"').append(value).append('"');
            else if (value.getClass().isArray()) stringBuilder.append(arrayToString(value,"[]"));
            else stringBuilder.append(String.valueOf(value));
            if (i == lastElement) return stringBuilder.append(encloserRight).toString();
            stringBuilder.append(", ");
        }
    }

    @Override
    public int indexOf(Object element) {
        return data.indexOf(element);
    }

    @Override
    public boolean contains(Object object) {
        return data.contains(object);
    }

    @Override
    public InmutableList<T> subList(int fromIndex, int toIndex) {
        return Tuple.<T>of((T[])data.subList(fromIndex, toIndex).toArray());
    }

    @Override
    public InmutableList<T> join(InmutableList<T> tuple) {
        List<T> tList = this.toList();
        tList.addAll(tuple.toList() );
        return new Tuple<>(tList);
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public Object[] toArray() {
        return data.toArray();
    }

    @Override
    public List<T> toList() {
        return new ArrayList<T>(data);
    }

    @Override
    public T get(int index) {
        return (T) data.get(index);
    }

    public <C> C getCasted(int index) {
        return (C) data.get(index);
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return data.iterator();
    }

    @Override
    public String toString() {
        return arrayToString(this.data.toArray(),"()");
    }

    public static <T> Tuple<T> of(T... values) {
        return new Tuple<T>(List.of(values));
    }
}
