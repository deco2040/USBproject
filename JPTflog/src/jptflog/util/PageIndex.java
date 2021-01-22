package jptflog.util;

import java.net.URLEncoder;

public class PageIndex {
	public static String pageList(int page,int totpage,String url,String addtag) {
		int idx_pre, idx_start;
		  	
		String s_pre = "";    // Prev 저장 변수
		String s_idx = "";    // 번호 저장 변수
		String s_next = "";   // Next 저장 변수

		idx_start = ((page-1) / 10) * 10 + 1 ;  // 시작 페이지 계산
		idx_pre = ((page-1) / 10);              // Priview 페이지 계산

	  	// Prev 표시 부분
	  	if(idx_pre > 0) {
	  		s_pre = "<a href='"+url+"&page="+(idx_pre*10)+addtag+"'>"
	  				+ "<img src=/Pds/img/i_prev.gif width=30 height=7></a>";
	  	} else {
	  		s_pre = "<img src=/Pds/img/i_prev.gif width=30 height=7> ";
	  	}

	  	// 번호 표시부분	
	  	for(int i=0;i<10;i++,idx_start++) {
	  		if(idx_start>totpage) break;
	  		if(idx_start == page)
	  			s_idx = s_idx + " "+idx_start+" ";
	  		else {
	  			s_idx = s_idx + " <a href='" + url + "&page=" + idx_start;
	  			s_idx = s_idx + addtag + "'> " + idx_start + " </a> ";
	  		}
	  	}
		// Next 표시부분
	  	if(idx_start <= totpage ) {
	  		s_next = "<a href='"+url+"&page="+idx_start+addtag+"'>"
	  				+ "<img src=/Pds/img/i_next.gif width=30 height=7></a>";
	  	} else {
	  		s_next = " <img src=/Pds/img/i_next.gif width=30 height=7>";
	  	}

	  	String outHtml = s_pre + "[" + s_idx + "]" + s_next;  // Html 문 조합
	  	return outHtml;
	}

	public static String pageListHan(int page,int totpage,String url,String query, String key) {
		int idx_pre, idx_start;
		  	
		String s_pre = "";    // Prev 저장 변수
		String s_idx = "";    // 번호 저장 변수
		String s_next = "";   // Next 저장 변수

		idx_start = ((page-1) / 10) * 10 + 1 ;  // 시작 페이지 계산
		idx_pre = ((page-1) / 10);              // Priview 페이지 계산

	  	// Prev 표시 부분
	  	if(idx_pre > 0) {
	  		s_pre = "<a href='"+url+"&page="+(idx_pre*10)+"&search="+query+"&key="+ URLEncoder.encode(key)+"'>"
	  				+ "<img src=/Pds/img/i_prev.gif width=30 height=7></a>";
	  	} else {
	  		s_pre = "<img src=/Pds/img/i_prev.gif width=30 height=7> ";
	  	}

	  	// 번호 표시부분	
	  	for(int i=0;i<10;i++,idx_start++) {
	  		if(idx_start>totpage) break;
	  		if(idx_start == page)
	  			s_idx = s_idx + " "+idx_start+" ";
	  		else {
	  			s_idx = s_idx + " <a href='" + url + "&page=" + idx_start;
	  			s_idx = s_idx + "&search="+query+"&key=" + URLEncoder.encode(key) + "'> " + idx_start + " </a> ";
	  		}
	  	}
		// Next 표시부분
	  	if(idx_start <= totpage ) {
	  		s_next = "<a href='"+url+"&page="+idx_start+ "&search="+query+"&key="+ URLEncoder.encode(key)+"'>"
	  				+ "<img src=/Pds/img/i_next.gif width=30 height=7></a>";
	  	} else {
	  		s_next = " <img src=/Pds/img/i_next.gif width=30 height=7>";
	  	}

	  	String outHtml = s_pre + "[" + s_idx + "]" + s_next;  // Html 문 조합
	  	return outHtml;
	}

}
