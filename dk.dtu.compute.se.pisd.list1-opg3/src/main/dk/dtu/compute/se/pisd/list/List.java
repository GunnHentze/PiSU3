package dk.dtu.compute.se.pisd.list;


/**
 * A list of {@see java.lang.Integer} values. An arbitrary number of values
 * can be added to the list at a given position. The values can be obtained
 * from the list from any position.
 * 
 * Note that we use the class {@see java.lang.Integer} here instead of a data type
 * for two reasons: first, we will extend this class later for defining
 * lists for other types than integers; second, using the class
 * {@see java.lang.Integer} allows us to return null as a result,
 * in case of errors (note that this will be improved later by using
 * exception handling).
 * 
 * @author Ekkart Kindler, ekki@dtu.dk
 */
public interface List {
	
	
	/**
	 * Removes all elements from the list. The list will be empty after the
	 * call returns. 
	 */
	void clear();

	/**
	 * Adds the an {@see java.lang.Integer} value at the given position to
	 * the list. If the position is 0 or negative, the value will be added
	 * as the first element (at position 0) of the list; if the position is
	 * greater or equal than the {@see #size()}, the value will be added
	 * at the end of the list.
	 * 
	 * @param pos   the position at which the value is to be inserted 
	 * @param value the value to be inserted
	 */
	void add(int pos, Integer value);
	
	/**
	 * Returns the value of the list at the given position. The list is not
	 * changed. If the position is outside the range of values of the list,
	 * <code>null</code> will be returned.
	 * 
	 * @param pos the position from which the value should be returned
	 * 
	 * @return the value at the given position
	 */
	Integer get(int pos);
	
	/**
	 * Removes the value at the given position from the list, and returns
	 * the removed value. If the position is outside the range of values
	 * of the list, <code>null</code> will be returned, and the
	 * list is not changed.
	 * 
	 * @param pos the position from which the value should be removed
	 * 
	 * @return the value which was removed
	 */
	Integer remove(int pos);
	
;
	
	/**
	 * Returns <code>true</code> if the list does not contain any elements.
	 * 
	 * @return <code>true</code> if the list contains no elements
	 */
	default boolean isEmpty() {
		return size() == 0;
	}
	
	/**
	 * Returns the number of values in the list. This is called the size
	 * of the list. 
	 * 
	 * @return the current size of the list
	 */
	int size();
	
	/**
	 * Sorts the values of the list in ascending order. Duplicate values
	 * are not removed by this operation
	 * 
	 */
	void sort();

	int indexOf(Integer value);
}
