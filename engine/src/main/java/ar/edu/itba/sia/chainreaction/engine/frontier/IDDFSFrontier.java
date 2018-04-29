package ar.edu.itba.sia.chainreaction.engine.frontier;

import ar.edu.itba.sia.chainreaction.engine.Node;

import java.util.*;

/**
 * Created by alejosaques on 29/4/18.
 */
public class IDDFSFrontier<E> implements Frontier<E> {


  private int currentDepth = 0;

  private int size = 0;


  private Map<Integer, Deque<Node<E>>> m;


  public IDDFSFrontier(){
    m = new TreeMap<>();
  }

  private boolean isAvailable(int depth){
    Deque<Node<E>> d = m.get(depth);
    if(d != null) {
      if (!d.isEmpty())
        return true;
      else
        m.remove(depth);
    }
    return false;
  }

  @Override
  public Node<E> getPrioritary() {
    if(isEmpty())
      throw new NoSuchElementException();
    while (!isAvailable(currentDepth))
      currentDepth ++;
    Deque<Node<E>> d = m.get(currentDepth);
    size --;
    return d.removeFirst();
  }

  @Override
  public void add(Node<E> n) {
    Deque<Node<E>> d = m.get(n.getDepth());
    if(d == null){
      d = new LinkedList<>();
      m.put(n.getDepth(), d);
    }
    d.addLast(n);
    size ++;
  }

  @Override
  public Node<E> observePrioritary() {
    if(isEmpty())
      throw new NoSuchElementException();
    while (!isAvailable(currentDepth))
      currentDepth ++;
    Deque<Node<E>> d = m.get(currentDepth);
    return d.getFirst();
  }

  @Override
  public int size() {
    return size;
  }

}
