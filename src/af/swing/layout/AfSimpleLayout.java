package af.swing.layout;

import java.awt.*;

public abstract class AfSimpleLayout implements LayoutManager
{
	@Override
	public void addLayoutComponent(String name, Component comp)
	{
	}

	@Override
	public void removeLayoutComponent(Component comp)
	{
	}

	@Override
	public Dimension preferredLayoutSize(Container parent)
	{
		return null;
	}

	@Override
	public Dimension minimumLayoutSize(Container parent)
	{
		return null;
	}

//	@Override
//	public void layoutContainer(Container parent)
//	{		
//	}

}
