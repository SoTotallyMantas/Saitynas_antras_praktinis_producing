<?xml version = "1.0" encoding = "UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <body>
                <h2>Orders</h2>
                <table border="1">
                    <tr bgcolor="#aba3a2">
                        <th>Address</th>
                        <th>Client</th>
                        <th>Driver</th>
                        <th>Dispatch</th>
                    </tr>

                    <xsl:for-each select="OrderList/Order">
                        <tr>
                            <td bgcolor="#ffff99">
                                <xsl:value-of select="address"/>
                            </td>


                            <td bgcolor="#ffcccc">
                                <xsl:call-template name="displayClient"/>
                            </td>
                            <td bgcolor="#ccffcc">
                                <xsl:call-template name="displayDriver"/>
                            </td>
                            <td bgcolor="#ccccff">
                                <xsl:call-template name="displayDispatch"/>
                            </td>

                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>



    <xsl:template name="displayClient">
        <xsl:apply-templates select="client"/>
    </xsl:template>

    <xsl:template match="client">
        <table border="1">
            <tr bgcolor="#ffcccc">
                <th>Client ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Phone Number</th>
            </tr>
            <tr>
                <td>
                    <xsl:value-of select="id"/>
                </td>
                <td>
                    <xsl:value-of select="FirstName"/>
                </td>
                <td>
                    <xsl:value-of select="Lastname"/>
                </td>
                <td>
                    <xsl:value-of select="Phone_Number"/>
                </td>
            </tr>
        </table>
    </xsl:template>

    <xsl:template name="displayDriver">
        <xsl:apply-templates select="driver"/>
    </xsl:template>

    <xsl:template match="driver">
        <table border="1">
            <tr bgcolor="#ccffcc">
                <th>Driver ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Phone Number</th>
                <th>License Plate</th>
            </tr>
            <tr>
                <td>
                    <xsl:value-of select="id"/>
                </td>
                <td>
                    <xsl:value-of select="FirstName"/>
                </td>
                <td>
                    <xsl:value-of select="Lastname"/>
                </td>
                <td>
                    <xsl:value-of select="Phone_Number"/>
                </td>
                <td>
                    <xsl:value-of select="License_Plate"/>
                </td>
            </tr>
        </table>
    </xsl:template>

    <xsl:template name="displayDispatch">
        <xsl:apply-templates select="dispatch"/>
    </xsl:template>

    <xsl:template match="dispatch">
        <table border="1">
            <tr bgcolor="#ccccff">
                <th>Dispatch ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Phone Number</th>
                <th>Work Number</th>
            </tr>
            <tr>
                <td>
                    <xsl:value-of select="id"/>
                </td>
                <td>
                    <xsl:value-of select="FirstName"/>
                </td>
                <td>
                    <xsl:value-of select="Lastname"/>
                </td>
                <td>
                    <xsl:value-of select="Phone_Number"/>
                </td>
                <td>
                    <xsl:value-of select="Work_Number"/>
                </td>
            </tr>
        </table>
    </xsl:template>

</xsl:stylesheet>
