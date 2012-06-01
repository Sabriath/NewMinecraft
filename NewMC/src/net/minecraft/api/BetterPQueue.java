package net.minecraft.api;

public class BetterPQueue<E>
{
	private class Node<T>
	{
		public Node<T> prev = null;
		public Node<T> next = null;
		public int c;
		public T t;
	}
	
	private Node<E> first = null;
	private Node<E> last = null;
	private int sz = 0;

	public BetterPQueue()
	{
		first = null;
		last = null;
		sz = 0;
	}
	
	public BetterPQueue(Object t[])
	{
		first = null;
		last = null;
		sz = 0;
		
		int j = t.length;
		if(j < 2)
			return;
		for(int i = 2; i < j; i += 2)
			add((Integer)t[i], (E)t[i+1]);
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
		Object f[] = new Object[sz * 2];
		int i = 0;
		
		for(Node<E> c = last; c != null; c = c.prev)
		{
			f[i++] = c.c;
			f[i++] = c.t;
		}
		return f;
	}

	public boolean remove(E o)
	{
		for(Node<E> c1 = first; c1 != null; c1 = c1.next)
		{
			if(c1.t == o)
			{
				sz--;
				if(c1.prev == null)
					first = c1.next;
				else
					c1.prev.next = c1.next;
				if(c1.next == null)
					last = c1.prev;
				else
					c1.next.prev = c1.prev;
				c1.prev = null;
				c1.next = null;
				return true;
			}
		}
		return false;
	}

	public void clear()
	{
		sz = 0;
		Node<E> c2;
		for(Node<E> c1 = first; c1 != null; c1 = c2)
		{
			c2 = c1.next;
			c1.prev = null;
			c1.next = null;
		}
		first = null;
		last = null;
	}

	public BetterPQueue<E> add(int e, E f)
	{
		Node<E> c = new Node<E>();
		c.c = e;
		c.t = f;
		sz++;

		if(first == null)
		{
			first = c;
			last = c;
			c.prev = null;
			c.next = null;
			return this;
		}
		for(Node<E> c2 = first; c2 != null; c2 = c2.next)
		{
			if(e < c2.c)
			{
				c.prev = c2.prev;
				c.next = c2;
				if(c2.prev == null)
					first = c;
				else
					c2.prev.next = c;
				return this;
			}
		}
		last.next = c;
		c.prev = last;
		last = c;
		return this;
	}


	public E removeLow()
	{
		if(sz == 0)
			return null;
		sz--;
		
		Node<E> c = first;
		first = first.next;
		if(first == null)
			last = null;
		else
			first.prev = null;
		c.next = null;
		return c.t;
	}
	
	public E removeHigh()
	{
		if(sz == 0)
			return null;
		sz--;
		
		Node<E> c = last;
		last = last.prev;
		if(last == null)
			first = null;
		else
			last.next = null;
		c.prev = null;
		return c.t;
	}

	public E pollLow()
	{
		if(sz == 0)
			return null;
		return first.t;
	}
	
	public E pollHigh()
	{
		if(sz == 0)
			return null;
		return last.t;
	}

	public E peek(int index)
	{
		if(sz == 0)
			return null;
		if((index <= 0) || (index >= sz))
			return null;
		for(Node<E> c = first.next; c != null; c = c.next)
		{
			if(--index == 0)
				return c.t;
		}
		return null;  //should never get here, but hushes the compiler warning
	}
	
	public E Find(int prio)
	{
		if(sz == 0)
			return null;
		if((prio < first.c) || (prio > last.c))
			return null;
		for(Node<E> c = first.next; c != last; c = c.next)
		{
			if(c.c == prio)
				return c.t;
		}
		return null;
	}
}
