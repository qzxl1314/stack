package Stack;


public class linkStack  {//实现栈的各个接口方法并初始化栈
    private  linkNode top;//栈顶指针
    private int length=0;//栈长度
    public linkStack(){
        top=null;
        length=0;
    }
    public linkNode Pop() {//实现出栈
       synchronized (this) {//同步加锁，有线程运行这个方法时会对这个方法加锁
            //System.out.println("本次出栈得编号为"+top.getKey()+"内容为："+top.getValue());
           if (top==null){
               System.out.println("这个栈是空栈！");
               return top;
           }else {
               top = top.getNext();
               length--;
               return top;
           }
        }
    }
    public void Push(linkNode newStack) {//实现入栈
        synchronized (this) {
            if (newStack != null) {
                if (top == null) {
                    top = newStack;
                    length++;
                }else {
                    newStack.setNext(top);
                    newStack.setKey(top.getKey() + 1);
                    top = newStack;
                    length++;
                }
            }
        }
    }
    public void Print() {//实现打印栈
        linkNode currentNode = top;
        if (currentNode != null) {
            System.out.println("===========================================================");
            while (currentNode != null) {
                System.out.println("key=" + currentNode.getKey() + "，value=" + currentNode.getValue());
                currentNode = currentNode.getNext();
            }
            System.out.println("===========================================================");
        } else {
            System.out.println("这个栈是空栈！");
        }
    }
    public int Length(){//实现获取栈长度

        synchronized (this) {
            return length;
        }
    }
    public  void Clear(){//实现清空栈
        top=null;
        length=0;
    }
    public  void Find(String value) {
        synchronized (this) {
            if (top==null)
            {
                System.out.println("这个栈是空栈！");
            }else {
                linkNode currentNode = top;
                while (currentNode != null) {
                    if (currentNode.getValue().equals(value)) {
                        //System.out.println("节点指针为：" + currentNode.getKey());
                        break;
                    }
                    currentNode = currentNode.getNext();
                }
            }
        }
    }

}
