package poeflasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

public class POEFlasks {

    private final static Integer[] globalFlasks = 
        {16,13,19,5,17,13,13,14,17,17,5,6,13,13,11,10,15,7,13,9,5,9,7,10,14,9,17,11,5,12,9,18,17,8,11,12,12,6,8,9,12,6,6,7,6,6,17,8};
    
    public static void main(String[] args) {
        System.out.println("Size: " + globalFlasks.length);
        Arrays.sort( globalFlasks );
        
        int maxBunches = 0;
        for( int f : globalFlasks )
            maxBunches += f;
        maxBunches /= 40;
        System.out.println("Max bunches possible: " + maxBunches);
        
        get40s( Arrays.asList( globalFlasks ), 0, new ArrayList<List<Integer>>() );
//        System.out.println( get40( Arrays.asList( globalFlasks ), -1, 0, new ArrayList<Integer>() ) );
    }

    private static List<Integer> removeOnce( List<Integer> list, List<Integer> toRemove ){
        List<Integer> newList = new ArrayList<>();
        int i = 0;
        for( int current : toRemove ){
            while( list.get(i) != current ){
                newList.add( list.get(i) );
                i++;
            }
            i++;
        }

        for( int j=i ; j<list.size() ; j++ )
            newList.add( list.get(j) );
        
        return newList;
    }
    
    private static int maxDone = 0;
    private static void get40s( List<Integer> list, int total, List<List<Integer>> currentList ){
        if( total > maxDone ){
            maxDone = total;
            System.out.println("Current: " + total + " -> " + list + " @@ " + currentList);
        }

        LinkedHashSet<List<Integer>> fTs = get40( list, -1, 0, new ArrayList<Integer>() );
        for( List<Integer> fT : fTs ){
            List<List<Integer>> newCurrentList = new ArrayList<>( currentList );
            newCurrentList.add( fT );
            get40s( removeOnce( list, fT ), total+1, newCurrentList );
        }
    }
    
    private static LinkedHashSet<List<Integer>> get40( List<Integer> flasks, int offset, int total, List<Integer> currentList ){
        LinkedHashSet<List<Integer>> newList = new LinkedHashSet<>();

        if( total == 40 )
            newList.add( currentList );
        else if (total < 40 )
            for( int i=offset+1 ; i<flasks.size() ; i++ ){
                List<Integer> newCurrentList = new ArrayList<>( currentList );
                newCurrentList.add( flasks.get(i) );
                newList.addAll( get40( flasks, i, total + flasks.get(i), newCurrentList ) );
            }
        
        return newList;
    }
}
