package com.thinkgem.jeesite.modules.agent.agent;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.agent.agent.entity.Agent;
import com.thinkgem.jeesite.modules.agent.agent.service.AgentService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author luotianwen
 */
@Controller
@RequestMapping(value = "/agent")
public class AgentInterfaceController  extends BaseController {
    private static final Random RANDOM = new Random();
    @Autowired
    private AgentService agentService;
    public static String generateGUID()
    {
        return new BigInteger(165, RANDOM).toString(36).toUpperCase();
    }
    @RequestMapping(value = "formadd")
    public String formadd(Agent agent, HttpServletRequest request, Model model) {
        model.addAttribute("agent", agent);
        String token=generateGUID();
        request.getSession().setAttribute("token",token);
        model.addAttribute("token",token);
        return "modules/sys/agent";
    }
    @RequestMapping(value = "saveadd")
    @ResponseBody
    public String saveadd(Agent agent,  String token,Model model,HttpServletResponse response,HttpServletRequest request, RedirectAttributes redirectAttributes) {
        Map map=new HashMap();
       map.put("status",0);
        if (!beanValidator(model, agent)){
             map.put("status",1);
            map.put("message","验证未通过");
            return renderString(response,map);
        }

       String  mtoken=(String)request.getSession().getAttribute("token");
        if(null==mtoken||null==token||"".equals(token)||!mtoken.equals(token)){
            map.put("status",1);
            map.put("message","别重复提交");
            return renderString(response,map);
        }
        User u=new User();
        u.setId("1");
        agent.setCreateBy(u);
        agent.setUpdateBy(u);
      /*  if("true".equals(agent.getSex())){
            agent.setSex("1");
        }
        else{
            agent.setSex("2");
        }*/
        Agent agent1=new Agent();
        agent1.setPhone(agent.getPhone());
        agent1.setMobile(agent.getMobile());
        List s=agentService.findList(agent1);
        if(null==s||s.size()==0){
            try {
                agentService.save(agent);
                request.getSession().removeAttribute("token");
            }catch (Exception e){
                e.printStackTrace();
                map.put("status",1);
                map.put("message","保存"+agent.getName()+"代理失败");
                return renderString(response,map);
            }
        }
        else{
            map.put("status",1);
            map.put("message","手机号已存在");
            return renderString(response,map);
        }
        return renderString(response,map);
    }
    @RequestMapping(value = "query")
    @ResponseBody
    public String query(Agent agent,  String token,Model model,HttpServletResponse response,HttpServletRequest request, RedirectAttributes redirectAttributes) {
        Map map=new HashMap();
        Agent agent1=new Agent();
        agent1.setPhone(agent.getPhone());
        agent1.setMobile(agent.getMobile());
        List<Agent> s=agentService.findList(agent1);
        if(null==s||s.size()==0){
            map.put("message","没有此用户");
        }
        else{
            if("1".equals(s.get(0).getState())){
                map.put("message", "审核通过");
            }
            else if("0".equals(s.get(0).getState())){
                map.put("message", "审核不通过");
            }
            else  {
                map.put("message", "未审核");
            }
        }
        return renderString(response,map);
    }
}
