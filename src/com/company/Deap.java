package com.company;

public class Deap {
    int[]deap;
    int numOfDeap;

public Deap(int size) {
    deap =new int[size];
}

    /**
     *If the element is in min-Heap. get the corresponding node J.
     * @param i element in minHeap.
     * @return J
     */
    public int inMinHeapGetJ (int i) {
        //J = i+2^([log2^i]-1)
        int j= (int) (i + Math.pow(2, Math.floor(Math.log(i + 1) / Math.log(2)) - 1));
        /**
         * If such node j does not exist, then let j be the node in the right subtree that
         * corresponds to the parent of i.
         */
        //if (j>n),j = [j/2]
        if(j>numOfDeap){
            j=(j-1)/2;
        }
    return j;
}

    /**
     * If the element is in max-Heap. get the corresponding node I.
     * @param j element in maxHeap.
     * @return I
     */
    public  int inMaxHeapGetI (int j) {
       // i = j-2^([log2^j]-1)
      int i = (int) (j - Math.pow(2,Math.floor(Math.log(j + 1) / Math.log(2)) - 1));
    return i;
}

    /**
     * Determines whether the element is in minheap or maxHeap.
     * @return if return true, means element in MaxHeap. return false means element in MinHeap.
     */
    protected boolean inMaxHeap(int i) {
        if( i >= 3 * Math.pow(2, Math.floor(Math.log(i + 1) / Math.log(2)) - 1 ) - 1){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Insertion
     * For minInsert: Compare value with the parent node, if value < parent node, swap!
     * For maxInsert: Compare value with the parent node, if value > parent node, swap!
     */

    private void minInsert(int location, int value) {
        //Make sure that parent node is not null.Then compare value with the parent node, if value < parent node, swap!
        for (int current; (current = (location - 1) / 2) != 0 && value < deap[current]; deap[location] = deap[current], location = current);
            deap[location] = value;
    }

    private void maxInsert(int location, int value) {
        //Make sure that parent node is not null.Then compare value with the parent node, if value > parent node, swap!
        for (int current; (current = (location - 1) / 2) != 0 && value > deap[current]; deap[location] = deap[current], location = current);
            deap[location] = value;
    }

    /**
     * Delete element.
     *Assignment:  removeHigh() ,removeLow() ;
     */
    public int removeHigh() {
        //delete the apex of second tree.
        int largestValueIndex = 2;
        int temValue = deap[numOfDeap--];
        while (largestValueIndex * 2 + 1 < numOfDeap) {
            //Compare the left and right elements and take the larger element as the node.
            if (deap[largestValueIndex * 2 + 1] > deap[largestValueIndex * 2 + 2]) {
                deap[largestValueIndex] = deap[largestValueIndex * 2 + 1];
                largestValueIndex = largestValueIndex * 2 + 1;
            } else {
                deap[largestValueIndex] = deap[largestValueIndex * 2 + 2];
                largestValueIndex = largestValueIndex * 2 + 2;
            }
        }
        int min = inMinHeapGetJ(largestValueIndex);
        if (min * 2 - 1 < numOfDeap) {
            if (deap[min * 2 + 1] > deap[min * 2 + 2]) {
                min = min * 2 + 1;
            } else {
                min = min * 2 + 2;
            }
        }
        //i.key <= j.key
        if (temValue >= deap[min]) {
            deap[largestValueIndex] = temValue;
        } else {
             deap[largestValueIndex] = deap[min];
            minInsert(min, temValue);
        }
        return temValue;
    }

    public int removeLow() {
        //delete apex of the first tree.
        int smallestElementIndx = 1;
        int temValue = deap[numOfDeap--];
        while (smallestElementIndx * 2 + 1 < numOfDeap) {
            //Compare the left and right elements and take the smaller element as the node.
            if (deap[smallestElementIndx * 2 + 1] < deap[smallestElementIndx * 2 + 2]) {
                deap[smallestElementIndx] = deap[smallestElementIndx * 2 + 1];
                smallestElementIndx = smallestElementIndx * 2 + 1;
            } else {
                deap[smallestElementIndx] = deap[smallestElementIndx * 2 + 2];
                smallestElementIndx = smallestElementIndx * 2 + 2;
            }
        }
        //i.key <= j.key
        int max = inMinHeapGetJ(smallestElementIndx);
        if (temValue > deap[max]) {
            deap[smallestElementIndx] = deap[max];
            maxInsert(max, temValue);
        } else {
            deap[smallestElementIndx] = temValue;
        }
        return temValue;

    }



    /**
     * Assignment: isEmpty() , size() , getLow() , getHigh() , add(x);
     */


     public boolean isEmpty () {
         //if deap is empty, return false, else return true.
         return numOfDeap == 0;
     }

    public int size(){
        return numOfDeap;
    }

    public int getLow()  {
        return deap[1];
    }

    public int getHigh() {
        return deap[2];
    }

    /**
     * Insert an element into the deap.
     * @param a Inserts the element a.
     */
    public void add(int a) {
        numOfDeap++;
        //if the deap is empty, X will be the first element.
        if (numOfDeap == 1) {
            deap[1] = a;
            return;
        }
        //if deap!=null && x position in the max-heap.
        if(inMaxHeap(numOfDeap)){
            int i = inMaxHeapGetI(numOfDeap);
            if (a < deap[i]) {
                deap[numOfDeap] = deap[i];
                minInsert(i, a);
            } else {
                maxInsert(numOfDeap, a);
            }
        }
        //if deap!=null && x position in the min-heap.
        else {
            int j = inMinHeapGetJ(numOfDeap);
            if (a > deap[j]) {
                deap[numOfDeap] = deap[j];
                maxInsert(j, a);
            } else {
                minInsert(numOfDeap, a);
            }
        }
    }

    public int[] getDeap() {
        return deap;
    }

    public void setDeap(int[] deap) {
        this.deap = deap;
    }

    public int getNumOfDeap() {
        return numOfDeap;
    }

    public void setNumOfDeap(int numOfDeap) {
        this.numOfDeap = numOfDeap;
    }
}