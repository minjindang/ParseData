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
			if((!names[5].contentEquals("處理完成")) && (!names[6].contentEquals("處理完成")) &&
			   (!names[7].contentEquals("處理完成")) && (!names[8].contentEquals("處理完成"))	
			   ){
				bw_log.write("未匯入的問題編號 : "+names[0]);
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
				/*"高分檢=分院檢察署"*/
				if(codName.contentEquals("高分檢")){
					names[2] = names[2].substring(0,2)+"分院檢察署";
				}			
				/*"技訓所=技能訓練所"*/
				if(codName.contentEquals("技訓所")){
					names[2] = names[2].substring(0,2)+"技能訓練所";
				}			
				/*"地檢=地方法院檢察署"*/
				if(codName.contentEquals("地檢")){
					names[2] = names[2].substring(0,2)+"地方法院檢察署";
				}			
				/*"二監=第二監獄"*/
				if(codName.contentEquals("二監")){
					names[2] = names[2].substring(0,2)+"第二監獄";
				}
				/*"少觀=少年觀護所"*/
				if(codName.contentEquals("少觀")){
					names[2] = names[2].substring(0,2)+"少年觀護所";
				}
				/*"少輔=少年輔育院"*/
				if(codName.contentEquals("少輔")){
					names[2] = names[2].substring(0,2)+"少年輔育院";
				}
				/*"女監=女子監獄"*/
				if(codName.contentEquals("女監")){
					names[2] = names[2].substring(0,2)+"女子監獄";
				}
				/*"最高檢=最高法院檢察署"*/
				if(names[2].contentEquals("最高檢")){
					names[2] = "最高法院檢察署";
				}
				/*"臺高檢=高等法院檢察署"*/
				if(names[2].contentEquals("臺高檢")){
					names[2] = "高等法院檢察署";
				}
				/*"司法官學院=司法官訓練所"*/
				if(names[2].contentEquals("司法官學院")){
					names[2] = "司法官訓練所";
				}
				/*"犯保及更保=財團法人犯罪被害人保護協會及財團法人臺灣更生保護會"*/
				if(names[2].contentEquals("犯保及更保")){
					names[2] = "財團法人犯罪被害人保護協會及財團法人臺灣更生保護會";
				}
				/*"國兩司=教育部國際及兩岸教育司"*/
				if(names[2].contentEquals("國兩司")){
					names[2] = "教育部國際及兩岸教育司";
				}
				/*"資訊處=法務部"*/
				if(names[2].contentEquals("資訊處") || names[2].contentEquals("秘書處") || 
				   names[2].contentEquals("人事處") || names[2].contentEquals("法制司") || 
				   names[2].contentEquals("綜合規劃司")){
					names[2] = "法務部";
				}
				
				if(names[5].contentEquals("處理完成") || names[6].contentEquals("處理完成") ||
				   names[7].contentEquals("處理完成") || names[8].contentEquals("處理完成") ||
				   names[9].contentEquals("處理完成") || names[10].contentEquals("處理完成")){
						 String done1 ="INSERT INTO red (REDYY,REDDAT,REDTIME,REDKIND,REDNO,REDUSRID," +
				         "REDNAM,REDCTY,REDDEPT,REDTEL,REDTEL1,REDCTYNAM,REDFAST,REDPRJ," +
				         "REDMAN,REDYN,REDEDAT,REDMEMO,FLOW,REDUSRCTY,FLAG1,REDCLASS,"+
				         "REDSOURCE,REDETIME,REDELAPSE) VALUES (" +
	 					 "'"+year+"','"+yeardate+"','17:30',"+
						 "'010','TCEA"+names[0]+"','KD1582',"+
						 "'盧譽心',(SELECT MIN(CODD) FROM COD WHERE CODM='00' AND CODNAM LIKE '%"+names[2]+"%'),'',"+
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
					//System.out.println("成功新增了" + count + "筆記錄");
						 
				} else {
					System.out.println("未匯入的問題編號 : "+names[0]);
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
				if (names[5].contentEquals("處理完成")	|| names[6].contentEquals("處理完成")
						|| names[7].contentEquals("處理完成") || names[8].contentEquals("處理完成")
						|| names[9].contentEquals("處理完成") || names[10].contentEquals("處理完成")) {
					String done2 = "insert into RD (" + "RDYY,RDNO,RDSEQ,"
							+ "RDCTY,RDNAM,RDDAT," + "RDTIME,RDMEMO,RDFLOW,"
							+ "RDYN,RDUSRID" + ")VALUES" + "('" + year
							+ "','TCEA" + names[0] + "','0',"
							+ "'KDAA','盧譽心','" + yeardate + "',"
							+ "'17:32','本案於" + yeardate + "由盧譽心受理。','KD1582',"
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
				if(names[5].contentEquals("處理完成") || names[6].contentEquals("處理完成") ||
				   names[7].contentEquals("處理完成") || names[8].contentEquals("處理完成") ||
				   names[9].contentEquals("處理完成") || names[10].contentEquals("處理完成")){
						String done3 ="insert into RD (" +
						"RDYY,RDNO,RDSEQ," +
						"RDCTY,RDNAM,RDDAT," +
						"RDTIME,RDMEMO,RDFLOW," +
						"RDYN,RDUSRID" +
						")VALUES" +
						"('"+year+"','TCEA"+names[0]+"','1'," +
						"'KDAA','盧譽心','"+yeardate+"'," +
						"'17:40','已處理。','KD1582'," +
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