<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : Messenger_StyleSheet.xsl
    Created on : January 31, 2015, 9:43 AM
    Author     : Marwa
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <html>
            <head>
                <title>Messenger_StyleSheet.xsl</title>
                <style>
                    #ms{
                    background-color: #F0F09E;
                    color: #9E0808;
                    }
                    .t_login{
                    width: 100%;
                    }
                    .login{
                    background-color: #9E0808;
                    color: #F0F09E;
                    }
                    .t_users{
                    width: 100%;
                    border: 1px solid #9E0808;
                    margin-bottom: 5px;
                    margin-top: 5px;
                    }
                    .users{
                    background-color: #F0F09E;
                    color: #9E0808;
                    }
                </style>
            </head>
            <body>
                <div id="ms">
                    <h1>Message History</h1>
                </div>
                <table class="t_login">
                    <xsl:for-each  select="MessageHistory/Login">
                        <tr class="login">
                            <td>
                                Login :<xsl:value-of select="@userName" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <xsl:for-each select="./UserTo" >
                                    <table class="t_users">
                                        <tr class="users">
                                            <td>
                                                User Name :<xsl:value-of select="@userName" />
                                            </td>
                                        </tr>
                                        <tr class="users">
                                            <td>
                                                Messages :
                                                <xsl:for-each select="./Body/Text" >
                                                    <table>
                                                        <tr class="users">
                                                            <td>
                                                                <xsl:value-of select="." />
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </xsl:for-each>
                                            </td>
                                        </tr>
                                    </table> 
                                </xsl:for-each>
                            </td>
                        </tr> 
                    </xsl:for-each>             
                </table>
            </body>
        </html>
    </xsl:template>
    
   

</xsl:stylesheet>
