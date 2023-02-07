package com.zhuzichu.controller

import com.zhuzichu.error.R
import spark.Request
import spark.Response
import java.util.*
import javax.mail.Session

class LoginController : BaseController() {


    override fun path(): String {
        return "/login"
    }

    override fun handleRequest(request: Request, response: Response): R<*> {
        val imapHost = request.queryParams("imapHost")
        val imapPort = request.queryParams("imapPort")
        val imapSecurity = request.queryParams("imapSecurity")

        val smtpHost = request.queryParams("smtpHost")
        val smtpPort = request.queryParams("smtpPort")
        val smtpSecurity = request.queryParams("smtpSecurity")

        val email = request.queryParams("email")
        val password = request.queryParams("password")

        var props = Properties()
        props.setProperty("mail.store.protocol", "imap")
        if(imapSecurity == "SSL/TLS"){
            props.setProperty("mail.imap.ssl.enable", "true")
        }else if(imapSecurity == "STARTTLS"){
            props.setProperty("mail.imap.starttls.Enable", "true")
        }
        props.setProperty("mail.imap.host", imapHost)
        props.setProperty("mail.imap.port", imapPort)
        val imapSession: Session = Session.getInstance(props)
        val store = imapSession.getStore("imap")
        store.connect(email, password)

        props = Properties()
        props.setProperty("mail.transport.protocol", "smtp")
        if(smtpSecurity == "SSL/TLS"){
            props.setProperty("mail.smtp.ssl.enable", "true")
        }else if(imapSecurity == "STARTTLS"){
            props.setProperty("mail.smtp.starttls.Enable", "true")
        }
        props.setProperty("mail.smtp.host", smtpHost)
        props.setProperty("mail.smtp.port", smtpPort)
        val smtpSession: Session = Session.getInstance(props)
        val transport = smtpSession.getTransport("smtp")
        transport.connect(email, password)
        return R.success(null)
    }

}