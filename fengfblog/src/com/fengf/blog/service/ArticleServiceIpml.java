package com.fengf.blog.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fengf.blog.mapper.ArticlecommentMapper;
import com.fengf.blog.mapper.ArticlelikeMapper;
import com.fengf.blog.mapper.ArticlesMapper;
import com.fengf.blog.mapper.AttentionMapper;
import com.fengf.blog.mapper.UsersMapper;
import com.fengf.blog.pojo.ArticleQueryVo;
import com.fengf.blog.pojo.Articlecomment;
import com.fengf.blog.pojo.Articlelike;
import com.fengf.blog.pojo.Articles;
import com.fengf.blog.pojo.Attention;
import com.fengf.blog.pojo.Users;
import com.fengf.common.utils.Page;

@Service
public class ArticleServiceIpml implements ArticleService {

	@Autowired
	private ArticlesMapper articlesMapper;
	@Autowired
	private UsersMapper usersMapper;
	@Autowired
	private ArticlelikeMapper articlelikeMapper;
	@Autowired
	private AttentionMapper attentionMapper;
	@Autowired
	private ArticlecommentMapper articlecommentMapper;
	private String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式 
	private String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式 
	private String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 
     
    
	public String getupDate() throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date=sdf.format(new Date());
		return date;
	}
	@Override
	public boolean savewriting(Articles articles) {
		articles.setCatalog("0");
		articles.setDislike(0);
		articles.setLikecount(0);
		articles.setReading(0);
		articles.setTop("0");
		try {
			articles.setUptime(getupDate());
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	
		int flag = articlesMapper.insertSelective(articles);
		if(flag<=0)
			return false;
		else {
			Users users = usersMapper.selectByusername(articles.getAuthor());
			int flag1 = usersMapper.addUserArticleCount(users.getUserId());
			if(flag1>0)return true;
			else return false;
		}
			
	}
	public String removeHTML(String htmlStr){
		Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
	    Matcher m_script=p_script.matcher(htmlStr); 
	    htmlStr=m_script.replaceAll(""); //过滤script标签 
	     
	    Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
	    Matcher m_style=p_style.matcher(htmlStr); 
	    htmlStr=m_style.replaceAll(""); //过滤style标签 
	     
	    Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
	    Matcher m_html=p_html.matcher(htmlStr); 
	    htmlStr=m_html.replaceAll(""); //过滤html标签 

	    return htmlStr.trim(); //返回文本字符串 
	}
	@Override
	public Page<Articles> selectAllPage(ArticleQueryVo vo) {

		
		Page<Articles> page=new Page<Articles>();
		//每页数
		page.setSize(5);
		vo.setSize(5);
		
		if(vo!=null ){
			//判断当前页
			if(vo.getPage()!=null){
				page.setPage(vo.getPage());
				vo.setStartRow((vo.getPage()-1)*vo.getSize());
			}
			//总条数
			page.setTotal(articlesMapper.articleCountByQueryVo(vo));
			List<Articles> list = articlesMapper.selectArticleListByQueryVo(vo);
			for(Articles article:list){
				
				article.setContent(removeHTML(article.getContent()+""));
			}
			page.setRows(list);
		}
		return page;
	}
	@Override
	public Articles showarticle(Integer articleId) {
		articlesMapper.addReadingByPrimaryKey(articleId);
		return articlesMapper.selectByPrimaryKey(articleId);
	}
	@Override
	public boolean likeAndDislike(Boolean flag,Integer articleId, Integer userId) {
		int flag1=0;
		int insertflag=0;
		Articles article = articlesMapper.selectByPrimaryKey(articleId);
		Articlelike articlelike = new Articlelike();
		articlelike.setArticleId(articleId);
		articlelike.setUserId(userId);
		if(flag){
			articlelike.setLikeordislike("like");
			article.setLikecount(article.getLikecount()+1);
			insertflag=articlelikeMapper.insertSelective(articlelike);
			flag1=articlesMapper.updateLikeByPrimaryKey(article);
			return flag1>0&&insertflag>0?true:false;
		}
		else{
			articlelike.setLikeordislike("dislike");
			article.setDislike(article.getDislike()+1);
			insertflag=articlelikeMapper.insertSelective(articlelike);
			flag1=articlesMapper.updateDisLikeByPrimaryKey(article);
			return flag1>0&&insertflag>0?true:false;
		}
	}
	@Override
	public Users getArticleAuthor(String author) {
		Users users = usersMapper.selectByusername(author);
		return users;
	}
	@Override
	public List<Users> selectHotUsers() {
		return usersMapper.selectHotUsers(5);
	}
	@Override
	public Articlelike getUserLikeAndDisLike(Integer articleId, Integer userId) {
		Articlelike articlelike= new Articlelike();
		articlelike.setArticleId(articleId);
		articlelike.setUserId(userId);
		
		return articlelikeMapper.selectByArticleIdAndUserId(articlelike);
	}
	@Override
	public boolean getIsAttention(Integer userId, String author) {
		Users attentionAuthor = usersMapper.selectByusername(author);
		Attention record = new Attention();
		record.setUserId(userId);
		record.setAttuserId(attentionAuthor.getUserId());

		Attention attention = attentionMapper.selectByAuthorIdAndUserId(record);

		if(attention == null) return false;
		else return true;
	}
	@Override
	public List<Articlecomment> selectNewComment(Articlecomment articlecomment) {
		
		return null;
	}
	@Override
	public boolean insertcomment(Articlecomment articlecomment) {
		
		try {
			articlecomment.setCommentTime(getupDate());
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		int flag = articlecommentMapper.insert(articlecomment);
		if (flag>0) return true;
		else return false;
	}
	@Override
	public List<Articlecomment> getCommentList(Integer articleId) {
		return articlecommentMapper.selectCommentByArticleId(articleId);
	}
	@Override
	public List<Articles> selecthotArticles() {
		List<Articles> listByUser = articlesMapper.selectArticleListByUser(1);
		for(Articles article : listByUser){
			article.setContent(removeHTML(article.getContent()));
		}
		return listByUser;
	}
}
