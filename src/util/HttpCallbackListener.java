package util;
/**
 * ʹ��Java �Ļص�����
 * onFinish()������ʾ���������ɹ���Ӧ���������ʱ����ã�
 * onError()��ʾ����������������ִ����ʱ����á�
 * ���������������в�����onFinish()�����еĲ��������ŷ��������ص����ݣ�
 * ��onError()�����еĲ�����¼�Ŵ������ϸ��Ϣ��
 * 
 */
public interface HttpCallbackListener {
	void onFinish(String response);
	void onError(Exception e);
}
