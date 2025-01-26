package segmentTree;

public class SegmentTreeNode {
    private int data;
    private SegmentTreeNode leftChild;
    private SegmentTreeNode rightChild;

    public SegmentTreeNode(int data, SegmentTreeNode leftChild, SegmentTreeNode rightChild) {
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public SegmentTreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(SegmentTreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public SegmentTreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(SegmentTreeNode rightChild) {
        this.rightChild = rightChild;
    }
}
