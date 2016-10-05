package textfiles;

import java.io.*;
import java.util.HashMap;


public class Counter {


	public static void counter (String input)
	{


		HashMap <String, Integer> frequency = new HashMap <String, Integer>();
		HashMap <String, BinaryTree> trees = new HashMap <String, BinaryTree>();


		for (int i=0; i < input.length(); i++)
		{
			String c = Character.toString(input.charAt(i));
			if (frequency.containsKey(c))
			{
				frequency.put(c,frequency.get(c) + 1 );
			}
			else
			{
				frequency.put(c,1);
			}

		}


		for (String key: frequency.keySet())

		{
			trees.put(key , new BinaryTree (key));

		}


		while (frequency.size() > 1)
		{
			String minKey1 = "";
			String minKey2 = "";
			int minVal1 = 999;
			int minVal2 = 999;

			for  (String key : frequency.keySet())
			{
				int val  = frequency.get(key);

				if (val < minVal1 && minVal1 == 999)
				{
					minVal1 = val;
					minKey1 = key;
				}

				else if (val < minVal2)
				{
					minVal2 = val;
					minKey2 = key;
				}


			}
			frequency.remove(minKey1);
			frequency.remove(minKey2);
			frequency.put(minKey1 + minKey2, minVal1 + minVal2);
			System.out.println(frequency.keySet());


		}

	}
}

