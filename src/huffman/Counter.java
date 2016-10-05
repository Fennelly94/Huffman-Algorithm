package huffman;

import java.io.*;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author Gavin Fennelly
 * @version 1 Created: 6/04/2016
 * @Last edited: 27/04/2016
 */

public class Counter {

	public void run(String input) {

		// Takes every letter in the string and counts the frequency of each letter
		HashMap<String, Integer> frequency = new HashMap<String, Integer>();

		// goes through each and every letter in the string
		for (int i = 0; i < input.length(); i++) {
			// String C is current letter we are on
			String c = Character.toString(input.charAt(i));
			//If the letter is already in counted for, increment it by 1
			if (frequency.containsKey(c)) {
				frequency.put(c, frequency.get(c) + 1);
				//Add the letter and set its value to 1 if doesn't already exist
			} else {
				frequency.put(c, 1);
			}
		}

		//Creates the priority queue called prQueue, this queue will guarantee smallest item
		PriorityQueue<TreeNode> prQueue = new PriorityQueue<>();

		// Making the leaf nodes by iterating through the frequency map using the keys
		Set<String> keys = frequency.keySet();
		for (String key : keys) {
			String nodeStage = key;
			//Frequency of the key
			Integer freq = frequency.get(key);
			//Left and right are the nodes which are empty at default (null)
			TreeNode node = new TreeNode(nodeStage, freq, null, null);

			//adds node to the priority																										
			prQueue.add(node);
		}

		// Creating the Huffman tree
		//Until the queue has only one node left, keep polling 2 nodes from the tree, group them
		//to a tree node and then add the node back to the tree.
		while (prQueue.size() > 1) {
			TreeNode node1 = prQueue.poll();
			TreeNode node2 = prQueue.poll();
			String totalNodeStage = node1.getNodeStage() + node2.getNodeStage();
			int totalFrequency = node1.getFrequency() + node2.getFrequency();
			TreeNode joins = new TreeNode(totalNodeStage, totalFrequency,
					node1, node2);
			prQueue.add(joins);
		}

		//stores the mapping of characters to corresponding binary code and 
		//prints them out to the console window.
		HashMap<Character, String> characterToBinary = new HashMap<>();
		TreeNode root = prQueue.poll();
		recordPath(characterToBinary, root, "");

		for (Character c : characterToBinary.keySet()) {
			System.out.println(c + " => " + characterToBinary.get(c));
		}

		output(input, characterToBinary);

	}

	public void recordPath(HashMap<Character, String> characterToBinary,
			TreeNode node, String path) {
		if (node.isLeaf()) {
			// if reached the bottom of the tree (a leaf node), get the
			// character value (nodeStage, eg. a) of the node, add this node as
			// a key to the characterToBinary map, the value is the path
			char character = node.getNodeStage().charAt(0);
			characterToBinary.put(character, path);
		} else {
			TreeNode left = node.left();
			TreeNode right = node.right();

			if (left != null) {
				recordPath(characterToBinary, left, path + "0");
			}
			if (right != null) {
				recordPath(characterToBinary, right, path + "1");
			}
		}
	}

	public void output(String text, HashMap<Character, String> binary) {
		FileWriter w = null;
		try {
			w = new FileWriter(new File("output.txt"));

			// map part
			for (Character c : binary.keySet()) {
				w.write(c); // key
				w.write('-');
				w.write(binary.get(c).toCharArray()); // value
				w.write(',');
			}

			w.write('/');

			// code part
			String binaryString = "";
			for (char c : text.toCharArray()) {
				binaryString += binary.get(c);
			}
			w.write(binaryString.toCharArray());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (w != null) {
				try {
					w.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
	}

	public void decompress() {
		BufferedReader br = null;
		try {

			//Sets up to read the output text
			br = new BufferedReader(new FileReader("output.txt"));
			//Reads the line of text
			String all = br.readLine();
			//Looks for the "/" to split up code
			String[] parts = all.split("[/]");	
			//Map part split
			String mapPart = parts[0];
			//Code part split
			String codePart = parts[1];
			//Looks for the "," to split
			String[] mapItems = mapPart.split("[,]");

			//Hash map for decoding the map from binary to character
			HashMap<String, Character> binaryToCharacter = new HashMap<>();
			for (String mapItem : mapItems) {
				//Looks for "-" to split up code
				String[] keyValuePair = mapItem.split("[-]");
				//Key part is split here
				String binary = keyValuePair[1];
				//Value part is split here
				String character = keyValuePair[0];
				binaryToCharacter.put(binary, character.charAt(0));
			}

			//Text is set to empty
			String decompressed = "";

			String partOfCode = "";
			while (codePart.length() > 0) {
				int size = 1;
				partOfCode = codePart.substring(0, size);

				// keep expanding until a match is found in the map
				while (!binaryToCharacter.containsKey(partOfCode)) {
					size++;
					partOfCode = codePart.substring(0, size);
				}

				decompressed += binaryToCharacter.get(partOfCode);
				codePart = codePart.substring(size, codePart.length()); 




			}

			System.out.println("Decompressed text: " + decompressed);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
