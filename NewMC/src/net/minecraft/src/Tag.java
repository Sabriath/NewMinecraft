package net.minecraft.src;

import net.minecraft.api.ITag;

public class Tag
	implements ITag
{
	private ITag Tparent;
	private ITag Tfchild;
	private ITag Tlchild;
	private ITag Tpchild;
	private ITag Tnchild;
	private String Tkey;
	private String Tval;
	
	public Tag(ITag p, String k)
	{
		ITag t = p.GetL();
		
		if(t == null)
			p.SetL(this);
		else
			t.SetN(this);
		Tparent = p;
		Tfchild = null;
		Tlchild = null;
		Tpchild = t;
		Tnchild = null;
		Tkey = k;
	}

	@Override
	public ITag Find(String key)
	{
		String[] t = key.split(".");
		String n;
		ITag cur = this;
		ITag cand = null;
		int i = t.length;
		
		for(int j = 0; j < i; j++)
		{
			n = t[j];
			cand = cur.GetF();
			do
			{
				if(cand == null)
					return null;
				if(n == cand.GetK())
					break;
				cand = cand.GetN();
				continue;
			} while(true);
		}
		return cand;
	}

	@Override
	public ITag Add(String key)
	{
		String[] t = key.split(".");
		String n;
		ITag cur = this;
		ITag cand = null;
		int i = t.length;
		
		for(int j = 0; j < i; j++)
		{
			n = t[j];
			cand = cur.GetF();
			do
			{
				if(cand == null)
				{
					cand = new Tag(cur, n);
					break;
				}
				if(n == cand.GetK())
					break;
				cand = cand.GetN();
				continue;
			} while(true);
		}
		return cand;
	}

	@Override
	public ITag Set(String val)
	{
		Tval = val;
		return this;
	}
	
	@Override
	public String Get()
	{
		return Tval;
	}

	@Override
	public void SetM(ITag m)
	{
		Tparent = m;
	}

	@Override
	public void SetF(ITag f)
	{
		Tfchild = f;
	}

	@Override
	public void SetL(ITag l)
	{
		Tlchild = l;
	}

	@Override
	public void SetP(ITag p)
	{
		Tpchild = p;
	}

	@Override
	public void SetN(ITag n)
	{
		Tnchild = n;
	}

	@Override
	public ITag GetM()
	{
		return Tparent;
	}

	@Override
	public ITag GetF()
	{
		return Tfchild;
	}

	@Override
	public ITag GetL()
	{
		return Tlchild;
	}

	@Override
	public ITag GetP()
	{
		return Tpchild;
	}

	@Override
	public ITag GetN()
	{
		return Tnchild;
	}

	@Override
	public void SetK(String k)
	{
		Tkey = k;
	}

	@Override
	public String GetK()
	{
		return Tkey;
	}
}
