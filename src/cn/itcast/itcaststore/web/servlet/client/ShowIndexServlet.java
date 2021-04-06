package cn.itcast.itcaststore.web.servlet.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.itcast.itcaststore.domain.Notice;
import cn.itcast.itcaststore.service.NoticeService;
import cn.itcast.itcaststore.service.ProductService;
/**
 *	鍓嶅彴椤甸潰灞曠ず鐨剆ervlet 
 *	1銆佸睍绀烘渶鏂版坊鍔犳垨淇敼鐨勪竴鏉″叕鍛�
 *  2銆佸睍绀烘湰鍛ㄧ儹閿�鍟嗗搧
 */
public class ShowIndexServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//鏌ヨ鏈�杩戜竴鏉″叕鍛婏紝浼犻�掑埌index.jsp椤甸潰杩涜灞曠ず
		NoticeService nService = new NoticeService();
		Notice notice = nService.getRecentNotice();
		req.setAttribute("n", notice);
		
		//鏌ヨ鏈懆鐑攢鐨勪袱鏉″晢鍝侊紝浼犻�掑埌index.jsp椤甸潰杩涜灞曠ず
		ProductService pService = new ProductService();
		List<Object[]> pList =  pService.getWeekHotProduct();
		/*for(Object[] os:pList){
			for(Object o:os){
				System.out.println(o);
			}
			System.out.println("---------------------");
		}*/
		req.setAttribute("pList", pList);
		
		//请求转发到/client/index.jsp
		req.getRequestDispatcher("/client/index.jsp").forward(req, resp);
	}
}
