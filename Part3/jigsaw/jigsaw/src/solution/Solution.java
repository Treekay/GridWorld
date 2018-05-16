package solution;

import jigsaw.Jigsaw;
import jigsaw.JigsawNode;
import java.util.List;
import java.util.ArrayList;
import java.util.Vector;

/**
 * 在此类中填充算法，完成重拼图游戏（N-数码问题）
 */
public class Solution extends Jigsaw {
    private int searchedNodesNum;
    // 已访问节点数：用以记录所有访问过的节点的数量
    
    private Vector<JigsawNode> openList;
    // 用以保存已发现但未访问的节点
    private Vector<JigsawNode> closeList;
    // 用以保存已发现的节点

	/**
	 * 拼图构造函数
	 */
	public Solution() {
		super();
	}

	/**
	 * 拼图构造函数
	 * @param bNode - 初始状态节点
	 * @param eNode - 目标状态节点
	 */
	public Solution(JigsawNode bNode, JigsawNode eNode) {
		super(bNode, eNode);
	}

	/**
	 *（实验一）广度优先搜索算法，求指定5*5拼图（24-数码问题）的最优解
     * 填充此函数，可在Solution类中添加其他函数，属性
	 * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
	 * @return 搜索成功时为true,失败为false
	 */
	public boolean BFSearch(JigsawNode bNode, JigsawNode eNode) {
		this.closeList = new Vector<JigsawNode>();
        this.openList = new Vector<JigsawNode>();
        this.searchedNodesNum = 0;

        super.setBeginJNode(bNode);
        super.setEndJNode(eNode);
        this.currentJNode = new JigsawNode(bNode);

        this.openList.add(this.beginJNode);
        
        while (this.openList.size() > 0) {
            super.currentJNode = this.openList.elementAt(0);
            if (super.currentJNode.equals(endJNode)) {
                super.getPath();
                break;
            }
            this.closeList.addElement(super.currentJNode);
            this.openList.removeElementAt(0);
            this.searchedNodesNum++;

            final int DIRS = 4;

            Vector<JigsawNode> nextNodes = new Vector<JigsawNode>();
            for (int i = 0; i < DIRS; i++) {
                JigsawNode tempJNode = new JigsawNode(super.currentJNode);
                if (tempJNode.move(i) && !this.closeList.contains(tempJNode)) {
                    nextNodes.addElement(tempJNode);
                }
            }

            for (int i = 0; i < nextNodes.size(); i++) {
                openList.addElement(nextNodes.get(i));
            }
        }
        System.out.println("Jigsaw BFS Search Result:");
        System.out.println("Begin state:" + bNode.toString());
        System.out.println("End state:" + eNode.toString());
        //System.out.println("Solution Path: " + super.getSolutionPath());
        System.out.println("Total number of searched nodes:" + this.searchedNodesNum);
        System.out.println("Depth of the current node is:" + super.currentJNode.getNodeDepth());
        return this.isCompleted();
	}

	/**
	 * 估价函数f(n)用来估计节点n的重要性，表示为：从起始节点，经过节点n，到达目标节点的代价。f(n)越小
	 * 表示节点n越优良，应该优先访问它的邻接节点。可参考的估价方法有：
	 * 1） 所有 放错位的数码 个数
	 * 2） 所有 放错位的数码与其正确位置的距离 之和
	 * 3） 后续节点不正确的数码个数
	 * 4） .....
	 */
	public void estimateValue(JigsawNode jNode) {
		// f(n) =  a * f1(n) + b * f2(n) + c * f3(n)
		jNode.setEstimatedValue((int)(0*allWrongNum(jNode) + 1.5*allWrongLength(jNode) + 1*afterWrongNum(jNode)));
	}

	/**
	 * (1)
	 * estimate the sum of all the number in wrong location
	 */
	private int allWrongNum(JigsawNode jNode) {
		int s = 0; // the num of the num in the wrong location
		int dimension = JigsawNode.getDimension();
		for (int index = 1; index < dimension * dimension; index++) {
			if (jNode.getNodesState()[index] != index && jNode.getNodesState()[index] != 0) {
				// count the num in the wrong location except the block one
				s++;
			}
		}
		return s;
	}

	/**
	 * (2)
	 * estimate the sum of the length from the wrong location to correct location
	 * of all the number in wrong location
	 */
	private int allWrongLength(JigsawNode jNode) {
		int s = 0; // the sum length of the num in the wrong location
		int dimension = JigsawNode.getDimension();
		for (int index = 1; index <= dimension * dimension; index++) {
			if (jNode.getNodesState()[index] != index && jNode.getNodesState()[index] != 0) {
				// calculate the sum length of the num in the wrong location from the wrong location to the correct locaiton
				int nowRow = (jNode.getNodesState()[index] - 1) / dimension;
				int nowCol = (jNode.getNodesState()[index] - 1) % dimension;
				int goalRow = (index - 1) / dimension;
				int goalCol = (index - 1) % dimension;
				s += Math.abs(nowRow - goalRow) + Math.abs(nowCol - goalCol);
			}
		}
		return s;
	}


	/**
	 * (3)
	 *（Demo+实验二）计算并修改状态节点jNode的代价估计值:f(n)
	 * 如 f(n) = s(n). s(n)代表后续节点不正确的数码个数
     * 此函数会改变该节点的estimatedValue属性值
     * 修改此函数，可在Solution类中添加其他函数，属性
	 * @param jNode - 要计算代价估计值的节点
	 */
	private int afterWrongNum(JigsawNode jNode) {
		int s = 0; // 后续节点不正确的数码个数
		int dimension = JigsawNode.getDimension();
		for (int index = 1; index < dimension * dimension; index++) {
			if (jNode.getNodesState()[index] + 1 != jNode.getNodesState()[index + 1]) {
				s++;
			}
		}
		return s;
	}

}
