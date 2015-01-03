package design.patterns.observer.comparators;

import design.patterns.observer.items.Item;
import org.apache.commons.lang3.builder.CompareToBuilder;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Item Comparator Class
 */
public class ItemComparator implements Comparator<Item>, Serializable {

    private static final long serialVersionUID = -9146764552091156106L;

    @Override public int compare(Item first, Item second){
        int comparisonValue = 1;
        if( first != null && second != null ){
            comparisonValue = new CompareToBuilder()
                                .append(first.getType(), second.getType())
                                .build();
        } else if( first == null && second == null ){
            comparisonValue = 0;
        }
        return comparisonValue;
    }
}
