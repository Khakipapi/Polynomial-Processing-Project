/*
 *Jose Reyes
 * Project 2
 * 11/12/2022
 * UMGC
 * CMSC 350
 *
 * determines whether a List object, supplied as a parameter, is in strictly
ascending order.
 */
import java.util.List;

public class OrderedList {
    public static <T extends Comparable<? super T>> boolean checkSorted(List<T> list){
        boolean isSorted = true;
        for(int i = list.size()-1; i > 0 ; i--){
            T current = list.get(i);
            if(!checkSorted(list, current)){
                isSorted = false;
            }
        }
        return isSorted;
    }

    private static <T extends Comparable<? super T>> boolean checkSorted(List<T> list, T current) {

        T currentValue = list.get(list.indexOf(current));
        T nextValue = list.get(list.indexOf(current) - 1);

        if (nextValue != null) {
// check for null values
            return currentValue.compareTo(nextValue) >= 0;
        }
        return true;
    }
}
