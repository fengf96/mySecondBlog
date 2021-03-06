package com.fengf.blog.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fengf.blog.mapper.ArticlesMapper;
import com.fengf.blog.mapper.AttentionMapper;
import com.fengf.blog.mapper.UsersMapper;
import com.fengf.blog.pojo.ArticleQueryVo;
import com.fengf.blog.pojo.Articles;
import com.fengf.blog.pojo.Attention;
import com.fengf.blog.pojo.UserQueryVo;
import com.fengf.blog.pojo.Users;
import com.fengf.common.utils.Page;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UsersMapper usersMapper;
	@Autowired
	private AttentionMapper attentionMapper;
	@Autowired
	private ArticlesMapper articlesMapper;
	@Override
	public Users userLogin(Users user) {
		Users current_user = usersMapper.selectByusername(user.getUserName());
		current_user.setJoinday(getJoinday(current_user));
		if(current_user != null && (user.getUserPassword().equals(current_user.getUserPassword())))
			return current_user;
		else return null;
		
	}
	public String getupDate() throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date=sdf.format(new Date());
		return date;
	}
	@Override
	public boolean userInsert(Users user) {
		user.setArticlecount(0);
		user.setAttention(0);
		user.setFans(0);
		user.setGold(0);
		user.setMoodcount(0);
		user.setIntroduction("赶快介绍一下自己吧...");
		user.setProfession("未填写");
		try {
		user.setJoinday(getupDate());
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		int flag = usersMapper.insert(user);
		if(flag<=0)
		return false;
		else return true;
	}
	@Override
	public boolean checkUsername(String userName) {
		Users user = usersMapper.selectByusername(userName);
		if(user != null)
			return false;
		else return true;
	}
	@Override
	public String getJoinday(Users current_user) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long jointime=0;
		try {
			jointime = sdf.parse(current_user.getJoinday()).getTime();
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		long time = new Date().getTime();
		return (time-jointime)/1000/24/60/60+"";
	}
	@Override
	public boolean personEdit(Users user) {
		int flag = usersMapper.updateByPrimaryKey(user);
		if(flag<=0)
			return false;
		else return true;
	}
	@Override
	public boolean insertAttention(Integer current_userId, Integer userId) {
		Attention attention = new Attention();
		attention.setUserId(current_userId);
		attention.setAttuserId(userId);
		attention.setAttentionGroup(0);
		try {
			attention.setAttentionTime(getupDate());
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		int flag = attentionMapper.insertSelective(attention);
		if(flag>0){
			int flag2=usersMapper.addUserFansByUserId(userId);
			int flag3=usersMapper.addUserAttentionByUserId(current_userId);
			if(flag2>0&&flag3>0) return true;
			else return false;
		}
		else return false;
	}
	@Override
	public boolean deleteAttention(Integer current_userId, Integer userId) {
		Attention attention = new Attention();
		attention.setUserId(current_userId);
		attention.setAttuserId(userId);
		int flag = attentionMapper.deleteByUserIdAndAttuserId(attention);
		if(flag>0){
			int flag2=usersMapper.deleteUserFansByUserId(userId);
			int flag3=usersMapper.deleteUserAttentionByUserId(current_userId);
			if(flag2>0&&flag3>0) return true;
			else return false;
		}
		else return false;
	}
	@Override
	public Page<Users> selectAllPage(UserQueryVo vo) {
		Page<Users> page=new Page<Users>();
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
			page.setTotal(usersMapper.userCountByQueryVo(vo));
			page.setRows(usersMapper.selectUserListByQueryVo(vo));
		}
		return page;
	}
	@Override
	public Users personcenter(Integer userId) {
		 Users user=usersMapper.selectByPrimaryKey(userId);
		 user.setJoinday(getJoinday(user));
		return user;
	}
	@Override
	public boolean getIsAttention(Integer current_userId, Integer userId) {
		Attention record = new Attention();
		record.setUserId(current_userId);
		record.setAttuserId(userId);
		Attention attention = attentionMapper.selectByAuthorIdAndUserId(record);
		if(attention == null) return false;
		else return true;
	}
	@Override
	public Page<Articles> selectUserAllPage(ArticleQueryVo vo) {
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
			page.setRows(articlesMapper.selectArticleListByQueryVo(vo));
		}
		return page;
	}
	@Override
	public Page<Users> selectAllAttentionPage(UserQueryVo vo) {
		Page<Users> page=new Page<Users>();
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
			page.setTotal(usersMapper.attentionCountByQueryVo(vo));
			page.setRows(usersMapper.selectAttentionListByQueryVo(vo));
		}
		return page;
	}
	@Override
	public Page<Users> selectAllFansPage(UserQueryVo vo) {
		Page<Users> page=new Page<Users>();
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
			page.setTotal(usersMapper.fansCountByQueryVo(vo));
			page.setRows(usersMapper.selectFansListByQueryVo(vo));
		}
		return page;
	}
	

}
