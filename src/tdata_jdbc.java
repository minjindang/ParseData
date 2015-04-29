import java.io.*;
import java.sql.*;
import java.util.Date;

public class tdata_jdbc {
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
		String connecString = "jdbc:sqlserver://172.16.30.55:1433;databaseName=KDTL_TCEA;user=kdtl;password=27152222";
		Connection connection =null;
		Statement statment1 = null;
		
		Date date = new Date();
		int year = date.getYear()-11;		
		BufferedReader done1_br = new BufferedReader(new FileReader(fin));
		String line = null;		
		
		try {
			connection = DriverManager.getConnection(connecString);			
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
						 String done1 ="INSERT INTO red (REDYY,REDDAT,REDTIME,REDKIND,REDNO,REDUSRID," +
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
						 "'17:30','0')";
					
					statment1 = connection.createStatement();
					statment1.executeUpdate(done1);
					//System.out.println("OK");
					//int count = statment1.executeUpdate(done1);
					//System.out.println("���\�s�W�F" + count + "���O��");
						 
				} else {
					System.out.println("���פJ�����D�s�� : "+names[0]);
				}
			}
			done1_br.close();
			statment1.close();			
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				System.out.println("Finally Exception");
			}
		}
	}
	
	private static void readFile_done2(File fin) throws IOException {
		
		Date date = new Date();
		int year = date.getYear()-11;	
		BufferedReader done2_br = new BufferedReader(new FileReader(fin));
		String line = null;
		
		String connecString = "jdbc:sqlserver://172.16.30.55:1433;databaseName=KDTL_TCEA;user=kdtl;password=27152222";
		Connection connection =null;
		Statement statment2 = null;
		
		try {
			connection = DriverManager.getConnection(connecString);		
			while ((line = done2_br.readLine()) != null) {
				String[] names = line.split(",");
				String yeardate = date.getYear() - 11
						+ names[1].substring(4, 8);
				if (names[5].contentEquals("�B�z����")	|| names[6].contentEquals("�B�z����")
						|| names[7].contentEquals("�B�z����") || names[8].contentEquals("�B�z����")
						|| names[9].contentEquals("�B�z����") || names[10].contentEquals("�B�z����")) {
					String done2 = "insert into RD (" + "RDYY,RDNO,RDSEQ,"
							+ "RDCTY,RDNAM,RDDAT," + "RDTIME,RDMEMO,RDFLOW,"
							+ "RDYN,RDUSRID" + ")VALUES" + "('" + year
							+ "','TCEA" + names[0] + "','0',"
							+ "'KDAA','�c�A��','" + yeardate + "',"
							+ "'17:32','���ש�" + yeardate + "�ѿc�A�ߨ��z�C','KD1582',"
							+ "'6','KD1582');";
					statment2 = connection.createStatement();
					statment2.executeUpdate(done2);
				}
			}
			done2_br.close();		
			statment2.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				System.out.println("Finally Exception");
			}
		}
	}
	
	private static void readFile_done3(File fin) throws IOException {
		Date date = new Date();
		int year = date.getYear()-11;		
		BufferedReader done3_br = new BufferedReader(new FileReader(fin));
		String line = null;
		
		String connecString = "jdbc:sqlserver://172.16.30.55:1433;databaseName=KDTL_TCEA;user=kdtl;password=27152222";
		Connection connection =null;
		Statement statment3 = null;
		
		try {
			connection = DriverManager.getConnection(connecString);		
			while ((line = done3_br.readLine()) != null) {
				String[] names = line.split(",");
				String yeardate = date.getYear()-11+names[1].substring(4, 8);
				if(names[5].contentEquals("�B�z����") || names[6].contentEquals("�B�z����") ||
				   names[7].contentEquals("�B�z����") || names[8].contentEquals("�B�z����") ||
				   names[9].contentEquals("�B�z����") || names[10].contentEquals("�B�z����")){
						String done3 ="insert into RD (" +
						"RDYY,RDNO,RDSEQ," +
						"RDCTY,RDNAM,RDDAT," +
						"RDTIME,RDMEMO,RDFLOW," +
						"RDYN,RDUSRID" +
						")VALUES" +
						"('"+year+"','TCEA"+names[0]+"','1'," +
						"'KDAA','�c�A��','"+yeardate+"'," +
						"'17:40','�w�B�z�C','KD1582'," +
						"'7','KD1582');";
						statment3 = connection.createStatement();
						statment3.executeUpdate(done3);
				}
			}
			done3_br.close();		
			statment3.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				System.out.println("Finally Exception");
			}
		}
	}	
}