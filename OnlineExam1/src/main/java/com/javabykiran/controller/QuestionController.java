package com.javabykiran.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.javabykiran.entity.Answer;
import com.javabykiran.entity.Questions;

@Controller
public class QuestionController {

	@Autowired
	SessionFactory factory;
	

	@RequestMapping("getQuestions")
	public ModelAndView getQuestions(String subject,HttpServletRequest request)
	{
		System.out.println(subject);
		
		Session session = factory.openSession();
		
		Query query = session.createQuery("from Questions where subject=:subject");
		query.setParameter("subject",subject);
		
		List<Questions> listOfQuestions=query.list();
		
		System.out.println(listOfQuestions);
		
		HttpSession httpSession = request.getSession();
		
		httpSession.setAttribute("listOfQuestions",listOfQuestions);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("questions");
		
		return modelAndView;
	}
	

	@RequestMapping("startExam")
	public ModelAndView startExam(String subject,HttpServletRequest request)
	{

		HttpSession httpSession = request.getSession();
		List<Questions> listOfQuestions = (List<Questions>) httpSession.getAttribute("listOfQuestions");
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("questionnavigation");
		modelAndView.addObject("question",listOfQuestions.get(0));
		
		httpSession.setAttribute("score",0);
		httpSession.setAttribute("qno",0);
		httpSession.setAttribute("submittedDetails",new HashMap<Integer,Answer>());
		
		return modelAndView;
	}
	
	
	@RequestMapping("storeResponse")
	public void storeResponse(Answer answer,HttpServletRequest request)
	{
		System.out.println("before getting correct answer from list " + answer);
		
		HttpSession httpSession = request.getSession();
		
		List<Questions> listOfQuestions = (List<Questions>) httpSession.getAttribute("listOfQuestions");
		
		String correctAnswer= listOfQuestions.get(answer.getQno()-1).getAnswer();
		
		answer.setCorrectAnswer(correctAnswer);
		
		System.out.println("after getting correct answer from list " + answer);
		
		// [qno=1 question=square answer=4 option1=2 option2=3] Question object
		
	}

	@RequestMapping("next")
	public ModelAndView next(HttpServletRequest request)
	{
		HttpSession httpSession = request.getSession();
		
		List<Questions> listOfQuestions = (List<Questions>) httpSession.getAttribute("listOfQuestions");
			
		httpSession.setAttribute("qno",(Integer)httpSession.getAttribute("qno") + 1);
		
		ModelAndView modelAndView = new ModelAndView();
		
		if((Integer)httpSession.getAttribute("qno")<listOfQuestions.size())
		{
			Questions question = listOfQuestions.get((Integer)httpSession.getAttribute("qno"));
			modelAndView.setViewName("questionnavigation");
			modelAndView.addObject("question",question);
		}
		
		else
		{
			modelAndView.setViewName("questionnavigation");
			modelAndView.addObject("message","questions over");
		}
		return modelAndView;
	}
	
	
	@RequestMapping("previous")
	public ModelAndView previous(HttpServletRequest request)
	{
		HttpSession httpSession = request.getSession();
		
		List<Questions> listOfQuestions = (List<Questions>) httpSession.getAttribute("listOfQuestions");
			
		httpSession.setAttribute("qno",(Integer)httpSession.getAttribute("qno") - 1);
		
		ModelAndView modelAndView = new ModelAndView();
		
		if((Integer)httpSession.getAttribute("qno")>=0)
		{
			Questions question = listOfQuestions.get((Integer)httpSession.getAttribute("qno"));
			modelAndView.setViewName("questionnavigation");
			modelAndView.addObject("question",question);
		}
		
		else
		{
			modelAndView.setViewName("questionnavigation");
			modelAndView.addObject("message","click on next button");
		}
		return modelAndView;
	}
	
	
	@RequestMapping("endexam")
	public ModelAndView endexam(HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("score");
		modelAndView.addObject("score","2");
		return modelAndView;
	}
}
