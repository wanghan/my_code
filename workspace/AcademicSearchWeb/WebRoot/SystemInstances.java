/**
 * ����һЩ���õ�ȫ�ֱ���..
 */
package sys;

/**
 * @author yuwei
 *
 */
public interface SystemInstances {
	
	public static int POOL_SIZE=1000;	//�̳߳ش�С
	
	//1��ͼ�񲿷֣�
	public static int LireInsertServPort=7101;	        //lire�� insert����Ķ˿�
	public static int HQUserInsertServPort=7101;	    //�ܿض˼����û�insert����Ķ˿�
	public static int LireQueryServPort=7102;	        //lire�� query����Ķ˿�
	public static int HQUserQueryServPort=7102;    //�ܿض˼����û�query����Ķ˿�
	public static int LireClusterServPort = 7103;    //lire�� cluster����Ķ˿�
	public static int HQUserClusterServPort=7103;	//�ܿض˼����û�cluster����Ķ˿�
	public static int LireClassificationServPort = 7104;  //lire��classification����˿�
	public static int HQUserClassificationServPort=7104;	//�ܿض˼����û�classification����Ķ˿�
	public static int DEICServPort=7105;					//DEIC����������ͼ����Ϣ�޸Ķ˿�
	//2���ı����֣�
	public static int LuceneInserServPort=7201;       //Lucene�� insert����Ķ˿�
	public static int HQLuceneInsertServPort=7201;   //�ܿض˼����û�insert����Ķ˿�
	public static int LuceneQueryServPort=7202;      //lucene�� query����Ķ˿� 
	public static int HQLuceneQueryServPort=7202;  //�ܿض˼����û�query����Ķ˿�

	//3����Ƶ���֣�
	public static int VideoInserServPort=7301;       //��Ƶ�������� insert����Ķ˿�
	public static int HQVideoInsertServPort=7301;    //�ܿض˼����û�insert����Ķ˿�
	public static int VideoQueryServPort=7302;      //��Ƶ�������� query����Ķ˿� 
	public static int HQVideoQueryServPort=7302;   //�ܿض˼����û�query����Ķ˿�
	public static int VideoReceiveServPort=7303;    //��Ƶ�������� receive����Ķ˿�
	public static int HQVideoReceiveSerPort=7303;  //�ܿض˼����û�receive����Ķ˿�

	//4����Ƶ���֣�
	public static int AudioInsertSerPort=7401;           //audio��insert����Ķ˿�
	public static int HQAudioInsertServPort=7401;    //HQ�˼����û�insert����Ķ˿�
	public static int AudioQuerySerPort=7402;           //audio��query����Ķ˿�
	public static int HQAudioQueryServPort=7402;   //HQ�˼����û�query����Ķ˿�

	//5�����¹��ܶ˿ڣ�
	public static int XmlUpdateServPort=7501;   //�ܿض˼����û�modify����Ķ˿�
	public static int HQTypeEvoSerPort=7502;   //�ܿض˼����û�type�汾�ݱ�Ķ˿�

	//6��AQL��ѯ�˿�
	public static int AqlQuerySerPort=7601;  		//�ܿض˼���aql��ѯ�˿�

	//7���ļ�����˿ڣ�
	public static int IMAGE_FILE_SERV_PORT=7701;  //�ܿض˺͵ײ�image�ļ�����˿�
	public static int TEXT_FILE_SERV_PORT=7702;    //�ܿض˺͵ײ�text�ļ�����˿�
	public static int VIDEO_FILE_SERV_PORT=7703;   //�ܿض˺͵ײ�video�ļ�����˿� 
	public static int AUDIO_FILE_SERV_PORT=7704;  //�ܿض˺͵ײ�audio�ļ�����˿�
	public static int DATA_SERV_PORT=7705;              //���ݴ��ݶ˿�
	public static int FILE_TRNS_PORT=7706;              //�ļ����ն˿�

	//8��������������ʵ�����µ�ip��
	public static String HQ_IP="192.168.16.209";	    			//��ʵ�ܿط�����ip
	public static String AQL_IP="192.168.13.20";	    			//��ʵAQL������ip
	public static String LIRE_IP="192.168.13.15";	    			//��ʵͼ������� ip
	public static String LUCENE_IP="192.168.13.15";  			//��ʵ�ı�������ip
	public static String VIDEO_IP="192.168.13.15";          //��ʵ��Ƶ������ip
	public static String AUDIO_IP="192.168.13.15";          //��ʵ��Ƶ������ip
	
	public static String DEIC_IP="192.168.13.240";			//��ʵͼ��汾�ݱ������
	
	public static int BUFFERSIZE=8192;			        //�ֽ�buffer��С
	public static String HQ_USER_FILE_CACHE_DIR=".\\temphq_user\\";  //HQ����ʱ�ļ��ı���λ��
	public static String HQ_LIRE_FILE_CACHE_DIR=".\\temphq_lire\\";  //HQ����ʱ�ļ��ı���λ��
	public static String LIRE_FILE_CACHE_DIR=".\\templire\\";        //LIRE����ʱ�ļ��ı���λ��
	
	public static String LUCENE_FILE_CACHE_DIR=".\\temphq_lucene\\";  //LUCENE����ʱ�ļ��ı���λ��
	public static String USER_FILE_CACHE_DIR=".\\tempuser\\";         //�û������ϵ���ʱ�ļ��ı���λ��
	
	public static String HQ_VIDEO_FILE_CACHE_DIR=".\\temphq_video\\";  //HQ����ʱ������Ƶ�ļ���λ��
	public static String VIDEO_FILE_CACH_DIR=".\\tempvideo\\";        //��Ƶ����������ʱ�ļ�����λ��
	
	public static String HQ_AUDIO_FILE_CACHE_DIR=".\\temphq_audio\\";  //HQ����ʱ������Ƶ��λ��
	public static String AUDIO_FILE_CACH_DIR=".\\tempaudio\\";        //��Ƶ����������ʱ�ļ�����λ��
	public static long SENDING_DELAY=5;
	
	public static String NULL_FILE_NAME="NOTHING";
	
	//query type
	public static int QUERY_NORMAL=1;
	public static int QUERY_BY_LIREID=2;
	public static int QUERY_IMAGE_FEATURE=3;
}
