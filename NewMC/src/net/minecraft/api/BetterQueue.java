package net.minecraft.api;

public class BetterQueue<E>
{
	private class Node<T>
	{
		public Node<T> next = null;
		public T t;
	}
	
	private Node<E> first;
	private Node<E> last;
	private int sz;
	
	public BetterQueue()
	{
		first = null;
		last = null;
		sz = 0;
	}
	
	public BetterQueue(E t[])
	{
		int j = t.length;
		if(j == 0)
		{
			first = null;
			last = null;
			sz = 0;
			return;
		}
		first = new Node<E>();
		last = first;
		last.t = t[0];
		for(int i = 1; i < j; i++)
		{
			last.next = new Node<E>();
			last = last.next;
			last.t = t[i];
		}
	}
	
	public int size()
	{
		return sz;
	}

	public boolean isEmpty()
	{
		if(sz > 0)
			return false;
		return true;
	}

	public boolean contains(E o)
	{
		for(Node<E> c = first; c != null; c = c.next)
		{
			if(c.t == o)
				return true;
		}
		return false;
	}

	public Object[] toArray()
	{
		Object f[] = new Object[sz];
		int i = 0;
		
		for(Node<E> c = first; c != null; c = c.next)
		{
			f[i++] = c.t;
		}
		return f;
	}

	public boolean delete(int index)
	{
		if((index < 0) || (index >= sz))
			return false;
		Node<E> c2 = null;
		for(Node<E> c1 = first.next; c1 != null; c1 = c1.next)
		{
			if(index-- == 0)
			{
				sz--;
				if(c2 == null)
					first = c1.next;
				else
					c2.next = c1.next;
				if(last == c2.next)
					last = c2;
				c1.next = null;
				c1 = null;
				c2 = null;
				return true;
			}
			c2 = c1;
		}
		return false;  //should never get here, but hushes the compiler warning
	}
	
	public boolean remove(E o)
	{
		Node<E> c2 = null;
		for(Node<E> c1 = first; c1 != null; c1 = c1.next)
		{
			if(c1.t == o)
			{
				sz--;
				if(c2 == null)
					first = c1.next;
				else
					c2.next = c1.next;
				if(last == c2.next)
					last = c2;
				c1.next = null;
				c1 = null;
				c2 = null;
				return true;
			}
			c2 = c1;
		}
		return false;
	}

	public void clear()
	{
		sz = 0;
		Node<E> c2 = null;
		for(Node<E> c1 = first; c1 != null; c1 = c2)
		{
			c2 = c1.next;
			c1.next = null;
		}
		first = null;
		last = null;
	}

	public BetterQueue<E> add(E e)
	{
		Node<E> c = new Node<E>();
		
		last.next = c;
		last = c;
		c.t = e;
		sz++;
		return this;
	}


	public E remove()
	{
		if(sz == 0)
			return null;
		sz--;
		
		Node<E> c = first;
		first = c.next;
		if(first == null)
			last = null;
		c.next = null;
		return c.t;
	}

	public E poll()
	{
		if(sz == 0)
			return null;
		return first.t;
	}

	public E peek(int index)
	{
		if((index < 0) || (index >= sz))
			return null;
		for(Node<E> c = first.next; c != null; c = c.next)
		{
			if(index-- == 0)
				return c.t;
		}
		return null;  //should never get here, but hushes the compiler warning
	}
}
