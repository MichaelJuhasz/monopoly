/** 
*  Very similar to the LinkedQueue referenced in class. 
*
**/
class CircularLinkedList
{
   private int size;
   private Node head;

   public CircularLinkedList()
   {
      head = null;
      size = 0;
   }  

   public void add(Object data)
   {
      if (size == 0)
      {
         head = new Node(data, null);
         head.setLink(head);
      }
      else 
      {
         Node currentNode = getNodeAt(size - 1);
         Node newNode = new Node(data, head);
         currentNode.setLink(newNode);
      }
      size++;
   }

   public Node getNodeAt(int nodePos)
   {
      if(nodePos >= size || nodePos < 0) return null;

      Node temp = head;
      for (int i = 0; i < nodePos; i++)
      {
         temp = temp.getLink();
      }

      return temp;
   }

   public void removeAt(int position)
   {
      Node tempNode = getNodeAt(position - 1);
      tempNode.setLink(tempNode.getLink().getLink());
      size--;
   }

   public void remove(Node n)
   {
      for (int i = 0; i < size; i++)
      {
         if (getNodeAt(i) == n) removeAt(i);
      }
   }

   public int getSize()
   {
      return size;
   }

   public Node getNext(Node n)
   {
      return n.getLink();
   }
}