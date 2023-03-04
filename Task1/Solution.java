import java.util.*;

class customHeap {
    private int x;
    private int n = (int)Math.pow(2,x);
    private List<Integer> pq;

    // constructors
    public customHeap() {
        x = 2; // by default binary heap
        pq = new ArrayList<Integer>();
    }

    public customHeap(int k) {
        x = k;
        pq = new ArrayList<Integer>();
    }

    public String print(){
        return pq.toString();
    }

    // inserting a value in the heap
    public void insert(int value) {
        // first insert it at the bottom
        pq.add(value);

        // Now heapify up till we place the value at the correct position
        int childrenIndex = pq.size() - 1;
        int parentIndex = (childrenIndex - 1) / n;
        while(parentIndex >= 0 && pq.get(childrenIndex) > pq.get(parentIndex)) {
            // since the value at the childrenIndex is greater and it is max heap, so we should swap
            int temp = pq.get(childrenIndex);
            pq.set(childrenIndex, pq.get(parentIndex));
            pq.set(parentIndex, temp);

            childrenIndex = parentIndex;
            parentIndex = (childrenIndex - 1) / n;
        }
    }

    // removing the maximum element from the heap - which will be at the top in case of a max heap
    public String popMax() {
        if(pq.isEmpty()) return "NULL HEAP";
        pq.set(0, pq.get(pq.size() - 1));
        pq.remove(pq.size() - 1);

        int parentIndex = 0;

        while(true) {
            int largestValueIndex = parentIndex;

            // visit all its children and find the maximum value child among them
            for(int i = n * parentIndex + 1; i <= (n * parentIndex + n) && i < pq.size(); i++) {
                if(pq.get(largestValueIndex) < pq.get(i)) {
                    largestValueIndex = i;
                }
            }

            if(largestValueIndex == parentIndex) {
                break;
            } else {
                // if the largest index is not the parent index, we need to heapify down
                int temp = pq.get(parentIndex);
                pq.set(parentIndex, pq.get(largestValueIndex));
                pq.set(largestValueIndex, temp);
                parentIndex = largestValueIndex;
            }
        }

        //return the updated heap as String
        return pq.toString();
    }
}

public class Solution{
    public static void main(String[] args){
        //creating customHeap Objects and comparing instances

        // custom heap with x=2 (normal max-heap)
        customHeap heap1 = new customHeap();
        int n = (int)(100*Math.random());
        for(int i=0; i<n; i++){
            heap1.insert((int)(Math.random()*100));
        }
        System.out.println("Heap is: "+heap1.print());
        n = n%(int)(100*Math.random());
        for(int i=0; i<n; i++){
            System.out.println("After popMax: "+heap1.popMax());
        }

        // custom heap with x=10 (intermediate value)
        customHeap heap2 = new customHeap(10);
        n = (int)(500*Math.random());
        for(int i=0; i<n; i++){
            heap2.insert((int)(Math.random()*1000));
        }
        System.out.println("Heap is: "+heap2.print());
        n = n%(int)(500*Math.random());
        for(int i=0; i<n; i++){
            System.out.println("After popMax: "+heap2.popMax());
        }

        // custom heap with x=100 (large value)
        customHeap heap3 = new customHeap(100);
        n = (int)(1000*Math.random());
        for(int i=0; i<n; i++){
            heap3.insert((int)(Math.random()*10000));
        }
        System.out.println("Heap is: "+heap3.print());
        n = n%(int)(1000*Math.random());
        for(int i=0; i<n; i++){
            System.out.println("After popMax: "+heap3.popMax());
        }
    }
}