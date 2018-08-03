package com.sp.model;

import java.sql.*;
import java.util.*;

public class UserBeanCl {
	private Statement sm = null;
	private ResultSet rs = null;
	private ResultSet rs1 = null;
	private Connection ct = null;
	private int pageCount = 0;
	private int rowCount = 0;
	private int pageSize = 3;

	public int getPageCount() {
		try {
			ct = new ConnDB().getConn();
			sm=ct.createStatement();
			rs = sm.executeQuery("select count(*) from users");
			if (rs.next()) {
				rowCount = rs.getInt(1);
			}
			if (rowCount % pageSize == 0) {
				pageCount = rowCount / pageSize;
			} else {
				pageCount = rowCount / pageSize + 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return pageCount;
	}

	public void close() { // 关闭各种打开的资源
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (rs1 != null) {
				rs1.close();
				rs1 = null;
			}
			if (sm != null) {
				sm.close();
				sm = null;
			}
			if (ct != null) {
				ct.close();
				ct = null;
			}
		} catch (Exception e) {
			e.printStackTrace();// 打印异常，以便修改
		}
	}

	public ArrayList getUsersByPage(int pageNow) {
		ArrayList al = new ArrayList(); 
		ArrayList<String> al1 = new ArrayList();
		String userid;
		String notInList;
		String rrr;
		String rrr1;
		String rrr2;
		System.out.println("for debug using, am I smart?");
		try {
			ct = new ConnDB().getConn();
			sm = ct.createStatement();
			System.out.println("The page size is: "+ pageSize);
			System.out.println("The pageNow is: "+ pageNow);
			System.out.println("about to run -- select userID from users limit " + " " +pageSize * pageNow);
			rs1= sm.executeQuery("select userID from users limit " +" " +pageSize * pageNow );
			System.out.println(" select userID from users limit" + pageSize * pageNow	+ " completed"	);
			
			notInList="(";
			while ( rs1.next()) {
				userid=rs1.getString(1);
				System.out.println("The user id to be added to al1 is: "+ userid);
				al1.add(userid);	
				System.out.println("The user id added to al1 is: "+ userid);
				
			}
			notInList="(" +"1,2,3"+ ")";
			System.out.println("not in list is:"+notInList );
			
			rrr=al1.toString();
			System.out.println("the string of rrr  is :"+ rrr);
			rrr1=rrr.replace('[', '(');
			rrr2=rrr1.replace(']',')');
			
			System.out.println("the string of rrr2  is :"+ rrr2);
			
			
			System.out.println("sql to run --" + "select * from users where userID not in " + " "+rrr2 + " limit " + " "+pageSize	);
			rs = sm.executeQuery("select * from users where userID not in " + rrr2+ " limit " + " "+pageSize);
			System.out.println("sql to completed -->" + "select * from users where userID not in " + " "+rrr2 + " limit " + " "+pageSize	);
			
/*			rs = sm.executeQuery("select top " + pageSize
					+ " * from users where userID not in (select top 
					+ pageSize * (pageNow - 1) + " userID from users) ");	
	*/
			while (rs.next()) {
				UserBean ub = new UserBean();
				ub.setUserID(rs.getString(1));
				ub.setUsername(rs.getString(2));
				ub.setPasswd(rs.getString(3));
				ub.setGrade(rs.getString(4));
				al.add(ub); // 将al放到arrayList中
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return al;
	}

	public boolean checkUser(String u, String p) {
		boolean b = false;
		try {
			ct = new ConnDB().getConn();// 获取ConnDB中的方法，操作数据库
			sm = ct.createStatement();
			rs = sm.executeQuery("select passwd from users where username='"
					+ u + "'");
			if (rs.next()) {
				if (rs.getString(1).equals(p)) {
					b = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 关闭各种打开的资源，释放内存
			this.close();
		}
		return b;
	}

}
