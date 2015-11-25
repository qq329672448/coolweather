package util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
	/**
	 * ���ǿͻ��������������һ��HTTP ���󣬷������յ�����֮��᷵��һЩ���ݸ��ͻ��ˣ�
	 * Ȼ��ͻ����ٶ���Щ���ݽ��н����ʹ���Ϳ�����
	 * ʹ�õ���WebView �ؼ�����ʵҲ����������ٶȵķ�����������һ��HTTP ����
	 * ���ŷ�����������������Ҫ���ʵ��ǰٶȵ���ҳ��
	 * ���ǻ�Ѹ���ҳ��HTML ������з��أ�Ȼ��WebView 
	 * �ٵ����ֻ���������ں˶Է��ص�HTML ������н��������ս�ҳ��չʾ������
	 * 
	 * 
	 * 
	 * ͨ����������Ƕ�Ӧ�ý���Щͨ�õ����������ȡ��һ�����������
	 * ���ṩһ����̬����������Ҫ�������������ʱ��ֻ��򵥵ص���һ�������������
	 * 
	 * ��������ͨ���������ں�ʱ��������sendHttpRequest()�������ڲ���û�п����̣߳�
	 * �������п��ܵ����ڵ���sendHttpRequest()������ʱ��ʹ�����̱߳�����ס��
	 * 
	 * ���������sendHttpRequest()�����п�����һ���߳�������HTTP ����
	 * ��ô��������Ӧ���������޷����з��صģ����еĺ�ʱ�߼�����
	 * �����߳�����еģ�sendHttpRequest()�������ڷ����������ü���Ӧ��ʱ���ִ�н����ˣ�
	 * ��ȻҲ���޷�������Ӧ�������ˡ�
	 * 
	 * sendHttpRequest()���������һ��HttpCallbackListener ������
	 * ���ڷ������ڲ�������һ�����̣߳�Ȼ�������߳���ȥִ�о�������������
	 * ע�����߳������޷�ͨ��return ������������ݵģ�
	 * ����������ǽ���������Ӧ�����ݴ�����HttpCallbackListener ��onFinish()�����У�
	 * ����������쳣�ͽ��쳣ԭ���뵽onError()������
	 * 
	 * HttpUtil ����ʹ�õ���HttpCallbackListener �ӿ����ص����񷵻صĽ��
	 */
	public static void sendHttpRequest(final String address,final HttpCallbackListener listener) {
		//�����߳�������������
		new Thread(new Runnable(){
			@Override
			public void run() {
				HttpURLConnection connection =null;
				try{
					//��ȡHttpURLConnectionʵ��
					URL url= new URL(address);
					connection =(HttpURLConnection) url.openConnection();
					//GET ��ʾϣ���ӷ����������ȡ���ݣ���POST ��
					//��ʾϣ���ύ���ݸ�������
					connection.setRequestMethod("GET");
					//�������ӳ�ʱms
					connection.setConnectTimeout(8000);
					//���ö�ȡ��ʱms
					connection.setReadTimeout(8000);
					//����getInputStream()�����Ϳ��Ի�ȡ�����������ص�������
					InputStream in =connection.getInputStream();
					//�Ի�ȡ���ļ������ж�ȡ
					BufferedReader reader =new BufferedReader(new InputStreamReader(in));
					StringBuilder response =new StringBuilder();
					String line;
					while((line=reader.readLine())!=null){
						response.append(line);
					}
					if(listener!=null){
						//�ص�onFinish()����
						listener.onFinish(response.toString());
					}
				}catch (Exception e) {
					// TODO: handle exception
				if(listener!=null){
						//�ص�onError()����
					listener.onError(e);
					}
				}finally{
					if(connection!=null){
						//����disconnect()���������HTTP ���ӹر�
						connection.disconnect();
					}
				}
			}
		}).start();
	}
}
