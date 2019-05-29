package Stack;

public class linkNode {//节点类
    private  int key=0;//键
    private String value=null;//值
    private linkNode next=null;//下一个节点

    public  linkNode(int key,String value){
        this.key=key;
        this.value=value;
        this.next=null;
    }

    public  int getKey(){
        return key;
    }
    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public linkNode getNext() {
        return next;
    }

    public void setNext(linkNode next) {
        this.next = next;
    }
}
