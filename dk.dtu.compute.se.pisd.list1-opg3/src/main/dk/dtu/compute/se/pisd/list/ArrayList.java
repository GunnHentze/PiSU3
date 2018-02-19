package dk.dtu.compute.se.pisd.list;

/**
 * A template for implementing a {@see dk.dtu.compute.se.pisd.list.List}
 * by using arrays. This is supposed to be implemented by
 * the students as a task of assignment 2 of the course. Note that
 * since Java arrays cannot be extended dynamically, the array must
 * be replaced at some points by a larger array. The {@see #sort()} method
 * can be implemented by using the bubble sort algorithm.
 * 
 * @author Ekkart Kindler, ekki@dtu.dk
 *
 */
public class ArrayList implements List {
	private Integer[] array = new Integer[10];
	private int size;
	private boolean sorted;

	/*
	 * The size is set to 0.
	 */
	@Override
	public void clear() {
		size=0;
		sorted = true;
	}
	
	
	/*
	 * The method adds an element to a given position in the array, while moving the following elements one place to the right.
	 * First it checks, if the array is big enough to add another integer within the set bounds. If not, it expands.
	 * If the given position if within the limits of the list, it adds the integer either at the head or tail of the array.
	 * Else the integer is added in the position wanted, and a elements to the right e
	 */
	@Override
	public void add(int pos, Integer value) {
		if(size==array.length) {
			asserSize(size);
		}

		if(pos<1) {
			for(int i=size; i>0; i--) {
				array[i]=array[i-1];
			}
			array[0]=value;
		}
		else if(pos>=size) {
			array[size]=value;
		}
		else {
			for(int i=size; i>pos; i--) {
				array[i]=array[i-1];
			}
			array[pos]=value;
		}
		size++;
		sorted = false;
	}

	/**
	 * Help method is called upon if the size of the list can't contain the number of elements.
	 * Initializing a new array twice the size of the original, which copies the elements from the original array
	 * 
	 * @param size 	the size of the array.
	 */
	private void asserSize(int size) {
		Integer[] na = new Integer[size*2];

		for(int i=0; i<array.length;i++) {
			na[i] = array[i];
		}
		array = na;
	}

	@Override
	public Integer get(int pos) {
		if(pos<size && pos>=0) {
			return array[pos];
		}
		else {
			return null;
		}
	}

	@Override
	public Integer remove(int pos) {
		int val = get(pos);
		for(int i=pos; i<size-1; i++) {
			array[i]=array[i+1];
		}
		size--;
		return val;	
	}

	@Override
	public int size() {
		if(size<=1) {
			sorted=true;
		}
		return size;
	}

	@Override
	public void sort() {
		quicksort(0, size-1);
		sorted = true;
	}

	private void quicksort(int lower, int upper) {
		if(lower>=upper) {
			return;
		}
		// Giver f�rst mening n�r vi har to elementer.
		int partition = partition(lower, upper);
		//System.out.println(lower);
		quicksort(lower, partition);
		
		quicksort(partition+1,upper);
	}
	
	private int partition(int lower, int upper) {
		int pivot = array[lower];
		
//		while(array[lower]<pivot) {
//			lower++;
//		}
		while(array[upper]>pivot) {
			upper--;
		}
		if(upper<=lower) {
			return upper;
		}
		int temp = array[lower];
		array[lower] = array[upper];
		array[upper] = temp;
		upper--;
		lower++;
		return pivot;
		}
//		int pivot = array[(lower+upper)/2];	
//		
//		//int pivot = array[(lower+upper)/2];
//		
////		if(lower>(lower+upper)/2) {
////			System.out.println("Adam sucks");
////		}
//		
//		
//		if(array[lower]<pivot && array[upper]>pivot) { 
//			return partition(lower+1, upper-1);
//		}
//		else if(array[lower]>pivot && array[upper]<pivot) {
//			int temp = array[lower];
//			array[lower] = array[upper];
//			array[upper] = temp;
//			return partition(lower+1, upper-1);
//		}
//		else if(array[lower]>pivot) { 
//			return partition(lower, upper-1); 
//		}
//		else if(array[upper] < pivot) {
//			return partition(lower+1, upper);
//		}
//		else {
//			return pivot;
//		}
		 

	
	/** The method finds the position/index of an element in the list, by using binary search.
	 * If the list is yet to be sorted, the method will use linear search.
	 * Whether the list is sorted or not, is determined by the private attribute "sorted"
	 * If the element is not found in the list, the method will return -1.
	 */

	@Override
	public int indexOf(Integer value) {
		if(sorted) {
			return binarySearch(array, 0, size-1, value);
		}
		else {
			for(int i=0; i<size;i++) {
				if(array[i]==value)
					return i;
			}
			return -1;
		}
	}
	/*
	 * Recursive search - Binary search.
	 * 
	 */
	private int binarySearch(Integer[] numbers, int i, int j, Integer val) {
		if(j<i) return -1;
		int m = (int)Math.floor((i+j)/2);
		if(numbers[m]==val) return m;
		else if(numbers[m]<val) return binarySearch(numbers,m+1,j,val);
		else if(numbers[m]>val) return binarySearch(numbers,i,m-1,val);
		else return -1;
	}
	
}
