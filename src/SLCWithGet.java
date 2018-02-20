/**
 TDA 416 Lab 2
 Grupp 37
 Rasmus Tomasson (rastom), Sofia Larborn (soflarb)
 */

public class SLCWithGet<E extends Comparable<? super E>> extends LinkedCollection<E> implements CollectionWithGet<E> {

    /**
     *  Finds the first occurence of an element
     *  in the collection that is equal to the argument
     *  <tt>e</tt> with respect to its natural order.
     *  I.e. <tt>e.compareTo(element)</tt> is 0.
     *
     *  @param e The dummy element to compare to.
     *  @return  An element  <tt>e'</tt> in the collection
     *           satisfying <tt>e.compareTo(e') == 0</tt>.
     *           If no element is found, <tt>null</tt> is returned
     */
    @Override
    public E get(E e) {
        //iterate the list and find the specified element
        if(e == null){
            return null;
        }
        return recursiveGet(e,head);
    }

    /**
     * Finds the specified element in the collection, returns its entry
     * @param e element to find
     * @param entry current entry to compare to
     * @return entry if found, null otherwise
     */
    private E recursiveGet(E e, Entry entry){
        if(entry == null)return null;

        int comp = e.compareTo(entry.element);

        if(comp == 0) {
            return entry.element;
        }
        else if(comp > 0){
            return null;
        }
        else{
            return recursiveGet(e,entry.next);
        }
    }

    /**
     * Adds an element to the collection in a selected manner
     * @param e the object to add into the list
     * @return true if the object has been added to the list.
     * @throws NullPointerException if parameter <tt>e<tt> is null.
     */
    @Override
    public boolean add(E e) {

        if(e == null){
            throw new NullPointerException();
        }
        if ((head == null) || (e.compareTo(head.element) < 0)){
            head = new Entry(e,head);
            return true;
        }
        return addHelper(e,head);
    }

    /**
     * Recursively follows the links until the element can be added
     * @param e the object to add into the list
     * @param current the current entry to compare to
     * @return head
     */
    private boolean addHelper(E e, Entry current){
		while (current.next != null)
		{
			if( e.compareTo(current.next.element) < 0 ){
				current.next = new Entry(e, current.next);
				return true;
			}
			current = current.next;
		}
		current.next = new Entry(e,null);
		return true;
    }
}
