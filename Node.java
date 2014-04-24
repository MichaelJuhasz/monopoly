class Node
{
   private Object data;
   private Node link;

   public Node(Object o, Node l)
   {
      link = l;
      data = o;
   }

   public Object getData()
   {
      return data;
   }

   public void setLink(Node newLink)
   {
      link = newLink;
   }
 
   public Node getLink()
   {
      return link;
   }
}