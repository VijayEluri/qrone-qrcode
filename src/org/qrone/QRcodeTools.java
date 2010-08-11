package org.qrone;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

/**
 * QR �R�[�h�摜�𐶐��p�c�[��<BR>
 * <BR>
 * com.swetake.util.Qrcode �𗘗p���Ď��ۂ� QR �R�[�h�摜�𐶐�����
 * �o�͂���ׂ̗��փ��C�u�����B
 * 
 * @author J.Tabuchi
 * @since 2005/8/16
 * @version 1.0
 * @link QrONE Technology : http://www.qrone.org/
 */
public class QRcodeTools {
	/**
	 * �G���[�������x�� L : �������@��V��
	 */
	public static final char ERROR_COLLECT_L = 'L';
	/**
	 * �G���[�������x�� M : �������@��P�T���@�i�W���j
	 */
	public static final char ERROR_COLLECT_M = 'M';
	/**
	 * �G���[�������x�� Q : �������@��Q�T��
	 */
	public static final char ERROR_COLLECT_Q = 'Q';
	/**
	 * �G���[�������x�� H : �������@��R�O��
	 */
	public static final char ERROR_COLLECT_H = 'H';
	
	/**
	 * �G���R�[�h�F�����̂�
	 */
	public static final char ENCODE_NUMBER              = 'N';
	/**
	 * �G���R�[�h�F�p�����̂�
	 */
	public static final char ENCODE_ALPHABET_AND_NUMBER = 'A';
	/**
	 * �G���R�[�h�F���̑��A8bit
	 */
	public static final char ENCODE_8_BIT               = 'B';
	
	/**
	 * �o�[�W���������w��
	 */
	public static final int  VERSION_AUTO = 0;

	/**
	 * �^����ꂽ�����񂩂� QR �R�[�h�摜�𐶐����w��t�@�C���ɏo�͂��܂��B
	 * @param str�@���͕�����
	 * @param errorcollect�@�G���[�������x��
	 * @param encode�@QR �摜�G���R�[�h�i�܂܂�镶����j
	 * @param version�@QR �o�[�W�����i�摜�T�C�Y�j
	 * @param zoom�@�g��{��
	 * @param format �t�@�C���t�H�[�}�b�g
	 * @param out �o�̓X�g���[��
	 * @throws IOException �o�̓G���[
	 */
	public static void streamQRcodeImage(String str, char errorcollect, 
			char encode,int version, int zoom, String format, OutputStream out)
	throws IOException{
		
		RenderedImage image = createQRcodeImage(
				str,errorcollect,encode,version,zoom);
		ImageIO.write(image,format,out);
	}
	
	/**
	 * �^����ꂽ�����񂩂� QR �R�[�h�摜�𐶐����w��t�@�C���ɏo�͂��܂��B
	 * @param str�@���͕�����
	 * @param version�@QR �o�[�W�����i�摜�T�C�Y�j
	 * @param zoom�@�g��{��
	 * @param file �o�̓t�@�C����
	 * @throws IOException �o�̓G���[
	 */
	public static void createQRcodeImageFile(String str, int version, int zoom,
			String file) throws IOException{
		RenderedImage image = createQRcodeImage(
				str.getBytes(),ERROR_COLLECT_M,ENCODE_8_BIT,version,zoom);
		
		File f = new File(file);
		String format = file.substring(file.lastIndexOf(".")+1);
		ImageIO.write(image,format,f);
	}
	
	/**
	 * �^����ꂽ�����񂩂� QR �R�[�h�摜�𐶐����w��t�@�C���ɏo�͂��܂��B
	 * @param str�@���͕�����
	 * @param version�@QR �o�[�W�����i�摜�T�C�Y�j
	 * @param zoom�@�g��{��
	 * @param file �o�̓t�@�C��
	 * @param format �o�̓t�@�C���̊g���q
	 * @throws IOException �o�̓G���[
	 */
	public static void createQRcodeImageFile(String str, int version, int zoom,
			File file, String format) throws IOException{
		RenderedImage image = createQRcodeImage(
				str.getBytes(),ERROR_COLLECT_M,ENCODE_8_BIT,version,zoom);
		ImageIO.write(image,format,file);
	}
	
	/**
	 * �^����ꂽ�����񂩂� QR �R�[�h�摜�𐶐����w��t�@�C���ɏo�͂��܂��B
	 * @param str�@���͕�����
	 * @param errorcollect�@�G���[�������x��
	 * @param encode�@QR �摜�G���R�[�h�i�܂܂�镶����j
	 * @param version�@QR �o�[�W�����i�摜�T�C�Y�j
	 * @param zoom�@�g��{��
	 * @param file �o�̓t�@�C��
	 * @param format �o�̓t�@�C���̊g���q
	 * @throws IOException �o�̓G���[
	 */
	public static void createQRcodeImageFile(String str,
			char errorcollect, char encode, int version, int zoom,
			File file, String format) throws IOException{
		RenderedImage image = createQRcodeImage(
				str.getBytes(),errorcollect,encode,version,zoom);
		ImageIO.write(image,format,file);
	}
	/**
	 * �^����ꂽ�����񂩂� QR �R�[�h�摜�𐶐����w��t�@�C���ɏo�͂��܂��B
	 * @param str�@���͕�����
	 * @param charset�@���͕�����̕����R�[�h
	 * @param errorcollect�@�G���[�������x��
	 * @param encode�@QR �摜�G���R�[�h�i�܂܂�镶����j
	 * @param version�@QR �o�[�W�����i�摜�T�C�Y�j
	 * @param zoom�@�g��{��
	 * @param file �o�̓t�@�C��
	 * @param format �o�̓t�@�C���̊g���q
	 * @throws IOException �o�̓G���[
	 */
	public static void createQRcodeImageFile(String str, String charset,
			char errorcollect, char encode, int version, int zoom,
			File file, String format) throws IOException{
		RenderedImage image = createQRcodeImage(
				str.getBytes(charset),errorcollect,encode,version,zoom);
		ImageIO.write(image,format,file);
	}

	/**
	 * �^����ꂽ�����񂩂� QR �R�[�h�摜�𐶐����܂�
	 * @param str�@���͕�����
	 * @param errorcollect�@�G���[�������x��
	 * @param encode�@QR �摜�G���R�[�h�i�܂܂�镶����j
	 * @param version�@QR �o�[�W�����i�摜�T�C�Y�j
	 * @param zoom�@�g��{��
	 * @return�@�o�͉摜
	 */
	public static RenderedImage createQRcodeImage(String str,
			char errorcollect, char encode, int version, int zoom){
		return createQRcodeImage(
				str.getBytes(),errorcollect,encode,version,zoom);
	}
	
	/**
	 * �^����ꂽ�����񂩂� QR �R�[�h�摜�𐶐����܂�
	 * @param str�@���͕�����
	 * @param charset�@���͕�����̕����R�[�h
	 * @param errorcollect�@�G���[�������x��
	 * @param encode�@QR �摜�G���R�[�h�i�܂܂�镶����j
	 * @param version�@QR �o�[�W�����i�摜�T�C�Y�j
	 * @param zoom�@�g��{��
	 * @return�@�o�͉摜
	 * @throws UnsupportedEncodingException �����R�[�h�s��
	 */
	public static RenderedImage createQRcodeImage(String str, String charset,
			char errorcollect, char encode, int version, int zoom)
	throws UnsupportedEncodingException{
		return createQRcodeImage(
				str.getBytes(charset),errorcollect,encode,version,zoom);
	}
	
	/**
	 * �^����ꂽ�o�C�g�񂩂� QR �R�[�h�摜�𐶐����܂�
	 * @param b�@���̓o�C�g��
	 * @param errorcollect�@�G���[�������x��
	 * @param encode�@QR �摜�G���R�[�h�i�܂܂�镶����j
	 * @param version�@QR �o�[�W�����i�摜�T�C�Y�j
	 * @param zoom�@�g��{��
	 * @return�@�o�͉摜
	 */
	public static RenderedImage createQRcodeImage(byte[] b,
			char errorcollect, char encode, int version, int zoom){
		Qrcode qrcode = new Qrcode();
		qrcode.setQrcodeEncodeMode(encode);
		qrcode.setQrcodeErrorCorrect(errorcollect);
		qrcode.setQrcodeVersion(version);
		boolean[][] r = qrcode.calQrcode(b);
		
		BufferedImage image = new BufferedImage(
				r.length*zoom,r.length*zoom,BufferedImage.TYPE_INT_RGB );
		
		WritableRaster raster = image.getRaster();
		int[] inputints = new int[r.length*r.length*3*zoom*zoom];
		for(int y=0;y<r.length;y++){
			for(int yc=0;yc<zoom;yc++){
				for(int x=0;x<r.length;x++){
					for(int xc=0;xc<zoom;xc++){
						int p = (((y*zoom)+yc)*r.length*zoom 
				          			+ (x*zoom)+xc)*3;
						if(r[x][y]){
							inputints[p++] = 0;
							inputints[p++] = 0;
							inputints[p++] = 0;
						}else{
							inputints[p++] = 255;
							inputints[p++] = 255;
							inputints[p++] = 255;
						}
					}
				}
			}
		}
		raster.setPixels(0,0,r.length*zoom,r.length*zoom,inputints);
		return image;
	}
	
	/**
	 * ����f��
	 * @param args
	 */
	public static void main(String[] args){
		if(args.length == 0){
			try { createQRcodeImageFile(
						"QRcodeTools by J.Tabuchi",7,2,"qrcode-tools.png");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		String str = args[0];
		char errorcollect = args[1].charAt(0);
		char encode = args[2].charAt(0);
		int version = Integer.parseInt(args[3]);
		int zoom = Integer.parseInt(args[4]);
		String format = args[5];
		try {
			streamQRcodeImage(str, errorcollect, encode, 
					version, zoom, format, System.out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
