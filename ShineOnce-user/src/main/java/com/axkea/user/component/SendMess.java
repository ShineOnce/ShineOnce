package com.axkea.user.component;


import com.axkea.common.util.JwtUtils;
import com.axkea.user.domain.User;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SendMess {

    @Value("${service.ipAddr}")
    String ipAddr;

    @Value("${mail.host}")
    String hostName;

    @Value("${mail.username}")
    String username;

    @Value("${mail.password}")
    String password;

    @Value("${mail.port}")
    int port;

    public  Boolean SendActivation(User user, String Email){
        String token = JwtUtils.getJwtToken(0L, user.getUsername());
        try{
            HtmlEmail email=new HtmlEmail();
            email.setCharset("utf-8");
            email.addTo(Email);
            email.setHostName(hostName);
            email.setSmtpPort(465);
            email.setSSLOnConnect(true);
            email.setFrom(username);
            email.setAuthentication(username,password);
            email.setSubject("账号激活");
            email.setMsg("请点击以下链接激活您的账号：  ".concat(ipAddr).concat("/user/activation?token=").concat(token));
            email.send();
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

}
