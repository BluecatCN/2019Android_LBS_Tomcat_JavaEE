package com.servlet.demo;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class FileUploadServlet
 */
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//鐟欙絽鍠卲ost鐠囬攱鐪伴惃鍕础閻拷
		//request.setCharacterEncoding("utf-8");
		
	/*	//閼惧嘲褰囨稉锟芥稉顏囩翻閸忋儲绁�
		ServletInputStream in = request.getInputStream();
		
		//鐠囪褰囧ù锟�
		String str = IOUtils.toString(in);
		
		System.out.println(str);*/
		
		
		//閼惧嘲褰囧銉ュ范缁鐤勬笟锟�
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//閸掓稑缂撶憴锝嗙�介崳銊ц鐎圭偘绶�
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		
		//fileUpload闁俺绻冪拠銉ヮ嚠鐠炩剝娼甸梽鎰煑閺傚洣娆㈤惃鍕亣鐏忥拷
		//鐠佸墽鐤嗚ぐ鎾存瀮娴犲墎娈戞径褍鐨稉锟�50KB
		//fileUpload.setFileSizeMax(1024*50);
		
		//鐠佸墽鐤嗘径姘嚋閺傚洣娆㈤惃鍕拷璇层亣鐏忓繋璐�300mb
		fileUpload.setSizeMax(1024*1024*300);
		
		try {
			//鐟欙絾鐎絩equest
			List<FileItem> fileItems = fileUpload.parseRequest(request);
			
			//闁秴宸籪ileItems閿涘矁顕伴崣鏍�冮崡鏇犳畱娣団剝浼�
			for (FileItem fileItem : fileItems) {
				
				//閸掋倖鏌囪ぐ鎾冲鐞涖劌宕熸い瑙勬Ц閸氾附妲告稉锟芥稉顏呮珮闁俺銆冮崡鏇€��
				if(fileItem.isFormField()){
					//閼惧嘲褰囩仦鐐达拷褍鎮�
					String fieldName = fileItem.getFieldName();
					
					//閼惧嘲褰囩仦鐐达拷褍锟斤拷
					String value = fileItem.getString("utf-8");
					
					System.out.println(fieldName+" = "+value);
				}else{
					//婵″倹鐏夐弰顖涙瀮娴犳儼銆冮崡鏇€��
					//閼惧嘲褰囬弬鍥︽閻ㄥ嫬銇囩亸锟�
					long size = fileItem.getSize();
					
					//閸掋倖鏌噑ize閺勵垰鎯佹稉锟�0
					if(size==0){
						continue;
					}
					
					//閼惧嘲褰囬弬鍥︽閻ㄥ嫮琚崹锟�
					String contentType = fileItem.getContentType();
					
					//閼惧嘲褰囬弬鍥︽閻ㄥ嫬鎮曠�涳拷
					String name = fileItem.getName();
					//閸掋倖鏌噉ame娑擃厽妲搁崥锕�瀵橀崥顐ｆ箒鐠侯垰绶炴穱鈩冧紖
					if(name.contains("\\")){
						//婵″倹鐏夐崠鍛儓閸掓瑦鍩呴崣鏍х摟缁楋缚瑕�
						name = name.substring(name.lastIndexOf("\\")+1);
					}
					
					//閻㈢喐鍨氭稉锟芥稉鐚哢ID閿涘奔缍旀稉鐑樻瀮娴犺泛鎮曢惃鍕缂傦拷
					String prefix = UUID.randomUUID().toString().replace("-", "");
					name = prefix+"_"+name;
					
					
					//閼惧嘲褰囩悰銊ュ礋妞ゅ湱娈戠仦鐐达拷褍鎮�
					String fieldName = fileItem.getFieldName();
					
					System.out.println("閺傚洣娆㈤惃鍕亣鐏忥拷: "+size);
					System.out.println("閺傚洣娆㈤惃鍕閸拷: "+contentType);
					System.out.println("閺傚洣娆㈤惃鍕倳鐎涳拷: "+name);
					System.out.println("鐞涖劌宕熸い绛篴me鐏炵偞锟窖冩倳: "+fieldName);
					
					//閼惧嘲褰嘢ervletContext鐎电钖�
					ServletContext context = this.getServletContext();
					//閼惧嘲褰囨い鍦窗閻ㄥ嫮婀＄�圭偠鐭惧锟�
					String path = context.getRealPath("/upload");
					
					//閸掋倖鏌囩捄顖氱窞閺勵垰鎯佺�涙ê婀�
					File file = new File(path);
					if(!file.exists()){
						//婵″倹鐏夋稉宥呯摠閸︺劏顕氱捄顖氱窞閿涘苯鍨崚娑樼紦娑擄拷娑擃亣鐭惧锟�
						file.mkdirs();
					}
					
					//鐏忓棙鏋冩禒璺哄晸閸忋儱鍩岀壕浣烘磸娑擄拷
					fileItem.write(new File(path+"/"+name));
					
				}
			}
			
			//闁插秴鐣鹃崥鎴濆煂娑擄拷娑擃亪銆夐棃锟�
			response.sendRedirect(request.getContextPath()+"/success.jsp");
			
		}catch(FileSizeLimitExceededException e){
			//娑擄拷娴ｅ棙宕熼懢宄板煂鐠囥儱绱撶敮闈╃礉閸掓瑨顕╅弰搴″礋娑擃亝鏋冩禒璺恒亣鐏忓繗绉存潻鍥閸掕翰锟斤拷
			//鐠佸墽鐤嗘稉锟芥稉顏堟晩鐠囶垱绉烽幁锟�
			request.setAttribute("msg", "閸楁洑閲滈弬鍥︽婢堆冪毈鐠囪渹绗夌憰浣界Т鏉╋拷50KB");
			//鏉烆剙褰傞崚鐧穘dex.jsp
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}catch(SizeLimitExceededException e){
			//娑擄拷娴ｅ棙宕熼懢宄板煂鐠囥儱绱撶敮闈╃礉閸掓瑨顕╅弰搴″礋娑擃亝鏋冩禒璺恒亣鐏忓繗绉存潻鍥閸掕翰锟斤拷
			//鐠佸墽鐤嗘稉锟芥稉顏堟晩鐠囶垱绉烽幁锟�
			request.setAttribute("msg", "閹碉拷閺堝鏋冩禒璺恒亣鐏忓繗顕稉宥堫洣鐡掑懓绻�300mb");
			//鏉烆剙褰傞崚鐧穘dex.jsp
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		
		catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
