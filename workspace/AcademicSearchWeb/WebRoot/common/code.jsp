<%@ page contentType="image/png" %>
 <%@ page import="java.awt.*,java.awt.image.*,java.util.*,javax.imageio.*" %> 
 <% 
    response.setHeader("Pragma","No-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setDateHeader("Expires",0);
    
    int width=40;
    int height=20;
    
    //´´½¨»º´æÍ¼Ïñ
    BufferedImage image = new BufferedImage(width,height,Image.SCALE_DEFAULT);
    
    Graphics g = image.getGraphics();
    
    g.setColor(new Color(000,102,153));
    g.fillRect(0,0,width,height);
    g.setFont(new Font("Arial",Font.PLAIN,16));
    
    Random random = new Random();
    
    StringBuffer srand = new StringBuffer();
    for(int i=0;i<4;i++)
    {
      String ranNum =String.valueOf(random.nextInt(10));
      srand.append(ranNum);
    
      g.setColor(new Color(255,255,255));
     
      g.drawString(ranNum,10*i+1,16);
      
 
     }
     g.dispose();
     
     session.setAttribute("_CODE",srand.toString());
     ImageIO.write(image,"jpg",response.getOutputStream());
     out.clear();
	 out = pageContext.pushBody();
  %>