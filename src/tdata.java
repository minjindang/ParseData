import java.io.*;
import java.util.Date;

public class tdata {
	public static void main(String[] args) {
		try {
			File fin = new File("C:\\temp.csv");
			readFile_done1(fin);
			readFile_done2(fin);
			readFile_done3(fin);
			errorlog(fin);			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void errorlog(File fin) throws IOException {		
		BufferedReader br_log = new BufferedReader(new FileReader(fin));
		File fout = new File("C:\\errorlog.txt");
		FileOutputStream fos = new FileOutputStream(fout);
		BufferedWriter bw_log = new BufferedWriter(new OutputStreamWriter(fos));
		String line = null;
		while ((line = br_log.readLine()) != null) {
			String[] names = line.split(",");
			if((!names[5].contentEquals("�B�z����")) && (!names[6].contentEquals("�B�z����")) &&
			   (!names[7].contentEquals("�B�z����")) && (!names[8].contentEquals("�B�z����"))	
			   ){
				bw_log.write("���פJ�����D�s�� : "+names[0]);
				bw_log.newLine();								
			}	
		}
		br_log.close();
		bw_log.close();
	}

	private static void readFile_done1(File fin) throws IOException {
		Date date = new Date();
		int year = date.getYear()-11;
		
		BufferedReader done1_br = new BufferedReader(new FileReader(fin));
		String line = null;
		File fout = new File("C:\\data_done1.txt");
		FileOutputStream fos = new FileOutputStream(fout);
		BufferedWriter done1_bw = new BufferedWriter(new OutputStreamWriter(fos));
		
		while ((line = done1_br.readLine()) != null) {
			String[] names = line.split(",");
			String yeardate = date.getYear()-11+names[1].substring(4, 8);
			String codName = names[2].substring(2, names[2].length());
			/*"������=���|�˹�p"*/
			if(codName.contentEquals("������")){
				names[2] = names[2].substring(0,2)+"���|�˹�p";
			}			
			/*"�ްV��=�ޯ�V�m��"*/
			if(codName.contentEquals("�ްV��")){
				names[2] = names[2].substring(0,2)+"�ޯ�V�m��";
			}			
			/*"�a��=�a��k�|�˹�p"*/
			if(codName.contentEquals("�a��")){
				names[2] = names[2].substring(0,2)+"�a��k�|�˹�p";
			}			
			/*"�G��=�ĤG�ʺ�"*/
			if(codName.contentEquals("�G��")){
				names[2] = names[2].substring(0,2)+"�ĤG�ʺ�";
			}
			/*"���[=�֦~�[�@��"*/
			if(codName.contentEquals("���[")){
				names[2] = names[2].substring(0,2)+"�֦~�[�@��";
			}
			/*"�ֻ�=�֦~���|�|"*/
			if(codName.contentEquals("�ֻ�")){
				names[2] = names[2].substring(0,2)+"�֦~���|�|";
			}
			/*"�k��=�k�l�ʺ�"*/
			if(codName.contentEquals("�k��")){
				names[2] = names[2].substring(0,2)+"�k�l�ʺ�";
			}
			/*"�̰���=�̰��k�|�˹�p"*/
			if(names[2].contentEquals("�̰���")){
				names[2] = "�̰��k�|�˹�p";
			}
			/*"�O����=�����k�|�˹�p"*/
			if(names[2].contentEquals("�O����")){
				names[2] = "�����k�|�˹�p";
			}
			/*"�q�k�x�ǰ|=�q�k�x�V�m��"*/
			if(names[2].contentEquals("�q�k�x�ǰ|")){
				names[2] = "�q�k�x�V�m��";
			}
			/*"�ǫO�Χ�O=�]�Ϊk�H�Ǹo�Q�`�H�O�@��|�ΰ]�Ϊk�H�O�W��ͫO�@�|"*/
			if(names[2].contentEquals("�ǫO�Χ�O")){
				names[2] = "�]�Ϊk�H�Ǹo�Q�`�H�O�@��|�ΰ]�Ϊk�H�O�W��ͫO�@�|";
			}
			/*"���q=�Ш|����ڤΨ⩤�Ш|�q"*/
			if(names[2].contentEquals("���q")){
				names[2] = "�Ш|����ڤΨ⩤�Ш|�q";
			}
			/*"��T�B=�k�ȳ�"*/
			if(names[2].contentEquals("��T�B") || names[2].contentEquals("���ѳB") || 
			   names[2].contentEquals("�H�ƳB") || names[2].contentEquals("�k��q") || 
			   names[2].contentEquals("��X�W���q")){
				names[2] = "�k�ȳ�";
			}
			
			if(names[5].contentEquals("�B�z����") || names[6].contentEquals("�B�z����") ||
			   names[7].contentEquals("�B�z����") || names[8].contentEquals("�B�z����") ||
			   names[9].contentEquals("�B�z����") || names[10].contentEquals("�B�z����")){
					 done1_bw.write("INSERT INTO red (REDYY,REDDAT,REDTIME,REDKIND,REDNO,REDUSRID," +
			         "REDNAM,REDCTY,REDDEPT,REDTEL,REDTEL1,REDCTYNAM,REDFAST,REDPRJ," +
			         "REDMAN,REDYN,REDEDAT,REDMEMO,FLOW,REDUSRCTY,FLAG1,REDCLASS,"+
			         "REDSOURCE,REDETIME,REDELAPSE) VALUES (" +
 					 "'"+year+"','"+yeardate+"','17:30',"+
					 "'010','TCEA"+names[0]+"','KD1582',"+
					 "'�c�A��',(SELECT MIN(CODD) FROM COD WHERE CODM='00' AND CODNAM LIKE '%"+names[2]+"%'),'',"+
					 "'','','"+names[3]+"',"+
					 "'4','"+yeardate+"','KD1582',"+
					 "'7','"+names[6]+"','"+names[4]+" NO."+names[0]+"',"+
					 "'KD1582','KDAA','Y',"+
					 "'010','02',"+
					 "'17:30','0')");
					 done1_bw.newLine();
			} else {
				System.out.println("���פJ�����D�s�� : "+names[0]);
			}
		}
		done1_br.close();
		done1_bw.close();
	}

	private static void readFile_done2(File fin) throws IOException {
		Date date = new Date();
		int year = date.getYear()-11;
		
		BufferedReader done2_br = new BufferedReader(new FileReader(fin));
		String line = null;
		File fout = new File("C:\\data_done2.txt");
		FileOutputStream fos = new FileOutputStream(fout);
		BufferedWriter done2_bw = new BufferedWriter(new OutputStreamWriter(fos));
		
		while ((line = done2_br.readLine()) != null) {
			String[] names = line.split(",");
			String yeardate = date.getYear()-11+names[1].substring(4, 8);
			if(names[5].contentEquals("�B�z����") || names[6].contentEquals("�B�z����") ||
			   names[7].contentEquals("�B�z����") || names[8].contentEquals("�B�z����") ||
			   names[9].contentEquals("�B�z����") || names[10].contentEquals("�B�z����")){
					done2_bw.write("insert into RD (" +
					"RDYY,RDNO,RDSEQ," +
					"RDCTY,RDNAM,RDDAT," +
					"RDTIME,RDMEMO,RDFLOW," +
					"RDYN,RDUSRID" +
					")VALUES" +
					"('"+year+"','TCEA"+names[0]+"','0'," +
					"'KDAA','�c�A��','"+yeardate+"'," +
					"'17:32','���ש�"+yeardate+"�ѿc�A�ߨ��z�C','KD1582'," +
					"'6','KD1582');");
					done2_bw.newLine();
			}
		}
		done2_br.close();
		done2_bw.close();
	}
	
	private static void readFile_done3(File fin) throws IOException {
		Date date = new Date();
		int year = date.getYear()-11;
		
		BufferedReader done3_br = new BufferedReader(new FileReader(fin));
		String line = null;
		File fout = new File("C:\\data_done3.txt");
		FileOutputStream fos = new FileOutputStream(fout);
		BufferedWriter done3_bw = new BufferedWriter(new OutputStreamWriter(fos));
		
		while ((line = done3_br.readLine()) != null) {
			String[] names = line.split(",");
			String yeardate = date.getYear()-11+names[1].substring(4, 8);
			if(names[5].contentEquals("�B�z����") || names[6].contentEquals("�B�z����") ||
			   names[7].contentEquals("�B�z����") || names[8].contentEquals("�B�z����") ||
			   names[9].contentEquals("�B�z����") || names[10].contentEquals("�B�z����")){
					done3_bw.write("insert into RD (" +
					"RDYY,RDNO,RDSEQ," +
					"RDCTY,RDNAM,RDDAT," +
					"RDTIME,RDMEMO,RDFLOW," +
					"RDYN,RDUSRID" +
					")VALUES" +
					"('"+year+"','TCEA"+names[0]+"','1'," +
					"'KDAA','�c�A��','"+yeardate+"'," +
					"'17:40','�w�B�z�C','KD1582'," +
					"'7','KD1582');");
					done3_bw.newLine();	
			}
		}
		done3_br.close();
		done3_bw.close();
	}
	
	private static void readFile_process1(File fin) throws IOException {
		Date date = new Date();
		int year = date.getYear()-11;
		
		BufferedReader process1_br = new BufferedReader(new FileReader(fin));
		String line = null;
		File fout = new File("C:\\data_process1.txt");
		FileOutputStream fos = new FileOutputStream(fout);
		BufferedWriter process1_bw = new BufferedWriter(new OutputStreamWriter(fos));
		
		while ((line = process1_br.readLine()) != null) {
			String[] names = line.split(",");
			String yeardate = date.getYear()-11+names[1].substring(4, 8);
			String codName = names[2].substring(2, names[2].length());
			/*"������=���|�˹�p"*/
			if(codName.contentEquals("������")){
				names[2] = names[2].substring(0,2)+"���|�˹�p";
			}			
			/*"�ްV��=�ޯ�V�m��"*/
			if(codName.contentEquals("�ްV��")){
				names[2] = names[2].substring(0,2)+"�ޯ�V�m��";
			}			
			/*"�a��=�a��k�|�˹�p"*/
			if(codName.contentEquals("�a��")){
				names[2] = names[2].substring(0,2)+"�a��k�|�˹�p";
			}			
			/*"�G��=�ĤG�ʺ�"*/
			if(codName.contentEquals("�G��")){
				names[2] = names[2].substring(0,2)+"�ĤG�ʺ�";
			}
			/*"���[=�֦~�[�@��"*/
			if(codName.contentEquals("���[")){
				names[2] = names[2].substring(0,2)+"�֦~�[�@��";
			}
			/*"�ֻ�=�֦~���|�|"*/
			if(codName.contentEquals("�ֻ�")){
				names[2] = names[2].substring(0,2)+"�֦~���|�|";
			}
			/*"�k��=�k�l�ʺ�"*/
			if(codName.contentEquals("�k��")){
				names[2] = names[2].substring(0,2)+"�k�l�ʺ�";
			}
			/*"�̰���=�̰��k�|�˹�p"*/
			if(names[2].contentEquals("�̰���")){
				names[2] = "�̰��k�|�˹�p";
			}
			/*"�O����=�����k�|�˹�p"*/
			if(names[2].contentEquals("�O����")){
				names[2] = "�����k�|�˹�p";
			}
			/*"�q�k�x�ǰ|=�q�k�x�V�m��"*/
			if(names[2].contentEquals("�q�k�x�ǰ|")){
				names[2] = "�q�k�x�V�m��";
			}
			/*"�ǫO�Χ�O=�]�Ϊk�H�Ǹo�Q�`�H�O�@��|�ΰ]�Ϊk�H�O�W��ͫO�@�|"*/
			if(names[2].contentEquals("�ǫO�Χ�O")){
				names[2] = "�]�Ϊk�H�Ǹo�Q�`�H�O�@��|�ΰ]�Ϊk�H�O�W��ͫO�@�|";
			}
			/*"���q=�Ш|����ڤΨ⩤�Ш|�q"*/
			if(names[2].contentEquals("���q")){
				names[2] = "�Ш|����ڤΨ⩤�Ш|�q";
			}
			/*"��T�B=�k�ȳ�"*/
			if(names[2].contentEquals("��T�B") || names[2].contentEquals("���ѳB") || 
			   names[2].contentEquals("�H�ƳB") || names[2].contentEquals("�k��q") || 
			   names[2].contentEquals("��X�W���q")){
				names[2] = "�k�ȳ�";
			}
			
			process1_bw.write("INSERT INTO red (REDYY,REDDAT,REDTIME,REDKIND,REDNO,REDUSRID," +
			         "REDNAM,REDCTY,REDDEPT,REDTEL,REDTEL1,REDCTYNAM,REDFAST,REDPRJ," +
			         "REDMAN,REDYN,REDEDAT,REDMEMO,FLOW,REDUSRCTY,FLAG1,REDCLASS,"+
			         "REDSOURCE,REDETIME,REDELAPSE) VALUES (" +
 					 "'"+year+"','"+yeardate+"','17:30',"+
					 "'010','TCEA"+names[0]+"','KD1582',"+
					 "'�c�A��',(SELECT MIN(CODD) FROM COD WHERE CODM='00' AND CODNAM LIKE '%"+names[2]+"%'),'',"+
					 "'','','"+names[3]+"',"+
					 "'4','"+yeardate+"','KD1582',"+
					 "'2','"+names[6]+"','"+names[4]+"NO."+names[0]+"',"+
					 "'KD1582','KDAA','Y',"+
					 "'010','02',"+
					 "'17:30','0')");
			process1_bw.newLine();
		}
		process1_br.close();
		process1_bw.close();
	}

	private static void readFile_process2(File fin) throws IOException {
		Date date = new Date();
		int year = date.getYear()-11;
		
		BufferedReader process2_br = new BufferedReader(new FileReader(fin));
		String line = null;
		File fout = new File("C:\\data_process2.txt");
		FileOutputStream fos = new FileOutputStream(fout);
		BufferedWriter process2_bw = new BufferedWriter(new OutputStreamWriter(fos));
		
		while ((line = process2_br.readLine()) != null) {
			String[] names = line.split(",");
			String yeardate = date.getYear()-11+names[1].substring(4, 8);
			process2_bw.write("insert into RD (" +
					"RDYY,RDNO,RDSEQ," +
					"RDCTY,RDNAM,RDDAT," +
					"RDTIME,RDMEMO,RDFLOW," +
					"RDYN,RDUSRID" +
					")VALUES" +
					"('"+year+"','TCEA"+names[0]+"','0'," +
					"'KDAA','�c�A��','"+yeardate+"'," +
					"'17:32','���ש�"+yeardate+"�ѳ\�z�Ө��z�C','KD0479'," +
					"'2','KD1582');");
			process2_bw.newLine();			
		}
		process2_br.close();
		process2_bw.close();
	}

}