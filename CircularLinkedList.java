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
	  Node tempNode;
	  if (position == 0) 
	  {
	     tempNode = getNodeAt(size - 1);
	  }
	  else
	  {
         tempNode = getNodeAt(position - 1);
	  }
	  if (size == 2) 
	  {
		  tempNode.setLink(tempNode);
	  }
	  else 
	  {
         tempNode.setLink(tempNode.getLink().getLink());
	  }
      size--;
      if (size <= 0) head = null;
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