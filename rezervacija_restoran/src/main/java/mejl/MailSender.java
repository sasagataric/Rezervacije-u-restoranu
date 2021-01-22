package mejl;

import javax.mail.*;
import javax.mail.internet.*;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class MailSender {

	final String senderEmailID = "restoranrezervacija@gmail.com";
	final String senderPassword = "268fcc26685b9b8cab2b648d4130afff2faef8edd4e1f23ea42fbf9647a88845";
	final String emailSMTPserver = "smtp.gmail.com";
	final String emailServerPort = "465";
	String receiverEmailID = null;
	String emailSubject = null;
	String emailBody = null;

	public MailSender(String receiverEmailID, String emailSubject, String emailBody)
	{
		
		String password;
		Scanner input = new Scanner(System.in);
		System.out.println("Uneti šifru mejla: (Uneti -1 za odustanka od slanja)"); 
		do {
			password=input.next();
			if(password.equals("-1")) {
				return;
			}
			try {
				if(senderPassword.equals(toHexString(getSHA(password)))) break;
			} catch (NoSuchAlgorithmException e) {
				System.out.println("Exception thrown for incorrect algorithm: " + e);
			}
			System.out.println("Neispravna šifra, probaj ponovo: (Uneti -1 za odustanka od slanja)");
			
		}while(true);
		
		
		
		
		this.receiverEmailID=receiverEmailID;
		this.emailSubject=emailSubject;
		this.emailBody=emailBody;
		
		Properties props = new Properties();
		props.put("mail.smtp.user",senderEmailID);
		props.put("mail.smtp.host", emailSMTPserver);
		props.put("mail.smtp.port", emailServerPort);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", emailServerPort);
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
	
		SecurityManager security = System.getSecurityManager();
	
		try{
			Authenticator auth = new SMTPAuthenticator(password);
			Session session = Session.getInstance(props, auth);
		
			MimeMessage msg = new MimeMessage(session);
			msg.setContent(emailBody, "text/html; charset=UTF-8");
			msg.setSubject(emailSubject);
			msg.setFrom(new InternetAddress(senderEmailID));
			msg.addRecipient(Message.RecipientType.TO,
			new InternetAddress(receiverEmailID));
			Transport.send(msg);
			}
			catch (Exception mex)
			{
				mex.printStackTrace();
			}
		System.out.println("Mejl je poslata.");
	}
	public class SMTPAuthenticator extends javax.mail.Authenticator
	{
		private String password;
		public SMTPAuthenticator(String password) {
			this.password=password;
		}
		
		public PasswordAuthentication getPasswordAuthentication()
		{
			return new PasswordAuthentication(senderEmailID, password);
		}
	}
	
	public static byte[] getSHA(String input) throws NoSuchAlgorithmException 
    {  
        // Static getInstance method is called with hashing SHA  
        MessageDigest md = MessageDigest.getInstance("SHA-256");  
  
        // digest() method called  
        // to calculate message digest of an input  
        // and return array of byte 
        return md.digest(input.getBytes(StandardCharsets.UTF_8));  
    } 
    
    public static String toHexString(byte[] hash) 
    { 
        // Convert byte array into signum representation  
        BigInteger number = new BigInteger(1, hash);  
  
        // Convert message digest into hex value  
        StringBuilder hexString = new StringBuilder(number.toString(16));  
  
        // Pad with leading zeros 
        while (hexString.length() < 32)  
        {  
            hexString.insert(0, '0');  
        }  
  
        return hexString.toString();  
    } 
	
	public static String textPoruke(String datum,String vreme, int brojosoba) {
		
		return "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n"
        		+ "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" style=\"width:100%;font-family:roboto, 'helvetica neue', helvetica, arial, sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0\">\r\n"
        		+ " <head> \r\n"
        		+ "  <meta charset=\"UTF-8\"> \r\n"
        		+ "  <meta content=\"width=device-width, initial-scale=1\" name=\"viewport\"> \r\n"
        		+ "  <meta name=\"x-apple-disable-message-reformatting\"> \r\n"
        		+ "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"> \r\n"
        		+ "  <meta content=\"telephone=no\" name=\"format-detection\"> \r\n"
        		+ "  <title>New email template 2021-01-06</title> \r\n"
        		+ "  <!--[if (mso 16)]>\r\n"
        		+ "    <style type=\"text/css\">\r\n"
        		+ "    a {text-decoration: none;}\r\n"
        		+ "    </style>\r\n"
        		+ "    <![endif]--> \r\n"
        		+ "  <!--[if gte mso 9]><style>sup { font-size: 100% !important; }</style><![endif]--> \r\n"
        		+ "  <!--[if gte mso 9]>\r\n"
        		+ "<xml>\r\n"
        		+ "    <o:OfficeDocumentSettings>\r\n"
        		+ "    <o:AllowPNG></o:AllowPNG>\r\n"
        		+ "    <o:PixelsPerInch>96</o:PixelsPerInch>\r\n"
        		+ "    </o:OfficeDocumentSettings>\r\n"
        		+ "</xml>\r\n"
        		+ "<![endif]--> \r\n"
        		+ "  <!--[if !mso]><!-- --> \r\n"
        		+ "  <link href=\"https://fonts.googleapis.com/css?family=Roboto:400,400i,700,700i\" rel=\"stylesheet\"> \r\n"
        		+ "  <!--<![endif]--> \r\n"
        		+ "  <style type=\"text/css\">\r\n"
        		+ "#outlook a {\r\n"
        		+ "	padding:0;\r\n"
        		+ "}\r\n"
        		+ ".ExternalClass {\r\n"
        		+ "	width:100%;\r\n"
        		+ "}\r\n"
        		+ ".ExternalClass,\r\n"
        		+ ".ExternalClass p,\r\n"
        		+ ".ExternalClass span,\r\n"
        		+ ".ExternalClass font,\r\n"
        		+ ".ExternalClass td,\r\n"
        		+ ".ExternalClass div {\r\n"
        		+ "	line-height:100%;\r\n"
        		+ "}\r\n"
        		+ ".es-button {\r\n"
        		+ "	mso-style-priority:100!important;\r\n"
        		+ "	text-decoration:none!important;\r\n"
        		+ "}\r\n"
        		+ "a[x-apple-data-detectors] {\r\n"
        		+ "	color:inherit!important;\r\n"
        		+ "	text-decoration:none!important;\r\n"
        		+ "	font-size:inherit!important;\r\n"
        		+ "	font-family:inherit!important;\r\n"
        		+ "	font-weight:inherit!important;\r\n"
        		+ "	line-height:inherit!important;\r\n"
        		+ "}\r\n"
        		+ ".es-desk-hidden {\r\n"
        		+ "	display:none;\r\n"
        		+ "	float:left;\r\n"
        		+ "	overflow:hidden;\r\n"
        		+ "	width:0;\r\n"
        		+ "	max-height:0;\r\n"
        		+ "	line-height:0;\r\n"
        		+ "	mso-hide:all;\r\n"
        		+ "}\r\n"
        		+ ".es-button-border:hover {\r\n"
        		+ "	border-color:#42d159 #42d159 #42d159 #42d159!important;\r\n"
        		+ "	background:#3498db!important;\r\n"
        		+ "}\r\n"
        		+ "@media only screen and (max-width:600px) {p, ul li, ol li, a { font-size:14px!important; line-height:150%!important } h1 { font-size:26px!important; text-align:center; line-height:120%!important } h2 { font-size:24px!important; text-align:center; line-height:120%!important } h3 { font-size:20px!important; text-align:center; line-height:120%!important } h1 a { font-size:26px!important } h2 a { font-size:24px!important } h3 a { font-size:20px!important } .es-menu td a { font-size:13px!important } .es-header-body p, .es-header-body ul li, .es-header-body ol li, .es-header-body a { font-size:13px!important } .es-footer-body p, .es-footer-body ul li, .es-footer-body ol li, .es-footer-body a { font-size:13px!important } .es-infoblock p, .es-infoblock ul li, .es-infoblock ol li, .es-infoblock a { font-size:11px!important } *[class=\"gmail-fix\"] { display:none!important } .es-m-txt-c, .es-m-txt-c h1, .es-m-txt-c h2, .es-m-txt-c h3 { text-align:center!important } .es-m-txt-r, .es-m-txt-r h1, .es-m-txt-r h2, .es-m-txt-r h3 { text-align:right!important } .es-m-txt-l, .es-m-txt-l h1, .es-m-txt-l h2, .es-m-txt-l h3 { text-align:left!important } .es-m-txt-r img, .es-m-txt-c img, .es-m-txt-l img { display:inline!important } .es-button-border { display:block!important } .es-btn-fw { border-width:10px 0px!important; text-align:center!important } .es-adaptive table, .es-btn-fw, .es-btn-fw-brdr, .es-left, .es-right { width:100%!important } .es-content table, .es-header table, .es-footer table, .es-content, .es-footer, .es-header { width:100%!important; max-width:600px!important } .es-adapt-td { display:block!important; width:100%!important } .adapt-img { width:100%!important; height:auto!important } .es-m-p0 { padding:0px!important } .es-m-p0r { padding-right:0px!important } .es-m-p0l { padding-left:0px!important } .es-m-p0t { padding-top:0px!important } .es-m-p0b { padding-bottom:0!important } .es-m-p20b { padding-bottom:20px!important } .es-mobile-hidden, .es-hidden { display:none!important } tr.es-desk-hidden, td.es-desk-hidden, table.es-desk-hidden { width:auto!important; overflow:visible!important; float:none!important; max-height:inherit!important; line-height:inherit!important } tr.es-desk-hidden { display:table-row!important } table.es-desk-hidden { display:table!important } td.es-desk-menu-hidden { display:table-cell!important } .es-menu td { width:1%!important } table.es-table-not-adapt, .esd-block-html table { width:auto!important } table.es-social { display:inline-block!important } table.es-social td { display:inline-block!important } a.es-button, button.es-button { font-size:14px!important; display:block!important; border-left-width:0px!important; border-right-width:0px!important } }\r\n"
        		+ "</style> \r\n"
        		+ " </head> \r\n"
        		+ " <body style=\"width:100%;font-family:roboto, 'helvetica neue', helvetica, arial, sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0\"> \r\n"
        		+ "  <div class=\"es-wrapper-color\" style=\"background-color:#F6F6F6\"> \r\n"
        		+ "   <!--[if gte mso 9]>\r\n"
        		+ "			<v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\">\r\n"
        		+ "				<v:fill type=\"tile\" color=\"#f6f6f6\"></v:fill>\r\n"
        		+ "			</v:background>\r\n"
        		+ "		<![endif]--> \r\n"
        		+ "   <table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-repeat:repeat;background-position:center top\"> \r\n"
        		+ "     <tr style=\"border-collapse:collapse\"> \r\n"
        		+ "      <td valign=\"top\" style=\"padding:0;Margin:0\"> \r\n"
        		+ "       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\"> \r\n"
        		+ "         <tr style=\"border-collapse:collapse\"> \r\n"
        		+ "          <td align=\"center\" style=\"padding:0;Margin:0\"> \r\n"
        		+ "           <table bgcolor=\"transparent\" class=\"es-content-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\"> \r\n"
        		+ "             <tr style=\"border-collapse:collapse\"> \r\n"
        		+ "              <td align=\"left\" style=\"Margin:0;padding-top:10px;padding-bottom:10px;padding-left:20px;padding-right:20px\"> \r\n"
        		+ "               <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> \r\n"
        		+ "                 <tr style=\"border-collapse:collapse\"> \r\n"
        		+ "                  <td align=\"center\" valign=\"top\" style=\"padding:0;Margin:0;width:560px\"> \r\n"
        		+ "                   <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> \r\n"
        		+ "                     <tr style=\"border-collapse:collapse\"> \r\n"
        		+ "                      <td align=\"center\" style=\"padding:0;Margin:0;display:none\"></td> \r\n"
        		+ "                     </tr> \r\n"
        		+ "                   </table></td> \r\n"
        		+ "                 </tr> \r\n"
        		+ "               </table></td> \r\n"
        		+ "             </tr> \r\n"
        		+ "           </table></td> \r\n"
        		+ "         </tr> \r\n"
        		+ "       </table> \r\n"
        		+ "       <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\"> \r\n"
        		+ "         <tr style=\"border-collapse:collapse\"> \r\n"
        		+ "          <td align=\"center\" style=\"padding:0;Margin:0\"> \r\n"
        		+ "           <table class=\"es-content-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"transparent\" align=\"center\"> \r\n"
        		+ "             <tr style=\"border-collapse:collapse\"> \r\n"
        		+ "              <td style=\"Margin:0;padding-top:15px;padding-bottom:15px;padding-left:20px;padding-right:20px;background-position:center bottom\" align=\"left\"> \r\n"
        		+ "               <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> \r\n"
        		+ "                 <tr style=\"border-collapse:collapse\"> \r\n"
        		+ "                  <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:560px\"> \r\n"
        		+ "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> \r\n"
        		+ "                     <tr style=\"border-collapse:collapse\"> \r\n"
        		+ "                      <td align=\"center\" style=\"padding:0;Margin:0;font-size:0px\"><a target=\"_blank\" href=\"https://viewstripo.email\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:roboto, 'helvetica neue', helvetica, arial, sans-serif;font-size:14px;text-decoration:underline;color:#2CB543\"><img src=\"https://www.pinclipart.com/picdir/big/52-526222_fork-clipart-spoon-fork-logo-restaurant-business-card.png\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"189\"></a></td> \r\n"
        		+ "                     </tr> \r\n"
        		+ "                   </table></td> \r\n"
        		+ "                 </tr> \r\n"
        		+ "               </table></td> \r\n"
        		+ "             </tr> \r\n"
        		+ "           </table></td> \r\n"
        		+ "         </tr> \r\n"
        		+ "       </table> \r\n"
        		+ "       <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\"> \r\n"
        		+ "         <tr style=\"border-collapse:collapse\"> \r\n"
        		+ "          <td align=\"center\" style=\"padding:0;Margin:0\"> \r\n"
        		+ "           <table class=\"es-content-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"transparent\" align=\"center\"> \r\n"
        		+ "             <tr style=\"border-collapse:collapse\"> \r\n"
        		+ "              <td style=\"padding:0;Margin:0;padding-bottom:20px;background-position:center top\" align=\"left\"> \r\n"
        		+ "               <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> \r\n"
        		+ "                 <tr style=\"border-collapse:collapse\"> \r\n"
        		+ "                  <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:600px\"> \r\n"
        		+ "                   <table style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:separate;border-spacing:0px;background-position:center bottom;background-color:#FFFFFF;border-radius:5px\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" role=\"presentation\"> \r\n"
        		+ "                     <tr style=\"border-collapse:collapse\"> \r\n"
        		+ "                      <td class=\"es-m-txt-l\" align=\"left\" style=\"Margin:0;padding-bottom:5px;padding-top:20px;padding-left:20px;padding-right:20px\"><h2 style=\"Margin:0;line-height:26px;mso-line-height-rule:exactly;font-family:roboto, 'helvetica neue', helvetica, arial, sans-serif;font-size:22px;font-style:normal;font-weight:normal;color:#3F3D3D\">Poštovani,</h2></td> \r\n"
        		+ "                     </tr> \r\n"
        		+ "                     <tr style=\"border-collapse:collapse\"> \r\n"
        		+ "                      <td align=\"left\" style=\"Margin:0;padding-bottom:5px;padding-top:10px;padding-left:20px;padding-right:20px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:14px;font-family:roboto, 'helvetica neue', helvetica, arial, sans-serif;line-height:21px;color:#3F3D3D\">Realizovana vam je rezervacija za "+datum.substring(0, 2)+"."+datum.substring(3, 5)+"."+datum.substring(6)+" u "+vreme.substring(0, 2)+":"+vreme.substring(2)+", broj mesta za stolom je "+brojosoba+". Molimo vas da nam potvrdite rezervaciju u roku od 2 dana kako bi ostala saèuvana.</p></td> \r\n"
        		+ "                     </tr> \r\n"
        		+ "                     <tr style=\"border-collapse:collapse\"> \r\n"
        		+ "                      <td class=\"es-m-txt-c\" align=\"left\" style=\"Margin:0;padding-top:10px;padding-bottom:20px;padding-left:20px;padding-right:20px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:14px;font-family:roboto, 'helvetica neue', helvetica, arial, sans-serif;line-height:21px;color:#3F3D3D\"><strong>Srdaèan pozdrav,<br>Vaš omiljeni restoran</strong></p></td> \r\n"
        		+ "                     </tr> \r\n"
        		+ "                   </table></td> \r\n"
        		+ "                 </tr> \r\n"
        		+ "                 <tr style=\"border-collapse:collapse\"> \r\n"
        		+ "                  <td align=\"left\" style=\"padding:0;Margin:0;width:600px\"> \r\n"
        		+ "                   <table style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-position:center top\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\"> \r\n"
        		+ "                     <tr style=\"border-collapse:collapse\"> \r\n"
        		+ "                      <td class=\"es-m-txt-c\" align=\"center\" style=\"padding:0;Margin:0;padding-top:5px;padding-bottom:5px;font-size:0\"> \r\n"
        		+ "                       <table class=\"es-table-not-adapt es-social\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> \r\n"
        		+ "                         <tr style=\"border-collapse:collapse\"> \r\n"
        		+ "                          <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;padding-right:10px\"><a target=\"_blank\" href=\"https://www.facebook.com/UltraCaffe-149891745041520/\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:roboto, 'helvetica neue', helvetica, arial, sans-serif;font-size:14px;text-decoration:underline;color:#2CB543\"><img title=\"Facebook\" src=\"https://oturoa.stripocdn.email/content/assets/img/social-icons/logo-black/facebook-logo-black.png\" alt=\"Fb\" width=\"32\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></a></td> \r\n"
        		+ "                          <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0\"><a target=\"_blank\" href=\"https://www.instagram.com/ultra_caffe/?hl=sr\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:roboto, 'helvetica neue', helvetica, arial, sans-serif;font-size:14px;text-decoration:underline;color:#2CB543\"><img title=\"Instagram\" src=\"https://oturoa.stripocdn.email/content/assets/img/social-icons/logo-black/instagram-logo-black.png\" alt=\"Inst\" width=\"32\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\"></a></td> \r\n"
        		+ "                         </tr> \r\n"
        		+ "                       </table></td> \r\n"
        		+ "                     </tr> \r\n"
        		+ "                   </table></td> \r\n"
        		+ "                 </tr> \r\n"
        		+ "               </table></td> \r\n"
        		+ "             </tr> \r\n"
        		+ "           </table></td> \r\n"
        		+ "         </tr> \r\n"
        		+ "       </table> \r\n"
        		+ "       <table class=\"es-footer\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top\"> \r\n"
        		+ "         <tr style=\"border-collapse:collapse\"> \r\n"
        		+ "          <td align=\"center\" style=\"padding:0;Margin:0\"> \r\n"
        		+ "           <table class=\"es-footer-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#FFFFFF\" align=\"center\"> \r\n"
        		+ "             <tr style=\"border-collapse:collapse\"> \r\n"
        		+ "              <td style=\"Margin:0;padding-top:5px;padding-bottom:20px;padding-left:20px;padding-right:20px;background-position:center top;background-color:transparent\" bgcolor=\"transparent\" align=\"left\"> \r\n"
        		+ "               <!--[if mso]><table style=\"width:560px\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:270px\" valign=\"top\"><![endif]--> \r\n"
        		+ "               <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\"> \r\n"
        		+ "                 <tr style=\"border-collapse:collapse\"> \r\n"
        		+ "                  <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:270px\"> \r\n"
        		+ "                   <table style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-position:center top\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\"> \r\n"
        		+ "                     <tr style=\"border-collapse:collapse\"> \r\n"
        		+ "                      <td align=\"center\" style=\"padding:0;Margin:0;display:none\"></td> \r\n"
        		+ "                     </tr> \r\n"
        		+ "                   </table></td> \r\n"
        		+ "                 </tr> \r\n"
        		+ "               </table> \r\n"
        		+ "               <!--[if mso]></td><td style=\"width:20px\"></td><td style=\"width:270px\" valign=\"top\"><![endif]--> \r\n"
        		+ "               <table class=\"es-right\" cellspacing=\"0\" cellpadding=\"0\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right\"> \r\n"
        		+ "                 <tr style=\"border-collapse:collapse\"> \r\n"
        		+ "                  <td align=\"left\" style=\"padding:0;Margin:0;width:270px\"> \r\n"
        		+ "                   <table style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-position:center top\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\"> \r\n"
        		+ "                     <tr style=\"border-collapse:collapse\"> \r\n"
        		+ "                      <td align=\"center\" style=\"padding:0;Margin:0;display:none\"></td> \r\n"
        		+ "                     </tr> \r\n"
        		+ "                   </table></td> \r\n"
        		+ "                 </tr> \r\n"
        		+ "               </table> \r\n"
        		+ "               <!--[if mso]></td></tr></table><![endif]--></td> \r\n"
        		+ "             </tr> \r\n"
        		+ "           </table></td> \r\n"
        		+ "         </tr> \r\n"
        		+ "       </table> \r\n"
        		+ "       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\"> \r\n"
        		+ "         <tr style=\"border-collapse:collapse\"> \r\n"
        		+ "          <td align=\"center\" style=\"padding:0;Margin:0\"> \r\n"
        		+ "           <table bgcolor=\"transparent\" class=\"es-content-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;width:600px\"> \r\n"
        		+ "             <tr style=\"border-collapse:collapse\"> \r\n"
        		+ "              <td align=\"left\" style=\"Margin:0;padding-left:20px;padding-right:20px;padding-top:30px;padding-bottom:30px;background-position:left top\"> \r\n"
        		+ "               <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> \r\n"
        		+ "                 <tr style=\"border-collapse:collapse\"> \r\n"
        		+ "                  <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:560px\"> \r\n"
        		+ "                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> \r\n"
        		+ "                     <tr style=\"border-collapse:collapse\"> \r\n"
        		+ "                      <td align=\"center\" style=\"padding:0;Margin:0;display:none\"></td> \r\n"
        		+ "                     </tr> \r\n"
        		+ "                   </table></td> \r\n"
        		+ "                 </tr> \r\n"
        		+ "               </table></td> \r\n"
        		+ "             </tr> \r\n"
        		+ "           </table></td> \r\n"
        		+ "         </tr> \r\n"
        		+ "       </table></td> \r\n"
        		+ "     </tr> \r\n"
        		+ "   </table> \r\n"
        		+ "  </div>  \r\n"
        		+ " </body>\r\n"
        		+ "</html>";
		
	}
	

}
