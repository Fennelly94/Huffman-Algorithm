package textfiles;
import java.util.ArrayList;
import java.util.List;

public class BinaryTree {

	private ArrayList <String> tree = new ArrayList <String> ();
	ArrayList<String> left = null;
	ArrayList<String> right = null;

	int level = 0;


	BinaryTree (String root)
	{
		tree.add(root);
	}

	public BinaryTree merge (BinaryTree  other)
	{
		return null;
	}



	public List<String> getNodesOnLevel (int level) // 2 to the power of level - 1
	{



		int start = (int) Math.pow(2, level) - 1 ; //(int) Makes it into to the power of

		int end = (int) Math.pow (2 , level +1 ) -1 ;

		if (start < tree.size ())
		{
			return tree.subList (start , end );
		}
		else
		{
			int size = end - start;
			int i  = 0;
			ArrayList<String> additionalTree = new ArrayList <String>();

			for (i = 0; i<size; i++)
			{
				additionalTree.add(null);
			}
			return additionalTree;
		}
	}

}

