package huffman;

/**
 * @author Gavin Fennelly
 * @version 1 Created: 6/04/2016
 * @Last edited: 27/04/2016
 */

public class TreeNode implements Comparable<TreeNode>{

	private TreeNode left,right;
	private String nodeStage;
	private int frequency;

	public TreeNode(String nodeStage, int frequency, TreeNode left, TreeNode right) {
		this.nodeStage = nodeStage;
		this.frequency = frequency;
		this.left = left;
		this.right = right;
	}

	public boolean isLeaf() {
		return nodeStage.length() == 1;
	}

	public TreeNode left() {
		return left;
	}

	public TreeNode right() {
		return right;
	}

	public String getNodeStage() {
		return nodeStage;
	}

	public int getFrequency() {
		return frequency;
	}

	@Override
	public int compareTo(TreeNode tn) {
		if(frequency < tn.frequency)
			return -1;
		if(frequency > tn.frequency)
			return 1;
		return 0;
	}
}
